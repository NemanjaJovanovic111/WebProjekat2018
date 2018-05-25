package model;

public class Volunteer extends User {
	
	private boolean status = false;

	public Volunteer() {
		super();
	}

	public Volunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, UserType userType, String phoneNumber, String territory) {
		super(username, password, email, firstName, lastName, profilePicture, userType, phoneNumber, territory);
	}

	public Volunteer(String username, String password, String email, String firstName, String lastName,
			String profilePicture, UserType userType, String phoneNumber, String territory, boolean status) {
		super(username, password, email, firstName, lastName, profilePicture, userType, phoneNumber, territory);
		this.status = status;
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
