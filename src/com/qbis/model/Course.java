package com.qbis.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Course {
	/*
	 * An Integer which describes an unique id of a user for a course.
	 */
	private Integer userId;
	/*
	 * An Integer which describes an unique id of a course.
	 */
	private Integer courseId;
	/*
	 * A String which describes Course's Name.
	 */
	private String courseName;
	/*
	 * A String which describes course's description.
	 */
	private String courseDesc;
	/*
	 * A String which describes a set instructions for a course to user.
	 */
	private String courseInstruct;
	/*
	 * A String which describes highlights for a course.
	 */
	private String coursehighlights;
	/*
	 * An Integer which describes an unique id for course's difficulty Level.
	 */
	private Integer levelId;
	/*
	 * A String which describes what is difficulty name?
	 */
	private String levelName;
	/*
	 * An Integer which describes an unique id for course's difficulty Level.
	 */
	private Integer languageId;
	/*
	 * A String which describes what is difficulty name?
	 */
	private String languageName;
	/*
	 * A String which describes tag names for a particular course.
	 */
	private String courseTag;
	/*
	 * A String is used for define the promotional video url for a course.
	 */
	private String promoVideoUrl;
	/*
	 * An Integer which describes that course is paid or free.
	 */
	private Integer isPaid;
	/*
	 * An Integer which describes that course is scheduled for any date or it has no
	 * schedule.
	 */
	private Integer isSchedule;
	/*
	 * A String is used for describing the path for a course's icon.
	 */
	private String courseImageUrl;
	/*
	 * A String which describes the created date or modified date for a course.
	 */
	private String courseModifyDate;
	/*
	 * A String which describes the created time or modified time for a course.
	 */
	private String courseModifyTime;
	/*
	 * A String type Array which describes a set of highlights for course.
	 */
	private String highlights[];
	/*
	 * A String which describes what is file name for course's icon.
	 */
	private String imageName;
	/*
	 * A String which describes information about course like how much
	 * pdf,videos,test e.t.c , it has.
	 */
	private String courseInfo;

	/*
	 * An Integer which describes that course is active or deactive.
	 */
	private Integer active;

	/*
	 * An Integer which describes that course is publish or not.
	 */
	private Integer publish;

	/*
	 * An Integer which defines the total sections inside a course.
	 */
	private Integer Sessions;

	/*
	 * A map which describes the course highlights.
	 */
	private Map<String, String> courseHighlight;
	/*
	 * AN integer describes the status of course.
	 */
	private String status;
	/*
	 * An Integer describes the enrollment number
	 */
	private Integer isEnroll;
	/*
	 * An Integer which describes the total enroll count.
	 */
	private Integer enrollCount;
	/*
	 * A String which describes Course's Name.
	 */
	private String subTitle;
	/*
	 * A String which describes course's schedule Data for publish.
	 */
    private Integer isKeepLearning;
    private String chaptercount;
	private String videocounts;
	private String schedulePublishDate;
	/*
	 * A String which describes course scheduled date or created date or modified
	 * date or published date.
	 */
	private String date;
	/*
	 * A String which describes teacher's name of a particular course
	 */
	private String teacherName;

	/*
	 * An Integer which describes about course type that course is internal or
	 * scorm.
	 */
	private Integer courseType;

	/*
	 * A String which defines the root folder name of scorm course.
	 */
	private String scormRootPath;
	
	
	private Integer practicetests;
	
	private String courseIcon;
	private String videoView;
	private String timeSpent;
	private String performanceTillNow;

	public String getCourseIcon() {
		return courseIcon;
	}

	public void setCourseIcon(String courseIcon) {
		this.courseIcon = courseIcon;
	}

	/*
	 * A List which defines all section datas.
	 */
	private List<Section> sectionList;
	
	private String courseSubname;

	public String getCourseModifyDate() {
		return courseModifyDate;
	}
    
	public Integer getIsKeepLearning() {
		return isKeepLearning;
	}

	public void setIsKeepLearning(Integer isKeepLearning) {
		this.isKeepLearning = isKeepLearning;
	}
	public void setCourseModifyDate(String courseModifyDate) {
		this.courseModifyDate = courseModifyDate;
	}

	public String getCourseModifyTime() {
		return courseModifyTime;
	}

	public void setCourseModifyTime(String courseModifyTime) {
		this.courseModifyTime = courseModifyTime;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getCourseInstruct() {
		return courseInstruct;
	}

	public void setCourseInstruct(String courseInstruct) {
		this.courseInstruct = courseInstruct;
	}

	public String getCoursehighlights() {
		return coursehighlights;
	}

	public void setCoursehighlights(String coursehighlights) {
		this.coursehighlights = coursehighlights;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTag() {
		return courseTag;
	}

	public void setCourseTag(String courseTag) {
		this.courseTag = courseTag;
	}

	public String getPromoVideoUrl() {
		return promoVideoUrl;
	}

	public void setPromoVideoUrl(String promoVideoUrl) {
		this.promoVideoUrl = promoVideoUrl;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	public Integer getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(Integer isSchedule) {
		this.isSchedule = isSchedule;
	}

	public String getCourseImageUrl() {
		return courseImageUrl;
	}

	public void setCourseImageUrl(String courseImageUrl) {
		this.courseImageUrl = courseImageUrl;
	}

	public String[] getHighlights() {
		return highlights;
	}

	public void setHighlights(String highlights[]) {
		this.highlights = highlights;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public Integer getSessions() {
		return Sessions;
	}

	public void setSessions(Integer Sessions) {
		this.Sessions = Sessions;
	}

	public Map<String, String> getCourseHighlight() {
		return courseHighlight;
	}

	public void setCourseHighlight(Map<String, String> courseHighlight) {
		this.courseHighlight = courseHighlight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsEnroll() {
		return isEnroll;
	}

	public void setIsEnroll(Integer isEnroll) {
		this.isEnroll = isEnroll;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getEnrollCount() {
		return enrollCount;
	}

	public void setEnrollCount(Integer enrollCount) {
		this.enrollCount = enrollCount;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public String getScormRootPath() {
		return scormRootPath;
	}

	public void setScormRootPath(String scormRootPath) {
		this.scormRootPath = scormRootPath;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public String getChaptercount() {
		return chaptercount;
	}

	public void setChaptercount(String chaptercount) {
		this.chaptercount = chaptercount;
	}

	public String getVideocounts() {
		return videocounts;
	}

	public void setVideocounts(String videocounts) {
		this.videocounts = videocounts;
	}

	public Integer getPracticetests() {
		return practicetests;
	}

	public void setPracticetests(Integer practicetests) {
		this.practicetests = practicetests;
	}

	public String getCourseSubname() {
		return courseSubname;
	}

	public void setCourseSubname(String courseSubname) {
		this.courseSubname = courseSubname;
	}

	public String getVideoView() {
		return videoView;
	}

	public void setVideoView(String videoView) {
		this.videoView = videoView;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getPerformanceTillNow() {
		return performanceTillNow;
	}

	public void setPerformanceTillNow(String performanceTillNow) {
		this.performanceTillNow = performanceTillNow;
	}
	
	

	

}


