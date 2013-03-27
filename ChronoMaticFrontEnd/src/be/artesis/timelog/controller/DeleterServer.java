package be.artesis.timelog.controller;

import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;
import java.io.IOException;
import java.net.MalformedURLException;

public class DeleterServer {
	
	public static void deleteTijdSpanne(String sessionKey, int id) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("tijdSpanne/delete/" + sessionKey + "/" + id);
	}
	
	public static void deleteProject(String sessionKey, int id) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("project/delete/" + sessionKey + "/" + id);
	}

	public static void deleteOpdrachtgever(String sessionKey, int id) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("opdrachtgever/delete/" + sessionKey + "/" + id);
	}

	public static void deleteTaak(String sessionKey, int id) throws MalformedURLException, IOException, WebserviceException {
		Connection.execute("task/delete/" + sessionKey + "/" + id);
	}

}
