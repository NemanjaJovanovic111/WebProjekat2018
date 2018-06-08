package model;

import java.util.UUID;

import dto.CommentDTO;
import rest.Util;

public class Comment {
	
	String id;
	String textComm;
	String dateTime;
	String username;
	String emergencyId;
	
	public Comment() {}
	
	public Comment(String id, String textComm, String dateTime, String username, String emergencyId) {
		super();
		this.id = id;
		this.textComm = textComm;
		this.dateTime = dateTime;
		this.username = username;
		this.emergencyId = emergencyId;
	}

	public Comment(CommentDTO dto) {
		this.id = UUID.randomUUID().toString();
		this.textComm = dto.getTextComm();
		this.dateTime = Util.getCurrentDateTime();
		this.username = dto.getUsername();
		this.emergencyId = dto.getEmergencyId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTextComm() {
		return textComm;
	}

	public void setTextComm(String textComm) {
		this.textComm = textComm;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
		return "Comment [id=" + id + ", textComm=" + textComm + ", dateTime=" + dateTime + ", username=" + username
				+ ", emergencyId=" + emergencyId + "]";
	}
	
}