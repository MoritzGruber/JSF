package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.ArticleDAO;
import utils.Utils;
import dto.Article;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ArticleSearchBean implements Serializable {
	private String searchString;
	private List<Article> searchResult;
	private String currentSelection; // id of the current selection in the table

	public String search() {
		searchResult = ArticleDAO.getInstance().findForPrefix(searchString);
		return "articleSearch";
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<Article> getSearchResult() {
		return searchResult;
	}

	public String getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(String currentSelection) {
		this.currentSelection = currentSelection;
		Article currentArticle = ArticleDAO.getInstance().findForNumber(new Integer(currentSelection));
		Utils.getSession().put("currentArticle", currentArticle);
	}
}
