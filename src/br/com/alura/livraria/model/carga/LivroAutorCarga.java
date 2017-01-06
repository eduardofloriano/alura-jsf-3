package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.LivroDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.util.JpaUtil;

public class LivroAutorCarga {

	
public static void main(String[] args) {
		
		EntityManager em = new JpaUtil().getEntityManager();
		LivroDAO dao = new LivroDAO(em);
		
		dao.merge(createLivroAutor(em.find(Livro.class, 1), em.find(Autor.class, 3)));
		dao.merge(createLivroAutor(em.find(Livro.class, 2), em.find(Autor.class, 5)));
		dao.merge(createLivroAutor(em.find(Livro.class, 3), em.find(Autor.class, 1)));
		dao.merge(createLivroAutor(em.find(Livro.class, 4), em.find(Autor.class, 2)));
		dao.merge(createLivroAutor(em.find(Livro.class, 5), em.find(Autor.class, 8)));
		dao.merge(createLivroAutor(em.find(Livro.class, 6), em.find(Autor.class, 4)));
		
		
		em.close();
		System.exit(0);
		
	}

	
	private static Livro createLivroAutor(Livro livro, Autor autor){
		livro.addAutor(autor);
		return livro;
	}
}
