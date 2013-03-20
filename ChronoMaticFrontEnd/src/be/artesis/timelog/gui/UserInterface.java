package be.artesis.timelog.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONException;

import be.artesis.timelog.clock.Clock;
import be.artesis.timelog.controller.Deleter;
import be.artesis.timelog.controller.Inserter;
import be.artesis.timelog.controller.Updater;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.*;
/**
 *
 * @author Gilliam
 */
public class UserInterface {
	
	//================================================================================
    // Properties
    //================================================================================
	
    private static Gebruiker user;
    private static int currentProjectIndex = -1;
    private static Validator validator = Validator.getInstance();

    //================================================================================
    // Getters + setters
    //================================================================================
    
    public static Gebruiker getUser() {
        return user;
    }

    public static void setUser(Gebruiker u) {
        user = u;
    }

    public static Project getCurrentProject() throws GUIException {
        if (currentProjectIndex == -1) {
            throw new GUIException("Please select a current project first");
        }
        return getProject(currentProjectIndex);
    }
    
    public static void setCurrentProjectIndex(int i) {
        UserInterface.currentProjectIndex = i;
    }

    public static int getCurrentProjectIndex() {
        return currentProjectIndex;
    }
    
    public static ArrayList<Project> getProjects(){
    	return user.getProjects();
    }
    
    public static Project getProject(int index){
    	return user.getProject(index);
    }
    
    public static ArrayList<Taak> getCurrentTasks() throws GUIException{
    	if (currentProjectIndex == -1) {
            throw new GUIException("Please select a current project first");
        }
    	return getTasks(currentProjectIndex);
    }
    
    public static ArrayList<Taak> getTasks(int projectIndex){
    	return getProject(projectIndex).getTaken();
    }
    
    public static Taak getTask(int projectindex, int taskindex){
    	return getProject(projectindex).getTaak(taskindex);
    }
    
    public static ArrayList<Opdrachtgever> getClients(){
    	return user.getOpdrachtgevers();
    }
    
    //public static Opdrachtgever getClient(Project p) throws DataInputException{
    //	return getClient(p.getOpdrachtgeverId());
    //}
    
    public static Opdrachtgever getClient(int index) throws DataInputException{
    	return user.getOpdrachtgever(index);
    }
    
    public static Tijdspanne getTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getBestedeTijd().get(index);
    }
    
    public static Tijdspanne getWorkedTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getGewerkteTijd().get(index);
    }
    
    public static Tijdspanne getPauzedTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getPauze().get(index);
    }

    //================================================================================
    // Save methods
    //================================================================================
    
	public static Opdrachtgever saveNewClient(String naam, String vooraam, String bedrijfsnaam, String email, String telefoonnummer)
			throws DataInputException, MalformedURLException, JSONException, IOException, WebserviceException {
		Opdrachtgever client = new Opdrachtgever();
		client.setNaam(naam);
		client.setVoornaam(vooraam);
		client.setBedrijfsnaam(bedrijfsnaam);
		client.setEmail(email);
		client.setTelefoonnummer(telefoonnummer);
		client.setID(Inserter.inputOpdrachtgever(validator.getSessionKey(), client)); // Make Client ++ Add ClientID returned from DB
		user.addOpdrachtgever(client);
		return client;
	}
	
	public static void saveClient(int index, String naam, String vooraam, String bedrijfsnaam, String email, String telefoonnummer)
			throws DataInputException, MalformedURLException, IOException, WebserviceException {
		Opdrachtgever c = (Opdrachtgever) getClient(index).clone();
        c.setNaam(naam);
        c.setVoornaam(vooraam);
        c.setBedrijfsnaam(bedrijfsnaam);
        c.setEmail(email);
        c.setTelefoonnummer(telefoonnummer);
        // Past opdrachtgeverwaarden aan in database
        Updater.updateOpdrachtgever(validator.getSessionKey(), c);
        getClients().set(index, c);
	}
	
	public static Project saveNewProject(String naam, String begindatum, String einddatum, int opdrachtgeverID)
			throws DataInputException, ParseException, MalformedURLException, IOException, WebserviceException, JSONException{
		Project p = new Project();
        p.setNaam(naam);
        p.setBegindatum(begindatum);
        p.setEinddatum(einddatum);
        p.setOpdrachtgeverId(opdrachtgeverID);
        UserInterface.getUser().addProject(p);
        p.setId(Inserter.inputProject(validator.getSessionKey(), p, opdrachtgeverID));
		return p;
	}
	
	public static void saveProject(int index, String naam, String begindatum, String einddatum)
			throws DataInputException, MalformedURLException, IOException, WebserviceException, ParseException{
		Project p = (Project) getProject(index).clone();
        p.setNaam(naam);
        p.setBegindatum(begindatum);
        p.setEinddatum(einddatum);
        // Past opdrachtgeverwaarden aan in database
        Updater.updateProject(validator.getSessionKey(), p);;
        getProjects().set(index, p);
	}
	
	public static Taak saveNewTask(String name, String startdate, String enddate, String comment, boolean completed)
			throws DataInputException, ParseException, GUIException, MalformedURLException, IOException, WebserviceException, JSONException{
        Taak t = new Taak();
        t.setNaam(name);
        t.setBegindatum(startdate);
        t.setGeschatteEinddatum(enddate);
        t.setCommentaar(comment);
        //Send to Database
        int pid = UserInterface.getCurrentProject().getId();
        t.setId((Inserter.inputTaak(validator.getSessionKey(), t, pid)));
        getCurrentProject().addTaak(t);
        return t;
	}
	
	public static void saveTask(int index, String name, String startdate, String enddate, String comment, boolean completed)
			throws GUIException, DataInputException, ParseException, MalformedURLException, IOException, WebserviceException{	
		Taak t = (Taak) getCurrentTasks().get(index).clone();
        t.setNaam(name);
        t.setBegindatum(startdate);
        t.setGeschatteEinddatum(enddate);
        t.setCommentaar(comment);
        t.setCompleted(completed);
        // Taakwaarden worden aangepast in database
        Updater.updateTaak(validator.getSessionKey(), t);
        getCurrentTasks().set(index, t);
	}
    
	public static Tijdspanne saveNewTimespan(long start, long stop, Taak t, boolean isPause)
			throws MalformedURLException, IOException, WebserviceException, DataInputException{
		Tijdspanne ts = new Tijdspanne(start, stop);
		ts.setPauze(isPause);
		Inserter.inputTijdSpanne(validator.getSessionKey(), ts, t.getID());
		t.addBestedeTijd(ts);
		return ts;
	}
	
	//FIXME Index voor totale lijst (bestedeTijd) of gewerkte/pauze apart?
	public static void saveTimespan(int index, long start, long stop, Taak t, boolean isPauze)
			throws DataInputException{
		Tijdspanne ts;
		if (!isPauze) {
			ts = t.getGewerkteTijd().get(index);
		} else {
			ts = t.getPauze().get(index);
		}
		
		Tijdspanne ts2 = (Tijdspanne) ts.clone();
		ts2.setBeginTijd(start);
		ts2.setEindTijd(stop);
		ts2.setPauze(isPauze);
		
		//Updater.updateTijdspanne(validator.getSessionKey(), ts2);
		if (!isPauze) {
			ts = t.getGewerkteTijd().set(index, ts2);
		} else {
			ts = t.getPauze().set(index, ts2);
		}
	}

    //================================================================================
    // Remove methods
    //================================================================================
	
	public static void removeClient(Opdrachtgever c)
			throws MalformedURLException, IOException, WebserviceException{
		Deleter.deleteOpdrachtgever(validator.getSessionKey(), c);
		getClients().remove(c);
	}
	
	public static void removeProject(Project p)
			throws MalformedURLException, IOException, WebserviceException{
		Deleter.deleteProject(validator.getSessionKey(), p);
        getProjects().remove(p);
	}
	
	public static void removeTask(Taak t)throws MalformedURLException, IOException, WebserviceException, GUIException{
		Deleter.deleteTaak(validator.getSessionKey(), t);
        getCurrentTasks().remove(t);
	}
	
	public static void removeTimespan(Tijdspanne ts, Taak t) throws MalformedURLException, IOException, WebserviceException{
		Deleter.deleteTijdSpanne(validator.getSessionKey(), ts);
		t.getGewerkteTijd().remove(ts);
	}
	
	
	
	
	
	
	
    // Code verplaatst vanwege verwijderde methode
    // Taak taak = Creator.createTaak();
    // Inserter.inputTaak(validator.getSessionKey(), t);
}