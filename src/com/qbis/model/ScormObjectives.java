package com.qbis.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScormObjectives {
private ArrayList<ScormObjective> objective;
private ScormPrimaryObjective primaryObjective;

public ArrayList<ScormObjective> getObjective() {
	return objective;
}

public void setObjective(ArrayList<ScormObjective> objective) {
	this.objective = objective;
}

public ScormPrimaryObjective getPrimaryObjective() {
	return primaryObjective;
}

public void setPrimaryObjective(ScormPrimaryObjective primaryObjective) {
	this.primaryObjective = primaryObjective;
}
}
