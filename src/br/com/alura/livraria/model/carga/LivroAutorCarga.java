package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;

public class LivroAutorCarga {

	
public static void main(String[] args) {
		
		EntityManager em = new JpaUtilCarga().getEntityManager();
		em.getTransaction().begin();
		
		em.merge(createLivroAutor(em.find(Livro.class, 1), em.find(Autor.class, 3)));
		em.merge(createLivroAutor(em.find(Livro.class, 2), em.find(Autor.class, 5)));
		em.merge(createLivroAutor(em.find(Livro.class, 3), em.find(Autor.class, 1)));
		em.merge(createLivroAutor(em.find(Livro.class, 4), em.find(Autor.class, 2)));
		em.merge(createLivroAutor(em.find(Livro.class, 5), em.find(Autor.class, 8)));
		em.merge(createLivroAutor(em.find(Livro.class, 6), em.find(Autor.class, 4)));
		
		em.getTransaction().commit();
		em.close();
		System.exit(0);
		
	}

	
	private static Livro createLivroAutor(Livro livro, Autor autor){
		livro.addAutor(autor);
		return livro;
	}
}
