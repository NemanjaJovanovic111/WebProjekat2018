package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dao.EmergencyDAO;
import model.Emergency;
import model.EmergencyState;
import model.EmergencyType;

@Path("/emergencyService")
public class EmergencyService {
	
	@POST
	@Path("/emergencyCreate")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Emergency createEmergency(
			@FormDataParam("locationName") String locationName, 
			@FormDataParam("municipalitie") String municipalitie,
			@FormDataParam("description") String description,
			@FormDataParam("gMap") String gMap,
			@FormDataParam("territory") String territory,
			@FormDataParam("emergencyType") EmergencyType emergencyType,
			@FormDataParam("image") InputStream fileInputStream,
			@FormDataParam("image") FormDataContentDisposition contentDispositionHeader,
			@FormDataParam("emergencyState") EmergencyState emergencyState
	) throws URISyntaxException, FileNotFoundException, IOException {
		String emergencyId = UUID.randomUUID().toString();
		String imagesDirPath = Util.getAbsolutePathToImagesDir("emergencies");
		Util.savePicture(imagesDirPath, emergencyId, fileInputStream);
		
		EmergencyDAO emergencyDAO = EmergencyDAO.getInstance();
		String relativePathToImage = Util.getRelativePathToImage("emergencies", emergencyId);
		
		return emergencyDAO.createEmergency(emergencyId, locationName, municipalitie, description,
				gMap, territory, emergencyType, relativePathToImage, emergencyState);
		
	}
	
	@GET
	@Path("/getAllEmergencies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emergency> getAllEmergency() throws FileNotFoundException, IOException {
		EmergencyDAO emergencyDAO = EmergencyDAO.getInstance();
		return emergencyDAO.getAll();
		
	}
	
	@POST
	@Path("/updateEmergency")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmergency(List<Emergency> emergencies) throws FileNotFoundException, IOException{
		EmergencyDAO emergencyDAO  = EmergencyDAO.getInstance();
		emergencyDAO.updateAll(emergencies);
	}
	
	@GET
	@Path("/getActive")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Emergency> getActive() throws FileNotFoundException, IOException {
		EmergencyDAO emergencyDAO = EmergencyDAO.getInstance();
		return emergencyDAO.getActive();
	}

}
