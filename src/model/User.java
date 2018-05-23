package model;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String profilePicture;
	
	private String phoneNumber;
	private String territory;
	private UserType userType;
	
	public User() {
		super();
	}

	public User(String username, String password, String email, String firstName, String lastName, String profilePicture,
			String phoneNumber, String territory, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.phoneNumber = phoneNumber;
		this.territory = territory;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", profilePicture=" + profilePicture + ", phoneNumber=" + phoneNumber
				+ ", territory=" + territory + ", userType=" + userType + "]";
	}
	
}

