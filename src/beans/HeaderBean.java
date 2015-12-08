package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import utils.Utils;

@ManagedBean
@ApplicationScoped
public class HeaderBean {
	public String initData() {
		try {
		} catch (Exception e) {
			Utils.createErrorMessage(e.getMessage());
		}
		
		return null;
	}
}
