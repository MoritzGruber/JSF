package beans;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import persistence.CategoryDAO;
import dto.Category;

@FacesConverter(value="categoryConverter")
public class CategoryConverter implements Converter {

	/**
	 * converts the String representation of the key back to the Object
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws FacesException {
		String number = value.substring(value.lastIndexOf("(") + 1, value.lastIndexOf(")"));		
		Category category = CategoryDAO.getInstance().findForNumber(new Integer(number));
		
		if (category == null) {
			throw new FacesException("Category " + value + " not found!");
		}
		
		return category;
	}

	/*
	 * converts category object to string
	 */
	public String getAsString(FacesContext context, UIComponent component, Object category) throws FacesException  {
		String result = null;
		
		try {
			result = ((Category) category).toString();
		} catch (Exception e) {
			throw new FacesException("Could not convert category " + category);
		}
		
		return result;
	}
}
