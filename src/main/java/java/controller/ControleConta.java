package controller;

import java.util.ArrayList;
import java.util.List;

import dao.DaoConta;
import dao.DaoEstoque;
import dao.DaoItemVenda;
import dao.DaoProduto;
import model.Conta;
import model.ItemVenda;

public class ControleConta {
	DaoConta daoConta = new DaoConta();
	DaoProduto daoProduto = new DaoProduto();
	DaoEstoque daoEstoque = new DaoEstoque();

	public Conta realizarPedido(Conta conta) {
		List<ItemVenda> itensPedidos = conta.getListaDeItemPedio();
		DaoItemVenda daoItemVenda = new DaoItemVenda();
		if (itensPedidos == null) {
			itensPedidos = new ArrayList<ItemVenda>();
		}
		Double total = conta.getTotal();
		Double valorParcial;
		ItemVenda item = new ItemVenda();
		for (ItemVenda i : itensPedidos) {
			valorParcial = 0d;
			item = i;
			valorParcial = item.getProduto().getValorVenda()* item.getQuantidade();
			item.setValorParcial(valorParcial);
			total += valorParcial;
			daoItemVenda.salvar(item);
			item = new ItemVenda();
		}
		
		
		daoEstoque.baixaEstoqueDePedidos(itensPedidos);
		daoConta.atualizar(conta);
		System.out.println("passou AQUI NO CONTROL");
		return conta;
	}

	public Conta novaConta(Conta conta) {
		Conta contaPendente = daoConta.buscarConta(conta);
		
		if (conta != null && (conta.getCliente() != null || (Integer) conta.getNumMesa() != null
				|| conta.getCartao() != null)) {
			
			//verifica se há pendencias na conta, se houver devolve a conta com pendência.
			if (contaPendente != null) {
				return contaPendente;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public Conta buscarConta(Conta conta) {

		return daoConta.buscarConta(conta);

	}

	public List<Conta> buscarContasAbertas() {
		return daoConta.buscarContasAbertas();
	}
	//01 já foi pago conta ok
	//02 pagando conta, conta paga
	//03 conta não paga
	public void pagarConta(Conta conta) {
			daoConta.atualizar(conta);		
	}
	
	public Conta buscarContaCartao(long cod){
		return daoConta.buscarContaCartao(cod); 
	}
	
	public void updateConta(Conta conta){
		daoEstoque.baixaEstoqueDePedidos(conta.getListaDeItemPedio());
		daoConta.atualizar(conta);
	}
	
	public void salvarConta(Conta conta){
		daoConta.salvar(conta);
	}

	public Conta buscarContaId(Long id) {
		return daoConta.encontrar(id);
	}
	
}
