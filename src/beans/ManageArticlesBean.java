package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.component.fileentry.FileEntryStatus;

import persistence.ArticleDAO;
import utils.Utils;
import dto.Article;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ManageArticlesBean implements Serializable {
	private String currentArticleNumber;
	private List<Article> articles;
	
	public List<Article> getArticles() {
		articles = ArticleDAO.getInstance().findAll();
		Collections.sort(articles, new Comparator<Article>() {
			public int compare(Article a1, Article a2) {
				return a1.getNumber() - a2.getNumber();
			}
		});

		return articles;
	}

	public String newArticle() {
		Article newArticle = new Article(ArticleDAO.getInstance()
				.getMaxArticleNumber() + 1, "", "", 0.0, null);

		try {
			ArticleDAO.getInstance().create(newArticle);
		} catch (Exception e) {
			Utils.createErrorMessage(e.getMessage());
		}

		return "manageArticles";
	}

	// Extract file name from content-disposition header of file part
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader: " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

	public void uploadListener(FileEntryEvent e) {
		FileEntry fe = (FileEntry) e.getComponent();
		FileEntryResults results = fe.getResults();
		
		FileEntryResults.FileInfo fileInfo = results.getFiles().get(0);
		if (fileInfo.isSaved()) {
			final String imageFileName = fileInfo.getFileName();
						
			// save pathname in article
			String filePath = "/images/" + imageFileName;
			Article currentArticle = ArticleDAO.getInstance()
					.findForNumber(new Integer(currentArticleNumber));
			currentArticle.setImagePath(filePath);
			ArticleDAO.getInstance().persist();
		}		
	}

	public String getCurrentArticleNumber() {
		return currentArticleNumber;
	}

	public String deleteArticle() throws Exception {
		ArticleDAO.getInstance().delete(new Integer(currentArticleNumber));
		return "manageArticles";
	}

	public String save() {
		ArticleDAO.getInstance().updateAllAndPersist(articles);
		return "manageArticles";
	}

	public void setCurrentArticleNumber(String currentArticleNumber) {
		this.currentArticleNumber = currentArticleNumber;
	}

}
