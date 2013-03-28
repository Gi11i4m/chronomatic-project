package be.artesis.timelog.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Date;

import com.toedter.components.JSpinField;

public class addTimeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel beginJLabel;
	private JLabel endJLabel;
	private JDateChooser dateChooser;
	private JSpinner timeSpinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addTimeDialog dialog = new addTimeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addTimeDialog() {
		setBounds(100, 100, 350, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JDateChooser beginDateChooser = new JDateChooser();
			beginDateChooser.setBounds(10, 42, 89, 20);
			contentPanel.add(beginDateChooser);
		}
		
		beginJLabel = new JLabel("Begin");
		beginJLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		beginJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		beginJLabel.setBounds(27, 11, 51, 20);
		contentPanel.add(beginJLabel);
		
		endJLabel = new JLabel("End");
		endJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		endJLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		endJLabel.setBounds(204, 11, 51, 20);
		contentPanel.add(endJLabel);
		
		timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		timeSpinner.setBounds(27, 100, 50, 20);
		contentPanel.add(timeSpinner);
		//FIXME
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(185, 42, 89, 20);
		contentPanel.add(dateChooser);
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
	}
}
