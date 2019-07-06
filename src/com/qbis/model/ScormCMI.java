package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScormCMI {
	private String completion_status;
	private String location;
	private String progress_measure;
	private String success_status;
	private String suspend_data;
	private String completion_threshold;
	private String credit;
	private String entry;
	private String launch_data;
	private String learner_id;
	private String learner_name;
	private String max_time_allowed;
	private String mode;
	private String scaled_passing_score;
	private String time_limit_action;
	private String total_time;
	private String comments_from_learner;
	private ScormCMIScore score;
	private String lastErrorCode;
	private Integer currentState;
    private String session_time;
    
	public String getCompletion_status() {
		return completion_status;
	}

	public void setCompletion_status(String completion_status) {
		this.completion_status = completion_status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProgress_measure() {
		return progress_measure;
	}

	public void setProgress_measure(String progress_measure) {
		this.progress_measure = progress_measure;
	}

	public String getSuccess_status() {
		return success_status;
	}

	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}

	public String getSuspend_data() {
		return suspend_data;
	}

	public void setSuspend_data(String suspend_data) {
		this.suspend_data = suspend_data;
	}

	public String getCompletion_threshold() {
		return completion_threshold;
	}

	public void setCompletion_threshold(String completion_threshold) {
		this.completion_threshold = completion_threshold;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getLaunch_data() {
		return launch_data;
	}

	public void setLaunch_data(String launch_data) {
		this.launch_data = launch_data;
	}

	public String getLearner_id() {
		return learner_id;
	}

	public void setLearner_id(String learner_id) {
		this.learner_id = learner_id;
	}

	public String getLearner_name() {
		return learner_name;
	}

	public void setLearner_name(String learner_name) {
		this.learner_name = learner_name;
	}

	public String getMax_time_allowed() {
		return max_time_allowed;
	}

	public void setMax_time_allowed(String max_time_allowed) {
		this.max_time_allowed = max_time_allowed;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getScaled_passing_score() {
		return scaled_passing_score;
	}

	public void setScaled_passing_score(String scaled_passing_score) {
		this.scaled_passing_score = scaled_passing_score;
	}

	public String getTime_limit_action() {
		return time_limit_action;
	}

	public void setTime_limit_action(String time_limit_action) {
		this.time_limit_action = time_limit_action;
	}

	public String getTotal_time() {
		return total_time;
	}

	public void setTotal_time(String total_time) {
		this.total_time = total_time;
	}

	public String getComments_from_learner() {
		return comments_from_learner;
	}

	public void setComments_from_learner(String comments_from_learner) {
		this.comments_from_learner = comments_from_learner;
	}

	public ScormCMIScore getScore() {
		return score;
	}

	public void setScore(ScormCMIScore score) {
		this.score = score;
	}

	public String getLastErrorCode() {
		return lastErrorCode;
	}

	public void setLastErrorCode(String lastErrorCode) {
		this.lastErrorCode = lastErrorCode;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}

	public String getSession_time() {
		return session_time;
	}

	public void setSession_time(String session_time) {
		this.session_time = session_time;
	}
}
