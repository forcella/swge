package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conta implements Serializable{

	private static final long serialVersionUID = 3908391460251174345L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	private Date dataFechamento;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Cartao cartao;
	private Integer numMesa;
	@ManyToOne
	private Usuario usuario;
	@OneToMany
	private List<ItemVenda> listaDeItemPedio;
	private Double total;
	private Double valorPago;
	private Boolean aberta;
	
	
	public Conta() {
		total =0d;
		valorPago =  0d;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ItemVenda> getListaDeItemPedio() {
		return listaDeItemPedio;
	}

	public void setListaDeItemPedio(List<ItemVenda> listaDeItemPedio) {
		this.listaDeItemPedio = listaDeItemPedio;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public int getNumMesa() {
		return numMesa;
	}
	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}

	public Boolean getAberta() {
		return aberta;
	}

	public void setAberta(Boolean aberta) {
		this.aberta = aberta;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	

}
