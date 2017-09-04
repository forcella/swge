package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Usuario;

@ManagedBean(name="beanLogin")
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




	public String logar() {
		//logica
		/*
	 	{
			ControllerLogin controllerLogin = new ControllerLogin();
			Usuario usuario = controllerLogin.logar;
			switch (usuario){
				case null: 
					msg = "Login ou senha incorretos";
					return false;
				
				case !null:
					msg = "Bem vindo" +usuario.getNome;
					this.usuario = usuario;
					return true;
				default:
					return false;
			
			}
		}
		*/
		logado = true;
		System.out.println("Sucesso");
		return "success";
	}
	public String deslogar() {
		
		return "faliure";
	}

}
