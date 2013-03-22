package chronomatic.email;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.ResultsetConverter;

import chronomatic.database.Database;
import chronomatic.database.DatabaseContainer;
import chronomatic.server.Authentication;

@Path("email/")
public class EmailVerificatie {
	
	@GET
	@Path("verificatie/{email}/{hash}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String verificatie(@PathParam("email") String email,@PathParam("hash") String hash) { 
		
		Connection con = DatabaseContainer.getConnection();
		
		String query =  "SELECT hash FROM gebruikers WHERE email = '" + email + "'";
		

		try{
			ResultSet rs = Database.executeGetQuery(con,query);
			//returnObject = ResultsetConverter.convert(rs);
			
			String piet = rs.getString(1); // kan anders zijn...?...
			
			if (piet == email) {
				// Zeg da de shit goe is uitgevoerd en kan inloggen
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		
		
		/*
		if(hash== hash & email == email) {
			het is goed
		}
		*/
		return "";
	}

}
