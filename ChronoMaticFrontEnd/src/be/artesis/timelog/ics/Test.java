package be.artesis.timelog.ics;

import java.io.IOException;
import java.util.ArrayList;

import be.artesis.timelog.view.Taak;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ValidationException;

public abstract class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("export: ");
			Taak[] taken = new Taak[1];
			taken[0] = new Taak("gimliam",1364261402, 1364358105,"de archon");
			//taken[1] = new Taak("stijn", 1364187600, 1364187600,"de derper");
			//taken[2] = new Taak("Yolan",1364259600, 1364266800,"het kieke ofte koe");
										
			//System.out.println(taken[0].getBegindatum());
			
			IcsExporteren.export(taken, "C:\\Users\\Yolan\\Desktop\\mijnIcs.ics");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			System.out.println("import: ");
			
			ArrayList<Taak> taken = IcsImporteren.importeren("C:\\Users\\Yolan\\Desktop\\mijnIcs.ics");
			System.out.println(taken);
			for(Taak taak: taken){
				System.out.println("begin datum:  " +taak.getBegindatum());
				System.out.println("eind datum: "+taak.getGeschatteEinddatum());
				System.out.println("naam: " + taak.getNaam());
				System.out.println("beschr: "+taak.getCommentaar());
				
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
