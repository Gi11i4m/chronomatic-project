package be.artesis.timelog.gui;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import be.artesis.timelog.clock.Clock;
import be.artesis.timelog.controller.Deleter;
import be.artesis.timelog.controller.Updater;
import be.artesis.timelog.model.Validator;
import be.artesis.timelog.model.WebserviceException;
import be.artesis.timelog.view.DataInputException;
import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;
import be.artesis.timelog.view.Tijdspanne;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JTextArea;

/**
 * @author Gilliam
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class GUIForm extends javax.swing.JFrame {

	LoginDialog login;
	Validator validator;
	final String NEWCLIENTITEM = "New client";

	public GUIForm() {
		validator = Validator.getInstance();
		login = new LoginDialog(this, true, validator);
		login.setVisible(true);
		initComponents();
	}

	/* Begin gegenereerde code */
	// Initialiseer GUI componenten
	private void initComponents() {
		JTabbedPane = new javax.swing.JTabbedPane();
		homeJPanel = new javax.swing.JPanel();
		homeJLabel = new javax.swing.JLabel();
		workJButton = new javax.swing.JButton();
		jScrollPane7 = new javax.swing.JScrollPane();
		homeProjectsJList = new javax.swing.JList();
		HomeProjectsJLabel = new javax.swing.JLabel();
		jScrollPane8 = new javax.swing.JScrollPane();
		homeTasksJList = new javax.swing.JList();
		TasksJLabel = new javax.swing.JLabel();
		projectsJPanel = new javax.swing.JPanel();
		projectsJLabel = new javax.swing.JLabel();
		addProjectJButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		projectsJList = new javax.swing.JList();
		clientJLabel = new javax.swing.JLabel();
		setCurrentProjectJButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		projectTasksJList = new javax.swing.JList();
		namecompJLabel = new javax.swing.JLabel();
		startdatecompJLabel = new javax.swing.JLabel();
		enddatecompJLabel1 = new javax.swing.JLabel();
		taskscompJLabel = new javax.swing.JLabel();
		clientcompJLabel1 = new javax.swing.JLabel();
		filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
				new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
		nameJTextField = new javax.swing.JTextField();
		startdateJTextField = new javax.swing.JTextField();
		enddateJTextField = new javax.swing.JTextField();
		saveProjectJButton = new javax.swing.JButton();
		removeProjectJButton = new javax.swing.JButton();
		percentageCompleteJProgressBar = new javax.swing.JProgressBar();
		percentageCompletecompJLabel = new javax.swing.JLabel();
		tasksJPanel = new javax.swing.JPanel();
		tasksJLabel = new javax.swing.JLabel();
		tasksJLabel.setBounds(10, 14, 33, 16);
		jScrollPane3 = new javax.swing.JScrollPane();
		jScrollPane3.setBounds(10, 40, 204, 316);
		tasksJList = new javax.swing.JList();
		addTaskJButton = new javax.swing.JButton();
		addTaskJButton.setBounds(442, 11, 230, 23);
		removeTaskJButton = new javax.swing.JButton();
		removeTaskJButton.setBounds(10, 362, 204, 23);
		scheduleJPanel = new javax.swing.JPanel();
		scheduleJLabel = new javax.swing.JLabel();
		optionsJPanel = new javax.swing.JPanel();
		settingsJLabel = new javax.swing.JLabel();
		headerJPanel = new javax.swing.JPanel();
		titleLabel = new javax.swing.JLabel();
		ingelogdJLabel = new javax.swing.JLabel();
		currentProjectJLabel = new javax.swing.JLabel();
		clockJLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Time Management System");
		setBackground(new java.awt.Color(51, 51, 51));
		this.getContentPane().setBackground(Color.darkGray);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setForeground(new java.awt.Color(51, 51, 51));
		setIconImages(null);
		setMaximumSize(new java.awt.Dimension(2000, 2000));
		setName("guiFrame");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent evt) {
				guiOpened(evt);
			}
		});

		JTabbedPane.setBackground(new java.awt.Color(51, 51, 51));
		JTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
		JTabbedPane.setName("tabContainer");

		homeJPanel.setBackground(new java.awt.Color(153, 153, 153));
		homeJPanel.setForeground(new java.awt.Color(65, 152, 134));

		homeJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		homeJLabel.setForeground(new java.awt.Color(255, 255, 255));
		homeJLabel.setText("Home");

		workJButton.setBackground(new java.awt.Color(14, 196, 188));
		workJButton.setFont(new java.awt.Font("Tahoma", 1, 18));
		workJButton.setForeground(new java.awt.Color(204, 204, 204));
		workJButton.setText("Work");
		workJButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				workClicked(evt);
			}
		});

		homeProjectsJList
				.setToolTipText("Press enter to set the current project");
		homeProjectsJList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						homeProjectsJListValueChanged(evt);
					}
				});
		homeProjectsJList.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				homeProjectsJListKeyReleased(evt);
			}
		});
		jScrollPane7.setViewportView(homeProjectsJList);

		HomeProjectsJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		HomeProjectsJLabel.setForeground(new java.awt.Color(0, 153, 153));
		HomeProjectsJLabel.setText("Projects");

		jScrollPane8.setViewportView(homeTasksJList);

		TasksJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		TasksJLabel.setForeground(new java.awt.Color(0, 153, 153));
		TasksJLabel.setText("Tasks");

		javax.swing.GroupLayout homeJPanelLayout = new javax.swing.GroupLayout(
				homeJPanel);
		homeJPanel.setLayout(homeJPanelLayout);
		homeJPanelLayout
				.setHorizontalGroup(homeJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								homeJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												homeJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																workJButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																662,
																Short.MAX_VALUE)
														.addGroup(
																homeJPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				homeJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								homeJLabel)
																						.addGroup(
																								homeJPanelLayout
																										.createSequentialGroup()
																										.addGroup(
																												homeJPanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jScrollPane7,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																157,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																HomeProjectsJLabel))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addGroup(
																												homeJPanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																TasksJLabel)
																														.addComponent(
																																jScrollPane8,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																160,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		homeJPanelLayout
				.setVerticalGroup(homeJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								homeJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(homeJLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												workJButton,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												53,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(28, 28, 28)
										.addGroup(
												homeJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																HomeProjectsJLabel)
														.addComponent(
																TasksJLabel))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												homeJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane8,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																249,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane7))
										.addContainerGap()));

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/HomeNeonIcon.png")),
				homeJPanel, "Home"); // NOI18N

		projectsJPanel.setBackground(new java.awt.Color(153, 153, 153));

		projectsJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		projectsJLabel.setForeground(new java.awt.Color(255, 255, 255));
		projectsJLabel.setText("Projects");

		addProjectJButton.setText("Add Project");
		addProjectJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						addProjectJButtonActionPerformed(evt);
					}
				});

		projectsJList.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		projectsJList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		projectsJList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						projectsJListValueChanged(evt);
					}
				});
		jScrollPane1.setViewportView(projectsJList);

		clientJLabel.setBackground(new java.awt.Color(204, 255, 255));
		clientJLabel.setForeground(new java.awt.Color(255, 255, 255));
		clientJLabel.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		clientJLabel
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		clientJLabel.setMaximumSize(null);
		clientJLabel.setMinimumSize(new java.awt.Dimension(34, 20));
		clientJLabel.setName("");
		clientJLabel.setPreferredSize(new java.awt.Dimension(34, 20));

		setCurrentProjectJButton.setText("Set as current project");
		setCurrentProjectJButton.setEnabled(false);
		setCurrentProjectJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						setCurrentProjectJButtonActionPerformed(evt);
					}
				});

		projectTasksJList.setBackground(new java.awt.Color(102, 102, 102));
		projectTasksJList.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		projectTasksJList.setForeground(new java.awt.Color(204, 204, 204));
		projectTasksJList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		projectTasksJList.setToolTipText("");
		projectTasksJList.setEnabled(false);
		jScrollPane2.setViewportView(projectTasksJList);

		namecompJLabel.setForeground(new java.awt.Color(255, 255, 255));
		namecompJLabel.setText("Naam");

		startdatecompJLabel.setForeground(new java.awt.Color(255, 255, 255));
		startdatecompJLabel.setText("Start date");

		enddatecompJLabel1.setForeground(new java.awt.Color(255, 255, 255));
		enddatecompJLabel1.setText("End date");

		taskscompJLabel.setForeground(new java.awt.Color(255, 255, 255));
		taskscompJLabel.setText("Tasks");

		clientcompJLabel1.setForeground(new java.awt.Color(255, 255, 255));
		clientcompJLabel1.setText("Client");

		nameJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				editFieldsFocused(evt);
			}
		});

		startdateJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				editFieldsFocused(evt);
			}
		});

		enddateJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				editFieldsFocused(evt);
			}
		});

		saveProjectJButton.setText("Save");
		saveProjectJButton.setEnabled(false);
		saveProjectJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saveProjectJButtonActionPerformed(evt);
					}
				});

		removeProjectJButton.setText("Remove project");
		removeProjectJButton.setEnabled(false);
		removeProjectJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeProjectJButtonActionPerformed(evt);
					}
				});

		percentageCompletecompJLabel.setForeground(new java.awt.Color(255, 255,
				255));
		percentageCompletecompJLabel.setText("Complete");

		javax.swing.GroupLayout projectsJPanelLayout = new javax.swing.GroupLayout(
				projectsJPanel);
		projectsJPanel.setLayout(projectsJPanelLayout);
		projectsJPanelLayout
				.setHorizontalGroup(projectsJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								projectsJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												projectsJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																projectsJPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								removeProjectJButton,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								204,
																								Short.MAX_VALUE)
																						.addComponent(
																								setCurrentProjectJButton,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								projectsJPanelLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												projectsJPanelLayout
																														.createSequentialGroup()
																														.addGap(32,
																																32,
																																32)
																														.addComponent(
																																clientcompJLabel1))
																										.addGroup(
																												projectsJPanelLayout
																														.createSequentialGroup()
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																filler2,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addGroup(
																																projectsJPanelLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				namecompJLabel,
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(
																																				startdatecompJLabel,
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(
																																				enddatecompJLabel1,
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(
																																				taskscompJLabel,
																																				javax.swing.GroupLayout.Alignment.TRAILING))))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								projectsJPanelLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												percentageCompletecompJLabel)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								saveProjectJButton,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								378,
																								Short.MAX_VALUE)
																						.addComponent(
																								clientJLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								startdateJTextField,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								378,
																								Short.MAX_VALUE)
																						.addComponent(
																								enddateJTextField,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								378,
																								Short.MAX_VALUE)
																						.addComponent(
																								nameJTextField)
																						.addComponent(
																								percentageCompleteJProgressBar,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																projectsJPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				projectsJLabel)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				addProjectJButton)))
										.addContainerGap()));
		projectsJPanelLayout
				.setVerticalGroup(projectsJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								projectsJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												projectsJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																addProjectJButton)
														.addComponent(
																projectsJLabel))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												projectsJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																projectsJPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								nameJTextField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								namecompJLabel))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								startdatecompJLabel)
																						.addComponent(
																								startdateJTextField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								enddatecompJLabel1)
																						.addComponent(
																								enddateJTextField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								projectsJPanelLayout
																										.createSequentialGroup()
																										.addGroup(
																												projectsJPanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																taskscompJLabel)
																														.addGroup(
																																projectsJPanelLayout
																																		.createSequentialGroup()
																																		.addGap(152,
																																				152,
																																				152)
																																		.addComponent(
																																				filler2,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addGap(32,
																												32,
																												32))
																						.addGroup(
																								projectsJPanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								clientJLabel,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								clientcompJLabel1))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				projectsJPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								percentageCompletecompJLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								28,
																								Short.MAX_VALUE)
																						.addComponent(
																								percentageCompleteJProgressBar,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				saveProjectJButton))
														.addGroup(
																projectsJPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				removeProjectJButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				setCurrentProjectJButton,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				36,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/ProjectsNeonIcon.png")),
				projectsJPanel, "Projects"); // NOI18N

		tasksJPanel.setBackground(new java.awt.Color(153, 153, 153));

		tasksJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		tasksJLabel.setForeground(new java.awt.Color(255, 255, 255));
		tasksJLabel.setText("Tasks");

		tasksJList.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		tasksJList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		tasksJList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						tasksJListprojectListValueChanged(evt);
					}
				});
		jScrollPane3.setViewportView(tasksJList);

		addTaskJButton.setText("Add Task to current Project");
		addTaskJButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addTaskJButtonActionPerformed(evt);
			}
		});

		removeTaskJButton.setText("Remove task");
		removeTaskJButton.setEnabled(false);
		removeTaskJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeTaskJButtonActionPerformed(evt);
					}
				});

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/TasksNeonIcon.png")),
				tasksJPanel, "Tasks");
		tasksJPanel.setLayout(null);
		tasksJPanel.add(tasksJLabel);
		tasksJPanel.add(addTaskJButton);
		tasksJPanel.add(jScrollPane3);
		tasksJPanel.add(removeTaskJButton);

		taskFieldsJPanel = new JPanel();
		taskFieldsJPanel.setLayout(null);
		taskFieldsJPanel.setBackground(Color.DARK_GRAY);
		taskFieldsJPanel.setBounds(273, 40, 399, 345);
		tasksJPanel.add(taskFieldsJPanel);

		label_5 = new JLabel();
		label_5.setText("Name");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(10, 14, 56, 14);
		taskFieldsJPanel.add(label_5);

		taskNameJTextField = new JTextField();
		taskNameJTextField.setBounds(76, 11, 307, 20);
		taskFieldsJPanel.add(taskNameJTextField);

		label_6 = new JLabel();
		label_6.setText("Start date");
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(10, 40, 56, 14);
		taskFieldsJPanel.add(label_6);

		taskStartdateJTextField = new JTextField();
		taskStartdateJTextField.setBounds(76, 37, 96, 20);
		taskFieldsJPanel.add(taskStartdateJTextField);

		label_7 = new JLabel();
		label_7.setText("Completed");
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(249, 51, 51, 21);
		taskFieldsJPanel.add(label_7);

		taskCompletedJCheckBox = new JCheckBox();
		taskCompletedJCheckBox.setBackground(Color.DARK_GRAY);
		taskCompletedJCheckBox.setBounds(306, 51, 21, 21);
		taskFieldsJPanel.add(taskCompletedJCheckBox);

		taskEnddateJTextField = new JTextField();
		taskEnddateJTextField.setBounds(76, 63, 96, 20);
		taskFieldsJPanel.add(taskEnddateJTextField);

		label_8 = new JLabel();
		label_8.setText("End date");
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(10, 66, 56, 14);
		taskFieldsJPanel.add(label_8);

		label_9 = new JLabel();
		label_9.setText("Comment");
		label_9.setForeground(Color.WHITE);
		label_9.setBounds(10, 121, 56, 14);
		taskFieldsJPanel.add(label_9);

		label_10 = new JLabel();
		label_10.setText("Worked time");
		label_10.setForeground(Color.WHITE);
		label_10.setBounds(10, 184, 67, 14);
		taskFieldsJPanel.add(label_10);

		taskTotalTimeCompJLabel = new JLabel();
		taskTotalTimeCompJLabel.setToolTipText("");
		taskTotalTimeCompJLabel.setForeground(SystemColor.menu);
		taskTotalTimeCompJLabel.setBackground(Color.LIGHT_GRAY);
		taskTotalTimeCompJLabel.setBounds(80, 245, 303, 23);
		taskFieldsJPanel.add(taskTotalTimeCompJLabel);

		taskTotalPauseCompJLabel = new JLabel();
		taskTotalPauseCompJLabel.setForeground(SystemColor.menu);
		taskTotalPauseCompJLabel.setBackground(Color.LIGHT_GRAY);
		taskTotalPauseCompJLabel.setBounds(80, 279, 303, 21);
		taskFieldsJPanel.add(taskTotalPauseCompJLabel);

		saveTaskJButton = new JButton();
		saveTaskJButton.setText("Save");
		saveTaskJButton.setEnabled(false);
		saveTaskJButton.setBounds(10, 311, 373, 23);
		taskFieldsJPanel.add(saveTaskJButton);

		taskCommentJTextArea = new JTextArea();
		taskCommentJTextArea.setBounds(76, 121, 307, 51);
		taskFieldsJPanel.add(taskCommentJTextArea);

		workedTimeJList = new JList();
		workedTimeJList.setBounds(76, 183, 307, 51);
		taskFieldsJPanel.add(workedTimeJList);
		clientsJPanel = new javax.swing.JPanel();
		clientsJLabel = new javax.swing.JLabel();
		jScrollPane5 = new javax.swing.JScrollPane();
		clientsJList = new javax.swing.JList();
		removeClientJButton = new javax.swing.JButton();

		clientsJPanel.setBackground(new java.awt.Color(153, 153, 153));

		clientsJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		clientsJLabel.setForeground(new java.awt.Color(255, 255, 255));
		clientsJLabel.setText("Clients");

		clientsJList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		clientsJList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						clientsJListValueChanged(evt);
					}
				});
		jScrollPane5.setViewportView(clientsJList);

		removeClientJButton.setText("Remove client");
		removeClientJButton.setEnabled(false);
		removeClientJButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeClientJButtonActionPerformed(evt);
					}
				});

		clientFieldsJPanel = new JPanel();
		clientFieldsJPanel.setBackground(Color.DARK_GRAY);

		javax.swing.GroupLayout clientsJPanelLayout = new javax.swing.GroupLayout(
				clientsJPanel);
		clientsJPanelLayout
				.setHorizontalGroup(clientsJPanelLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								clientsJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												clientsJPanelLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																clientsJPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				clientsJPanelLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								jScrollPane5,
																								GroupLayout.DEFAULT_SIZE,
																								197,
																								Short.MAX_VALUE)
																						.addComponent(
																								removeClientJButton,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				141,
																				Short.MAX_VALUE)
																		.addComponent(
																				clientFieldsJPanel,
																				GroupLayout.PREFERRED_SIZE,
																				324,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																clientsJLabel))
										.addContainerGap()));
		clientsJPanelLayout
				.setVerticalGroup(clientsJPanelLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								clientsJPanelLayout
										.createSequentialGroup()
										.addGroup(
												clientsJPanelLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																clientsJPanelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				clientsJLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane5,
																				GroupLayout.DEFAULT_SIZE,
																				323,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				removeClientJButton,
																				GroupLayout.PREFERRED_SIZE,
																				23,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.TRAILING,
																clientsJPanelLayout
																		.createSequentialGroup()
																		.addGap(33)
																		.addComponent(
																				clientFieldsJPanel,
																				GroupLayout.DEFAULT_SIZE,
																				352,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		clientFieldsJPanel.setLayout(null);

		JLabel label = new JLabel();
		label.setBounds(10, 9, 69, 14);
		label.setText("Name");
		label.setForeground(Color.WHITE);
		clientFieldsJPanel.add(label);

		clientNameJTextField = new JTextField();
		clientNameJTextField.setBounds(89, 6, 225, 20);
		clientFieldsJPanel.add(clientNameJTextField);

		JLabel label_1 = new JLabel();
		label_1.setBounds(10, 34, 69, 14);
		label_1.setText("First name");
		label_1.setForeground(Color.WHITE);
		clientFieldsJPanel.add(label_1);

		clientFirstNameJTextField = new JTextField();
		clientFirstNameJTextField.setBounds(89, 31, 225, 20);
		clientFieldsJPanel.add(clientFirstNameJTextField);

		JLabel label_2 = new JLabel();
		label_2.setBounds(10, 59, 69, 14);
		label_2.setText("Company");
		label_2.setForeground(Color.WHITE);
		clientFieldsJPanel.add(label_2);

		clientCompanyJTextField = new JTextField();
		clientCompanyJTextField.setBounds(89, 56, 225, 20);
		clientFieldsJPanel.add(clientCompanyJTextField);

		JLabel label_3 = new JLabel();
		label_3.setBounds(10, 84, 69, 14);
		label_3.setText("E-mail");
		label_3.setForeground(Color.WHITE);
		clientFieldsJPanel.add(label_3);

		clientEmailJTextField = new JTextField();
		clientEmailJTextField.setBounds(89, 81, 225, 20);
		clientFieldsJPanel.add(clientEmailJTextField);

		JLabel label_4 = new JLabel();
		label_4.setBounds(10, 109, 79, 14);
		label_4.setText("Phone number");
		label_4.setForeground(Color.WHITE);
		clientFieldsJPanel.add(label_4);

		clientPhoneNumberJTextField = new JTextField();
		clientPhoneNumberJTextField.setBounds(89, 106, 225, 20);
		clientFieldsJPanel.add(clientPhoneNumberJTextField);

		saveClientJButton = new JButton();
		saveClientJButton.setBounds(10, 318, 304, 23);
		saveClientJButton.setText("Save");
		saveClientJButton.setEnabled(false);
		clientFieldsJPanel.add(saveClientJButton);
		clientsJPanel.setLayout(clientsJPanelLayout);

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/ClientsNeonIcon.png")),
				clientsJPanel, "Clients"); // NOI18N

		scheduleJPanel.setBackground(new java.awt.Color(153, 153, 153));

		scheduleJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		scheduleJLabel.setForeground(new java.awt.Color(255, 255, 255));
		scheduleJLabel.setText("Schedule");

		javax.swing.GroupLayout scheduleJPanelLayout = new javax.swing.GroupLayout(
				scheduleJPanel);
		scheduleJPanel.setLayout(scheduleJPanelLayout);
		scheduleJPanelLayout.setHorizontalGroup(scheduleJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						scheduleJPanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(scheduleJLabel)
								.addContainerGap(621, Short.MAX_VALUE)));
		scheduleJPanelLayout.setVerticalGroup(scheduleJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						scheduleJPanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(scheduleJLabel)
								.addContainerGap(369, Short.MAX_VALUE)));

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/CalendarNeonIcon.png")),
				scheduleJPanel, "Schedule"); // NOI18N

		optionsJPanel.setBackground(new java.awt.Color(153, 153, 153));

		settingsJLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 14));
		settingsJLabel.setForeground(new java.awt.Color(255, 255, 255));
		settingsJLabel.setText("Settings");

		javax.swing.GroupLayout optionsJPanelLayout = new javax.swing.GroupLayout(
				optionsJPanel);
		optionsJPanel.setLayout(optionsJPanelLayout);
		optionsJPanelLayout.setHorizontalGroup(optionsJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						optionsJPanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(settingsJLabel)
								.addContainerGap(627, Short.MAX_VALUE)));
		optionsJPanelLayout.setVerticalGroup(optionsJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						optionsJPanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(settingsJLabel)
								.addContainerGap(369, Short.MAX_VALUE)));

		JTabbedPane.addTab(
				"",
				new javax.swing.ImageIcon(getClass().getResource(
						"/be/artesis/timelog/gui/icons/SettingsNeonIcon.png")),
				optionsJPanel, "Settings"); // NOI18N

		headerJPanel.setBackground(new java.awt.Color(64, 64, 64));

		titleLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18));
		titleLabel.setForeground(new java.awt.Color(255, 255, 255));
		titleLabel.setText("ChronoMatic");

		ingelogdJLabel.setBackground(new java.awt.Color(255, 255, 255));
		ingelogdJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		ingelogdJLabel.setForeground(new java.awt.Color(255, 0, 0));
		ingelogdJLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ingelogdJLabel.setText("Not logged in");
		ingelogdJLabel.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.LOWERED));
		ingelogdJLabel.setName("");
		ingelogdJLabel.setOpaque(true);

		currentProjectJLabel.setBackground(new java.awt.Color(255, 255, 255));
		currentProjectJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		currentProjectJLabel.setForeground(new java.awt.Color(0, 204, 204));
		currentProjectJLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		currentProjectJLabel.setText("Current project: ...");
		currentProjectJLabel.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.LOWERED));
		currentProjectJLabel.setName("");
		currentProjectJLabel.setOpaque(true);

		clockJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/be/artesis/timelog/gui/icons/ClockNeonIcon.png"))); // NOI18N

		javax.swing.GroupLayout headerJPanelLayout = new javax.swing.GroupLayout(
				headerJPanel);
		headerJPanel.setLayout(headerJPanelLayout);
		headerJPanelLayout
				.setHorizontalGroup(headerJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								headerJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												headerJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																currentProjectJLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE)
														.addComponent(
																ingelogdJLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(titleLabel)
										.addGap(18, 18, 18)
										.addComponent(clockJLabel)
										.addGap(6, 6, 6)));
		headerJPanelLayout
				.setVerticalGroup(headerJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								headerJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												headerJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																titleLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																clockJLabel,
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																headerJPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				ingelogdJLabel)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				currentProjectJLabel)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		clockJLabel.getAccessibleContext().setAccessibleName("iconJLabel");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(headerJPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(JTabbedPane));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(headerJPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(JTabbedPane)));
		pack();
	}

	/* Einde gegenereerde code */

	private void workClicked(java.awt.event.MouseEvent evt) {
		try {
			Project p = UserControl.getCurrentProject();
			if (p.getTaken().isEmpty()) {
				throw new GUIException("Current project contains no tasks");
			}
			boolean taskAvailable = false;
			for (Taak t : p.getTaken()) {
				if (!t.overTijd()) {
					taskAvailable = true;
					break;
				}
			}
			if (!taskAvailable) {
				throw new GUIException(
						"Current project contains no running tasks");
			}
			setVisible(false);
			WorkDialog work = new WorkDialog(this, true, validator);
			work.setVisible(true);
			setVisible(true);
			loadTaskInfo(tasksJList.getSelectedIndex());
			toggleButtonStates();
		} catch (GUIException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void addProjectJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		NewProjectDialog newProject = new NewProjectDialog(this, true,
				validator);
		newProject.setVisible(true);
		refreshProjectsList();
		refreshClientsList();
		clearFieldsOnPanel(projectsJPanel);
		clearFieldsOnPanel(clientsJPanel);
		toggleButtonStates();
	}

	private void projectsJListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_projectsJListValueChanged
		loadProjectInfo(projectsJList.getSelectedIndex());
		toggleButtonStates();
	}

	private void loadProjectInfo(int index) {
		if (index != -1) {
			Project p = UserControl.getUser().getProjects().get(index);
			nameJTextField.setText(p.getNaam());
			startdateJTextField.setText(Clock.timestampToDateString(p
					.getBegindatum()));
			enddateJTextField.setText(Clock.timestampToDateString(p
					.getEinddatum()));
			try {
				clientJLabel.setText(UserControl.getUser()
						.getOpdrachtgever(p.getOpdrachtgeverId()).toString());
			} catch (DataInputException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

			refreshTasksList(p, projectTasksJList);
			percentageCompleteJProgressBar
					.setValue((int) (((Project) projectsJList
							.getSelectedValue()).getPercentageComplete() * 100));
		}
	}

	private void loadTaskInfo(int index) throws GUIException {
		if (index != -1) {
			Taak t = (Taak) tasksJList.getSelectedValue();
			taskNameJTextField.setText(t.getNaam());
			taskStartdateJTextField.setText(Clock.timestampToDateString(t
					.getBegindatum()));
			taskEnddateJTextField.setText(Clock.timestampToDateString(t
					.getGeschatteEinddatum()));
			taskCommentJTextArea.setText(t.getCommentaar());
			taskCompletedJCheckBox.setSelected(t.getCompleted());

			DefaultListModel listmodel = new DefaultListModel();

			for (Iterator<Tijdspanne> it = t.getGewerkteTijd().iterator(); it
					.hasNext();) {
				Tijdspanne ts = it.next();
				listmodel.addElement(ts);
			}

			workedTimeJList.setModel(listmodel);
			taskTotalTimeCompJLabel.setText("Total worked : "
					+ Clock.longTimeToString(t.getTotaleWerktijd(), false));
			taskTotalPauseCompJLabel.setText("Total paused : "
					+ Clock.longTimeToString(t.getTotalePauze(), false));
		}
	}

	private void loadClientInfo(int index) {
		if (index != -1) {
			Opdrachtgever o = (Opdrachtgever) clientsJList.getSelectedValue();
			clientNameJTextField.setText(o.getNaam());
			clientFirstNameJTextField.setText(o.getVoornaam());
			clientCompanyJTextField.setText(o.getBedrijfsnaam());
			clientEmailJTextField.setText(o.getEmail());
			clientPhoneNumberJTextField.setText(o.getTelefoonnummer());
		}
	}

	private void refreshProjectsList(JList... lists) {
		DefaultListModel listmodel = new DefaultListModel();

		for (Iterator<Project> it = UserControl.getUser().getProjects()
				.iterator(); it.hasNext();) {
			Project p = it.next();
			listmodel.addElement(p);
		}

		for (JList list : lists) {
			list.setModel(listmodel);
			list.setCellRenderer(new ProjectCellRenderer());
			// FIXME selected index bijhouden en terugzetten
			list.setSelectedIndex(-1);

		}
	}

	private void refreshTasksList(Project p, JList... lists) {
		for (JList list : lists) {
			DefaultListModel listmodel = new DefaultListModel();

			for (Iterator<Taak> it = p.getTaken().iterator(); it.hasNext();) {
				Taak t = it.next();
				listmodel.addElement(t);
			}

			list.setModel(listmodel);
			list.setCellRenderer(new TaskCellRenderer());
			list.setSelectedIndex(-1);
		}
	}

	private void refreshClientsList() {
		DefaultListModel listmodel = new DefaultListModel();

		for (Iterator<Opdrachtgever> it = UserControl.getUser()
				.getOpdrachtgevers().iterator(); it.hasNext();) {
			Opdrachtgever o = it.next();
			listmodel.addElement(o);
		}
		clientsJList.setModel(listmodel);
		clientsJList.setSelectedIndex(-1);
	}

	private void clearFieldsOnPanel(JPanel panel) {
		Component[] clientPanelComps = panel.getComponents();
		for (Component c : clientPanelComps) {
			if (c instanceof JTextField) {
				((JTextField) c).setText(null);
			}
		}
		if (panel.equals(projectsJPanel)) {
			projectTasksJList.setModel(new DefaultListModel());
			clientJLabel.setText(null);
		} else if (panel.equals(tasksJPanel)) {
			taskCommentJTextArea.setText(null);
			workedTimeJList.setModel(new DefaultListModel());
			taskCompletedJCheckBox.setSelected(false);
		}
	}

	private void toggleButtonStates() {
		boolean taskSelected = tasksJList.getSelectedIndex() != -1;
		boolean projectSelected = projectsJList.getSelectedIndex() != -1;
		boolean clientSelected = clientsJList.getSelectedIndex() != -1;
		saveTaskJButton.setEnabled(taskSelected);
		removeTaskJButton.setEnabled(taskSelected);
		setCurrentProjectJButton.setEnabled(projectSelected);
		saveProjectJButton.setEnabled(projectSelected);
		removeProjectJButton.setEnabled(projectSelected);
		saveClientJButton.setEnabled(clientSelected);
		removeClientJButton.setEnabled(clientSelected);
	}

	private void guiOpened(java.awt.event.WindowEvent evt) {
		ingelogdJLabel.setText(UserControl.getUser().getGebruikersnaam());
		refreshProjectsList(projectsJList, homeProjectsJList);
		refreshClientsList();
	}

	private void editFieldsFocused(java.awt.event.FocusEvent evt) {
		toggleButtonStates();
	}

	private void clientsJListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {

		if (clientsJList.getSelectedValue().equals(NEWCLIENTITEM)) {
			clearFieldsOnPanel(clientFieldsJPanel);
			saveClientJButton.setText("Save [new]");
		} else {
			loadClientInfo(clientsJList.getSelectedIndex());
			saveClientJButton.setText("Save");
		}
		toggleButtonStates();
	}

	private void saveProjectJButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			Project p = (Project) UserControl.getUser().getProjects()
					.get(projectsJList.getSelectedIndex()).clone();
			p.setNaam(nameJTextField.getText());
			p.setBegindatum(Clock.StringToTimestamp(startdateJTextField
					.getText()));
			p.setEinddatum(Clock.StringToTimestamp(enddateJTextField.getText()));
			// Past projectwaarden aan in database
			Updater.updateProject(validator.getSessionKey(), p);
			JOptionPane.showMessageDialog(this, "Project edited!");
			UserControl.getUser().getProjects()
					.set(projectsJList.getSelectedIndex(), p);
		} catch (DataInputException | IOException | WebserviceException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			refreshProjectsList(projectsJList, homeProjectsJList);
			clearFieldsOnPanel(projectsJPanel);
			toggleButtonStates();
		}
	}

	private void addTaskJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			NewTaskDialog addTaskDialog = new NewTaskDialog(this, true,
					validator);
			addTaskDialog.setVisible(true);
			refreshTasksList(UserControl.getCurrentProject(), tasksJList);
		} catch (GUIException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			clearFieldsOnPanel(tasksJPanel);
			loadProjectInfo(projectsJList.getSelectedIndex());
		}
	}

	private void tasksJListprojectListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {
		try {
			loadTaskInfo(tasksJList.getSelectedIndex());
		} catch (GUIException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
		toggleButtonStates();
	}

	private void removeProjectJButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to delete this project?", null,
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			try {
				Project p = UserControl.getUser().getProjects()
						.get(projectsJList.getSelectedIndex());
				Deleter.deleteProject(validator.getSessionKey(), p);
				UserControl.getUser().removeProject(
						(Project) projectsJList.getSelectedValue());
				refreshProjectsList(projectsJList, homeProjectsJList);
				clearFieldsOnPanel(projectsJPanel);
				toggleButtonStates();
				JOptionPane.showMessageDialog(this, "Project removed!");
			} catch (IOException | WebserviceException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
	}

	private void removeClientJButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to delete this client?", null,
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			try {
				Opdrachtgever c = UserControl.getUser().getOpdrachtgever(
						((Opdrachtgever) clientsJList.getSelectedValue())
								.getID());
				// !! Deze for-lus moet verwijderd worden en vervangen worden
				// door een error van de server
				for (Project p : UserControl.getUser().getProjects()) {
					if (p.getOpdrachtgeverId() == c.getID()) {
						throw new GUIException(
								"This client is associated with a project");
					}
				}
				Deleter.deleteOpdrachtgever(validator.getSessionKey(), c);
				UserControl.getUser().getOpdrachtgevers().remove(c);
				refreshClientsList();
				clearFieldsOnPanel(clientsJPanel);
				toggleButtonStates();
				JOptionPane.showMessageDialog(this, "Client removed!");
			} catch (GUIException | DataInputException | IOException
					| WebserviceException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}

	}

	private void setCurrentProjectJButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			UserControl
					.setCurrentProjectIndex(projectsJList.getSelectedIndex());
			currentProjectJLabel.setText("Current project: "
					+ UserControl.getCurrentProject().getNaam());
			addTaskJButton.setText("Add Task to "
					+ UserControl.getCurrentProject().getNaam());
			refreshProjectsList(projectsJList, homeProjectsJList);
			refreshTasksList(UserControl.getCurrentProject(), tasksJList);
			clearFieldsOnPanel(tasksJPanel);
		} catch (GUIException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void homeProjectsJListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {
		if (homeProjectsJList.getSelectedIndex() != -1) {
			refreshTasksList(
					UserControl.getUser().getProject(
							homeProjectsJList.getSelectedIndex()),
					homeTasksJList);
		} else {
			try {
				refreshTasksList(UserControl.getCurrentProject(),
						homeTasksJList);
			} catch (GUIException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void homeProjectsJListKeyReleased(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == 10) {
			UserControl.setCurrentProjectIndex(homeProjectsJList
					.getSelectedIndex());
			refreshProjectsList(homeProjectsJList);
			try {
				refreshTasksList(UserControl.getCurrentProject(), tasksJList);
			} catch (GUIException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void removeTaskJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to delete this task?", null,
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			try {
				Taak t = UserControl.getCurrentProject().getTaken()
						.get(tasksJList.getSelectedIndex());
				Deleter.deleteTaak(validator.getSessionKey(), t);
				UserControl.getCurrentProject().getTaken().remove(t);
				refreshTasksList(UserControl.getCurrentProject(), tasksJList);
				clearFieldsOnPanel(tasksJPanel);
				toggleButtonStates();
				JOptionPane.showMessageDialog(this, "Task removed!");
			} catch (GUIException | IOException | WebserviceException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel HomeProjectsJLabel;
	private javax.swing.JTabbedPane JTabbedPane;
	private javax.swing.JLabel TasksJLabel;
	private javax.swing.JButton addProjectJButton;
	private javax.swing.JButton addTaskJButton;
	private javax.swing.JLabel clientJLabel;
	private javax.swing.JLabel clientcompJLabel1;
	private javax.swing.JLabel clientsJLabel;
	private javax.swing.JList clientsJList;
	private javax.swing.JPanel clientsJPanel;
	private javax.swing.JLabel clockJLabel;
	private javax.swing.JLabel currentProjectJLabel;
	private javax.swing.JTextField enddateJTextField;
	private javax.swing.JLabel enddatecompJLabel1;
	private javax.swing.Box.Filler filler2;
	private javax.swing.JPanel headerJPanel;
	private javax.swing.JLabel homeJLabel;
	private javax.swing.JPanel homeJPanel;
	private javax.swing.JList homeProjectsJList;
	private javax.swing.JList homeTasksJList;
	private javax.swing.JLabel ingelogdJLabel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JScrollPane jScrollPane8;
	private javax.swing.JTextField nameJTextField;
	private javax.swing.JLabel namecompJLabel;
	private javax.swing.JPanel optionsJPanel;
	private javax.swing.JProgressBar percentageCompleteJProgressBar;
	private javax.swing.JLabel percentageCompletecompJLabel;
	private javax.swing.JList projectTasksJList;
	private javax.swing.JLabel projectsJLabel;
	private javax.swing.JList projectsJList;
	private javax.swing.JPanel projectsJPanel;
	private javax.swing.JButton removeClientJButton;
	private javax.swing.JButton removeProjectJButton;
	private javax.swing.JButton removeTaskJButton;
	private javax.swing.JButton saveProjectJButton;
	private javax.swing.JLabel scheduleJLabel;
	private javax.swing.JPanel scheduleJPanel;
	private javax.swing.JButton setCurrentProjectJButton;
	private javax.swing.JLabel settingsJLabel;
	private javax.swing.JTextField startdateJTextField;
	private javax.swing.JLabel startdatecompJLabel;
	private javax.swing.JLabel tasksJLabel;
	private javax.swing.JList tasksJList;
	private javax.swing.JPanel tasksJPanel;
	private javax.swing.JLabel taskscompJLabel;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JButton workJButton;
	private JTextField clientNameJTextField;
	private JTextField clientFirstNameJTextField;
	private JTextField clientCompanyJTextField;
	private JTextField clientEmailJTextField;
	private JTextField clientPhoneNumberJTextField;
	private JPanel clientFieldsJPanel;
	private JButton saveClientJButton;
	private JPanel taskFieldsJPanel;
	private JLabel label_5;
	private JTextField taskNameJTextField;
	private JLabel label_6;
	private JTextField taskStartdateJTextField;
	private JLabel label_7;
	private JCheckBox taskCompletedJCheckBox;
	private JTextField taskEnddateJTextField;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel taskTotalTimeCompJLabel;
	private JLabel taskTotalPauseCompJLabel;
	private JButton saveTaskJButton;
	private JTextArea taskCommentJTextArea;
	private JList workedTimeJList;
}