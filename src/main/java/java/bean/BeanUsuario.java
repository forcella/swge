package bean;

import java.util.HashMap;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controller.ControleUsuario;
import model.Funcao;
import model.Usuario;

@ManagedBean
public class BeanUsuario {

	
	private Usuario user;
	private ControleUsuario controleUsuario = new ControleUsuario();
	private Map<String, String> funcoes;

	private String funcao;
	
	
	
	public BeanUsuario(){
		setUsuario(new Usuario());
		
		Map<String, String> mapFuncoes = new HashMap<String, String>();
		if(controleUsuario.buscarFuncoes() == null){
			
		}else{
			for (Funcao func : controleUsuario.buscarFuncoes()) {
			mapFuncoes.put(func.getFuncao(),func.getFuncao());
		
			}
		}
		funcoes = mapFuncoes;
		
	}
	
	
	public void cadastrar(){
		
		ControleUsuario controlUser = new ControleUsuario();
		user.setFuncao(controlUser.buscarFuncaoPorNome(funcao));
		System.out.println(funcao);
		user = controlUser.cadastrar(user);
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar o usuario"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Usuario cadastrado Com Sucesso"));
		}
		user= new Usuario();
	}
	
	
	public void limpar(){
		setUsuario(new Usuario());
	}
	
	public Usuario getUsuario() {
		return user;
	}

	public void setUsuario(Usuario usuario) {
		this.user = usuario;
	}


	public Map<String, String> getFuncoes() {
		return funcoes;
	}


	public void setFuncoes(Map<String, String> funcoes) {
		this.funcoes = funcoes;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
}
