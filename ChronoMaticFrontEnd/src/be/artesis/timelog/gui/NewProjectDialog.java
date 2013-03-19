package be.artesis.timelog.gui;

import be.artesis.timelog.controle.DataControle;
import be.artesis.timelog.controller.Inserter;
import be.artesis.timelog.model.*;
import be.artesis.timelog.view.*;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**
 *
 * @author Gilliam
 */
public class NewProjectDialog extends javax.swing.JDialog {

    private Opdrachtgever client;
    private ArrayList<Opdrachtgever> clients;
    boolean newClientOpened = false;
    Validator validator;
    /**
     * Creates new form NewProjectDialog
     *
     * @param parent
     * @param modal
     * @param validator
     */
    public NewProjectDialog(java.awt.Frame parent, boolean modal, Validator validator) {
        super(parent, modal);
        setLocationRelativeTo(parent);
        initComponents();
        initMyComponents();
        this.validator = validator;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        projectnameJLabel = new javax.swing.JLabel();
        startdateJLabel = new javax.swing.JLabel();
        enddateJLabel = new javax.swing.JLabel();
        projectnameJTextField = new javax.swing.JTextField();
        startdateJTextField = new javax.swing.JTextField();
        enddateJTextField = new javax.swing.JTextField();
        clientJLabel = new javax.swing.JLabel();
        clientJComboBox = new javax.swing.JComboBox();
        addProjectJButton = new javax.swing.JButton();
        newClientJPanel = new javax.swing.JPanel();
        backJButton = new javax.swing.JButton();
        nameJTextField = new javax.swing.JTextField();
        telephoneJLabel = new javax.swing.JLabel();
        nameJLabel = new javax.swing.JLabel();
        firstNameJTextField = new javax.swing.JTextField();
        telephoneJTextField = new javax.swing.JTextField();
        firstNameJLabel = new javax.swing.JLabel();
        companyJTextField = new javax.swing.JTextField();
        emailJLabel = new javax.swing.JLabel();
        emailJTextField = new javax.swing.JTextField();
        companyJLabel = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Project");
        setBackground(new java.awt.Color(186, 12, 12));
        setForeground(new java.awt.Color(255, 204, 0));
        setMinimumSize(new java.awt.Dimension(295, 350));
        setPreferredSize(new java.awt.Dimension(295, 350));
        setResizable(false);

        projectnameJLabel.setText("Projectname");

        startdateJLabel.setText("Start date");

        enddateJLabel.setText("End date");

        startdateJTextField.setToolTipText("dd/mm/yyyy");

        enddateJTextField.setToolTipText("dd/mm/yyyy");

        clientJLabel.setText("Client");

        clientJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New client" }));

        addProjectJButton.setText("Add project");
        addProjectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addProjectClicked(evt);
            }
        });

        newClientJPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        backJButton.setText("Hide");
        backJButton.setEnabled(false);
        backJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideButtonClicked(evt);
            }
        });

        nameJTextField.setEnabled(false);

        telephoneJLabel.setText("Telephone number");

        nameJLabel.setText("Name");

        firstNameJTextField.setEnabled(false);

        telephoneJTextField.setEnabled(false);

        firstNameJLabel.setText("First name");

        companyJTextField.setEnabled(false);

        emailJLabel.setText("Email adress");

        emailJTextField.setEnabled(false);

        companyJLabel.setText("Company");

        javax.swing.GroupLayout newClientJPanelLayout = new javax.swing.GroupLayout(newClientJPanel);
        newClientJPanel.setLayout(newClientJPanelLayout);
        newClientJPanelLayout.setHorizontalGroup(
            newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClientJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newClientJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newClientJPanelLayout.createSequentialGroup()
                                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telephoneJLabel)
                                    .addComponent(emailJLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailJTextField)
                                    .addComponent(telephoneJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(newClientJPanelLayout.createSequentialGroup()
                                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(companyJLabel)
                                    .addComponent(firstNameJLabel))
                                .addGap(18, 18, 18)
                                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstNameJTextField)
                                    .addComponent(companyJTextField)
                                    .addComponent(nameJTextField)))))
                    .addComponent(backJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        newClientJPanelLayout.setVerticalGroup(
            newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClientJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameJLabel))
                .addGap(18, 18, 18)
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameJLabel))
                .addGap(18, 18, 18)
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyJLabel))
                .addGap(48, 48, 48)
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailJLabel))
                .addGap(18, 18, 18)
                .addGroup(newClientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telephoneJLabel)
                    .addComponent(telephoneJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(clientJComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(startdateJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enddateJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(startdateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enddateJTextField))
                    .addComponent(clientJLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(projectnameJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(projectnameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addProjectJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(newClientJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projectnameJLabel)
                            .addComponent(projectnameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startdateJLabel)
                            .addComponent(enddateJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startdateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enddateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clientJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(addProjectJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(newClientJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addProjectClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProjectClicked
        try {
            if (!newClientOpened) {
                if (((Opdrachtgever) clientJComboBox.getSelectedItem()).getVoornaam().equals("New")) {
                    showClientForm();
                } else {
                    client = clients.get(clientJComboBox.getSelectedIndex());
                    addProject(projectnameJTextField.getText(), startdateJTextField.getText(), enddateJTextField.getText(), client.getID());
                    this.dispose();
                }
            } else {
                addClient();
                addProject(projectnameJTextField.getText(), startdateJTextField.getText(), enddateJTextField.getText(), client.getID());
                this.dispose();
            }
        } catch (IOException | WebserviceException | JSONException | DataInputException | ParseException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_addProjectClicked

    private void hideButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideButtonClicked
        hideClientForm();
    }//GEN-LAST:event_hideButtonClicked

    private void addProject(String name, String startDate, String endDate, int opdrachtgeverID) throws MalformedURLException, IOException, WebserviceException, JSONException, DataInputException, ParseException {
        Project p = new Project();
        p.setNaam(name);
        p.setBegindatum(startDate);
        p.setEinddatum(endDate);
        p.setOpdrachtgeverId(opdrachtgeverID);
        p.setId(Inserter.inputProject(validator.getSessionKey(), p, client.getID()));
        UserInterface.getUser().addProject(p);
    }

    private void addClient() throws JSONException, IOException, WebserviceException, DataInputException {
        client = new Opdrachtgever();
        client.setNaam(nameJTextField.getText());
        client.setVoornaam(firstNameJTextField.getText());
        client.setBedrijfsnaam(companyJTextField.getText());
        client.setEmail(emailJTextField.getText());
        client.setTelefoonnummer(telephoneJTextField.getText());
        client.setID(Inserter.inputOpdrachtgever(validator.getSessionKey(), client)); // Make Client ++ Add ClientID returned from DB
        UserInterface.getUser().addOpdrachtgever(client);
    }

    private void showClientForm() {
        this.setSize(650, 350);
        projectnameJTextField.setEnabled(false);
        startdateJTextField.setEnabled(false);
        enddateJTextField.setEnabled(false);
        clientJComboBox.setEnabled(false);
        addProjectJButton.setText("Add project with new client");
        newClientOpened = true;
        for (Component c : newClientJPanel.getComponents()) {
            c.setEnabled(true);
        }
    }

    private void hideClientForm() {
        this.setSize(295, 350);
        projectnameJTextField.setEnabled(true);
        startdateJTextField.setEnabled(true);
        enddateJTextField.setEnabled(true);
        clientJComboBox.setEnabled(true);
        newClientOpened = false;
        addProjectJButton.setText("Add project");
        for (Component c : newClientJPanel.getComponents()) {
            c.setEnabled(false);
        }
    }

    private void initMyComponents() {
        startdateJTextField.setText(DataControle.sdf.format(new Date()));
        clients = UserInterface.getUser().getOpdrachtgevers();
        DefaultComboBoxModel combomodel = new DefaultComboBoxModel();
        combomodel.addElement(new Opdrachtgever("Client", "New", "add a new client", "", "", 0));
        for (Iterator<Opdrachtgever> it = clients.iterator(); it.hasNext();) {
            Opdrachtgever o = it.next();
            combomodel.addElement(o);
        }
        clientJComboBox.setModel(combomodel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProjectJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JComboBox clientJComboBox;
    private javax.swing.JLabel clientJLabel;
    private javax.swing.JLabel companyJLabel;
    private javax.swing.JTextField companyJTextField;
    private javax.swing.JLabel emailJLabel;
    private javax.swing.JTextField emailJTextField;
    private javax.swing.JLabel enddateJLabel;
    private javax.swing.JTextField enddateJTextField;
    private javax.swing.JLabel firstNameJLabel;
    private javax.swing.JTextField firstNameJTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JPanel newClientJPanel;
    private javax.swing.JLabel projectnameJLabel;
    private javax.swing.JTextField projectnameJTextField;
    private javax.swing.JLabel startdateJLabel;
    private javax.swing.JTextField startdateJTextField;
    private javax.swing.JLabel telephoneJLabel;
    private javax.swing.JTextField telephoneJTextField;
    // End of variables declaration//GEN-END:variables
}
