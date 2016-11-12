package DAOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static Connection conn;
	private static final String URL = "jdbc:mysql://localhost:3306/mydb?" + "user=root&password=1212";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
		} catch (SQLException | ClassNotFoundException e) {
			// handle any errors
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + ((SQLException) e).getSQLState());
		    System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
	}
	
	public static Connection conexion() throws SQLException{
		if (conn.isClosed()){
			conn = DriverManager.getConnection(URL);
		}
		return conn;
	}
}
