package dao;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.TipoUnidade;

public class DaoTipoUnidade extends DaoGenerica<TipoUnidade,Long>{

	public DaoTipoUnidade() {
		super(TipoUnidade.class); 
	}
	
	public TipoUnidade procuraTpUnidadeNome(String unidade) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoUnidade> query = builder.createQuery(TipoUnidade.class);
		Root<TipoUnidade> from = query.from(TipoUnidade.class);
		TipoUnidade results = null;
		try {
			TypedQuery<TipoUnidade> typedQuery = entityManager
					.createQuery(query.select(from).where(builder.like(from.<String>get("nome"), unidade)));
			results = typedQuery.getSingleResult();
		} catch (Exception e) {
		}
		return results;
	}
	
	
}
