package controller;

import java.util.List;

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

	
	public List<Cliente> buscarTodos(){
		
		return daoCliente.listarTodos();
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

	public Cliente atualiza(Cliente cliente) {
		return daoCliente.atualizar(cliente);
		
	}

}
