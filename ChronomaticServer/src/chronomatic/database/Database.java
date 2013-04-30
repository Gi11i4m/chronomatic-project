package chronomatic.database;

import java.sql.*;

public class Database {
	public static Connection con = null;
	
	public static Connection getConnection(String host, String user, String password, String db_name) {
		try {
	
			
			if(con == null) {
				System.out.println("New connection established");
				Class.forName("com.mysql.jdbc.Driver");	
				con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db_name,user,password);
				return con;
			}
			else if(con.isClosed()) {
				return con;
			}
			else { 
				return con;
			}			
		}
		catch (SQLException e) { 
			System.out.println("Fout opgetreden bij het verbinden met MySQL");
		}
		catch (Exception e) { 
			System.out.println("Fout opgetreden bij het laden van de driver" + e.toString());
		}
		return null;
	}
	
	public static boolean executeNullQuery(Connection con,String query) { 
		try {
			if(!con.isClosed()) { 
				Statement s =  con.createStatement();
				return (s.executeUpdate(query) > 0);
			}
		}
		catch(SQLException e) { 
			System.out.println("Fout met het uitvoeren van query" + e.toString());
		}
		return false;
	}
	
	public static ResultSet executeGetQuery(Connection con, String query) throws Exception { 
		try {
			if(!con.isClosed()) { 
				Statement s =  con.createStatement();
				//ResultSet rs = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				//System.out.println(s.getGeneratedKeys());
				ResultSet rs = s.getGeneratedKeys();
				return rs;
			}
			else 
				throw new Exception("Connection was not opened");
		}
		catch(SQLException e) { 
			throw new Exception("Fout met het uivoeren van query");
		}
	}
	
	public static ResultSet executeQuery(Connection con, String query) throws Exception { 
		try {
			if(!con.isClosed()) { 
				Statement s =  con.createStatement();
				ResultSet rs = s.executeQuery(query);
				return rs;
			}
			else 
				throw new Exception("Connection was not opened");
		}
		catch(SQLException e) { 
			throw new Exception("Fout met het uivoeren van query");
		}
	}
	
}
