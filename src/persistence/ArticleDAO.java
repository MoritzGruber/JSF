package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Utils;
import dto.Article;
import dto.User;

public class ArticleDAO {
	static private ArticleDAO instance;
	private Map<Integer, Article> data = new HashMap<Integer, Article>();
	private String articleFile;

	private ArticleDAO() {
		articleFile = Utils.getPersistencePath("articles.ser");
	}
	
	static public ArticleDAO getInstance() {
		if (instance == null) {
			instance = new ArticleDAO();
		}
		return instance;
	}
			
	@SuppressWarnings("unchecked")
	public void load() {
		data = (Map<Integer, Article>) Utils.loadFromFile(articleFile);
		if (data == null) {
			data = new HashMap<Integer, Article>();
		}
	}
	
	public void persist() {
		Utils.storeToFile(articleFile, data);
	}
	
	synchronized public List<Article> findForCategory(String categoryName) {
		List<Article> result = new ArrayList<Article>();

		load();
		for (Map.Entry<Integer, Article> entry : data.entrySet()) {
			Article article = entry.getValue();
			
			if (article.getCategoryName().equals(categoryName))
				result.add(article);
		}
		
		return result;		
	}
	
	synchronized public void create(Article article) throws Exception {
		load();
		if (data.get(article.getNumber()) != null) {
			throw new Exception("Article with number " + 
					article.getNumber() + " already exists!");
		}
		data.put(article.getNumber(), article);
		persist();
	}
	
	synchronized public List<Article> findAll() {
		List<Article> result = new ArrayList<Article>();
		
		load();
		for (Map.Entry<Integer, Article> entry : data.entrySet()) {
			result.add(entry.getValue());
		}
		
		return result;
	}

	synchronized public List<Article> findForPrefix(String prefix) {
		List<Article> result = new ArrayList<Article>();
		
		load();
		for (Map.Entry<Integer, Article> entry : data.entrySet()) {
			Article article = entry.getValue();
			
			if (article.getName().toLowerCase().indexOf(prefix) != -1 || 
				article.getDescription().toLowerCase().indexOf(prefix) != -1)
				result.add(article);
		}
		
		return result;
	}

	synchronized public Article findForNumber(Integer number) {
		Article result = null;
		
		load();
		for (Map.Entry<Integer, Article> entry : data.entrySet()) {
			if (entry.getValue().getNumber() == number) {
				result = entry.getValue(); 
				break;
			}
		}
		
		return result;
	}

	synchronized public void updateAndPersist(Article article) {
		load();
		data.put(article.getNumber(), article);
		persist();
	}
	
	synchronized public void updateAllAndPersist(List<Article> articles) {
		load();
		for (Article article : articles) {
			data.put(article.getNumber(), article);
		}
		persist();
	}
	
	synchronized public int getMaxArticleNumber() {
		int max = 0;
		
		load();
		for (Article article : findAll()) {
			if (article.getNumber() > max) 
				max = article.getNumber();
		}
		
		return max;
	}
	
	synchronized public void delete(int articleNumber) {
		load();
		data.remove(articleNumber);
		persist();
	}
}
