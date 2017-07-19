package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

//	JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/posdb";
	
//	Database credentials
	static final String USER = "diieem";
	static final String PASS = "diieem1993";
	
	public static Connection getConnection(){
		Connection conn = null;
		
		try{
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn) throws SQLException{
		conn.close();
	}
}
