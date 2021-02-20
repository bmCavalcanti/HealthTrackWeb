package br.com.healthtrack.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionController {

	private static ConnectionController connectionController;

	private ConnectionController() {
	}

	public static ConnectionController getInstance() {
		if (connectionController == null) {
			connectionController = new ConnectionController();
		}
		return connectionController;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("", "", ""); // set credentials
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}