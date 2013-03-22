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
		public static final String OPDRACHTGEVER = "opdrachtgever"; 
		public static final String PROJECT = JVelden.PROJECTEN.toString().toLowerCase();
		public static final String TAAK = JVelden.TAKEN.toString().toLowerCase();
		public static final String TIJDSPANNE = JVelden.TIJDSPANNES.toString().toLowerCase();		
		
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
			
			JSONArray jArr = (JSONArray) jObj.get(PROJECT);
			jArr.put(jProject);
			
			jObj.put(PROJECT, jArr);
			
			schrijfweg(jProject);
		
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
			
			schrijfweg(jTaak);
			
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
			jTijd.put("ispauze", tijd.isPauze());
			jTijd.put("linkId", taakid);
			
			schrijfweg(jTijd);
			
			//JSONArray tijdarray = new JSONArray();
			//tijdarray.put(gegtijd);
											
		}
		
		public void schrijfOpdrachtgever(Opdrachtgever opdrachtgever) throws JSONException, IOException {
			System.out.println(LocalDatabaseReader.LeesBestand(filePath));
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			JSONObject jOdrachtgever = new JSONObject();
			
			jOdrachtgever.put("id", opdrachtgever.getID());
			jOdrachtgever.put("bedrijfsnaam", opdrachtgever.getBedrijfsnaam());
			jOdrachtgever.put("voornaam", opdrachtgever.getVoornaam());
			jOdrachtgever.put("naam", opdrachtgever.getNaam());
			jOdrachtgever.put("email", opdrachtgever.getEmail());						
			jOdrachtgever.put("telefoonnummer", opdrachtgever.getTelefoonnummer());
			
			JSONObject jObj = LocalDatabaseReader.LeesBestand(filePath);
			
			JSONArray jArr = (JSONArray) jObj.get(OPDRACHTGEVER);
			jArr.put(jOdrachtgever);
			
			jObj.put(OPDRACHTGEVER, jArr);
			
			schrijfweg(jObj);
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
			collection.put(OPDRACHTGEVER ,jarOpdrachtgevers );
			collection.put(PROJECT , jarProjecten );
			collection.put(TAAK,jarTaken );
			collection.put(TIJDSPANNE,jarTijdspannes );
			
			schrijfweg(collection);
			
			//System.out.println(collection);
		}

	
}










