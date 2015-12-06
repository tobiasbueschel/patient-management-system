package view;
import java.awt.Color;
import java.awt.Desktop;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;


public class Profile extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);
	private Color darkGrey = new Color(112,112,112);
	private Color white = new Color(255,255,255);
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfStreet;
	private JTextField tfPostCode;
	private JTextField tfCity;
	private JTextField tfPhoneNumber;
	private JTextField tfGender;
	private JTextField tfEmergency;
	private JTextField tfMedicalCondition;
	private JTextField textField;
	

	

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
		btnBack.setBounds(10, 0, 50, 50);
		btnBack.setBorderPainted(false);
	    menuPanel.add(btnBack);
		
		
		// MENU: button - save
		JButton btnLogout = new JButton ();
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// do something
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon("images/save_inverted.png"));
				btnLogout.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon("images/save.png"));
				btnLogout.setOpaque(false);
			}
		});
		btnLogout.setBackground(white);
		btnLogout.setIcon(new ImageIcon("images/save.png"));
		btnLogout.setBounds(940, 0, 50, 50);
		btnLogout.setBorderPainted(false);
	    menuPanel.add(btnLogout);
		
		contentPane.add(menuPanel);

		// ===============================================================================================
		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 50, 1000, 528);
		mainPanel.setBackground(white);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblPatientPhoto = new JLabel("Patient Photo");
		lblPatientPhoto.setBounds(20, 20, 220, 200);
		lblPatientPhoto.setBackground(green);
		lblPatientPhoto.setOpaque(true);
		mainPanel.add(lblPatientPhoto);
		
		
		// fullname
		JLabel FullName = new JLabel();
		FullName.setBounds(260, 31, 340, 30);
		mainPanel.add(FullName);
		
		// firstname
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(30, 230, 75, 30);
		mainPanel.add(lblFirstname);
	
		tfFirstName = new JTextField();
		tfFirstName.setBounds(100, 230, 140, 30);
		mainPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		// lastname
		JLabel lblLastName = new JLabel("Lastname:");
		lblLastName.setBounds(30, 260, 75, 30);
		mainPanel.add(lblLastName);
	
		tfLastName = new JTextField();
		tfLastName.setBounds(100, 260, 140, 30);
		mainPanel.add(tfLastName);
		tfLastName.setColumns(10);
		
		// DOB
		JLabel lblDoB = new JLabel("DOB:");
		lblDoB.setBounds(30, 290, 75, 30);
		mainPanel.add(lblDoB);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(622, 31, 70, 30);
		mainPanel.add(lblAge);
		
		JLabel lblAgeCalculated = new JLabel();
		lblAgeCalculated.setBounds(253, 262, 70, 30);
		mainPanel.add(lblAgeCalculated);
		
		
		
		// Street
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(30, 320, 70, 30);
		mainPanel.add(lblStreet);
	
		tfStreet = new JTextField();
		tfStreet.setBounds(100, 320, 140, 30);
		mainPanel.add(tfStreet);
		tfStreet.setColumns(10);
		
		// PostCode
		JLabel lblPostCode = new JLabel("Postcode:");
		lblPostCode.setBounds(30, 350, 70, 30);
		mainPanel.add(lblPostCode);
	
		tfPostCode = new JTextField();
		tfPostCode.setBounds(100, 350, 140, 30);
		mainPanel.add(tfPostCode);
		tfPostCode.setColumns(10);
		
		// City
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(30, 380, 70, 30);
		mainPanel.add(lblCity);
	
		tfCity = new JTextField();
		tfCity.setBounds(100, 380, 140, 30);
		mainPanel.add(tfCity);
		tfCity.setColumns(10);
		
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(30, 410, 75, 30);
		mainPanel.add(lblPhone);
		
		JLabel lblEmergency = new JLabel("Emergency:");
		lblEmergency.setBounds(30, 440, 75, 30);
		mainPanel.add(lblEmergency);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(30, 470, 75, 30);
		mainPanel.add(lblGender);
		
		tfGender = new JTextField();
		tfGender.setColumns(10);
		tfGender.setBounds(100, 470, 140, 30);
		mainPanel.add(tfGender);
		
		tfEmergency = new JTextField();
		tfEmergency.setColumns(10);
		tfEmergency.setBounds(100, 440, 140, 30);
		mainPanel.add(tfEmergency);
		
		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setColumns(10);
		tfMedicalCondition.setBounds(255, 140, 350, 30);
		mainPanel.add(tfMedicalCondition);
		
		JLabel lblMedicalCondition = new JLabel("Medical Condition:");
		lblMedicalCondition.setBounds(260, 115, 121, 30);
		lblMedicalCondition.setFont(new Font(lblMedicalCondition.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblMedicalCondition);
		

		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(100, 410, 140, 30);
		mainPanel.add(tfPhoneNumber);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(255, 230, 350, 270);
		mainPanel.add(textField);
		
		JList list = new JList();
		list.setToolTipText("");
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(622, 145, 340, 320);
		mainPanel.add(list);
		
		// source: http://toedter.com/jcalendar/
		JDateChooser dateDOB = new JDateChooser();
		dateDOB.setBounds(100, 290, 140, 28);
		dateDOB.setBackground(grey);
		dateDOB.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(dateDOB);
		
		
		JDateChooser dateAppointment = new JDateChooser();
		dateAppointment.setBounds(385, 68, 220, 28);
		dateAppointment.setBackground(grey);
		dateAppointment.setBorder(BorderFactory.createEmptyBorder());
		
		if (dateAppointment.getDate() == null){
			dateAppointment.setMinSelectableDate(new Date()); // source: http://stackoverflow.com/questions/22092365/hide-or-disable-past-dates-on-jdatechooser
		}

		mainPanel.add(dateAppointment);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(692, 69, 270, 27);
		mainPanel.add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(792, 472, 177, 29);
		mainPanel.add(btnDelete);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()); // source: http://stackoverflow.com/questions/13517770/jfilechooser-filters
				JFileChooser fc = new JFileChooser();
				
				fc.addChoosableFileFilter(imageFilter);
				fc.setAcceptAllFileFilterUsed(false);
				
				int status = fc.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION){
					File imgFile = fc.getSelectedFile();
					System.out.println(imgFile.getPath());
				}
				
			}
		});
		btnUpload.setBounds(615, 472, 177, 29);
		mainPanel.add(btnUpload);
		
		
		JLabel lblMedicalImages = new JLabel("Medical Images:");
		lblMedicalImages.setBounds(622, 115, 121, 30);
		lblMedicalImages.setFont(new Font(lblMedicalImages.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblMedicalImages);
		
		
		
		JLabel lblInsuranceCompany = new JLabel("Insurance:");
		lblInsuranceCompany.setBounds(622, 73, 80, 16);
		lblInsuranceCompany.setFont(new Font(lblInsuranceCompany.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblInsuranceCompany);
		
		JLabel lblNextAppointment = new JLabel("Next Appointment:");
		lblNextAppointment.setBounds(260, 73, 130, 16);
		lblNextAppointment.setFont(new Font(lblNextAppointment.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblNextAppointment);
		
		
		
		JButton btnSearchGoogle = new JButton("Search Google");
		btnSearchGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchGoogle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Desktop.isDesktopSupported())
				{
				  try {
					Desktop.getDesktop().browse(new URI("http://www.google.com/search?q=" + tfMedicalCondition.getText().replaceAll(" ", "+")));
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
				if(Desktop.isDesktopSupported())
				{
				  try {
					Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/w/index.php?search=" + tfMedicalCondition.getText().replaceAll(" ", "+")));
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
		lblComments.setFont(new Font(lblComments.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblComments);
		
		
		
	
		
		JLabel lblPatientInfoBG = new JLabel("");
		lblPatientInfoBG.setOpaque(true);
		lblPatientInfoBG.setBackground(grey);
		lblPatientInfoBG.setBounds(20, 111, 960, 399);
		mainPanel.add(lblPatientInfoBG);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(222, 222, 222));
		label.setBounds(239, 20, 741, 83);
		mainPanel.add(label);
		



		








		
		contentPane.setVisible(true);
		setContentPane(contentPane);
	}
}
