package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ApplicationContext {
	static private String persistenceFolder = "/tmp";
	
	static public String getPersistenceFolder() {
		// besser als managedProperty in faces-config.xml
		return persistenceFolder;  
	}
	static public void setPersistenceFolder(String pPersistenceFolder) {
		persistenceFolder = pPersistenceFolder;
	}
}
