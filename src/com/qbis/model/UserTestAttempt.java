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
public class UserTestAttempt {
	/*
	 * An Integer which describes attempted id for a test.
	 */
	private Integer userTestAttemptPk;
	/*
	 * An Integer which describes question id for correct attempted.
	 */
	private Integer correctQueAttempt;
	/*
	 * An Integer which describes question id for wrong attempted
	 */
	private Integer wrongQueAttempt;
	/*
	 * An Integer which describes total skip question of a test by student.
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
	 * A Float which describes total obtained marks for a test
	 */
	private Float obtainMarks;
	/*
	 * A List which describes all attempted question by student
	 */
	private List<UserQuestionAttempt> userQueAttemptList;
	/*
	 * A Integer which describes taken time in second by student in a test.
	 */
	private Integer testTakenTime;
	/*
	 * A Integer which describes test is successfully submitted or not.
	 */
	private Integer submitStatus;
	/*
	 * An Integer which describes id for a particular test.
	 */
	private Integer testId;
	/*
	 * A String which describes test taken time in a test.
	 */
	private String timeTaken;
	/*
	 * A String which describes the time when user has started to given a test.
	 */
	private String testGivenTime;
	/*
	 * An Integer which describes total marks for a test.
	 */
	private Integer totalMarks;
	/*
	 * An Integer which describes attempted id for a given test.
	 */
	private Integer userTestAttemptId;

	/*
	 * An Integer which describes total question for a given test for api.
	 */
	private Integer totalQuestion;

	/*
	 * A String which describes user is pass or fail.
	 */
	private String grade;
	/*
	 * A String which defines that review is enable or not for test.
	 */
	private String isReview;
	/*
	 * A String which defines that review is enable with correct answer or not
	 * for test.
	 */
	private String reviewWithCorrect;

	/*
	 * A String which describes percentage of user.
	 */
	
	private Integer everyquestionmark;

	private Double percentage;
	/*
	 * A String which describes json status of a given test.
	 */
	private Integer jsonStatus;

	private String testName;

	private Integer contentId;

	private Long sessionId;
	
	private Float floattotalmarks;
	
	private String sessionName;
	
	private Integer isPractice;


	
	

	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public Float getFloattotalmarks() {
		return floattotalmarks;
	}

	public void setFloattotalmarks(Float floattotalmarks) {
		this.floattotalmarks = floattotalmarks;
	}

	public Integer getUserTestAttemptPk() {
		return userTestAttemptPk;
	}

	public void setUserTestAttemptPk(Integer userTestAttemptPk) {
		this.userTestAttemptPk = userTestAttemptPk;
	}

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

	public void setUserQueAttemptList(List<UserQuestionAttempt> userQueAttemptList) {
		this.userQueAttemptList = userQueAttemptList;
	}

	public Integer getTestTakenTime() {
		return testTakenTime;
	}

	public void setTestTakenTime(Integer testTakenTime) {
		this.testTakenTime = testTakenTime;
	}

	public Integer getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getTestGivenTime() {
		return testGivenTime;
	}

	public void setTestGivenTime(String testGivenTime) {
		this.testGivenTime = testGivenTime;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Integer getUserTestAttemptId() {
		return userTestAttemptId;
	}

	public void setUserTestAttemptId(Integer userTestAttemptId) {
		this.userTestAttemptId = userTestAttemptId;
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

	public String getIsReview() {
		return isReview;
	}

	public void setIsReview(String isReview) {
		this.isReview = isReview;
	}

	public String getReviewWithCorrect() {
		return reviewWithCorrect;
	}

	public void setReviewWithCorrect(String reviewWithCorrect) {
		this.reviewWithCorrect = reviewWithCorrect;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Integer getJsonStatus() {
		return jsonStatus;
	}

	public void setJsonStatus(Integer jsonStatus) {
		this.jsonStatus = jsonStatus;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public final Integer getContentId() {
		return contentId;
	}

	public final void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public final Long getSessionId() {
		return sessionId;
	}

	public final void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getEveryquestionmark() {
		return everyquestionmark;
	}

	public void setEveryquestionmark(Integer everyquestionmark) {
		this.everyquestionmark = everyquestionmark;
	}
    
}
