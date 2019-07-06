package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author ankur
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Option {
	/*
	 * An Integer which describes the unique id for a option.
	 */
	private Integer optionId;
	/*
	 * A String which describes the option title for a question.
	 */
	private String optionName;
	/*
	 * If a question has sub questions then it is used for describe.
	 */
	private String subQuestion;
	/*
	 * A List which describes sub-options details.
	 */
	private List<Object> subOption;
	/*
	 * A List which describes the correct option's id for a question.
	 */
	private List<Integer> correctAnswer;
    /*
     * An Integer which describes the correct answer flag.
     */
	private Integer answerStatus;
	/*
	 * An Integer which describes the order of options for a particular question.
	 */
	private Integer optionOrder;
	/*
	 * An String which describes the order of options for a particular question in alphabets.
	 */
	private char optionPosition;
	/*
	 * given option's pk
	 */
	private Integer optionPk;
	
	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}

	public List<Integer> getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(List<Integer> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<Object> getSubOption() {
		return subOption;
	}

	public void setSubOption(List<Object> subOption) {
		this.subOption = subOption;
	}
    
	public Integer getAnswerStatus() {
		return answerStatus;
	}
	
	public void setAnswerStatus(Integer answerStatus) {
		this.answerStatus=answerStatus;
	}
	
	public Integer getOptionOrder() {
		return optionOrder;
	}
	
	public void setOptionOrder(Integer optionOrder) {
		this.optionOrder=optionOrder;
	}
	
	

	public char getOptionPosition() {
		return optionPosition;
	}

	public void setOptionPosition(char optionPosition) {
		this.optionPosition = optionPosition;
	}

	public Integer getOptionPk() {
		return optionPk;
	}

	public void setOptionPk(Integer optionPk) {
		this.optionPk = optionPk;
	}
}
