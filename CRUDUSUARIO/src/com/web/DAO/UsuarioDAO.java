package com.web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.model.Usuario;
import com.web.util.ConnectionFactory;
public class UsuarioDAO {

	private static final String UPDATE_SQL = "UPDATE USUARIO SET NOME = ?, MATRICULA = ?  WHERE ID =?";
	private static final String OBTER_POR_ID_SQL = "SELECT * FROM USUARIO WHERE ID =?";
	private static final String OBTER_TODOS_SQL = "SELECT * FROM usuario";
	private static final String INSERT_INTO = "INSERT INTO usuario (NOME, LOGIN, MATRICULA) VALUES  (?,?,?);";
	private static final String CONSULTARSQL = "SELECT * FROM USUARIO WHERE ID LIKE ?";
	private static final String DELETE = "DELETE FROM USUARIO WHERE ID = ?";

	public Usuario consultar(int codigo) {
		Usuario usuario = null;

		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(OBTER_POR_ID_SQL);) {
			consulta.setInt(1, codigo);

			ResultSet resultado = consulta.executeQuery();
			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(resultado.getInt("ID"));
				usuario.setLogin(resultado.getString("LOGIN"));
				usuario.setMatricula(resultado.getInt("MATRICULA"));
				usuario.setNome(resultado.getString("NOME"));

			}
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public ArrayList<Usuario> listartodos() {
		ArrayList<Usuario> listartodos = new ArrayList<>();

		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(OBTER_TODOS_SQL);) {

			ResultSet resultado = consulta.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("ID"));
				usuario.setLogin(resultado.getString("LOGIN"));
				usuario.setMatricula(resultado.getInt("MATRICULA"));
				usuario.setNome(resultado.getString("NOME"));
				listartodos.add(usuario);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listartodos;
	}

	public void update(Usuario usuario) {
		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(UPDATE_SQL);) {

			consulta.setString(1,usuario.getLogin());
			consulta.setString(2, usuario.getNome());
			consulta.setInt(3, usuario.getMatricula());
			consulta.setInt(4, usuario.getId());
			consulta.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
		
		
	public void save(Usuario usuario) {
		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement ps = conexao.prepareStatement(INSERT_INTO);) {
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setInt(3, usuario.getMatricula());

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(DELETE);) {

			consulta.setInt(1, id);

			consulta.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public List<Usuario> consultar(String id) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try (Connection conexao = ConnectionFactory.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(CONSULTARSQL);) {
			consulta.setString(1, "%" + id.toUpperCase() + "%");

			ResultSet resultado = consulta.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("ID"));
				usuario.setLogin(resultado.getString("LOGIN"));
				usuario.setId(resultado.getInt("MATRICULA"));
				usuario.setNome(resultado.getString("NOME"));

				lista.add(usuario);
			}

			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
