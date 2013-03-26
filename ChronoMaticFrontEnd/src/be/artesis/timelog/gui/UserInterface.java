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
    
    //Enkel voor treenode class
    public static Project getProject(String name) throws GUIException{
    	for (Project p : getProjects()) {
			if (p.getNaam().equals(name)) {
				return p;
			}
		}
    	throw new GUIException("Project not found!");
    }
    
    //Enkel voor treenode class
    public static Taak getTaak(Project p, String name) throws GUIException{
    	for (Taak t : p.getTaken()) {
    		if (t.getNaam().equals(name)) {
				return t;
			}
		}
    	throw new GUIException("Project not found!");
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
    
    public static Opdrachtgever getProjectClient(int projectindex) throws DataInputException{
    	return getClient(getProject(projectindex).getOpdrachtgeverId());
    }
    
    public static Opdrachtgever getClient(int index) throws DataInputException{
    	return user.getOpdrachtgever(index);
    }
    
    public static Tijdspanne getTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getTotaleTijd().get(index);
    }
    
    public static Tijdspanne getWorkedTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getGewerkteTijd().get(index);
    }
    
    public static Tijdspanne getPauzedTimespan(int projectindex, int taskindex, int index){
    	return getTask(projectindex, taskindex).getPauze().get(index);
    }

    //================================================================================
    // Create + update methods
    //================================================================================
    
    public static void updateUser(String firstName, String lastName, String email)
    		throws DataInputException, MalformedURLException, IOException, WebserviceException{
    	Gebruiker u = (Gebruiker) getUser().clone();
    	u.setVoornaam(firstName);
    	u.setNaam(lastName);
    	u.setEmail(email);
    	Updater.updateGebruiker(validator.getSessionKey(), u);
    	setUser(u);
    }
    
	public static Opdrachtgever createClient(String naam, String voornaam, String bedrijfsnaam, String email, String telefoonnummer)
			throws DataInputException, JSONException, IOException, WebserviceException{
		Opdrachtgever client = new Opdrachtgever();
		client.setNaam(naam);
		client.setVoornaam(voornaam);
		client.setBedrijfsnaam(bedrijfsnaam);
		client.setEmail(email);
		client.setTelefoonnummer(telefoonnummer);
		user.addOpdrachtgever(client);
		try {
			client.setID(Inserter.inputOpdrachtgever(validator.getSessionKey(), client));
		} catch (JSONException | IOException | WebserviceException e) {
			user.removeOpdrachtgever(client);
			throw e;
		} // Make Client ++ Add ClientID returned from DB
		return client;
	}
	
	public static void updateClient(int index, String naam, String voornaam, String bedrijfsnaam, String email, String telefoonnummer)
			throws DataInputException, MalformedURLException, IOException, WebserviceException {
		Opdrachtgever c = (Opdrachtgever) getClient(index).clone();
        c.setNaam(naam);
        c.setVoornaam(voornaam);
        c.setBedrijfsnaam(bedrijfsnaam);
        c.setEmail(email);
        c.setTelefoonnummer(telefoonnummer);
        // Past opdrachtgeverwaarden aan in database
        Updater.updateOpdrachtgever(validator.getSessionKey(), c);
        getClients().set(index, c);
	}
	
	public static Project createProject(String naam, long begindatum, long einddatum, int opdrachtgeverId)
			throws DataInputException, ParseException, IOException, WebserviceException, JSONException{
		Project p = new Project();
        p.setNaam(naam);
        p.setBegindatum(begindatum);
        p.setEinddatum(einddatum);
        p.setOpdrachtgeverId(opdrachtgeverId);
        UserInterface.getUser().addProject(p);
        try {
			p.setId(Inserter.inputProject(validator.getSessionKey(), p, opdrachtgeverId));
		} catch (IOException | WebserviceException| JSONException e) {
			user.removeProject(p);
			throw e;
		}
		return p;
	}
	
	public static void updateProject(int index, String naam, long begindatum, long einddatum, int opdrachtgeverId)
			throws DataInputException, MalformedURLException, IOException, WebserviceException, ParseException{
		Project p = (Project) getProject(index).clone();
        p.setNaam(naam);
        p.setBegindatum(begindatum);
        p.setEinddatum(einddatum);
        p.setOpdrachtgeverId(opdrachtgeverId);
        // Past projectwaarden aan in database
        Updater.updateProject(validator.getSessionKey(), p);;
        getProjects().set(index, p);
	}
	
	public static Taak createTask(String name, long startdate, long enddate, String comment, boolean completed)
			throws DataInputException, ParseException, GUIException, IOException, WebserviceException, JSONException{
        Taak t = new Taak();
        t.setNaam(name);
        t.setBegindatum(startdate);
        t.setGeschatteEinddatum(enddate);
        if ("".equals(comment)) {
        	comment = " ";
        }
		t.setCommentaar(comment);
        t.setCompleted(completed);
        //Send to Database
        int pid = UserInterface.getCurrentProject().getId();
        getCurrentProject().addTaak(t);
        try {
			t.setId((Inserter.inputTaak(validator.getSessionKey(), t, pid)));
		} catch (IOException | WebserviceException | JSONException e) {
			getCurrentProject().removeTaak(t);
			throw e;
		}
        return t;
	}
	
	public static void updateTask(int index, String name, long startdate, long enddate, String comment, boolean completed)
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
    
	public static Tijdspanne createTimespan(long start, long stop, Taak t, boolean isPause)
			throws DataInputException, IOException, WebserviceException{
		Tijdspanne ts = new Tijdspanne(start, stop);
		ts.setPauze(isPause);
		t.addBestedeTijd(ts);
		try {
			Inserter.inputTijdSpanne(validator.getSessionKey(), ts, t.getID());
		} catch (IOException | WebserviceException e) {
			t.removeBestedeTijd(ts);
			throw e;
		}
		return ts;
	}
	
	public static void updateTimespan(int index, long start, long stop, Taak t, boolean isPause)
			throws DataInputException, IOException, WebserviceException{
		int i = t.convertTimespanIndex(index, isPause);
		
		Tijdspanne ts = (Tijdspanne) t.getTotaleTijd().get(i).clone();
		ts.setBeginTijd(start);
		ts.setEindTijd(stop);
		ts.setPauze(isPause);
		
		t.getTotaleTijd().set(i, ts);
		Updater.updateTijdspanne(validator.getSessionKey(), ts);
	}

    //================================================================================
    // Remove methods
    //================================================================================
	
	public static void deleteClient(Opdrachtgever c)
			throws MalformedURLException, IOException, WebserviceException, GUIException{
		for (Project p : UserInterface.getProjects()) {
			if (p.getOpdrachtgeverId() == c.getID()) {
				throw new GUIException("This client is associated with a project");
			}
		}
		Deleter.deleteOpdrachtgever(validator.getSessionKey(), c);
		getClients().remove(c);
	}
	
	public static void deleteProject(Project p)
			throws MalformedURLException, IOException, WebserviceException{
		Deleter.deleteProject(validator.getSessionKey(), p);
        getProjects().remove(p);
	}
	
	public static void deleteTask(Taak t)throws MalformedURLException, IOException, WebserviceException, GUIException{
		Deleter.deleteTaak(validator.getSessionKey(), t);
        getCurrentTasks().remove(t);
	}
	
	public static void deleteTimespan(Tijdspanne ts, Taak t) throws MalformedURLException, IOException, WebserviceException{
		Deleter.deleteTijdSpanne(validator.getSessionKey(), ts);
		t.getGewerkteTijd().remove(ts);
	}
	
	
	
	
	
	
	
    // Code verplaatst vanwege verwijderde methode
    // Taak taak = Creator.createTaak();
    // Inserter.inputTaak(validator.getSessionKey(), t);
}