package controller;

import java.sql.Connection;

import model.SQLiteConnector;

public class Login {
	
	Connection connection = null;
	
	Login(){
		connection = SQLiteConnector.dbConnector();
	}
	
}
