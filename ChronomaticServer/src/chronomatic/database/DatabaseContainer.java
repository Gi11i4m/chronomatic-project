package chronomatic.database;

import java.sql.*;

/**
 * Deze klasse haalt de configuratie op voor de huidige server
 * Tijdelijk worden de waardes in de code zelf aangepast, later komt dit uit een .xml bestand of dergelijke.
 * @author Daim
 *
 */
public class DatabaseContainer {
	
	// Online server
	/*
	private static String host = "127.4.109.129";
	private static String user = "admin4VATmAC";
	private static String password = "8yK1xX8kJXTe";
	private static String db_name = "chrono_matic";
	*/
	
	// Lokale server
	private static String host = "127.0.0.1";
	private static String user = "root";
	private static String password = "";
	private static String db_name = "chrono_matic";
	
	
	public static Connection getConnection() {
		return Database.getConnection(host,user,password,db_name);
	}
	
}
