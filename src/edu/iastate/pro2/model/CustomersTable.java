package edu.iastate.pro2.model;

public class CustomersTable {
	public static final String TABLE_NAME = "customers";
	public static final String ROW_ID = "id";
	public static final String ROW_NAME = "name";
	public static final String ROW_ADDRESS = "address";
	public static final String ROW_BALANCE = "balance";
	public static final String ROW_POINTS = "points";
	
	public static final String SCHEMA = TABLE_NAME + "(" +
			ROW_ID + " INTEGER PRIMARY KEY," +
			ROW_NAME + " TEXT NOT NULL," +
			ROW_ADDRESS + " TEXT NOT NULL," +
			ROW_BALANCE + " REAL NOT NULL," +
			ROW_POINTS + " INTEGER NOT NULL);";
}
