package be.artesis.timelog.model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Connection {
        
        //private static final String server = "http://chronomatic-artesisti.rhcloud.com/ChronomaticServer/";
        private static final String server = "http://localhost:8080/ChronomaticServer/";
        public static URL normalize(String commando) throws MalformedURLException {
            return new URL((server + commando).replaceAll(" ", "%20"));
        }

	public static boolean execute(String commando) throws IOException, WebserviceException{
            URL url = normalize(commando);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new WebserviceException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            return true;
	}
	
	public static JSONObject getObject(String commando) throws IOException, JSONException{
            JSONObject jObject = null;
            URL url = normalize(commando);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            System.out.println(url);
            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			//sjonarray.addString
			//om het probleem op te vangen in de connection class dat ge gene string in de constructor kunt toevoegen
            JSONArray jArray = new JSONArray(br.readLine());
            JSONArray jArray = new JSONArray();
            
            for(int i=0;i<jArray.length();i++)
            {
                jObject = jArray.getJSONObject(i);
            }				 
            return jObject;
	}
        public static JSONArray getArray(String commando) throws IOException, JSONException{		
            JSONArray Jarray = null;
            URL url = normalize(commando);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            Jarray = new JSONArray(br.readLine());
            return Jarray;
	}
}
