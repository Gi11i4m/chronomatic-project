package be.artesis.timelog.gui;

import be.artesis.timelog.model.Validator;

/**
 * @author Gilliam
 */
public class Main {

    public static void main(String args[]) {
    	Validator validator = Validator.getInstance();
        GUIForm gui = new GUIForm(validator);
        LoginDialog login = new LoginDialog(gui, validator);
        login.setVisible(true);
       /* if (login.getResult()) {
            gui.setLocationRelativeTo(login);
            gui.setVisible(true);
        } else {
            gui.dispose();
        }*/
    }
}
