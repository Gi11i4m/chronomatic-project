package be.artesis.timelog.controller;

import be.artesis.timelog.lokaleopslag.Commando;
import be.artesis.timelog.lokaleopslag.JVelden;
import be.artesis.timelog.lokaleopslag.LocalDatabaseReader;
import be.artesis.timelog.lokaleopslag.LocalDatabaseWriter;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.*;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.*;

import be.artesis.timelog.converter.*;

public class InserterLocal {
	//ID's genereren????
	
	private static int projectId = 0;
	private static int opdrachtgeverId = 0;
	private static int taakId = 0;
	private static int tijdspannesId = 0;				
	
	public  static int inputTijdSpanne(Tijdspanne tijdSpanne, int taakId) throws MalformedURLException, IOException, WebserviceException, JSONException {
		tijdSpanne.setID(++tijdspannesId);	
		
		JSONObject jTijd = Converter.converteer(tijdSpanne,taakId);
		
		LocalDatabaseWriter.schrijfweg(voegToe(jTijd,JVelden.TIJDSPANNES),Commando.INSERT);
		
		return tijdspannesId;
	}

	public static int inputProject( Project project, int opdrachtGeverId) throws MalformedURLException, IOException, WebserviceException, JSONException {
        project.setId(++projectId);                
        
        JSONObject jProject = Converter.converteer(project);        	
		LocalDatabaseWriter.schrijfweg(voegToe(jProject,JVelden.PROJECTEN), Commando.INSERT);	
		
        return projectId;
    }

	public static int inputOpdrachtgever(Opdrachtgever opdrachtgever) throws JSONException, MalformedURLException, IOException, WebserviceException {            
		opdrachtgever.setID(++opdrachtgeverId);
		
		JSONObject jOpdrachtgever = Converter.converteer(opdrachtgever);
					
		
		LocalDatabaseWriter.schrijfweg(voegToe(jOpdrachtgever,JVelden.OPDRACHTGEVERS), Commando.INSERT);
		return opdrachtgeverId;
	}

	public static int inputTaak(Taak taak, int projectID) throws MalformedURLException, IOException, WebserviceException, JSONException {
		taak.setId(++taakId);							
		JSONObject jTaak = Converter.converteer(taak, projectID);		
		LocalDatabaseWriter.schrijfweg(voegToe(jTaak,JVelden.TAKEN),Commando.INSERT);
					
		return taakId;
    }
	
	private static  JSONObject voegToe(JSONObject jInsert,JVelden veld) throws JSONException, IOException{
		
		JSONObject jObj = LocalDatabaseReader.LeesBestand(LocalDatabaseWriter.URL + Commando.INSERT + LocalDatabaseWriter.EXTENSIE);			
		if (jObj == null){
			jObj = LocalDatabaseWriter.makeDatabase(Commando.INSERT);
			
		}
		JSONArray jArr = (JSONArray) jObj.get(veld.toString().toLowerCase());
		jArr.put(jInsert);
		
		jObj.put(veld.toString().toLowerCase(), jArr);
		
		return jObj;					
	}
	

	
}
