package be.artesis.timelog.gui;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JOptionPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import be.artesis.timelog.controller.InserterServer;
import be.artesis.timelog.model.ExistingUsernames;
import be.artesis.timelog.secure.MD5Generator;
import be.artesis.timelog.view.DataControle;
import be.artesis.timelog.view.DataInputException;

@SuppressWarnings({ "serial" })
public class NewUserPanel extends javax.swing.JPanel {

	/**
	 * Creates new form NewUserDialog
	 * @param parent
	 * @param modal
	 */
	LoginForm parent;

	public NewUserPanel(LoginForm parent) {
		this.parent = parent;
		initComponents();
	}

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
		goBackJButton = new javax.swing.JButton();

		goBackJButton.setText("Ga terug");
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

		goBackJButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				goBackJButtonClicked(evt);
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
		this.add(goBackJButton);

		this.firstNameJLabel.setBounds(48, 74, 112, 25);
		this.firstNameJTextField.setBounds(48, 112, 200, 25);
		this.nameJLabel.setBounds(370, 74, 112, 25);
		this.nameJTextField.setBounds(370, 112, 200, 25);
		this.usernameJLabel.setBounds(48, 179, 112, 25);
		this.usernameJTextField.setBounds(48, 217, 200, 25);
		this.emailJLabel.setBounds(370, 179, 112, 25);
		this.emailJTextField.setBounds(370, 217, 200, 25);
		this.passwordJLabel.setBounds(48, 283, 112, 25);
		this.passwordJPasswordField.setBounds(48, 321, 200, 25);
		this.passwordRepeatJLabel.setBounds(370, 283, 112, 25);
		this.passwordRepeatJPasswordField.setBounds(370, 321, 200, 25);
		this.passwordStrengthJLabel.setBounds(458, 397, 112, 25);
		this.goBackJButton.setBounds(48, 436, 100, 25);
		this.registerJButton.setBounds(370, 436, 100, 25);
		this.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { firstNameJTextField, nameJTextField, emailJTextField, usernameJTextField, passwordJPasswordField, registerJButton, passwordRepeatJPasswordField }));
	}

	private void goBackJButtonClicked(java.awt.event.MouseEvent evt) {
		parent.displayTab("BASISPANEL");
	}

	private void registerJButtonClicked(java.awt.event.MouseEvent evt) {
		// maak een nieuwe gebruiker aan aan de hand van volgende velden:
		try {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String firstName = firstNameJTextField.getText();
			String name = nameJTextField.getText();
			String username = usernameJTextField.getText();
			String email = emailJTextField.getText();
			String password = new String(passwordJPasswordField.getPassword());
			String repeatPassword = new String(passwordRepeatJPasswordField.getPassword());

			if (!DataControle.persoonNaamCorrect(firstName) || !DataControle.persoonNaamCorrect(name)) {
				throw new DataInputException("Name contains illegal charaters");
			}

			if (!DataControle.naamCorrect(username)) {
				throw new DataInputException("Username contains illegal characters");
			}

			//check of username al bestaat
			if (ExistingUsernames.check(username, email)) {
				throw new DataInputException("Username already exists");

			}

			if (!password.equals(repeatPassword)) {
				throw new DataInputException("Passwords do not match");
			}

			if (!DataControle.emailCorrect(email)) {
				throw new DataInputException("Wrong email format");
			}
			InserterServer.CreateUser(name, firstName, username, MD5Generator.gen(password), email);
			JOptionPane.showMessageDialog(this, "Thank you for registering. A confirmation email has been \n " + "sent to the address you supplied. Follow the instructions in the \n " + "email to complete the registration process.");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			this.setCursor(Cursor.getDefaultCursor());
			parent.displayTab("BASISPANEL");
		}
	}

	private void passwordJPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {
		passwordStrengthJLabel.setText("Strength: " + DataControle.passwoordSterkte(passwordJPasswordField.getPassword()));
	}

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
	private javax.swing.JButton goBackJButton;
}
