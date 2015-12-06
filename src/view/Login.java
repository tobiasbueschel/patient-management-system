package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.SQLiteConnector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0, 189, 154);
	private Color grey = new Color(222, 222, 222);
	private Color darkGrey = new Color(112, 112, 112);
	private Color white = new Color(255, 255, 255);

	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setResizable(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// LOGIN FORM: header: logo
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/logo.png"));
		lblNewLabel.setBounds(467, 130, 60, 50);
		contentPane.add(lblNewLabel);

		// LOGIN FORM: header: title
		JLabel llbWelcome = new JLabel("WELCOME");
		llbWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		llbWelcome.setForeground(green);
		llbWelcome.setHorizontalAlignment(JLabel.CENTER);
		llbWelcome.setBounds(345, 180, 310, 61);
		contentPane.add(llbWelcome);

		// LOGIN FORM: header: background
		JLabel formHeader = new JLabel("");
		formHeader.setBackground(grey);
		formHeader.setOpaque(true);
		formHeader.setBounds(325, 90, 350, 177);
		contentPane.add(formHeader);

		// LOGIN FORM: body: username
		textFieldUN = new JTextField("Username");
		textFieldUN.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textFieldUN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldUN.selectAll();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				textFieldUN.setForeground(green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				textFieldUN.setForeground(darkGrey);
			}
		});
		textFieldUN.setBackground(grey);
		textFieldUN.setSelectionColor(green);
		textFieldUN.setBounds(345, 291, 310, 40);
		textFieldUN.setBorder(BorderFactory.createEmptyBorder());
		textFieldUN.setBorder(BorderFactory.createCompoundBorder(
				textFieldUN.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textFieldUN.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textFieldUN);

		// LOGIN FORM: body: password
		passwordField = new JPasswordField("Password");
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.selectAll();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				passwordField.setForeground(green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				passwordField.setForeground(darkGrey);
			}
		});
		passwordField.setBounds(345, 360, 310, 40);
		passwordField.setBackground(grey);
		passwordField.setSelectionColor(green);
		passwordField.setBorder(BorderFactory.createEmptyBorder());
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				passwordField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		contentPane.add(passwordField);

		// LOGIN FORM: body: login button source: https://www.youtube.com/watch?v=IGTL5mvYU54
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = SQLiteConnector.dbConnector();

				try {
					String query = "select * from user where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, String.valueOf(passwordField.getPassword()));

					ResultSet rs = pst.executeQuery();
					int count = 0;

					while (rs.next()) {
						count++;
					}

					if (count == 1) {
						JOptionPane.showMessageDialog(null,"Username and password is correct!");

						Home home = new Home();
						home.setVisible(true);
						dispose();

					} else if (count > 1) {
						JOptionPane.showMessageDialog(null,
								"Duplicate username and password!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Username or password is not correct!");
					}
					
					rs.close();
					pst.close();
				}

				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		btnLogin.setBounds(345, 429, 310, 40);
		btnLogin.setForeground(white);
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBackground(green);
		btnLogin.setBorderPainted(false);
		btnLogin.setOpaque(true);
		contentPane.add(btnLogin);

		// LOGIN FORM: background
		JLabel backgroundForm = new JLabel("");
		backgroundForm.setBounds(325, 90, 350, 400);
		backgroundForm.setBackground(white);
		backgroundForm.setOpaque(true);
		contentPane.add(backgroundForm);

		// BACKGROUND IMAGE
		JLabel backgroundApp = new JLabel("background");
		backgroundApp.setBounds(0, 0, 1000, 600);
		backgroundApp.setIcon(new ImageIcon("images/background.png"));
		contentPane.add(backgroundApp);
		
		contentPane.getRootPane().setDefaultButton(btnLogin); // allows user to press enter when logging in source: http://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
		contentPane.setVisible(true);
	}
}
