package be.artesis.timelog.lokaleopslag;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.view.*;

public class LocalDatabaseWriter {

	/*public static void addProject(Project p) throws JSONException {
		
		JSONArray array = new JSONArray();
		array.put("f");
		
		//System.out.println(array.);
		
		JSONObject object = new JSONObject();
		object.put("ff", "piet");
		//object.
		//System.out.println(object.get("ff"));
	}*/
	
	/**
	 * @param args
	 * @throws JSONException
	 */
	
		
		
		public void schrijfProject(Project project){
			JSONObject soorten = new JSONObject();
	
			JSONObject gegproject = new JSONObject();
			
			gegproject.put("naam", project.getNaam());
			gegproject.put("beginDatumPro", project.getBegindatum());
			gegproject.put("eindDatumPro", project.getEinddatum());
			
			//////////
		}
		public void schrijfTaak(Taak taak){
			
			JSONObject gegtaak = new JSONObject();
			gegtaak.put("naam", taak.getNaam());
			gegtaak.put("beginDatum", taak.getBegindatum());
			gegtaak.put("eindDatum", taak.getGeschatteEinddatum());
			gegtaak.put("commentaar", taak.getCommentaar());
			
			JSONArray taakarray = new JSONArray();
			taakarray.put(gegtaak);
		}
		public void schrijfTijdspannne(Tijdspanne tijd){
			////
			
			JSONObject gegtijd = new JSONObject();
			gegtijd.put("beginTijd", tijd.getBeginTijd());
			gegtijd.put("eindTijd", tijd.getEindTijd());
			
			JSONArray tijdarray = new JSONArray();
			tijdarray.put(gegtijd);
											
		}
		
			
		private void schrijfweg(){
			
			gegtaak.put("tijdspannes", tijdarray);
			
			gegproject.put("taken", taakarray);
			
			soorten.put("project",gegproject);
	
			System.out.println(soorten);
		}
			/*try {
		 
				FileWriter file = new FileWriter("c:\\test.json");
				file.write(list.toString());
				file.flush();
				file.close();
		 
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		
	 
	     
	
	
}