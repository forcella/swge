package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.ControleCartao;
import controller.ControleConta;
import model.Cartao;
import model.Conta;

@ManagedBean
@ViewScoped
public class BeanClienteCheckout implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8332482090613026916L;
	private String situacao;
	private String cor;
	private Conta conta;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Long codCartao;

	public BeanClienteCheckout() {
		situacao = "";
		cor = "";
		conta = new Conta();
	}

	public void checkout() {
		ControleConta controleConta = new ControleConta();
		ControleCartao controleCartao = new ControleCartao();
		Cartao cartao = new Cartao();
		cartao = controleCartao.buscarPorCod(codCartao);
		conta = controleConta.buscarContaCartao(cartao.getId());
		if(controleCartao.buscarPorCod(codCartao) != null) {
			if (conta != null) {
				situacao = "PAGAR";
				cor = "color:#442525";

			} else {
				situacao = "PAGO";
				cor = "color:#223d2c";
			}
		}else {
			situacao = "INEXISTENTE";
			cor = "color:#ABAB00";
		}
		
		

	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getCodCartao() {
		return codCartao;
	}

	public void setCodCartao(Long codCartao) {
		this.codCartao = codCartao;
	}

}
