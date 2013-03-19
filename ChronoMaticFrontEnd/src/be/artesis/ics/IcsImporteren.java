package be.artesis.ics;

import java.io.FileOutputStream;
import java.io.IOException;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

public class IcsImporteren {
	//op delen in twee klasses een voor in een ander voor uit
	
	public static void icsMaken() throws IOException, ValidationException{		
		//opdelen in:
		// agenda/callender maken
		// event smaken	 
		// ag/cal wegschrijven
		
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		calendar.set(java.util.Calendar.DAY_OF_MONTH, 25);

		// initialise as an all-day event..
		VEvent christmas = new VEvent(new Date(calendar.getTime()), "Christmas Day");

		// Generate a UID for the event..
		UidGenerator ug = new UidGenerator("1");
		christmas.getProperties().add(ug.generateUid());

		net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
		cal.getComponents().add(christmas);
		cal.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));

		cal.getProperties().add(Version.VERSION_2_0);

		cal.getProperties().add(CalScale.GREGORIAN);
		System.out.println(cal);
		FileOutputStream fout = new FileOutputStream("mycalendar.ics");

		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(cal, fout);			
		
	}	
}
