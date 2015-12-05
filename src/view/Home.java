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


public class Home extends JFrame {

	private JPanel contentPane;
	private Color green = new Color(0,189,154);
	private Color grey = new Color(222,222,222);
	private Color darkGrey = new Color(112,112,112);
	private Color white = new Color(255,255,255);
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(green);

		// MENU: import
		JMenu jmImport = new JMenu ("Import");
		menuBar.add(jmImport);
		
		// MENU: export
		JMenu jmExport = new JMenu ("Export");
		menuBar.add(jmExport);
		jmExport.setOpaque(true);
		jmExport.setBackground(green);
		
		JMenuItem menuItem = new JMenuItem("Test");
		jmExport.add(menuItem);

		
		textField = new JTextField();
		menuBar.add(textField);
		
		// HEADER: title
		JLabel lblHeader = new JLabel("PRMS");
		lblHeader.setForeground(white);
		lblHeader.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblHeader.setOpaque(true);
		menuBar.add(lblHeader);
		
		setJMenuBar(menuBar);
		


//		
//		JComboBox comboBox = new JComboBox();
//		menuBar.add(comboBox);
		


		
		setContentPane(contentPane);
		
		
		
		contentPane.setLayout(new MigLayout("", "[]", "[]"));

		
				
		contentPane.setVisible(true);
	}
}
