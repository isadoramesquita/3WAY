package com.framework.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.ValidationException;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

@ManagedBean(name = "usuarioService")
@ApplicationScoped
public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public void telaEdicao(Usuario usario) throws ValidationException {
		if (!usario.getMatricula().startsWith("ADM")) {
			throw new ValidationException("Somente usuario com" + "perfil ADM podem acessar a página de edição");
		}
	}

	public void salvarUsuario(Usuario usuario) throws ValidationException {
		this.validarMatricula(usuario);
		usuario.setSenha(this.encriptarSenha(usuario.getSenha()));
		if (!usuarioDAO.inserirUsuario(usuario)) {
			throw new ValidationException("Usuário já existe");
		}
	}

	private void validarMatricula(Usuario usuario) throws ValidationException {
		if (!usuario.getMatricula().startsWith("M") && !usuario.getMatricula().startsWith("ADM")) {
			throw new ValidationException("A matricula do usuário deve iniciar com M ou ADM");
		}
	}

	public String encriptarSenha(String senha) {
		String senhaRetorno = "";
		MessageDigest algoritm;
		try {
			algoritm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algoritm.digest(senha.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaRetorno = hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return senhaRetorno;
	}

	public Usuario consultarUsuario(Integer idUsuario) {
		return usuarioDAO.getUsuario(idUsuario);
	}
	
	public void mergeUsuario(Usuario usuario) {
		usuarioDAO.alterarUsuario(usuario);
	}

	public void deletarUsuario(Usuario usuario) {
		usuarioDAO.deletarUsuario(usuario);
	}

	public static Usuario getUsuario(Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
