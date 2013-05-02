package be.artesis.timelog.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import be.artesis.timelog.model.Validator;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Date;

import com.toedter.components.JSpinField;
import javax.swing.SpinnerModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addTimeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel beginJLabel;
	private JLabel endJLabel;
	private JSpinner beginTimeSpinner;
	private JSpinner endTimeSpinner;
	private JLabel timeJLabel;
	private Validator validator;
	private final int MAX_LENGTH = 5;
	private JLabel dateJLabel;
	private JDateChooser beginDateChooser;
	private JDateChooser endDateChooser;

	/**
	 * Create the dialog.
	 */
	public addTimeDialog(java.awt.Frame parent, boolean modal, Validator validator) {
		super(parent, modal);
		setUndecorated(false);
		setLocationRelativeTo(parent);
		this.validator = validator;
		setBounds(100, 100, 500, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

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
		beginTimeSpinner.setValue(new Date());
		beginTimeSpinner.setBounds(141, 86, 75, 20);
		contentPanel.add(beginTimeSpinner);

		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(timeEditor2);
		endTimeSpinner.setValue(new Date());
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
		beginDateChooser.setDate(new Date());
		beginDateChooser.setDateFormatString("dd/MM/yyyy");
		beginDateChooser.setBounds(141, 42, 89, 20);
		contentPanel.add(beginDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.setDate(new Date());
		endDateChooser.setBounds(318, 42, 89, 20);
		
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		JComponent comp = beginTimeSpinner.getEditor();
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		formatter.setCommitsOnValidEdit(true);
	}
	
	public void okPressed(){
		long d1 = beginDateChooser.getDate().getTime() / 1000;
		long d2 = endDateChooser.getDate().getTime() / 1000;
		
		long t1 = (long) beginTimeSpinner.getValue();
		long t2 = (long) beginTimeSpinner.getValue();
		
		long begin = d1 + t1;
		long end = d2 + t2;
		
		System.out.println(d1 + ", " + t1 + " = " + begin);
		System.out.println(d2 + ", " + t2 + " = " + end);
	}
}
