package model;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SQLiteConnector {
	
	static Connection connection = null;
	
	public static Connection dbConnector(){
		
		try{
			 // load the sqlite-JDBC driver using the current class loader
			Class.forName("org.sqlite.JDBC");
			
		    // create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:PatientDatabase");
			System.out.println("connection works");
			return connection;
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
