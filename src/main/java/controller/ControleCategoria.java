package controller;

import java.util.ArrayList;
import java.util.List;

import dao.DaoCategoria;
import model.Categoria;

public class ControleCategoria {
	
	DaoCategoria daoCategoria= new DaoCategoria(); 
	
	public Categoria cadastrar(Categoria _categoria) {
		_categoria = daoCategoria.salvar(_categoria);
		return _categoria;	
	}
	
	public List<Categoria> buscarTodas(){
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.addAll(daoCategoria.listarTodos());
		return categorias;
	}
}
