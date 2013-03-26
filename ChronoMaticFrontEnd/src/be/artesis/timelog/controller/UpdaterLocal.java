package be.artesis.timelog.controller;


import be.artesis.timelog.lokaleopslag.Commando;
import be.artesis.timelog.lokaleopslag.LocalDatabaseWriter;
import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Gebruiker;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

public class UpdaterLocal {
	
	private static final LocalDatabaseWriter wr = new LocalDatabaseWriter(Commando.UPDATE);	

	public static void updateGebruiker(String sessionKey, Gebruiker gebruiker) throws MalformedURLException, IOException, WebserviceException{
		Connection.execute("gebruiker/update/" + sessionKey + "/" + gebruiker.getNaam() + "/" + gebruiker.getVoornaam() + "/" + gebruiker.getGebruikersnaam() + "/" +gebruiker.getEmail());
	}
        
	public static void updateProject(String sessionKey, Project project,int opdrachtgeverId) throws MalformedURLException, IOException, WebserviceException, JSONException {
		wr.schrijfProject(project, opdrachtgeverId);
	}

	public static void updateOpdrachtgever(String sessionKey, Opdrachtgever opdrachtgever) throws MalformedURLException, IOException, WebserviceException, JSONException{
		wr.schrijfOpdrachtgever(opdrachtgever);
	}
	// FIXME Indien dit niet lukt, effe checken hoe het enkele commits terug was en aanpassen
	public static void updateTaak(String sessionKey, Taak taak, int projectId) throws MalformedURLException, IOException, WebserviceException, JSONException {
		wr.schrijfTaak(taak, projectId);
    }
	public static void updateTijdspanne(String sessionKey, Tijdspanne tijdspanne,int taakId) throws IOException, WebserviceException, JSONException{		 
		wr.schrijfTijdspannne(tijdspanne,taakId);
	}
}