package controller;

import java.util.List;

import dao.DaoTipoMovimentacao;
import model.TipoMovimentacao;

public class ControleTipoMovimentacao {
	DaoTipoMovimentacao daoTipoMovimentacao = new DaoTipoMovimentacao();
	
	
	public List<TipoMovimentacao> buscarTodos(){
		
		return daoTipoMovimentacao.listarTodos();
	}
}
