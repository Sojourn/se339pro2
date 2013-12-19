package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.iastate.pro2.ui.AddMoviePanel.AddMovieListener;
import edu.iastate.pro2.ui.MovieTasksPanel.MovieTaskListener;

@SuppressWarnings("serial")
public class MoviesPanel extends JPanel {
	private final JPanel selectMoviePanel;
	private final MovieTasksPanel movieTasksPanel;
	private final JTable moviesTable;
	private final AddMoviePanel addMoviePanel;

	public MoviesPanel() {
		moviesTable = new JTable();
		moviesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = moviesTable.getSelectionModel();
		selectionModel.addListSelectionListener(listSelectionListener);

		selectMoviePanel = new JPanel();
		selectMoviePanel.setLayout(new BorderLayout());
		selectMoviePanel.setBorder(BorderFactory
				.createTitledBorder("Select Movie"));
		selectMoviePanel.add(new JScrollPane(moviesTable));

		movieTasksPanel = new MovieTasksPanel();
		movieTasksPanel.setBorder(BorderFactory
				.createTitledBorder("Movie Tasks"));

		addMoviePanel = new AddMoviePanel();
		addMoviePanel.setBorder(BorderFactory.createTitledBorder("Add Movie"));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(movieTasksPanel);
		bottomPanel.add(addMoviePanel);

		setLayout(new GridLayout(2, 1));
		add(selectMoviePanel);
		add(bottomPanel);
	}

	public void setMoviesModel(AbstractTableModel model) {
		moviesTable.setModel(model);
	}

	public void addMovieListener(AddMovieListener listener) {
		addMoviePanel.addMovieListener(listener);
	}

	public void addMovieTaskListener(MovieTaskListener listener) {
		movieTasksPanel.addMovieTaskListener(listener);
	}
	
	public String getSelectedMovieTitle() {
		int row = moviesTable.getSelectedRow();
		return row < 0 ? null : (String) moviesTable.getValueAt(row, 0);
	}

	private void updateMovieInformation() {
		// (String) moviesTable.getValueAt(
		// moviesTable.getSelectedRow(), 0));
	}

	private final ListSelectionListener listSelectionListener = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				updateMovieInformation();
			}
		}
	};
}
