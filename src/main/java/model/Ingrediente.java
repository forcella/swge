package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = 6125152000305988179L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "produto_id") 
	private Long produtoId;
	@ManyToOne
	@JoinColumn(name="igrediente_id", nullable=false)
	private Produto produto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	
	

}
