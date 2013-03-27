package be.artesis.timelog.controller;

import be.artesis.timelog.lokaleopslag.Commando;
import be.artesis.timelog.lokaleopslag.JVelden;
import be.artesis.timelog.lokaleopslag.LocalDatabaseReader;
import be.artesis.timelog.lokaleopslag.LocalDatabaseWriter;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleterLocaal {
	
	public static void deleteTijdSpanne( Tijdspanne tijdSpanne) throws MalformedURLException, IOException, WebserviceException, JSONException {
		LocalDatabaseWriter.schrijfweg(voegToe(tijdSpanne.getId(), JVelden.TIJDSPANNES), Commando.DELETE);
	}
	
	public static void deleteProject( Project project) throws MalformedURLException, IOException, WebserviceException, JSONException {
		LocalDatabaseWriter.schrijfweg(voegToe(project.getId(),JVelden.PROJECTEN),Commando.DELETE);
	}

	public static void deleteOpdrachtgever( Opdrachtgever opdrachtgever) throws MalformedURLException, IOException, WebserviceException, JSONException {
		LocalDatabaseWriter.schrijfweg(voegToe(opdrachtgever.getID(),JVelden.OPDRACHTGEVERS),Commando.DELETE);
	}

	public static void deleteTaak(Taak taak) throws MalformedURLException, IOException, WebserviceException, JSONException {
		LocalDatabaseWriter.schrijfweg(voegToe(taak.getId(),JVelden.TAKEN),Commando.DELETE);
	}	
	private static  JSONObject voegToe(int id,JVelden veld) throws JSONException, IOException{
		
		JSONObject jObj = LocalDatabaseReader.LeesBestand(LocalDatabaseWriter.URL + Commando.DELETE + LocalDatabaseWriter.EXTENSIE);			
		if (jObj == null){
			jObj = LocalDatabaseWriter.makeDatabase(Commando.DELETE);
			
		}
		
		JSONArray jArr = (JSONArray) jObj.get(veld.toString().toLowerCase());
		JSONObject jId = new JSONObject();
		jObj.put("id", id);
		jArr.put(jId);
		
		jObj.put(veld.toString().toLowerCase(), jArr);
		
		return jObj;					
	}

}
