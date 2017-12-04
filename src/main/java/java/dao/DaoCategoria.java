package dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Categoria;

public class DaoCategoria extends DaoGenerica<Categoria,Long>{

	public DaoCategoria() {
		super(Categoria.class);
		// TODO Auto-generate
	}
	
	public Categoria buscaPorNome(String categoria) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
		Root<Categoria> from = query.from(Categoria.class);
		Categoria results = null;
		try {
			TypedQuery<Categoria> typedQuery = entityManager
					.createQuery(query.select(from).where(builder.like(from.<String>get("categoria"), categoria)));
			results = typedQuery.getSingleResult();
		} catch (Exception e) {
		}
		return results;
	}
}