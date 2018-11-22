package com.framework.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

@ManagedBean(name = "cardLoginMB")
@ViewScoped
public class CardLoginMB {

	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	private void apiRecebeDadosCartao() {
		String nomeUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("nomeUsuario");

		String matricula = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("matricula");

		usuario.setNomeUsuario(nomeUsuario);
		usuario.setMatricula(matricula);
	}

	public String enviar() {
		
		apiRecebeDadosCartao();
		
		usuario = usuarioDao.getUsuarioMatricula(usuario.getNomeUsuario(),usuario.getMatricula()); 
		if (usuario == null ) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula não encontrada", "Erro no Login!"));
			return null;
		}	else {
			return "/acessoLiberado";
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
