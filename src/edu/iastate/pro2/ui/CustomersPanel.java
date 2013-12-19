package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import edu.iastate.pro2.model.MovieType;
import edu.iastate.pro2.ui.AddCustomerPanel.AddCustomerListener;
import edu.iastate.pro2.ui.MovieTasksPanel.MovieTaskListener;

@SuppressWarnings("serial")
public class CustomersPanel extends JPanel {
	private final JPanel customerSearchPanel;
	private final CustomerInformationPanel customerInformationPanel;
	private final JTable customersTable;
	private final AddCustomerPanel addCustomerPanel;
	
	public CustomersPanel() {
		customersTable = new JTable();
		
		customerSearchPanel = new JPanel();
		customerSearchPanel.setLayout(new BorderLayout());
		customerSearchPanel.setBorder(BorderFactory.createTitledBorder("Find Customer"));
		customerSearchPanel.add(new JScrollPane(customersTable));
		
		customerInformationPanel = new CustomerInformationPanel();
		customerInformationPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
		
		addCustomerPanel = new AddCustomerPanel();
		addCustomerPanel.setBorder(BorderFactory.createTitledBorder("Add Customer"));
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2, 1));
		rightPanel.add(customerInformationPanel);
		rightPanel.add(addCustomerPanel);
		
		setLayout(new GridLayout(1, 2));
		add(customerSearchPanel);
		add(rightPanel);
	}
	
	public void setCustomersModel(AbstractTableModel model) {
		customersTable.setModel(model);
	}
	
	public void addCustomerListener(AddCustomerListener listener) {
		addCustomerPanel.addCustomerListener(listener);
	}
}
