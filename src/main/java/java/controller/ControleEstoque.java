package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.DaoEstoque;
import dao.DaoProduto;
import dao.DaoTipoMovimentacao;
import dao.DaoUsuario;
import model.Estoque;
import model.Produto;
import model.TipoMovimentacao;
import model.Usuario;

public class ControleEstoque implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4511293005732605525L;
	DaoEstoque daoEstoque = new DaoEstoque();
	DaoProduto daoProduto = new DaoProduto();
	DaoUsuario daoUsuario = new DaoUsuario();
	DaoTipoMovimentacao daoTipoMovimentacao = new DaoTipoMovimentacao();
	
	public Estoque cadastrar(Estoque estoque) {
		daoEstoque.salvar(estoque);	
		return estoque;	
	}


	public Estoque buscarPorId(Long id) {
		
		return daoEstoque.encontrar(id);
	}

	public Estoque editar(Estoque estoque) {
		Estoque e = new Estoque();
		Produto p = new Produto();
		Usuario u = new Usuario();
		TipoMovimentacao t = new TipoMovimentacao();
		
		e = daoEstoque.encontrar(estoque.getId());
		p = daoProduto.encontrar(estoque.getProduto().getId());
		u = daoUsuario.encontrar(estoque.getUsuario().getId());
		t = daoTipoMovimentacao.encontrar(estoque.getTipoMovimentacao().getId());
		
		if (e!= null && p!= null && u!= null && t!= null && estoque.getQuantidade() > 0){
			e.setProduto(p);
			e.setUsuario(u);
			e.setTipoMovimentacao(t);
			e.setData(estoque.getData());
			e.setQuantidade(estoque.getQuantidade());
			e.setValorPago(estoque.getValorPago());
			return e;
		}
		
		return null;
	}	
	
	
	public List<Estoque> listarTodos(){
		List<Estoque> estoque = null;
		try {
			estoque = daoEstoque.listarTodos();
			if (estoque == null){
				estoque = new ArrayList<Estoque>();
			}
		} catch (Exception e) {
			estoque = new ArrayList<Estoque>();
		}
		
		return estoque;
		
	}
	
	public Estoque atualiza(Estoque estoque) {
		return daoEstoque.atualizar(estoque);
	}
}
