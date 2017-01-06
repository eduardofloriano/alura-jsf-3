package br.com.alura.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.AutorDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.util.JpaUtil;

@ManagedBean
@ViewScoped
public class AutorBean {

	private EntityManager em = new JpaUtil().getEntityManager();
	private AutorDAO autorDAO = new AutorDAO(em);
	
	private Autor autor = new Autor();
	private List<Autor> autores;
	

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		this.autores = autorDAO.obterTodosAutores();
		return this.autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public void gravar() {

		if(this.autor.getId() == null){
			autorDAO.persist(autor);
			System.out.println("Gravou o Autor: " + autor.getNome());
		}else{
			autorDAO.merge(autor);
			System.out.println("Alterou o Autor: " + autor.getNome());
		}
		
		this.autores = autorDAO.obterTodosAutores();
		clear();
		
		//return "livro?faces-redirect=true";
	}
	
	public void remover(Autor autor){
		System.out.println("Removendo o autor: " + autor.getNome());
		autorDAO.remove(autor);
		System.out.println("Autor removido com sucesso.");
		
	}
	
	public void carregar(Autor autor){
		this.autor = autor;
	}
	
	public void carregarPorId(){
		
		Integer id = this.autor.getId();
		this.autor = autorDAO.obterAutorPorId(id);
		if (this.autor == null){
			this.autor = new Autor();
		}
		
		
		
	}
	
	public void clear(){
		autor = new Autor();
	}

}
