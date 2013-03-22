package be.artesis.timelog.gui;

/**
 * @author Gilliam
 */
public class Main {

    public static void main(String args[]) {
        GUIForm gui = new GUIForm();        
        if (gui.login.getResult()) {
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        } else {
            gui.dispose();
        }
    }
}