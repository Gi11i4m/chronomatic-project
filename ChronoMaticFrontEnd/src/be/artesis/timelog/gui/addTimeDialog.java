package be.artesis.timelog.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.DataInputException;
import be.artesis.timelog.view.Taak;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings({ "serial", "deprecation" })
public class addTimeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Taak t;
	private JLabel beginJLabel;
	private JLabel endJLabel;
	private JSpinner beginTimeSpinner;
	private JSpinner endTimeSpinner;
	private JLabel timeJLabel;
	private final int MAX_LENGTH = 5;
	private final Date now = new Date();
	private Date today;
	private JLabel dateJLabel;
	private JDateChooser beginDateChooser;
	private JDateChooser endDateChooser;

	/**
	 * Create the dialog.
	 */
	public addTimeDialog(java.awt.Frame parent, boolean modal, Taak t) {
		super(parent, modal);
		setUndecorated(false);
		setLocationRelativeTo(parent);
		this.t = t;
		setBounds(100, 100, 500, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		today = new Date(now.getYear(), now.getMonth(), now.getDate(), 0, 0, 0);

		beginJLabel = new JLabel("Begin");
		beginJLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		beginJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		beginJLabel.setBounds(141, 11, 51, 20);
		contentPanel.add(beginJLabel);

		endJLabel = new JLabel("End");
		endJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		endJLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		endJLabel.setBounds(318, 11, 51, 20);
		contentPanel.add(endJLabel);

		beginTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(beginTimeSpinner, "HH:mm");
		beginTimeSpinner.setEditor(timeEditor);
		beginTimeSpinner.setValue(now);
		beginTimeSpinner.setBounds(141, 86, 75, 20);
		contentPanel.add(beginTimeSpinner);

		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(timeEditor2);
		endTimeSpinner.setValue(now);
		endTimeSpinner.setBounds(327, 86, 61, 20);
		contentPanel.add(endTimeSpinner);

		JTextField beginTimeJTextField = ((JSpinner.DefaultEditor) beginTimeSpinner.getEditor()).getTextField();
		beginTimeJTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				JTextField t = (JTextField) arg0.getSource();
				if (!(Character.isDigit(c)) || t.getText().length() == MAX_LENGTH) {
					arg0.consume();
				}
			}
		});

		JTextField endTimeJTextField = ((JSpinner.DefaultEditor) endTimeSpinner.getEditor()).getTextField();
		endTimeJTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				JTextField t = (JTextField) arg0.getSource();
				if (!(Character.isDigit(c)) || t.getText().length() == MAX_LENGTH) {
					arg0.consume();
				}
			}
		});

		timeJLabel = new JLabel("Time");
		timeJLabel.setBounds(55, 89, 75, 14);
		contentPanel.add(timeJLabel);

		dateJLabel = new JLabel("Date");
		dateJLabel.setBounds(55, 51, 46, 14);
		contentPanel.add(dateJLabel);

		beginDateChooser = new JDateChooser();
		beginDateChooser.setDate(today);
		beginDateChooser.setDateFormatString("dd/MM/yyyy");
		beginDateChooser.setBounds(141, 42, 100, 20);
		contentPanel.add(beginDateChooser);

		endDateChooser = new JDateChooser();
		endDateChooser.setDate(today);
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.setBounds(328, 42, 100, 20);
		contentPanel.add(endDateChooser);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						okPressed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelPressed();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void cancelPressed() {
		this.dispose();
	}

	//FIXME optie om pauze toe te voegen nodig?
	public void okPressed() {
		Date d1 = beginDateChooser.getDate();
		Date d2 = endDateChooser.getDate();

		Date t1 = (Date) beginTimeSpinner.getValue();
		Date t2 = (Date) endTimeSpinner.getValue();

		Long l1 = (d1.getTime() / 1000) + (t1.getMinutes() * 60 + t1.getHours() * 3600);
		Long l2 = (d2.getTime() / 1000) + (t2.getMinutes() * 60 + t2.getHours() * 3600);

		try {
			UserInterface.createTimespan(l1, l2, t, false);
			this.dispose();
		} catch (DataInputException | IOException | WebserviceException | JSONException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
