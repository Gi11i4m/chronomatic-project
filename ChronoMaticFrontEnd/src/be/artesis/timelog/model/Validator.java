package be.artesis.timelog.model;

import java.io.BufferedReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Validator
{
	private volatile static Validator uniqueInstance;
	private String sessionKey;

	private Validator() {}

	public static Validator getInstance() {
		if(uniqueInstance == null){
			synchronized (Validator.class){
				if(uniqueInstance == null){
					uniqueInstance = new Validator();
				}
			}
		}
		return uniqueInstance;
	}
	public String ValideerGebruiker(String username , String paswoord){

		return "";
	}

	public String getSessionKey() {
		return sessionKey;
	}

	private void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public boolean login(String gebruikersnaam, String paswoord) throws IOException, JSONException, WebserviceException{
   		
		  				   			   					  
                JSONObject jObject = Connection.getObject("auth/login/" + gebruikersnaam+ "/"+ paswoord);

                if(jObject.has("error")){
                 throw new WebserviceException("Failed : HTTP error code : "
                                        + jObject.getString("error"));

                }else if(jObject.getString("key") != ""){
                        setSessionKey(jObject.getString("key"));
                        return true;

                }else{
                   return false; 
                }
		

		
		
		
	}

	public boolean valideerSessie() throws IOException, JSONException{

		boolean returnBool = false;

						
		JSONObject jObject = Connection.getObject("auth/check/" + sessionKey );
		   	   // JSONArray to JSONObject

	   	returnBool = jObject.getBoolean("success");

		


		return returnBool;
	}
}