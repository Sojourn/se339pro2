package edu.iastate.pro2.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.iastate.pro2.model.MoviesTable;
import edu.iastate.pro2.model.SQLTableModel;
import edu.iastate.pro2.ui.MovieRentalApplication;
import edu.iastate.pro2.ui.MoviesPanel;

public class MoviesController {
	private final PreparedStatement queryMovies;
	private final SQLTableModel moviesModel;

	public MoviesController(Connection connection, MoviesPanel moviesPanel) {
		queryMovies = prepareQueryMovies(connection);
		moviesModel = new SQLTableModel();
		moviesPanel.setMoviesModel(moviesModel);
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
			return connection.prepareStatement("select * from "
					+ MoviesTable.TABLE_NAME);
		} catch (SQLException e) {
			MovieRentalApplication.handleException(e);
			return null;
		}
	}
}
