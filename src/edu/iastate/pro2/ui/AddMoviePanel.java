package edu.iastate.pro2.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import edu.iastate.pro2.model.MovieType;

@SuppressWarnings("serial")
public class AddMoviePanel extends JPanel {
	public interface AddMovieListener {
		public void onAddMovie(String name, MovieType type, int amount);
	}

	private final Set<AddMovieListener> listeners;
	private final JTextField nameEntry;
	private final JComboBox<MovieType> typeEntry;
	private final JSpinner quantityEntry;
	private final JButton addButton;

	public AddMoviePanel() {
		listeners = new HashSet<AddMovieListener>();
		nameEntry = new JTextField();
		typeEntry = new JComboBox<MovieType>(MovieType.values());
		quantityEntry = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (AddMovieListener listener : listeners)
					listener.onAddMovie(nameEntry.getText(),
							(MovieType) typeEntry.getSelectedItem(),
							(Integer) quantityEntry.getValue());
				clearForm();
			}

		});

		setLayout(new SpringLayout());
		add(new JLabel("Name"));
		add(nameEntry);
		add(new JLabel("Type"));
		add(typeEntry);
		add(new JLabel("Quantity"));
		add(quantityEntry);
		add(addButton);
		add(new JPanel());

		SpringUtilities.makeCompactGrid(this, 4, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
	}

	public void addMovieListener(AddMovieListener listener) {
		listeners.add(listener);
	}

	public void clearForm() {
		nameEntry.setText("");
	}
}
