package SQLCONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private final static String url = "jdbc:sqlserver://localhost:1433;" + "databaseName = ManejadorPUCMM;integratedSecurity = true";
	
	//Connection
	public static Connection getConnection() throws SQLException{
		Connection myConnection = DriverManager.getConnection(url);
		return myConnection;
	}
}
