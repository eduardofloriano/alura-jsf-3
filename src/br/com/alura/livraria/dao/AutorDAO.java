package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.util.Log;

public class AutorDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected EntityManager em;
	private DAO<Autor> dao;
	
	@PostConstruct
	public void init(){
		this.dao = new DAO<Autor>(em, Autor.class);
	}
	
	
	public List<Autor> obterAutoresPorLivro(){
		return dao.findList(Autor.OBTER_AUTORES_POR_LIVRO);
	}
	
	public List<Autor> obterTodosAutores(){
		return dao.findList(Autor.OBTER_TODOS_AUTORES);
	}
	
	public List<Autor> obterAutoresDoLivro(Livro livro){	
		return dao.findList(Livro.OBTER_AUTORES_DO_LIVRO);
	}
	
	public Autor obterAutorPorId(Integer id){
		return dao.find(id);
		
	}
	
	@Log
	public void persist(Autor autor) {
		dao.persist(autor);
		
	}

	@Log
	public void merge(Autor autor) {
		dao.merge(autor);
		
	}

	@Log
	public void remove(Autor autor) {
		dao.remove(autor);
		
	}
	
}
