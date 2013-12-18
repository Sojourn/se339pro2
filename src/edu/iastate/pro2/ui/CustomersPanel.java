package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CustomersPanel extends JPanel {
	private final JPanel customerSearchPanel;
	private final JPanel customerInformationPanel;
	private final JTable customersTable;
	
	public CustomersPanel() {
		customersTable = new JTable();
		
		customerSearchPanel = new JPanel();
		customerSearchPanel.setLayout(new BorderLayout());
		customerSearchPanel.setBorder(BorderFactory.createTitledBorder("Find Customer"));
		customerSearchPanel.add(new JScrollPane(customersTable));
		
		customerInformationPanel = new JPanel();
		customerInformationPanel.setLayout(new BorderLayout());
		customerInformationPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
		
		setLayout(new GridLayout(1, 2));
		add(customerSearchPanel);
		add(customerInformationPanel);
	}
	
	public void setCustomersModel(AbstractTableModel model) {
		customersTable.setModel(model);
	}
}
