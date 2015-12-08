package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import utils.Utils;
import dto.Article;
import dto.User;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ArticleViewBean implements Serializable {
	private String addedMessage;
	
	public String addToCart() {
		User user = (User) Utils.getSession().get("currentUser");
		
		user.getUserAccount().getCurrentCart().addArticle(getCurrentArticle());
		addedMessage = "Article added to cart.";
		
		return "articleView";
	}

	public Article getCurrentArticle() {
		Article currentArticle = (Article) Utils.getSession().get("currentArticle");
		return currentArticle;
	}

	public String getAddedMessage() {
		String result = addedMessage;
		addedMessage = null; // only show once
		
		return result;
	}

	public void setAddedMessage(String addedMessage) {
		this.addedMessage = addedMessage;
	}
}
