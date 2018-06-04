package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Admin;
import model.User;
import model.UserType;
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

	
	
	public static User edit(User updatedUser) throws FileNotFoundException, IOException {
		
		if(updatedUser.getUserType() == UserType.ADMIN) {
			AdminDAO dao = AdminDAO.getInstance();
			for(User u : dao.getAll()) {
				if(u.getUsername().equals(updatedUser.getUsername())){
					u.setPassword(updatedUser.getPassword());
					u.setEmail(updatedUser.getEmail());
					u.setFirstName(updatedUser.getFirstName());
					u.setLastName(updatedUser.getLastName());
					u.setProfilePicture(updatedUser.getProfilePicture());
					u.setPhoneNumber(updatedUser.getPhoneNumber());
					u.setTerritoryId(updatedUser.getTerritoryId());
				}
				return u;
			}
		}
		else if(updatedUser.getUserType() == UserType.VOLUNTEER) {
			VolunteerDAO dao = VolunteerDAO.getInstance();
			for(User u : dao.getAll()) {
				if(u.getUsername().equals(updatedUser.getUsername())){
					u.setPassword(updatedUser.getPassword());
					u.setEmail(updatedUser.getEmail());
					u.setFirstName(updatedUser.getFirstName());
					u.setLastName(updatedUser.getLastName());
					u.setProfilePicture(updatedUser.getProfilePicture());
					u.setPhoneNumber(updatedUser.getPhoneNumber());
					u.setTerritoryId(updatedUser.getTerritoryId());
				}
				return u;
			}
		}
		return null;
	}
			

}
