package edu.iastate.pro2.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.iastate.pro2.model.CustomersTable;
import edu.iastate.pro2.model.SQLTableModel;
import edu.iastate.pro2.ui.AddCustomerPanel;
import edu.iastate.pro2.ui.CustomerSelector;
import edu.iastate.pro2.ui.CustomersPanel;
import edu.iastate.pro2.ui.MovieRentalApplication;
import edu.iastate.pro2.ui.MovieTasksPanel.MovieTaskListener;

public class CustomersController {
	private final PreparedStatement queryCustomers;
	private final PreparedStatement insertCustomer;
	private final SQLTableModel customersModel;

	public CustomersController(Connection connection,
			CustomersPanel customersPanel) {
		queryCustomers = prepareQueryCustomers(connection);
		insertCustomer = prepareInsertCustomer(connection);
		customersModel = new SQLTableModel();

		customersPanel.setCustomersModel(customersModel);
		customersPanel.addCustomerListener(addCustomerListener);

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
			return connection
					.prepareStatement("select name as Name, address as Address from "
							+ CustomersTable.TABLE_NAME);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}

	private static PreparedStatement prepareInsertCustomer(Connection connection) {
		try {
			return connection.prepareStatement("insert into "
					+ CustomersTable.TABLE_NAME + "(" + CustomersTable.ROW_NAME
					+ ", " + CustomersTable.ROW_ADDRESS + ", "
					+ CustomersTable.ROW_BALANCE + ", "
					+ CustomersTable.ROW_POINTS + ") values(?, ?, 0, 0);");
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}
	
	private final AddCustomerPanel.AddCustomerListener addCustomerListener = new AddCustomerPanel.AddCustomerListener() {

		@Override
		public void onAddCustomer(String name, String address) {
			try {
				insertCustomer.setString(1, name);
				insertCustomer.setString(2, address);
				insertCustomer.execute();

				invalidateCustomers();
			} catch (SQLException e) {
				MovieRentalApplication.handleException(e);
			}
		}
	};
}
