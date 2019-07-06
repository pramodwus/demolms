package com.qbis.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author ankur
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScormOrganizationItem {
	private String identifier;
	private String identifierref;
	private String title;
	private String parameters;
	private ArrayList<ScormOrganizationItem> items;
	private ScormSequencing sequencing;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifierref() {
		return identifierref;
	}

	public void setIdentifierref(String identifierref) {
		this.identifierref = identifierref;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public ArrayList<ScormOrganizationItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<ScormOrganizationItem> items) {
		this.items = items;
	}

	public ScormSequencing getSequencing() {
		return sequencing;
	}

	public void setSequencing(ScormSequencing sequencing) {
		this.sequencing = sequencing;
	}

}
