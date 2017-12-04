package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controller.ControleCategoria;
import controller.ControleProduto;
import dao.DaoTipoUnidade;
import model.Categoria;
import model.Produto;
import model.TipoUnidade;


@ManagedBean
@SessionScoped
public class BeanProduto implements Serializable{

	
	
	
	private static final long serialVersionUID = -2932022504660742920L;
	
	@ManagedProperty(value = "#{beanPageUpdaterNotify}")
	private BeanPageUpdaterNotify beanPageUpdaterNotify;
	
	public BeanPageUpdaterNotify getBeanPageUpdaterNotify() {
		return beanPageUpdaterNotify;
	}

	public void setBeanPageUpdaterNotify(BeanPageUpdaterNotify beanPageUpdaterNotify) {
		this.beanPageUpdaterNotify = beanPageUpdaterNotify;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Produto produto;
	private String tipoUnidade;
	private List<TipoUnidade> tpsUnidades;
	private String nomeCategoria;
	private Long idEdit;
	
	private ControleProduto controleProduto;
	private ControleCategoria controleCategoria;
	
	private DaoTipoUnidade daoTpUnidade;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosSelecionados;
	
	private List<Categoria> allCategorias;
	private List<TipoUnidade> allTiposUnidade;
	
	
	private TipoUnidade tpsUnidade;
	private List<String> listaUnidades;
	
	private Produto produtoSelecionado;
	private Produto produtoEditado;
	private List<Produto> igredientes;
	
	private boolean visibilidade;
	
	private List<String> cats = new ArrayList<String>();
	
	private Map<String, String> unidades;
	private Map<String, String> categorias;
	
	public List<String> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<String> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public BeanProduto() {
	
		produtoSelecionado = new Produto();
		controleProduto = new ControleProduto();
		controleCategoria = new ControleCategoria();
		produtoEditado = new Produto();
		produto = new Produto();
		setProduto(new Produto());
		setDaoTpUnidade(new DaoTipoUnidade());
		
		updateLists();
		
		
		listaUnidades = new ArrayList<String>();
		igredientes = new ArrayList<Produto>();
		produto.setValorVenda(0d);
		produto.setQuantidadeEstoque(0);
		
		Map<String, String> mapTipo = new HashMap<String, String>();
		for (TipoUnidade tps : getTpsUnidades()) {
			mapTipo.put(tps.getNome(), tps.getNome());
		}
		unidades = mapTipo;
		
		Map<String, String> mapCat = new HashMap<String, String>();
		for (Categoria cat : controleCategoria.buscarTodos()) {
			mapCat.put(cat.getCategoria(), cat.getCategoria());
			cats.add(cat.getCategoria());
		}
		categorias = mapCat;
		setVisibilidade(true);
	}

	public void carregarPaginaCompleta(){
		produtoSelecionado = new Produto();
		controleProduto = new ControleProduto();
		controleCategoria = new ControleCategoria();
		produtoEditado = new Produto();
		produto = new Produto();
		setProduto(new Produto());
		setDaoTpUnidade(new DaoTipoUnidade());
		
		updateLists();
		
		
		listaUnidades = new ArrayList<String>();
		igredientes = new ArrayList<Produto>();
		produto.setValorVenda(0d);
		produto.setQuantidadeEstoque(0);
		
		Map<String, String> mapTipo = new HashMap<String, String>();
		for (TipoUnidade tps : getTpsUnidades()) {
			mapTipo.put(tps.getNome(), tps.getNome());
		}
		unidades = mapTipo;
		
		Map<String, String> mapCat = new HashMap<String, String>();
		for (Categoria cat : controleCategoria.buscarTodos()) {
			mapCat.put(cat.getCategoria(), cat.getCategoria());
			cats.add(cat.getCategoria());
		}
		categorias = mapCat;
		setVisibilidade(true);
	}
	
	public void cadastrar() {

		ControleProduto controleProduto = new ControleProduto();
		if(produto.getIngredientes() == null){
			produto.setIngredientes(new ArrayList<Produto>());
			produto = controleProduto.cadastrar(produto);
		}else{
			produto = controleProduto.cadastrar(produto);
		}
		
		if (produto == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar o produto"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Produto cadastrado Com Sucesso"));
		}
		listaProdutos = controleProduto.buscarTodos();
		produto = new Produto();
		beanPageUpdaterNotify.notfyUpdateProduto();
	}
	
	public void alterar() {
			
			setVisibilidade(true);
			ControleProduto controleProduto = new ControleProduto();
			produtoEditado.setId(idEdit);
			System.out.println(produtoEditado.getId());
			if(produtoEditado.getIngredientes() == null){
				produtoEditado.setIngredientes(new ArrayList<Produto>());
				produtoEditado = controleProduto.atualizar(produtoEditado);
				
			}else{
				produtoEditado = controleProduto.atualizar(produtoEditado);
			}
		
			if (produtoEditado == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel editar o produto"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Produto editado Com Sucesso"));
			}
			listaProdutos = controleProduto.buscarTodos();
			produtoEditado = new Produto();
			tipoUnidade = "";
			nomeCategoria = "";
			beanPageUpdaterNotify.notfyUpdateProduto();
	}
	
	public void selecionaLinha(){
		produtoEditado = produtoSelecionado;
		idEdit = produtoSelecionado.getId();
		System.out.println(idEdit);
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
	
	public List<TipoUnidade> completaTipoUnidade(String query){
		List<TipoUnidade> todosTipos = tpsUnidades;
		List<TipoUnidade> tiposFiltrados = new ArrayList<TipoUnidade>();
		for (int i = 0; i < todosTipos.size(); i++) {
			TipoUnidade p = todosTipos.get(i);
			if (p.getNome().toLowerCase().contains(query)) {
				tiposFiltrados.add(p);
			}
		}

		return tiposFiltrados;
	}
	
	
	public List<Categoria> completaCategoria(String query){
		List<Categoria> todasCategorias = allCategorias;
		List<Categoria> catsFiltrados = new ArrayList<Categoria>();
		for (int i = 0; i < todasCategorias.size(); i++) {
			Categoria cat = todasCategorias.get(i);
			if (cat.getCategoria().toLowerCase().contains(query)) {
				catsFiltrados.add(cat);
			}
		}

		return catsFiltrados;
	}
	/*
	public void abrirAlterar(Long _id){
		Produto _prod = new Produto();
		_prod =  controleProduto.buscarPorId(_id);
		produtoSelecionado = _prod;
		visibilidade = true;
	}
	*/
	
	public void habilitarBotoes(){
		setVisibilidade(false);
	}
	
	public void apagar(){
		controleProduto.remover(produtoSelecionado.getId());
		listaProdutos = controleProduto.buscarTodos();
		setProdutoSelecionado(new Produto());
		tipoUnidade = "";
		nomeCategoria = "";
		produtoEditado = new Produto();
		beanPageUpdaterNotify.notfyUpdateProduto();
	}
	
	
	
	public void addIgrediente() {
		System.out.println(produtoSelecionado.getNome());
		igredientes.add(produtoSelecionado);
		produtoSelecionado = new Produto();
	}

	public TipoUnidade handleSelectTpUnidade(SelectEvent e) {
		TipoUnidade tp = (TipoUnidade) e.getObject();
		
		return tp; 
	}
	
	public TipoUnidade handleUnSelectTpUnidade(SelectEvent e) {
		TipoUnidade tp = (TipoUnidade) e.getObject();
		
		return tp; 
	}
	
	public Categoria handleSelectCategoria(SelectEvent e) {
		Categoria cat = (Categoria) e.getObject();
		
		return cat; 
	}
	
	public Categoria handleUnSelectCategoria(SelectEvent e) {
		Categoria cat = (Categoria) e.getObject();
		
		return cat; 
	}
	
	
	
	
	public Produto handleSelect(SelectEvent e) {
		Produto p = (Produto) e.getObject();
		
		return p; 
	}

	public Produto handleUnSelect(UnselectEvent e) {
		Produto p = (Produto) e.getObject();
		return p;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public void limpar() {
		setProduto(new Produto());
	}
	public void limparEdit(){
		setProdutoSelecionado(new Produto());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String nome) {
		this.tipoUnidade = nome;
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

	public Map<String, String> getCategorias() {
		return categorias;
	}

	public void setCategorias(Map<String, String> categorias) {
		this.categorias = categorias;
	}

	public List<Produto> getListaProdutosSelecionados() {
		return listaProdutosSelecionados;
	}

	public void setListaProdutosSelecionados(List<Produto> listaProdutosSelecionados) {
		this.listaProdutosSelecionados = listaProdutosSelecionados;
	}

	public List<String> getCats() {
		return cats;
	}

	public void setCats(List<String> cats) {
		this.cats = cats;
	}

	public boolean isVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}

	public List<Categoria> getAllCategorias() {
		return allCategorias;
	}

	public void setAllCategorias(List<Categoria> allCategorias) {
		this.allCategorias = allCategorias;
	}

	public List<TipoUnidade> getAllTiposUnidade() {
		return allTiposUnidade;
	}

	public void setAllTiposUnidade(List<TipoUnidade> allTiposUnidade) {
		this.allTiposUnidade = allTiposUnidade;
	}

	public Produto getProdutoEditado() {
		return produtoEditado;
	}

	public void setProdutoEditado(Produto produtoEditado) {
		this.produtoEditado = produtoEditado;
	}

	public long getIdEdit() {
		return idEdit;
	}

	public void setIdEdit(long idEdit) {
		this.idEdit = idEdit;
	}

	public void updateLists() {
		tpsUnidades = daoTpUnidade.listarTodos();
		listaProdutos = controleProduto.buscarTodos();
		allCategorias = controleCategoria.buscarTodos();
		setAllTiposUnidade(daoTpUnidade.listarTodos());
		
	}



}
