package be.artesis.timelog.externAuth;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GetUserInfo {

	public static JSONObject request(String token, SocialMedia social) throws IOException, JSONException, ParserConfigurationException, SAXException {
		URL url;
		JSONObject returnObject = new JSONObject();
		
		StringBuilder params = new StringBuilder();
		params.append(social.getUserInfoUrl());
		params.append("access_token=");
		params.append(token);
		
		url = new URL(params.toString());
		
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    if (conn.getResponseCode() != 200){
	        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	    }
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String readLine;
	    String inputData = "";
	    while ((readLine = br.readLine()) != null) {
	        inputData+=(readLine);
	    }
	    br.close();
	    
	    //inputData = inputData.replace(" ","");
	    JSONObject requestedJson; 
	    
	    // Linkedin XML parser
	    if(social.toString().equals("linkedin")) {
	    	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (new InputSource(new StringReader(inputData)));
	        doc.getDocumentElement ().normalize ();
	        returnObject.put("email",doc.getElementsByTagName("email-address").item(0).getTextContent());
	        returnObject.put("voornaam", " ");
	    	returnObject.put("naam", " ");
	    }
	    
	    //Microsofts manier om json door te sturen
	    else if(social.toString().equals("microsoft")) {
	    	requestedJson = new JSONObject(inputData);
	    	JSONObject tempJSONObject = requestedJson.getJSONObject("emails");

	    	returnObject.put("email",tempJSONObject.getString("account"));
	    	returnObject.put("voornaam", requestedJson.get("first_name"));
	    	returnObject.put("naam", requestedJson.get("last_name"));
	    }
	    else if(social.toString().equals("google")) {
	    	requestedJson = new JSONObject(inputData);
	    	
	    	returnObject.put("email",requestedJson.getString("email"));
	    	returnObject.put("voornaam", requestedJson.get("given_name"));
	    	returnObject.put("naam", requestedJson.get("family_name"));
	    }
	    
	    else if(social.toString().equals("facebook")) {
	    	requestedJson = new JSONObject(inputData);
	    	returnObject.put("email",requestedJson.getString("email"));
	    	returnObject.put("voornaam", requestedJson.get("first_name"));
	    	returnObject.put("naam", requestedJson.get("last_name"));
	    }

	    return returnObject;
	}
}
