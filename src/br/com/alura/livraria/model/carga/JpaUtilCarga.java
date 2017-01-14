package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtilCarga {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria-postgresql");
	
	
	public EntityManager getEntityManager(){
		
		return emf.createEntityManager();
	}

}
