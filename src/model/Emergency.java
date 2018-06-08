package model;

import org.joda.time.DateTime;

public class Emergency {
	
	String id;
	String locationName;
	String municipalitie;
	String description;
	String dateTime;
	String gMapLoc;
	String territory;
	EmergencyType emergencyType;
	String picture;
	EmergencyState emergencyState;
	String volunteer;
	
	public Emergency(){}
	
	public Emergency(String id, String locationName, String municipalitie, String description,
			String gMapLoc, String territory, EmergencyType emergencyType, String picture,
			EmergencyState emergencyState) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.municipalitie = municipalitie;
		this.description = description;
		setDateTime();
		this.gMapLoc = gMapLoc;
		this.territory = territory;
		this.emergencyType = emergencyType;
		this.picture = picture;
		this.emergencyState = emergencyState;
		this.volunteer = "";
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getMunicipalitie() {
		return municipalitie;
	}

	public void setMunicipalitie(String municipalitie) {
		this.municipalitie = municipalitie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public void setDateTime() {
		DateTime currentDateTime = DateTime.now();
		String year = Integer.toString(currentDateTime.getYear());
		String month = Integer.toString(currentDateTime.getMonthOfYear());
		String day = Integer.toString(currentDateTime.getDayOfMonth());
		String hour = Integer.toString(currentDateTime.getHourOfDay());
		String minute = Integer.toString(currentDateTime.getMinuteOfHour());
		
		setDateTime(day + "." + month + "." + year + " " + hour + ":" + minute);
	}

	public String getgMapLoc() {
		return gMapLoc;
	}

	public void setgMapLoc(String gMapLoc) {
		this.gMapLoc = gMapLoc;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public EmergencyType getEmergencyType() {
		return emergencyType;
	}

	public void setEmergencyType(EmergencyType emergencyType) {
		this.emergencyType = emergencyType;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public EmergencyState getEmergencyState() {
		return emergencyState;
	}

	public void setEmergencyState(EmergencyState emergencyState) {
		this.emergencyState = emergencyState;
	}

	public String getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(String volunteer) {
		this.volunteer = volunteer;
	}

	@Override
	public String toString() {
		return "Emergency [locationName=" + locationName + ", municipalitie=" + municipalitie + ", description="
				+ description + ", dateTime=" + dateTime + ", gMapLoc=" + gMapLoc + ", territory=" + territory
				+ ", emergencyType=" + emergencyType + ", picture=" + picture + ", emergencyState=" + emergencyState
				+ ", volunteer=" + volunteer + "]";
	}

}
