package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScormSequencing {
private ScormObjectives objectives;

public ScormObjectives getObjectives() {
	return objectives;
}

public void setObjectives(ScormObjectives objectives) {
	this.objectives = objectives;
}
}
