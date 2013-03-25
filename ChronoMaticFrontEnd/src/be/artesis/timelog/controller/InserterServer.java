package be.artesis.timelog.controller;

import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.*;

public class InserterServer {
	
	public static void CreateUser(String naam, String voornaam, String gebruikersnaam, String password, String email) throws MalformedURLException, IOException, WebserviceException {
        Connection.execute("gebruiker/create/" + naam + "/" + voornaam + "/" + gebruikersnaam + "/" + password + "/" +  email);
	}
	
	public static void CreateUserExtern(String naam, String voornaam, String email) throws MalformedURLException, IOException, WebserviceException {
        Connection.execute("gebruiker/createExtern/" + naam + "/" + voornaam + "/" + email);
	}

	public static int inputTijdSpanne(String sessionKey,long beginTijd, long eindTijd, boolean isPauze, int taakID) throws MalformedURLException, IOException, WebserviceException, JSONException {
		JSONObject key = Connection.getObject("tijdspanne/create/" + sessionKey + "/" + beginTijd + "/" + eindTijd + "/" +isPauze + "/" + taakID);
		return key.getInt("GENERATED_KEY");		
	}

	public static int inputProject(String sessionKey,String naam, long begindatum, long einddatum, int opdrachtGeverID) throws MalformedURLException, IOException, WebserviceException, JSONException {            
		JSONObject key = Connection.getObject("project/create/" + sessionKey + "/" + naam + "/" + begindatum + "/" + einddatum + "/" + opdrachtGeverID);
		return key.getInt("GENERATED_KEY");
    }

	public static int inputOpdrachtgever(String sessionKey,String bedrijfsnaam, String naam, String voornaam, String email, String telefoonnummer ) throws JSONException, MalformedURLException, IOException, WebserviceException {
            // nog korter maken, maar is voor leesbaarheid
        JSONObject key = Connection.getObject("opdrachtgever/create/" + sessionKey + "/" + bedrijfsnaam + "/" + naam + "/" + voornaam + "/" + email + "/" + telefoonnummer);
        return key.getInt("GENERATED_KEY");
	}

	public static int inputTaak(String sessionKey, String naam, long begindatum, long geschatteEinddatum, String commentaar, boolean completed  , int projectID) throws MalformedURLException, IOException, WebserviceException, JSONException {
        JSONObject key = Connection.getObject("task/create/" + sessionKey + "/" + naam + "/" + begindatum + "/" + geschatteEinddatum + "/" + commentaar + "/" + projectID + "/" + completed);
        return key.getInt("GENERATED_KEY");
    }
}
