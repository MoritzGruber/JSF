package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ShoppingCart implements Serializable {
	private Date payedDate = null;
	private User user;
	private Map<Integer, CartElement> elements = new HashMap<Integer, CartElement>(); 
	
	public ShoppingCart(User user) {
		this.user = user;
	}
	
	public void addArticle(Article article) {
		CartElement elem = elements.get(article.getNumber());
		
		if (elem == null) {
			elements.put(article.getNumber(), new CartElement(article, 1));
		} else {
			elem.setNumberOfArticles(elem.getNumberOfArticles() + 1);
		}
	}
	
	public void removeArticle(Article article) {
		CartElement elem = elements.get(article.getNumber());
		
		if (elem != null && elem.getNumberOfArticles() > 0) {
			elem.setNumberOfArticles(elem.getNumberOfArticles() - 1);
		}
	}
	
	public void removeArticleCompletely(Article article) {
		CartElement elem = elements.get(article.getNumber());
		
		if (elem != null) {
			elements.remove(article.getNumber());
		}
	}
	
	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public User getUser() {
		return user;
	}

	public List<CartElement> getElements() {
		List<CartElement> result = new ArrayList<CartElement>();
		
		for (Map.Entry<Integer, CartElement> entry : elements.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}
	
	public double getTotal() {
		double result = 0.;
		
		for (CartElement elem : getElements()) {
			result += elem.getNumberOfArticles() * elem.getArticle().getPrice();
		}
		
		return result;
	}

}
