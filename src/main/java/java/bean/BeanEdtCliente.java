package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import controller.ControleCliente;
import controller.ControleTipoDocumento;
import model.Cliente;
import model.TipoDocumento;

@ManagedBean
@ViewScoped
public class BeanEdtCliente implements Serializable {

	private static final long serialVersionUID = 1692609936667207344L;
	private String tituloOperacao;
	private String btnNomeOp;
	private List<Cliente> listaClientes;
	
	private ControleCliente controleCliente;
	private String msg;
	private List<Cliente> clienteFiltrado;
	private Cliente clienteSelecionado;
	private List<TipoDocumento> listaTipoDoc;
	private boolean bolBtnEditar;
	private ControleTipoDocumento controleTipoDocumento;
	private TipoDocumento selecionadoTipoDoc;
	
	public BeanEdtCliente() {
			
		controleCliente = new ControleCliente();
		controleTipoDocumento = new ControleTipoDocumento();
		listaTipoDoc = controleTipoDocumento.buscarTodos();
		setSelecionadoTipoDoc(new TipoDocumento());
		msg = "";
		tituloOperacao = "Cliente";
		btnNomeOp = "Editar";
		listaClientes = controleCliente.buscarTodos();
		setBolBtnEditar(true);
	}

	public void limpar() {
		clienteSelecionado = new Cliente();
		System.out.println(listaClientes.get(0).getNome());
		setBolBtnEditar(true);
	}


	public void selecionaLinha() {
		System.out.println("seleciona linha");
		tituloOperacao = "Editando";
		
		setBolBtnEditar(false);
	}

	public void editar(){
		System.out.println("Editar");
		tituloOperacao = "Cliente";
		setBolBtnEditar(true);
		controleCliente.atualiza(clienteSelecionado);
		limpar();

		
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

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public ControleCliente getControleCliente() {
		return controleCliente;
	}

	public void setControleCliente(ControleCliente controleCliente) {
		this.controleCliente = controleCliente;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Cliente> getClienteFiltrado() {
		return clienteFiltrado;
	}

	public void setClienteFiltrado(List<Cliente> clienteFiltrado) {
		this.clienteFiltrado = clienteFiltrado;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public boolean isBolBtnEditar() {
		return bolBtnEditar;
	}

	public void setBolBtnEditar(boolean bolBtnEditar) {
		this.bolBtnEditar = bolBtnEditar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TipoDocumento> getListaTipoDoc() {
		return listaTipoDoc;
	}

	public void setListaTipoDoc(List<TipoDocumento> listaTipoDoc) {
		this.listaTipoDoc = listaTipoDoc;
	}

	public TipoDocumento getSelecionadoTipoDoc() {
		return selecionadoTipoDoc;
	}

	public void setSelecionadoTipoDoc(TipoDocumento selecionadoTipoDoc) {
		this.selecionadoTipoDoc = selecionadoTipoDoc;
	}


}
