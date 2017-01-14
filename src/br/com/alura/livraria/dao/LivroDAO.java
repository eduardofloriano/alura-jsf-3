package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Livro;

public class LivroDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	private DAO<Livro> dao;
	
	@PostConstruct
	public void init(){
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}
	
	public List<Livro> obterLivrosPorAutor(Livro livro){
		return dao.findList(Livro.OBTER_LIVROS_POR_AUTOR);
	}
	
	
	public List<Livro> obterTodosLivros(){
		return dao.findList(Livro.OBTER_TODOS_LIVROS);
	}
	
	public Livro find(Integer id) {
		return dao.find(id);
	}

	public void persist(Livro t) {
		dao.persist(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void merge(Livro t) {
		dao.merge(t);
	}

	public List<Livro> obterTodosLivrosPaginado(int inicio, int quantidade){
		
		TypedQuery<Livro> query = em.createNamedQuery(Livro.OBTER_TODOS_LIVROS, Livro.class);
		query.setFirstResult(inicio);
		query.setMaxResults(quantidade);
		return query.getResultList();
	}
	
}
