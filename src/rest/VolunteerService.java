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

import dao.VolunteerDAO;
import model.Volunteer;

@Path("/volunteerService")
public class VolunteerService {
	
	@GET
	@Path("/getVolWithTerritoryId")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Volunteer> getVolWithTerritoryId(@QueryParam("territoryId") String territoryId) throws FileNotFoundException, IOException {
		
		VolunteerDAO volunteerDAO = VolunteerDAO.getInstance();
		List<Volunteer> volunteers = volunteerDAO.sameVolTerId(territoryId);
		return volunteers;
	}
	
	
	@GET
	@Path("/getAllVolunteers")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Volunteer> getAllVolunteers() throws FileNotFoundException, IOException{
		VolunteerDAO volunteerDAO  = VolunteerDAO.getInstance();
		List<Volunteer> volunteers = volunteerDAO.getAll();
		return volunteers;
	}

	@POST
	@Path("/updateVolunteer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVol(List<Volunteer> vol) throws FileNotFoundException, IOException{
		VolunteerDAO volunteerDAO  = VolunteerDAO.getInstance();
		
		volunteerDAO.updateAll(vol);
		
		
		
			
			
	}

		
	
}