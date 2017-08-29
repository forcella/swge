package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1020950800821773964L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int quantidadeEstoque;
	@ManyToOne
	private TipoUnidade tipoUnidade;
	private Double tamanhoDaUnidade;
	
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_igrediente")
	private Produto igrediente;

	@OneToMany(mappedBy="igrediente")
	private List<Produto> igredientes;
	
	public List<Produto> getIgredientes() {
		return igredientes;
	}
	public void setIgredientes(List<Produto> igredientes) {
		this.igredientes = igredientes;
	}
	private Boolean comercializavel;
	private Double valorVenda;
	private Boolean maiorIdade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public TipoUnidade getTipoUnidade() {
		return tipoUnidade;
	}
	public void setTipoUnidade(TipoUnidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}
	public Double getTamanhoDaUnidade() {
		return tamanhoDaUnidade;
	}
	public void setTamanhoDaUnidade(Double tamanhoDaUnidade) {
		this.tamanhoDaUnidade = tamanhoDaUnidade;
	}
	public Boolean getComercializavel() {
		return comercializavel;
	}
	public void setComercializavel(Boolean comercializavel) {
		this.comercializavel = comercializavel;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Boolean getMaiorIdade() {
		return maiorIdade;
	}
	public void setMaiorIdade(Boolean maiorIdade) {
		this.maiorIdade = maiorIdade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Produto getIgrediente() {
		return igrediente;
	}
	public void setIgrediente(Produto igrediente) {
		this.igrediente = igrediente;
	}
	
	
	
	

}
