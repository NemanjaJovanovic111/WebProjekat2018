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
import model.Volunteer;
import rest.Util;

	public class TerritoryDAO {
		
		private static TerritoryDAO instance = null;
		private static String filePath;
		private ArrayList<Territory> territories = new ArrayList<Territory>();
		
		public TerritoryDAO() throws FileNotFoundException, IOException {
			filePath = Util.getAbsolutePathToDeployedApp() + "territory.json";
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
		
		
		public ArrayList<Territory> getAll(){
			return territories;
		}
		
		public Territory getOne(String territoryId) {
			for(Territory t : territories) {
				if(t.getId().equals(territoryId))
					return t;
			}
			return null;
		}
		
		public void deleteTerritories(List<String> territoryIds) throws JsonGenerationException, JsonMappingException, IOException {
			
			for(String territoryId : territoryIds) {
				for(Territory t: territories) {
					if(t.getId().equals(territoryId)) {
						territories.remove(t);
						break;
					}
				}
			}
			
			saveTerritories();
			
		}
		
		public void updateAllTerritories(List<Territory> updatedTerritory) throws JsonGenerationException, JsonMappingException, IOException {
			for (Territory t : territories) {
				for (Territory updated : updatedTerritory) {
					if (t.getId().equals(updated.getId())) {
						t.setArea(updated.getArea());
						t.setName(updated.getName());
						t.setPopulation(updated.getPopulation());
					}
				}

			}

			saveTerritories();
		
		}
	}



