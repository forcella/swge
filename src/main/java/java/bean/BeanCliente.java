package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controller.ControleCliente;
import dao.DaoTipoDocumento;
import model.Cliente;
import model.TipoDocumento;

@ManagedBean
@SessionScoped
public class BeanCliente {

	private Cliente cliente;
	private String msg;
	private String numeroDoc;
	private long id;
	private List<TipoDocumento> tpsDocumento;
	private DaoTipoDocumento daoTpDocumento;
	@ManagedProperty(value = "#{beanPageUpdaterNotify}")
	private BeanPageUpdaterNotify beanPageUpdaterNotify;
	
	
	public BeanCliente() {
		setCliente(new Cliente());
		msg = "";
		numeroDoc = "";
		daoTpDocumento = new DaoTipoDocumento();
		tpsDocumento = daoTpDocumento.listarTodos();
	}

	
	
	public void cadastrar() {

		ControleCliente controleCliente = new ControleCliente();
		cliente.setTipoDocumento(daoTpDocumento.encontrar(id));
		cliente = controleCliente.cadastrar(cliente);
		beanPageUpdaterNotify.notfyUpdatePdv();
		if (cliente == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar o usuario"));
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Cliente Cadastrado Com Sucesso"));
		}
		cliente = new Cliente();
	}

	



	public void procurarClientePorDoc() {

		ControleCliente controleCliente = new ControleCliente();
		cliente = controleCliente.buscarPorDocumento(numeroDoc);

		if (cliente == null) {
			msg = "Número de documento não encontrado, verifique os dados";
		} else {
			msg = "Cliente encontrado";
		}
	}
	
	public void limpar(){
		cliente = new Cliente();
	}
	public void editarCliente() {
		cliente = new Cliente();
		ControleCliente controleCliente = new ControleCliente();
		cliente = controleCliente.editar(cliente);

		if (cliente == null) {
			msg = "Erro nos dados inseridos";
		} else {
			msg = "Cliente editado";
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public List<TipoDocumento> getTpsDocumento() {
		return tpsDocumento;
	}

	public void setTpsDocumento(List<TipoDocumento> tpsDocumento) {
		this.tpsDocumento = tpsDocumento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BeanPageUpdaterNotify getBeanPageUpdaterNotify() {
		return beanPageUpdaterNotify;
	}



	public void setBeanPageUpdaterNotify(BeanPageUpdaterNotify beanPageUpdaterNotify) {
		this.beanPageUpdaterNotify = beanPageUpdaterNotify;
	}
}
