package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Neeraj
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserQuestionAttempt {
	/*
	 * An Integer which describes attempted id for a question.
	 */
	private Integer UserQueAttemptPk;
	/*
	 * An Integer which describes attempted id for a test.
	 */
	private Integer UserTestAttemptPk;
	/*
	 * An Integer which describes question id.
	 */
	private Integer questionId;
	/*
	 * An Integer which describes marks for a particular question.
	 */
	private Integer marks;
	/*
	 * A List which describes all attempted answer by student.
	 */
	private List<UserAttepmtAnswer> UserAttepmtAnsList;

	public Integer getUserQueAttemptPk() {
		return UserQueAttemptPk;
	}

	public void setUserQueAttemptPk(Integer userQueAttemptPk) {
		UserQueAttemptPk = userQueAttemptPk;
	}

	public Integer getUserTestAttemptPk() {
		return UserTestAttemptPk;
	}

	public void setUserTestAttemptPk(Integer userTestAttemptPk) {
		UserTestAttemptPk = userTestAttemptPk;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public List<UserAttepmtAnswer> getUserAttepmtAnsList() {
		return UserAttepmtAnsList;
	}

	public void setUserAttepmtAnsList(List<UserAttepmtAnswer> userAttepmtAnsList) {
		UserAttepmtAnsList = userAttepmtAnsList;
	}

}
