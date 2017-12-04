package dao;

import java.util.List;

import model.ItemVenda;

public class DaoItemVenda extends DaoGenerica<ItemVenda, Long>{

	public DaoItemVenda() {
		super(ItemVenda.class);
	}
	public List<ItemVenda> buscarTodos() {
		
		return listarTodos();
	}
}
