package be.artesis.timelog.controller;

import be.artesis.timelog.lokaleopslag.Commando;
import be.artesis.timelog.lokaleopslag.LocalDatabaseWriter;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.*;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.*;

public class InserterLocal {
	//ID's genereren????
	
	private static int projectId = 0;
	private static int opdrachtgeverId = 0;
	private static int taakId = 0;
	private static int tijdspannesId = 0;	
	
	private static final LocalDatabaseWriter wr = new LocalDatabaseWriter(Commando.INSERT);		
	
	public static int inputTijdSpanne(Tijdspanne tijdSpanne, int taakID) throws MalformedURLException, IOException, WebserviceException, JSONException {
		tijdSpanne.setID(tijdspannesId++);	
		wr.schrijfTijdspannne(tijdSpanne,taakID);
		return tijdspannesId;
	}

	public static int inputProject( Project project, int opdrachtGeverId) throws MalformedURLException, IOException, WebserviceException, JSONException {
        project.setId(projectId++);
        wr.schrijfProject(project,opdrachtGeverId);
        return projectId;
    }

	public static int inputOpdrachtgever(Opdrachtgever opdrachtgever) throws JSONException, MalformedURLException, IOException, WebserviceException {            
		opdrachtgever.setID(opdrachtgeverId++);
		wr.schrijfOpdrachtgever(opdrachtgever);
		return opdrachtgeverId;
	}

	public static int inputTaak(Taak taak, int projectID) throws MalformedURLException, IOException, WebserviceException, JSONException {
		taak.setId(taakId++);
		wr.schrijfTaak(taak, projectID);
		return taakId;
    }
}
