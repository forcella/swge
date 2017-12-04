package controller;

import java.util.List;

import dao.DaoCartao;
import model.Cartao;

public class ControleCartao {
	
	DaoCartao daoCartao = new DaoCartao();	
	
	public Cartao buscarPorId(Long id) {
		
		return daoCartao.encontrar(id);
	}
	
	public List<Cartao> buscarTodos(){
		
		return daoCartao.listarTodos();
	}
	
	public List<Cartao> buscarTodosDisponiveis(){
		
		return daoCartao.listarCartoesDisponiveis();
	}

	public void dispCard(Cartao cartao) {
		// TODO Auto-generated method stub
		daoCartao.atualizar(cartao);
	}
	
	public Cartao buscarPorCod(Long codCartao) {
		
		return daoCartao.buscarPorCod(codCartao);
	}


	
}
