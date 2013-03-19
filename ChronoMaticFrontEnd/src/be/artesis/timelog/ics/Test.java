package be.artesis.timelog.ics;

import java.io.IOException;

import be.artesis.timelog.view.Taak;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ValidationException;

public abstract class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			
			Taak[] taken = new Taak[3];
			taken[0] = new Taak("gimliam",1363755600, 1364187600,"de archon");
			taken[1] = new Taak("stijn",1363669200,1364187600,"de derper");
			taken[2] = new Taak("Yolan",1364248800,1364256000,"het kieke ofte koe");
			
			IcsExporteren.export(taken, "C:\\Users\\Yolan\\Desktop\\mijnIcs.ics");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		try {
			
			Taak[] taken = IcsImporteren.importeren("C:\\Users\\Yolan\\Desktop\\mijnIcs.ics");
			
			for(Taak taak: taken){
				System.out.println(taak);
				
			}
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
