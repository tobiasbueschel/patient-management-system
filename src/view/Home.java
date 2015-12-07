package view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
import javax.swing.JTable;


public class Home extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);
	private Color darkGrey = new Color(112,112,112);
	private Color white = new Color(255,255,255);
	private JTextField txtSearch;
	private JTable table;
	private JPanel mainPanel;


	/**
	 * Create the frame.
	 */
	public Home() {
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
		
		// MENU: title
		JLabel lblTitle = new JLabel("PRMS");
		lblTitle.setForeground(white);
		lblTitle.setBackground(green);
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblTitle.setBounds(10, 0, 70, 50);
		lblTitle.setHorizontalAlignment(lblTitle.CENTER);
		lblTitle.setOpaque(true);
		menuPanel.add(lblTitle);
		
		// MENU: search selector
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(white);
		comboBox.setBackground(green);
		comboBox.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		comboBox.setBounds(225, 10, 134, 35);
		comboBox.setOpaque(true);
		menuPanel.add(comboBox);
		
		// MENU: search field
		txtSearch = new JTextField();
		txtSearch.setText("Search...");
		txtSearch.setBounds(370, 12, 176, 28);
		txtSearch.setColumns(10);
		menuPanel.add(txtSearch);
		
		
		// MENU: button - new patient
		JButton btnNewPatient = new JButton ();
		btnNewPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Profile profile = new Profile();
				profile.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewPatient.setIcon(new ImageIcon("images/new_inverted.png"));
				btnNewPatient.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewPatient.setIcon(new ImageIcon("images/new.png"));
				btnNewPatient.setOpaque(false);
			}
		});
		btnNewPatient.setBackground(white);
		btnNewPatient.setIcon(new ImageIcon("images/new.png"));
		btnNewPatient.setBounds(640, 0, 50, 50);
		btnNewPatient.setBorderPainted(false);
	    menuPanel.add(btnNewPatient);
		
	    
		// MENU: button - select all
		JButton btnSelect = new JButton ();
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSelect.setIcon(new ImageIcon("images/select_inverted.png"));
				btnSelect.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSelect.setIcon(new ImageIcon("images/select.png"));
				btnSelect.setOpaque(false);
			}
		});
		btnSelect.setBackground(white);
		btnSelect.setIcon(new ImageIcon("images/select.png"));
		btnSelect.setBounds(700, 0, 50, 50);
		btnSelect.setBorderPainted(false);
	    menuPanel.add(btnSelect);
	    
	    
		// MENU: button - import
		JButton btnImport = new JButton ();
		btnImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnImport.setIcon(new ImageIcon("images/import_inverted.png"));
				btnImport.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnImport.setIcon(new ImageIcon("images/import.png"));
				btnImport.setOpaque(false);
			}
		});
		btnImport.setBackground(white);
		btnImport.setIcon(new ImageIcon("images/import.png"));
		btnImport.setBounds(760, 0, 50, 50);
		btnImport.setBorderPainted(false);
	    menuPanel.add(btnImport);
		
		// MENU: button - export
		JButton btnExport = new JButton ();
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExport.setIcon(new ImageIcon("images/export_inverted.png"));
				btnExport.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExport.setIcon(new ImageIcon("images/export.png"));
				btnExport.setOpaque(false);
			}
		});
		btnExport.setBackground(white);
		btnExport.setIcon(new ImageIcon("images/export.png"));
		btnExport.setBounds(820, 0, 50, 50);
		btnExport.setBorderPainted(false);
	    menuPanel.add(btnExport);
		
		// MENU: button - delete
		JButton btnDelete = new JButton ();
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setIcon(new ImageIcon("images/delete_inverted.png"));
				btnDelete.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setIcon(new ImageIcon("images/delete.png"));
				btnDelete.setOpaque(false);
			}
		});
		btnDelete.setBackground(white);
		btnDelete.setIcon(new ImageIcon("images/delete.png"));
		btnDelete.setBounds(880, 0, 50, 50);
		btnDelete.setBorderPainted(false);
	    menuPanel.add(btnDelete);
		
		// MENU: button - logout
		JButton btnLogout = new JButton ();
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon("images/logout_inverted.png"));
				btnLogout.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon("images/logout.png"));
				btnLogout.setOpaque(false);
			}
		});
		btnLogout.setBackground(white);
		btnLogout.setIcon(new ImageIcon("images/logout.png"));
		btnLogout.setBounds(940, 0, 50, 50);
		btnLogout.setBorderPainted(false);
	    menuPanel.add(btnLogout);
		
		contentPane.add(menuPanel);

		
		
		// Main Panel
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 50, 1000, 528);
		mainPanel.setBackground(grey);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		


		table = new JTable();
		table.setBounds(37, 20, 926, 477);
		mainPanel.add(table);

		
		
		contentPane.setVisible(true);
		setContentPane(contentPane);
	}
}
