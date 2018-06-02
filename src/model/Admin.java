package model;

public class Admin extends User {

	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, String email, String firstName, String lastName,
			String profilePicture, UserType userType, String phoneNumber, String territory) {
		super(username, password, email, firstName, lastName, profilePicture, userType, phoneNumber, territory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getEmail()="
				+ getEmail() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getProfilePicture()=" + getProfilePicture() + ", getUserType()=" + getUserType()
				+ ", getPhoneNumber()=" + getPhoneNumber() + ", getTerritory()=" + getTerritoryId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
