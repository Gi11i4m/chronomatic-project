package be.artesis.ics;

import java.io.FileInputStream;
import java.io.IOException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import be.artesis.timelog.view.Taak;

public class IcsExporteren {
	
	public static Taak icsLezen() throws IOException, ParserException{
		//opsplitsen in:
		//lezen van ICS bestand 
		//taak object maken van events
		//taken in array of arraylist  doorgeven aan aanroeper
		
		FileInputStream fin = new FileInputStream("mycalendar.ics");

		CalendarBuilder builder = new CalendarBuilder();
		Calendar calendar = builder.build(fin);
		
		System.out.println(calendar);
		
		new Taak();
		
		return null;
		
	}
	private void inlezen(){
		
		
	}

}
