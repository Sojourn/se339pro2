package edu.iastate.pro2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalDatabase {
	private final Connection connection;
	public final PreparedStatement queryMovies;
	
	public RentalDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:rental.db");
		createSchema();
		queryMovies = connection.prepareStatement("select * from " + MoviesTable.TABLE_NAME + ";");
	}

	private void createSchema() throws SQLException {
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE IF NOT EXISTS " + MoviesTable.SCHEMA);
		statement.execute("CREATE TABLE IF NOT EXISTS " + CustomersTable.SCHEMA);
		statement.execute("CREATE TABLE IF NOT EXISTS " + RentalsTable.SCHEMA);

		statement.close();
	}
}
