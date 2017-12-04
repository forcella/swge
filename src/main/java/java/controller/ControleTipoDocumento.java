package controller;

import java.util.List;

import dao.DaoTipoDocumento;
import model.TipoDocumento;

public class ControleTipoDocumento {
	private DaoTipoDocumento daoTipoDocumento = new DaoTipoDocumento();	
	
	
	public List<TipoDocumento> buscarTodos() {
		
		return daoTipoDocumento.listarTodos();
	}


	public TipoDocumento buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return daoTipoDocumento.encontrar(id);
	}

}
