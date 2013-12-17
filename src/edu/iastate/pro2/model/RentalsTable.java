package edu.iastate.pro2.model;

public class RentalsTable {
	public static final String TABLE_NAME = "rentals";
	public static final String ROW_MOVIE = "movie";
	public static final String ROW_CUSTOMER = "customer_id";
	
	public static final String SCHEMA = TABLE_NAME + "(" +
			ROW_MOVIE + " TEXT NOT NULL," +
			ROW_CUSTOMER + " INTEGER NOT NULL" +
			")";
}
