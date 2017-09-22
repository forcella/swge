package dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Usuario;

public class DaoUsuario extends DaoGenerica<Usuario, Long> {
	public DaoUsuario() { 
		super(Usuario.class);
	}

	public Usuario logar(String login, String senha) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> from = query.from(Usuario.class);
		Usuario result = null;
		try {
			TypedQuery<Usuario> typedQuery = entityManager
					.createQuery(query.select(from).where(builder.and(builder.equal(from.<String>get("login"), login),
							builder.equal(from.<String>get("senha"), senha))));
			result = typedQuery.getSingleResult();
		} catch (Exception e) {
		}
		return result;
	}
}
