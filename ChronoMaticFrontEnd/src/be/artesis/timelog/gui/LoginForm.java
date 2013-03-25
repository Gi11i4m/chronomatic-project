package be.artesis.timelog.gui;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import be.artesis.timelog.controller.Inserter;
import be.artesis.timelog.externAuth.*;
import be.artesis.timelog.model.ExistingUsernames;
import be.artesis.timelog.model.CreatorFromJSON;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.secure.MD5Generator;
import be.artesis.timelog.secure.WinRegistry;
import be.artesis.timelog.view.DataInputException;
import be.artesis.timelog.view.Gebruiker;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
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
import javax.swing.border.Border;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class LoginForm extends javax.swing.JFrame implements ActionListener {
	@SuppressWarnings("unchecked")
	private static final long serialVersionUID = 1L;
	public Validator validator;
	private SocialMedia social;
	private java.awt.Frame parent;

	private CardLayout layout;
	private Container pane;
	private JFXPanel basisPanel;
	private JFXPanel browserPanel;
	private JPanel newUserPanel;

	private JTextField usernameJTextField;
	private JPasswordField passwordJPasswordField;
	private JLabel usernameJLabel;
	private JLabel paswoordJLabel;
	private JButton aanmeldenButton;
	private JLabel newAccountJLabel;
	private JLabel socialMediaJLabel;
	private JButton googleJButton;
	private JButton facebookJButton;
	private JButton browserGoBackJButton;
	private JButton microsoftJButton;
	private JButton twitterJButton;
	private JButton linkedinJButton;
	private ImageIcon googleIcon;
	private ImageIcon facebookIcon;
	private ImageIcon microsoftIcon;
	private ImageIcon twitterIcon;
	private ImageIcon linkedinIcon;

	public LoginForm(java.awt.Frame parent, Validator validator) {
		setResizable(false);
		this.parent = parent;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.validator = validator;
		this.setTitle("Login");
		this.setSize(720, 520);

		// set dialog in center
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		
		pane = getContentPane();
		layout = new CardLayout();
		pane.setBackground(Color.WHITE);
		pane.setLayout(layout);

		basisPanel = new JFXPanel();
		browserPanel = new JFXPanel();
		newUserPanel = new NewUserPanel(this);
		pane.add(basisPanel, "BASISPANEL");
		pane.add(browserPanel, "BROWSERPANEL");
		pane.add(newUserPanel, "NEWUSERPANEL");

		initComponents();

		this.displayTab("BASISPANEL");
	}

	public void displayTab(String name) {
		layout.show(pane, name);
	}

	public void loginExtern(String accessToken) {
		try {
			// Facebook moet geen Access token aanvragen, de rest wel
			if (!social.toString().equals("facebook")) {
				accessToken = AccessToken.request(accessToken, social);
			}

			JSONObject userInfoJSONObj = GetUserInfo.request(accessToken, social);
			
			String email = userInfoJSONObj.getString("email");

			// als gebruiker nog niet bestaat..
			if (ExistingUsernames.check(email)) {
				Inserter.CreateUserExtern(userInfoJSONObj.getString("naam"), userInfoJSONObj.getString("voornaam"), email);
			}

			if (validator.loginExtern(email)) {
				loadUserData();
				parent.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Login failed");
			}

		} catch (IOException | JSONException | WebserviceException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

	public void login() {
		try {
            if (usernameJTextField.getText().equals("")) {
                
                    UserInterface.setUser(new Gebruiker("Flebus", "Gilliam", "Gi11i4m", "gi11i4m@gmail.com")); // tijdelijke user
                    
						UserInterface.getUser().addOpdrachtgever(new Opdrachtgever("Flebus", "Gilliam", "Mot-art", "blabla", "0475", 456));
					
                    UserInterface.getUser().addOpdrachtgever(new Opdrachtgever("Schouten", "Girmi", "Artesis", "bla", "0478", 457));
                    UserInterface.getUser().addProject(new Project("Test project 1", 456, 1343059472, 1453059472));
                    UserInterface.getUser().addProject(new Project("Test project 2", 457, 1243059472, 1553059472));
                    UserInterface.getUser().getProjects().get(1).addTaak(new Taak("Test taak", 1343059472, 1453059472, ""));
                this.dispose();
                
            } else if (validator.login(usernameJTextField.getText(), new String(passwordJPasswordField.getPassword()))) {
            	loadUserData();
            	this.dispose();
            	/*try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignore) {}
                */
                parent.setVisible(true);
            } else {

            }
		} catch (DataInputException | HeadlessException | IOException | JSONException | WebserviceException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error connecting to server");
			this.dispose();
		}

	}

	private void loadUserData() {
		try {
			UserInterface.setUser(CreatorFromJSON.createGebruiker(validator.getSessionKey()));
			UserInterface.getUser().setProjects(CreatorFromJSON.createProjecten(validator.getSessionKey()));
			UserInterface.getUser().setOpdrachtgevers(CreatorFromJSON.createOpdrachtgevers(validator.getSessionKey()));

			for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
				UserInterface.getUser().getProject(i).addTaken(CreatorFromJSON.createTaken(validator.getSessionKey(), UserInterface.getUser().getProject(i).getId()));
			}

			for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
				// System.out.println("project: "+UserControl.getUser().getProject(i));
				for (int j = 0; j < UserInterface.getUser().getProject(i).getTaken().size(); j++) {
					UserInterface.getUser().getProject(i).getTaak(j).addTotaleTijd(CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserInterface.getUser().getProject(i).getTaak(j).getID(), false));
					UserInterface.getUser().getProject(i).getTaak(j).addTotaleTijd(CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserInterface.getUser().getProject(i).getTaak(j).getID(), true));
					// System.out.println("taak "
					// +UserControl.getUser().getProject(i).getTaak(j).getID()+
					// ": " + UserControl.getUser().getProject(i).getTaak(j));
					// System.out.println("gewerkt: " +
					// UserControl.getUser().getProject(i).getTaak(j).getGewerkteTijd());
					// System.out.println("pauze: "+UserControl.getUser().getProject(i).getTaak(j).getPauze());
				}
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents() {

		

		JFXPanel loading = new JFXPanel();
		pane.add(loading, "loading");
		
		

		/*ImageIcon loadingGif = new ImageIcon();
		loadingGif = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/loading.gif")));
		JLabel loadingJLabel = new JLabel(loadingGif);
		loadingJLabel.setBounds(431, 124, 200, 200);
		browserPanel.add(loadingJLabel);*/
		
		
		
		googleIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/google.png")));
		facebookIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/facebook.png")));
		microsoftIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/microsoft.png")));
		twitterIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/twitter.png")));
		linkedinIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/be/artesis/timelog/gui/icons/linkedin.png")));


		// initialize fields
		usernameJLabel = new JLabel("Gebruikersnaam:");
		paswoordJLabel = new JLabel("Wachtwoord:");
		aanmeldenButton = new JButton("Aanmelden");
		usernameJTextField = new JTextField("p");
		passwordJPasswordField = new JPasswordField("p");
		newAccountJLabel = new JLabel("Of maak een account aan");
		socialMediaJLabel = new JLabel("Of meld u aan bij");
		browserGoBackJButton = new JButton("Aanmelden met een andere account");
		googleJButton = new JButton(googleIcon);
		googleJButton.setName("google");
		facebookJButton = new JButton(facebookIcon);
		facebookJButton.setName("facebook");
		microsoftJButton = new JButton(microsoftIcon);
		microsoftJButton.setName("microsoft");
		linkedinJButton = new JButton(linkedinIcon);
		linkedinJButton.setName("linkedin");
		//twitterJButton = new JButton(twitterIcon);
		//twitterJButton.setName("twitter");
		
		usernameJLabel.setBounds(31, 124, 138, 16);
		paswoordJLabel.setBounds(31, 227, 138, 16);
		aanmeldenButton.setBounds(31, 327, 107, 25);
		usernameJTextField.setBounds(31, 153, 247, 22);
		passwordJPasswordField.setBounds(31, 256, 247, 22);
		newAccountJLabel.setBounds(140, 331, 160, 16);
		socialMediaJLabel.setBounds(464, 124, 130, 20);
		browserGoBackJButton.setBounds(0, 462, 240, 25);
		googleJButton.setBounds(520, 175, 48, 48);
		facebookJButton.setBounds(520, 238, 48, 48);
		microsoftJButton.setBounds(520, 301, 48, 48);
		linkedinJButton.setBounds(520, 368, 48, 48);
		//twitterJButton.setBounds(493, 334, 48, 48);
		
		// Backgrounds socialmedia buttons
		googleJButton.setBackground(Color.WHITE);
		facebookJButton.setBackground(Color.WHITE);
		microsoftJButton.setBackground(Color.WHITE);
		linkedinJButton.setBackground(Color.WHITE);
		//twitterJButton.setBackground(Color.WHITE);
		
		// set label fonts
		socialMediaJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paswoordJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameJTextField.setColumns(10);

		// set action listener
		googleJButton.addActionListener(this);
		facebookJButton.addActionListener(this);
		microsoftJButton.addActionListener(this);
		linkedinJButton.addActionListener(this);
		//twitterJButton.addActionListener(this);

		// add to panel
		basisPanel.add(usernameJLabel);
		basisPanel.add(paswoordJLabel);
		basisPanel.add(aanmeldenButton);
		basisPanel.add(usernameJTextField);
		basisPanel.add(passwordJPasswordField);
		basisPanel.add(newAccountJLabel);
		basisPanel.add(socialMediaJLabel);
		basisPanel.add(googleJButton);
		basisPanel.add(facebookJButton);
		basisPanel.add(microsoftJButton);
		//basisPanel.add(twitterJButton);
		basisPanel.add(linkedinJButton);
		basisPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{usernameJTextField, passwordJPasswordField, aanmeldenButton, googleJButton, facebookJButton, microsoftJButton, twitterJButton, linkedinJButton, newAccountJLabel}));
		browserPanel.add(browserGoBackJButton);

		// Action listeners
		try {
			aanmeldenButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					login();

					//Thread voor het loading gifke
					/*Thread loginLoadingThread = new Thread() {
						public void run() {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									displayTab("loading");
								}
							});
							login();
						}
					};*/
					
					//loginLoadingThread.start();
					
					//parent.setVisible(true);
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			//JOptionPane.showMessageDialog(this, "Login mislukt");			
		}

		browserGoBackJButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Platform.exit();
				displayTab("BASISPANEL");
			}
		});

		newAccountJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				displayTab("NEWUSERPANEL");
			}
		});
		
		//Maak de registry key voor de gebruikersnaam & paswoord
		try {
			WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic");
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
	
	

	@Override
	public void actionPerformed(ActionEvent evt) {

		JButton sender = (JButton) evt.getSource();

        switch (sender.getName()) {
            case "google":  social = new Google();
                     break;
            case "facebook":  social = new Facebook();
                     break;
            case "microsoft":  social = new Microsoft();
                     break;
            case "twitter":  social = new Twitter();
                     break;
            case "linkedin":  social = new Linkedin();
                     break;
            default: social = new Google();
                     break;
        }
        //this.displayTab("loading");
        
        loginProviderJButtonClicked();
	}
	
	private void loginProviderJButtonClicked() {
		AuthBrowser browser = new AuthBrowser(this, social);
		browser.buildUrl();
		browser.initBrowser(browserPanel);
		this.displayTab("BROWSERPANEL");
		//this.setSize(820, 620);
	}
}