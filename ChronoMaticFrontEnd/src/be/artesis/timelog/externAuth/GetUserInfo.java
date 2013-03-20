/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.artesis.timelog.externAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;


public class GetUserInfo {

	public static String retreive(String token, String provider) throws IOException, JSONException {
                
            String urlGoogle = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token;
            String urlFacebook = "https://graph.facebook.com/me?fields=email&access_token=" + token;
            URL url = new URL(urlFacebook);

            if(provider.equals("Facebook")) {
                    url = new URL(urlFacebook);
            }
            else if(provider.equals("Google")) {
                    url = new URL(urlGoogle);
            }
            else if(provider.equals("Microsoft")) {
                url = new URL(urlGoogle);
            }
		
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    if (conn.getResponseCode() != 200){
	        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String line;
	    String test = null;
	    while ((line = br.readLine()) != null)
	    {
	        test+=(line);
	    }
	    
	    br.close();
	    
	    test = test.replace(" ","");
	    test = test.replace("null", "");
	    
	    JSONObject requestedJson = new JSONObject(test);
	    
//	    String info = "id: " + requestedJson.getString("id");
//	    info += "\r\nemail: " + requestedJson.getString("email");
	    
	    return requestedJson.getString("email");
	}
}
