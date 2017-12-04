package controller;

import java.util.List;

import dao.DaoProduto;
import model.Produto;

public class ControleProduto {
	
	DaoProduto daoProduto = new DaoProduto();
	
	
	public Produto cadastrar(Produto produto) {
		daoProduto.salvar(produto);	
		return produto;	
	}

	public Produto buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public Produto salvarEdicao(Produto produto){
		daoProduto.atualizar(produto);
		return produto;
	}
	
	public Produto buscarPorId(Long id) {
		
		return daoProduto.encontrar(id);
	}

	public Produto editar(Produto produto) {
		Produto prod = new Produto();
		prod = daoProduto.encontrar(produto.getId());
		if (prod!= null){
			prod.setNome(produto.getNome());
			
			return prod;
		}
		
		return null;
	}
	public List<Produto> buscarTodos(){
		DaoProduto daoProduto = new DaoProduto();
		
		return daoProduto.listarTodos();
	}
	public Produto atualizar(Produto produto) {
		return daoProduto.atualizar(produto);
	}

	public void remover(Long _id) {
		daoProduto.remover(_id);	
	}
}
