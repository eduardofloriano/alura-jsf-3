package br.com.alura.livraria.model.carga;

import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.util.JpaUtil;

public class UsuarioCarga {

	public static void main(String[] args) {
		
		EntityManager em = new JpaUtil().getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		
		dao.persist(createUsuario("eduardofloriano@outlook.com"));		
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
