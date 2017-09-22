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
	private final String cadastroProduto;
	private final String cadastroFuncionario;
	private final String cadastroCliente;
	private final String cadastroCategoria;
	
	
	public BeanPageLoader() {
		page ="";
		index = "index.xhtml";
		login = "login.xhtml";
		produto = "view/produto.xhtml";
		pdv = "view/pdv.xhtml";
		caixa = "view/caixa.xhtml";
		cadastroProduto = "view/produto/cadastro.xhtml";
		cadastroFuncionario = "view/funcionario/cadastro.xhtml";
		cadastroCliente = "view/cliente/cadastro.xhtml";
		cadastroCategoria = "view/categoria/cadastro.xhtml";
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
	
	public void loadCadastroProduto(){
		page = cadastroProduto;
	}
	
	public void loadCadastroFuncionario(){
		page = cadastroFuncionario;
	}
	
	public void loadCadastroCliente(){
		page = cadastroCliente;
	}
	
	public void loadCadastroCategoria(){
		page = cadastroCategoria;
	}
	
	public void loadCaixa() {
		page = caixa;
	}


	public String getCadastroCategoria() {
		return cadastroCategoria;
	}

}
