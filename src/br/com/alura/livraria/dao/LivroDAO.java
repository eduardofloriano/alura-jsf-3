package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;

public class LivroDAO extends DAO<Livro> {

	public LivroDAO(EntityManager em){
		this.em = em;
	}
	
	
	public List<Livro> obterLivrosPorAutor(Livro livro){
		
		TypedQuery<Livro> query = em.createNamedQuery(Livro.OBTER_LIVROS_POR_AUTOR, Livro.class);		
		//TODO: setar os parameters	
		
		return query.getResultList();
	}
	
	public List<Autor> obterAutoresDoLivro(Livro livro){	
		
		TypedQuery<Autor> query = em.createNamedQuery(Livro.OBTER_AUTORES_DO_LIVRO, Autor.class);
		return query.getResultList();
	}
	
	public List<Livro> obterTodosLivros(){
		TypedQuery<Livro> query = em.createNamedQuery(Livro.OBTER_TODOS_LIVROS, Livro.class);
		return query.getResultList();
	}
	
	public List<Livro> obterTodosLivrosPaginado(int inicio, int quantidade){
		
		TypedQuery<Livro> query = em.createNamedQuery(Livro.OBTER_TODOS_LIVROS, Livro.class);
		query.setFirstResult(inicio);
		query.setMaxResults(quantidade);
		return query.getResultList();
		
	}
	
}
