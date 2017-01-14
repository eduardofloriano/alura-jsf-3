package br.com.alura.livraria.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	private DAO<Usuario> dao;

	@PostConstruct
	public void init() {
		this.dao = new DAO<Usuario>(this.em, Usuario.class);
	}

	public Usuario obterUsuarioPorEmail(Usuario usuarioLogin) {

		Usuario usuario = null;
		TypedQuery<Usuario> query = em.createNamedQuery(Usuario.OBTER_USUARIO_POR_EMAIL, Usuario.class);
		query.setParameter("pEmail", usuarioLogin.getEmail());
		query.setParameter("pSenha", usuarioLogin.getSenha());

		try {
			usuario = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return usuario;
	}

	public Usuario find(Integer id) {
		return dao.find(id);
	}

	public void persist(Usuario t) {
		dao.persist(t);
	}

	public void remove(Usuario t) {
		dao.remove(t);
	}

	public void merge(Usuario t) {
		dao.merge(t);
	}

}
