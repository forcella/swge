package controller;

import java.util.List;

import dao.DaoCategoria;
import model.Categoria;

public class ControleCategoria {
	
	DaoCategoria daoCategoria = new DaoCategoria();
	
	
	public Categoria cadastrar(Categoria tipoProduto) {
		daoCategoria.salvar(tipoProduto);	
		return tipoProduto;	
	}

	public Categoria buscarPorNome(String nome) {
		
		return daoCategoria.buscaPorNome(nome);
	}

	public Categoria buscarPorId(Long id) {
		
		return daoCategoria.encontrar(id);
	}

	public Categoria editar(Categoria tipoProduto) {
		Categoria tProd = new Categoria();
		tProd = daoCategoria.encontrar(tipoProduto.getId());
		if (tProd!= null){
			tProd.setCategoria(tProd.getCategoria());
			daoCategoria.atualizar(tProd);
			
			return tProd;
		}
		
		return null;
	}
	public List<Categoria> buscarTodos(){
				return daoCategoria.listarTodos();
	}
	

}
