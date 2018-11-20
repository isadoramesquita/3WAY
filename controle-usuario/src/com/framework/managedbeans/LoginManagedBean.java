package com.framework.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped

public class LoginManagedBean {

	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public String envia() {
		usuario = usuarioDao.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());

		FacesContext context = FacesContext.getCurrentInstance();

		if (usuario == null) {
			usuario = new Usuario();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usu�rio n�o encontrado!", "Erro no Login!"));
			context.validationFailed();


			return null;
		} else {
			context.getExternalContext().getSessionMap().put("logado", true);
			return "/restrito/listagemUsuario.xhtml?faces-redirect=true";
		}
	}

	// realiza o logout do usuario logado
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("logado");
		return "/login/login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
