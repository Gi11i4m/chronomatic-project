package be.artesis.timelog.view;

import java.util.ArrayList;
import java.util.Collections;

public class Gebruiker {

	// ================================================================================
	// Properties
	// ================================================================================

	private String naam;
	private String voornaam;
	private String gebruikersnaam;
	private String email;
	private String telefoonnummer;
	private String straat;
	private String plaats;
	private String VAT;
	private String companyName;
	private String IBAN;
	private String BIC;
	private int id;
	private ArrayList<Project> projects;
	private ArrayList<Opdrachtgever> opdrachtgevers;

	// ================================================================================
	// Constructors
	// ================================================================================

	public Gebruiker(String naam, String voornaam, String gebruikersnaam, String email) {
		this.naam = naam;
		this.voornaam = voornaam;
		this.gebruikersnaam = gebruikersnaam;
		this.email = email;
		this.id = 0;
		this.projects = new ArrayList<Project>();
		this.opdrachtgevers = new ArrayList<Opdrachtgever>();
	}

	public Gebruiker(String naam, String voornaam, String gebruikersnaam, String email, int id) {
		this(naam, voornaam, gebruikersnaam, email);
		this.id = id;
		this.projects = new ArrayList<Project>();
		this.opdrachtgevers = new ArrayList<Opdrachtgever>();
	}

	public Gebruiker() {
		this.projects = new ArrayList<Project>();
		this.opdrachtgevers = new ArrayList<Opdrachtgever>();
	}

	// ================================================================================
	// Getters + setters
	// ================================================================================

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) throws DataInputException {
		if (DataControle.persoonNaamCorrect(naam)) {
			this.naam = naam;
		} else {
			throw new DataInputException("Name empty or contains illegal characters");
		}
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) throws DataInputException {
		if (DataControle.persoonNaamCorrect(voornaam)) {
			this.voornaam = voornaam;
		} else {
			throw new DataInputException("First name empty or contains illegal characters");
		}
	}

	public String getVolledigeNaam() {
		return getVoornaam() + " " + getNaam();
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	// public void setGebruikersnaam(String gebruikersnaam) throws
	// DataInputException {
	// if (DataControle.naamCorrect(gebruikersnaam)) {
	// this.gebruikersnaam = gebruikersnaam;
	// } else {
	// throw new
	// DataInputException("Username empty or contains illegal characters");
	// }
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws DataInputException {
		if (DataControle.emailCorrect(email)) {
			this.email = email;
		} else {
			throw new DataInputException("Email empty or contains illegal characters");
		}
	}
	
	public String getTelefoonnummer(){
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

	public String getVAT() {
		return VAT;
	}

	public void setVAT(String vAT) {
		VAT = vAT;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getBIC() {
		return BIC;
	}

	public void setBIC(String bIC) {
		BIC = bIC;
	}

	public int getID() {
		return id;
	}

	public ArrayList<Opdrachtgever> getOpdrachtgevers() {
		return opdrachtgevers;
	}

	public Opdrachtgever getOpdrachtgever(int id) throws DataInputException {
		Opdrachtgever client = null;
		for (Opdrachtgever o : opdrachtgevers) {
			if (o.getID() == id) {
				client = o;
			}
		}
		if (client == null) {
			throw new DataInputException("Client not found");
		}
		return client;
	}

	public Project getProject(int index) {
		return projects.get(index);
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(Project[] projects) {
		Collections.addAll(this.projects, projects);
	}

	public void setOpdrachtgevers(Opdrachtgever[] opdrachtgevers) {
		Collections.addAll(this.opdrachtgevers, opdrachtgevers);
	}

	// ================================================================================
	// Add + remove methods
	// ================================================================================

	public void addProject(Project p) throws DataInputException {
		if (!DataControle.naamBestaat(projects, p)) {
			projects.add(p);
		} else {
			throw new DataInputException("Name already exists");
		}
	}

	public boolean removeProject(Project p) {
		return projects.remove(p);
	}

	public boolean removeProject(int i) {
		Project p = projects.get(i);
		return projects.remove(p);
	}

	public void addOpdrachtgever(Opdrachtgever o) throws DataInputException {
		if (!DataControle.naamBestaat(opdrachtgevers, o)) {
			opdrachtgevers.add(o);
		} else {
			throw new DataInputException("Name already exists");
		}
	}

	public boolean removeOpdrachtgever(Opdrachtgever o) {
		return opdrachtgevers.remove(o);
	}

	public boolean removeOpdrachtgever(int i) {
		Opdrachtgever o = opdrachtgevers.get(i);
		return opdrachtgevers.remove(o);
	}

	// ================================================================================
	// Extra
	// ================================================================================

	@Override
	public String toString() {
		return voornaam + " " + naam + " (" + gebruikersnaam + ")";
	}

	@Override
	public Object clone() {
		Gebruiker g = new Gebruiker(this.naam, this.voornaam, this.gebruikersnaam, this.email);
		g.id = this.id;
		g.projects = this.projects;
		g.opdrachtgevers = this.opdrachtgevers;
		return g;
	}
}