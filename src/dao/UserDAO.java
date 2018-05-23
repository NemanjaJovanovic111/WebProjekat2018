package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import rest.Util;

public class UserDAO {
	
	private static UserDAO instance = null;
	private static String filePath;
	private ArrayList<User> users = new ArrayList<User>();
	
	public UserDAO() throws FileNotFoundException, IOException {
		filePath = Util.getPathToDeployedApp() + "admins.json";
		loadUsers();
	}

	public static UserDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}
	
	private void loadUsers() throws FileNotFoundException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		users = new ObjectMapper().readValue(content, new TypeReference<List<User>>(){});
	}

	public User login(String username, String password) {
		
		for (User u : users) {
			if (u.getUsername().equals(username) && 
			    u.getPassword().equals(password)) {
				
				return u;
			}
		}
		return null;
	}

}
