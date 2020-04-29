package pms.view;

import pms.model.SQLiteConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>The login class constructs the login screen</p>
 *
 * @version 0.7
 */

public class Login extends JFrame {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
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

    /* ================================ Form Header ================================ */
    /** LOGIN FORM: header: logo */
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon("images/logo.png"));
    lblNewLabel.setBounds(467, 130, 60, 50);
    contentPane.add(lblNewLabel);

    /** LOGIN FORM: header: title */
    JLabel llbWelcome = new JLabel("WELCOME");
    llbWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
    llbWelcome.setForeground(CustomColors.green);
    llbWelcome.setHorizontalAlignment(JLabel.CENTER);
    llbWelcome.setBounds(345, 180, 310, 61);
    contentPane.add(llbWelcome);

    /** LOGIN FORM: header: background */
    JLabel formHeader = new JLabel("");
    formHeader.setBackground(CustomColors.grey);
    formHeader.setOpaque(true);
    formHeader.setBounds(325, 90, 350, 177);
    contentPane.add(formHeader);

    /* ================================ Form Body ================================ */
    /** LOGIN FORM: body: username */
    textFieldUN = new JTextField("Username");
    textFieldUN.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
    textFieldUN.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldUN.selectAll();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        textFieldUN.setForeground(CustomColors.green);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        textFieldUN.setForeground(CustomColors.darkGrey);
      }
    });
    textFieldUN.setBackground(CustomColors.grey);
    textFieldUN.setSelectionColor(CustomColors.green);
    textFieldUN.setBounds(345, 291, 310, 40);
    textFieldUN.setBorder(BorderFactory.createEmptyBorder());
    textFieldUN.setBorder(BorderFactory.createCompoundBorder(
      textFieldUN.getBorder(),
      BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    textFieldUN.setHorizontalAlignment(JTextField.CENTER);
    contentPane.add(textFieldUN);

    /** LOGIN FORM: body: password */
    passwordField = new JPasswordField("Password");
    passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
    passwordField.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        passwordField.selectAll();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        passwordField.setForeground(CustomColors.green);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        passwordField.setForeground(CustomColors.darkGrey);
      }
    });
    passwordField.setBounds(345, 360, 310, 40);
    passwordField.setBackground(CustomColors.grey);
    passwordField.setSelectionColor(CustomColors.green);
    passwordField.setBorder(BorderFactory.createEmptyBorder());
    passwordField.setBorder(BorderFactory.createCompoundBorder(
      passwordField.getBorder(),
      BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    passwordField.setHorizontalAlignment(JPasswordField.CENTER);
    contentPane.add(passwordField);

    /** LOGIN FORM: body: login button @link - reference https://www.youtube.com/watch?v=IGTL5mvYU54 */
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

          String query2 = "select * from user";
          PreparedStatement pst2 = connection.prepareStatement(query2);
          ResultSet rs2 = pst2.executeQuery();
          System.out.println(rs2.next());

          int count = 0;

          while (rs.next()) {
            count++;
          }

          /** validates if username & password are correct */
          if (count == 1) {
            Home home = new Home();
            home.setVisible(true);
            dispose();

          } else if (count > 1) {
            JOptionPane.showMessageDialog(null,
              "You have entered a duplicated password or username");
          } else {
            JOptionPane.showMessageDialog(null,
              "Username or Password is not correct. Please try again.");
          }

          rs.close();
          pst.close();
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1);
        } finally {
          try {
            connection.close();
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }

      }
    });
    btnLogin.setBounds(345, 429, 310, 40);
    btnLogin.setForeground(CustomColors.white);
    btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
    btnLogin.setBackground(CustomColors.green);
    btnLogin.setBorderPainted(false);
    btnLogin.setOpaque(true);
    contentPane.add(btnLogin);

    /** LOGIN FORM: background */
    JLabel backgroundForm = new JLabel("");
    backgroundForm.setBounds(325, 90, 350, 400);
    backgroundForm.setBackground(CustomColors.white);
    backgroundForm.setOpaque(true);
    contentPane.add(backgroundForm);

    /* ================================ Background Image ================================ */
    /** Background Image label */
    JLabel backgroundApp = new JLabel("background");
    backgroundApp.setBounds(0, 0, 1000, 600);
    backgroundApp.setIcon(new ImageIcon("images/background.png"));
    contentPane.add(backgroundApp);

    /** sets the 'enter' key as the default button */
    contentPane.getRootPane()
      .setDefaultButton(btnLogin);
    contentPane.setVisible(true);
  }
}
