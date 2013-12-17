package edu.iastate.pro2.model;

public class MoviesTable {
	public static final String TABLE_NAME = "movies";
	public static final String ROW_NAME = "name";
	
	public static final String SCHEMA = TABLE_NAME + "(" +
			ROW_NAME + " TEXT PRIMARY KEY NOT NULL);";
}
