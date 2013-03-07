package be.artesis.timelog.view;

import be.artesis.timelog.clock.Clock;
import java.util.ArrayList;
import java.util.Collections;

public class Project {

    private String naam;
    private int opdrachtgeverId;
    private long begindatum;
    private long einddatum;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private ArrayList<Taak> taken;

    public Project(String naam, int opdrachtgeverId, long begindatum, long einddatum) {
        this.naam = naam;
        this.opdrachtgeverId = opdrachtgeverId;
        this.begindatum = begindatum;
        this.einddatum = einddatum;
        this.id = 0;
        this.taken = new ArrayList<Taak>();
    }

    public Project(String naam, int opdrachtgeverId, long begindatum, long einddatum, int id) {
        this(naam, opdrachtgeverId, begindatum, einddatum);
        this.id = id;
        this.taken = new ArrayList<Taak>();
    }

    public Project() {
        this.taken = new ArrayList<Taak>();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String projectnaam) throws DataInputException {
        if (DataControle.naamCorrect(projectnaam)) {
            this.naam = projectnaam;
        } else {
            throw new DataInputException("Projectname contains illegal characters");
        }
    }

    public int getOpdrachtgeverId() {
        return opdrachtgeverId;
    }

    public void setOpdrachtgeverId(int opdrachtgeverId) {
        this.opdrachtgeverId = opdrachtgeverId;
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
        if (einddatum == 0 || datum <= einddatum) {
            for (Taak t : taken) {
                if (t.getBegindatum() < datum) {
                    throw new DataInputException("One or more tasks out of project bounds");
                }
            }
            begindatum = datum;
        } else {
            throw new DataInputException("Startdate is later than enddate");
        }
    }

    public long getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(String datum) throws DataInputException {
        if (DataControle.datumCorrect(datum)) {
            long tdatum = Clock.StringToTimestamp(datum);
            setEinddatum(tdatum);
            
        } else {
            throw new DataInputException("Date format not correct");
        }
    }

    public void setEinddatum(long datum) throws DataInputException {
        if (datum >= begindatum) {
            for (Taak t : taken) {
                if (t.getGeschatteEinddatum() > datum) {
                    throw new DataInputException("One or more tasks out of project bounds");
                }
            }
            einddatum = datum;
        } else {
            throw new DataInputException("Enddate is earlier than startdate");
        }
    }

    public void addTaak(Taak t) throws DataInputException {
        if (!DataControle.naamBestaat(taken, t)) {
            if (t.getBegindatum() >= begindatum && t.getGeschatteEinddatum() <= einddatum) {
                taken.add(t);
            } else {
                throw new DataInputException("Task dates out of project bounds");
            }
        } else {
            throw new DataInputException("Name already exists");
        }
    }

    public ArrayList<Taak> getTaken() {
        return taken;
    }

    public boolean removeTaak(Taak t) {
        return taken.remove(t);
    }

    public void removeTaak(int i) {
        taken.remove(i);
    }

    public double getPercentageComplete() {
        double percent = 0;

        if (!taken.isEmpty()) {
            double complete = 0, total = 0;
            for (Taak t : taken) {
                if (t.getCompleted()) {
                    complete++;
                }
                total++;
            }
            percent = complete / total;
        }

        return percent;
    }
    public void setTaken(Taak[] taken) {
        Collections.addAll(this.taken, taken);
    }
    public Taak getTaak(int index){
        return taken.get(index);
    }

    @Override
    public String toString() {
        return naam;
    }
}