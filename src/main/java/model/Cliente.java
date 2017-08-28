package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cliente implements Serializable{

	
	private static final long serialVersionUID = 7106801829663849989L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String email;
	private List<String> telefone;
	@ManyToOne
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void addTelefone(String telefone) {
		this.telefone.add(telefone);
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
