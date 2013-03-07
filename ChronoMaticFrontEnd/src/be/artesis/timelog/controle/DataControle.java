package be.artesis.timelog.controle;


import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataControle {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatNaam(String naam) {
        if (naam.contains(" ")) {
            StringBuilder s = new StringBuilder();
            String delen[] = naam.split(" ");
            for (int i = 0; i < delen.length; i++) {
                s.append(formatNaamDeel(delen[i]));
                if (i != delen.length - 1) {
                    s.append(" ");
                }
            }
            return s.toString();
        } else {
            return formatNaamDeel(naam);
        }
    }

    //returns de naam met correcte hoofdletters
    private static String formatNaamDeel(String naamdeel) {
        if (naamdeel.contains("-")) {
            StringBuilder s = new StringBuilder();
            String delen[] = naamdeel.split("-");
            for (int i = 0; i < delen.length; i++) {
                s.append(formatDeel(delen[i]));
                if (i != delen.length - 1) {
                    s.append("-");
                }
            }
            return s.toString();
        } else {
            return formatDeel(naamdeel);
        }
    }

    private static String formatDeel(String deel) {
        return deel.substring(0, 1).toUpperCase().concat(deel.substring(1, deel.length()).toLowerCase());
    }

    //Controle op letters en '-'
    public static boolean persoonNaamCorrect(String naam) {
        if (naam != null && naam.length() != 0 && naam.matches("[A-z]+(-[A-z]+)*")) {
            return true;
        } else {
            return false;
        }
    }

    //Controle op letters, cijfers, spaties, '-' en '.' (nog aan te passen)
    //anders methode naam?
    public static boolean naamCorrect(String naam) {
        if (naam != null && naam.length() != 0 && naam.matches("[A-z0-9\\s-.]+")) {
            return true;
        } else {
            return false;
        }
    }

    //Controle op enkel cijfers
    public static boolean telefoonCorrect(String nummer) {
        if (nummer != null && nummer.length() != 0 && nummer.matches("[0-9/.-]*")) {
            return true;
        } else {
            return false;
        }
    }

    //Controle 'xxx'@'xxx'.'xxx'
    public static boolean emailCorrect(String email) {
        if (email != null && email.length() != 0 && email.matches("[A-z0-9_.]+([A-z0-9])*@[A-z0-9]+\\.[A-z]+")) {
            return true;
        } else {
            return false;
        }
    }
    //Controleert of de datum in het gespecifieerd formaat staat
    public static boolean datumCorrect(String datum) {
        if (datum != null && datum.length() != 0) {
            try {
                sdf.parse(datum);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else{
            return false;
        }
    }


    //Returns of de datum op vandaag of later valt
    public static boolean datumVandaagOfLater(Date datum) {
        return datum.compareTo(new Date()) >= 0;
    }

    //controleert of de naam al in een lijst van namen voorkomt
    public static boolean naamBestaat(String[] names, String naam) {
        for (String name : names) {
            if (name == naam) {
                return true;
            }
        }
        return false;
    }

    //controle lengte, hoofdletter, cijfers, speciale tekens
    //sterkte op 10
    public static int passwoordSterkte(String password) {
        int sterkte = 0;
        if (password.length() > 4) {
            sterkte += 4;

            if (password.matches(".*[0-9]+.*")) {
                sterkte += 3;
            }

            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    sterkte += 2;
                    break;
                }
            }

            if (password.matches(".*\\W+.*")) {
                sterkte += 1;
            }
        }
        return sterkte;
    }
}
