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

import model.Volunteer;
import rest.Util;

public class VolunteerDAO {
	
	private static VolunteerDAO instance = null;
	private static String filePath;
	private ArrayList< Volunteer> volunteers = new ArrayList< Volunteer>();
	
	public VolunteerDAO() throws FileNotFoundException, IOException {
		filePath = Util.getAbsolutePathToDeployedApp() + "volunteers.json";
		loadVolunteers();
	}

	public static VolunteerDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new VolunteerDAO();
		return instance;
	}
	
	private void loadVolunteers() throws FileNotFoundException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		volunteers = new ObjectMapper().readValue(content, new TypeReference<List<Volunteer>>(){});
	}
	
	private void saveVolunteers() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
		writer.writeValue(new File(filePath), volunteers);
	}

	public boolean volunteersExists(String username) {
		for(Volunteer v : volunteers) {
			if(v.getUsername().toLowerCase().trim().equals(username.toLowerCase().trim()))
				return true;
		}
		return false;
	}
	
	public Volunteer createVolunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, String phoneNumber, String territory) throws JsonGenerationException, JsonMappingException, IOException {
		Volunteer volunteer = new Volunteer(username, password, email, firstName, lastName, profilePicture, phoneNumber, territory);
		volunteers.add(volunteer);
		saveVolunteers();
		return volunteer;
	}

	public ArrayList<Volunteer> getAll() {
				return volunteers;
	}

}
