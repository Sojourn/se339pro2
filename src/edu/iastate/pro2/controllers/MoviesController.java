package edu.iastate.pro2.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;

import edu.iastate.pro2.model.MovieType;
import edu.iastate.pro2.model.MoviesTable;
import edu.iastate.pro2.model.RentalsTable;
import edu.iastate.pro2.model.SQLTableModel;
import edu.iastate.pro2.ui.AddMoviePanel.AddMovieListener;
import edu.iastate.pro2.ui.MovieTasksPanel.MovieTaskListener;
import edu.iastate.pro2.ui.CustomerSelector;
import edu.iastate.pro2.ui.MovieRentalApplication;
import edu.iastate.pro2.ui.MoviesPanel;

public class MoviesController {
	private final PreparedStatement queryMovies;
	private final PreparedStatement insertMovie;
	private final SQLTableModel moviesModel;
	private final MoviesPanel moviesPanel;
	private final CustomerSelector customerSelector;

	public MoviesController(Connection connection, MoviesPanel moviesPanel) {
		queryMovies = prepareQueryMovies(connection);
		insertMovie = prepareInsertMovie(connection);
		moviesModel = new SQLTableModel();
		this.moviesPanel = moviesPanel;
		customerSelector = new CustomerSelector(connection);
		
		moviesPanel.setMoviesModel(moviesModel);
		moviesPanel.addMovieListener(addMovieListener);
		moviesPanel.addMovieTaskListener(movieTaskListener);
		
		invalidateMovies();
	}

	public void invalidateMovies() {
		try {
			moviesModel.setData(queryMovies.executeQuery());
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
		}
	}

	private static PreparedStatement prepareQueryMovies(Connection connection) {
		try {
			return connection.prepareStatement("select name as Name, type as Type, quantity as Quantity from "
					+ MoviesTable.TABLE_NAME);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}

	private static PreparedStatement prepareInsertMovie(
			Connection connection) {
		try {
			String sql = "insert into " + MoviesTable.TABLE_NAME + "("
					+ MoviesTable.ROW_NAME + ", " + MoviesTable.ROW_TYPE + ", "
					+ MoviesTable.ROW_QUANTITY + ") values(?, ?, ?);";

			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}
	
	private final AddMovieListener addMovieListener = new AddMovieListener() {
		
		@Override
		public void onAddMovie(String name, MovieType type, int amount) {
			try {
				insertMovie.setString(1,  name);
				insertMovie.setString(2, type.name());
				insertMovie.setInt(3, amount);
				insertMovie.execute();
				
				invalidateMovies();
			} catch(SQLException e) {
				MovieRentalApplication.handleException(e);
			}
		}
	};
	
	private final MovieTaskListener movieTaskListener = new MovieTaskListener() {
		
		@Override
		public void rentMovie() {
			String movieTitle = moviesPanel.getSelectedMovieTitle();
			if(movieTitle == null)
				return;
			
			int customerID = customerSelector.selectCustomer();
			if(customerID == -1)
				return;
			
			// TODO: If movie quantity > 0, reduce quantity by 1 and create a rental entry
		}
		
		@Override
		public void purchaseMovie() {
			MovieRentalApplication.handleException(new Exception("Not implemented yet :("));
		}
	};
}
