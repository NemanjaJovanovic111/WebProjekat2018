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
	

	public static User login(String username, String password) throws FileNotFoundException, IOException {
		
		ArrayList<Admin> admins = AdminDAO.getInstance().getAll();
		ArrayList<Volunteer> volunteers = VolunteerDAO.getInstance().getAll();
		
		
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
