package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 2748837857727564816L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String login;
	private String senha;
	@ManyToOne
	private Funcao funcao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setTipoUsuario(Funcao funcao) {
		this.funcao = funcao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
