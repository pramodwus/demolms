package com.qbis.common;

/**
 * @author kuldeep singh
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

public class QueryData {
	private List<String> columns = new ArrayList<String>();
	private List<Row> rows = new ArrayList<Row>();

	public void addColumn(String col) {
		columns.add(col);
	}

	public void addRow(Row row) {
		rows.add(row);
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<Row> getRows() {
		return rows;
	}

	public class Row {
		private List<String> rowItems = new ArrayList<String>();

		public void add(String rowItem) {
			rowItems.add(rowItem);
		}

		public List<String> getRowItems() {
			return rowItems;
		}

		public String getRowItem(int index) {
			return this.rowItems.get(index);
		}
	}
}
