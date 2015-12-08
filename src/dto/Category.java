package dto;

import java.io.Serializable;
import java.util.List;

import persistence.ArticleDAO;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int number;
	private String name;

	public Category(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getNameWithArticles() {
		List<Article> articles = ArticleDAO.getInstance().findForCategory(name);
		
		return name + " (" + articles.size() + ")";
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		List<Article> articles = ArticleDAO.getInstance().findForCategory(name);
		
		return articles;
	}
	
	public String toString() {
		return name + " (" + number + ")";
	}

	public int getNumber() {
		return number;
	}

}
