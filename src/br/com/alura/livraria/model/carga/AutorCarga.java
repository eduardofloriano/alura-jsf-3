package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.AutorDAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.util.JpaUtil;

public class AutorCarga {

	public static void main(String[] args) {
		
		EntityManager em = new JpaUtil().getEntityManager();
		AutorDAO dao = new AutorDAO(em);
		
		dao.persist(createAutor("Luis Eduardo"));
		dao.persist(createAutor("Juca Camargo"));
		dao.persist(createAutor("Stephen King"));
		dao.persist(createAutor("Tolkien"));
		dao.persist(createAutor("Machado de Assis"));
		dao.persist(createAutor("Ellie Golding"));
		dao.persist(createAutor("J.K Rol"));
		dao.persist(createAutor("Cristopher Tolkien"));
		
		em.close();
		System.exit(0);
		
	}

	
	private static Autor createAutor(String nome){
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
		
	}
}
