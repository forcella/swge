package dao;

import java.util.List;

import javax.persistence.Query;

import model.Cartao;

public class DaoCartao extends DaoGenerica<Cartao, Long>{

	public DaoCartao() {
		super(Cartao.class);
	}
	
	public List<Cartao> listarCartoesDisponiveis(){
		List<?> cartoesDisponiveis;
		Query query = entityManager.createQuery(
				"SELECT c FROM Cartao c WHERE c.disponivel is true");
		cartoesDisponiveis = (List<?>) query.getResultList();
		return (List<Cartao>) cartoesDisponiveis;
	}

	public Cartao buscarPorCod(Long codCartao) {
		Cartao card = null;
		try {
			Query query = entityManager.createQuery(
					"SELECT c FROM Cartao c WHERE c.cod = :cardCod");
			query.setParameter("cardCod",codCartao);
			card =  (Cartao) query.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return card;
		
	}

}
