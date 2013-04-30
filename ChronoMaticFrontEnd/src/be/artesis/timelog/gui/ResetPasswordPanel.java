package be.artesis.timelog.gui;

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
		usernameJLabel.setText("Username:");
		goBackJButton.setText("Go back");
		sendJButton.setText("Send e-mail");
		
		this.add(usernameJLabel);
		this.add(usernameJTextField);
		this.add(goBackJButton);
		this.add(sendJButton);
		
		this.usernameJLabel.setBounds(48,42,112,25);
        this.usernameJTextField.setBounds(48,80,200,25);
        this.goBackJButton.setBounds(48,436,112,25);
        this.sendJButton.setBounds(48,130,112,25);
		
		sendJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendJButtonClicked(evt);
            }
        });
        
        goBackJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackJButtonClicked(evt);
            }
        });
	}
	
	private void sendJButtonClicked(java.awt.event.MouseEvent evt) {
		
    	parent.displayTab("BASISPANEL");
    }
	
	private void goBackJButtonClicked(java.awt.event.MouseEvent evt) {
    	parent.displayTab("BASISPANEL");
    }
	
	private javax.swing.JLabel usernameJLabel;
	private javax.swing.JTextField usernameJTextField;
	private javax.swing.JButton goBackJButton;
	private javax.swing.JButton sendJButton;
}
