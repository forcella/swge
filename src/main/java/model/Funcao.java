package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Funcao implements Serializable{

	private static final long serialVersionUID = -8085049112806902061L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nomeDafuncao;
	private Boolean gerente;
	private Boolean vendedor;
	private Boolean caixa;
	private Boolean controleDeCliente;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFuncao() {
		return nomeDafuncao;
	}
	public void setFuncao(String funcao) {
		this.nomeDafuncao = funcao;
	}
	public Boolean getGerente() {
		return gerente;
	}
	public void setGerente(Boolean gerente) {
		this.gerente = gerente;
	}
	public Boolean getVendedor() {
		return vendedor;
	}
	public void setVendedor(Boolean vendedor) {
		this.vendedor = vendedor;
	}
	public Boolean getCaixa() {
		return caixa;
	}
	public void setCaixa(Boolean caixa) {
		this.caixa = caixa;
	}
	public Boolean getControleDeCliente() {
		return controleDeCliente;
	}
	public void setControleDeCliente(Boolean controleDeCliente) {
		this.controleDeCliente = controleDeCliente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
