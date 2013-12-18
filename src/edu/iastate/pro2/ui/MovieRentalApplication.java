package edu.iastate.pro2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import edu.iastate.pro2.controllers.CustomersController;
import edu.iastate.pro2.controllers.MoviesController;
import edu.iastate.pro2.model.DatabaseUtil;

public class MovieRentalApplication {
	private CustomersPanel customersPanel;
	private MoviesPanel moviesPanel;

	private Connection connection;

	private MoviesController moviesController;
	private CustomersController customersController;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MovieRentalApplication();
			}

		});
	}

	public MovieRentalApplication() {
		initializeViews();
		initializeDatabaseConnection();
		initializeControllers();
	}

	private void initializeDatabaseConnection() {
		try {
			connection = DatabaseUtil.connect("rental.db");
			DatabaseUtil.prepareDatabaseSchema(connection);
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void initializeViews() {
		moviesPanel = new MoviesPanel();
		customersPanel = new CustomersPanel();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Movies", moviesPanel);
		tabbedPane.addTab("Customers", customersPanel);

		JFrame window = new JFrame();
		window.setLayout(new BorderLayout(0, 0));
		window.add(tabbedPane);

		window.setTitle("Movie Rental Application");
		window.setMinimumSize(new Dimension(640, 480));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
	}

	private void initializeControllers() {
		moviesController = new MoviesController(connection, moviesPanel);
		customersController = new CustomersController(connection,
				customersPanel);
	}

	public static void handleException(Exception e) {
		e.printStackTrace();
		System.exit(-1);
	}
}
