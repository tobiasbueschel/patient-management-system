package model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *  <p>This class establishes the connection with the SQLite database</p>
 *
 *  @credit "Programmieren Lernen mit Java - Chapter 14"
 *  @author Tobias Büschel
 *  @verison 0.7
 */
public class SQLiteConnector {

    static Connection connection = null;

    public static Connection dbConnector() {

        try {
            /** load the sqlite-JDBC driver with the help of the class loader */
            Class.forName("org.sqlite.JDBC");

            /** create the database connection */
            connection = DriverManager.getConnection("jdbc:sqlite:PatientDatabase");
            System.out.println("connection works");
            return connection;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
