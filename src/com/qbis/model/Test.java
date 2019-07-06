package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qbis.model.Question;

/**
 * A class which describes the all details about a test.
 * 
 * @author ankur
 * 
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Test {
	private Integer contentViewId;
	

	/*
	 * An Integer which describes the id of a test.
	 */
	private Integer testId;
	/*
	 * A String which describes test titles.
	 */
	private String testName;
	/*
	 * A String which describes Organization Name of a test.
	 */
	private String orgName;
	/*
	 * A String which describes Time Bound of a test.
	 */
	private String testTime;
	/*
	 * An Integer which describes that Test is free or paid.0 means Free and 1
	 * means Paid.
	 */
	private Integer testType;
	/*
	 * An Integer which describes the price of a paid test.
	 */
	private Integer testPrice;
	/*
	 * A String which describes teacher's name of a particular test
	 */
	private String teacherName;
	/*
	 * An Integer which describes that Test is adaptive or Not.
	 */
	private Integer testAdaptive;
	/*
	 * An Integer which describes how many times has A Student given that test.
	 */
	private Integer maxAttempts;
	/*
	 * A String which describes the test's description.
	 */
	private String testDesc;
	/*
	 * A String which describes the test's tags.
	 */
	private String testTag;
	/*
	 * An Integer which describes that test would be show to all user or not.
	 */
	private Integer view;
	/*
	 * A String which describes test Instructions.
	 */
	private String testInstruct;
	/*
	 * An Integer which defines neg. mark for each questions.
	 */
	private Integer negMark;
	/*
	 * An Integer which describes that test can be pause or not.
	 */
	private Integer testPause;
	/*
	 * An Integer which describes the user id of Test User.
	 */
	private Integer userId;
	/*
	 * An Integer which describes the company id of test user.
	 */
	private Integer companyId;
	/*
	 * An Integer which describes the Section Id;
	 */
	private Integer sectionId;
	/*
	 * A String which describes Section Name;
	 */
	private String sectionName;
	/*
	 * An Integer which describes the marks for a particular test.
	 */
	private Integer testMark;
	/*
	 * An Integer which describes the marks for a particular section.
	 */
	private Integer sectionMark;
	/*
	 * An Integer which describes that test is only saved or is also published.
	 */
	private Integer testStatus;
	/*
	 * An Integer which describes max. mark obtain by student in a particular
	 * test
	 */
	private Double maxMark;
	/*
	 * An Integer which describes min. mark obtain by student in a particular
	 * test
	 */
	private Double minMark;
	/*
	 * A Double which describes avg. mark obtain by student in a particular test
	 */
	private Double avgMark;
	/*
	 * An Inetger which describes how many student has attempted to a particular
	 * test.
	 */
	private Integer totalAppearStudents;
	/*
	 * A String which describes the created date of test.
	 */
	private String testCreatedDate;
	/*
	 * An Integer which describes user_test_attempt's id.
	 */
	private Integer userTestAttemptId;
	/*
	 * An Object of Question which describes all questions for a test.
	 */
	private Question[] question;
	/*
	 * An Integer which describes how many questions have a particular test.
	 */
	private Integer totalQuestion;
	/*
	 * An Integer which describes that test has been published or not.
	 */
	private Integer testPublishStatus;
	/*
	 * An Integer which describes that all questions are of same mark in a
	 * particular test.
	 */
	private Integer equalMarkTest;
	/*
	 * An Integer which describes mark for every question in the test.
	 */
	private Integer everyQuestionMark;
	/*
	 * A section array
	 */
	private Section sectionlist[];
	/*
	 * A String for given test json
	 */
	private Object answerJson;
	/*
	 * An Integer for no of attempt by a student for a test.
	 */
	private Integer noOfAttempted;
	/*
	 * A List which describes all attempted details for a test.
	 */
	private List<UserTestAttempt> userTestAttempt;
	/*
	 * An Integer defines that review is enable or not for a published test.
	 */
	private Integer isReview;
	/*
	 * An Integer defines that review is with correct answer or not.
	 */
	private Integer reviewWithCorrect;
	/*
	 * An Integer which describes test is public or not.
	 */
	private Integer isPublic;
	/*
	 * An Integer which describes the total time for a test in minute.
	 */
	private Integer timeMinute;

	/*
	 * A String array of test's tag.
	 */
	private String[] tags;

	/*
	 * A Question type List .
	 */
	private List<Question> questionList;

	/*
	 * An Integer which defines the course id.
	 */
	private Integer courseId;

	/*
	 * An Integer which defines the content id.
	 */
	private Integer contentId;

	/*
	 * An Integer which defines the id's of selected questions.
	 */
	private List<Integer> selectedQuestionIds;

	/*
	 * An Integer which defines the id's of selected sections.
	 */
	private List<Integer> selectedSectionIds;
	/*
	 * An Integer which defines that sections inside test have shuffle property.
	 */
	private Integer shuffleSection;
	/*
	 * An Integer which defines that questions inside test have shuffle
	 * property.
	 */
	private Integer shuffleQuestion;
	/*
	 * An Integer which defines that options inside test have shuffle property.
	 */
	private Integer shuffleOption;
	/*
	 * A String describes about test's icon.
	 */
	private String testIcon;
	/*
	 * A String describes the complete path of test's icon.
	 */
	private String testIconUrl;
	/*
	 * An Integer which describes that test is schedule or not.
	 */
	private Integer isSchedule;
	/*
	 * A String which describes test's schedule Data for publish.
	 */
	private String schedulePublishDate;
	/*
	 * A String which describes test's scheduled date or created date or
	 * modified date or published date.
	 */
	private String date;

	private List<Tag> tagList;

	private Integer isRandom;

	private Integer maxQuestions;
	
	private Integer isPractice;



	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	public Question[] getQuestion() {
		return question;
	}

	public void setQuestion(Question[] question) {
		this.question = question;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getOrgName() {
		return orgName;
	}
	public Integer getContentViewId() {
		return contentViewId;
	}

	public void setContentViewId(Integer contentViewId) {
		this.contentViewId = contentViewId;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Integer getTestPrice() {
		return testPrice;
	}

	public void setTestPrice(Integer testPrice) {
		this.testPrice = testPrice;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getTestAdaptive() {
		return testAdaptive;
	}

	public void setTestAdaptive(Integer testAdaptive) {
		this.testAdaptive = testAdaptive;
	}

	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getTestTag() {
		return testTag;
	}

	public void setTestTag(String testTag) {
		this.testTag = testTag;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public String getTestInstruct() {
		return testInstruct;
	}

	public void setTestInstruct(String testInstruct) {
		this.testInstruct = testInstruct;
	}

	public Integer getNegMark() {
		return negMark;
	}

	public void setNegMark(Integer negMark) {
		this.negMark = negMark;
	}

	public Integer getTestPause() {
		return testPause;
	}

	public void setTestPause(Integer testPause) {
		this.testPause = testPause;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getTestMark() {
		return testMark;
	}

	public void setTestMark(Integer testMark) {
		this.testMark = testMark;
	}

	public Integer getSectionMark() {
		return sectionMark;
	}

	public void setSectionMark(Integer sectionMark) {
		this.sectionMark = sectionMark;
	}

	public Integer getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Integer testStatus) {
		this.testStatus = testStatus;
	}

	public Double getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(Double maxMark) {
		this.maxMark = maxMark;
	}

	public Double getMinMark() {
		return minMark;
	}

	public void setMinMark(Double minMark) {
		this.minMark = minMark;
	}

	public Double getAvgMark() {
		return avgMark;
	}

	public void setAvgMark(Double avgMark) {
		this.avgMark = avgMark;
	}

	public Integer getTotalAppearStudents() {
		return totalAppearStudents;
	}

	public void setTotalAppearStudents(Integer totalAppearStudents) {
		this.totalAppearStudents = totalAppearStudents;
	}

	public String getTestCreatedDate() {
		return testCreatedDate;
	}

	public void setTestCreatedDate(String testCreatedDate) {
		this.testCreatedDate = testCreatedDate;
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

	public Integer getTestPublishStatus() {
		return testPublishStatus;
	}

	public void setTestPublishStatus(Integer testPublishStatus) {
		this.testPublishStatus = testPublishStatus;
	}

	public Integer getEqualMarkTest() {
		return equalMarkTest;
	}

	public void setEqualMarkTest(Integer equalMarkTest) {
		this.equalMarkTest = equalMarkTest;
	}

	public Integer getEveryQuestionMark() {
		return everyQuestionMark;
	}

	public void setEveryQuestionMark(Integer everyQuestionMark) {
		this.everyQuestionMark = everyQuestionMark;
	}

	public Section[] getSectionlist() {
		return sectionlist;
	}

	public void setSectionlist(Section sectionlist[]) {
		this.sectionlist = sectionlist;
	}

	public Object getAnswerJson() {
		return answerJson;
	}

	public void setAnswerJson(Object answerJson) {
		this.answerJson = answerJson;
	}

	public Integer getNoOfAttempted() {
		return noOfAttempted;
	}

	public void setNoOfAttempted(Integer noOfAttempted) {
		this.noOfAttempted = noOfAttempted;
	}

	public List<UserTestAttempt> getUserTestAttempt() {
		return userTestAttempt;
	}

	public void setUserTestAttempt(List<UserTestAttempt> userTestAttempt) {
		this.userTestAttempt = userTestAttempt;
	}

	public Integer getIsReview() {
		return isReview;
	}

	public void setIsReview(Integer isReview) {
		this.isReview = isReview;
	}

	public Integer getReviewWithCorrect() {
		return reviewWithCorrect;
	}

	public void setReviewWithCorrect(Integer reviewWithCorrect) {
		this.reviewWithCorrect = reviewWithCorrect;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getTimeMinute() {
		return timeMinute;
	}

	public void setTimeMinute(Integer timeMinute) {
		this.timeMinute = timeMinute;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public List<Integer> getSelectedQuestionIds() {
		return selectedQuestionIds;
	}

	public void setSelectedQuestionIds(List<Integer> selectedQuestionIds) {
		this.selectedQuestionIds = selectedQuestionIds;
	}

	public Integer getShuffleSection() {
		return shuffleSection;
	}

	public void setShuffleSection(Integer shuffleSection) {
		this.shuffleSection = shuffleSection;
	}

	public Integer getShuffleQuestion() {
		return shuffleQuestion;
	}

	public void setShuffleQuestion(Integer shuffleQuestion) {
		this.shuffleQuestion = shuffleQuestion;
	}

	public Integer getShuffleOption() {
		return shuffleOption;
	}

	public void setShuffleOption(Integer shuffleOption) {
		this.shuffleOption = shuffleOption;
	}

	public String getTestIcon() {
		return testIcon;
	}

	public void setTestIcon(String testIcon) {
		this.testIcon = testIcon;
	}

	public String getTestIconUrl() {
		return testIconUrl;
	}

	public void setTestIconUrl(String testIconUrl) {
		this.testIconUrl = testIconUrl;
	}

	public List<Integer> getSelectedSectionIds() {
		return selectedSectionIds;
	}

	public void setSelectedSectionIds(List<Integer> selectedSectionIds) {
		this.selectedSectionIds = selectedSectionIds;
	}

	public Integer getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(Integer isSchedule) {
		this.isSchedule = isSchedule;
	}

	public String getSchedulePublishDate() {
		return schedulePublishDate;
	}

	public void setSchedulePublishDate(String schedulePublishDate) {
		this.schedulePublishDate = schedulePublishDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public final List<Tag> getTagList() {
		return tagList;
	}

	public final void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public final Integer getIsRandom() {
		return isRandom;
	}

	public final void setIsRandom(Integer isRandom) {
		this.isRandom = isRandom;
	}

	public final Integer getMaxQuestions() {
		return maxQuestions;
	}

	public final void setMaxQuestions(Integer maxQuestions) {
		this.maxQuestions = maxQuestions;
	}
}



