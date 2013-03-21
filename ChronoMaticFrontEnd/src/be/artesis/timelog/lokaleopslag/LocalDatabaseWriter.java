package be.artesis.timelog.lokaleopslag;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.view.*;

public class LocalDatabaseWriter {
		private static final String URL = "c:\\Users\\Yolan\\Desktop\\";
		private static final String OPDRACHTGEVER = "opdrachtgever"; 
		private static final String PROJECT = "project";
		private static final String TAAK = "taak";
		private static final String TIJDSPANNE = "tijdspanne";
		
		
		private String filePath;
		
		public LocalDatabaseWriter(Commando c){
			filePath = URL + c + ".txt";
			
		}
		
			
		public void schrijfProject(Project project) throws JSONException, IOException{	
			
			System.out.println(LocalDatabaseReader.LeesBestand(filePath));
			if(LocalDatabaseReader.LeesBestand(filePath) == null){
				
				makeDatabase();
			}
			
			
			JSONObject jObj = LocalDatabaseReader.LeesBestand(filePath);
			
			JSONObject jProject = new JSONObject();
			
			jProject.put("naam", project.getNaam());
			jProject.put("beginDatum", project.getBegindatum());
			jProject.put("eindDatum", project.getEinddatum());
			
			JSONArray jArr = (JSONArray) jObj.get(PROJECT);
			jArr.put(jProject);
			
			jObj.put(PROJECT, jArr);
			
			schrijfweg(jProject);
		
		}
		public void schrijfTaak(Taak taak) throws JSONException, IOException{
			
			JSONObject jTaak = new JSONObject();
			jTaak.put("naam", taak.getNaam());
			jTaak.put("beginDatum", taak.getBegindatum());
			jTaak.put("eindDatum", taak.getGeschatteEinddatum());
			jTaak.put("commentaar", taak.getCommentaar());
			
			schrijfweg(jTaak);
			
			//JSONArray taakarray = new JSONArray();
			//taakarray.put(gegtaak);
		}
		public void schrijfTijdspannne(Tijdspanne tijd) throws JSONException, IOException{
			////
			
			JSONObject jTijd = new JSONObject();
			jTijd.put("beginTijd", tijd.getBeginTijd());
			jTijd.put("eindTijd", tijd.getEindTijd());
			
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
		
		private void makeDatabase() throws JSONException, IOException{
						
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
			
			System.out.println(collection);
		}

	
}










