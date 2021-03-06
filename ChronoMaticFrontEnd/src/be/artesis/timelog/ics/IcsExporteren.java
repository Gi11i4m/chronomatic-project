package be.artesis.timelog.ics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.GregorianCalendar;

import be.artesis.timelog.controle.DataControle;
import be.artesis.timelog.view.Taak;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

public class IcsExporteren {
	//Het exporteren van een array van taken naar een ICS bestand
	private static Calendar cal;
	
	public static void export(Taak[] taken, String url) throws IOException, ValidationException{		
		//opdelen in:
		// agenda/callender maken
		// event smaken	 
		
		maakCalendar();
		
		for(Taak taak : taken){			
			cal.getComponents().add(maakVEvent(taak));
		}			
		
		url = DataControle.icsPathCorrect(url);
		if (url == null)
			throw new ValidationException("Pad naar bestand is incorrect");
		
		schrijfWeg(cal, url);		
	}	
	private static void maakCalendar(){
		// initialise as an all-day event..
		
		cal = new net.fortuna.ical4j.model.Calendar();
		cal.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));

		cal.getProperties().add(Version.VERSION_2_0);

		cal.getProperties().add(CalScale.GREGORIAN);
		
	}
	private static VEvent maakVEvent(Taak taak) throws SocketException{
		

		//Timestamp tBegin = new Timestamp(taak.getBegindatum() * 1000);		
		java.util.Calendar cBegin = GregorianCalendar.getInstance();
		cBegin.setTimeInMillis((taak.getBegindatum() * 1000)- 21600000);
		
		//Timestamp tEind = new Timestamp(taak.getGeschatteEinddatum() * 1000);
		java.util.Calendar cEind= GregorianCalendar.getInstance();
		cEind.setTimeInMillis((taak.getGeschatteEinddatum() * 1000)- 21600000);		 
	
		DateTime dBegin = new DateTime(cBegin.getTime()); 
		DateTime dEind = new DateTime(cEind.getTime()); 
		
		VEvent event = new VEvent(dBegin, dEind, taak.getNaam());
		
		event.getProperties().add(new Description(taak.getCommentaar()));
		// Generate a UID for the event..
		UidGenerator ug = new UidGenerator("1");
		event.getProperties().add(ug.generateUid());
		
		
		return event;
	}
	
	private static void schrijfWeg(Calendar cal, String url) throws IOException, ValidationException{
		// agenda/calendar wegschrijven
		FileOutputStream fout = new FileOutputStream(url);
		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(cal, fout);
		fout.flush();
		fout.close();
	}
}
