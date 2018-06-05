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

	
	
	public static User edit(String username,
							String newPassword,
							String oldPassword,
							String email,
							String firstName,
							String lastName,
							String phoneNumber,
							String territoryId,
							String imagePath,
							UserType userType) throws FileNotFoundException, IOException {
		
		User updatedUser = null;
		if(userType == UserType.ADMIN) {
			AdminDAO dao = AdminDAO.getInstance();
			for(User u : dao.getAll()) {
				if(u.getUsername().equals(username)) {
					updatedUser = editUserFields(u, newPassword, oldPassword, email, firstName, 
							lastName, imagePath, phoneNumber, territoryId);
					dao.saveAdmins();
					break;
				}
			}
		}
		else if(userType == UserType.VOLUNTEER) {
			VolunteerDAO dao = VolunteerDAO.getInstance();
			for(User u : dao.getAll()) {
				if(u.getUsername().equals(username)){
					updatedUser = editUserFields(u, newPassword, oldPassword, email, firstName, 
							lastName, imagePath, phoneNumber, territoryId);
					dao.saveVolunteers();
					break;
				}
			}
		}
		return updatedUser;
	}
	
	private static User editUserFields(User userToEdit, String newPassword, String oldPassword, String email,
			String firstName, String lastName, String imagePath, String phoneNumber, String territoryId) {
		if(!newPassword.equals("") && userToEdit.getPassword().equals(oldPassword)) {
			userToEdit.setPassword(newPassword);
		}
		if(!imagePath.equals("")) {
			userToEdit.setProfilePicture(imagePath);
		}
		
		userToEdit.setEmail(email);
		userToEdit.setFirstName(firstName);
		userToEdit.setLastName(lastName);
		userToEdit.setPhoneNumber(phoneNumber);
		userToEdit.setTerritoryId(territoryId);
		
		return userToEdit;
	}
			

}
