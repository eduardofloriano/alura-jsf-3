package br.com.alura.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.util.JpaUtil;

@ManagedBean
@ViewScoped
public class LoginBean {

	private EntityManager em = new JpaUtil().getEntityManager();
	private UsuarioDAO usuarioDAO = new UsuarioDAO(em);

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String autenticar() {

		Usuario usuario = usuarioDAO.obterUsuarioPorEmail(this.usuario);

		FacesContext context = FacesContext.getCurrentInstance();

		if (usuario == null) {
			context.addMessage(null,
					new FacesMessage("Usuario não encontrado."));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "login?faces-redirect=true";
		} else {
			context.getExternalContext().getSessionMap()
					.put("usuarioLogado", usuario);
			// context.getExternalContext().getSessionMap().put("emailUsuario",
			// usuario.getEmail());
			return "livro.xhtml?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return "login.xhtml?faces-redirect=true";

	}

}
