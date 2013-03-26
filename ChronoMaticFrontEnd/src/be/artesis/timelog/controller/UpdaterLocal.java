package be.artesis.timelog.controller;


import be.artesis.timelog.converter.Converter;
import be.artesis.timelog.lokaleopslag.Commando;
import be.artesis.timelog.lokaleopslag.JVelden;
import be.artesis.timelog.lokaleopslag.LocalDatabaseReader;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdaterLocal {	

	// FIXME klopt niet! 
	public static void updateGebruiker(String sessionKey, Gebruiker gebruiker) throws MalformedURLException, IOException, WebserviceException{
		Connection.execute("gebruiker/update/" + sessionKey + "/" + gebruiker.getNaam() + "/" + gebruiker.getVoornaam() + "/" + gebruiker.getGebruikersnaam() + "/" +gebruiker.getEmail());
	}
        
	public static void updateProject(Project project) throws MalformedURLException, IOException, WebserviceException, JSONException {
		
		checkUpdate( Converter.converteer(project), JVelden.PROJECTEN);
	}

	public static void updateOpdrachtgever( Opdrachtgever opdrachtgever) throws MalformedURLException, IOException, WebserviceException, JSONException{
		checkUpdate( Converter.converteer(opdrachtgever), JVelden.OPDRACHTGEVERS);
	}
	
	public static void updateTaak(Taak taak, int projectId) throws MalformedURLException, IOException, WebserviceException, JSONException {
		checkUpdate(Converter.converteer(taak,projectId), JVelden.TAKEN);
    }
	
	public static void updateTijdspanne(Tijdspanne tijdspanne,int taakId) throws IOException, WebserviceException, JSONException{		 
		checkUpdate(Converter.converteer(tijdspanne,taakId), JVelden.TIJDSPANNES);
	}
	private static void checkUpdate(JSONObject jTest, JVelden veld) throws JSONException, IOException{
			
		JSONObject jFile = LocalDatabaseReader.LeesBestand(LocalDatabaseWriter.URL + Commando.INSERT + LocalDatabaseWriter.EXTENSIE);
		boolean gevonden = false;
		if (jFile != null){				
		
			JSONArray jArr = jFile.getJSONArray(veld.toString().toLowerCase());
			
			for (int i = 0; i < jArr.length(); i++) {					
				JSONObject jObjCheck =  (JSONObject) jArr.get(i);
				if(jObjCheck.getInt("id")== jTest.getInt("id")){
					System.out.println("gevonden");
					jArr.put(i,jTest);
					jFile.remove(veld.toString().toLowerCase());
					jFile.put(veld.toString().toLowerCase(),jArr);					
					LocalDatabaseWriter.schrijfweg(jFile, Commando.INSERT);
					gevonden = true;
				}
			}
		}
		if (!gevonden){
			JSONObject jObj = LocalDatabaseReader.LeesBestand(LocalDatabaseWriter.URL + Commando.UPDATE + LocalDatabaseWriter.EXTENSIE);			
			if (jObj == null){
				jObj = LocalDatabaseWriter.makeDatabase(Commando.UPDATE);
				
			}
			JSONArray jArr2 = jObj.getJSONArray(veld.toString().toLowerCase());
			jArr2.put(jTest);
			LocalDatabaseWriter.schrijfweg(jObj.put(veld.toString().toLowerCase(), jArr2),Commando.UPDATE);
		}
		
	}
}