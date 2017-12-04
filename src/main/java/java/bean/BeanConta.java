package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.ControleCartao;
import controller.ControleConta;
import dao.DaoConta;
import model.Conta;


@ManagedBean
@SessionScoped
public class BeanConta implements Serializable{

	private static final long serialVersionUID = -762649975335626467L;

	@ManagedProperty(value = "#{beanPageUpdaterNotify}")
	private BeanPageUpdaterNotify beanPageUpdaterNotify;
	
	private Conta contaSelecionada;
	private Conta contaFechadaSelecionada;
	private Conta contaAbertaSelecionada;
	private List<Conta> todasContas;
	private DaoConta daoConta;
	private ControleConta controlConta;
	private boolean mostrarNota;
	private List<Conta> contasFiltradas;
	
	public BeanConta(){
		setContaSelecionada(new Conta());
		setContaFechadaSelecionada(new Conta());
		setContaAbertaSelecionada(new Conta());
		todasContas = new ArrayList<Conta>();
		contasFiltradas = new ArrayList<Conta>();
		daoConta = new DaoConta();
		updateTodasContas();
		contaSelecionada.setNumMesa(0);
		contaFechadaSelecionada.setNumMesa(0);
		contaAbertaSelecionada.setNumMesa(0);
		controlConta = new ControleConta();
		mostrarNota = false;
	}
	
	public void setarIdConta(long id){
		System.out.println(id);
		contaSelecionada.setId(id);
	}
	public void updateTodasContas() {
		todasContas = daoConta.buscarContasAbertas();
		
	}

	public List<Conta> completaContas(String query) {
		List<Conta> todasContas = daoConta.buscarContasAbertas();
		List<Conta> contasFiltradas = new ArrayList<Conta>();

		for (int i = 0; i < todasContas.size(); i++) {
			Conta count = todasContas.get(i);
			String codCartao = String.valueOf(count.getCartao().getCod());
			if (codCartao.contains(query)) {
				contasFiltradas.add(count);
			}
		}

		return contasFiltradas;
	}
	
	
	public void mostrarCampoNota(){
		mostrarNota= true;
	}
	
	public void fecharConta(){
		Conta conta = new Conta();
		System.out.println(contaSelecionada.getId());
		conta = contaSelecionada;
		
		conta.setAberta(false);
		conta.setValorPago(contaSelecionada.getTotal());
		conta.setCartao(contaSelecionada.getCartao());
		conta.setCliente(contaSelecionada.getCliente());
		conta.setData(contaSelecionada.getData());
		conta.setId(contaSelecionada.getId());
		conta.setListaDeItemPedio(contaSelecionada.getListaDeItemPedio());
		conta.setNumMesa(contaSelecionada.getNumMesa());
		conta.setTotal(contaSelecionada.getTotal());
		conta.setUsuario(contaSelecionada.getUsuario());
		conta.setDataFechamento(new Date());
		
		
		ControleCartao controlCartao = new ControleCartao();
		contaSelecionada.getCartao().setDisponivel(true);
		contaSelecionada.setAberta(false);
		controlCartao.dispCard(contaSelecionada.getCartao());
		controlConta.pagarConta(conta);
		daoConta = new DaoConta();
		todasContas = daoConta.buscarContasAbertas();
		contaSelecionada = new Conta();
		contaSelecionada.setNumMesa(0);
		beanPageUpdaterNotify.notfyUpdateCaixa();
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public void carregarContas() {
		DaoConta daoConta = new DaoConta();
		todasContas = daoConta.buscarContasAbertas();
	}

	public boolean isMostrarNota() {
		return mostrarNota;
	}

	public void setMostrarNota(boolean mostrarNota) {
		this.mostrarNota = mostrarNota;
	}

	public BeanPageUpdaterNotify getBeanPageUpdaterNotify() {
		return beanPageUpdaterNotify;
	}

	public void setBeanPageUpdaterNotify(BeanPageUpdaterNotify beanPageUpdaterNotify) {
		this.beanPageUpdaterNotify = beanPageUpdaterNotify;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Conta> getTodasContas() {
		return todasContas;
	}

	public void setTodasContas(List<Conta> todasContas) {
		this.todasContas = todasContas;
	}

	public List<Conta> getContasFiltradas() {
		return contasFiltradas;
	}

	public void setContasFiltradas(List<Conta> contasFiltradas) {
		this.contasFiltradas = contasFiltradas;
	}

	public Conta getContaFechadaSelecionada() {
		return contaFechadaSelecionada;
	}

	public void setContaFechadaSelecionada(Conta contaFechadaSelecionada) {
		this.contaFechadaSelecionada = contaFechadaSelecionada;
	}

	public Conta getContaAbertaSelecionada() {
		return contaAbertaSelecionada;
	}

	public void setContaAbertaSelecionada(Conta contaAbertaSelecionada) {
		this.contaAbertaSelecionada = contaAbertaSelecionada;
	}

	
}
