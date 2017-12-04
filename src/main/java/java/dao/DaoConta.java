package dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Conta;

public class DaoConta extends DaoGenerica<Conta, Long> {

	public DaoConta() {
		super(Conta.class);
	}

	

	public List<Conta> buscarContasAbertas() {
		List<?> contasAbertas;
		Query query = entityManager.createQuery(
				"SELECT c FROM Conta c WHERE c.aberta is true");
		contasAbertas = (List<?>) query.getResultList();
		return (List<Conta>) contasAbertas;
	}

	
	public Conta buscarConta(Conta conta) {
		Conta c = null;
		if (conta.getCartao() != null) {
			Query query = entityManager.createQuery(
					"SELECT c FROM Conta c where cliente_id = :cliId AND c.aberta is TRUE");
			query.setParameter("cliId", conta.getCliente().getId());
			
			try {
				c = (Conta) query.getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return c;

	}



	public Conta buscarContaCartao(long cod) {
		Conta c = new Conta();
		Query query = entityManager.createQuery(
					"SELECT c FROM Conta c where cartao_id = :cod AND  c.aberta is TRUE");
		query.setParameter("cod", cod);
			
		try {
			c = (Conta) query.getSingleResult();
		} catch (Exception e) {
			c = null;
		}
		return c;
	}



	public List<Conta> buscarTodas() {
		return listarTodos();
	}



	



	
	
	
	
//	public Conta buscarContaPendente(Conta conta) {
//		
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Conta> query = builder.createQuery(Conta.class);
//		Root<Conta> fromConta = query.from(Conta.class);
//		Conta c = buscarConta(conta);
//		try {
//			TypedQuery<Conta> typedQuery = entityManager
//					.createQuery(query.select(fromConta).where(builder.notEqual((fromConta.<Double>get("total")), 
//							(fromConta.<Double>get("valor_pago")))));
//			c = typedQuery.getSingleResult();
//		} catch (Exception e) {
//		}
//		
//		return c;
//	}
}
