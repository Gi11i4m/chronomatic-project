package be.artesis.timelog.lokaleopslag;

import java.io.IOException;

import org.json.JSONException;

import be.artesis.timelog.view.Opdrachtgever;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		try {
			Opdrachtgever opdrachtgever = new Opdrachtgever("Possy","phil","Artesis","possy@artisis.awesome","123456789",12);
			LocalDatabaseWriter.schrijfOpdrachtgever(opdrachtgever);
			
			LocalDatabaseReader.LeesBestand("c:\\Users\\Yolan\\Desktop\\lokaal.txt");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			LocalDatabaseWriter lbcw = new LocalDatabaseWriter(Commando.DELETE);
			
			Opdrachtgever opdrachtgever = new Opdrachtgever("Possy","phil","Artesis","possy@artisis.awesome","123456789",12);
			
			lbcw.schrijfOpdrachtgever(opdrachtgever);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
