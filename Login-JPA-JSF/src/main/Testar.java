package main;

import java.util.Date;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

public class Testar {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();

		Usuario usuario = new Usuario();
		// usuario.setId(7);
		usuario.setNomeUsuario("Isadora Mesquita");
		usuario.setSenha("isadora");
		usuario.setMatricula("m123456");
		usuario.setUltimoAcesso(new Date());

		dao.inserirUsuario(usuario);

	}
}
