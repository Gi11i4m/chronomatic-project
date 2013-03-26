package be.artesis.timelog.gui;

import be.artesis.timelog.model.Validator;

public class Main {

    public static void main(String args[]) {
    	Validator validator = Validator.getInstance();
        GUIForm gui = new GUIForm(validator);
        LoginForm login = new LoginForm(gui, validator);
        login.setVisible(true);
    }
}
