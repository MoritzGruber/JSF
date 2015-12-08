package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import beans.ApplicationContext;

public class Utils {
	static public void createErrorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(message));
	}
	
	static public Map<String, Object> getSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		return context.getExternalContext().getSessionMap();
	}
	
	static public Object loadFromFile(String path) {
		Object result = null;
		
		try {
			File inputFile = new File(path);
			
			if (!inputFile.exists()) {
				inputFile.createNewFile();
			}
				
			FileInputStream fileInput = new FileInputStream(path);
			if (fileInput.available() != 0) {
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				result = objectInput.readObject();
				objectInput.close();
				fileInput.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	static public void storeToFile(String path, Object data) {
		try {
			File outputFile = new File(path);
			
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
				
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(data);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public String getPersistencePath(String fileName) {
		String persistenceFolder = ApplicationContext.getPersistenceFolder();
		
		return persistenceFolder + File.separatorChar + fileName;
	}
	
}
