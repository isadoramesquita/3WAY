package main;

import java.util.Date;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

public class Testar {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();

		Usuario usuario = new Usuario();
		// usuario.setId(7);
		usuario.setNomeUsuario("admin");
		usuario.setSenha("12345");
		usuario.setMatricula("20204433");
		
		usuario.setNomeUsuario("Isadora");
		usuario.setMatricula("5555555");
		usuario.setSenha("senha");
		
		usuario.setNomeUsuario("Joao Da Silva");
		usuario.setMatricula("2324232");
		usuario.setSenha("54321");
		
		

		usuario.setUltimoAcesso(new Date());

		dao.inserirUsuario(usuario);

	}
}
