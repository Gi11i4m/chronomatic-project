package be.artesis.timelog.ics;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javafx.beans.property.Property;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import be.artesis.timelog.view.Taak;

public class IcsImporteren {
	
	public static Taak[] importeren(String url) throws IOException, ParserException{
		//opsplitsen in:
		//lezen van ICS bestand 
		//taak object maken van events
		//taken in array   doorgeven aan aanroeper
		
		Calendar calendar = inlezen(url);
		
		//System.out.println(calendar);
									
		return genereerArray(calendar);
		
	}
	private static Calendar inlezen(String url) throws IOException, ParserException{
		
		FileInputStream fin = new FileInputStream(url);

		CalendarBuilder builder = new CalendarBuilder();
		return builder.build(fin);
	}
	
	private static Taak[] genereerArray(Calendar calendar){
		
		
		
		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
		    //Component component = (Component) i.next();
		    VEvent vevent = (VEvent) i.next();
		    //System.out.println("DERPIEComponent [" + vevent.getName() + "]");
		    //vevent.getProperties().get
		
		    
		    
		    
		    
		    /*for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
		        Property property = (Property) j.next();
		        System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
		    }*/
		}
		return null;
	}
	
	private static Taak maakTaak(VEvent vTaak){
		
		return null;
	}

}
