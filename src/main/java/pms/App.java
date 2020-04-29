package pms;

import pms.view.Login;

import java.awt.*;

/**
 * Patient Management System
 */
public class App {

  public static void main(String[] args) {

    EventQueue.invokeLater(() -> {
      try {
        Login frame = new Login();
        frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
