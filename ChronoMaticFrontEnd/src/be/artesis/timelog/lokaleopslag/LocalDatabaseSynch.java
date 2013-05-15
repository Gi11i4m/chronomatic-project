package be.artesis.timelog.lokaleopslag;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.controller.DeleterServer;
import be.artesis.timelog.controller.InserterServer;
import be.artesis.timelog.controller.UpdaterServer;
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
	
	public void synch() throws JSONException, IOException, WebserviceException{
		
		for ( Commando c :Commando.values()){
			synchCommando(c);
		}
		
	}
	private void synchCommando(Commando c) throws JSONException, IOException, WebserviceException{		
		
		JSONObject file = LocalDatabaseReader.LeesBestand( LocalDatabaseWriter.URL+ c +".txt");
		if (file!=null){
			jarrOpdrachtgevers = file.getJSONArray(JVelden.OPDRACHTGEVERS.toString().toLowerCase());
			jarrProjecten =  file.getJSONArray(JVelden.PROJECTEN.toString().toLowerCase());		
			jarrTaken = file.getJSONArray(JVelden.TAKEN.toString().toLowerCase());
			jarrTijdspannes = file.getJSONArray(JVelden.TIJDSPANNES.toString().toLowerCase());
			if (c == Commando.INSERT){
				if (!jarrOpdrachtgevers.isNull(0)){insertJarr(JVelden.OPDRACHTGEVERS, jarrOpdrachtgevers, jarrProjecten);}
				if (!jarrProjecten.isNull(0)){insertJarr(JVelden.PROJECTEN,jarrProjecten,jarrTaken);}
				if (!jarrTaken.isNull(0)){insertJarr(JVelden.TAKEN, jarrTaken, jarrTijdspannes);}
				if (!jarrTijdspannes.isNull(0)){insertJarr(JVelden.TIJDSPANNES, jarrTijdspannes, null);}
			}else if(c== Commando.UPDATE){
				if (!jarrOpdrachtgevers.isNull(0)){updateJarr(JVelden.OPDRACHTGEVERS, jarrOpdrachtgevers);}
				if (!jarrProjecten.isNull(0)){updateJarr(JVelden.PROJECTEN, jarrProjecten);}
				if (!jarrTaken.isNull(0)){updateJarr(JVelden.TAKEN, jarrTaken);}
				if (!jarrTijdspannes.isNull(0)){updateJarr(JVelden.TIJDSPANNES, jarrTijdspannes);}
			}else if (c == Commando.DELETE) {
				if (!jarrOpdrachtgevers.isNull(0)){deleteJarr(JVelden.OPDRACHTGEVERS, jarrOpdrachtgevers);}
				if (!jarrProjecten.isNull(0)){deleteJarr(JVelden.PROJECTEN, jarrProjecten);}
				if (!jarrTaken.isNull(0)){deleteJarr(JVelden.TAKEN, jarrTaken);}
				if (!jarrTijdspannes.isNull(0)){deleteJarr(JVelden.TIJDSPANNES, jarrTijdspannes);}
			}
		}
		File f = new File(LocalDatabaseWriter.URL+ c +".txt");
		f.delete();
	}
	
	private void insertJarr(JVelden jVeld, JSONArray jarr, JSONArray jarrLager) throws MalformedURLException, JSONException, IOException, WebserviceException{
		
		for(int i = 0 ; i < jarr.length();i++){	
			//System.out.println("i=" + i);
			
			JSONObject jObj =  jarr.getJSONObject(i);
			int fakeId = jObj.getInt("id");
			int realId = getId(jVeld,jObj);
			//System.out.println("id= "+realId);
			//System.out.println();
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
	
	public void updateJarr(JVelden jVeld,JSONArray jarr) throws JSONException, MalformedURLException, IOException, WebserviceException{
		
		for(int i = 0 ; i < jarr.length();i++){				
			
			JSONObject jObj =  jarr.getJSONObject(i);
			update(jVeld, jObj);
		}
	}
	public void deleteJarr(JVelden jVeld,JSONArray jarr) throws JSONException, MalformedURLException, IOException, WebserviceException{
		
		for(int i = 0 ; i < jarr.length();i++){				
			
			JSONObject jObj =  jarr.getJSONObject(i);
			delete(jVeld, jObj.getInt("id"));
		}
	}
	
	private int getId(JVelden jVeld,JSONObject jObj) throws MalformedURLException, JSONException, IOException, WebserviceException{
	
		switch (jVeld) {
		case OPDRACHTGEVERS:
			return insertOpdrachtgever(jObj);
		case TAKEN:
			return insertTaak(jObj);
		case PROJECTEN:
			return insertProject(jObj);	
		case TIJDSPANNES:
			return insertTijdspanne(jObj);
		}
		return 0;
	}
	private void update(JVelden jVeld,JSONObject jObj) throws MalformedURLException, JSONException, IOException, WebserviceException{
		
		switch (jVeld) {
		case OPDRACHTGEVERS:
			updateOpdrachtgever(jObj);
			break;
		case TAKEN:
			updateTaak(jObj);
			break;
		case PROJECTEN:
			updateProject(jObj);
			break;
		case TIJDSPANNES:
			updateTijdspanne(jObj);
			break;
		}		
	}
	private void delete(JVelden jVeld,int id) throws MalformedURLException, JSONException, IOException, WebserviceException{
		
		switch (jVeld) {
		case OPDRACHTGEVERS:
			deleteOpdrachtgever(id);
			break;
		case TAKEN:
			deleteTaak(id);
			break;
		case PROJECTEN:
			deleteProject(id);
			break;
		case TIJDSPANNES:
			deleteTijdspanne(id);
			break;
		}		
	}
	
	private  int insertOpdrachtgever(JSONObject jOpdrachtgever) throws MalformedURLException, JSONException, IOException, WebserviceException{
		return InserterServer.inputOpdrachtgever(v.getSessionKey(), jOpdrachtgever.getString("bedrijfsnaam"), jOpdrachtgever.getString("naam"), jOpdrachtgever.getString("voornaam"), jOpdrachtgever.getString("email"), jOpdrachtgever.getString("telefoonnummer"));
	}	
	
	private  int insertProject(JSONObject jProject) throws MalformedURLException, IOException, WebserviceException, JSONException{
		return InserterServer.inputProject(v.getSessionKey(), jProject.getString("naam"), jProject.getLong("beginDatum"), jProject.getLong("eindDatum"), jProject.getInt("linkId"));
	}
	
	private  int insertTaak(JSONObject jTaak) throws MalformedURLException, IOException, WebserviceException, JSONException {
		return InserterServer.inputTaak(v.getSessionKey(), jTaak.getString("naam"), jTaak.getLong("beginDatum"), jTaak.getLong("eindDatum"), jTaak.getString("commentaar"), jTaak.getBoolean("completed"), jTaak.getInt("linkId"));
	}
	private  int insertTijdspanne(JSONObject jTijdspanne) throws MalformedURLException, IOException, WebserviceException, JSONException {
		return InserterServer.inputTijdSpanne(v.getSessionKey(), jTijdspanne.getLong("beginTijd"), jTijdspanne.getLong("eindTijd"), jTijdspanne.getBoolean("isPauze"), jTijdspanne.getInt("linkId"));
	}
	
	private  void updateOpdrachtgever(JSONObject jOpdrachtgever) throws MalformedURLException, JSONException, IOException, WebserviceException{
		UpdaterServer.updateOpdrachtgever(v.getSessionKey(),jOpdrachtgever.getInt("id") ,jOpdrachtgever.getString("bedrijfsnaam"), jOpdrachtgever.getString("naam"), jOpdrachtgever.getString("voornaam"), jOpdrachtgever.getString("email"), jOpdrachtgever.getString("telefoonnummer"));
	}	
	
	private  void updateProject(JSONObject jProject) throws MalformedURLException, IOException, WebserviceException, JSONException{
		UpdaterServer.updateProject(v.getSessionKey(), jProject.getInt("id"),jProject.getString("naam"), jProject.getLong("beginDatum"), jProject.getLong("eindDatum"), jProject.getInt("linkId"));
	}
	
	private  void updateTaak(JSONObject jTaak) throws MalformedURLException, IOException, WebserviceException, JSONException {
		UpdaterServer.updateTaak(v.getSessionKey(), jTaak.getInt("id"), jTaak.getString("naam"), jTaak.getLong("beginDatum"), jTaak.getLong("eindDatum"), jTaak.getString("commentaar"), jTaak.getBoolean("completed"), jTaak.getInt("linkId"));
	}
	private  void updateTijdspanne(JSONObject jTijdspanne) throws MalformedURLException, IOException, WebserviceException, JSONException {		
		UpdaterServer.updateTijdspanne(v.getSessionKey(), jTijdspanne.getInt("id"), jTijdspanne.getLong("beginTijd"), jTijdspanne.getLong("eindTijd"), jTijdspanne.getBoolean("isPauze"), jTijdspanne.getInt("linkId"));
	}
//
	private  void deleteOpdrachtgever(int id) throws MalformedURLException, JSONException, IOException, WebserviceException{
		DeleterServer.deleteOpdrachtgever(v.getSessionKey(),id);
	}	
	
	private  void deleteProject(int id) throws MalformedURLException, IOException, WebserviceException, JSONException{
		DeleterServer.deleteProject(v.getSessionKey(),id);
	}
	
	private  void deleteTaak(int id) throws MalformedURLException, IOException, WebserviceException, JSONException {
		DeleterServer.deleteTaak(v.getSessionKey(), id);
	}
	private  void deleteTijdspanne(int id) throws MalformedURLException, IOException, WebserviceException, JSONException {		
		DeleterServer.deleteTijdSpanne(v.getSessionKey(), id);
	}
	

}
