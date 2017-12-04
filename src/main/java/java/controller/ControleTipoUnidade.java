package controller;

import dao.DaoTipoUnidade;
import model.TipoUnidade;

public class ControleTipoUnidade {

	DaoTipoUnidade daoTipoUnidade = new DaoTipoUnidade();
	
	public TipoUnidade buscarPorId(Long id) {
		
		return daoTipoUnidade.encontrar(id);
	}
	
	public TipoUnidade cadastrar(TipoUnidade tpUnidade){
		
		tpUnidade = daoTipoUnidade.salvar(tpUnidade);
		return tpUnidade;
	}
	
}
