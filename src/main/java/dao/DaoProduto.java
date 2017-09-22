package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import controller.ControleProduto;
import model.Produto;

public class DaoProduto extends DaoGenerica<Produto, Long> {

	public DaoProduto() {
		super(Produto.class);
	}

	public List<Produto> procuraProdutoNome(String nomeProduto) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		List<Produto> results = null;
		try {
			TypedQuery<Produto> typedQuery = entityManager
					.createQuery(query.select(from).where(builder.like(from.<String>get("nome"), nomeProduto)));
			results = typedQuery.getResultList();
		} catch (Exception e) {
		}
		return results;
	}
	
	@Override
	public Produto salvar(Produto p) {
		ControleProduto controleProduto = new ControleProduto();
		List<Produto> igredientes = new ArrayList<Produto>();
		 EntityTransaction t = entityManager.getTransaction();
	       t.begin();
	       for (Produto i : p.getIngredientes()) {
			igredientes.add(controleProduto.buscarPorId(i.getId()));
	       }
	       p.setIngredientes(igredientes);
	       entityManager.persist(p);
	       entityManager.flush();
	       t.commit();
	       return p;
	}
}
