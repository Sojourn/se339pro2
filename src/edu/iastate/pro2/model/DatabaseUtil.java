package edu.iastate.pro2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
	public static Connection connect(String database)
			throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + database);
	}

	public static void prepareDatabaseSchema(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE IF NOT EXISTS " + MoviesTable.SCHEMA);
		statement
				.execute("CREATE TABLE IF NOT EXISTS " + CustomersTable.SCHEMA);
		statement.execute("CREATE TABLE IF NOT EXISTS " + RentalsTable.SCHEMA);

		statement.close();
	}
}
