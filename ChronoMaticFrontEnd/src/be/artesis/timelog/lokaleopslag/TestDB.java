package be.artesis.timelog.lokaleopslag;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;

import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		//LocalDatabaseWriter wr = new LocalDatabaseWriter(Commando.INSERT);
		try {
			//wr.makeDatabase();
			
			//LocalDatabaseWriter lbcw = new LocalDatabaseWriter(Commando.INSERT);
			/*
			 //opdrachtgevers
			Opdrachtgever opdrachtgever = new Opdrachtgever("Possy","phil","1","possy@artisis.awesome","123456789",12);
			Opdrachtgever opdrachtgever1 = new Opdrachtgever("Psy","ph","2","possy@artisis.awesome","123456789",1);
			Opdrachtgever opdrachtgever2= new Opdrachtgever("Papa","piemek","3","awesome","",2);
			Opdrachtgever opdrachtgever3= new Opdrachtgever("stijn","de piet","4","nomo","",3);	
			
			lbcw.schrijfOpdrachtgever(opdrachtgever);
			lbcw.schrijfOpdrachtgever(opdrachtgever1);
			lbcw.schrijfOpdrachtgever(opdrachtgever2);
			lbcw.schrijfOpdrachtgever(opdrachtgever3);
			*/
			
			/*
			//tijdspannes
			Tijdspanne t0 = new Tijdspanne(2,5,1,false);
			Tijdspanne t1 = new Tijdspanne(20,50,1,true);
			Tijdspanne t2 = new Tijdspanne(1000,525,2,false);
			
			lbcw .schrijfTijdspannne(t0,1);
			lbcw .schrijfTijdspannne(t1,2);
			lbcw .schrijfTijdspannne(t2,1);
			*/
			
			/*
			//taak
			Taak t0 = new Taak("frits",1,1,"azerty");
			Taak t1 = new Taak("freddy",2,3,"qwerty");
			Taak t3 = new Taak("fons",10,20,"olapola");
			Taak t2 = new Taak("fuckface",666,13,"t");
			
			lbcw.schrijfTaak(t0, 1);
			lbcw.schrijfTaak(t1, 2);
			lbcw.schrijfTaak(t2, 1);
			lbcw.schrijfTaak(t3, 3);
			
			
			*/
			/*
			//projecten
			Project p0 = new Project("javaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",1,10,20,3);
			Project p1 = new Project("C#",100,1000,2000,4);
			Project p2 = new Project("fritselshnizel",1,10,20,3);
			Project p3 = new Project("poepsnoepje",1,10,20,3);
			Project p4 = new Project("nee",1,10,20,3);
			
			lbcw.schrijfProject(p0, 1);
			lbcw.schrijfProject(p1, 2);
			lbcw.schrijfProject(p2, 2);
			lbcw.schrijfProject(p3, 1);
			lbcw.schrijfProject(p4, 1);
			
			*/
			Validator v = Validator.getInstance();
			v.login("p", "p");
			
			LocalDatabaseSynch lds = new LocalDatabaseSynch(v);
			lds.synch(Commando.INSERT);
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebserviceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
