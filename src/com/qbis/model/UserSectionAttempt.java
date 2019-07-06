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
public class UserSectionAttempt {
	/*
	 * An Integer which describes attempted id for a section.
	 */
	private Integer userSectionAttemptId;
	/*
	 * An Integer which describes question id for correct attempted.
	 */
	private Integer correctQueAttempt;
	/*
	 * An Integer which describes question id for wrong attempted
	 */
	private Integer wrongQueAttempt;
	/*
	 * An Integer which describes total skip question of a section by student.
	 */
	private Integer unAttemptQue;
	/*
	 * A Float which describes total score of total correct attempted questions.
	 */
	private Float correctQueScore;
	/*
	 * A Float which describes total negative score for wrong attempt.
	 */
	private Float wrongQueScore;
	/*
	 * A Float which describes total obtained marks for a section
	 */
	private Float obtainMarks;
	/*
	 * A List which describes all attempted question by student
	 */
	private List<UserQuestionAttempt> userQueAttemptList;
	/*
	 * A Integer which describes taken time in second by student in a section.
	 */
	private Integer sectionTakenTime;
	/*
	 * A Integer which describes section is successfully submitted or not.
	 */
	private Integer submitStatus;
	/*
	 * An Integer which describes id for a particular section.
	 */
	private Integer sectionId;
	/*
	 * A String which describes section taken time in a section.
	 */
	private String timeTaken;
	/*
	 * A String which describes the time when user has started to given a section.
	 */
	private String sectionGivenTime;
	/*
	 * An Integer which describes total marks for a section.
	 */
	private Integer totalMarks;

	/*
	 * An Integer which describes total question for a given section for api.
	 */
	private Integer totalQuestion;

	/*
	 * A String which describes user is pass or fail.
	 */
	private String grade;

	public Integer getCorrectQueAttempt() {
		return correctQueAttempt;
	}

	public void setCorrectQueAttempt(Integer correctQueAttempt) {
		this.correctQueAttempt = correctQueAttempt;
	}

	public Integer getWrongQueAttempt() {
		return wrongQueAttempt;
	}

	public void setWrongQueAttempt(Integer wrongQueAttempt) {
		this.wrongQueAttempt = wrongQueAttempt;
	}

	public Integer getUnAttemptQue() {
		return unAttemptQue;
	}

	public void setUnAttemptQue(Integer unAttemptQue) {
		this.unAttemptQue = unAttemptQue;
	}

	public Float getCorrectQueScore() {
		return correctQueScore;
	}

	public void setCorrectQueScore(Float correctQueScore) {
		this.correctQueScore = correctQueScore;
	}

	public Float getWrongQueScore() {
		return wrongQueScore;
	}

	public void setWrongQueScore(Float wrongQueScore) {
		this.wrongQueScore = wrongQueScore;
	}

	public Float getObtainMarks() {
		return obtainMarks;
	}

	public void setObtainMarks(Float obtainMarks) {
		this.obtainMarks = obtainMarks;
	}

	public List<UserQuestionAttempt> getUserQueAttemptList() {
		return userQueAttemptList;
	}

	public void setUserQueAttemptList(
			List<UserQuestionAttempt> userQueAttemptList) {
		this.userQueAttemptList = userQueAttemptList;
	}

	public Integer getSectionTakenTime() {
		return sectionTakenTime;
	}

	public void setSectionTakenTime(Integer sectionTakenTime) {
		this.sectionTakenTime = sectionTakenTime;
	}

	public Integer getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getSectionGivenTime() {
		return sectionGivenTime;
	}

	public void setSectionGivenTime(String sectionGivenTime) {
		this.sectionGivenTime = sectionGivenTime;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Integer getUserSectionAttemptId() {
		return userSectionAttemptId;
	}

	public void setUserSectionAttemptId(Integer userSectionAttemptId) {
		this.userSectionAttemptId = userSectionAttemptId;
	}

	public Integer getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

}
