package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.TerritoryDAO;
import dto.TerritoryDTO;
import model.Territory;

@Path("/territoryService")
public class TerritoryService {
	
	@POST
	@Path("/addTerritory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Territory createTerritory(TerritoryDTO territoryDTO) throws FileNotFoundException, IOException{
		
		TerritoryDAO territoryDAO = TerritoryDAO.getInstance();
		
		if(territoryDAO.territoryExists(territoryDTO.getName()))
			return null;
		else {
			return territoryDAO.createTerritory(territoryDTO);
		}
		
	}
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Territory> getAllTerritory() throws FileNotFoundException, IOException {
		
		TerritoryDAO territoryDAO = TerritoryDAO.getInstance();
		return territoryDAO.getAll();
		
	}
	
}
