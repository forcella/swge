package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GerenciaProduto implements Serializable{

	private static final long serialVersionUID = -5541923749774260741L;
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private TipoMovimentacao tipoMovimentacaoProduto;
	private String justificativa;
	private int quantidade;
	private Double valorPago;
	private Timestamp data;
	
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
	public TipoMovimentacao getTipoMovimentacaoProduto() {
		return tipoMovimentacaoProduto;
	}
	public void setTipoMovimentacaoProduto(TipoMovimentacao tipoMovimentacaoProduto) {
		this.tipoMovimentacaoProduto = tipoMovimentacaoProduto;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
