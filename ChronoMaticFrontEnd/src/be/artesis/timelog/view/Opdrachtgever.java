package be.artesis.timelog.view;

public class Opdrachtgever implements Cloneable{

	//================================================================================
    // Properties
    //================================================================================
	
    private String naam;
    private String voornaam;
    private String bedrijfsnaam;
    private String email;
    private String telefoonnummer;
	private String straat;
    private String plaats;
    private int id;

    //================================================================================
    // Constructors
    //================================================================================
    
    public Opdrachtgever(String naam, String voornaam, String bedrijfsnaam, String email, String telefoonnummer, int id) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.bedrijfsnaam = bedrijfsnaam;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.id = id;
    }

    public Opdrachtgever() {
        this.id = 0;
    };

    //================================================================================
    // Getters + setters
    //================================================================================
    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) throws DataInputException{
        if (DataControle.persoonNaamCorrect(naam)) {
            this.naam = naam;
        }
        else{
            throw new DataInputException("Name is empty or contains illegal characters");
        }
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) throws DataInputException{
        if (DataControle.persoonNaamCorrect(voornaam)) {
            this.voornaam = voornaam;
        }
        else{
            throw new DataInputException("First name is empty or contains illegal characters");
        }
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) throws DataInputException {
        if (DataControle.naamCorrect(bedrijfsnaam)) {
            this.bedrijfsnaam = bedrijfsnaam;
        }
        else{
            throw new DataInputException("Company name empty or contains illegal characters");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws DataInputException{
        if (DataControle.emailCorrect(email)) {
            this.email = email;
        }
        else{
            throw new DataInputException("Email empty or contains illegal characters");
        }
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) throws DataInputException{
        if (DataControle.telefoonCorrect(telefoonnummer)) {
            this.telefoonnummer = telefoonnummer;
        }
        else{
            throw new DataInputException("Phone number empty or contains illegal characters");
        }
    }
    
    public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	
	public String getAdres(){
		return straat + ", " + plaats;
	}

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    //================================================================================
    // Extra
    //================================================================================
    
    @Override
    public String toString() {
        return getVoornaam() + " " + getNaam() + " [" + getBedrijfsnaam() + "]";
    }
    
    @Override
    public Object clone(){
        Opdrachtgever o = new Opdrachtgever(this.naam, this.voornaam, this.bedrijfsnaam, this.email, this.telefoonnummer, this.id);
        return o;
    }
}