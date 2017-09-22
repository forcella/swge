package model;

import dao.DaoProduto;

public class Teste {
	
	public static void main(String[] args) {
		DaoProduto daoProduto = new DaoProduto();
		Produto prod  = daoProduto.encontrar(48l);
		
		for (Produto p : prod.getIngredientes()) {
			System.out.println(p.getNome());
		}
		System.out.println(prod.getNome());
	}

}
