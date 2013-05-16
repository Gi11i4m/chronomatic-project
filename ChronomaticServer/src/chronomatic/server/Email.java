package chronomatic.server;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chronomatic.database.Database;
import chronomatic.database.DatabaseContainer;

@Path("email/")
public class Email {
	
	@GET
	@Path("verificatie/{email}/{hash}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String verificatie(@PathParam("email") String email,@PathParam("hash") String hash) { 
		
		Connection con = DatabaseContainer.getConnection();
		
		String query =  "SELECT hash FROM gebruikers WHERE email = '" + email + "'";
		String returnHash = null;
		String output = "Foutieve verificatie code";
		
		String query2 = "UPDATE gebruikers SET validated = '1' WHERE hash='"+ hash +"' AND email = '"+ email +"'";
		try{
			ResultSet rs = Database.executeQuery(con, query);
			
			while(rs.next()) {
				returnHash = (String) rs.getObject(1);
			}
			
			if(hash.equals(returnHash)) {
				Connection concon = DatabaseContainer.getConnection();
				Database.executeNullQuery(concon, query2);
				output = "Uw e-mail adres werd geverifierd";
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		
		return output;
	}

}
