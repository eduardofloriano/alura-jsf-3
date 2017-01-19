package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.model.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private FacesContext context;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String autenticar() {

		Usuario usuario = usuarioDAO.obterUsuarioPorEmail(this.usuario);

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

		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return "login.xhtml?faces-redirect=true";

	}

}
