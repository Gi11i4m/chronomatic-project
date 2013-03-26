package be.artesis.timelog.gui;

import java.lang.reflect.InvocationTargetException;

import be.artesis.timelog.model.Validator;
import be.artesis.timelog.secure.WinRegistry;

public class Main {

    public static void main(String args[]) {
    	Validator validator = Validator.getInstance();
        GUIForm gui = new GUIForm(validator);
        LoginForm login = new LoginForm(gui, validator);
        
        
        
		String username;
		String password;
		try {
			username = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "username");
			password = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "password");
		
			if(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin").equals("true")) {
				login.login(username, password);
				//System.out.println(username+" "+ WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic","password"));
			}
			else {
				login.setVisible(true);
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
    }
}
