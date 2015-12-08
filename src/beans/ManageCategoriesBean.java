package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.CategoryDAO;
import utils.Utils;
import dto.Category;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ManageCategoriesBean implements Serializable {
	private String currentCategoryNumber;
	private List<Category> categories;
	
	public List<String> getCategoriesAsStrings() {
		List<String> result = new ArrayList<String>();
		
		for (Category category : getCategories()) {
			result.add(category.getName());
		}
		
		return result;
	}
	
	public List<Category> getCategories() {
		categories = CategoryDAO.getInstance().findAll();
		Collections.sort(categories, new Comparator<Category>() {
			public int compare(Category c1, Category c2) {
				return c1.getName().compareTo(c2.getName());
			}			
		});
		
		return categories;
	}
	
	public String newCategory() {
		Category newCategory = new Category(
				CategoryDAO.getInstance().getMaxCategoryNumber() + 1, 
				      "new category");
		
		try {
			CategoryDAO.getInstance().create(newCategory);
		} catch (Exception e) {
			Utils.createErrorMessage(e.getMessage());
		}		
		
		return "manageCategories";
	}
	
	public String deleteCategory() {		
		CategoryDAO.getInstance().delete(new Integer(currentCategoryNumber));
		return "manageCategories";
	}
	
	public String save() {
		CategoryDAO.getInstance().updateAllAndPersist(categories);
		return "manageCategories";
	}

	public void setCurrentCategoryNumber(String number) {
		this.currentCategoryNumber = number;
	}
}
