package be.artesis.timelog.gui;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import be.artesis.timelog.controller.Inserter;
import be.artesis.timelog.externAuth.AuthBrowser;
import be.artesis.timelog.externAuth.GetUserInfo;
import be.artesis.timelog.externAuth.RequestGoogleToken;
import be.artesis.timelog.model.CheckExistingUsernames;
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
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;

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
import java.awt.image.BufferedImage;

public class LoginDialog extends javax.swing.JDialog {
	@SuppressWarnings("unchecked")
	private static final long serialVersionUID = 1L;
	private boolean result;
	public Validator validator;
	private java.awt.Frame parent;

	private CardLayout layout;
	private Container pane;
	private JFXPanel basisPanel;
	private JFXPanel browserPanel;
	private final String BASISPANEL = "Basis";
	private final String BROWSERPANEL = "Browser";

	private JTextField usernameJTextField;
	private JPasswordField passwordJPasswordField;
	private JLabel usernameJLabel;
	private JLabel paswoordJLabel;
	private JButton aanmeldenButton;
	private JLabel newAccountJLabel;
	private JLabel thirdPartyJLabel;
	private JButton googleJButton;
	private JButton facebookJButton;
	private JButton browserGoBackJButton;
	private JButton microsoftJButton;

	public LoginDialog(java.awt.Frame parent, boolean modal, Validator validator) {
		super(parent, modal);
		setResizable(false);
		this.parent = parent;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.validator = validator;
		result = false;
		this.setTitle("Login");
		this.setSize(720, 520);

		// set dialog in center
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		initComponents();

		this.displayTab(BASISPANEL);
	}

	private void displayTab(String name) {
		layout.show(pane, name);
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	// ni meer dan?
	// private boolean loginOnServer(String username, char[] password) throws
	// ConnectException, IOException, JSONException, WebserviceException {
	// return validator.login(username, new String(password));
	// }

	public void maakExterneGebruiker(String authCode, String provider) {
		//try {
			System.out.println(authCode);
			/*
			if (provider.equals("Google")) {
				authCode = RequestGoogleToken.request(authCode);
			}
			String email = GetUserInfo.retreive(authCode, provider);
			if (CheckExistingUsernames.check(email)) {
				Inserter.CreateUserExtern("", "", email, "");
			}

			loginExtern(email);

		} catch (IOException | JSONException | WebserviceException e) {
			e.printStackTrace();
		}*/
	}

	public void loginExtern(String email) {
		try {
			if (validator.loginExtern(email)) {
				loadUserData();
				result = true;
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Login failed");
			}
		} catch (HeadlessException | IOException | JSONException
				| WebserviceException e) {
			// TODO Auto-generated catch block
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

                result = true;
                this.dispose();
                
            } else if (validator.login(usernameJTextField.getText(), new String(passwordJPasswordField.getPassword()))) {
            	loadUserData();
                result = true;
                this.dispose();
            } else {

            }
			if (usernameJTextField.getText().equals("")) {

					UserInterface.setUser(new Gebruiker("Flebus", "Gilliam",
							"Gi11i4m", "gi11i4m@gmail.com")); // tijdelijke user
					UserInterface.getUser().addOpdrachtgever(
							new Opdrachtgever("Flebus", "Gilliam", "Mot-art",
									"blabla", "0475", 456));
					UserInterface.getUser().addOpdrachtgever(
							new Opdrachtgever("Schouten", "Girmi", "Artesis",
									"bla", "0478", 457));
					UserInterface.getUser().addProject(
							new Project("Test project 1", 456, 1343059472,
									1453059472));
					UserInterface.getUser().addProject(
							new Project("Test project 2", 457, 1243059472,
									1553059472));
					UserInterface
							.getUser()
							.getProjects()
							.get(1)
							.addTaak(
									new Taak("Test taak", 1343059472,
											1453059472, ""));
				result = true;
				this.dispose();

			} else if (validator.login(usernameJTextField.getText(),
					new String(passwordJPasswordField.getPassword()))) {
				loadUserData();
				result = true;
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Login failed");
			}
			
		} catch (DataInputException | HeadlessException | IOException | JSONException | WebserviceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadUserData() {
		try {
			UserInterface.setUser(CreatorFromJSON.createGebruiker(validator
					.getSessionKey()));
			UserInterface.getUser().setProjects(
					CreatorFromJSON.createProjecten(validator.getSessionKey()));
			UserInterface.getUser().setOpdrachtgevers(
					CreatorFromJSON.createOpdrachtgevers(validator
							.getSessionKey()));

			for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
				UserInterface
						.getUser()
						.getProject(i)
						.setTaken(
								CreatorFromJSON.createTaken(
										validator.getSessionKey(),
										UserInterface.getUser().getProject(i)
												.getId()));
			}

			for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
				// System.out.println("project: "+UserControl.getUser().getProject(i));
				for (int j = 0; j < UserInterface.getUser().getProject(i)
						.getTaken().size(); j++) {
					UserInterface
							.getUser()
							.getProject(i)
							.getTaak(j)
							.setGewerkteTijd(
									CreatorFromJSON.createTijdspannes(
											validator.getSessionKey(),
											UserInterface.getUser()
													.getProject(i).getTaak(j)
													.getID(), false));
					UserInterface
							.getUser()
							.getProject(i)
							.getTaak(j)
							.setGewerkteTijd(
									CreatorFromJSON.createTijdspannes(
											validator.getSessionKey(),
											UserInterface.getUser()
													.getProject(i).getTaak(j)
													.getID(), true));
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

		pane = getContentPane();
		layout = new CardLayout();
		pane.setBackground(Color.WHITE);
		pane.setLayout(layout);

		// basisPanel.setLayout(null);

		basisPanel = new JFXPanel();
		browserPanel = new JFXPanel();
		pane.add(basisPanel, BASISPANEL);
		pane.add(browserPanel, BROWSERPANEL);

		JFXPanel loading = new JFXPanel();
		pane.add(loading, "loading");

		ImageIcon loadingGif = new ImageIcon();
		loadingGif = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(
						"/be/artesis/timelog/gui/icons/loading.gif")));
		JLabel loadingJLabel = new JLabel(loadingGif);
		loadingJLabel.setBounds(431, 124, 200, 200);
		loading.add(loadingJLabel);

		// initialize fields
		usernameJLabel = new JLabel("Gebruikersnaam:");
		usernameJLabel.setBounds(31, 124, 138, 16);
		paswoordJLabel = new JLabel("Wachtwoord:");
		paswoordJLabel.setBounds(31, 227, 138, 16);
		aanmeldenButton = new JButton("Aanmelden");
		aanmeldenButton.setBounds(31, 327, 107, 25);
		usernameJTextField = new JTextField("p");
		usernameJTextField.setBounds(31, 153, 247, 22);
		passwordJPasswordField = new JPasswordField("p");
		passwordJPasswordField.setBounds(31, 256, 247, 22);
		newAccountJLabel = new JLabel("Of maak een account aan");
		newAccountJLabel.setBounds(140, 331, 160, 16);
		thirdPartyJLabel = new JLabel("Of meld u aan bij");
		thirdPartyJLabel.setBounds(464, 125, 118, 16);
		googleJButton = new JButton("Google");
		googleJButton.setBounds(493, 175, 107, 25);
		facebookJButton = new JButton("Facebook");
		facebookJButton.setBounds(493, 228, 107, 25);
		browserGoBackJButton = new JButton("Aanmelden met een andere account");
		microsoftJButton = new JButton("Microsoft");
		microsoftJButton.setBounds(493, 281, 107, 25);

		// set label fonts
		usernameJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paswoordJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		browserGoBackJButton.setBounds(0, 462, 240, 25);

		usernameJTextField.setColumns(10);

		// add to panel
		basisPanel.add(usernameJLabel);
		basisPanel.add(paswoordJLabel);
		basisPanel.add(aanmeldenButton);
		basisPanel.add(usernameJTextField);
		basisPanel.add(passwordJPasswordField);
		basisPanel.add(newAccountJLabel);
		basisPanel.add(thirdPartyJLabel);
		basisPanel.add(googleJButton);
		basisPanel.add(facebookJButton);
		basisPanel.add(microsoftJButton);
		browserPanel.add(browserGoBackJButton);

		// Action listeners
		try {
			aanmeldenButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
	
					// Thread voor het loading gifke
					/*Thread loginLoadingThread = new Thread() {
						public void run() {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									displayTab("loading");
								}
							});*/
							login();
						//}
					//};
					//loginLoadingThread.start();
				}
			});
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			//JOptionPane.showMessageDialog(this, "Login mislukt");			
		}

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
		microsoftJButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginProviderJButtonClicked(evt);
			}
		});

		browserGoBackJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						Platform.exit();
						displayTab(BASISPANEL);
					}
		});
	}

	private void loginProviderJButtonClicked(ActionEvent evt) {
		AuthBrowser browser = new AuthBrowser();
		browser.initBrowser(this, browserPanel, evt.getActionCommand());
		this.displayTab(BROWSERPANEL);
	}
}
