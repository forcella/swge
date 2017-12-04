package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controller.ControleCartao;
import controller.ControleCliente;
import controller.ControleConta;
import dao.DaoItemVenda;
import dao.DaoProduto;
import model.Cartao;
import model.Cliente;
import model.Conta;
import model.ItemVenda;
import model.Produto;

@ManagedBean
@SessionScoped
public class BeanPdv {

	@ManagedProperty(value = "#{beanLogin}")
	private BeanLogin beanLogin;

	@ManagedProperty(value = "#{beanConta}")
	private BeanConta beanConta;

	@ManagedProperty(value = "#{beanPageUpdaterNotify}")
	private BeanPageUpdaterNotify beanPageUpdaterNotify;

	private String tipo;
	private Produto produtoSelecionado;
	private ItemVenda itemVenda;
	private int quantidade;
	private String obs;
	private List<ItemVenda> itensPedido;
	private List<ItemVenda> itensSelecionados;
	private List<Produto> listaProdutosDiponiveis;
	private DaoProduto daoProduto;
	private ControleCartao controleCartao;

	private int[] numMesa = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private int mesa;

	private List<Cliente> clientes;
	private Cliente cliente;

	private List<Cartao> cartoes;
	private List<Cartao> cartoesDisponiveis;
	private Cartao cartao;
	private ControleCliente controleCliente;
	private boolean visivel = true;
	private boolean pedvisivel = false;

	public BeanPdv() {
		tipo = "balcao";
		daoProduto = new DaoProduto();
		itemVenda = new ItemVenda();
		itemVenda.setValorParcial(0d);
		itemVenda.setQuantidade(0);
		produtoSelecionado = new Produto();
		controleCliente = new ControleCliente();
		itensPedido = new ArrayList<ItemVenda>();
		quantidade = 1;
		clientes = new ArrayList<Cliente>();
		cliente = new Cliente();

		cartao = new Cartao();
		controleCartao = new ControleCartao();
		
		updateLists();

		itensSelecionados = new ArrayList<ItemVenda>();

		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void abrirConta() {

		Conta conta = new Conta();
		cartao.setDisponivel(false);
		controleCartao.dispCard(cartao);
		conta.setCartao(cartao);
		conta.setCliente(cliente);
		conta.setData(new Date());
		conta.setNumMesa(mesa);
		conta.setTotal(0.0);
		conta.setValorPago(0.0);
		conta.setListaDeItemPedio(itensSelecionados);
		conta.setUsuario(beanLogin.getUsuario());
		ControleConta controleConta = new ControleConta();
		Conta conta2 = new Conta();
		conta2 = conta;
		conta = controleConta.novaConta(conta);
		if (conta != null) {
			// avisar no dialog
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn:",
					"Cliente já possui uma conta aberta numero do cartao: " + conta.getCartao().getCod()));
		} else {
			conta2.setAberta(true);
			controleConta.salvarConta(conta2);
			// buscar cartao, update cartao USADO
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Conta Aberta com Sucesso"));
		}
		cartao = new Cartao();
		cliente = new Cliente();
		conta = new Conta();
		conta2 = new Conta();
		mesa = 0;
		cartoesDisponiveis = controleCartao.buscarTodosDisponiveis();
		beanPageUpdaterNotify.notfyUpdateCaixa();
	}

	public void lancarPedido() {
		ItemVenda item = new ItemVenda();
		item.setProduto(getProdutoSelecionado());
		item.setUsuario(beanLogin.getUsuario());
		item.setQuantidade(quantidade);
		item.setValorParcial(getProdutoSelecionado().getValorVenda() * item.getQuantidade());
		item.setObs(obs);
		itensPedido.add(item);
		pedvisivel = true;
		obs = "";
		quantidade = 1;
		produtoSelecionado = new Produto();
	}

	public void fecharPedido() {
		Conta conta = new Conta();
		Double total =0d;
		ControleConta controlConta = new ControleConta();
		DaoItemVenda daoItemVenda = new DaoItemVenda();
		Long cod = -1l;
		
		try {
			cod = Long.valueOf(cartao.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (cod == -1) {
			
			conta.setAberta(false);
			conta.setData(new Date());
			conta.setDataFechamento(new Date());
			
			
			for (ItemVenda i : itensPedido) {
				daoItemVenda.salvar(i);
				conta.getListaDeItemPedio().add(daoItemVenda.encontrar(i.getId()));
				total += i.getValorParcial();
			}
			
			
			
		} else {

			conta = controlConta.buscarContaCartao(cod);
			System.out.println(cartao.getCod());
			if (conta == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error!", "Não foi encontrada conta vinculada ao cartão"));
			} else {
				//conta.getListaDeItemPedio().addAll(itensPedido);
				
			
				for (ItemVenda i : itensPedido) {
					daoItemVenda.salvar(i);
					conta.getListaDeItemPedio().add(daoItemVenda.encontrar(i.getId()));
					total += i.getValorParcial();
				}				
			}
		}
		conta.setTotal(conta.getTotal()+total);
		controlConta.updateConta(conta);
		itensPedido = new ArrayList<ItemVenda>();
		pedvisivel = false;
		obs = "";
		quantidade = 1;
		produtoSelecionado = new Produto();
		cartao = new Cartao();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Pedido lançado com Sucesso"));

	}

	public void cancelarPedido() {
		itensPedido = new ArrayList<ItemVenda>();
		pedvisivel = false;
		obs = "";
		quantidade = 1;
		produtoSelecionado = new Produto();
		cartao = new Cartao();
	}

	public void atualizaValorParcial() {

		itemVenda.setValorParcial(itemVenda.getQuantidade() * produtoSelecionado.getValorVenda());
	}

	public List<Cartao> completaCartao(String query) {
		List<Cartao> todosCartoes = cartoes;
		List<Cartao> cartoesFiltrados = new ArrayList<Cartao>();

		for (int i = 0; i < todosCartoes.size(); i++) {
			Cartao cart = todosCartoes.get(i);
			String codCartao = String.valueOf(cart.getCod());
			if (codCartao.contains(query)) {
				cartoesFiltrados.add(cart);
			}
		}

		return cartoesFiltrados;
	}

	public List<Cartao> completaCartoesDisponiveis(String query) {
		List<Cartao> todosCartoes = cartoesDisponiveis = controleCartao.buscarTodosDisponiveis();
		List<Cartao> cartoesFiltrados = new ArrayList<Cartao>();

		for (int i = 0; i < todosCartoes.size(); i++) {
			Cartao cart = todosCartoes.get(i);
			String codCartao = String.valueOf(cart.getCod());
			if (codCartao.contains(query)) {
				cartoesFiltrados.add(cart);
			}
		}

		return cartoesFiltrados;
	}

	public List<Cliente> completaCliente(String query) {
		List<Cliente> todosClientes = clientes;
		List<Cliente> clientesFiltrados = new ArrayList<Cliente>();

		for (int i = 0; i < todosClientes.size(); i++) {
			Cliente cli = todosClientes.get(i);
			String docCliente = String.valueOf(cli.getNumeroDocumento());
			if (docCliente.contains(query)) {
				clientesFiltrados.add(cli);
			}

		}
		return clientesFiltrados;
	}

	public Cliente handleSelectCli(SelectEvent e) {
		Cliente cli = (Cliente) e.getObject();

		return cli;
	}

	public Cliente handleUnSelectCli(UnselectEvent e) {
		Cliente cli = (Cliente) e.getObject();
		return cli;
	}

	public Cartao handleSelectAbrir(SelectEvent e) {
		Cartao card = (Cartao) e.getObject();

		return card;
	}

	public Cartao handleUnSelectAbrir(UnselectEvent e) {
		Cartao card = (Cartao) e.getObject();
		return card;
	}

	public Cartao handleSelect(SelectEvent e) {
		Conta conta = new Conta();
		ControleConta controlConta = new ControleConta();
		conta = controlConta.buscarContaCartao(cartao.getId());
		System.out.println(cartao.getCod());
		if (conta == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Não foi encontrada conta vinculada ao cartão"));
			Cartao card = (Cartao) e.getObject();
			visivel = false;

			itensPedido = new ArrayList<ItemVenda>();
			pedvisivel = false;
			return card;

		} else {
			visivel = true;
			Cartao card = (Cartao) e.getObject();
			itensPedido = new ArrayList<ItemVenda>();
			pedvisivel = false;
			return card;
		}
	}

	public Cartao handleUnSelect(UnselectEvent e) {
		Cartao card = (Cartao) e.getObject();
		itensPedido = new ArrayList<ItemVenda>();
		return card;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public List<ItemVenda> getItensSelecionados() {
		return itensSelecionados;
	}

	public void setItensSelecionados(List<ItemVenda> itensSelecionados) {
		this.itensSelecionados = itensSelecionados;
	}

	public List<Produto> getListaProdutosDiponiveis() {
		return listaProdutosDiponiveis;
	}

	public void setListaProdutosDiponiveis(List<Produto> listaProdutosDiponiveis) {
		this.listaProdutosDiponiveis = listaProdutosDiponiveis;
	}

	public int[] getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int[] numMesa) {
		this.numMesa = numMesa;
	}

	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public List<ItemVenda> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemVenda> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public boolean isPedvisivel() {
		return pedvisivel;
	}

	public void setPedvisivel(boolean pedvisivel) {
		this.pedvisivel = pedvisivel;
	}

	public List<Cartao> getCartoesDisponiveis() {
		return cartoesDisponiveis;
	}

	public void setCartoesDisponiveis(List<Cartao> cartoesDisponiveis) {
		this.cartoesDisponiveis = cartoesDisponiveis;
	}

	public BeanConta getBeanConta() {
		return beanConta;
	}

	public void setBeanConta(BeanConta beanConta) {
		this.beanConta = beanConta;
	}

	public BeanPageUpdaterNotify getBeanPageUpdaterNotify() {
		return beanPageUpdaterNotify;
	}

	public void setBeanPageUpdaterNotify(BeanPageUpdaterNotify beanPageUpdaterNotify) {
		this.beanPageUpdaterNotify = beanPageUpdaterNotify;
	}

	public void updateLists() {
		tipo = "balcao";
		visivel = true;
		cartao = new Cartao();
		cartoes = controleCartao.buscarTodos();
		cartoesDisponiveis = controleCartao.buscarTodosDisponiveis();
		listaProdutosDiponiveis = daoProduto.listarTodosProdutosDiponiveis();
		clientes = controleCliente.buscarTodos();
		
	}

}
