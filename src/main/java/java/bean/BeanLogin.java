package bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.ControleUsuario;
import model.Usuario;

@ManagedBean(name="beanLogin", eager=true)
@SessionScoped
public class BeanLogin implements Serializable{
	
	private static final long serialVersionUID = 3403983992200477868L;
	private Usuario usuario;
	private Boolean logado;
	private String msg;

	public BeanLogin() {
		usuario = new Usuario();
		logado = false;
		
		System.out.println("Bean Login sendo criado");
	}
	
	public void logout(){
		usuario = new Usuario();
		logado = false;
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void logar() {
		ControleUsuario controleUsuario = new ControleUsuario();
		Usuario _usuario = controleUsuario.logar(usuario.getLogin(), usuario.getSenha());
		if (_usuario != null) {
			logado = true;
			usuario = _usuario;
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("app.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha Incorretos!", "ASA"));
		}
	}

	public void limparCampos() {
		usuario = new Usuario();
	}
}