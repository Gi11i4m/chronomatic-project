package be.artesis.timelog.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class JSONOverdrager
{
    public static JSONObject getGebruiker(String sessionKey) throws IOException, JSONException {
	
    	JSONObject jObject = Connection.getObject("gebruiker/read/" + sessionKey);
		return jObject;
	}
       
    public static JSONObject[] getProjectenVanGebruiker(String sessionKey) throws IOException, JSONException {
		return JArrayToJSONArrayArray(Connection.getArray("gebruiker/projecten/" + sessionKey ));		
	}
	
	public static JSONObject[] getTakenVanProject(String sessionKey, int projectID) throws IOException, JSONException {
		return  JArrayToJSONArrayArray(Connection.getArray("project/tasks/" + sessionKey + "/" + projectID ));		
	}               
	
        
	public static JSONObject[] getOpdrachtgeverVanGebruiker(String sessionKey) throws IOException, JSONException {
		return JArrayToJSONArrayArray(Connection.getArray("gebruiker/opdrachtgevers/" + sessionKey ));
	}
	
    public static JSONObject[] getTijdspannesVanTaak(String sessionKey, int taakID, boolean pauze) throws IOException, JSONException {
    	return JArrayToJSONArrayArray(Connection.getArray("task/tijdspannes/" + sessionKey + "/" + taakID + "/" + pauze ));		
	}        	
	
    private static JSONObject[] JArrayToJSONArrayArray(JSONArray jArray) {
    	JSONObject[] JSONArray = new JSONObject[jArray.length()];
        for(int i=0;i<jArray.length();i++) {
            try {
                JSONArray[i] = jArray.getJSONObject(i);
            } catch (JSONException ex) {
                Logger.getLogger(JSONOverdrager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return JSONArray;
    }
        
        
        
         /*
	public static JSONObject getProject(String sessionKey, int projectID)
	{		
		return Connection.getObject("project/read/" + sessionKey + "/" + projectID);
	}
        
	public static JSONObject getTaak(String sessionKey, int taakID)
	{
		return Connection.getObject("task/read/" + sessionKey + "/" + taakID );		
	}
       
	public static JSONObject[] getTijdspanne(String sessionKey, int TijdspanneID)
	{
                return JArrayToJSONArrayArray(Connection.getArray("tijdspanne/read/" + sessionKey + "/" + TijdspanneID ));		
	}
	
	public static JSONObject getOpdrachtgever(String sessionKey, int opdrachtgeverID)
	{
            //geeft een bepaalde opdracht gever weer adhv de opdrachtgeverID
		return Connection.getObject("opdrachtgever/read/" + sessionKey + "/" + opdrachtgeverID );		
	}
        public static JSONObject makeConnection(String type)
	{
		try {
			URL url = new URL("http://localhost:8080/ChronomaticServer/" + type);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(
				new InputStreamReader((conn.getInputStream())));
		
			JSONArray jArray = new JSONArray(br.readLine());
			
			JSONObject jObject = new JSONObject();
			
			// JSONArray to JSONObject
			for(int i=0;i<jArray.length();i++) {
				try {
					jObject = jArray.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			//
			
			return jObject;
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
        }*/
}