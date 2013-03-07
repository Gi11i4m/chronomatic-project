package be.artesis.timelog.controller;

import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;
import java.io.IOException;
import java.net.MalformedURLException;

public class Deleter {
	
	public static void deleteTijdSpanne(String sessionKey, Tijdspanne tijdSpanne) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("tijdSpanne/delete/" + sessionKey + "/" + tijdSpanne.getID());
	}
	
	public static void deleteProject(String sessionKey, Project project) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("project/delete/" + sessionKey + "/" + project.getId());
	}

	public static void deleteOpdrachtgever(String sessionKey, Opdrachtgever opdrachtgever) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("opdrachtgever/delete/" + sessionKey + "/" + opdrachtgever.getID());
	}

	public static void deleteTaak(String sessionKey, Taak taak) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("task/delete/" + sessionKey + "/" + taak.getID());
	}

}
