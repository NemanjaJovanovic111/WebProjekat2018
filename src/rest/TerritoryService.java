package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.TerritoryDAO;
import dao.VolunteerDAO;
import dto.TerritoryDTO;
import model.Territory;
import model.Volunteer;

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
		List<Territory> territories = territoryDAO.getAll();
		return territories;
	}
	
	@GET
	@Path("/getOne")
	@Produces(MediaType.APPLICATION_JSON)
	public Territory getOneTerritory(@QueryParam("territoryId") String territoryId) throws FileNotFoundException, IOException {
		TerritoryDAO territoryDAO = TerritoryDAO.getInstance();
		System.out.println(territoryId);
		Territory territory = territoryDAO.getOne(territoryId);
		System.out.println(territory);
		return territory;
		
	}
	
	@POST
	@Path("/deleteTerritories")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteTerritories(List<String> territoryIds) throws FileNotFoundException, IOException {
		System.out.println("afeafeaea");
		TerritoryDAO territoryDAO = TerritoryDAO.getInstance();
		territoryDAO.deleteTerritories(territoryIds);
	}
	
	@POST
	@Path("/updateTerritory")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTerritory(List<Territory> ter) throws FileNotFoundException, IOException{
		TerritoryDAO territoryDAO  = TerritoryDAO.getInstance();
		territoryDAO.updateAllTerritories(ter);
	}
	
}
