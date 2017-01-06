package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Autor;

public class AutorDAO extends DAO<Autor> {

	
	public AutorDAO(EntityManager em){
		this.em = em;
	}
	
	
	public List<Autor> obterAutoresPorLivro( Autor autor){
		
		TypedQuery<Autor> query = em.createNamedQuery(Autor.OBTER_AUTORES_POR_LIVRO, Autor.class);
		
		//TODO: setar os parameters		
		
		return query.getResultList();
	}
	
	public List<Autor> obterTodosAutores(){
		TypedQuery<Autor> query = em.createNamedQuery(Autor.OBTER_TODOS_AUTORES, Autor.class);
		return query.getResultList();
	}
	
	public Autor obterAutorPorId(Integer id){
		return em.find(Autor.class, id);
		
	}
	
}
