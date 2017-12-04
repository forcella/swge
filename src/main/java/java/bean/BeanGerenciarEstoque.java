package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controller.ControleEstoque;
import controller.ControleProduto;
import controller.ControleTipoMovimentacao;
import model.Estoque;
import model.Produto;
import model.TipoMovimentacao;

@ManagedBean
@ViewScoped
public class BeanGerenciarEstoque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4736068763230728864L;

	@ManagedProperty(value = "#{beanLogin}")
	private BeanLogin beanLogin;

	private String tituloOperacao;
	private String btnNomeOp;
	private List<Estoque> listaDeEstoque;
	private Estoque estoque;
	private ControleEstoque controleEstoque;
	private String msg;
	private List<Estoque> estoqueFiltrado;
	private List<TipoMovimentacao> listaTipoMov;
	private ControleTipoMovimentacao controleTipoMovimentacao;
	private Estoque estoqueCadastro;
	private Estoque estoqueSelecionado;
	private boolean bolBtnCadastrar;
	private boolean bolBtnEditar;

	public BeanGerenciarEstoque() {

		estoque = new Estoque();
		setEstoqueCadastro(new Estoque());
		setEstoqueSelecionado(new Estoque());
		controleEstoque = new ControleEstoque();
		controleTipoMovimentacao = new ControleTipoMovimentacao();
		msg = "";
		listaTipoMov = controleTipoMovimentacao.buscarTodos();
		tituloOperacao = "Lançar Estoque";
		btnNomeOp = "Cadastrar";
		listaDeEstoque = controleEstoque.listarTodos();
		estoqueCadastro.setData(new Date());
		setBolBtnEditar(true);
		setBolBtnCadastrar(false);
	}

	public void limpar() {
		estoqueCadastro = new Estoque();
		estoqueCadastro.setData(new Date());
		setBolBtnEditar(true);
		setBolBtnCadastrar(false);
	}

	public void cadastrar() {
		estoqueCadastro.setUsuario(beanLogin.getUsuario());
		estoqueCadastro.setQuantidadeOriginal(estoqueCadastro.getQuantidade());
		atualizaEstoqueTotal();
		controleEstoque.cadastrar(estoqueCadastro);
		listaDeEstoque = controleEstoque.listarTodos();
		estoqueCadastro = new Estoque();
		estoqueCadastro.setData(new Date());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Estoque Cadastrado"));

	}

	public void selecionaLinha() {
		estoqueCadastro = estoqueSelecionado;
		tituloOperacao = "Editar Estoque";
		setBolBtnEditar(false);
		setBolBtnCadastrar(true);
	}

	public void editar(){
		tituloOperacao = "Lançar Estoque";
		setBolBtnEditar(true);
		setBolBtnCadastrar(false);
		atualizaEstoqueTotal();
		
		Produto p = estoqueCadastro.getProduto();
		int estoqTotal = p.getQuantidadeEstoque();
		int estqAtual = estoqueSelecionado.getQuantidade();
		int estqNovo = estoqueCadastro.getQuantidade();

		estoqTotal += estqNovo - estqAtual;
		
		p.setQuantidadeEstoque(estoqTotal);
		
		if(estoqTotal < 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Estoque total abaixo de zero"));
		}else if(estqNovo < estqAtual) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "A nova quantidade de estoque é menor que a quantidade Atual"));

		}else {
			//atualiza estoque
			estoqueCadastro.setQuantidadeOriginal(estoqueCadastro.getQuantidade());
			estoqueCadastro.setQuantidade(estqAtual += (estqNovo - estqAtual));
			ControleProduto controleProduto  = new ControleProduto();
			controleProduto.atualizar(p);
			controleEstoque.atualiza(estoqueCadastro);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Estoque editado"));
		}				
		estoqueCadastro = new Estoque();
		estoqueSelecionado = new Estoque();
		estoqueCadastro.setData(new Date());
	}

	private void atualizaEstoqueTotal() {
		Produto p = estoqueCadastro.getProduto();
		int estqAtual = p.getQuantidadeEstoque();
		int estqAdcional = estoqueCadastro.getQuantidade();
		p.setQuantidadeEstoque(estqAtual + estqAdcional);
		ControleProduto controleProduto = new ControleProduto();
		controleProduto.atualizar(p);
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public void setTituloOperacao(String tituloOperacao) {
		this.tituloOperacao = tituloOperacao;
	}

	public String getBtnNomeOp() {
		return btnNomeOp;
	}

	public void setBtnNomeOp(String btnNomeOp) {
		this.btnNomeOp = btnNomeOp;
	}

	public List<Estoque> getListaDeEstoque() {
		return listaDeEstoque;
	}

	public void setListaDeEstoque(List<Estoque> listaDeEstoque) {
		this.listaDeEstoque = listaDeEstoque;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoqueEdicao(Estoque estoque) {
		this.estoque = estoque;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the estoqueFiltrado
	 */
	public List<Estoque> getEstoqueFiltrado() {
		return estoqueFiltrado;
	}

	/**
	 * @param estoqueFiltrado
	 *            the estoqueFiltrado to set
	 */
	public void setEstoqueFiltrado(List<Estoque> estoqueFiltrado) {
		this.estoqueFiltrado = estoqueFiltrado;
	}

	public List<TipoMovimentacao> getListaTipoMov() {
		return listaTipoMov;
	}

	public void setListaTipoMov(List<TipoMovimentacao> listaTipoMov) {
		this.listaTipoMov = listaTipoMov;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Estoque getEstoqueCadastro() {
		return estoqueCadastro;
	}

	public void setEstoqueCadastro(Estoque estoqueCadastro) {
		this.estoqueCadastro = estoqueCadastro;
	}

	public Estoque getEstoqueSelecionado() {
		return estoqueSelecionado;
	}

	public void setEstoqueSelecionado(Estoque estoqueSelecionado) {
		this.estoqueSelecionado = estoqueSelecionado;
	}

	public boolean isBolBtnEditar() {
		return bolBtnEditar;
	}

	public void setBolBtnEditar(boolean bolBtnEditar) {
		this.bolBtnEditar = bolBtnEditar;
	}

	public boolean isBolBtnCadastrar() {
		return bolBtnCadastrar;
	}

	public void setBolBtnCadastrar(boolean bolBtnCadastrar) {
		this.bolBtnCadastrar = bolBtnCadastrar;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

}
