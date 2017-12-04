package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Query;

import model.Conta;
import model.ItemVenda;
import model.Produto;

public class DaoRelatorio extends DaoGenerica<Conta, Long>{

	public DaoRelatorio() {
		super(Conta.class);
		
	}
	
	public List<Conta> buscarRelatoriosPorIntervalo(Date dataInico, Date dataFinal) {
		List<Conta> listaRelatorioLucro = null;
		
			Query query = entityManager.createQuery(
					"SELECT c FROM Conta c WHERE datafechamento BETWEEN :dataIni AND :dataFim");
			query.setParameter("dataIni",dataInico);
			query.setParameter("dataFim",dataFinal);
		try {
			listaRelatorioLucro = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaRelatorioLucro;

	}
	
	public List<Conta> buscarRelatoriosPorIntervaloTodasContas(Date dataInico, Date dataFinal) {
		List<Conta> listaRelatorioLucro = null;
		
			Query query = entityManager.createQuery(
					"SELECT c FROM Conta c WHERE data BETWEEN :dataIni AND :dataFim");
			query.setParameter("dataIni",dataInico);
			query.setParameter("dataFim",dataFinal);
		try {
			listaRelatorioLucro = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaRelatorioLucro;

	}
	
	public List<ItemVenda> listarItensMaisVendidosSempre() {
		DaoItemVenda daoItemVenda = new DaoItemVenda();
		DaoConta daoConta = new DaoConta();
		List<Conta> todasContas = daoConta.buscarTodas();
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
}
