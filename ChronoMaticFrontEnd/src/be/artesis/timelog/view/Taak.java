package be.artesis.timelog.view;

import be.artesis.timelog.clock.Clock;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Taak implements Cloneable {

	//================================================================================
    // Properties
    //================================================================================
	
    private String naam;
    private long begindatum;
    private long geschatteEinddatum;
    private String commentaar;
    private boolean completed;
    private int id;
    private ArrayList<Tijdspanne> bestedeTijd;

    //================================================================================
    // Constructors
    //================================================================================
    
    public Taak(String naam, long begindatum, long geschatteEinddatum, String commentaar) {
        this(naam, begindatum, geschatteEinddatum, commentaar, 0);
    }

    public Taak(String naam, long begindatum, long geschatteEinddatum, String commentaar, int id) {
        this.naam = naam;
        this.begindatum = begindatum;
        this.geschatteEinddatum = geschatteEinddatum;
        this.commentaar = commentaar;
        this.completed = false;    
        this.bestedeTijd = new ArrayList<Tijdspanne>();      
        this.id = id;       
    }

    public Taak() {
        this("", 0, 0, "");
    }
    
    //================================================================================
    // Getters + setters
    //================================================================================
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) throws DataInputException {
        if (DataControle.naamCorrect(naam)) {
            this.naam = naam;
        } else {
            throw new DataInputException("Project name contains illegal characters");
        }
    }

    public long getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(String datum) throws DataInputException, ParseException {
        if (DataControle.datumCorrect(datum)) {
            long tdatum = Clock.StringToTimestamp(datum);
            setBegindatum(tdatum);
        } else {
            throw new DataInputException("Date format not correct");
        }
    }

    public void setBegindatum(long datum) throws DataInputException {
        if (geschatteEinddatum == 0 || datum <= geschatteEinddatum) {
            for (Tijdspanne t : bestedeTijd) {
                if (t.getBeginTijd() < datum) {
                    throw new DataInputException("One or more timespans out of task bounds");
                }
            }
            begindatum = datum;
        } else {
            throw new DataInputException("Startdate is later than enddate");
        }
    }

    public long getGeschatteEinddatum() {
        return geschatteEinddatum;
    }

    public void setGeschatteEinddatum(String datum) throws DataInputException, ParseException {
        if (DataControle.datumCorrect(datum)) {
            long tdatum = Clock.StringToTimestamp(datum);
            setGeschatteEinddatum(tdatum);
        } else {
            throw new DataInputException("Date format not correct");
        }
    }

    public void setGeschatteEinddatum(long datum) throws DataInputException {
        if (datum >= begindatum) {
            for (Tijdspanne t : bestedeTijd) {
                if (t.getEindTijd() > datum) {
                    throw new DataInputException("One or more timespans out of task bounds");
                }
            }
            geschatteEinddatum = datum;
        } else {
            throw new DataInputException("Enddate is earlier than startdate");
        }
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void toggleCompleted() {
        if (completed = true) {
            completed = false;
        } else {
            completed = true;
        }
    }

    public int getID() {
        return id;
    }

    public ArrayList<Tijdspanne> getBestedeTijd() {
        return bestedeTijd;
    }

    public ArrayList<Tijdspanne> getGewerkteTijd(){
    	ArrayList<Tijdspanne> gewerkt = new ArrayList<Tijdspanne>();
    	for (Tijdspanne ts : bestedeTijd) {
			if (!ts.isPauze()) {
				gewerkt.add(ts);
			}
		}
    	return gewerkt;
    }
    
    public ArrayList<Tijdspanne> getPauze(){
    	ArrayList<Tijdspanne> pauze = new ArrayList<Tijdspanne>();
    	for (Tijdspanne ts : bestedeTijd) {
			if (ts.isPauze()) {
				pauze.add(ts);
			}
		}
    	return pauze;
    }
    
    public long getTotaleWerktijd() {
        long l = 0;
        for (Tijdspanne t : bestedeTijd) {
        	if (!t.isPauze()) {
                l += t.getTijdsduur();
			}
        }
        return l;
    }

    public long getTotalePauze() {
        long l = 0;
        for (Tijdspanne t : bestedeTijd) {
        	if (t.isPauze()) {
        		l += t.getTijdsduur();
			}
            
        }
        return l;
    }
    
    //================================================================================
    // Add + remove functies
    //================================================================================
    
    public void addBestedeTijd(Tijdspanne t) throws DataInputException {
        if (t.getBeginTijd() >= begindatum && t.getEindTijd() <= geschatteEinddatum) {
            bestedeTijd.add(t);
        } else {
            throw new DataInputException("Timespan out of task bounds");
        }
    }

    public boolean removeBestedeTijd(Tijdspanne t) {
        return bestedeTijd.remove(t);
    }

    public void removeBestedeTijd(int i) {
        bestedeTijd.remove(i);
    }

    //================================================================================
    // Extra functionaliteit
    //================================================================================
    
    //return tijd tot de geschatte einddatum in seconden
    public long tijdResterend() {
        long now = Clock.generateUnixTimestamp();
        long diff = geschatteEinddatum - now;
        return diff;
    }

    //geeft aan of de huidige taak de geschatte einddatum heeft overschreden
    public boolean overTijd() {
        long now = Clock.generateUnixTimestamp() - 86400;
        long diff = geschatteEinddatum - now;

        return diff < 0;
    }

    public void setGewerkteTijd(Tijdspanne[] t) {
        Collections.addAll(this.bestedeTijd, t);
    }

    @Override
    public String toString() {
        return naam;
    }
    
    @Override
    public Object clone(){
        Taak t = new Taak(this.naam, this.begindatum, this.geschatteEinddatum, this.commentaar, this.id);
        t.setCompleted(this.getCompleted());
        return t;
    }
}