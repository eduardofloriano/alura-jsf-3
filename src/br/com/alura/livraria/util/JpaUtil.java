package br.com.alura.livraria.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria-postgresql");

	@Produces
	@RequestScoped
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em){
		em.close();
	}
	
}
