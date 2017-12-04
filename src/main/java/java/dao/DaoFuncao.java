package dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Funcao;

public class DaoFuncao extends DaoGenerica<Funcao,Long>{
	
	public DaoFuncao() {
		super(Funcao.class);
	}
	
	public Funcao buscaPorNome(String funcao) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Funcao> query = builder.createQuery(Funcao.class);
		Root<Funcao> from = query.from(Funcao.class);
		Funcao results = null;
		try {
			TypedQuery<Funcao> typedQuery = entityManager
					.createQuery(query.select(from).where(builder.like(from.<String>get("nomeDafuncao"), funcao)));
			results = typedQuery.getSingleResult();
		} catch (Exception e) {
		}
		return results;
	}
	

}
