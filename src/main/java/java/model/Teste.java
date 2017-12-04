package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import dao.DaoProduto;
import dao.DaoUsuario;

public class Teste {
	
	public static void main(String[] args) {
		
		Funcao funcao = new Funcao();
		funcao.setFuncao("Gerente");
		funcao.setId(1l);
		funcao.setCaixa(true);
		funcao.setControleDeCliente(true);
		funcao.setGerente(true);
		funcao.setVendedor(true);
		
		Usuario user = new Usuario();
		user.setNome("administrator");
		user.setLogin("adm");
		user.setSenha("adm");
		user.setFuncao(funcao);
		
		
		DaoUsuario dao = new DaoUsuario();
		dao.salvar(user);
	}

}
