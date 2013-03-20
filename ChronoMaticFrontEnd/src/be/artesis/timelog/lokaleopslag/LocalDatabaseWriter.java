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
	public static void main(String[] args) throws JSONException {
		
		Project p = new Project("PietProject",17,123456,321564);
		Taak t = new Taak("NegerTaak",123456,123654,"Commento");
		Tijdspanne tijd = new Tijdspanne(123,123);
		Tijdspanne tijd2 = new Tijdspanne(798,798);
		
		JSONObject soorten = new JSONObject();

		JSONObject gegproject = new JSONObject();
		
		gegproject.put("naam", p.getNaam());
		gegproject.put("beginDatumPro", p.getBegindatum());
		gegproject.put("eindDatumPro", p.getEinddatum());
		
		//////////
		
		JSONObject gegtaak = new JSONObject();
		gegtaak.put("naam", t.getNaam());
		gegtaak.put("beginDatum", t.getBegindatum());
		gegtaak.put("eindDatum", t.getGeschatteEinddatum());
		gegtaak.put("commentaar", t.getCommentaar());
		
		JSONArray taakarray = new JSONArray();
		taakarray.put(gegtaak);
		
		////
		
		JSONObject gegtijd = new JSONObject();
		gegtijd.put("beginTijd", tijd.getBeginTijd());
		gegtijd.put("eindTijd", tijd.getEindTijd());
		
		JSONArray tijdarray = new JSONArray();
		tijdarray.put(gegtijd);
		//
		gegtijd = new JSONObject();
		gegtijd.put("beginTijd", tijd2.getBeginTijd());
		gegtijd.put("eindTijd", tijd2.getEindTijd());
		
		tijdarray.put(gegtijd);
		
		////
		
		gegtaak.put("tijdspannes", tijdarray);
		
		gegproject.put("taken", taakarray);
		
		soorten.put("project",gegproject);

		System.out.println(soorten);
	 
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
