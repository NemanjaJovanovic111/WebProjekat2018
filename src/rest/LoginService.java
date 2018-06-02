package rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.UserDAO;
import dto.LoginDTO;
import model.User;

@Path("/loginService")
public class LoginService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(LoginDTO loginDto) throws URISyntaxException, FileNotFoundException, IOException {
		System.out.println(Util.getAbsolutePathToDeployedApp());
		User user = UserDAO.login(loginDto.getUsername(), loginDto.getPassword());
		return user;
	}
	
}
