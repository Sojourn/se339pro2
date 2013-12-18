package edu.iastate.pro2.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.iastate.pro2.model.CustomersTable;
import edu.iastate.pro2.model.SQLTableModel;
import edu.iastate.pro2.ui.CustomersPanel;
import edu.iastate.pro2.ui.MovieRentalApplication;

public class CustomersController {
	private final PreparedStatement queryCustomers;
	private final SQLTableModel customersModel;

	public CustomersController(Connection connection,
			CustomersPanel customersPanel) {
		queryCustomers = prepareQueryCustomers(connection);
		customersModel = new SQLTableModel();
		customersPanel.setCustomersModel(customersModel);
		invalidateCustomers();
	}

	public void invalidateCustomers() {
		try {
			customersModel.setData(queryCustomers.executeQuery());
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
		}
	}

	private static PreparedStatement prepareQueryCustomers(Connection connection) {
		try {
			return connection.prepareStatement("select * from "
					+ CustomersTable.TABLE_NAME);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}
}
