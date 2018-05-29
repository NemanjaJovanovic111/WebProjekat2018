package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Emergency;
import model.EmergencyState;
import model.EmergencyType;
import rest.Util;

public class EmergencyDAO {
	
	private static EmergencyDAO instance = null;
	private static String filePath;
	private ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
	
	public EmergencyDAO() throws FileNotFoundException, IOException {
		filePath = Util.getAbsolutePathToDeployedApp() + "emergency.json";
		loadEmergencies();
	}

	public static EmergencyDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new EmergencyDAO();
		return instance;
	}
	
	private void loadEmergencies() throws FileNotFoundException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		emergencies = new ObjectMapper().readValue(content, new TypeReference<List<Emergency>>(){});
	}
	
	private void saveEmergencies() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
		writer.writeValue(new File(filePath), emergencies);
	}
	
	public Emergency createEmergency(String id, String locationName, String municipalitie, String description,
			String gMapLoc, String territory, EmergencyType emergencyType, String picture, EmergencyState emergencyState) throws JsonGenerationException, JsonMappingException, IOException {
		Emergency emergency = new Emergency(id, locationName, municipalitie, description,
				gMapLoc, territory, emergencyType, picture, emergencyState);
		emergencies.add(emergency);
		saveEmergencies();
		return emergency;
	}
	
	public ArrayList<Emergency> getAll(){
		return emergencies;
	}

}
