package be.artesis.timelog.lokaleopslag;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalDatabaseWriter {
	//hier aant troubleshooten. Het opslaan en opvragen van shit van begin tot eind door de lokale db
	
		public static final String URL = "C:\\Users\\Stijn\\Desktop\\";		
		public static final String EXTENSIE = ".txt";									
			
		public static void schrijfweg(JSONObject j, Commando c) throws IOException{							
			 
			FileWriter file = new FileWriter(URL + c.toString().toLowerCase() + EXTENSIE);
			file.write(j.toString());
			file.flush();
			file.close();
		 			
		}
		
		
		public static JSONObject  makeDatabase(Commando c) throws JSONException, IOException{
						
			JSONArray jarOpdrachtgevers = new JSONArray();
			JSONArray jarProjecten = new JSONArray();
			JSONArray jarTijdspannes = new JSONArray();
			JSONArray jarTaken = new JSONArray();
			
			//JSONObject jOpdrachtgevers = new JSONObject();
			//JSONObject jProjecten = new JSONObject();
			//JSONObject jTijdspannes = new JSONObject();
			//JSONObject jTaken = new JSONObject();
			
			
			
			JSONObject collection = new JSONObject();
			collection.put(JVelden.OPDRACHTGEVERS.toString().toLowerCase() ,jarOpdrachtgevers );
			collection.put(JVelden.PROJECTEN.toString().toLowerCase() , jarProjecten );
			collection.put(JVelden.TAKEN.toString().toLowerCase(),jarTaken );
			collection.put(JVelden.TIJDSPANNES.toString().toLowerCase(),jarTijdspannes );				
			
			schrijfweg(collection,c);
			
			return collection;
			
			//System.out.println(collection);
		}

	
}










