package be.artesis.timelog.view;

import be.artesis.timelog.clock.Clock;
import java.util.ArrayList;
import java.util.Collections;

public class Taak implements Cloneable {

    private String naam;
    private long begindatum;
    private long geschatteEinddatum;
    private String commentaar;
    private boolean completed;
    private int id;

    
    private ArrayList<Tijdspanne> gewerkteTijd;
    private ArrayList<Tijdspanne> pauze;

    public Taak(String naam, long begindatum, long geschatteEinddatum, String commentaar) {
        this(naam, begindatum, geschatteEinddatum, commentaar, 0);
    }

    public Taak(String naam, long begindatum, long geschatteEinddatum, String commentaar, int id) {
        this.naam = naam;
        this.begindatum = begindatum;
        this.geschatteEinddatum = geschatteEinddatum;
        this.commentaar = commentaar;
        this.completed = false;    
        this.gewerkteTijd = new ArrayList<Tijdspanne>();
        this.pauze = new ArrayList<Tijdspanne>();        
        this.id = id;       
    }

    public Taak() {
        this("", 0, 0, "");
    }
    
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

    public void setBegindatum(String datum) throws DataInputException {
        if (DataControle.datumCorrect(datum)) {
            long tdatum = Clock.StringToTimestamp(datum);
            setBegindatum(tdatum);
        } else {
            throw new DataInputException("Date format not correct");
        }

    }

    public void setBegindatum(long datum) throws DataInputException {
        if (geschatteEinddatum == 0 || datum <= geschatteEinddatum) {
            for (Tijdspanne t : gewerkteTijd) {
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

    public void setGeschatteEinddatum(String datum) throws DataInputException {
        if (DataControle.datumCorrect(datum)) {
            long tdatum = Clock.StringToTimestamp(datum);
            setGeschatteEinddatum(tdatum);
        } else {
            throw new DataInputException("Date format not correct");
        }
    }

    public void setGeschatteEinddatum(long datum) throws DataInputException {
        if (datum >= begindatum) {
            for (Tijdspanne t : gewerkteTijd) {
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

    public ArrayList<Tijdspanne> getGewerkteTijd() {
        return gewerkteTijd;
    }

    public void addGewerkteTijd(Tijdspanne t) throws DataInputException {
        if (t.getBeginTijd() >= begindatum && t.getEindTijd() <= geschatteEinddatum) {
            gewerkteTijd.add(t);
        } else {
            throw new DataInputException("Timespan out of task bounds");
        }
    }

    public boolean removeGewerkteUren(Tijdspanne t) {
        return gewerkteTijd.remove(t);
    }

    public void removeGewerkteUren(int i) {
        gewerkteTijd.remove(i);
    }

    public ArrayList<Tijdspanne> getPauze() {
        return pauze;
    }

    public void addPauze(Tijdspanne t) throws DataInputException {
        if (t.getBeginTijd() >= begindatum && t.getEindTijd() <= geschatteEinddatum) {
            pauze.add(t);
        } else {
            throw new DataInputException("Timespan out of task bounds");
        }
    }

    public boolean removePauze(Tijdspanne t) {
        return pauze.remove(t);
    }

    public void removePauze(int i) {
        pauze.remove(i);
    }

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

    public long getTotaleWerktijd() {
        long l = 0;
        for (Tijdspanne t : gewerkteTijd) {
            l += t.getTijdsduur();
        }
        return l;
    }

    public long getTotalePauze() {
        long l = 0;
        for (Tijdspanne t : pauze) {
            l += t.getTijdsduur();
        }
        return l;
    }

    public void setGewerkteTijd(Tijdspanne[] t) {
        Collections.addAll(this.gewerkteTijd, t);
    }

    public void setPauze(Tijdspanne[] t) {
        Collections.addAll(this.pauze, t);
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