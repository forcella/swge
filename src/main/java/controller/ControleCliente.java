package controller;

import dao.DaoCliente;
import model.Cliente;

public class ControleCliente {
	DaoCliente daoCliente = new DaoCliente();
		
	public Cliente cadastrar(Cliente cliente) {
		daoCliente.salvar(cliente);	
		return cliente;	
	}

	public Cliente buscarPorDocumento(String numeroDoc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente buscarPorId(Long id) {
		
		return daoCliente.encontrar(id);
	}

	public Cliente editar(Cliente cliente) {
		Cliente c = new Cliente();
		c = daoCliente.encontrar(cliente.getId());
		if (c!= null){
			c.setNome(cliente.getNome());
			c.setEmail(cliente.getEmail());
			
			return c;
		}
		
		return null;
	}

}
