package com.dbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnection {

	private static final String SERVER = "localhost";
	private static final String PORT = "1521"; // Porta TCP padr�o do Oracle
	private static final String DATABASE = "xe";

	// Configura��o dos par�metros de autentica��o
	private static final String USER = "POKEMON";
	private static final String PASS = "oracle";

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;

		// Abre-se a conex�o com o Banco de Dados
		Connection con = DriverManager.getConnection(url, USER, PASS);

		// sempre usar o schema vem_ser
		con.createStatement().execute("alter session set current_schema=POKEMON");

		return con;
	}

}
