package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private static Connection conn = null;
	
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/academia"; //Nome da base de dados
		String user = "db_adm_academia"; //nome do usuário do MySQL
		String password = "1234"; //senha do MySQL
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
		
	}
	
	public static Connection teste(){
		String url = "jdbc:mysql://localhost:3306/academia"; //Nome da base de dados
		String user = "db_adm_academia"; //nome do usuário do MySQL
		String password = "1234"; //senha do MySQL
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexao;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
}
