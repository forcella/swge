package controller;

import java.util.List;

import dao.DaoFuncao;
import dao.DaoUsuario;
import model.Funcao;
import model.Usuario;

public class ControleUsuario {
	
	
	DaoFuncao daoFuncao = new DaoFuncao();
	DaoUsuario daoUser = new DaoUsuario();
	
	public Usuario logar(String login, String senha) {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.logar(login, senha);
	}
	
	
	
	public Usuario cadastrar(Usuario user){
		daoUser.salvar(user);
		return user;
	}
	
	public List<Funcao> buscarFuncoes(){
		
		return daoFuncao.listarTodos();
	}

	
	public Funcao buscarFuncaoPorNome(String nome) {
		
		return daoFuncao.buscaPorNome(nome);
	}

	
}