package rest;

public class Util {
	
	public static String getPathToDeployedApp() {
		String path = Util.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		return path;
	}

}
