package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Utils;
import dto.Category;

public class CategoryDAO {
	static private CategoryDAO instance;
	private Map<Integer, Category> data;
	private String categoriesFile;
	
	private CategoryDAO() {		
		categoriesFile = Utils.getPersistencePath("categories.ser");
	}

	@SuppressWarnings("unchecked")
	public void load() {
		data = (Map<Integer, Category>) Utils.loadFromFile(categoriesFile);
		if (data == null) {
			data = new HashMap<Integer, Category>();
		}
	}
	
	public void persist() {
		Utils.storeToFile(categoriesFile, data);
	}
	
	static public CategoryDAO getInstance() {
		if (instance == null) {
			instance = new CategoryDAO();
		}
		return instance;
	}
		
	synchronized public void create(Category category) throws Exception {
		load();
		if (data.get(category.getNumber()) != null) {
			throw new Exception("Category " + 
					category.getName() + " already exists!");
		}
		data.put(category.getNumber(), category);
		persist();
	}
	
	synchronized public List<Category> findAll() {
		load();
		List<Category> result = new ArrayList<Category>();
		
		for (Map.Entry<Integer, Category> entry : data.entrySet()) {
			result.add(entry.getValue());
		}
		
		return result;
	}
	
	synchronized public Category findForName(String categoryName) {
		load();
		Category result = null;
		
		for (Map.Entry<Integer, Category> entry : data.entrySet()) {
			if (entry.getValue().getName().equals(categoryName)) {
				result = entry.getValue(); 
				break;
			}
		}
		
		return result;
	}
	
	synchronized public Category findForNumber(int number) {
		load();
		Category result = data.get(number);
		
		return result;
	}
	
	synchronized public void delete(int number) {		
		load();
		if (data.get(number) != null) {
			data.remove(number);
			persist();
		}
	}
	
	public String toString() {
		return findAll().toString();
	}
	
	synchronized public void updateAllAndPersist(List<Category> list) {
		load();
		for (Category category : list) {
			data.put(category.getNumber(), category);
		}
		persist();
	}

	synchronized public int getMaxCategoryNumber() {
		int max = 0;
		
		load();
		for (Category category : findAll()) {
			if (category.getNumber() > max) 
				max = category.getNumber();
		}
		
		return max;
	}
	
}
