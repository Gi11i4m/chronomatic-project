package be.artesis.timelog.gui;

import be.artesis.timelog.view.Gebruiker;
import be.artesis.timelog.view.Project;
/**
 *
 * @author Gilliam
 */
public class UserControl {

    private static Gebruiker user;
    private static int currentProjectIndex = -1;

    public static Gebruiker getUser() {
        return user;
    }

    public static void setUser(Gebruiker u) {
        user = u;
    }

    public static Project getCurrentProject() throws GUIException {
        if (currentProjectIndex == -1) {
            throw new GUIException("Please select a current project first");
        }
        return user.getProjects().get(currentProjectIndex);
    }

    public static void setCurrentProjectIndex(int i) {
        UserControl.currentProjectIndex = i;
    }

    public static int getCurrentProjectIndex() {
        return currentProjectIndex;
    }

    // Code verplaatst vanwege verwijderde methode
    // Taak taak = Creator.createTaak();
    // Inserter.inputTaak(validator.getSessionKey(), t);
}