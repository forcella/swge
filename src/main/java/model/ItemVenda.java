package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda implements Serializable{

	private static final long serialVersionUID = 1364444663540100725L;
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Conta conta;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Produto produto;
	private int quantidade;
	private Double valorParcial;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorParcial() {
		return valorParcial;
	}
	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		

}
