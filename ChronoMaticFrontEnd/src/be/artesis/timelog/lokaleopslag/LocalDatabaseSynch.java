package be.artesis.timelog.lokaleopslag;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.controller.InserterServer;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;


public class LocalDatabaseSynch {

	private JSONArray jarrProjecten;
	private JSONArray jarrOpdrachtgevers;
	private JSONArray jarrTaken;
	private JSONArray jarrTijdspannes;
	
	private Validator v;
	
	
	//deze klasse heeft maar 1 methode "synch" en dat is alle shizzle die je opgeslagen hebt via de local ins/upd/del in een txt,
	//opsturen via de server ins/upd/del naar de server
	
	public LocalDatabaseSynch(Validator v){
		jarrProjecten = null;
		jarrOpdrachtgevers = null;
		jarrTaken = null;		
		jarrTijdspannes = null;
		this.v = v;
	}
	
	public  void synch(Commando c) throws JSONException, IOException, WebserviceException{
		JSONObject file = LocalDatabaseReader.LeesBestand( LocalDatabaseWriter.URL+ c +".txt");
		
		jarrOpdrachtgevers = file.getJSONArray(JVelden.OPDRACHTGEVERS.toString().toLowerCase());
		jarrProjecten =  file.getJSONArray(JVelden.PROJECTEN.toString().toLowerCase());		
		jarrTaken = file.getJSONArray(JVelden.TAKEN.toString().toLowerCase());
		jarrTijdspannes = file.getJSONArray(JVelden.TIJDSPANNES.toString().toLowerCase());
		
		if (!jarrOpdrachtgevers.isNull(0)){synchJarr(JVelden.OPDRACHTGEVERS, jarrOpdrachtgevers, jarrProjecten);}
		if (!jarrProjecten.isNull(0)){synchJarr(JVelden.PROJECTEN,jarrProjecten,jarrTaken);}
		if (!jarrTaken.isNull(0)){synchJarr(JVelden.TAKEN, jarrTaken, jarrTijdspannes);}
		if (!jarrTijdspannes.isNull(0)){synchJarr(JVelden.TIJDSPANNES, jarrTijdspannes, null);}
			
		
	}
	
	private void synchJarr(JVelden jVeld, JSONArray jarr, JSONArray jarrLager) throws MalformedURLException, JSONException, IOException, WebserviceException{
		System.out.println("jarrlength:" + jarr.length());
		for(int i = 0 ; i < jarr.length();i++){	
			System.out.println("i=" + i);
			
			JSONObject jObj =  jarr.getJSONObject(i);
			int fakeId = jObj.getInt("id");
			int realId = getId(jVeld,jObj);
			System.out.println("id= "+realId);
			System.out.println();
			if(jarrLager != null){
				for (int j = 0; j < jarrLager.length(); j++) {
					
					JSONObject jProject =  (JSONObject) jarrLager.get(j);
					if(jProject.getInt("linkId") == fakeId){
						jProject.put("linkId", realId);
					}
				}
			}
		}
	}
	
	private int getId(JVelden jVeld,JSONObject jObj) throws MalformedURLException, JSONException, IOException, WebserviceException{
	
		switch (jVeld) {
		case OPDRACHTGEVERS:
			return synchOpdrachtgever(jObj);
		case TAKEN:
			return synchTaak(jObj);
		case PROJECTEN:
			return synchProject(jObj);	
		case TIJDSPANNES:
			return synchTijdspanne(jObj);
		}
		return 0;
	}
	
	private  int synchOpdrachtgever(JSONObject jOpdrachtgever) throws MalformedURLException, JSONException, IOException, WebserviceException{
		return InserterServer.inputOpdrachtgever(v.getSessionKey(), jOpdrachtgever.getString("bedrijfsnaam"), jOpdrachtgever.getString("naam"), jOpdrachtgever.getString("voornaam"), jOpdrachtgever.getString("email"), jOpdrachtgever.getString("telefoonnummer"));
	}	
	
	private  int synchProject(JSONObject jProject) throws MalformedURLException, IOException, WebserviceException, JSONException{
		return InserterServer.inputProject(v.getSessionKey(), jProject.getString("naam"), jProject.getLong("beginDatum"), jProject.getLong("eindDatum"), jProject.getInt("linkId"));
	}
	
	private  int synchTaak(JSONObject jTaak) throws MalformedURLException, IOException, WebserviceException, JSONException {
		return InserterServer.inputTaak(v.getSessionKey(), jTaak.getString("naam"), jTaak.getLong("beginDatum"), jTaak.getLong("eindDatum"), jTaak.getString("commentaar"), jTaak.getBoolean("completed"), jTaak.getInt("linkId"));
	}
	private  int synchTijdspanne(JSONObject jTijdspanne) throws MalformedURLException, IOException, WebserviceException, JSONException {
		return InserterServer.inputTijdSpanne(v.getSessionKey(), jTijdspanne.getLong("beginTijd"), jTijdspanne.getLong("eindTijd"), jTijdspanne.getBoolean("isPauze"), jTijdspanne.getInt("linkId"));
	}

}
