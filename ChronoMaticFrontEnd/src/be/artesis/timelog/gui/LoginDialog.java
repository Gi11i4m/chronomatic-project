package be.artesis.timelog.gui;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JPanel;

import be.artesis.timelog.externAuth.AuthBrowser;
import be.artesis.timelog.model.CreatorFromJSON;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.DataInputException;
import be.artesis.timelog.view.Gebruiker;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.ConnectException;
import javax.swing.JOptionPane;
import org.json.JSONException;
//
import java.awt.Dimension;
import java.awt.Point;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginDialog extends javax.swing.JDialog {
	@SuppressWarnings("unchecked")

	
	private static final long serialVersionUID = 1L;
	private boolean result;
    public Validator validator;
    private java.awt.Frame parent;
    
	CardLayout layout;
	Container pane;
	JPanel basisPanel;
	JFXPanel browserPanel;
	private final String BASISPANEL = "Basis";
	private final String BROWSERPANEL = "Browser";
	private JTextField UsernameJTextField;
	
	private JPasswordField PasswordJPasswordField;
	private JLabel gebruikersnaamJLabel;
	private JLabel paswoordJLabel;
	private JButton aanmeldenButton;
	private JLabel newAccountJLabel;
	private JLabel ThirdPartyJLabel;
	private JButton googleJButton;
	private JButton facebookJButton;
	
	JButton browserGoBackJButton;
	
	public LoginDialog(java.awt.Frame parent, boolean modal, Validator validator) {
		super(parent, modal);
        setResizable(false);
        this.parent = parent;
        setLocationRelativeTo(parent);
        this.validator = validator;
        result = false;
		
        initComponents();
        
        this.displayTab(BASISPANEL);
	}
	
	private void displayTab(String name) {
		layout.show(this.getContentPane(), name);
    }
	
	public boolean getResult() {
        return result;
    }
	
	public void setResult(boolean result) {
        this.result = result;
    }
	
	private boolean loginOnServer(String username, char[] password) throws ConnectException, IOException, JSONException, WebserviceException {
        return validator.login(username, new String(password));
    }
	
	public void login() {
		try {
            if (gebruikersnaamJLabel.getText().equals("")) {
                try {
                    UserControl.setUser(new Gebruiker("Flebus", "Gilliam", "Gi11i4m", "gi11i4m@gmail.com")); // tijdelijke user
                    UserControl.getUser().addOpdrachtgever(new Opdrachtgever("Flebus", "Gilliam", "Mot-art", "blabla", "0475", 456));
                    UserControl.getUser().addOpdrachtgever(new Opdrachtgever("Schouten", "Girmi", "Artesis", "bla", "0478", 457));
                    UserControl.getUser().addProject(new Project("Test project 1", 456, 1343059472, 1453059472));
                    UserControl.getUser().addProject(new Project("Test project 2", 457, 1243059472, 1553059472));
                    UserControl.getUser().getProjects().get(1).addTaak(new Taak("Test taak", 1343059472, 1453059472, ""));
                } catch (DataInputException ex) {
                    //JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                result = true;
                this.dispose();
                
            } else if (loginOnServer(gebruikersnaamJLabel.getText(), PasswordJPasswordField.getPassword())) {
                UserControl.setUser(CreatorFromJSON.createGebruiker(validator.getSessionKey()));
                UserControl.getUser().setProjects(CreatorFromJSON.createProjecten(validator.getSessionKey()));
                UserControl.getUser().setOpdrachtgevers(CreatorFromJSON.createOpdrachtgevers(validator.getSessionKey()));                
                
                for(int i = 0; i < UserControl.getUser().getProjects().size(); i++){
                    UserControl.getUser().getProject(i).setTaken(CreatorFromJSON.createTaken(validator.getSessionKey(), UserControl.getUser().getProject(i).getId()));
                }
                
                for(int i = 0; i < UserControl.getUser().getProjects().size(); i++){
                    System.out.println("project: "+UserControl.getUser().getProject(i));
                    for(int j = 0; j <UserControl.getUser().getProject(i).getTaken().size(); j++){
                        UserControl.getUser().getProject(i).getTaak(j).setGewerkteTijd( CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserControl.getUser().getProject(i).getTaak(j).getID(),false));                     
                        UserControl.getUser().getProject(i).getTaak(j).setPauze( CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserControl.getUser().getProject(i).getTaak(j).getID(),true));                     
                        System.out.println("taak " +UserControl.getUser().getProject(i).getTaak(j).getID()+ ": " + UserControl.getUser().getProject(i).getTaak(j));
                        System.out.println("gewerkt: " + UserControl.getUser().getProject(i).getTaak(j).getGewerkteTijd());
                        System.out.println("pauze: "+UserControl.getUser().getProject(i).getTaak(j).getPauze());                       
                    }
                }
                result = true;
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        } catch (JSONException | HeadlessException | IOException | WebserviceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
	}
	
	private void initComponents() {
		this.setTitle("Login");
		this.setSize(720, 520);
		pane = getContentPane();
		layout = new CardLayout();
		pane.setBackground(Color.WHITE);
		pane.setLayout(layout);
		
		basisPanel = new JPanel();
		basisPanel.setLayout(null);
		browserPanel = new JFXPanel();
		pane.add(basisPanel, BASISPANEL);
		pane.add(browserPanel, BROWSERPANEL);
		
		//initialize fields
		gebruikersnaamJLabel = new JLabel("Gebruikersnaam:");
		paswoordJLabel = new JLabel("Paswoord:");
		aanmeldenButton = new JButton("Aanmelden");
		UsernameJTextField = new JTextField();
		PasswordJPasswordField = new JPasswordField();
		newAccountJLabel = new JLabel("Of maak een account aan");
		ThirdPartyJLabel = new JLabel("Of meld u aan bij");
		googleJButton = new JButton("Google");
		facebookJButton = new JButton("Facebook");
		browserGoBackJButton = new JButton("Aanmelden met een andere account");
		
		// set label fonts
		gebruikersnaamJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paswoordJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		// set bounds
		gebruikersnaamJLabel.setBounds(31, 124, 138, 16);
		paswoordJLabel.setBounds(31, 227, 138, 16);
		aanmeldenButton.setBounds(31, 327, 97, 25);
		UsernameJTextField.setBounds(31, 153, 247, 22);
		PasswordJPasswordField.setBounds(31, 256, 247, 22);
		newAccountJLabel.setBounds(140, 331, 160, 16);
		ThirdPartyJLabel.setBounds(464, 125, 118, 16);
		googleJButton.setBounds(493, 175, 107, 25);
		facebookJButton.setBounds(493, 228, 107, 25);
		browserGoBackJButton.setBounds(0, 462, 240, 25);
		
		UsernameJTextField.setColumns(10);
		
		// add to panel
		basisPanel.add(gebruikersnaamJLabel);
		basisPanel.add(paswoordJLabel);
		basisPanel.add(aanmeldenButton);
		basisPanel.add(UsernameJTextField);
		basisPanel.add(PasswordJPasswordField);
		basisPanel.add(newAccountJLabel);
		basisPanel.add(ThirdPartyJLabel);
		basisPanel.add(googleJButton);
		basisPanel.add(facebookJButton);
		browserPanel.add(browserGoBackJButton);
		
		googleJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	loginProviderJButtonClicked(evt);
            }
        });

		facebookJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	loginProviderJButtonClicked(evt);
            }
        });
		
		
	}
	
	private void loginProviderJButtonClicked(ActionEvent evt) {
		AuthBrowser browser = new AuthBrowser();
		browser.initBrowser(this, browserPanel, evt.getActionCommand());
		this.displayTab(BROWSERPANEL);
	}
}
