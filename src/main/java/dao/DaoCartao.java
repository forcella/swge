package dao;

import model.Cartao;
import model.Cliente;

public class DaoCartao extends DaoGenerica<Cartao, Long>{

	protected DaoCartao() {
		super(Cartao.class);
	}

}
