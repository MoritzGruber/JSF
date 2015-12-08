package beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import utils.Utils;
import dto.ShoppingCart;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class CheckoutBean implements Serializable {
	private ShoppingCart cart;
	
	public CheckoutBean() {
		User currentUser = (User) Utils.getSession().get("currentUser");
		cart = currentUser.getUserAccount().getCurrentCart();
	}
	
	public String doCheckout() {
		TimeZone tz = TimeZone.getTimeZone("GMT+1");
		Date now = Calendar.getInstance(tz).getTime();
		cart.setPayedDate(now);
		cart.getUser().getUserAccount().getShoppingHistory().add(cart);
		ShoppingCart newCart = new ShoppingCart(cart.getUser());
		cart.getUser().getUserAccount().setCurrentCart(newCart);
		cart = newCart;

		return "checkoutDone";
	}
	
	public ShoppingCart getCart() {
		return cart;
	}

}
