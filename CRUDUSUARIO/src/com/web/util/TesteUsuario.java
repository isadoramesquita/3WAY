package com.web.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.web.DAO.UsuarioDAO;
import com.web.model.Usuario;

public class TesteUsuario {

	public static void main(String[] args) {
		Connection con;
		try {
			con = ConnectionFactory.getConexao();
			if (con != null)
				System.out.println("Conexão estabelecida! UHUU!");
			System.out.println("____________________________________________________");
			con.close();
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = dao.consultar(2);
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getMatricula());
			System.out.println(usuario.getNome());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}