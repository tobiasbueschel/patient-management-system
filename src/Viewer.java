import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Viewer extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);

	private JPasswordField pwdPassword;
	private JTextField textField;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer frame = new Viewer();
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
	public Viewer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/Tobias/Dropbox/UCL/Programming/Java/PMS/images/background.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setResizable(false);
		setContentPane(contentPane);
		
		// adds LOGIN button
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(345, 429, 310, 40);
		btnLogin.setForeground(SystemColor.text);
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("hello");
			}
		});
		contentPane.setLayout(null);
		btnLogin.setBackground(green);
		btnLogin.setBorderPainted(false);
		btnLogin.setOpaque(true);
		contentPane.add(btnLogin);
		
		// username field
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textField.selectAll();
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		textField.setBackground(grey);
		textField.setSelectionColor(green);
		textField.setBounds(345, 291, 310, 40);
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setText("Username");
		contentPane.add(textField);
		
		// password field
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.selectAll();
			}
		});
		pwdPassword.setBounds(345, 360, 310, 40);
		pwdPassword.setBackground(grey);
		pwdPassword.setSelectionColor(green);
		pwdPassword.setBorder(BorderFactory.createEmptyBorder());
		pwdPassword.setBorder(BorderFactory.createCompoundBorder(pwdPassword.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		pwdPassword.setHorizontalAlignment(JPasswordField.CENTER);
		pwdPassword.setText("Password");
		contentPane.add(pwdPassword);
		
		
		// form Header
		JLabel formHeader = new JLabel("");
		formHeader.setBackground(grey);
		formHeader.setOpaque(true);
		formHeader.setBounds(325, 90, 350, 177);
		contentPane.add(formHeader);

		// form background
		JLabel backgroundForm = new JLabel("");
		backgroundForm.setBounds(325, 90, 350, 400);
		backgroundForm.setBackground(SystemColor.menu);
		backgroundForm.setOpaque(true);
		contentPane.add(backgroundForm);
		
		// background image
		JLabel backgroundApp = new JLabel("background");
		backgroundApp.setBounds(0, 0, 1000, 600);
		backgroundApp.setIcon(new ImageIcon("images/background.png"));
		contentPane.add(backgroundApp);

		
		
		contentPane.setVisible(true);
	}
}
