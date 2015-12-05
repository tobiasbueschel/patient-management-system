package view;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);
	private Color darkGrey = new Color(112,112,112);
	private Color white = new Color(255,255,255);

	private JPasswordField pwdPassword;
	private JTextField textField;

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
		textField = new JTextField("Username");
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.selectAll();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				textField.setForeground(green);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textField.setForeground(darkGrey);
			}
		});
		textField.setBackground(grey);
		textField.setSelectionColor(green);
		textField.setBounds(345, 291, 310, 40);
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textField.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textField);
		
		// LOGIN FORM: body: password
		pwdPassword = new JPasswordField("Password");
		pwdPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.selectAll();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pwdPassword.setForeground(green);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pwdPassword.setForeground(darkGrey);
			}
		});
		pwdPassword.setBounds(345, 360, 310, 40);
		pwdPassword.setBackground(grey);
		pwdPassword.setSelectionColor(green);
		pwdPassword.setBorder(BorderFactory.createEmptyBorder());
		pwdPassword.setBorder(BorderFactory.createCompoundBorder(pwdPassword.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		pwdPassword.setHorizontalAlignment(JPasswordField.CENTER);		
		contentPane.add(pwdPassword);
				
		// LOGIN FORM: body: login button
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
				dispose();
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
				
		contentPane.setVisible(true);
	}
	
}
