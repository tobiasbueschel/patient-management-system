package pms;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import pms.view.Login;

import java.awt.*;

/**
 * Patient Management System
 */
public class App {

  private static final Logger LOGGER = LogManager.getLogger(App.class);

  public static void main(String[] args) {

    EventQueue.invokeLater(() -> {
      try {
        Login frame = new Login();
        frame.setVisible(true);
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
      }
    });
  }
}
