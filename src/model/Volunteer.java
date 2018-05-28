package model;

import dto.VolunteerDTO;

public class Volunteer extends User {
	
	private boolean status = false;

	public Volunteer() {
		super();
	}

	public Volunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, String phoneNumber, String territory) {
		super(username, password, email, firstName, lastName, profilePicture, UserType.VOLUNTEER, phoneNumber, territory);
		status = false;
	}

	public Volunteer(VolunteerDTO dto){
		username = dto.getUsername();
		password = dto.getPassword();
		email  = dto.getEmail();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		profilePicture = dto.getProfilePicture();
		userType = UserType.VOLUNTEER;
		phoneNumber  = dto.getPhoneNumber();
		territory = dto.getTerritory();	
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Volunteer [status=" + status + "]";
	}
	
}
