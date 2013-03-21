package be.artesis.timelog.externAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserInfo {

	public static String request(String token, SocialMedia social) throws IOException, JSONException {
		
		StringBuilder params = new StringBuilder();
		params.append(social.getUserInfoUrl());
		params.append("access_token=");
		params.append(token);
		
		URL url = new URL(params.toString());
		
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    if (conn.getResponseCode() != 200){
	        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	    }
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String readLine;
	    String JSONString = null;
	    while ((readLine = br.readLine()) != null) {
	        JSONString+=(readLine);
	    }
	    
	    br.close();
	    
	    JSONString = JSONString.replace(" ","");
	    JSONString = JSONString.replace("null", "");
	    
	    JSONObject requestedJson = new JSONObject(JSONString);
	    //System.out.println(requestedJson.getJSONObject("emails"));
	    
	    
//	    String info = "id: " + requestedJson.getString("id");
//	    info += "\r\nemail: " + requestedJson.getString("email");
	    return requestedJson.getString("email"); // MS
	    //return requestedJson.getString("email");
	}
}
