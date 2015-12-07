package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import model.SQLiteConnector;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.toedter.calendar.JDateChooser;

import controller.CopyFile;
import controller.BookEntry;

import java.sql.Connection;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;

import javax.swing.JTextArea;

public class Profile extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0, 189, 154);
	private Color grey = new Color(222, 222, 222);
	private Color darkGrey = new Color(112, 112, 112);
	private Color white = new Color(255, 255, 255);
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfStreet;
	private JTextField tfPostCode;
	private JTextField tfCity;
	private JTextField tfPhoneNumber;
	private JTextField tfEmergency;
	private JTextField tfMedicalCondition;
	private JLabel lblFullName;
	private JLabel lblPatientPhoto;
	private JDateChooser dateDOB;
	private JDateChooser dateAppointment;
	private JComboBox<String> cbGender;
	private JComboBox<String> cbBilling;
	private JComboBox<String> cbInsurance;

	
	private String[] insurances = { "A La Carte Healthcare",
			"ACE European Group Limited", "AIG Direct",
			"Allianz Worldwide Care Limited", "Amariz Health Insurance",
			"ANT Insurance", "ASDA Finance Services", "AXA Insurance", "AXA PPP Health Insurance",
			"AXA Sunlife", "B and CE Insurance", "Barclays Insurance", "BCWA Healthcare",
			"Benenden Healthcare Society Limited", "BHSF Health Insurance", "Bolton and District Hospital Saturday",
			"Boots Insurance", "Bright Grey", "British Friendly Society Limited", "British Insurance Limited", "Bupa", "Capital Healthcare",
			"Castle Cover", "CI Healthcare", "CIGNA", "Cirencester Friendly Society Limited", "CIS Health Insurance", "Combined Insurance", "Countrywide",
			"CS Healthcare", "Dencover Insurance", "Denplan Insurance", "Dentists and General Insurance", "Direct Line Life"};

	/**
	 * Create the frame.
	 */
	public Profile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		// MENU: Panel
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 1000, 50);
		menuPanel.setLayout(null);
		menuPanel.setBackground(green);

		// MENU: button - back
		JButton btnBack = new JButton();
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home home = new Home();
				home.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setIcon(new ImageIcon("images/back_inverted.png"));
				btnBack.setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setIcon(new ImageIcon("images/back.png"));
				btnBack.setOpaque(false);
			}
		});
		btnBack.setBackground(white);
		btnBack.setIcon(new ImageIcon("images/back.png"));
		btnBack.setBounds(37, 0, 50, 50);
		btnBack.setBorderPainted(false);
		menuPanel.add(btnBack);
		
		


		// MENU: button - save
		JButton btnSave = new JButton();
		
		btnSave.setBackground(grey);
		btnSave.setIcon(new ImageIcon("images/save.png"));
		btnSave.setBounds(906, 0, 50, 50);
		btnSave.setBorderPainted(false);
		menuPanel.add(btnSave);

		contentPane.add(menuPanel);

		// ===============================================================================================
		// Main Panel

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 50, 1000, 528);
		mainPanel.setBackground(grey);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		JButton lblCamera = new JButton();
		lblPatientPhoto = new JLabel();

		lblCamera.setBounds(29, 20, 50, 30);		
		lblCamera.setBorderPainted(false);

		lblCamera.setIcon(new ImageIcon("images/camera.png"));
		lblCamera.addMouseListener(new MouseAdapter() {
			
			// source: http://stackoverflow.com/questions/13517770/jfilechooser-filters
			@Override
			public void mouseClicked(MouseEvent e) {
				File imgFile = null;
				FileFilter imageFilter = new FileNameExtensionFilter(
						"Image files", ImageIO.getReaderFileSuffixes()); 
				JFileChooser fc = new JFileChooser();

				fc.addChoosableFileFilter(imageFilter);
				fc.setAcceptAllFileFilterUsed(false);

				int status = fc.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					imgFile = fc.getSelectedFile();
					System.out.println(imgFile.getPath());
					String ext = "." + FilenameUtils.getExtension(imgFile.getAbsolutePath()); // http://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
					new CopyFile(imgFile.getAbsolutePath(), "images/profile" + ext);
					
					InputStream is;
					try {
						is = new BufferedInputStream(new FileInputStream("images/profile" + ext));
						Image savedImg = ImageIO.read(is);
						savedImg = savedImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
						lblPatientPhoto.setIcon(new ImageIcon(savedImg));

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});
		mainPanel.add(lblCamera);

		
		lblPatientPhoto.setBounds(37, 20, 200, 200);
		lblPatientPhoto.setBackground(green);
		lblPatientPhoto.setOpaque(true);
		mainPanel.add(lblPatientPhoto);

		lblFullName = new JLabel();

		// firstname
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(37, 230, 75, 30);
		mainPanel.add(lblFirstname);

		tfFirstName = new JTextField();
		tfFirstName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				lblFullName.setText(tfFirstName.getText() + " "
						+ tfLastName.getText());
			}
		});
		tfFirstName.setBounds(107, 230, 135, 30);
		mainPanel.add(tfFirstName);
		tfFirstName.setColumns(10);

		// lastname
		JLabel lblLastName = new JLabel("Lastname:");
		lblLastName.setBounds(37, 260, 75, 30);
		mainPanel.add(lblLastName);

		tfLastName = new JTextField();
		tfLastName.setBounds(107, 260, 135, 30);
		tfLastName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				lblFullName.setText(tfFirstName.getText() + " "
						+ tfLastName.getText());
			}
		});
		mainPanel.add(tfLastName);
		tfLastName.setColumns(10);

		// fullname
		lblFullName.setText(tfFirstName.getText() + " " + tfLastName.getText());
		lblFullName.setForeground(green);
		lblFullName.setFont(new Font(lblFullName.getFont().toString(),Font.BOLD, 30));
		lblFullName.setBounds(260, 20, 340, 30);
		mainPanel.add(lblFullName);

		// DOB
		JLabel lblDoB = new JLabel("DOB:");
		lblDoB.setBounds(37, 290, 75, 30);
		mainPanel.add(lblDoB);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(622, 20, 70, 30);
		lblAge.setFont(new Font(lblAge.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblAge);

		JLabel lblAgeCalculated = new JLabel();
		lblAgeCalculated.setBounds(657, 20, 75, 30);
		mainPanel.add(lblAgeCalculated);

		// Street
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(37, 320, 70, 30);
		mainPanel.add(lblStreet);

		tfStreet = new JTextField();
		tfStreet.setBounds(107, 320, 135, 30);
		mainPanel.add(tfStreet);
		tfStreet.setColumns(10);

		// PostCode
		JLabel lblPostCode = new JLabel("Postcode:");
		lblPostCode.setBounds(37, 350, 70, 30);
		mainPanel.add(lblPostCode);

		tfPostCode = new JTextField();
		tfPostCode.setBounds(107, 350, 135, 30);
		mainPanel.add(tfPostCode);
		tfPostCode.setColumns(10);

		// City
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(37, 380, 70, 30);
		mainPanel.add(lblCity);

		tfCity = new JTextField();
		tfCity.setBounds(107, 380, 135, 30);
		mainPanel.add(tfCity);
		tfCity.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(37, 410, 75, 30);
		mainPanel.add(lblPhone);

		JLabel lblEmergency = new JLabel("Emergency:");
		lblEmergency.setBounds(37, 440, 75, 30);
		mainPanel.add(lblEmergency);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(37, 470, 75, 30);
		mainPanel.add(lblGender);

		tfEmergency = new JTextField();
		tfEmergency.setColumns(10);
		tfEmergency.setBounds(107, 440, 135, 30);
		mainPanel.add(tfEmergency);

		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setColumns(10);
		tfMedicalCondition.setBounds(255, 140, 350, 30);
		mainPanel.add(tfMedicalCondition);

		JLabel lblMedicalCondition = new JLabel("Medical Condition:");
		lblMedicalCondition.setBounds(260, 115, 121, 30);
		lblMedicalCondition.setFont(new Font(lblMedicalCondition.getFont()
				.toString(), Font.BOLD, 12));
		mainPanel.add(lblMedicalCondition);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(107, 410, 135, 30);
		mainPanel.add(tfPhoneNumber);

		DefaultListModel listModel = new DefaultListModel();
		
		
		JList list = new JList(listModel);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	
		        evt.getSource();
		        if (evt.getClickCount() == 2) {
					 final ImageIcon icon = new ImageIcon("images/profile.jpg");
				        JOptionPane.showMessageDialog(null, null, null, JOptionPane.INFORMATION_MESSAGE, icon);
		        }
		    }
		});

		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBounds(622, 145, 340, 320);
		
		list.setCellRenderer(new BookCellRenderer());
		list.setVisibleRowCount(4);
		
		mainPanel.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(622, 145, 340, 320);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollPane);

		// source: http://toedter.com/jcalendar/
		dateDOB = new JDateChooser();

		System.out.println(dateDOB.getDate());

		// source:
		// http://stackoverflow.com/questions/24509189/jdatechooser-focuslistener
		dateDOB.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {

				// TODO: implement algorithm that deletes age when dateDOB ==
				// null
				Calendar dob = Calendar.getInstance();
				dob.setTime((Date) e.getNewValue());

				Calendar today = Calendar.getInstance();
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

				if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)
						|| (today.get(Calendar.MONTH) == dob
								.get(Calendar.MONTH) && today
								.get(Calendar.DAY_OF_MONTH) < dob
								.get(Calendar.DAY_OF_MONTH))) {
					age--;
				}
				lblAgeCalculated.setText(String.valueOf(age) + " years");
			}
		});
		dateDOB.setBounds(107, 290, 135, 30);
		dateDOB.setBackground(grey);
		dateDOB.setBorder(BorderFactory.createEmptyBorder());

		if (dateDOB.getDate() == null) {
			dateDOB.setMaxSelectableDate(new Date());
		}

		mainPanel.add(dateDOB);

		JDateChooser dateAppointment = new JDateChooser();
		dateAppointment.setBounds(385, 68, 220, 28);
		dateAppointment.setBackground(grey);
		dateAppointment.setBorder(BorderFactory.createEmptyBorder());
		dateAppointment.setDateFormatString("MMMMM d, yyyy");

		// source:
		// http://stackoverflow.com/questions/22092365/hide-or-disable-past-dates-on-jdatechooser
		if (dateAppointment.getDate() == null) {
			dateAppointment.setMinSelectableDate(new Date());
		}

		mainPanel.add(dateAppointment);

		JComboBox cbInsurance = new JComboBox();
		cbInsurance.setBounds(692, 69, 270, 27);

		for (int count = 0; count < insurances.length; count++) {
			cbInsurance.addItem(insurances[count]);
		}

		mainPanel.add(cbInsurance);



		JButton btnUpload = new JButton("Upload");
		btnUpload.addMouseListener(new MouseAdapter() {
			
			// source: http://stackoverflow.com/questions/13517770/jfilechooser-filters
			@Override
			public void mouseClicked(MouseEvent e) {
				File imgFile = null;
				FileFilter imageFilter = new FileNameExtensionFilter(
						"Image files", ImageIO.getReaderFileSuffixes()); 
				JFileChooser fc = new JFileChooser();

				fc.addChoosableFileFilter(imageFilter);
				fc.setAcceptAllFileFilterUsed(false);

				int status = fc.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					imgFile = fc.getSelectedFile();
					System.out.println(imgFile.getPath());
					String ext = "." + FilenameUtils.getExtension(imgFile.getAbsolutePath()); // http://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
					new CopyFile(imgFile.getAbsolutePath(), "images/profile" + ext);
					
					InputStream is;
					try {
						is = new BufferedInputStream(new FileInputStream("images/profile" + ext));
						Image savedImg = ImageIO.read(is);
						savedImg = savedImg.getScaledInstance(150, 100, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
						
						listModel.addElement(new BookEntry("hello", new ImageIcon(savedImg)));

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});
		btnUpload.setBounds(615, 472, 177, 29);
		mainPanel.add(btnUpload);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnDelete.setBounds(792, 472, 177, 29);
		mainPanel.add(btnDelete);

		JLabel lblMedicalImages = new JLabel("Medical Images:");
		lblMedicalImages.setBounds(622, 115, 121, 30);
		lblMedicalImages.setFont(new Font(
				lblMedicalImages.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblMedicalImages);

		JLabel lblInsuranceCompany = new JLabel("Insurance:");
		lblInsuranceCompany.setBounds(622, 73, 80, 16);
		lblInsuranceCompany.setFont(new Font(lblInsuranceCompany.getFont()
				.toString(), Font.BOLD, 12));
		mainPanel.add(lblInsuranceCompany);

		JLabel lblNextAppointment = new JLabel("Next Appointment:");
		lblNextAppointment.setBounds(260, 73, 130, 16);
		lblNextAppointment.setFont(new Font(lblNextAppointment.getFont()
				.toString(), Font.BOLD, 12));
		mainPanel.add(lblNextAppointment);

		JButton btnSearchGoogle = new JButton("Search Google");
		btnSearchGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchGoogle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(
								new URI("http://www.google.com/search?q="
										+ tfMedicalCondition.getText()
												.replaceAll(" ", "+")));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearchGoogle.setBounds(253, 177, 177, 29);
		mainPanel.add(btnSearchGoogle);

		JButton btnSearchWikipedia = new JButton("Search Wikipedia");
		btnSearchWikipedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(
								new URI(
										"https://en.wikipedia.org/w/index.php?search="
												+ tfMedicalCondition.getText()
														.replaceAll(" ", "+")));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnSearchWikipedia.setBounds(430, 177, 177, 29);
		mainPanel.add(btnSearchWikipedia);

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(260, 205, 121, 30);
		lblComments.setFont(new Font(lblComments.getFont().toString(),
				Font.BOLD, 12));
		mainPanel.add(lblComments);

		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setBackground(white);
		label.setBounds(260, 110, 700, 2);
		mainPanel.add(label);
		
		JComboBox cbGender = new JComboBox();
		cbGender.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {

				String iconPath = null;
				
				try{
				iconPath = lblPatientPhoto.getIcon().toString();
				}
				catch (Exception e1){
					// TODO: implement logger
				}
				
				if (lblPatientPhoto.getIcon() == null || iconPath.equals("images/default_female.png") ){
					lblCamera.setIcon(new ImageIcon("images/camera.png"));
					lblPatientPhoto.setIcon(new ImageIcon("images/default_male.png"));
				}
				else if (iconPath.equals("images/default_male.png")){
					lblCamera.setIcon(new ImageIcon("images/camera.png"));
					lblPatientPhoto.setIcon(new ImageIcon("images/default_female.png"));
				}
				
			
		}

		});
		cbGender.setBounds(106, 472, 136, 30);
		cbGender.addItem(new String("Male"));
		cbGender.addItem(new String("Female"));
		mainPanel.add(cbGender);
		
		JLabel lblBilling = new JLabel("Billing:");
		lblBilling.setBounds(744, 20, 43, 30);
		lblBilling.setFont(new Font(lblBilling.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblBilling);
		
		JComboBox cbBilling = new JComboBox();
		cbBilling.setBounds(792, 20, 170, 30);
		cbBilling.addItem(new String("Paid"));
		cbBilling.addItem(new String("Payment outstanding"));
		mainPanel.add(cbBilling);
		
		JTextArea taComments = new JTextArea();
		taComments.setBounds(255, 233, 350, 263);
		taComments.setEditable(true);

		JScrollPane sp = new JScrollPane(taComments);
		sp.setBounds(255, 233, 350, 263);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(sp);
			
		
		btnSave.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("resource")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Connection connection = SQLiteConnector.dbConnector();

				PreparedStatement pst = null;
				int count = 0;

				// source: https://www.youtube.com/watch?v=6cNYUc2PIag
				try {		
					
					
					Statement stmt = connection.createStatement();
					String query1 = "select count(*) from PatientInfo";
					ResultSet rs = stmt.executeQuery(query1);
					
					while (rs.next()){
						count++;
					}
					
					rs.close();
					stmt.close();
					
					System.out.println(count);		
								
					
					String query = "insert into PatientInfo (firstName, lastName, dob, street, postCode, city, phoneNumber, emergencyNumber, gender, medicalCondition, billing, comment, insurance, profilePhoto, nextAppointment) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					pst = connection.prepareStatement(query);
					
					pst.setString(1, tfFirstName.getText());
					pst.setString(2, tfLastName.getText());
					pst.setString(3, dateDOB.getDate().toString());
					pst.setString(4, tfStreet.getText());
					pst.setString(5, tfPostCode.getText());
					pst.setString(6, tfCity.getText());
					pst.setString(7, tfPhoneNumber.getText());
					pst.setString(8, tfEmergency.getText());
					pst.setString(9, cbGender.getSelectedItem().toString());
					pst.setString(10, tfMedicalCondition.getText());
					pst.setString(11, cbBilling.getSelectedItem().toString());
					pst.setString(12, taComments.getText());
					pst.setString(13, cbInsurance.getSelectedItem().toString());
					pst.setString(14, "hello");
					pst.setString(15, dateAppointment.getDate().toString());

					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Saved");

					pst.close();
				}
				catch (SQLException e1) {
					System.err.println("No connection with SQLite possible.");
					e1.printStackTrace();
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnSave.setIcon(new ImageIcon("images/save_inverted.png"));
				btnSave.setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSave.setIcon(new ImageIcon("images/save.png"));
				btnSave.setOpaque(false);
			}
		});
		

		

		contentPane.setVisible(true);
		setContentPane(contentPane);
	}
}
