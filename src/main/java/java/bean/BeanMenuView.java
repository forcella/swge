package bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import model.Funcao;

@ManagedBean
public class BeanMenuView {

	@ManagedProperty(value = "#{beanLogin}")
	private BeanLogin beanLogin;
	private MenuModel model;
	private DefaultMenuItem item;
	private String qtd;
	@ManagedProperty(value = "#{beanConta}")
	private BeanConta beanConta;
	@ManagedProperty(value = "#{beanProduto}")
	private BeanProduto beanProduto;
	@ManagedProperty(value = "#{beanRelatorio}")
	private BeanRelatorio beanRelatorio;
	@ManagedProperty(value = "#{beanPdv}")
	private BeanPdv beanPdv;

	
	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		try {
			generateMenu(beanLogin.getUsuario().getFuncao());
		} catch (Exception e) {
			beanLogin = new BeanLogin();
		}

	}

	private void generateMenu(Funcao funcao) {

		if (funcao != null) {
			if (funcao.getCaixa()) {
				carregaMenuCaixa();
			}
			if (funcao.getVendedor()) {
				carregaMenuPdv();
			}
			if (funcao.getControleDeCliente()) {
				carregaMenuCliente();
			}
			if (funcao.getGerente()) {
				carregaMenuGerente();
			}

			
			
		}else {
			redirectIndex();
		}

	}

	private void redirectIndex() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		try {
			response.sendRedirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void carregaMenuCaixa() {
		
		item = new DefaultMenuItem("Caixa");
		item.setIcon("fa fa-usd fa-border");
		item.setCommand("#{beanPageLoader.loadCaixa}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanConta.carregarContas();
		model.addElement(item);

	}

	private void carregaMenuCliente() {
		DefaultSubMenu clienteMenu = new DefaultSubMenu("Cliente");
		clienteMenu.setIcon("fa fa-user fa-border");
		item = new DefaultMenuItem("Cadastro");
		
		item.setCommand("#{beanPageLoader.loadCadastroCliente}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		clienteMenu.addElement(item);

		item = new DefaultMenuItem("Checkout");
		//item.setIcon("ui-icon-close");
		item.setCommand("#{beanPageLoader.loadCheckoutCliente}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		clienteMenu.addElement(item);

		item = new DefaultMenuItem("Editar");
		//item.setIcon("ui-icon-search");
		item.setCommand("#{beanPageLoader.loadEditarCliente}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		clienteMenu.addElement(item);

		model.addElement(clienteMenu);
	}

	private void carregaMenuGerente() {
		DefaultSubMenu gerenteMenu = new DefaultSubMenu("Gerente");
		DefaultSubMenu produtoMenu = new DefaultSubMenu("Produto");
		
		gerenteMenu.setIcon("fa fa-book fa-border");
		
		
		item = new DefaultMenuItem("Cadastro");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadCadastroProduto}");
		item.setAjax(true);
		beanProduto.carregarPaginaCompleta();
		item.setUpdate("conteudo");
		produtoMenu.addElement(item);

		item = new DefaultMenuItem("Listar");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadListarProdutos}");
		item.setAjax(true);
		beanProduto.carregarPaginaCompleta();
		item.setUpdate("conteudo");
		produtoMenu.addElement(item);
		
		item = new DefaultMenuItem("Estoque");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadGerenciarEstoque}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		produtoMenu.addElement(item);
		gerenteMenu.addElement(produtoMenu);

		DefaultSubMenu funcionarioMenu = new DefaultSubMenu("Usuario");

		item = new DefaultMenuItem("Cadastro");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadCadastroFuncionario}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		funcionarioMenu.addElement(item);
		gerenteMenu.addElement(funcionarioMenu);

		DefaultSubMenu categoriaMenu = new DefaultSubMenu("Cadastros Sistemas");

		item = new DefaultMenuItem("Categoria");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadCadastroCategoria}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		categoriaMenu.addElement(item);

		item = new DefaultMenuItem("Tipo de Unidade");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadCadastroTpUnidade}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		categoriaMenu.addElement(item);
		gerenteMenu.addElement(categoriaMenu);

		DefaultSubMenu relatorio = new DefaultSubMenu("Relatorios");
		
	
		
		item = new DefaultMenuItem("Relatorio de Contas");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadRelatorioContas}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanRelatorio.inicializarPagina();
		relatorio.addElement(item);
		
		item = new DefaultMenuItem("Relatorio de Estoque");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadRelatorioEstoque}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanRelatorio.inicializarPaginaREstoque();
		relatorio.addElement(item);
		
		
		item = new DefaultMenuItem("Consumo Produto x Periodo");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadRelatorioConsumo}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanRelatorio.inicializarPaginaConsumo();
		relatorio.addElement(item);
		
		gerenteMenu.addElement(relatorio);
		model.addElement(gerenteMenu);
	}

	public void carregaMenuPdv() {
		
		DefaultSubMenu pdv = new DefaultSubMenu("Ponto de Venda");
		pdv.setIcon("fa fa-cart-arrow-down fa-border");
		
		
		item = new DefaultMenuItem("Abrir Conta");
		
		item.setCommand("#{beanPageLoader.loadAbrirConta}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanPdv.updateLists();
		pdv.addElement(item);
		
		item = new DefaultMenuItem("Lançar Produtos");
		//item.setIcon("ui-icon-disk");
		item.setCommand("#{beanPageLoader.loadPdv}");
		item.setAjax(true);
		item.setUpdate("conteudo");
		beanPdv.updateLists();
		pdv.addElement(item);
		
		
		
		model.addElement(pdv);
	}

	public MenuModel getModel() {
		return model;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public BeanConta getBeanConta() {
		return beanConta;
	}

	public void setBeanConta(BeanConta beanConta) {
		this.beanConta = beanConta;
	}
	public DefaultMenuItem getItem() {
		return item;
	}

	public void setItem(DefaultMenuItem item) {
		this.item = item;
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	public BeanProduto getBeanProduto() {
		return beanProduto;
	}

	public void setBeanProduto(BeanProduto beanProduto) {
		this.beanProduto = beanProduto;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	public BeanRelatorio getBeanRelatorio() {
		return beanRelatorio;
	}

	public void setBeanRelatorio(BeanRelatorio beanRelatorio) {
		this.beanRelatorio = beanRelatorio;
	}

	public BeanPdv getBeanPdv() {
		return beanPdv;
	}

	public void setBeanPdv(BeanPdv beanPdv) {
		this.beanPdv = beanPdv;
	}

}
