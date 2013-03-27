package be.artesis.timelog.controller;


import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Gebruiker;


import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

public class UpdaterServer {

	public static void updateGebruiker(String sessionKey, Gebruiker gebruiker) throws MalformedURLException, IOException, WebserviceException{
		Connection.execute("gebruiker/update/" + sessionKey + "/" + gebruiker.getNaam() + "/" + gebruiker.getVoornaam() + "/" + gebruiker.getGebruikersnaam() + "/" +gebruiker.getEmail());
	}
        
	public static void updateProject(String sessionKey,int id, String naam, long begindatum, long einddatum, int opdrachtGeverID) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("project/update/" + sessionKey + "/" + id + "/" + naam + "/" + begindatum + "/" + einddatum + "/" + opdrachtGeverID);
	}

	public static void updateOpdrachtgever(String sessionKey,int id, String bedrijfsnaam, String naam, String voornaam, String email, String telefoonnummer) throws MalformedURLException, IOException, WebserviceException{
		Connection.execute("opdrachtgever/update/" + sessionKey + "/" + id+ "/" + bedrijfsnaam + "/" + naam + "/" + voornaam + "/" + email + "/" + telefoonnummer);
	}

	public static void updateTaak(String sessionKey,int id, String naam, long begindatum, long geschatteEinddatum, String commentaar, boolean completed  , int projectID) throws MalformedURLException, IOException, WebserviceException, JSONException {		
		Connection.execute("task/update/" + sessionKey + "/" + naam + "/" + begindatum+ "/" + geschatteEinddatum + "/" + commentaar + "/" + id + "/" + completed);
    }
	public static void updateTijdspanne(String sessionKey,int id,long beginTijd, long eindTijd, boolean isPauze, int taakID) throws MalformedURLException, IOException, WebserviceException, JSONException {		 
		Connection.execute("tijdspanne/update/" +sessionKey + "/" + eindTijd + "/" +eindTijd + "/" + isPauze + "/" + id + "/"+taakID);
	}
}