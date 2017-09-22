package bean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import controller.ControleUsuario;
import model.Usuario;

@ManagedBean
@SessionScoped
public class BeanLogin {
	private Usuario usuario;
	private Boolean logado;
	private String msg;

	public BeanLogin() {
		usuario = new Usuario();
		logado = false;
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
		}
	}

	public String deslogar() {
		return "faliure";
	}
}