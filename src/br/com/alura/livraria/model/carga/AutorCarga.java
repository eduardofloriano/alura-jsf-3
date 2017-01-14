package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.model.Autor;

public class AutorCarga {

	public static void main(String[] args) {
		
		EntityManager em = new JpaUtilCarga().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(createAutor("Luis Eduardo"));
		em.persist(createAutor("Juca Camargo"));
		em.persist(createAutor("Stephen King"));
		em.persist(createAutor("Tolkien"));
		em.persist(createAutor("Machado de Assis"));
		em.persist(createAutor("Ellie Golding"));
		em.persist(createAutor("J.K Rol"));
		em.persist(createAutor("Cristopher Tolkien"));
		
		em.getTransaction().commit();
		em.close();
		System.exit(0);
		
	}

	
	private static Autor createAutor(String nome){
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
		
	}
}
