package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import utils.Utils;
import dto.CartElement;
import dto.ShoppingCart;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class CartBean implements Serializable {
	private ShoppingCart cart;
	
	public CartBean() {
		User currentUser = (User) Utils.getSession().get("currentUser");
		cart = currentUser.getUserAccount().getCurrentCart();
	}
	
	public String updateCart() {
		for (CartElement elem : cart.getElements()) {
			if (elem.getNumberOfArticles() == 0) {
				cart.removeArticleCompletely(elem.getArticle());
			}
		}
		
		return "cart";
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
}
