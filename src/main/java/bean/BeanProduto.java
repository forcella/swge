package bean;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controller.ControleProduto;
import dao.DaoTipoUnidade;
import model.Produto;
import model.TipoUnidade;

@ManagedBean
public class BeanProduto {

	private Produto produto;
	private String nome;
	private List<TipoUnidade> tpsUnidades;
	private DaoTipoUnidade daoTpUnidade;
	private List<Produto> listaProdutos;
	private TipoUnidade tpsUnidade;
	private List<String> listaUnidades;
	private ControleProduto controleProduto;
	private Produto produtoSelecionado;
	private List<Produto> igredientes;

	private Map<String, String> unidades;

	public List<String> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<String> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public BeanProduto() {
		produtoSelecionado = new Produto();
		controleProduto = new ControleProduto();
		setProduto(new Produto());
		setDaoTpUnidade(new DaoTipoUnidade());
		// setTpsUnidades(daoTpUnidade.listarTodos());
		tpsUnidades = daoTpUnidade.listarTodos();
		listaProdutos = controleProduto.buscarTodos();
		listaUnidades = new ArrayList<String>();
		igredientes = new ArrayList<Produto>();
		Map<String, String> map = new HashMap<String, String>();
		for (TipoUnidade tps : getTpsUnidades()) {
			map.put(tps.getNome(), tps.getNome());
		}
		unidades = map;

		produto.setComercializavel(false);
		produto.setValorVenda(0d);
		produto.setMaiorIdade(false);
	}

	public void cadastrar() {

		ControleProduto controleProduto = new ControleProduto();
		System.out.println("-------" + nome);
		// produto.setTipoUnidade(daoTpUnidade.encontrar(id));

		produto = controleProduto.cadastrar(produto);
		if (produto == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar o usuario"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Produto cadastrado Com Sucesso"));
		}
		produto = new Produto();
	}

	public List<Produto> completaProduto(String query) {
		List<Produto> todosProdutos = listaProdutos;
		List<Produto> produtosFiltrados = new ArrayList<Produto>();

		for (int i = 0; i < todosProdutos.size(); i++) {
			Produto p = todosProdutos.get(i);
			if (p.getNome().toLowerCase().contains(query)) {
				produtosFiltrados.add(p);
			}
		}

		return produtosFiltrados;
	}

	public void addIgrediente() {
		System.out.println(produtoSelecionado.getNome());
		igredientes.add(produtoSelecionado);
		produtoSelecionado = new Produto();
	}

	public Produto handleSelect(SelectEvent e) {
		Produto p = (Produto) e.getObject();
		
		return p;
		
	}

	public Produto handleUnSelect(UnselectEvent e) {
		Produto p = (Produto) e.getObject();
		return p;
	}

	public void limpar() {
		setProduto(new Produto());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoUnidade> getTpsUnidades() {
		return tpsUnidades;
	}

	public void setTpsUnidades(List<TipoUnidade> tpsUnidades) {
		this.tpsUnidades = tpsUnidades;
	}

	public DaoTipoUnidade getDaoTpUnidade() {
		return daoTpUnidade;
	}

	public void setDaoTpUnidade(DaoTipoUnidade daoTpUnidade) {
		this.daoTpUnidade = daoTpUnidade;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public TipoUnidade getTpsUnidade() {
		return tpsUnidade;
	}

	public void setTpsUnidade(TipoUnidade tpsUnidade) {
		this.tpsUnidade = tpsUnidade;
	}

	public Map<String, String> getUnidades() {
		return unidades;
	}

	public void setUnidades(Map<String, String> unidades) {
		this.unidades = unidades;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<Produto> getIgredientes() {
		return igredientes;
	}

	public void setIgredientes(List<Produto> igredientes) {
		this.igredientes = igredientes;
	}

}
