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

	public static String request(String token, SocialMedia social) throws IOException, JSONException, ParserConfigurationException, SAXException {
		URL url;
		String returnString = null;
		
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
	    
	    System.out.println(inputData);
	    //inputData = inputData.replace(" ","");
	    JSONObject requestedJson; 
	    
	    if(social.toString().equals("linkedin")) {
	    	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (new InputSource(new StringReader(inputData)));
	        doc.getDocumentElement ().normalize ();
	        returnString = doc.getElementsByTagName("email-address").item(0).getTextContent();
	    }
	    
	    else if(social.toString().equals("microsoft")) {
	    	requestedJson = new JSONObject(inputData);
	    	JSONObject obj = requestedJson.getJSONObject("emails");
	    	returnString = obj.getString("account");
	    }
	    else {
	    	requestedJson = new JSONObject(inputData);
	    	returnString = requestedJson.getString("email");
	    }

	    return returnString;
	}
}
