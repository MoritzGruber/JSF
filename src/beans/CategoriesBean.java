package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import persistence.CategoryDAO;
import dto.Category;

@SuppressWarnings("serial")
@ManagedBean
@ApplicationScoped
public class CategoriesBean implements Serializable {
	public List<Category> getCategories() {
		return CategoryDAO.getInstance().findAll();
	}
	
	public String showArticlesForCategory() {
		return "showArticlesForCategory";
	}
}
