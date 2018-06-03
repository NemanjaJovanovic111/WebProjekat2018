package model;

public class Volunteer extends User {
	
	private UserStatus userStatus = UserStatus.ACTIVE;

	public Volunteer() {
		super();
	}

	public Volunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, String phoneNumber, String territoryId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.userType = UserType.VOLUNTEER;
		this.phoneNumber = phoneNumber;
		this.territoryId = territoryId;
		this.userStatus = UserStatus.ACTIVE;
	}
	
	public Volunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, UserType userType, String phoneNumber, String territoryId, UserStatus userStatus) {
		super(username, password, email, firstName, lastName, profilePicture, userType, phoneNumber, territoryId);
		this.userStatus = userStatus;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "Volunteer [userStatus=" + userStatus + "]";
	}
	
}
