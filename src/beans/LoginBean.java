package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.UserDAO;
import utils.Utils;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private Integer userId;
	private String password;
	private User currentUser;
	private String message;
	
	public String doLogin() {
		String result = "authenticationError";

		message = " ";
		User user = UserDAO.getInstance().findForId(userId);		
		if (user == null) {
			message = "Invalid user id!";
		} else if (!user.getPassword().equals(password)) {
			message = "Invalid user id/password pair!";			
		} else {
			result = "authenticated";
			Utils.getSession().put("currentUser", user); // now user is authenticated
			currentUser = user;
		}

		return result;
	}
	
	public String doLogout() {
		currentUser = null; // not logged in any more!
		return "login";
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public String getMessage() {
		return message;
	}
}
