package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.util.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorDAO autorDAO;
	
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

	@Transacional
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
	
	@Transacional
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
