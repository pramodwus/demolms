package com.qbis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class is used for all setting for question.
 * 
 * @author ankur
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QuestionSetting {

	/*
	 * A Object of QuestionUiStyle defines UI related settings for question.
	 */
	private QuestionUiStyle questionUiStyle;

	public QuestionUiStyle getQuestionUiStyle() {
		return questionUiStyle;
	}

	public void setQuestionUiStyle(QuestionUiStyle questionUiStyle) {
		this.questionUiStyle = questionUiStyle;
	}
}
