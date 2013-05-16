package be.artesis.timelog.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import be.artesis.timelog.controller.InserterServer;
import be.artesis.timelog.externAuth.AccessToken;
import be.artesis.timelog.externAuth.AuthBrowser;
import be.artesis.timelog.externAuth.Facebook;
import be.artesis.timelog.externAuth.GetUserInfo;
import be.artesis.timelog.externAuth.Google;
import be.artesis.timelog.externAuth.Linkedin;
import be.artesis.timelog.externAuth.Microsoft;
import be.artesis.timelog.externAuth.SocialMedia;
import be.artesis.timelog.model.CreatorFromJSON;
import be.artesis.timelog.model.ExistingUsernames;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.secure.MD5Generator;
import be.artesis.timelog.secure.WinRegistry;

public class LoginForm extends javax.swing.JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public Validator validator;
	private SocialMedia social;
	private java.awt.Frame parent;

	private CardLayout layout;
	private Container pane;
	private JFXPanel basisPanel;
	private JFXPanel browserPanel;
	private JPanel newUserPanel;
	private JPanel resetPassPanel;

	private JTextField usernameJTextField;
	private JPasswordField passwordJPasswordField;
	private JLabel usernameJLabel;
	private JLabel paswoordJLabel;
	private JButton aanmeldenButton;
	private JLabel newAccountJLabel;
	private JLabel resetPassJLabel;
	private JLabel socialMediaJLabel;
	private JButton googleJButton;
	private JButton facebookJButton;
	private JLabel browserGoBackButtonJLabel;
	private JButton microsoftJButton;
	//private JButton twitterJButton;
	private JButton linkedinJButton;
	private JLabel logoLabel;
	private ImageIcon logo;
	private ImageIcon googleIcon;
	private ImageIcon facebookIcon;
	private ImageIcon microsoftIcon;
	//private ImageIcon twitterIcon;
	private ImageIcon linkedinIcon;
	private JCheckBox autoLoginInternCheckBox;
	private JCheckBox autoLoginExternCheckBox;

	public LoginForm(java.awt.Frame parent, Validator validator) {
		setResizable(false);
		this.parent = parent;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.validator = validator;
		this.setTitle("Chronomatic Login");
		this.setSize(720, 520);

		// set dialog in center screen
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
		resetPassPanel = new ResetPasswordPanel(this);

		pane.add(basisPanel, "BASISPANEL");
		pane.add(browserPanel, "BROWSERPANEL");
		pane.add(newUserPanel, "NEWUSERPANEL");
		pane.add(resetPassPanel, "RESETPASSPANEL");

		initComponents();

	}

	public void displayTab(String name) {
		layout.show(pane, name);
	}

	public void login(String username, String password) {

		try {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			// Maak key in registry
			WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic");

			if (validator.login(username, password)) {
				loadUserData();

				//paswoord opslaan
				if (autoLoginInternCheckBox.isSelected()) {
					WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin", "intern");
					WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "username", usernameJTextField.getText());
					WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "password", password);
				}
				this.setVisible(false);

				parent.setVisible(true);
			} else {

			}
		} catch (HeadlessException | IOException | JSONException | WebserviceException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error! We have no clue what's wrong. \nJust go play outside and wait for a while");
		} finally {
			this.setCursor(Cursor.getDefaultCursor());
		}

	}

	public void generateLoginExtern(String accessToken) {
		JSONObject userInfoJSONObj = null;
		try {
			// Facebook moet geen Access token aanvragen, de rest wel (omwille van oude versie Oauth)
			if (!social.toString().equals("facebook")) {
				accessToken = AccessToken.request(accessToken, social);
			}
			userInfoJSONObj = GetUserInfo.request(accessToken, social);

			loginExtern(userInfoJSONObj);
		} catch (IOException | JSONException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

	public void loginExtern(JSONObject userInfoJSONObj) {
		try {
			// Maak key
			WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic");

			String email = userInfoJSONObj.getString("email");
			// als gebruiker nog niet bestaat..

			if (!ExistingUsernames.check(email, email)) {
				InserterServer.CreateUserExtern(userInfoJSONObj.getString("naam"), userInfoJSONObj.getString("voornaam"), email);
			}

			if (validator.loginExtern(email)) {
				loadUserData();

				if (autoLoginExternCheckBox.isSelected()) {
					WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "autologin", "extern");
					WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic", "username", email);
				}
				parent.setVisible(true);
				//this.dispose();
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Login failed");
			}

		} catch (IOException | JSONException | WebserviceException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void loadUserData() {
		try {
			UserInterface.setUser(CreatorFromJSON.createGebruiker(validator.getSessionKey()));
			UserInterface.getUser().setProjects(CreatorFromJSON.createProjecten(validator.getSessionKey()));

			if (UserInterface.getUser().getProject(0) != null) {
				UserInterface.getUser().setOpdrachtgevers(CreatorFromJSON.createOpdrachtgevers(validator.getSessionKey()));

				for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
					UserInterface.getUser().getProject(i).addTaken(CreatorFromJSON.createTaken(validator.getSessionKey(), UserInterface.getUser().getProject(i).getId()));
				}

				for (int i = 0; i < UserInterface.getUser().getProjects().size(); i++) {
					for (int j = 0; j < UserInterface.getUser().getProject(i).getTaken().size(); j++) {
						UserInterface.getUser().getProject(i).getTaak(j).addTotaleTijd(CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserInterface.getUser().getProject(i).getTaak(j).getID(), false));
						UserInterface.getUser().getProject(i).getTaak(j).addTotaleTijd(CreatorFromJSON.createTijdspannes(validator.getSessionKey(), UserInterface.getUser().getProject(i).getTaak(j).getID(), true));
						UserInterface.getUser().getProject(i).getTaak(j).getGewerkteTijd();
					}
				}
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		JFXPanel loading = new JFXPanel();
		pane.add(loading, "loading");

		//clockJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/be/artesis/timelog/gui/icons/ClockNeonIcon.png")));

		ImageIcon lijntjeBrowser = new ImageIcon();
		lijntjeBrowser = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/lijntje.png")));
		JLabel lijntjeBrowserJLabel = new JLabel(lijntjeBrowser);
		lijntjeBrowserJLabel.setBounds(0, 450, 715, 5);
		browserPanel.add(lijntjeBrowserJLabel);

		//logo
		logo = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/logo.png")));

		// socialmedia icons
		googleIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/google.png")));
		facebookIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/facebook.png")));
		microsoftIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/microsoft.png")));
		//twitterIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
		//		getClass().getResource("/be/artesis/timelog/gui/icons/twitter.png")));
		linkedinIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/be/artesis/timelog/gui/icons/linkedin.png")));

		// initialize fields
		usernameJLabel = new JLabel("Username:");
		paswoordJLabel = new JLabel("Password:");
		aanmeldenButton = new JButton("Log in");
		usernameJTextField = new JTextField();
		passwordJPasswordField = new JPasswordField();
		newAccountJLabel = new JLabel("Or create an account");
		resetPassJLabel = new JLabel("Forgot your password?");
		socialMediaJLabel = new JLabel("Or use your favorite social media to login");
		browserGoBackButtonJLabel = new JLabel("< Login with a different account");
		logoLabel = new JLabel(logo);
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
		autoLoginInternCheckBox = new JCheckBox("Sign me in when Chronomatic starts");
		autoLoginExternCheckBox = new JCheckBox("Login automatically");

		usernameJLabel.setBounds(31, 124, 138, 16);
		paswoordJLabel.setBounds(31, 227, 138, 16);
		aanmeldenButton.setBounds(31, 347, 107, 25);
		usernameJTextField.setBounds(31, 153, 247, 22);
		passwordJPasswordField.setBounds(31, 256, 247, 22);
		newAccountJLabel.setBounds(140, 351, 160, 16);
		resetPassJLabel.setBounds(31, 390, 160, 16);
		socialMediaJLabel.setBounds(380, 124, 300, 50);
		browserGoBackButtonJLabel.setBounds(20, 456, 300, 25);
		logoLabel.setBounds(170, 0, 380, 100);
		googleJButton.setBounds(520, 175, 48, 48);
		facebookJButton.setBounds(520, 238, 48, 48);
		microsoftJButton.setBounds(520, 301, 48, 48);
		linkedinJButton.setBounds(520, 368, 48, 48);
		//twitterJButton.setBounds(493, 334, 48, 48);
		autoLoginInternCheckBox.setBounds(27, 283, 300, 25);
		autoLoginExternCheckBox.setBounds(450, 456, 200, 25);

		// Backgrounds socialmedia buttons
		googleJButton.setBackground(Color.WHITE);
		facebookJButton.setBackground(Color.WHITE);
		microsoftJButton.setBackground(Color.WHITE);
		linkedinJButton.setBackground(Color.WHITE);
		autoLoginInternCheckBox.setBackground(Color.WHITE);

		// set label fonts
		browserGoBackButtonJLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		basisPanel.add(resetPassJLabel);
		basisPanel.add(socialMediaJLabel);
		basisPanel.add(logoLabel);
		basisPanel.add(googleJButton);
		basisPanel.add(facebookJButton);
		basisPanel.add(microsoftJButton);
		//basisPanel.add(twitterJButton);
		basisPanel.add(linkedinJButton);
		basisPanel.add(autoLoginInternCheckBox);
		browserPanel.add(browserGoBackButtonJLabel);
		browserPanel.add(autoLoginExternCheckBox);

		// Action listeners
		try {
			aanmeldenButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {

					try {
						login(usernameJTextField.getText(), MD5Generator.gen(new String(passwordJPasswordField.getPassword())));
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
		}
		browserGoBackButtonJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				browserGoBackButtonJLabelClicked();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLACK);
			}
		});

		newAccountJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				displayTab("NEWUSERPANEL");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLACK);
			}
		});

		resetPassJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				displayTab("RESETPASSPANEL");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.BLACK);
			}

		});

	}

	private void browserGoBackButtonJLabelClicked() {
		this.setCursor(Cursor.getDefaultCursor());
		Platform.exit();
		displayTab("BASISPANEL");
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		JButton sender = (JButton) evt.getSource();

		switch (sender.getName()) {
		case "google":
			social = new Google();
			break;
		case "facebook":
			social = new Facebook();
			break;
		case "microsoft":
			social = new Microsoft();
			break;
		//case "twitter":
		//	social = new Twitter();
		//	break;
		case "linkedin":
			social = new Linkedin();
			break;
		default:
			social = new Google();
			break;
		}
		loginProviderJButtonClicked();
	}

	private void loginProviderJButtonClicked() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		AuthBrowser browser = new AuthBrowser(this, social);
		browser.buildUrl();
		browser.initBrowser(browserPanel);
		this.displayTab("BROWSERPANEL");
	}
}
