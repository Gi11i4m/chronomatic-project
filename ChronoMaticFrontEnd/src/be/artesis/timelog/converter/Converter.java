package be.artesis.timelog.converter;

import org.json.JSONException;
import org.json.JSONObject;

import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;

public class Converter {

	public static JSONObject converteer(Opdrachtgever opdrachtgever) throws JSONException{
		JSONObject jOpdrachtgever = new JSONObject();
		
		jOpdrachtgever.put("id", opdrachtgever.getID());
		jOpdrachtgever.put("bedrijfsnaam", opdrachtgever.getBedrijfsnaam());
		jOpdrachtgever.put("voornaam", opdrachtgever.getVoornaam());
		jOpdrachtgever.put("naam", opdrachtgever.getNaam());
		jOpdrachtgever.put("email", opdrachtgever.getEmail());						
		jOpdrachtgever.put("telefoonnummer", opdrachtgever.getTelefoonnummer());
		
		return jOpdrachtgever;
	}
	public static JSONObject converteer(Taak taak, int projectId) throws JSONException{
		JSONObject jTaak = new JSONObject();
		
		jTaak.put("id", taak.getId());
		jTaak.put("naam", taak.getNaam());
		jTaak.put("beginDatum", taak.getBegindatum());
		jTaak.put("eindDatum", taak.getGeschatteEinddatum());
		jTaak.put("commentaar", taak.getCommentaar());
		jTaak.put("completed", taak.getCompleted());
		jTaak.put("linkId", projectId);
		 
		return jTaak;
		
	}
	public static JSONObject converteer(Tijdspanne tijdspanne, int taakId) throws JSONException{
		JSONObject jTijd = new JSONObject();
		
		jTijd.put("id", tijdspanne.getId());
		jTijd.put("beginTijd", tijdspanne.getBeginTijd());
		jTijd.put("eindTijd", tijdspanne.getEindTijd());
		jTijd.put("isPauze", tijdspanne.isPauze());
		jTijd.put("linkId", taakId);
		
		return jTijd;
	}
	public static JSONObject converteer(Project project, int opdrachtgeverId) throws JSONException{
		JSONObject jProject = new JSONObject();
		
		jProject.put("id", project.getId());
		jProject.put("naam", project.getNaam());
		jProject.put("beginDatum", project.getBegindatum());
		jProject.put("eindDatum", project.getEinddatum());
		jProject.put("linkId",opdrachtgeverId);
		
		return jProject;
	}
	

}
