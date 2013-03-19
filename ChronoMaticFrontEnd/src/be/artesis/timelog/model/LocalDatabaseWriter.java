package be.artesis.timelog.model;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.view.*;

public class LocalDatabaseWriter {

	public static void addProject(Project p) throws JSONException {
		
		JSONArray array = new JSONArray();
		array.put("f");
		
		//System.out.println(array.);
		
		JSONObject object = new JSONObject();
		object.put("ff", "piet");
		//object.
		//System.out.println(object.get("ff"));
	}
	
	/**
	 * @param args
	 * @throws JSONException
	 */
	public static void main(String[] args) throws JSONException {
		 
		JSONObject bestand = new JSONObject();
	 
		JSONArray listInserts = new JSONArray();
		
		JSONArray project1 = new JSONArray();
		
		JSONArray gegevens = new JSONArray();
		
		JSONObject naam = new JSONObject();
		JSONObject gebruikersnaam = new JSONObject();
		
		naam.put("naam","stijn");
		gebruikersnaam.put("gebruikersnaam","depietos");

		gegevens.put(naam);
		gegevens.put(gebruikersnaam);
		
		project1.put(gegevens);
		
		listInserts.put(project1);
	 
		bestand.put("insert", listInserts);
		//obj.put("insert","ff");
	 
		/*try {
	 
			FileWriter file = new FileWriter("c:\\test.json");
			file.write(list.toString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	 
		System.out.print(bestand);
	 
	     }
	
	
}
