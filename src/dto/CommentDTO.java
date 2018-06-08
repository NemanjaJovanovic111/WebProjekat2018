package dto;

public class CommentDTO {
	
	String textComm;
	String username;
	String emergencyId;

	public CommentDTO() {}

	public CommentDTO(String textComm, String username, String emergencyId) {
		super();
		System.out.println("CREATIN DTO");
		this.textComm = textComm;
		this.username = username;
		this.emergencyId = emergencyId;
	}

	public String getTextComm() {
		return textComm;
	}

	public void setTextComm(String textComm) {
		this.textComm = textComm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmergencyId() {
		return emergencyId;
	}

	public void setEmergencyId(String emergencyId) {
		this.emergencyId = emergencyId;
	}

	@Override
	public String toString() {
		return "CommentDTO [textComm=" + textComm + ", username=" + username + ", emergencyId=" + emergencyId + "]";
	}
	
}
