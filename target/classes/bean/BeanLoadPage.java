package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BeanLoadPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String page;
	private final String loginPage;

	public BeanLoadPage() {
			loginPage = "login.xhtml";
			page = "";
		
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void loginPage() {
		page = loginPage;
	}
}
