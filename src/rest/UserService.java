package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dao.UserDAO;
import dto.LoginDTO;
import model.User;
import model.UserType;

@Path("/userService")
public class UserService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(LoginDTO loginDto) throws URISyntaxException, FileNotFoundException, IOException {
		System.out.println(Util.getAbsolutePathToDeployedApp());
		User user = UserDAO.login(loginDto.getUsername(), loginDto.getPassword());
		return user;
	}
	
	@POST
	@Path("/updateUser")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public static User updateUser(
			@FormDataParam("username") String username,
			@FormDataParam("newPassword") String newPassword, 
			@FormDataParam("oldPassword") String oldPassword,
			@FormDataParam("email") String email,
			@FormDataParam("firstName") String firstName, 
			@FormDataParam("lastName") String lastName,
			@FormDataParam("phoneNumber") String phoneNumber,
			@FormDataParam("territoryId") String territoryId,
			@FormDataParam("userType") UserType userType,
			@FormDataParam("image") InputStream fileInputStream,
			@FormDataParam("image") FormDataContentDisposition contentDispositionHeader) throws FileNotFoundException, IOException {
		
		String relativePathToImage = "";
		
		// if new profilePicture has been selected
		if(contentDispositionHeader.getFileName() != null) {
			String pictureFileName = UUID.randomUUID().toString();
			String imagesDirPath = Util.getAbsolutePathToImagesDir("users");
			
			Util.savePicture(imagesDirPath, pictureFileName, fileInputStream);
			relativePathToImage = Util.getRelativePathToImage("users", pictureFileName);
		}

		
		User updatedUser = UserDAO.edit(username, newPassword, oldPassword, email, firstName, lastName,
				phoneNumber, territoryId, relativePathToImage, userType);

		return updatedUser;
	}

}
