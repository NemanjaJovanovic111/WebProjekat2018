package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
	String id;
	String textComm;
	LocalDateTime dateTime;
	String userCommId;
	
	public Comment(String textComm, LocalDateTime dateTime, String userCommId) {
		super();
		this.id = UUID.randomUUID().toString();
		this.textComm = textComm;
		this.dateTime = LocalDateTime.now();
		this.userCommId = userCommId;
	}
	
	public String getTextComm() {
		return textComm;
	}
	
	public void setTextComm(String textComm) {
		this.textComm = textComm;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getUserCommId() {
		return userCommId;
	}
	
	public void setUserCommId(String userCommId) {
		this.userCommId = userCommId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}