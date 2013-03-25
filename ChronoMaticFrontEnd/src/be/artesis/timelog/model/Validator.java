package be.artesis.timelog.model;

import java.io.BufferedReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.secure.MD5Generator;

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
        
		MD5Generator MD5 = new MD5Generator();
		//JSONObject jObject = Connection.getObject("auth/login/" + gebruikersnaam+ "/"+ MD5.gen(paswoord));
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
	
	public boolean loginExtern(String email) throws IOException, JSONException, WebserviceException{
			  
        JSONObject jObject = Connection.getObject("auth/loginExtern/" + email);

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