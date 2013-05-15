package be.artesis.timelog.gui;

import java.awt.Cursor;
import java.io.IOException;

import javax.swing.JOptionPane;

import be.artesis.timelog.model.Connection;
import be.artesis.timelog.model.WebserviceException;

@SuppressWarnings("serial")
public class ResetPasswordPanel extends javax.swing.JPanel {

	LoginForm parent;
    public ResetPasswordPanel(LoginForm parent) {
    	this.parent = parent;
        initComponents();
    }
	private void initComponents() {
		this.setLayout(null);
		usernameJLabel = new javax.swing.JLabel();
		usernameJTextField = new javax.swing.JTextField();
		goBackJButton = new javax.swing.JButton();
		sendJButton = new javax.swing.JButton();
		Text1JLabel = new javax.swing.JLabel();
		
		usernameJLabel.setText("Username:");
		goBackJButton.setText("Go back");
		sendJButton.setText("Send e-mail");
		Text1JLabel.setText("To reset your password, enter the username you use to sign in to Chronomatic.");
		
		this.add(usernameJLabel);
		this.add(usernameJTextField);
		this.add(goBackJButton);
		this.add(sendJButton);
		this.add(Text1JLabel);
		
		this.usernameJLabel.setBounds(48,52,112,25);
        this.usernameJTextField.setBounds(48,90,200,25);
        this.goBackJButton.setBounds(48,436,112,25);
        this.sendJButton.setBounds(48,140,112,25);
        this.Text1JLabel.setBounds(48,20,600,25);
        
		sendJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					sendJButtonClicked(evt);
				} catch (IOException | WebserviceException e) {
					e.printStackTrace();
				}
            }
        });
        
        goBackJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackJButtonClicked(evt);
            }
        });
	}
	
	private void sendJButtonClicked(java.awt.event.MouseEvent evt) throws IOException, WebserviceException {
		try {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection.execute("gebruiker/resetpassword/" + usernameJTextField.getText());
			JOptionPane.showMessageDialog(this, "An email has been sent to the accounts email address.");
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error! We have no clue what's wrong. \nJust go play outside and wait for a while");
		}
		finally {
			  this.setCursor(Cursor.getDefaultCursor());
			  parent.displayTab("BASISPANEL");
		}
    }
	
	private void goBackJButtonClicked(java.awt.event.MouseEvent evt) {
    	parent.displayTab("BASISPANEL");
    }
	
	private javax.swing.JLabel usernameJLabel;
	private javax.swing.JTextField usernameJTextField;
	private javax.swing.JButton goBackJButton;
	private javax.swing.JButton sendJButton;
	private javax.swing.JLabel Text1JLabel;
}
