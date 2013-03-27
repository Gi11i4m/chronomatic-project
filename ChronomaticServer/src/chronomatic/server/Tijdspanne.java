package chronomatic.server;

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

@Path("tijdspanne/")
public class Tijdspanne {
	
	@GET 
	@Path("read/{sessionKey}/{tijdspanneID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readSession(@PathParam("sessionKey") String sessionKey,@PathParam("tijdspanneID") String tijdspanneID ) { 
		Connection con = DatabaseContainer.getConnection();
		
		int userID = Authentication.getUserId(sessionKey);
		
		String query = "SELECT begin_uur, eind_uur,pauze FROM projecten AS P INNER JOIN taken AS T INNER JOIN tijdspanne AS G ON P.gebruikers_id = " + userID + " AND P.ID = T.projecten_ID AND T.ID = G.taken_ID AND G.ID = " + tijdspanneID;

		JSONArray returnObject = new JSONArray();
		
		try { 
			if(userID > 0) { 
				ResultSet rs = Database.executeQuery(con, query);
				returnObject = ResultsetConverter.convert(rs);
			}
			else { 
				JSONObject error = new JSONObject();
				error.put("error","Invalid session");
				returnObject.put(error);
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		
		return returnObject.toString();
	}
	
	
	@GET 
	@Path("create/{sessionKey}/{beginUur}/{eindUur}/{pauze}/{taakID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createSession(@PathParam("sessionKey") String sessionKey, @PathParam("beginUur") int beginUur,@PathParam("eindUur") int eindUur, @PathParam("pauze") boolean pauze, @PathParam("taakID") int taakID) {
		Connection con = DatabaseContainer.getConnection();
		
		int userID = Authentication.getUserId(sessionKey);
		
		String query = "INSERT INTO tijdspanne(begin_uur, eind_uur, pauze, taken_ID) VALUES(" + beginUur + ", " + eindUur + "," + pauze + "," + taakID + ")";
		System.out.println(query);
		try { 
			if(userID > 0) { 
				if(Database.executeNullQuery(con, query))
					return "[{\"success\":true}]";
				else 
					return "[{\"success\":false,\"error\":\"Invalid taskID\"}]";
			}
			else { 
				return "[{\"success\":false,\"error\":\"Session is invalid\"}]";
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "[{\"success\":false,\"error\":\"Invalid parameters\"}]";
	}
	
	@GET 
	@Path("update/{sessionKey}/{beginUur}/{eindUur}/{pauze}/{tijdspanneID}/{taken_ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateSession(@PathParam("sessionKey") String sessionKey, @PathParam("beginUur") int beginUur,@PathParam("eindUur") int eindUur, @PathParam("pauze") boolean pauze, @PathParam("tijdspanneID") int tijdspanneID, @PathParam("taken_ID") int taken_ID) { 
		Connection con = DatabaseContainer.getConnection();
		
		int userID = Authentication.getUserId(sessionKey);
		
		String query = "UPDATE tijdspanne SET begin_uur = " + beginUur + ", eind_uur = " + eindUur + ", pauze = " + pauze + ", taken_ID = " + taken_ID + " WHERE ID = " + tijdspanneID + " AND (SELECT COUNT(ID) FROM tijdspanne AS TP INNER JOIN taken AS T INNER JOIN projecten AS P  ON TP.ID = " + tijdspanneID + " AND TP.taken_ID = T.ID AND T.ID = P.taken_ID AND P.gebruikers_ID = " + userID + ") > 0 ";
		
		try { 
			if(userID > 0) { 
				if(Database.executeNullQuery(con, query))
					return "[{\"success\":true}]";
				else 
					return "[{\"success\":false,\"error\":\"Invalid taskID\"}]";
			}
			else { 
				return "[{\"success\":false,\"error\":\"Session is invalid\"}]";
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "[{\"success\":false,\"error\":\"Invalid parameters\"}]";
	}
	
	
	@GET 
	@Path("delete/{sessionKey}/{tijdspanneID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteSession(@PathParam("sessionKey") String sessionKey,  @PathParam("tijdspanneID") int tijdspanne) { 
		Connection con = DatabaseContainer.getConnection();
		
		int userID = Authentication.getUserId(sessionKey);
		
		String query = "DELETE FROM tijdspanne WHERE ID = " + tijdspanne + " AND (SELECT COUNT(ID) FROM tijdspanne AS TP INNER JOIN taken AS T INNER JOIN projecten AS P  ON TP.ID = " + tijdspanne + " AND TP.taken_ID = T.ID AND T.ID = P.taken_ID AND P.gebruikers_ID = " + userID + ") > 0 ";
		
		try { 
			if(userID > 0) { 
				if(Database.executeNullQuery(con, query))
					return "[{\"success\":true}]";
				else 
					return "[{\"success\":false,\"error\":\"Invalid taskID\"}]";
			}
			else { 
				return "[{\"success\":false,\"error\":\"Session is invalid\"}]";
			}
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "[{\"success\":false,\"error\":\"Invalid parameters\"}]";
	}
}
