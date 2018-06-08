package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import dto.CommentDTO;
import model.Comment;
import rest.Util;

public class CommentDAO {
	
	private static CommentDAO instance = null;
	private static String filePath;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public CommentDAO() throws FileNotFoundException, IOException {
		filePath = Util.getAbsolutePathToDeployedApp() + "comments.json";
		loadComments();
	}

	public static CommentDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new CommentDAO();
		return instance;
	}
	
	private void loadComments() throws FileNotFoundException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		comments = new ObjectMapper().readValue(content, new TypeReference<List<Comment>>(){});
	}
	
	public void saveComments() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
		writer.writeValue(new File(filePath), comments);
	}

	public ArrayList<Comment> getAll() {
		return comments;
	}
	
	public Comment createComment(CommentDTO commentDTO) throws JsonGenerationException, JsonMappingException, IOException {
		Comment comment = new Comment(commentDTO);
		comments.add(comment);
		saveComments();
		return comment;
	}
	
	public ArrayList<Comment> getByEmergencyId(String emergencyId) {
		ArrayList<Comment> comms = new ArrayList<Comment>();
		for(Comment c : comments) {
			if(c.getEmergencyId().equals(emergencyId))
				comms.add(c);
		}
		return comms;
	}

}
