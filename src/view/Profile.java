package view;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;


public class Profile extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);
	private Color darkGrey = new Color(112,112,112);
	private Color white = new Color(255,255,255);
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfDob;
	private JTextField tfStreet;
	private JTextField tfPostCode;
	private JTextField tfCity;
	private JTextField tfPhoneNumber;
	private JTextField tfBilling;
	private JTextField tfEmergency;
	private JTextField tfMedicalCondition;
	

	

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
		lblPatientPhoto.setBounds(20, 20, 180, 180);
		lblPatientPhoto.setBackground(green);
		lblPatientPhoto.setOpaque(true);
		mainPanel.add(lblPatientPhoto);
		
		
		// fullname
		JLabel FullName = new JLabel();
		FullName.setBounds(234, 25, 70, 30);
		mainPanel.add(FullName);
		
		// firstname
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(30, 210, 75, 30);
		mainPanel.add(lblFirstname);
	
		tfFirstName = new JTextField();
		tfFirstName.setBounds(100, 210, 130, 30);
		mainPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		// lastname
		JLabel lblLastName = new JLabel("Lastname:");
		lblLastName.setBounds(30, 240, 75, 30);
		mainPanel.add(lblLastName);
	
		tfLastName = new JTextField();
		tfLastName.setBounds(100, 240, 130, 30);
		mainPanel.add(tfLastName);
		tfLastName.setColumns(10);
		
		// DOB
		JLabel lblDoB = new JLabel("DOB:");
		lblDoB.setBounds(30, 270, 75, 30);
		mainPanel.add(lblDoB);
	
		tfDob = new JTextField();
		tfDob.setBounds(100, 270, 130, 30);
		mainPanel.add(tfDob);
		tfDob.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(234, 163, 70, 30);
		mainPanel.add(lblAge);
		
		JLabel lblAgeCalculated = new JLabel();
		lblAgeCalculated.setBounds(304, 157, 70, 30);
		mainPanel.add(lblAgeCalculated);
		
		
		
		// Street
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(30, 300, 70, 30);
		mainPanel.add(lblStreet);
	
		tfStreet = new JTextField();
		tfStreet.setBounds(100, 300, 130, 30);
		mainPanel.add(tfStreet);
		tfStreet.setColumns(10);
		
		// PostCode
		JLabel lblPostCode = new JLabel("Postcode:");
		lblPostCode.setBounds(30, 330, 70, 30);
		mainPanel.add(lblPostCode);
	
		tfPostCode = new JTextField();
		tfPostCode.setBounds(100, 330, 130, 30);
		mainPanel.add(tfPostCode);
		tfPostCode.setColumns(10);
		
		// City
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(30, 360, 70, 30);
		mainPanel.add(lblCity);
	
		tfCity = new JTextField();
		tfCity.setBounds(100, 360, 130, 30);
		mainPanel.add(tfCity);
		tfCity.setColumns(10);
		
		
		JLabel lblPhoneNumber = new JLabel("Phone:");
		lblPhoneNumber.setBounds(30, 390, 75, 30);
		mainPanel.add(lblPhoneNumber);
		
		JLabel lblEmergencyPhone = new JLabel("Emergency:");
		lblEmergencyPhone.setBounds(30, 420, 75, 30);
		mainPanel.add(lblEmergencyPhone);
		
		JLabel lblBillingStatus = new JLabel("Billing:");
		lblBillingStatus.setBounds(30, 450, 75, 30);
		mainPanel.add(lblBillingStatus);
		
		tfBilling = new JTextField();
		tfBilling.setColumns(10);
		tfBilling.setBounds(100, 450, 130, 30);
		mainPanel.add(tfBilling);
		
		tfEmergency = new JTextField();
		tfEmergency.setColumns(10);
		tfEmergency.setBounds(100, 420, 130, 30);
		mainPanel.add(tfEmergency);
		
		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setColumns(10);
		tfMedicalCondition.setBounds(589, 163, 341, 30);
		mainPanel.add(tfMedicalCondition);
		
		JLabel lblMedicalCondition = new JLabel("Medical Condition:");
		lblMedicalCondition.setBounds(454, 163, 121, 30);
		lblMedicalCondition.setFont(new Font(lblMedicalCondition.getFont().toString(), Font.BOLD, 12));
		mainPanel.add(lblMedicalCondition);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(100, 390, 130, 30);
		mainPanel.add(tfPhoneNumber);
	
		
		JLabel lblPatientInfoBG = new JLabel("");
		lblPatientInfoBG.setOpaque(true);
		lblPatientInfoBG.setBackground(grey);
		lblPatientInfoBG.setBounds(20, 199, 220, 310);
		mainPanel.add(lblPatientInfoBG);

		
		contentPane.setVisible(true);
		setContentPane(contentPane);
	}
}
