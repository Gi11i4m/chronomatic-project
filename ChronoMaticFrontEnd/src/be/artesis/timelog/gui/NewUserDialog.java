package be.artesis.timelog.gui;

import java.awt.BorderLayout;

import be.artesis.timelog.controller.InserterLocal;
import be.artesis.timelog.model.CheckExistingUsernames;
import be.artesis.timelog.view.DataControle;
import be.artesis.timelog.view.DataInputException;
import javax.swing.JOptionPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

/**
 *
 * @author Gilliam
 */
public class NewUserDialog extends javax.swing.JPanel {

    /**
     * Creates new form NewUserDialog
     * @param parent
     * @param modal
     */
	LoginDialog parent;
    public NewUserDialog(LoginDialog parent) {
    	this.parent = parent;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	this.setLayout(null);

        firstNameJLabel = new javax.swing.JLabel();
        nameJLabel = new javax.swing.JLabel();
        usernameJLabel = new javax.swing.JLabel();
        emailJLabel = new javax.swing.JLabel();
        usernameJTextField = new javax.swing.JTextField();
        nameJTextField = new javax.swing.JTextField();
        firstNameJTextField = new javax.swing.JTextField();
        registerJButton = new javax.swing.JButton();
        passwordJLabel = new javax.swing.JLabel();
        passwordRepeatJLabel = new javax.swing.JLabel();
        passwordJPasswordField = new javax.swing.JPasswordField();
        passwordRepeatJPasswordField = new javax.swing.JPasswordField();
        passwordStrengthJLabel = new javax.swing.JLabel("");

        firstNameJLabel.setText("Voornaam");
        nameJLabel.setText("Naam");
        usernameJLabel.setText("Gebruikersnaam");
        emailJLabel.setText("Email");
        registerJButton.setText("Registreer");
        registerJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerJButtonClicked(evt);
            }
        });

        passwordJLabel.setText("Paswoord");
        passwordRepeatJLabel.setText("Herhaal paswoord");
        passwordJPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordJPasswordFieldKeyReleased(evt);
            }
        });
        
        this.add(firstNameJLabel);
        this.add(nameJLabel);
        this.add(usernameJLabel);
        this.emailJTextField = new javax.swing.JTextField();
        this.add(emailJTextField);
        this.add(emailJLabel);
        this.add(usernameJTextField);
        this.add(nameJTextField);
        this.add(firstNameJTextField);
        this.add(registerJButton);
        this.add(passwordJLabel);
        this.add(passwordRepeatJLabel);
        this.add(passwordJPasswordField);
        this.add(passwordRepeatJPasswordField);
        this.add(passwordStrengthJLabel);
        
        this.firstNameJLabel.setBounds(48,74,112,25);
        this.nameJLabel.setBounds(48,179,112,25);
        this.usernameJLabel.setBounds(370,74,112,25);
        this.emailJTextField.setBounds(48,331,200,25);
        this.emailJLabel.setBounds(48,283,112,25);
        this.usernameJTextField.setBounds(370,112,200,25);
        this.nameJTextField.setBounds(48,217,200,25);
        this.firstNameJTextField.setBounds(48,112,200,25);
        this.registerJButton.setBounds(48,436,200,25);
        this.passwordJLabel.setBounds(370,179,112,25);
        this.passwordRepeatJLabel.setBounds(370,283,112,25);
        this.passwordJPasswordField.setBounds(370,217,200,25);
        this.passwordRepeatJPasswordField.setBounds(370,331,200,25);
        this.passwordStrengthJLabel.setBounds(458,397,112,25);
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{firstNameJTextField, nameJTextField, emailJTextField, usernameJTextField, passwordJPasswordField, registerJButton, passwordRepeatJPasswordField}));
    }

    private void registerJButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerJButtonClicked
        // maak een nieuwe gebruiker aan aan de hand van volgende velden:
        try {
            String firstName = firstNameJTextField.getText();
            String name = nameJTextField.getText();
            String username = usernameJTextField.getText();
            String email = emailJTextField.getText();
            String password = new String(passwordJPasswordField.getPassword());
            String repeatPassword = new String(passwordRepeatJPasswordField.getPassword());
            if (!DataControle.persoonNaamCorrect(firstName) || !DataControle.persoonNaamCorrect(name)) {
                throw new DataInputException("Name contains illegal charaters");
            }
            if(!DataControle.naamCorrect(username)){
                throw new DataInputException("Username contains illegal characters");
            }
            //check of username al bestaat
            if (CheckExistingUsernames.check(email)) {
            	throw new DataInputException("Username already exists");
				
			}
            Inserter.CreateUser(firstName, name, username, email, password);
            // 
            //}
            if (!password.equals(repeatPassword)) {
                throw new DataInputException("Passwords do not match");
            }
            if (!DataControle.emailCorrect(email)) {
                throw new DataInputException("Wrong email format");
            }
            
            InserterServer.CreateUser(name, firstName, username, password, email);
            
            JOptionPane.showMessageDialog(this, "Your account has been created!");
            
            parent.displayTab("BASISPANEL");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void passwordJPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordJPasswordFieldKeyReleased
        passwordStrengthJLabel.setText("Strength: " + DataControle.passwoordSterkte(passwordJPasswordField.getPassword()));
    }//GEN-LAST:event_passwordJPasswordFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailJLabel;
    private javax.swing.JTextField emailJTextField;
    private javax.swing.JLabel firstNameJLabel;
    private javax.swing.JTextField firstNameJTextField;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JPasswordField passwordJPasswordField;
    private javax.swing.JLabel passwordRepeatJLabel;
    private javax.swing.JPasswordField passwordRepeatJPasswordField;
    private javax.swing.JLabel passwordStrengthJLabel;
    private javax.swing.JButton registerJButton;
    private javax.swing.JLabel usernameJLabel;
    private javax.swing.JTextField usernameJTextField;
    // End of variables declaration//GEN-END:variables
}
