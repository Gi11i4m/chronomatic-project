package chronomatic.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.ResultsetConverter;

import chronomatic.database.Database;
import chronomatic.database.DatabaseContainer;
import chronomatic.email.MailNewUser;
import chronomatic.email.Mailer;

@Path("gebruiker/")
public class Gebruiker {
	
	@GET
	@Path("projecten/{sessieID}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getProjecten(@PathParam("sessieID") String sessieID) {
		Connection con = DatabaseContainer.getConnection();
		
		int userID = Authentication.getUserId(sessieID);
		String query =  "SELECT ID, naam, start_datum, eind_datum, opdrachtgevers_ID FROM projecten WHERE gebruikers_ID = '" + userID + "'";
		
		try{
			ResultSet rs = Database.executeQuery(con, query); 
			if(rs.next()) {
				
				rs.previous();
				JSONArray returnObject = ResultsetConverter.convert(rs);

				return returnObject.toString();
			}
			else { 
				// Geen projecten
				JSONObject error = new JSONObject();
				error.put("error","Geen projecten");
				
				return "["+error.toString()+"]";
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return "error";
	}
	
	@GET
	@Path("opdrachtgevers/{sessieID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOpdrachtgevers(@PathParam("sessieID") String sessieID) { 
		Connection con = DatabaseContainer.getConnection();		
		int userID = Authentication.getUserId(sessieID);
		
		String query = "SELECT bedrijfsnaam, email, ID, naam, telefoonnummer, voornaam, gebruikers_ID FROM opdrachtgevers WHERE gebruikers_ID = " + userID;
		try
		{
			ResultSet rs = Database.executeQuery(con, query); 
			if(rs.next())
			{
				rs.previous();
				JSONArray returnObject = ResultsetConverter.convert(rs);
				return returnObject.toString();
			}
			else 
			{ 
				// Geen project
				JSONObject error = new JSONObject();
				error.put("error","Geen opdrachtgevers");
				
				return "["+error.toString()+"]";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("read/{sessieID}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String read(@PathParam("sessieID") String sessieID) {
		Connection con = DatabaseContainer.getConnection();		
		int userID = Authentication.getUserId(sessieID);
		String query =  "SELECT ID, naam, voornaam, gebruikersnaam, email FROM gebruikers WHERE ID = '" + userID + "'";
		
		try
		{
			ResultSet rs = Database.executeQuery(con, query); 
			if(rs.next())
			{
				rs.previous();
				JSONArray returnObject = ResultsetConverter.convert(rs);

				return returnObject.toString();
			}
			else 
			{ 
				JSONObject error = new JSONObject();
				error.put("error","No projects");
				
				//return error.toString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GET
	@Path("create/{naam}/{voornaam}/{gebruikersnaam}/{password}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@PathParam("naam") String naam,@PathParam("voornaam") String voornaam,@PathParam("gebruikersnaam") String gebruikersnaam,@PathParam("password") String password,@PathParam("email") String email) 
	{
		
		Connection con = DatabaseContainer.getConnection();
		String hash = null;
		Random ran = new Random();
		try {
			hash = RandomMD5.generate((Integer.toString((ran.nextInt()))));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		String query = "INSERT INTO gebruikers (naam,voornaam,gebruikersnaam,passwoord,email, hash, validated) VALUES ('"+naam+"','"+ voornaam +"','"+gebruikersnaam+"','" + password + "','" + email + "','" + hash + "','0')";
		JSONObject returnObject = new JSONObject();
		
		try {
			returnObject.put("result", Database.executeNullQuery(con, query));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mailer mailer = new MailNewUser(gebruikersnaam, email, hash);
		mailer.sendMail();
		
		return "["+returnObject.toString()+"]";
	}
	
	// Create user met externe auth
	@GET
	@Path("createExtern/{naam}/{voornaam}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@PathParam("naam") String naam,@PathParam("voornaam") String voornaam,@PathParam("email") String email) 
	{
		Connection con = DatabaseContainer.getConnection();
		
		String query = "INSERT INTO gebruikers (naam,voornaam,gebruikersnaam,email) VALUES ('"+naam+"','"+ voornaam +"','"+email+" ','"+email+"')";
		
		JSONObject returnObject = new JSONObject();
		//Mailer.sendMail(email);
		try {
			returnObject.put("result", Database.executeNullQuery(con, query));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "["+returnObject.toString()+"]";
	}
	
	@GET
	@Path("update/{sessieID}/{naam}/{voornaam}/{gebruikersnaam}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@PathParam("sessieID") String sessieID,@PathParam("naam") String naam,@PathParam("voornaam") String voornaam,@PathParam("gebruikersnaam") String gebruikersnaam,@PathParam("email") String email) 
	{ 
		int userID = Authentication.getUserId(sessieID);
		Connection con = DatabaseContainer.getConnection();
		System.out.println(userID);
		String query = "UPDATE gebruikers SET naam='" + naam + "', voornaam='" + voornaam + "', gebruikersnaam='" + gebruikersnaam + "', email='" + email + "' WHERE ID ='" + userID + "' LIMIT 1";
		
		JSONObject returnObject = new JSONObject();
		try {
			returnObject.put("result", Database.executeNullQuery(con, query));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObject.toString();
	}
	
	@GET
	@Path("checkExists/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String checkExists(@PathParam("username") String username) {
		Connection con = DatabaseContainer.getConnection();

		String query =  "SELECT Count(1) FROM gebruikers WHERE gebruikersnaam = '" + username + "'";

		try{
			ResultSet rs = Database.executeQuery(con, query); 
			if(rs.next()) {
				
				rs.previous();
				JSONArray returnObject = ResultsetConverter.convert(rs);
				//System.out.println(returnObject);
				return returnObject.toString();
			}
			else { 
				// Geen projecten
				JSONObject error = new JSONObject();
				error.put("error","Geen projecten");
				
				return error.toString();
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return "error";
	}

}
