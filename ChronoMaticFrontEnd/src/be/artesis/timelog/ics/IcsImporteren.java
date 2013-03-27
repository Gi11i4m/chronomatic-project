package be.artesis.timelog.ics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;

public class IcsImporteren {
	
	public static ArrayList<Taak> importTasks(String url) throws IOException, ParserException{
		//opsplitsen in:
		//lezen van ICS bestand 
		//taak object maken van events
		//taken in array   doorgeven aan aanroeper
		
		Calendar calendar = inlezen(url);
		
		//System.out.println(calendar);
									
		return genereerArray(calendar);
		
	}
	
	public static ArrayList<Project> importTasksInProject(String url) throws IOException, ParserException{
				
		Project p = new Project("Imported tasks",0,0,0);
		Taak[] taken = new Taak[importTasks(url).size()];
		p.setTaken(importTasks(url).toArray(taken));
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(p);
		return projects;				
	}
	
	private static Calendar inlezen(String url) throws IOException, ParserException{
		
		FileInputStream fin = new FileInputStream(url);
			
		
		CalendarBuilder builder = new CalendarBuilder();
		return builder.build(fin);
	}
	
	private static ArrayList<Taak> genereerArray(Calendar calendar){
				
		ArrayList<Taak> taken = new ArrayList<Taak>();
		
		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
			
		    //Component component = (Component) i.next();
		    VEvent vevent = (VEvent) i.next();
		    taken.add( maakTaak(vevent));
		    
		    
		}
		return taken;
	}
	
	private static Taak maakTaak(VEvent vevent){
		
		DtStart dtBegin = vevent.getStartDate();
		DtEnd dtEind =  vevent.getEndDate();
		
		//System.out.println("start: " + dtBegin.getValue());
		//System.out.println("eind: "+dtEind.getValue());
	    
		
		java.util.Calendar cBegin= new GregorianCalendar();
		cBegin.set(java.util.Calendar.MONTH, Integer.parseInt(dtBegin.getValue().substring(4,6))-1);
		cBegin.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(dtBegin.getValue().substring(6,8)));
		cBegin.set(java.util.Calendar.YEAR, Integer.parseInt(dtBegin.getValue().substring(0, 4)));
		cBegin.set(java.util.Calendar.HOUR_OF_DAY,Integer.parseInt(dtBegin.getValue().substring(9,11)));
		cBegin.set(java.util.Calendar.MINUTE,Integer.parseInt(dtBegin.getValue().substring(11,13)));
		cBegin.set(java.util.Calendar.SECOND,Integer.parseInt(dtBegin.getValue().substring(13,15)));
		System.out.println("Calendar: " +cBegin.getTime());
		
		java.util.Calendar cEind= new GregorianCalendar();
		cEind.set(java.util.Calendar.MONTH, Integer.parseInt(dtEind.getValue().substring(4,6))-1);
		cEind.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(dtEind.getValue().substring(6,8)));
		cEind.set(java.util.Calendar.YEAR, Integer.parseInt(dtEind.getValue().substring(0, 4)));
		cEind.set(java.util.Calendar.HOUR_OF_DAY,Integer.parseInt(dtEind.getValue().substring(9,11)));
		cEind.set(java.util.Calendar.MINUTE,Integer.parseInt(dtEind.getValue().substring(11,13)));
		cEind.set(java.util.Calendar.SECOND,Integer.parseInt(dtEind.getValue().substring(13,15)));
		System.out.println("Calendar: "+ cEind.getTime());
		
		long lBegin =(cBegin.getTimeInMillis() /1000)+21600 ;
		long lEind = (cEind.getTimeInMillis() /1000) +21600;
		System.out.println("begin: " + lBegin);
		System.out.println("eind: " + lEind);
		
		return new Taak(vevent.getSummary().getValue(),lBegin,lEind,vevent.getDescription().getValue());
		
		//return null;
	}

}






