package rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/registerService")
public class RegisterService {
	
	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public char uploadFile(
			@FormDataParam("username") String username, 
			@FormDataParam("password") String password,
			@FormDataParam("conf_password") String confPassword,
			@FormDataParam("email") String email,
			@FormDataParam("name") String name, 
			@FormDataParam("surname") String surname,
			@FormDataParam("phone_number") Integer phoneNumber,
			@FormDataParam("territory") String territory,
			@FormDataParam("image") InputStream fileInputStream,
			@FormDataParam("image") FormDataContentDisposition contentDispositionHeader) throws IOException, URISyntaxException {
		
		String userImagesDirAbsolutePath = Util.getPathToDeployedApp() + "../../../users/";
		File userImagesDir = new File(userImagesDirAbsolutePath);
		
		if(!userImagesDir.exists())
			userImagesDir.mkdirs();
		
		String userImageAbsolutePath = userImagesDirAbsolutePath + username + ".jpg";
		
		saveFile(fileInputStream, userImageAbsolutePath);
		fileInputStream.close();
		
		return 'c';	
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