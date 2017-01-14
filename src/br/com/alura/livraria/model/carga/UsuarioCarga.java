package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.model.Usuario;

public class UsuarioCarga {

	public static void main(String[] args) {
		
		EntityManager em = new JpaUtilCarga().getEntityManager();
		em.getTransaction().begin();
		em.persist(createUsuario("eduardofloriano@outlook.com"));		
		em.getTransaction().commit();
		em.close();
		System.exit(0);
		
	}

	
	private static Usuario createUsuario(String email){
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha("12345");
		return usuario;
		
	}
}
