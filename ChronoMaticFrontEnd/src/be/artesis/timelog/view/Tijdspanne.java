package be.artesis.timelog.view;

import be.artesis.timelog.clock.Clock;

public class Tijdspanne {

    private long beginTijd;
    private long eindTijd;
    private boolean pauze;  
    private int id;

    public Tijdspanne(long beginTijd, long eindTijd) {
        this.beginTijd = beginTijd;
        this.eindTijd = eindTijd;
        this.id = 0;
        this.pauze = false;        
    }

    public Tijdspanne(long beginTijd, long eindTijd, int id, boolean pauze) {
        this.beginTijd = beginTijd;
        this.eindTijd = eindTijd;
        this.id = id;
        this.pauze = pauze;
    }

     public void setPauze(boolean pauze) {
        this.pauze = pauze;
    }

    public boolean isPauze() {
        return pauze;
    }

    public int getId() {
        return id;
    }

    public long getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(long tijd) throws DataInputException {
        if (eindTijd == 0 || tijd < eindTijd) {
            beginTijd = tijd;
        } else {
            throw new DataInputException("Begintime after endtime");
        }
    }
    
    public long getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(long tijd) throws DataInputException {
        if (tijd > beginTijd) {
            eindTijd = tijd;
        } else {
            throw new DataInputException("Endtime before begintime");
        }
    }

    public int getID() {
        return id;
    }

    public long getTijdsduur() {
        return eindTijd - beginTijd;
    }
    
    @Override
    public String toString(){
        return Clock.timestampToString(beginTijd) + "    →    " + Clock.timestampToString(eindTijd);
    }
    
    
    public static void main(String[] args) {
    }
}
