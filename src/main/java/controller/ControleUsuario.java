package controller;

import dao.DaoUsuario;
import model.Usuario;

public class ControleUsuario {
	public Usuario logar(String login, String senha) {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.logar(login, senha);
	}
}