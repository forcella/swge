package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import dao.DaoConta;
import dao.DaoItemVenda;
import dao.DaoProduto;
import dao.DaoRelatorio;
import model.Conta;
import model.ItemVenda;
import model.Produto;


public class ControleRelatorio {
	DaoRelatorio daoRelatorio= new DaoRelatorio();
	public List<Conta> buscarRelatoriosPorIntervalo(Date dataInicio, Date dataFinal) {				
		return daoRelatorio.buscarRelatoriosPorIntervalo(dataInicio, dataFinal);

	}
	public List<Conta> buscarRelatoriosPorIntervaloTodasContas(Date dataInicio, Date dataFinal) {
		return daoRelatorio.buscarRelatoriosPorIntervaloTodasContas(dataInicio, dataFinal);
	}
	
	public List<ItemVenda> maisVendidosSempre() {
		List<ItemVenda> itens = daoRelatorio.listarItensMaisVendidosSempre();
		return itens;
	}
	public List<ItemVenda> maisVendidos(Date dataIni, Date dataFim) {
		DaoRelatorio daoRelatorio = new DaoRelatorio();
		List<Conta> todasContas = daoRelatorio.buscarRelatoriosPorIntervaloTodasContas(dataIni, dataFim);
		List<ItemVenda> todosItens = new ArrayList<ItemVenda>();
		List<ItemVenda> resultado = new ArrayList<ItemVenda>();
		HashMap<Produto, Integer> listaFiltrada = new HashMap<Produto, Integer>();
		
		for(Conta count : todasContas){
			for(ItemVenda item: count.getListaDeItemPedio()){
				todosItens.add(item);
			}
		}
		
		
		for (ItemVenda item : todosItens) {
			if (listaFiltrada.containsKey(item.getProduto())) {
				int qtd = 0;
				for(ItemVenda item2: todosItens){
					if(item.getProduto().getId().equals(item2.getProduto().getId())){
						qtd += item2.getQuantidade();
					}
				}
				listaFiltrada.put((item.getProduto()), qtd);
			} else {
				listaFiltrada.put(item.getProduto(), item.getQuantidade());
			}
		}
		for (Entry<Produto, Integer> entry : listaFiltrada.entrySet()) {
			Produto p = entry.getKey();
			Integer i = entry.getValue();
			ItemVenda item = new ItemVenda();
			item.setProduto(p);
			item.setQuantidade(i);
			resultado.add(item);
		}
		return resultado;
		
	}
	public List<Produto> buscarProdutoEstoqueFiltro(int qtdBusca) {
		DaoProduto daoProd = new DaoProduto();
		List<Produto> produtos = daoProd.listarTodos();
		List<Produto> produtosFiltrados = new ArrayList<Produto>();
		
		for(Produto prod: produtos){
			if(prod.getControladoEstoque()){
				if(prod.getQuantidadeEstoque() < qtdBusca){
					produtosFiltrados.add(prod);
				}
			}
		}
		
		return produtosFiltrados; 
	}
	public List<Produto> buscarProtudosEstoque() {
		DaoProduto daoProd = new DaoProduto();
		List<Produto> produtos = daoProd.listarTodos();
		List<Produto> produtosFiltrados = new ArrayList<Produto>();
		
		for(Produto prod: produtos){
			if(prod.getControladoEstoque()){
				produtosFiltrados.add(prod);
			}
		}
		
		return produtosFiltrados; 
	}
}
