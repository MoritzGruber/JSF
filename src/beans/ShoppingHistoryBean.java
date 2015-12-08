package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import utils.Utils;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class ShoppingHistoryBean implements Serializable {
	private User currentUser;
	
	public ShoppingHistoryBean() {
		currentUser = (User) Utils.getSession().get("currentUser");
	}

	public User getCurrentUser() {
		return currentUser;
	}
	
}
