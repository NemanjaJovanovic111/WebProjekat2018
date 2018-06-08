package rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.joda.time.DateTime;

public class Util {
	
	public static String getAbsolutePathToDeployedApp() {
		return Util.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}
	
	// absolute path to images folders on deployed app
	public static String getAbsolutePathToImagesDir(String objects) {
		return getAbsolutePathToDeployedApp() + "../../" + objects + "/";
	}
	
	// path to image for displaying in html pages
	public static String getRelativePathToImage(String objects, String fileName) {
		return "./" + objects + "/" + fileName + ".jpg";
	}
	
	public static void savePicture(String imagesDirPath, String fileName, InputStream fileInputStream) throws IOException { 
		File userImagesDir = new File(imagesDirPath);
		
		if(!userImagesDir.exists())
			userImagesDir.mkdirs();
		
		String userImageAbsolutePath = imagesDirPath + fileName + ".jpg";
		
		saveFile(fileInputStream, userImageAbsolutePath);
		fileInputStream.close();
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
	
	public static String getCurrentDateTime() {
		DateTime currentDateTime = DateTime.now();
		String year = Integer.toString(currentDateTime.getYear());
		String month = Integer.toString(currentDateTime.getMonthOfYear());
		String day = Integer.toString(currentDateTime.getDayOfMonth());
		String hour = Integer.toString(currentDateTime.getHourOfDay());
		String minute = Integer.toString(currentDateTime.getMinuteOfHour());
		
		return day + "." + month + "." + year + " " + hour + ":" + minute;
	}
	
	

}
