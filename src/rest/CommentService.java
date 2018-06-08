package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.CommentDAO;
import dto.CommentDTO;
import model.Comment;

@Path("/commentService")
public class CommentService {
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment create(CommentDTO commentDTO) throws URISyntaxException, FileNotFoundException, IOException {
		CommentDAO commentDAO = CommentDAO.getInstance();
		Comment comm = commentDAO.createComment(commentDTO);
		return comm;
	}
	
	@GET
	@Path("/getByEmergencyId")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getByEmergencyId(@QueryParam("emergencyId") String emergencyId) throws FileNotFoundException, IOException {
		CommentDAO commentDAO = CommentDAO.getInstance();
		return commentDAO.getByEmergencyId(emergencyId);
	}


}

