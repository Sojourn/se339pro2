package edu.iastate.pro2.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SQLTableModel extends AbstractTableModel {
	private final ArrayList<String[]> data = new ArrayList<String[]>();
	private String[] columnNames;
	private int columnCount;
	private int rowCount;

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	public void setData(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();

		columnCount = metaData.getColumnCount();
		columnNames = new String[columnCount];
		for (int i = 0; i < columnNames.length; i++)
			columnNames[i] = metaData.getColumnName(i + 1);

		data.clear();
		rowCount = 0;
		while (result.next()) {
			rowCount++;

			String[] row = new String[columnCount];
			data.add(row);

			for (int i = 0; i < columnNames.length; i++)
				row[i] = result.getString(i + 1);
		}

		fireTableStructureChanged();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data.get(row)[column];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
