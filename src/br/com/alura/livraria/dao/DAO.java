package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DAO<T> {

	protected EntityManager em;
	private Class<T> classe;

	public DAO(EntityManager em, Class<T> classe){
		this.em = em;
		this.classe = classe;
	}
	
	public T find(Integer id){
		return em.find(classe, id);
	}
	
	public void persist(T t){
//		em.getTransaction().begin();
		em.persist(t);
//		em.getTransaction().commit();
	}
	
	public void remove(T t){
//		em.getTransaction().begin();
		em.remove(t);
//		em.getTransaction().commit();
	}
	
	public void merge(T t){
//		em.getTransaction().begin();
		em.merge(t);
//		em.getTransaction().commit();
	}
	
	public List<T> findList(String namedQuery){
		TypedQuery<T> query = em.createNamedQuery(namedQuery, classe);
		return query.getResultList();
	}
	
	
}
