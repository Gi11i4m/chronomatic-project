package be.artesis.timelog.view;

import be.artesis.timelog.clock.Clock;

public class Tijdspanne {

    //================================================================================
    //Properties
    //================================================================================
	
    private long beginTijd;
    private long eindTijd;  
    private int id;
    private boolean pauze;

    //================================================================================
    // Constructor 
    //================================================================================
    
    public Tijdspanne(){
    	this(0,0);
    }
    
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

    //================================================================================
    // Getters + setters
    //================================================================================
    
     public void setPauze(boolean pauze) {
        this.pauze = pauze;
    }

    public boolean isPauze() {
        return pauze;
    }

    public int getId() {
        return id;
    }
    
    public void setID(int id){
    	this.id = id;
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

    public long getTijdsduur() {
        return eindTijd - beginTijd;
    }
    
    //================================================================================
    // Extra
    //================================================================================
    
    @Override
    public String toString(){
        return Clock.timestampToString(beginTijd) + "    →    " + Clock.timestampToString(eindTijd);
    }
    
    public Object clone(){
        Tijdspanne t = new Tijdspanne(this.beginTijd, this.eindTijd, this.id, this.pauze);
        return t;
    }
}
