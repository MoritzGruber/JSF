package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.UserDAO;
import utils.Utils;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ManageUsersBean implements Serializable {
	private String currentUserId;
	private List<User> users;
	
	public List<User> getUsers() {
		users = UserDAO.getInstance().findAll();
		Collections.sort(users, new Comparator<User>() {
			public int compare(User u1, User u2) {
				return u1.getName().compareTo(u2.getName());
			}			
		});
		
		return users;
	}

	public String newUser() {
		User newUser = new User(
				UserDAO.getInstance().getMaxUserId() + 1, 
				"", "", "", "", "", "");
		
		try {
			UserDAO.getInstance().create(newUser);
		} catch (Exception e) {
			Utils.createErrorMessage(e.getMessage());
		}		
		
		return "manageUsers";
	}
	
	public String deleteUser() throws Exception {
		User currentUser = UserDAO.getInstance().findForId(
				new Integer(currentUserId));
		UserDAO.getInstance().delete(currentUser);
		
		return "manageUsers";
	}
	
	public String save() {
		UserDAO.getInstance().updateAllAndPersist(users);
		return "manageUsers";
	}
	
	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
}
