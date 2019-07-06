package com.qbis.model;

import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author ankur
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Question {
	/*
	 * An Integer which describes unique id for every question.
	 */
	private Integer questionId;
	/*
	 * An Integer which describes unique id for every section.
	 */
	private Integer sectionId;
	/*
	 * A String which describes question title.
	 */
	private String questionName;
	/*
	 * An Integer which describes the order of question in a section.
	 */
	private Integer questionNo;
	/*
	 * An Integer which describes the type of a Test.
	 */
	private Integer questionType;
	/*
	 * An Integer which describes the given mark for a question.
	 */
	private Integer questionMark;
	/*
	 * A List type Object which describes option's details.
	 */
	private List<Object> option1;
	/*
	 * A List which describes the correct option's id for a question.
	 */
	private List<Integer> correctAnswer;
	/*
	 * A String which describes the explanation for a correct answer.
	 */
	private String explanation;
	/*
	 * A List which describes the sub-questions for a question.
	 */
	private List<String> subQuestion;
	/*
	 * An Object of option class
	 */
	private Option[] option;
	/*
	 * An Object of option class
	 */
	private Option[] subOption;
	/*
	 * An Integer which describes user_test_attempt's id.
	 */
	private Integer userTestAttemptId;
	/*
	 * An Integer which describes the status of answer that submitted answer is
	 * correct or not.
	 */
	private Integer isCorrect;
	/*
	 * An Integer which describes the negative mark for wrong attempt of a
	 * question.
	 */
	private Float negMark;
	/*
	 * A list which describes all options for a question.
	 */
	private List<Option> optionList;
	/*
	 * A list which describes all options for a question.
	 */
	private List<Option> subOptionList;
	/*
	 * An Integer which describes a particular option is correct or not.
	 */
	private Integer correctAnsFlagCount;
	/*
	 * A List which describes the given answer by a user of a question.
	 */
	private List<Integer> givenAnswer;
	/*
	 * An Integer which describes the mark obtained by a user for a question
	 */
	private Integer markObtained;
	/*
	 * An String which describes the question is favorite or not by student at
	 * test attempted time
	 */
	private int favorites;
	/*
	 * An Integer which describes the comments of student about question
	 */
	private String notes;
	/*
	 * An Integer which describes user_test_attempt's id.
	 */
	private Integer userQueAttemptId;
	/*
	 * A String which defines question's type name
	 */
	private String questionTypeName;
	/*
	 * A string that defines the created date of a question.
	 */
	private String createdDate;
	/*
	 * An Integer which describes that question is mapped or not.
	 */
	private Integer questionIsMap;
	/*
	 * An Integer which describes that question has formula or not.
	 */
	private Integer isFormula;
	/*
	 * A String which defines the formula's text if question has formula.
	 */
	private String mathFormula;
	/*
	 * An Integer defines it is question copy or not.
	 */
	private Integer isParent;
	/*
	 * A String which describes all mapped test with question.
	 */
	private String mappedTest;
	/*
	 * An Integer which describes that question is new inserted into test or
	 * not.
	 */
	private Integer isNew;
	/*
	 * An Integer which describes the user id.
	 */
	private Integer userId;
	/*
	 * An Integer describes about the id of note.
	 */
	private Integer noteId;
	/*
	 * An Integer describes about the id of favorite question.
	 */
	private Integer favoriateId;
	/*
	 * it is dummy may be not in future.
	 */
	private String favorite;
	/*
	 * A String which defines the extra data about options for a particular
	 * data.
	 */
	private String answerValue;
	/*
	 * A List which is used for sorting answer for choice matrix type question.
	 */
	private List<Object> choiceMatrixGivenAnswer;
	/*
	 * A List which is used for given answer of question by user.
	 */
	private List<Object> userGivenAnswer;
	
	/*
	 * A List which is used for the autogenerated tags.
	 */
	private List<Tag> tagList;
	
	/*
	 * An Object about queston' settings.
	 */
	private QuestionSetting questionSetting;
	
	private String time;
	/*
	 * An String for display question view time.
	 */
	private String seconds;
	
	private Integer contentTypeId;
	
	private MultipartFile file;
	private String fileName;
	private Integer contentId;
	private Integer pageNum;
	private Integer slideHoldTime;
	
	private String board;
	private String session;
	private String classTag;
	private String subject;
	private String chapter;
	private String act;
	

	public Integer getSlideHoldTime() {
		return slideHoldTime;
	}

	public void setSlideHoldTime(Integer slideHoldTime) {
		this.slideHoldTime = slideHoldTime;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionMark() {
		return questionMark;
	}

	public void setQuestionMark(Integer questionMark) {
		this.questionMark = questionMark;
	}

	public List<Object> getOption1() {
		return option1;
	}

	public void setOption1(List<Object> option1) {
		this.option1 = option1;
	}

	public List<Integer> getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(List<Integer> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public List<String> getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(List<String> subQuestion) {
		this.subQuestion = subQuestion;
	}

	public Option[] getOption() {
		return option;
	}

	public void setOption(Option[] option) {
		this.option = option;
	}

	public Integer getUserTestAttemptId() {
		return userTestAttemptId;
	}

	public void setUserTestAttemptId(Integer userTestAttemptId) {
		this.userTestAttemptId = userTestAttemptId;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	public Integer getCorrectAnsFlagCount() {
		return correctAnsFlagCount;
	}

	public void setCorrectAnsFlagCount(Integer correctAnsFlagCount) {
		this.correctAnsFlagCount = correctAnsFlagCount;
	}

	public Integer getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Integer isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Float getNegMark() {
		return negMark;
	}

	public void setNegMark(Float negMark) {
		this.negMark = negMark;
	}

	public List<Integer> getGivenAnswer() {
		return givenAnswer;
	}

	public void setGivenAnswer(List<Integer> givenAnswer) {
		this.givenAnswer = givenAnswer;
	}

	public Integer getMarkObtained() {
		return markObtained;
	}

	public void setMarkObtained(Integer markObtained) {
		this.markObtained = markObtained;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getUserQueAttemptId() {
		return userQueAttemptId;
	}

	public void setUserQueAttemptId(Integer userQueAttemptId) {
		this.userQueAttemptId = userQueAttemptId;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getQuestionIsMap() {
		return questionIsMap;
	}

	public void setQuestionIsMap(Integer questionIsMap) {
		this.questionIsMap = questionIsMap;
	}

	public Integer getIsFormula() {
		return isFormula;
	}

	public void setIsFormula(Integer isFormula) {
		this.isFormula = isFormula;
	}

	public String getMathFormula() {
		return mathFormula;
	}

	public void setMathFormula(String mathFormula) {
		this.mathFormula = mathFormula;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public String getMappedTest() {
		return mappedTest;
	}

	public void setMappedTest(String mappedTest) {
		this.mappedTest = mappedTest;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getFavoriateId() {
		return favoriateId;
	}

	public void setFavoriateId(Integer favoriateId) {
		this.favoriateId = favoriateId;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	public Option[] getSubOption() {
		return subOption;
	}

	public void setSubOption(Option[] subOption) {
		this.subOption = subOption;
	}

	public List<Object> getChoiceMatrixGivenAnswer() {
		return choiceMatrixGivenAnswer;
	}

	public void setChoiceMatrixGivenAnswer(List<Object> choiceMatrixGivenAnswer) {
		this.choiceMatrixGivenAnswer = choiceMatrixGivenAnswer;
	}

	public List<Option> getSubOptionList() {
		return subOptionList;
	}

	public void setSubOptionList(List<Option> subOptionList) {
		this.subOptionList = subOptionList;
	}

	public QuestionSetting getQuestionSetting() {
		return questionSetting;
	}

	public void setQuestionSetting(QuestionSetting questionSetting) {
		this.questionSetting = questionSetting;
	}

	public List<Object> getUserGivenAnswer() {
		return userGivenAnswer;
	}

	public void setUserGivenAnswer(List<Object> userGivenAnswer) {
		this.userGivenAnswer = userGivenAnswer;
	}
	
	public String getSeconds() {
		return seconds;
	}
	
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getClassTag() {
		return classTag;
	}

	public void setClassTag(String classTag) {
		this.classTag = classTag;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	

	
	
	
	
}
