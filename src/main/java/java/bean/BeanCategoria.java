package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controller.ControleCategoria;
import model.Categoria;

@ManagedBean
public class BeanCategoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5616811911142734890L;
	private Categoria categoria;
	private List<Categoria> categorias;
	private String msg;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BeanCategoria(){
		setCategoria(new Categoria());
		msg = "";
	}
	
	public void cadastrar(){
		ControleCategoria controleCategoria = new ControleCategoria();
		categoria = controleCategoria.cadastrar(categoria);
		if (categoria == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar a categoria"));
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Categoria Cadastrada Com Sucesso"));
		}
		categoria = new Categoria();
	}
	
	public void buscarTodas(){
		
		ControleCategoria controleCategoria = new ControleCategoria();
		categorias = controleCategoria.buscarTodos();
	}
	
	public void limpar(){
		categoria = new Categoria();
	}
	
	
	
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	
}
