package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Admin;
import model.User;
import model.Volunteer;

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
