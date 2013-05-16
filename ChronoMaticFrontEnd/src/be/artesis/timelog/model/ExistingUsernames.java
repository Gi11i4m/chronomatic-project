package be.artesis.timelog.model;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class ExistingUsernames {

	public static boolean check(String gebruikersnaam, String email) throws JSONException {
		JSONObject jObject = null;
		try {
			jObject = Connection.getObject("gebruiker/checkExists/"+gebruikersnaam + "/" + email);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return (jObject.getInt("Count(1)") == 1) ;
		
	}
}
