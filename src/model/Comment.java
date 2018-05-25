package model;

import java.time.LocalDateTime;

public class Comment {
	
	String textComm;
	LocalDateTime dateTime;
	String userCommId;
	
	public Comment(String textComm, LocalDateTime dateTime, String userCommId) {
		super();
		this.textComm = textComm;
		this.dateTime = dateTime;
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
	
}