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
	private final String abrirConta;
	private final String caixa;
	private final String cadastroProduto;
	private final String cadastroFuncionario;
	private final String cadastroCliente;
	private final String checkoutCliente;
	private final String editarCliente;
	private final String cadastroCategoria;
	private final String cadastroTpUnidade;
	private final String listarProdutos;
	private final String gerenciarEstoque;
	
	private final String relatorioEstoque;
	private final String consumoPeriodo;
	private final String contasAbertas;
	
	
	public BeanPageLoader() {
		page ="";
		index = "index.xhtml";
		login = "login.xhtml";
		produto = "view/produto.xhtml";
		pdv = "view/pdv/pdv.xhtml";
		caixa = "view/caixa.xhtml";
		cadastroProduto = "view/produto/cadastro.xhtml";
		cadastroFuncionario = "view/usuario/cadastro.xhtml";
		cadastroCliente = "view/cliente/cadastro.xhtml";
		checkoutCliente = "view/cliente/checkout.xhtml";
		editarCliente = "view/cliente/editar.xhtml";
		cadastroCategoria = "view/categoria/cadastro.xhtml";
		listarProdutos = "view/produto/listar.xhtml";
		gerenciarEstoque = "view/estoque/estoque.xhtml";
		cadastroTpUnidade = "view/tpUnidade/cadastro.xhtml";
		abrirConta = "view/pdv/abrirconta.xhtml";
		consumoPeriodo = "view/relatorios/consumoProduto.xhtml";
		contasAbertas = "view/relatorios/contasAbertas.xhtml";
		relatorioEstoque = "view/relatorios/gerenciaEstoque.xhtml";
		
		
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
	public void loadCheckoutCliente() {
		page = checkoutCliente;
	}
	public void loadEditarCliente() {
		page = editarCliente;
	}
	public void loadCadastroCategoria(){
		page = cadastroCategoria;
	}
	
	public void loadCaixa() {
		page = caixa;
	}
	
	public void loadListarProdutos(){
		page = listarProdutos;
	}

	public void loadGerenciarEstoque() {
		page = gerenciarEstoque;
	}
	public void loadCadastroTpUnidade() {
		page = cadastroTpUnidade;
	}


	public void loadAbrirConta() {
		page = abrirConta;
	}
	
	public void loadRelatorioConsumo(){
		page = consumoPeriodo;
	}
	public void loadRelatorioContas(){
		page = contasAbertas;
	}
	public void loadRelatorioEstoque(){
		page = relatorioEstoque;
	}

}
