package rest;

import java.io.ByteArrayOutputStream;
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


@Path("/registerService")
public class RegisterService {
	
	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public char uploadFile(@FormDataParam("username") String username, 
			@FormDataParam("password") String password,
			@FormDataParam("conf_password") String confPassword,
			@FormDataParam("email") String email,
			@FormDataParam("name") String name, 
			@FormDataParam("surname") String surname,
			@FormDataParam("phone_number") Integer phoneNumber,
			@FormDataParam("territory") String territory,
			@FormDataParam("image") InputStream fileInputStream,
			@FormDataParam("image") FormDataContentDisposition contentDispositionHeader) throws IOException, URISyntaxException {
		
//		System.out.println(username + " : "+ password + " : "+ email + " : "+ name + " : "+ surname+ " : "+ phoneNumber  );
		System.out.println("hit!");
		return 'c';

	}
	
	public static void saveFile(InputStream in, String path) throws IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while ((n=in.read(buf)) != -1)
		{
		   out.write(buf, 0, n);
		}
		out.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos = new FileOutputStream(path);
		fos.write(response);
		fos.close();
		
	}

//	@POST
//	@Path("/register")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response register(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
//		
//		if (ServletFileUpload.isMultipartContent(request)) {
//			FileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			String uploadPath = Util.getPath();
//			System.out.println(uploadPath);
//			
//			String catalinaPath = request.getServletContext().getRealPath("/") + "images";
//			
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//			File catalinaDir = new File(catalinaPath);
//			if (!catalinaDir.exists()) {
//				catalinaDir.mkdir();
//			}
//			java.util.List items = null;
//			
//			try {
//				items = upload.parseRequest(request);
//			} catch (FileUploadException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if (items != null) {
//				Iterator <FileItem> iter = items.iterator();
//				
//				HashMap<String, String> parameters = new HashMap<String, String>();
//				while (iter.hasNext()) {
//					FileItem item = iter.next();
//					if (!item.isFormField() && item.getSize() > 0) {
//						String fileName = new File(item.getName()).getName();
//						String filePath = uploadPath + File.separator + fileName;
//						File storeFile = new File(filePath);
//						//item.write(storeFile);
//						File catalinaFile = new File(catalinaPath + "/" + fileName);
//						System.out.println(catalinaFile.getAbsolutePath());
//						item.write(catalinaFile);
//						parameters.put(item.getFieldName(), fileName);
//					}
//					else {
//						parameters.put(item.getFieldName(), item.getString());
//					}
//			}
//		
//		
//			String uname = parameters.get("username");
//			if (UserDao.getInstance().find(uname) != null) {
//				return new ResponseObject("", "Username already in use!");
//			}
//			String password = parameters.get("password");
//			String email = parameters.get("email");
//			String name = parameters.get("name");
//			String surname = parameters.get("surname");
//			String phone = parameters.get("phone");
//			String territory = parameters.get("territory");
//			String image = parameters.get("image");
//			
//			User newUser = new User(uname, password, email, image);
//			Volunteer newVol = new Volunteer(uname, name, surname, Integer.parseInt(phone), territory, false);
//			
//			UserDao.getInstance().getUsers().add(newUser);
//			UserDao.getInstance().saveUsers();
//			
//			VolunteerDao.getInstance().getVolunteers().add(newVol);
//			VolunteerDao.getInstance().saveVolunteers();
//			
//			return new ResponseObject("main.html", "");
//			}}
//		}
//		return null;
//	
//	}
	
}
