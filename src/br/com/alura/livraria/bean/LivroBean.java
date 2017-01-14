package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDAO;
import br.com.alura.livraria.dao.LivroDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.model.LivroDataModelo;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	private List<Livro> livros;
	private List<Autor> autores;
	
	@Inject
	private LivroDataModelo livroDataModelo;

	
	private Integer autorId;

	@Inject
	AutorDAO autorDAO;
	@Inject
	LivroDAO livroDAO;

	public List<Autor> getAutores() {
		if (autores == null) {
			autores = autorDAO.obterTodosAutores();
		}
		return autores;
	}

	public List<Livro> getLivros() {
		if(this.livros == null){
			livros = livroDAO.obterTodosLivros();
		}
		
		return livros;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void gravar() {

		System.out.println("Reading Object - livro: " + livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter ao menos um autor!"));
		}

		if (this.livro.getId() == null) {
			livroDAO.persist(livro);
			System.out.println("Gravou o livro: " + livro.getTitulo());

		} else {
			livroDAO.merge(livro);
			System.out.println("Alterou o livro: " + livro.getTitulo());
		}

		clear();
		atualizaLivros();

	}

	public void remover(Livro livro) {
		System.out.println("Removendo o livro: " + livro.getTitulo());
		livroDAO.remove(livro);
		System.out.println("Removeu o livro: " + livro.getTitulo());
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando o livro: " + livro.getTitulo());
		this.livro = livro;
		System.out.println("Carregado o livro: " + livro.getTitulo());
	}

	public void clear() {
		livro = new Livro();
	}

	public void gravarAutor() {
		Autor autor = autorDAO.obterAutorPorId(autorId);
		livro.addAutor(autor);
		System.out.println("Gravou o autor " + autor.getNome()
				+ " Para o livro: " + livro.getTitulo());
	}

	public void removerAutor(Autor autor) {
		this.livro.removerAutor(autor);
		System.out.println("Removeu o autor " + autor.getNome()
				+ " Para o livro: " + livro.getTitulo());
	}

	public String formAutor() {
		return "autor?faces-redirect=true";
	}

	public void validateCaracterInicial(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"Deveria comecar com 1"));
		}
	}
	
	private void atualizaLivros(){
		this.livros = livroDAO.obterTodosLivros();
	}
	
	public LivroDataModelo getLivroDataModelo() {
		return livroDataModelo;
	}

	public void setLivroDataModelo(LivroDataModelo livroDataModelo) {
		this.livroDataModelo = livroDataModelo;
	}

}
