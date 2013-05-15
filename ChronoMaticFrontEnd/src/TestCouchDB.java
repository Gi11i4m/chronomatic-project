import net.sf.json.JSONObject;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;


public class TestCouchDB {

	public static void main(String[] args) {
		Session s = new Session("localhost", 5984);
		//s.createDatabase("test");
		
		//Database db = s.getDatabase("test");
		
		JSONObject jo = new JSONObject();
		
			jo.put("id", 1);
			jo.put("naam", "piets");
			
			Document d = new Document(jo);
			jso
			//sjonarray.addString
			//om het probleem op te vangen in de connection class dat ge gene string in de constructor kunt toevoegen
			
			
	
		
		System.out.println(jo.toString());
		System.out.println(d);
			
		
	}
	
}
