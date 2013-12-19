package edu.iastate.pro2.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class AddCustomerPanel extends JPanel {
	public interface AddCustomerListener {
		public void onAddCustomer(String name, String address);
	}

	private final Set<AddCustomerListener> listeners;
	private final JTextField nameEntry;
	private final JTextField addressEntry;
	private final JButton addButton;

	public AddCustomerPanel() {
		listeners = new HashSet<AddCustomerListener>();
		nameEntry = new JTextField();
		addressEntry = new JTextField();

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameEntry.getText().equals("")
						|| addressEntry.getText().equals(""))
					return;

				for (AddCustomerListener listener : listeners)
					listener.onAddCustomer(nameEntry.getText(),
							addressEntry.getText());
				clearForm();
			}

		});

		setLayout(new SpringLayout());
		add(new JLabel("Name"));
		add(nameEntry);
		add(new JLabel("Address"));
		add(addressEntry);
		add(addButton);
		add(new JPanel());

		SpringUtilities.makeCompactGrid(this, 3, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
	}

	public void addCustomerListener(AddCustomerListener listener) {
		listeners.add(listener);
	}

	public void clearForm() {
		nameEntry.setText("");
		addressEntry.setText("");
	}
}
