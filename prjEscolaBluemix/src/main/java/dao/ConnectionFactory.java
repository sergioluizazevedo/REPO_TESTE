package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.primefaces.expression.impl.ThisExpressionResolver;

public abstract class ConnectionFactory {

	private static final String URL = "jdbc:mysql://us-cdbr-sl-dfw-01.cleardb.net/ibmx_e5d910b669a088e";
	private static final String PASSWORD = "3775f8af";
	private static final String USER = "bfbd2cc4552201";

	/**
	 * private static final String URL = "jdbc:mysql://localhost:3306/db_escola";
	 * private static final String PASSWORD = "pass4root"; private static final
	 * String USER = "root";
	 * 
	 */

	public static Connection getConnection() {

		try {
			//// Faz com que a classe seja carregada pela JVM
			Class.forName("com.mysql.jdbc.Driver");
			// Cria a conex�o com o banco de dados
			return DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {
			System.out.println("Ocorreu um erro no getConnection" + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void closeAll(Connection conexao, PreparedStatement stm, ResultSet rs) {
		try {

			// se rs for diferente de null e rs n�o estiver fechado
			if (rs != null && !rs.isClosed()) {
				rs.close();
				System.out.println("ResultSet Fechado");
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
				System.out.println("PreparedStatement Fechado");
			}
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
				System.out.println("Connection Fechado");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
