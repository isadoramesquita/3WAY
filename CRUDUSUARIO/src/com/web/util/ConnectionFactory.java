package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static String url = "jdbc:postgresql://localhost:5432/3WAY"; //colocar depois de localhost o nome do banco de dado
	public static String usuario = "postgres";
	public static String senha = "mesquita";
	
	public static Connection getConexao() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection(url,usuario,senha);
					
		}catch(ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
