package dto;

import java.io.Serializable;

import persistence.CategoryDAO;
import utils.Utils;

@SuppressWarnings("serial")
public class Article implements Serializable {
	private int number;
	private String name;
	private String description;
	private String imagePath;
	private double price;
	private boolean deleted = false;
	private String categoryName;
	private Object file;

	public String viewArticle() {
		Utils.getSession().put("currentArticle", this);
		return "articleView";
	}
	
	public Article(int number, String name, 
			String description,
			double price, String categoryName) {
		super();
		this.number = number;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryName = categoryName;
	}

	public Object getFile() {
		return file;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return CategoryDAO.getInstance().findForName(categoryName);
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public String toString() {
		return "Article (name=" + name + ", imagePath=" + imagePath +
				")";
	}
}
