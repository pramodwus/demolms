package com.qbis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Neeraj
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserAttepmtAnswer {

	/*
	 * An Integer which describes id of a attempted question by user.
	 */
	private Integer userQueAttemptPk;

	/*
	 * An Integer which describes id for a particular option.
	 */
	private Integer optionId;

	/*
	 * A String which describes the text submitted by student for subjective
	 * type question.
	 */
	private String subjectiveAns;

	public Integer getUserQueAttemptPk() {
		return userQueAttemptPk;
	}

	public void setUserQueAttemptPk(Integer userQueAttemptPk) {
		this.userQueAttemptPk = userQueAttemptPk;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getSubjectiveAns() {
		return subjectiveAns;
	}

	public void setSubjectiveAns(String subjectiveAns) {
		this.subjectiveAns = subjectiveAns;
	}

}
