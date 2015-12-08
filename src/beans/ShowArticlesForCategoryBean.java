package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.ArticleDAO;
import persistence.CategoryDAO;
import utils.Utils;
import dto.Article;
import dto.Category;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ShowArticlesForCategoryBean implements Serializable {
	private String selectedArticleNumber;
	private String selectedCategoryName;
		
	public List<Article> getArticles() {
		Category category = CategoryDAO.getInstance().findForName(selectedCategoryName);
		
		return category.getArticles();
	}
	
	public String viewArticle() {
		Utils.getSession().put("currentArticle", 
				ArticleDAO.getInstance().findForNumber(new Integer(selectedArticleNumber)));
		return "articleView";
	}

	public String getSelectedArticleNumber() {
		return selectedArticleNumber;
	}

	public void setSelectedArticleNumber(String selectedArticleNumber) {
		this.selectedArticleNumber = selectedArticleNumber;
	}

	public void setSelectedCategoryName(String selectedCategoryName) {
		this.selectedCategoryName = selectedCategoryName;
	}

	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}
}
