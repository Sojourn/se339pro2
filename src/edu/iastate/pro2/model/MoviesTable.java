package edu.iastate.pro2.model;

public class MoviesTable {
	public static final String TABLE_NAME = "movies";
	public static final String ROW_NAME = "name";
	public static final String ROW_TYPE = "type";
	public static final String ROW_GENERE = "genre";
	public static final String ROW_RATING = "rating";
	public static final String ROW_QUANTITY = "quantity";
	
	public static final String SCHEMA = TABLE_NAME + "(" +
			ROW_NAME + " TEXT PRIMARY KEY NOT NULL," +
			ROW_TYPE + " TEXT NOT NULL," +
			ROW_GENERE + " TEXT NOT NULL," +
			ROW_RATING + " TEXT NOT NULL," +
			ROW_QUANTITY + " INTEGER NOT NULL" +
			");";
}
