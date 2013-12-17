package edu.iastate.pro2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import edu.iastate.pro2.model.RentalDatabase;
import edu.iastate.pro2.model.SqlTableModel;

public class MovieRentalApplication {
	private RentalDatabase db;
	private JFrame window;
	private JTable moviesView;
	private SqlTableModel moviesModel;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MovieRentalApplication();
			}

		});
	}

	public MovieRentalApplication() {
		initializeDB();
		initializeUI();
	}

	private void initializeDB() {
		try {
			db = new RentalDatabase();
		} catch (Exception e) {
			System.err.println("Unable to open rental database");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void initializeUI() {
		window = new JFrame();
		window.setLayout(new BorderLayout(0, 0));
		
		try {
			moviesModel = new SqlTableModel();
			moviesModel.setData(db.queryMovies.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		moviesView = new JTable(moviesModel);
		window.add(new JScrollPane(moviesView));
		
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setMinimumSize(new Dimension(640, 480));
		window.setTitle("Movie Rental Application");
		window.setVisible(true);
	}
}
