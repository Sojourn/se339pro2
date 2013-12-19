package edu.iastate.pro2.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import edu.iastate.pro2.model.CustomersTable;
import edu.iastate.pro2.model.SQLTableModel;

public class CustomerSelector {
	private final PreparedStatement customerQuery;

	public CustomerSelector(Connection connection) {
		customerQuery = prepareCustomerQuery(connection);
	}

	public int selectCustomer() {
		SQLTableModel model = new SQLTableModel();
		try {
			model.setData(customerQuery.executeQuery());
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
		}

		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));
		dialog.setMinimumSize(new Dimension(200, 300));
		dialog.setTitle("Select Customer");
		
		JButton selectButton = new JButton("Select Customer");
		selectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});

		dialog.add(new JScrollPane(table));
		dialog.add(selectButton);
		dialog.setVisible(true);
		dialog.dispose();
		
		int row = table.getSelectedRow();
		if(row < 0) {
			return -1;
		} else {
			return Integer.parseInt((String) table.getValueAt(row, 0));
		}
	}

	private static PreparedStatement prepareCustomerQuery(Connection connection) {
		try {
			String sql = "select " + CustomersTable.ROW_ID + ", "
					+ CustomersTable.ROW_NAME + " from "
					+ CustomersTable.TABLE_NAME + ";";

			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}
}
