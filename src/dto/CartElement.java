package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartElement implements Serializable {
	private Article article;
	private int numberOfArticles;
	
	public CartElement(Article article, int numberOfArticles) {
		super();
		this.article = article;
		this.numberOfArticles = numberOfArticles;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
	public int getNumberOfArticles() {
		return numberOfArticles;
	}

	public void setNumberOfArticles(int numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

}
