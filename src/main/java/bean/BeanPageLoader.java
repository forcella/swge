package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BeanPageLoader {
	
	private String page;
	private final String index;
	private final String login;
	private final String produto;
	private final String pdv;
	private final String caixa;
	
	public BeanPageLoader() {
		page ="";
		index = "index.xhtml";
		login = "login.xhtml";
		produto = "view/produto.xhtml";
		pdv = "view/pdv.xhtml";
		caixa = "view/caixa.xhtml";
		
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public void loadIndex() {
		page = index;
	}


	public void loadLogin() {
		page = login;
	}


	public void loadProduto() {
		page = produto;
	}


	public void loadPdv() {
		page = pdv;
	}


	public void loadCaixa() {
		page = caixa;
	}

}
