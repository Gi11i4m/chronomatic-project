package be.artesis.timelog.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JList;

public class Prul extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prul frame = new Prul();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prul() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 11, 389, 239);
		contentPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(3dlu;default)"),
				ColumnSpec.decode("110px"), FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("27px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("6px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("49px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("6px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("43px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("6px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("27px"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(57dlu;pref)"), }));

		label = new JLabel();
		label.setText("Naam");
		label.setForeground(Color.WHITE);
		panel.add(label, "3, 2, left, center");

		textField = new JTextField();
		panel.add(textField, "6, 2, 12, 1, fill, top");

		label_1 = new JLabel();
		label_1.setText("Start date");
		label_1.setForeground(Color.WHITE);
		panel.add(label_1, "3, 4, left, center");

		textField_1 = new JTextField();
		panel.add(textField_1, "6, 4, 12, 1, fill, top");

		label_2 = new JLabel();
		label_2.setText("End date");
		label_2.setForeground(Color.WHITE);
		panel.add(label_2, "3, 6, left, center");

		textField_2 = new JTextField();
		panel.add(textField_2, "6, 6, 12, 1, fill, top");

		label_3 = new JLabel();
		label_3.setText("Tasks");
		label_3.setForeground(Color.WHITE);
		panel.add(label_3, "3, 8, left, center");

		list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.add(list, "6, 8, 12, 1, fill, fill");
	}
}
