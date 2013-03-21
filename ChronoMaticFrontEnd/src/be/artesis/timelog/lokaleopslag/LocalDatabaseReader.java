package be.artesis.timelog.lokaleopslag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class LocalDatabaseReader {
	
	public static JSONObject LeesBestand(String url) throws JSONException, IOException{
				
		File f = new File(url);
		if (!f.exists()){
			return null;
		}
		BufferedReader br = new BufferedReader(new FileReader(url));
		
		String sCurrentLine;

		sCurrentLine = br.readLine();
		System.out.println(sCurrentLine);
					
		System.out.println();
		JSONObject jObj = new JSONObject(sCurrentLine);
		 
		
		return jObj;
	}
		
}
