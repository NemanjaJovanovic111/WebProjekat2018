package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Admin;
import rest.Util;

public class AdminDAO {
	
	private static AdminDAO instance = null;
	private static String filePath;
	private ArrayList<Admin> admins = new ArrayList< Admin>();
	
	public AdminDAO() throws FileNotFoundException, IOException {
		filePath = Util.getAbsolutePathToDeployedApp() + "admins.json";
		loadAdmins();
	}

	public static AdminDAO getInstance() throws FileNotFoundException, IOException{
		if (instance == null)
			instance = new AdminDAO();
		return instance;
	}
	
	private void loadAdmins() throws FileNotFoundException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		admins = new ObjectMapper().readValue(content, new TypeReference<List<Admin>>(){});
	}

	public boolean adminExists(String username) {
		for(Admin a : admins) {
			if(a.getUsername().toLowerCase().trim().equals(username.toLowerCase().trim()))
				return true;
		}
		return false;
	}

	public ArrayList<Admin> getAll() {
		return admins;
	}

}
