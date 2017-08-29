package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
 
public class Conexao {
	private static EntityManagerFactory emf;
 
	public static EntityManager getEntityManager() {
		 if (emf == null){
			emf = Persistence.createEntityManagerFactory("swgedb");
		 }
		 return emf.createEntityManager();
	}
}