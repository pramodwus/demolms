package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class is used for UI related setting in question.
 * 
 * @author ankur
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QuestionUiStyle {
	/*
	 * A List which defines the titles for column in question.
	 */
	private List<String> columnTitles;
	/*
	 * A List which defines the titles for row in question.
	 */
	private List<String> rowTitles;
    /*
     * A List which defines the list item titles.
     */
	private List<String> listItemTitles;
	
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public void setColumnTitles(List<String> columnTitles) {
		this.columnTitles = columnTitles;
	}

	public List<String> getRowTitles() {
		return rowTitles;
	}

	public void setRowTitles(List<String> rowTitles) {
		this.rowTitles = rowTitles;
	}

	public List<String> getListItemTitles() {
		return listItemTitles;
	}

	public void setListItemTitles(List<String> listItemTitles) {
		this.listItemTitles = listItemTitles;
	}

}
