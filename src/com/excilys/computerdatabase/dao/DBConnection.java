package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerdatabase.exceptions.ConnexionException;

public class DBConnection {

//	private Connection conn;
	private static DBConnection _instance = null;

	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final static String LOGIN = "admincdb";
	private final static String PASSWORD = "qwerty1234";

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		if (_instance == null) {
			synchronized (DBConnection.class) {
				if (_instance == null) {
					_instance = new DBConnection();
				}
			}
		}
		return _instance;
	}

	public Connection getConnection() throws ConnexionException {
		synchronized (DBConnection.class) {
			Connection c = null;
			try {
				c = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			} catch (SQLException e) {
				throw new ConnexionException("Echec lors de l'ouverture de la connexion");
			}
			return c;
		}

	}

	public void closeConnection(Connection c) throws ConnexionException {
		synchronized (DBConnection.class) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ConnexionException("Echec lors de la fermeture de la connexion");
			}
		}
	}

}