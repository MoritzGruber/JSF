package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class UserAccount implements Serializable {
	private User user;
	private String creditCardNumber;
	private List<ShoppingCart> shoppingHistory = new ArrayList<ShoppingCart>();
	private ShoppingCart currentCart;

	public UserAccount(User user, String creditCardNumber) {
		this.user = user;
		this.creditCardNumber = creditCardNumber;
		currentCart = new ShoppingCart(user);
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public List<ShoppingCart> getShoppingHistory() {
		return shoppingHistory;
	}
	
	public User getUser() {
		return user;
	}

	public ShoppingCart getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(ShoppingCart currentCart) {
		this.currentCart = currentCart;
	}

}
