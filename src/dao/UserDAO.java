package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Admin;
import model.User;
import model.Volunteer;
import rest.Util;

public class UserDAO {
	
	private static UserDAO instance = null;
	private ArrayList<Admin> admins = new ArrayList<Admin>();
	private ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
	
	public UserDAO() throws FileNotFoundException, IOException {
		loadUsers();
	}

	public static UserDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}
	
	private void loadUsers() throws FileNotFoundException, IOException {
		loadAdmins();
		loadVolunteers();
	}
	
	private void loadAdmins() throws JsonParseException, JsonMappingException, IOException {
		String filePath = Util.getPathToDeployedApp() + "admins.json";
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		admins = new ObjectMapper().readValue(content, new TypeReference<List<Admin>>(){});		
	}
	
	private void loadVolunteers() throws JsonParseException, JsonMappingException, IOException {
		String filePath = Util.getPathToDeployedApp() + "volunteers.json";
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		volunteers = new ObjectMapper().readValue(content, new TypeReference<List<Volunteer>>(){});
		
		for(Volunteer v : volunteers) { 
			System.out.println(v);
		}
	}

	public User login(String username, String password) {
		
		for (User u : admins) {
			if (u.getUsername().equals(username) && 
			    u.getPassword().equals(password)) {
				
				return u;
			}
		}
		
		for (User u : volunteers) {
			if (u.getUsername().equals(username) && 
			    u.getPassword().equals(password)) {
				
				return u;
			}
		}
		
		return null;
	}

}
