/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.artesis.timelog.model;

import be.artesis.timelog.view.Gebruiker;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yolan
 */
public class test {
    
    public static void main(String[] args) {
        /*try {
            /*
            JSONObject[] j = JSONOverdrager.getProjectenVanGebruiker("c06lu7miempl8jk82livcv9mg0");
            
            System.out.println(JSONOverdrager.getGebruiker("c06lu7miempl8jk82livcv9mg0"));        
            System.out.println(JSONOverdrager.getOpdrachtgeverVanProject("c06lu7miempl8jk82livcv9mg0", 2));
           // System.out.println(JSONOverdrager.getProject("c06lu7miempl8jk82livcv9mg0", 2));
            System.out.println("hier "+ j[1]);
            //System.out.println(JSONOverdrager.getTaak("c06lu7miempl8jk82livcv9mg0", 2));
            System.out.println(JSONOverdrager.getTakenVanProject("c06lu7miempl8jk82livcv9mg0",2));
            //System.out.println(JSONOverdrager.getTijdspanne("c06lu7miempl8jk82livcv9mg0",2));
            System.out.println(JSONOverdrager.getTijdspannesVanTaak("c06lu7miempl8jk82livcv9mg0", 2));       
            
            Validator v = Validator.getInstance();
            v.login("p", "p");
            Gebruiker g = CreatorFromJSON.createGebruiker(v.getSessionKey());            
            //JSONObject[] j = JSONOverdrager.getProjectenVanGebruiker(v.getSessionKey());                         
            Project[] p = CreatorFromJSON.createProjecten(v.getSessionKey());
            JSONOverdrager.getOpdrachtgeverVanGebruiker(v.getSessionKey());
            Opdrachtgever[] o = CreatorFromJSON.createOpdrachtgevers(v.getSessionKey());
            
            Taak[] t = CreatorFromJSON.createTaken(v.getSessionKey(),p[0].getID());
            Tijdspanne[] s = CreatorFromJSON.createTijdspannes(v.getSessionKey(), t[0].getID());
            
        } catch (JSONException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }*/
               
    }
    
}
