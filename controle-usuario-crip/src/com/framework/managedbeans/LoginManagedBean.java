package com.framework.managedbeans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;
import com.framework.service.UsuarioService;

@ManagedBean(name = "LoginMB")
@ViewScoped

public class LoginManagedBean {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	
	@ManagedProperty("#{usuarioService}")
	private UsuarioService usuarioService;

	public String envia() {
		//usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), getUsuarioService().encriptarSenha(usuario.getSenha()));
		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		FacesContext context = FacesContext.getCurrentInstance();
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usu�rio n�o encontrado!", "Erro no Login!"));
			FacesContext.getCurrentInstance().validationFailed();
			
			context.getExternalContext().getSessionMap().put("logado", false);
			 
			return null;
		} else {
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("id", usuario.getId());
			
			context.getExternalContext().getSessionMap().put("logado", true);
			return "/restrito/listagemUsuario.xhtml?faces-redirect=true";
		}
	}

	// Realiza o logout do usu�rio logado
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

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}