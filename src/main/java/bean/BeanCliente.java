package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.ControleCliente;
import model.Cliente;

@ManagedBean
@SessionScoped
public class BeanCliente {

	private Cliente cliente;
	private String msg;
	private String numeroDoc;

	public BeanCliente() {
		setCliente(new Cliente());
		msg = "";
		numeroDoc = "";
	}

	public void cadastrar() {

		ControleCliente controleCliente = new ControleCliente();
		cliente = controleCliente.cadastrar(cliente);
		if (cliente == null) {
			msg = "O formulário contém erros";
		} else {
			msg = "Cadastro de cliente efetuado";
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

}
