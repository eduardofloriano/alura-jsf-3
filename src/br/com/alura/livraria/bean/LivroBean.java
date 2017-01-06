package br.com.alura.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.AutorDAO;
import br.com.alura.livraria.dao.LivroDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.model.LivroDataModelo;
import br.com.alura.livraria.util.JpaUtil;

@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private List<Livro> livros;
	private List<Autor> autores;
	private LivroDataModelo livroDataModelo = new LivroDataModelo();

	
	private Integer autorId;

	private EntityManager em = new JpaUtil().getEntityManager();
	AutorDAO autorDAO = new AutorDAO(em);
	LivroDAO livroDAO = new LivroDAO(em);

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
		// return livroDAO.obterAutoresDoLivro(livro);
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
