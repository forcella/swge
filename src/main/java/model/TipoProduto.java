package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoProduto implements Serializable{

	private static final long serialVersionUID = 3175946767108191782L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nomeDoTipo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDoTipo() {
		return nomeDoTipo;
	}
	public void setNomeDoTipo(String nomeDoTipo) {
		this.nomeDoTipo = nomeDoTipo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
