package dao;

import model.Produto;

public class DaoProduto extends DaoGenerica<Produto,Long>{

	protected DaoProduto() {
		super(Produto.class);
	}

}
