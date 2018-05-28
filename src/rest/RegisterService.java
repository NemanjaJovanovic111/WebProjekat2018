package rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
		
		String picturePath = savePicture(username, fileInputStream);
		
		VolunteerDAO volunteerDAO = VolunteerDAO.getInstance();
		
		if(!volunteerDAO.volunteersExists(username))
			return volunteerDAO.createVolunteer(username, password, email, firstName, lastName, 
					picturePath, phoneNumber, territory);
		else
			return null;
	}	
	
	public static String savePicture(String username, InputStream fileInputStream) throws IOException { 
		String userImagesDirAbsolutePath = Util.getPathToDeployedApp() + "../../users/";
		File userImagesDir = new File(userImagesDirAbsolutePath);
		
		if(!userImagesDir.exists())
			userImagesDir.mkdirs();
		
		String userImageAbsolutePath = userImagesDirAbsolutePath + username + ".jpg";
		
		saveFile(fileInputStream, userImageAbsolutePath);
		fileInputStream.close();
		
		return "./users/" + username + ".jpg";
	}
	
	
	public static void saveFile(InputStream in, String path) throws IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
		   out.write(buffer, 0, n);
		}
		out.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos = new FileOutputStream(path);
		fos.write(response);
		fos.close();
		
	}

}