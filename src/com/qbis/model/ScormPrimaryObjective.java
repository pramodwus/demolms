package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScormPrimaryObjective {
	private String objectiveID;
	private String satisfiedByMeasure;
	private String minNormalizedMeasure;
	public String getObjectiveID() {
		return objectiveID;
	}
	public void setObjectiveID(String objectiveID) {
		this.objectiveID = objectiveID;
	}
	public String getSatisfiedByMeasure() {
		return satisfiedByMeasure;
	}
	public void setSatisfiedByMeasure(String satisfiedByMeasure) {
		this.satisfiedByMeasure = satisfiedByMeasure;
	}
	public String getMinNormalizedMeasure() {
		return minNormalizedMeasure;
	}
	public void setMinNormalizedMeasure(String minNormalizedMeasure) {
		this.minNormalizedMeasure = minNormalizedMeasure;
	}
}
