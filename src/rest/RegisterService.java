package rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dao.VolunteerDAO;
import model.Volunteer;


@Path("/registerService")
public class RegisterService {
	
	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Volunteer registerVolunteer(
			@FormDataParam("username") String username, 
			@FormDataParam("password") String password,
			@FormDataParam("email") String email,
			@FormDataParam("firstName") String firstName, 
			@FormDataParam("lastName") String lastName,
			@FormDataParam("phoneNumber") String phoneNumber,
			@FormDataParam("territory") String territory,
			@FormDataParam("image") InputStream fileInputStream,
			@FormDataParam("image") FormDataContentDisposition contentDispositionHeader) throws IOException, URISyntaxException {
		
		
		String imagesDirPath = Util.getAbsolutePathToImagesDir("users");
		Util.savePicture(imagesDirPath, username, fileInputStream);
		
		VolunteerDAO volunteerDAO = VolunteerDAO.getInstance();
		String relativePathToImage = Util.getRelativePathToImage("users", username);
		if(!volunteerDAO.volunteersExists(username))
			return volunteerDAO.createVolunteer(username, password, email, firstName, lastName, 
					relativePathToImage, phoneNumber, territory);
		else
			return null;
	}	

}