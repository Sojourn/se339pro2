package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import edu.iastate.pro2.model.CustomersTable;
import edu.iastate.pro2.model.MoviesTable;
import edu.iastate.pro2.model.RentalDatabase;
import edu.iastate.pro2.model.SqlTableModel;

public class MovieRentalApplication {
	private RentalDatabase db;
	private JFrame window;
	private JTabbedPane tabbedPane;
	private CustomersPanel customersPanel;
	private MoviesPanel moviesPanel;

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
		
//		try {
//			moviesModel = new SqlTableModel();
//			moviesModel.setData(db.queryMovies.executeQuery());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		moviesView = new JTable(moviesModel);
//		window.add(new JScrollPane(moviesView));
		
		SqlTableModel moviesModel = new SqlTableModel();
		SqlTableModel customersModel = new SqlTableModel();
		try {
			moviesModel.setData(db.query("select * from " + MoviesTable.TABLE_NAME));
			customersModel.setData(db.query("select * from " + CustomersTable.TABLE_NAME));
		} catch (SQLException e) {
		}
		
		moviesPanel = new MoviesPanel();
		moviesPanel.setMoviesModel(moviesModel);
		
		customersPanel = new CustomersPanel();
		customersPanel.setCustomersModel(customersModel);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Movies", moviesPanel);
		tabbedPane.addTab("Customers", customersPanel);
		window.add(tabbedPane);
		
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setMinimumSize(new Dimension(640, 480));
		window.setTitle("Movie Rental Application");
		window.setVisible(true);
	}
}
