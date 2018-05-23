package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import dto.TerritoryDTO;
import model.Territory;
import rest.Util;

	public class TerritoryDAO {
		
		private static TerritoryDAO instance = null;
		private static String filePath;
		private ArrayList<Territory> territories = new ArrayList<Territory>();
		
		public TerritoryDAO() throws FileNotFoundException, IOException {
			filePath = Util.getPathToDeployedApp() + "territory.json";
			loadTerritories();
		}

		public static TerritoryDAO getInstance() throws FileNotFoundException, IOException{
			if (instance == null)
				instance = new TerritoryDAO();
			return instance;
		}
		
		private void loadTerritories() throws FileNotFoundException, IOException {
			String content = new String(Files.readAllBytes(Paths.get(filePath)));
			territories = new ObjectMapper().readValue(content, new TypeReference<List<Territory>>(){});
		}
		
		private void saveTerritories() throws JsonGenerationException, JsonMappingException, IOException {
			ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
			writer.writeValue(new File(filePath), territories);
		}

		public boolean territoryExists(String name) {
			for(Territory t : territories) {
				if(t.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
					return true;
			}
			return false;
		}
		
		public Territory createTerritory(TerritoryDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
			Territory territory = new Territory(dto);
			territories.add(territory);
			saveTerritories();
			return territory;
		}

	}


