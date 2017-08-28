package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoMovimentacaoProduto implements Serializable{

	private static final long serialVersionUID = -5537132560020107332L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String tipoMovimentacao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
