package be.artesis.timelog.lokaleopslag;

import java.io.IOException;

import org.json.JSONException;

import be.artesis.timelog.model.Validator;
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

		LocalDatabaseWriter wr = new LocalDatabaseWriter(Commando.INSERT);
		try {
			wr.makeDatabase();
			
			LocalDatabaseWriter lbcw = new LocalDatabaseWriter(Commando.INSERT);
			
			Opdrachtgever opdrachtgever = new Opdrachtgever("Possy","phil","Artesis","possy@artisis.awesome","123456789",12);
			Opdrachtgever opdrachtgever1 = new Opdrachtgever("Pssy","ph","Artsi","possy@artisis.awesome","123456789",1);
			Opdrachtgever opdrachtgever2= new Opdrachtgever("Papa","piemek","Aap","awesome","",2);						
			
			lbcw.schrijfOpdrachtgever(opdrachtgever);
			lbcw.schrijfOpdrachtgever(opdrachtgever1);
			lbcw.schrijfOpdrachtgever(opdrachtgever2);
			
			Validator v = Validator.getInstance();
			
			
			LocalDatabaseSynch
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
