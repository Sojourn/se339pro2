package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MoviesPanel extends JPanel {
	private final JPanel selectMoviePanel;
	private final JPanel movieInformationPanel;
	private final JTable moviesTable;
	
	public MoviesPanel() {
		moviesTable = new JTable();
		
		selectMoviePanel = new JPanel();
		selectMoviePanel.setLayout(new BorderLayout());
		selectMoviePanel.setBorder(BorderFactory.createTitledBorder("Select Movie"));
		selectMoviePanel.add(new JScrollPane(moviesTable));
		
		movieInformationPanel = new JPanel();
		movieInformationPanel.setBorder(BorderFactory.createTitledBorder("Movie Information"));
		
		setLayout(new GridLayout(2, 1));
		add(selectMoviePanel);
		add(movieInformationPanel);
	}
	
	public void setMoviesModel(AbstractTableModel model) {
		moviesTable.setModel(model);
	}
}
