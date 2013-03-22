package be.artesis.timelog.externAuth;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

public class Test {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws IOException, JSONException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub

		String piet = GetUserInfo.request("433543220-uayP9fuc9e8qm2ebUUlxEFYtfk7p2SQbuoz74EEE", new Twitter());
		System.out.println(piet);
	}

}
