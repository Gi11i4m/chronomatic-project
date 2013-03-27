package be.artesis.timelog.gui;

import java.lang.reflect.InvocationTargetException;

import be.artesis.timelog.model.Validator;
import be.artesis.timelog.secure.WinRegistry;

public class Main {

    public static void main(String args[]) {
    	
    	/*
    	if(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin") != null) {
    			
    		WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin");
    		WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "username");
    		WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "password");
    	}
    	*/
    	
    	
    	Validator validator = Validator.getInstance();
        GUIForm gui = new GUIForm(validator);
        LoginForm login = new LoginForm(gui, validator);
        
        
        // Auto Login
		String username;
		String password;
		try {
			username = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "username");
			password = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "password");
			
			String autologin = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin");
			
			if(autologin != null) {
				if(autologin.equals("intern")) {
					login.login(username, password);
				}
				else if(autologin.equals("extern")) {
					login.loginExtern(username);
				}
				else {
					login.setVisible(true);
				}
			}
			else {
				login.setVisible(true);
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
    }
}
