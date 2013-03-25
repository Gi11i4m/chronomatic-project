package be.artesis.timelog.lokaleopslag;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.view.*;

public class LocalDatabaseWriter {
	//hier aant troubleshooten. Het opslaan en opvragen van shit van begin tot eind door de lokale db
	
		public static final String URL = "c:\\Users\\Yolan\\Desktop\\";
		//public static final String OPDRACHTGEVER = "opdrachtgever"; 
		//public static final String PROJECT = JVelden.PROJECTEN.toString().toLowerCase();
		//public static final String TAAK = JVelden.TAKEN.toString().toLowerCase();
		//public static final String TIJDSPANNE = JVelden.TIJDSPANNES.toString().toLowerCase();		
		
		private String filePath;
				
		public LocalDatabaseWriter(Commando c){
			filePath = URL + c + ".txt";
			
		}
					
		public void schrijfProject(Project project, int opdrachtgeverId) throws JSONException, IOException{	
			
			//System.out.println(LocalDatabaseReader.LeesBestand(filePath));
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			
			JSONObject jObj = LocalDatabaseReader.LeesBestand(filePath);
			
			JSONObject jProject = new JSONObject();
			
			jProject.put("id", project.getId());
			jProject.put("naam", project.getNaam());
			jProject.put("beginDatum", project.getBegindatum());
			jProject.put("eindDatum", project.getEinddatum());
			jProject.put("linkId",opdrachtgeverId);
			
			JSONArray jArr = (JSONArray) jObj.get(JVelden.PROJECTEN.toString().toLowerCase());
			jArr.put(jProject);
			
			jObj.put(JVelden.PROJECTEN.toString().toLowerCase(), jArr);
			
			schrijfweg(voegToe(jProject,JVelden.PROJECTEN));
		
		}
		public void schrijfTaak(Taak taak, int projectID) throws JSONException, IOException{
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			JSONObject jTaak = new JSONObject();
			
			jTaak.put("id", taak.getId());
			jTaak.put("naam", taak.getNaam());
			jTaak.put("beginDatum", taak.getBegindatum());
			jTaak.put("eindDatum", taak.getGeschatteEinddatum());
			jTaak.put("commentaar", taak.getCommentaar());
			jTaak.put("completed", taak.getCompleted());
			jTaak.put("linkId", projectID);
			
			schrijfweg(voegToe(jTaak,JVelden.TAKEN));
			
			//JSONArray taakarray = new JSONArray();
			//taakarray.put(gegtaak);
		}
		public void schrijfTijdspannne(Tijdspanne tijd, int taakid) throws JSONException, IOException{
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			JSONObject jTijd = new JSONObject();
			
			jTijd.put("id", tijd.getId());
			jTijd.put("beginTijd", tijd.getBeginTijd());
			jTijd.put("eindTijd", tijd.getEindTijd());
			jTijd.put("isPauze", tijd.isPauze());
			jTijd.put("linkId", taakid);
			
			schrijfweg(voegToe(jTijd,JVelden.TIJDSPANNES));
			
			//JSONArray tijdarray = new JSONArray();
			//tijdarray.put(gegtijd);
											
		}
		
		public void schrijfOpdrachtgever(Opdrachtgever opdrachtgever) throws JSONException, IOException {
			//System.out.println(LocalDatabaseReader.LeesBestand(filePath));
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			JSONObject jOpdrachtgever = new JSONObject();
			
			jOpdrachtgever.put("id", opdrachtgever.getID());
			jOpdrachtgever.put("bedrijfsnaam", opdrachtgever.getBedrijfsnaam());
			jOpdrachtgever.put("voornaam", opdrachtgever.getVoornaam());
			jOpdrachtgever.put("naam", opdrachtgever.getNaam());
			jOpdrachtgever.put("email", opdrachtgever.getEmail());						
			jOpdrachtgever.put("telefoonnummer", opdrachtgever.getTelefoonnummer());
						
			
			schrijfweg(voegToe(jOpdrachtgever,JVelden.OPDRACHTGEVERS));
		}
		private JSONObject voegToe(JSONObject jInsert,JVelden veld) throws JSONException, IOException{
			
			JSONObject jObj = LocalDatabaseReader.LeesBestand(filePath);
			
			JSONArray jArr = (JSONArray) jObj.get(veld.toString().toLowerCase());
			jArr.put(jInsert);
			
			jObj.put(veld.toString().toLowerCase(), jArr);
			
			return jObj;					
		}
		
			
		private void schrijfweg(JSONObject j) throws IOException{							
		 
			FileWriter file = new FileWriter(filePath);
			file.write(j.toString());
			file.flush();
			file.close();
		 			
		}
		
		public void makeDatabase() throws JSONException, IOException{
						
			JSONArray jarOpdrachtgevers = new JSONArray();
			JSONArray jarProjecten = new JSONArray();
			JSONArray jarTijdspannes = new JSONArray();
			JSONArray jarTaken = new JSONArray();
			
			//JSONObject jOpdrachtgevers = new JSONObject();
			//JSONObject jProjecten = new JSONObject();
			//JSONObject jTijdspannes = new JSONObject();
			//JSONObject jTaken = new JSONObject();
			
			
			
			JSONObject collection = new JSONObject();
			collection.put(JVelden.OPDRACHTGEVERS.toString().toLowerCase() ,jarOpdrachtgevers );
			collection.put(JVelden.PROJECTEN.toString().toLowerCase() , jarProjecten );
			collection.put(JVelden.TAKEN.toString().toLowerCase(),jarTaken );
			collection.put(JVelden.TIJDSPANNES.toString().toLowerCase(),jarTijdspannes );
			
			schrijfweg(collection);
			
			//System.out.println(collection);
		}

	
}










