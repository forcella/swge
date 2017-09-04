package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BeanPageLoader {
	
	private String page;
	private final String index;
	private final String login;
	
	
	public BeanPageLoader() {
		page ="";
		index = "index.xhtml";
		login = "login.xhtml";
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public String getIndex() {
		return index;
	}


	public String getLogin() {
		return login;
	}

}
