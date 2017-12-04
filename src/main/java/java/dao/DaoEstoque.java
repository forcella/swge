package dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import model.Estoque;
import model.ItemVenda;
import model.Produto;

public class DaoEstoque extends DaoGenerica<Estoque, Long> {

	public DaoEstoque() {
		super(Estoque.class);
	}

	@Transactional
	public boolean baixaEstoqueDePedidos(List<ItemVenda> itensPedidos) {
		boolean bol = false;

		if (!itensPedidos.isEmpty()) {
			System.out.println("Lista cheia");
			try {
				for (ItemVenda item : itensPedidos) {
					if (item.getProduto().getControladoEstoque()) {

						for (int i = 0; i < item.getQuantidade(); i++) {
							Estoque estq = new Estoque();
							Query query = entityManager.createQuery(
									"SELECT e FROM Estoque e WHERE quantidade  > 0 AND produto_id = :idProd1 ORDER BY data ASC");
							query.setParameter("idProd1", item.getProduto().getId());
							estq = (Estoque) query.getResultList().get(0);
							if (estq.getQuantidade() > 0) {
								estq.setQuantidade(estq.getQuantidade() - 1);
								atualizar(estq);
								bol = true;
							}else {
								return false;
							}
							
							Produto prod = new Produto();
							DaoProduto daoProduto = new DaoProduto();
							prod = daoProduto.encontrar(item.getId());
							prod.setQuantidadeEstoque(prod.getQuantidadeEstoque()-item.getQuantidade());
							daoProduto.atualizar(prod);
							
							if(prod.getQuantidadeEstoque() <1) {
								return false;
							}

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Lista vazia");
		}

		return bol;
	}

}
