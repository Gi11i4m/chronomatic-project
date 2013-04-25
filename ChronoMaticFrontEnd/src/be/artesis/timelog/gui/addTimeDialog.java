package be.artesis.timelog.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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

public class addTimeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel beginJLabel;
	private JLabel endJLabel;
	private JDateChooser endDateChooser;
	private JDateChooser beginDateChooser;
	private JSpinner beginTimeSpinner;
	private JSpinner endTimeSpinner;
	private JLabel dateJLabel;
	private JLabel timeJLabel;
	private Validator validator;
	
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
		
		beginTimeSpinner = new JSpinner( new SpinnerDateModel() );
		beginTimeSpinner.addKeyListener(new KeyAdapter() {  
	         public void keyTyped(KeyEvent e) {  
	             char c = e.getKeyChar();  
	             if (!(Character.isDigit(c) ||  
	                (c == KeyEvent.VK_BACK_SPACE) ||  
	                (c == KeyEvent.VK_DELETE))) {  
	                  e.consume();  
	                }  
	           }  
	         }); 
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(beginTimeSpinner, "HH:mm");
		beginTimeSpinner.setEditor(timeEditor);
		beginTimeSpinner.setValue(new Date());
		beginTimeSpinner.setBounds(124, 100, 51, 20);
		contentPanel.add(beginTimeSpinner);

		endTimeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(timeEditor2);
		endTimeSpinner.setValue(new Date());
		endTimeSpinner.setBounds(299, 100, 51, 20);
		contentPanel.add(endTimeSpinner);
		
		beginDateChooser = new JDateChooser();
		beginDateChooser.setDateFormatString("dd/MM/yyyy");
		beginDateChooser.setDate(new Date());
		beginDateChooser.setBounds(124, 42, 89, 20);
		contentPanel.add(beginDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.setDate(new Date());
		endDateChooser.setBounds(299, 42, 89, 20);
		contentPanel.add(endDateChooser);
		
		dateJLabel = new JLabel("Date");
		dateJLabel.setBounds(37, 48, 46, 14);
		contentPanel.add(dateJLabel);
		
		timeJLabel = new JLabel("Time");
		timeJLabel.setBounds(37, 103, 46, 14);
		contentPanel.add(timeJLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	
}
