package com.qbis.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.ConstantUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.EmailSender;
import com.qbis.common.QBisVersion;
import com.qbis.common.QbisUtils;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.Status;
import com.qbis.gcm.NotificationManagement;
import com.qbis.model.Attempts;
import com.qbis.model.ContentAttemptRelation;
import com.qbis.model.ContentView;
import com.qbis.model.Course;
import com.qbis.model.CourseAttemptResponse;
import com.qbis.model.CourseSession;
import com.qbis.model.Minute;
import com.qbis.model.Option;
import com.qbis.model.Question;
import com.qbis.model.QuestionSetting;
import com.qbis.model.Section;
import com.qbis.model.SectionAttempt;
import com.qbis.model.SectionContent;
import com.qbis.model.ServiceResult.ContentStatus;
import com.qbis.model.ServiceResult.IsPractice;
import com.qbis.model.Session;
import com.qbis.model.Tag;
import com.qbis.model.Test;
import com.qbis.model.TestStatus;
import com.qbis.model.User;
import com.qbis.model.UserSessionLisence;
import com.qbis.model.UserTestAttempt;

/**
 * This class is used to perform every operation related to course.
 * 
 * @author ankur
 *
 */
@Component
public class CourseService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(CourseService.class);

	/**
	 * An Integer which describes the content type id for quiz in course content.
	 */
	private static final Integer CONTENT_TYPE_ID_FOR_QUIZ = 2;
	/**
	 * Instance of {@link StudentTestService}
	 */
	@Autowired
	private StudentTestService studentTestService;
  
	@Autowired
	private StudentCourseService  studentCourseService;
	/**
	 * <p>
	 * This is used for saving course details.
	 * </p>
	 * 
	 * @param course This is only parameter which has details about course.
	 * @return boolean This returns true if course details has been saved
	 *         successfully otherwise false.
	 * @throws ParseException
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public boolean saveCourseDetails(Course course) throws ParseException {
		logger.log(Level.DEBUG, " Inside Course Service saveCourseDetails method:::::::::::");
		boolean courseStatus = false;
		String coursehighlights = "";
		for (String str : course.getHighlights()) {
			coursehighlights = coursehighlights + str + "####";
		}
		course.setCoursehighlights(coursehighlights.length() == 0 ? "####" : coursehighlights);
		course.setCourseTag(course.getCourseTag().replaceAll(",", ", ") + ",");
		if (course.getIsSchedule() != null && course.getIsSchedule() == 1 && course.getSchedulePublishDate() != null) {
			String date = QbisUtils.convertDatetoMysqlDateFormat(course.getSchedulePublishDate().trim());
			course.setSchedulePublishDate(date);
		} else {
			course.setIsSchedule(0);
			course.setSchedulePublishDate(null);
		}
		try {
			Integer courseId = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_BASIC_INFO,
					new Object[] { course.getCourseName(), course.getSubTitle(), course.getCourseDesc(),
							course.getCoursehighlights(), course.getPromoVideoUrl(), course.getCourseImageUrl(),
							course.getCourseTag(), course.getIsPaid(), course.getIsSchedule(), course.getLevelId(),
							course.getLanguageId(), course.getUserId(), course.getSchedulePublishDate(),
							course.getCourseType(), course.getScormRootPath() });
			if (courseId > 0) {
				courseStatus = true;
				course.setCourseId(courseId);
				if (QBisVersion.VERSION_2.equals(ConstantUtil.CURRENT_VERSION)) {
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveCourseDetails method:::::", e);
		}
		return courseStatus;
	}

	/**
	 * <p>
	 * This is used when user updates the course details.
	 * </p>
	 * 
	 * @param course This is only parameter which has details about course.
	 * @return boolean This returns true if course details has been updated
	 *         successfully otherwise false.
	 * @throws ParseException
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public boolean updateCourseDetails(Course course) throws ParseException {
		logger.log(Level.DEBUG, " Inside Course Service updateCourseDetails method:::::::::::");
		boolean courseStatus = false;
		String coursehighlights = "";
		try {
			for (String str : course.getHighlights()) {
				coursehighlights = coursehighlights + str + "####";
			}
			course.setCoursehighlights(coursehighlights.length() == 0 ? "####" : coursehighlights);
			course.setCourseTag(course.getCourseTag().replaceAll(",", ", ") + ",");
			if (course.getIsSchedule() != null && course.getIsSchedule() == 1
					&& course.getSchedulePublishDate() != null) {
				String date = QbisUtils.convertDatetoMysqlDateFormat(course.getSchedulePublishDate().trim());
				course.setSchedulePublishDate(date);
			} else {
				course.setIsSchedule(0);
				course.setSchedulePublishDate(null);
			}

			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_BASIC_INFO,
					new Object[] { course.getCourseName(), course.getSubTitle(), course.getCourseDesc(),
							course.getCoursehighlights(), course.getPromoVideoUrl(), course.getCourseImageUrl(),
							course.getCourseImageUrl(), course.getCourseTag(), course.getIsPaid(),
							course.getIsSchedule(), course.getLevelId(), course.getLanguageId(),
							course.getSchedulePublishDate(), course.getUserId(), course.getCourseId() });
			if (id > 0) {
				courseStatus = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR, "Exception Inside Course Service updateCourseDetails method:::::", e);
		}
		return courseStatus;
	}

	/**
	 * <p>
	 * This is used for fetching list of course difficulty level.
	 * </p>
	 * 
	 * .
	 * 
	 * @return String This contains all difficulty level's name and their id's.
	 * @exception IOException On input error.
	 */
	public String getCourseLevelList() {
		logger.log(Level.DEBUG, " Inside Course Service getCourseLevelList method:::::::::::");
		String str = "";
		try {
			QueryData data = QueryManager.executeQuery(QueryStrings.GET_COURSE_LEVEL_LIST);
			for (QueryData.Row row : data.getRows()) {
				str = str + row.getRowItem(0) + "@@@@" + row.getRowItem(1) + "####";
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getCourseLevelList method:::::", e);
		}
		return str;
	}

	/**
	 * <p>
	 * This is used for fetching list of language(like English) for course.
	 * </p>
	 * 
	 * .
	 * 
	 * @return String This contains all language's name and their id's.
	 * @exception IOException On input error.
	 */
	public String getCourseLanguageList() {
		logger.log(Level.DEBUG, " Inside Course Service getCourseLanguageList method:::::::::::");
		String str = "";
		try {
			QueryData data = QueryManager.executeQuery(QueryStrings.GET_COURSE_LANGUAGE_LIST);
			for (QueryData.Row row : data.getRows()) {
				str = str + row.getRowItem(0) + "@@@@" + row.getRowItem(1) + "####";
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getCourseLanguageList method:::::", e);
		}
		return str;
	}

	/**
	 * <p>
	 * This is used getting a particular course details based on course id.
	 * </p>
	 * 
	 * @param courseId    This is first parameter for getting course details based
	 *                    on course Id.
	 * @param userId      This is second parameter for getting course details based
	 *                    on user Id .
	 * @param isPublished This is third parameter for getting course details bases
	 *                    on published status or unpublished status.
	 * @return Course This returns A Course Object which has all information about
	 *         course.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public Course getCourseDetailsById(int courseId, int userId, int isPublished) {
		logger.log(Level.DEBUG, " Inside Course Service getCourseDetailsById method:::::::::::");
		Course course = new Course();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_DETAILS_BY_COURSE_ID,
					new Object[] { courseId, userId, isPublished });
			for (QueryData.Row row : data.getRows()) {
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				String highlights = row.getRowItem(3) == null || row.getRowItem(3).length() == 0 ? "NA####"
						: row.getRowItem(3);
				course.setHighlights(highlights.split("####"));
				course.setPromoVideoUrl(
						row.getRowItem(4) == null || row.getRowItem(4).length() == 0 ? "NA" : row.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
				String newTag = "";
				if (row.getRowItem(6) != null) {
					String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
					for (int i = 0; i < tag.length; i++) {
						newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i]) : (newTag + "#" + tag[i] + ", "));
					}
				}

				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(7));
				course.setLanguageName(row.getRowItem(8));
				course.setCourseModifyDate(row.getRowItem(9));
				course.setCourseModifyTime(row.getRowItem(10));
				course.setActive(Integer.parseInt(row.getRowItem(13)));
				course.setPublish(Integer.parseInt(row.getRowItem(14)));
				course.setIsSchedule(Integer.parseInt(row.getRowItem(16)));
				course.setSchedulePublishDate(
						row.getRowItem(17) != null ? QbisUtils.convertMysqlDateFormatToDate(row.getRowItem(17)) : null);
				course.setCourseType(Integer.parseInt(row.getRowItem(18)));
				course.setScormRootPath(row.getRowItem(19));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getCourseDetailsById method:::::", e);
		}
		return course;
	}

	/**
	 * <p>
	 * This is used for fetching all section list for a particular course.
	 * </p>
	 * 
	 * @param courseId This is only parameter on which based , all section details
	 *                 are getting.
	 * @param url
	 * 
	 * @return List<Section> This returns A List of Sections which has all
	 *         information about every section for a test.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public List<Section> getSectionList(int courseId, String url) {
		logger.log(Level.DEBUG, " Inside Course Service getSectionList method:::::::::::");
		List<Section> sectionList = new ArrayList<Section>();
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION_LIST_FOR_COURSE_PREVIEW,
					new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {
				Section section = new Section();
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				section.setSectionName(row.getRowItem(1));
				section.setSectionSortOrder(row.getRowItem(2) != null ? Integer.parseInt(row.getRowItem(2)) : 0);
				section.setIsPractice(Integer.parseInt(row.getRowItem(3)));
				section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
				QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CONTENT_LIST_WITH_ATTEMPT,
						new Object[] { section.getSectionId() });
				SectionContent sectionContent[] = new SectionContent[contentdata.getRows().size()];
				int sec = 0;
				for (QueryData.Row content : contentdata.getRows()) {
					sectionContent[sec] = new SectionContent();
					sectionContent[sec].setContentId(Integer.parseInt(content.getRowItem(0)));
					sectionContent[sec].setContentName(content.getRowItem(8) != null ? content.getRowItem(8) : "");
					sectionContent[sec].setSectionId(Integer.parseInt(content.getRowItem(2)));
					sectionContent[sec].setContent(content.getRowItem(3));
					sectionContent[sec].setContentTypeId(Integer.parseInt(content.getRowItem(4)));

					sectionContent[sec].setContentIconPath(content.getRowItem(6));
					String iconpath = ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + content.getRowItem(6);
					sectionContent[sec].setContentIconURL(iconpath);

					sectionContent[sec].setContentOrder(
							content.getRowItem(7) != null ? Integer.parseInt(content.getRowItem(7)) : 0);
					sectionContent[sec].setIsExternalURL(Integer.parseInt(content.getRowItem(10)));
					if (sectionContent[sec].getIsExternalURL() == 0) {
						sectionContent[sec].setContentType(content.getRowItem(5));
						if (content.getRowItem(5).equalsIgnoreCase("TEST")) {
							sectionContent[sec]
									.setContentPath(url + "/testpreview?action=frame&testId=" + content.getRowItem(3));
						} else {
							sectionContent[sec]
									.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + content.getRowItem(3));
						}
					} else {
						sectionContent[sec].setContentType("URL");
						sectionContent[sec].setContentPath(content.getRowItem(3));
					}
					if (content.getRowItem(5).equalsIgnoreCase("VIDEO")
							|| content.getRowItem(5).equalsIgnoreCase("PDF")) {
						sectionContent[sec].setQuestionList(
								new QuestionService().findVideoQuestionByContentId(sectionContent[sec].getContentId()));
					}
					if (content.getRowItem(5).equalsIgnoreCase("PPT")
							|| content.getRowItem(5).equalsIgnoreCase("PPTX")) {
						sectionContent[sec].setQuestionList(
								new UploadContentService().findAudioByContentId(sectionContent[sec].getContentId()));
					}
					if (content.getRowItem(11) != null) {
						sectionContent[sec].setNumPages(Integer.parseInt(content.getRowItem(11)));
					}
					if (content.getRowItem(12) != null) {
						sectionContent[sec].setAttemptId((Integer.parseInt(content.getRowItem(12))));
					}
					if (content.getRowItem(13) != null) {
						sectionContent[sec].setAttemptName((content.getRowItem(13)));
					}
					sectionContent[sec].setSessionId(Long.parseLong(content.getRowItem(14)));
					System.out.println("Null value==="+content.getRowItem(14));
					sec++;
				}
				section.setSectionContent(sectionContent);
				section.setSession(getSessionList(courseId));
				sectionList.add(section);
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getSectionList method:::::", e);
		}

		return sectionList;
	}

	public List<Section> getChapterTest(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service getChapterTest method:::::::::::");
		List<Section> chapterTestList = new ArrayList<>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_CHAPTER_TEST, new Object[] { courseId });
		for (QueryData.Row content : data.getRows()) {
			Section chapter = new Section();
			chapter.setSectionId(Integer.parseInt(content.getRowItem(0)));
			chapter.setSectionName(content.getRowItem(1));
			chapter.setCourseId(Integer.parseInt(content.getRowItem(2)));
			chapter.setIsChapterTest(Integer.parseInt(content.getRowItem(3)));
			chapterTestList.add(chapter);
		}
		return chapterTestList;
	}

	/**
	 * <p>
	 * This is used for fetching all practice section list for a particular course.
	 * </p>
	 * 
	 * @param courseId This is only parameter on which based , all section details
	 *                 are getting.
	 * @param url
	 * 
	 * @return List<Section> This returns A List of Sections which has all
	 *         information about every section for a test.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */

	public List<ContentAttemptRelation> getContentAttemptRelation() {
		logger.log(Level.DEBUG, " Inside Course Service getAttemptList method:::::::::::");
		List<ContentAttemptRelation> count = new ArrayList<ContentAttemptRelation>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CONTENT_ATTEMPT_RELATION, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				ContentAttemptRelation attemptRelation = new ContentAttemptRelation();
				attemptRelation.setContentId(Integer.parseInt(row.getRowItem(0)));
				// attemptRelation.setAttemptId(Integer.parseInt(row.getRowItem(1)));
				count.add(attemptRelation);

			}

		}

		catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getSectionList method:::::", e);
		}
		return count;
	}

	/**
	 * <p>
	 * This is used for fetching all section list for a particular course.
	 * </p>
	 * 
	 * @param courseId This is only parameter on which based , all section details
	 *                 are getting.
	 * @param url
	 * 
	 * @return List<Section> This returns A List of Sections which has all
	 *         information about every section for a test.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */

	public Integer getAttemptCount() {
		logger.log(Level.DEBUG, " Inside Course Service getAttemptList method:::::::::::");
		Integer count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_COUNT_FOR_DYNAMIC_ATTEMPT_CREATION,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				Attempts attempts = new Attempts();
				count = attempts.setAttemptList(Integer.parseInt(row.getRowItem(0)));

			}

		}

		catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getSectionList method:::::", e);
		}

		return count;
	}


	public boolean deleteSession(Integer sessionId)
	{
	logger.log(Level.DEBUG, " Inside Course Service deleteSession method:::::::::::");
	boolean status=false;
	try {
	Integer id = QueryManager.execuateUpdate(QueryStrings.DELETE_SESSIONS,
	new Object[] { sessionId });
	if (id > 0) {
	status = true;
	}
	}
	catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
	}

	return status;

	}




	public boolean editSession(Session session) {
		logger.log(Level.DEBUG, " Inside Course Service deleteSession method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_SESSIONS,
					new Object[] { session.getSessionName(), session.getSessonId() });
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return status;

	}


	/**
	 * This method is used for getting attempt list based on attempt id.
	 */
	public List<Attempts> getAttemptList(Integer attemptId) {
		logger.log(Level.DEBUG, " Inside Course Service getAttemptList method:::::::::::");
		List<Attempts> listOfAttempts = new ArrayList<Attempts>();
		try {
			QueryData data = null;
			if (attemptId != null && attemptId > 0) {
				data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_LIST_FOR_DYNAMIC_ATTEMPT_CREATION_BY_ID,
						new Object[] { attemptId });
			} else {
				data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_LIST_FOR_DYNAMIC_ATTEMPT_CREATION,
						new Object[] {});
			}
			for (QueryData.Row row : data.getRows()) {
				Attempts attempts = new Attempts();
				attempts.setId((Integer.parseInt(row.getRowItem(0))));
				attempts.setName(row.getRowItem(1));
				listOfAttempts.add(attempts);
			}
		}

		catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getSectionList method:::::", e);
		}
		return listOfAttempts;
	}

	public List<Session> getSessionList(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service getAttemptList method:::::::::::");
		List<Session> sessionList = new ArrayList<>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.SESSION_RELATION, new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {
				Session session = new Session();
				session.setSessonId(Integer.parseInt(row.getRowItem(0)));
				session.setSessionName(row.getRowItem(1));
				session.setSectionId(Integer.parseInt(row.getRowItem(2)));
				session.setIsLive(Integer.parseInt(row.getRowItem(3)));
				session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));

				// attemptRelation.setAttemptId(Integer.parseInt(row.getRowItem(1)));
				sessionList.add(session);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionList;
	}

	public List<Session> getSessionListForChapterTest(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service getAttemptList method:::::::::::");
		List<Session> sessionList = new ArrayList<>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.SESSION_RELATION, new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {
				Session session = new Session();
				session.setSessonId(Integer.parseInt(row.getRowItem(0)));
				session.setSessionName(row.getRowItem(1));
				session.setSectionId(Integer.parseInt(row.getRowItem(2)));
				session.setIsLive(Integer.parseInt(row.getRowItem(3)));
				session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));

				// attemptRelation.setAttemptId(Integer.parseInt(row.getRowItem(1)));
				sessionList.add(session);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionList;
	}

	/**
	 * <p>
	 * This is used for saving details of a section for course.
	 * </p>
	 * 
	 * @param section This is only parameter on which based, every section's details
	 *                is fetched.
	 * @return boolean This returns true if all section's details has been saved
	 *         otherwise false.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public boolean saveSectionDetails(Section section) {
		logger.log(Level.DEBUG, " Inside Course Service saveSectionDetails method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SECTION_DETAILS, new Object[] {
					section.getSectionName(), section.getCourseId(), IsPractice.NO.getValue(), null, 0 ,1});
			if (id > 0) {
				section.setSectionId(id);
				status = true;
				savePracticeSessionDetails(section, id);
				saveChapterTestDetails(section,id);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveSectionDetails method:::::", e);
		}
		return status;
	}

	public boolean savePracticeSessionDetails(Section section, Integer parentId) {
		logger.log(Level.DEBUG, " Inside Course Service savePracticeSessionDetails method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SECTION_DETAILS,
					new Object[] { section.getSectionName() + " (Practice) ", section.getCourseId(),
							IsPractice.YES.getValue(), parentId, 0,0 });
			if (id > 0) {
				section.setSectionId(id);
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveSectionDetails method:::::", e);
		}

		return status;
	}

	/*
	 * public boolean saveChapterTestDetails(Course course) {
	 * logger.log(Level.DEBUG,
	 * " Inside Course Service saveSectionDetails method:::::::::::"); boolean
	 * status = false; try { Integer id =
	 * QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_TEST_DETAILS, new
	 * Object[] { course.getCourseName()+" Test", course.getCourseId(),
	 * IsPractice.NO.getValue()}); if (id > 0) { status = true; } //
	 * status=saveSessionDetails(section,id); } catch (Exception e) {
	 * logger.log(Level.ERROR,
	 * "Exception Inside Course Service saveSectionDetails method:::::", e); }
	 * return status; }
	 */

	public boolean saveSessionDetails(Section section, Integer sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service saveSessionDetail method:::::::::::");
		boolean status = false;

		Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SESSION_DETAILS,
				new Object[] { section.getSessionName(), sectionId, 1, section.getIsChapterTest(),1,1 });
		if (id > 0) {
			section.setSectionId(id);
			status = true;

		}
		return status;
	}

	public boolean saveSessionDetailsForChapterTest(Section section, Integer sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service saveSessionDetail method:::::::::::");
		boolean status = false;

		Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SESSION_DETAILS,
				new Object[] { section.getSessionName(), sectionId, 1, 1,1,1 });
		if (id > 0) {
			section.setSectionId(id);
			status = true;
		}
		return status;
	}

	public boolean saveChapterTestDetails(Section section,Integer sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service saveChapterTestDEtails method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SECTION_DETAILS, new Object[] {
					section.getSectionName() + " Test", section.getCourseId(), IsPractice.NO.getValue(),sectionId, 1,0 });
			if (id > 0) { // section.setSectionId(id);
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveChapterTestDEtails method:::::", e);
		}

		return status;
	}

	/**
	 * <p>
	 * This is used for updating Section Name for course.
	 * </p>
	 * 
	 * @param section This is only parameter on which based, section's name would be
	 *                fetched.
	 * @return boolean This returns true if section's name has been updated
	 *         otherwise false.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public boolean updateSectionDetails(Section section) {
		logger.log(Level.DEBUG, " Inside Course Service updateSectionDetails method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_SECTION_NAME,
					new Object[] { section.getSectionName(), section.getSectionId() });
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updateSectionDetails method:::::", e);
		}
		return status;

	}

	/**
	 * <p>
	 * This is used for saving details of content of course's section.
	 * </p>
	 * 
	 * @param content This is only parameter on which based, every sectionContent's
	 *                details is fetched.
	 * @return boolean This returns true if all content's details has been saved
	 *         otherwise false.
	 * @exception IOException          On input error.
	 * @exception NullPointerException
	 */
	public Boolean savecoursesectioncontent(SectionContent content) {
		logger.log(Level.DEBUG, " Inside Course Service savecoursesectioncontent method:::::::::::");
		Boolean status = false;
		try {
			Integer contentId = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
					new Object[] { content.getContentName(), content.getContent(), content.getContentTypeId(), 0,
							content.getUserId(), 0, null, null, ContentStatus.FINISHED.getValue() });
			if (contentId > 0) {
				status = true;
				QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_SECTION_MAPPING, new Object[] { contentId,
						content.getSectionId(), content.getContentName(), content.getAttemptId() });
				content.setContentId(contentId);

			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service savecoursesectioncontent method:::::", e);
		}
		return status;
	}

	public Boolean savecoursesectioncontent1(SectionContent content, Long sessionId) {
		logger.log(Level.DEBUG, " Inside Course Service savecoursesectioncontent method:::::::::::");
		Boolean status = false;
		try {
			Integer contentId = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS1,
					new Object[] { content.getContentName(), content.getContent(), content.getContentTypeId(), 0,
							content.getUserId(), 0, null, null, ContentStatus.FINISHED.getValue() });
			if (contentId > 0) {
				status = true;
				QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_SECTION_MAPPING1, new Object[] { contentId,
						content.getSectionId(), content.getContentName(), content.getAttemptId(), sessionId });
				content.setContentId(contentId);

			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service savecoursesectioncontent method:::::", e);
		}
		return status;
	}

	/**
	 * <p>
	 * This is used for checking that file extension is valid for upload or not When
	 * user tries for uploading any file on server.
	 * </p>
	 * 
	 * @param extension This is only parameter which defines about file 's
	 *                  extension.
	 * @return String This returns a string which contains information of content
	 *         type, content type id and icon path if file extension is valid
	 *         otherwise returns empty strings.
	 * @exception IOException On input error.
	 */
	public String checkFileTypeValid(String extension) {
		logger.log(Level.DEBUG, " Inside Course Service checkFileTypeValid method:::::::::::");
		int id = 0;
		String contentType = "";
		String contentData = "";
		String ContentIcon = "";
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_CONTENT_TYPE_ID, new Object[] { extension });
			for (QueryData.Row row : data.getRows()) {
				id = Integer.parseInt(row.getRowItem(0));
				contentType = row.getRowItem(1);
				ContentIcon = row.getRowItem(2);
			}
			contentData = contentData + id + "####" + contentType + "####" + ContentIcon;
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service checkFileTypeValid method:::::", e);
		}
		return contentData;
	}

	/**
	 * <p>
	 * This is used for getting list of all courses.
	 * </p>
	 * 
	 * @param userId      This is first parameter which is used for getting course's
	 *                    list based on user id.
	 * @param isPublished This is second parameter which is used for getting details
	 *                    based on published or unpublished status of course.
	 * @return List This returns a Course type List which contains information about
	 *         course's.
	 * @exception IOException On input error.
	 */
	public List<Course> getPublishedDraftedCourse(int userId, int isPublished) {
		logger.log(Level.DEBUG, " Inside Course Service getPublishedDraftedCourse method:::::::::::");
		List<Course> courseList = new ArrayList<Course>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_LIST_BY_USERID,
					new Object[] { userId, isPublished });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1).substring(0, 1).toUpperCase() + row.getRowItem(1).substring(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
				String newTag = "";
				if (row.getRowItem(6) != null) {
					String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
					for (int i = 0; i < tag.length; i++) {
						newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i]) : (newTag + "#" + tag[i] + ", "));
					}
				}

				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(7));
				course.setLanguageName(row.getRowItem(8));
				course.setCourseModifyDate(row.getRowItem(9));
				course.setCourseModifyTime(row.getRowItem(10));
				course.setSubTitle(row.getRowItem(11) != null ? row.getRowItem(11) : "");
				course.setEnrollCount(Integer.parseInt(row.getRowItem(12)));
				course.setIsSchedule(Integer.parseInt(row.getRowItem(13)));
				course.setSchedulePublishDate(row.getRowItem(14));
				String info = "";
				QueryData cont = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE,
						new Object[] { course.getCourseId(), isPublished });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " " + rowCont.getRowItem(1) + " ";
				}
				course.setCourseInfo(info.length() > 0 ? info : "0 Content");
				courseList.add(course);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getPublishedDraftedCourse method:::::", e);
		}

		return courseList;
	}

	/**
	 * This is used getting course list based on skip and limit.
	 * 
	 * @param userId
	 * @param isPublished
	 * @param limit
	 * @param offset
	 * @return List<Course>
	 */
	public List<Course> getPublishedDraftedCourse(Integer userId, Integer isPublished, Integer limit, Integer offset) {
		logger.log(Level.DEBUG, " Inside Course Service getPublishedDraftedCourse method:::::::::::");
		List<Course> courseList = new ArrayList<Course>();
		try {
			int skip = offset * limit;
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_LIST_BY_USERID_WITH_PAGINATION,
					new Object[] { userId, isPublished, limit, skip });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1).substring(0, 1).toUpperCase() + row.getRowItem(1).substring(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
				String newTag = "";
				if (row.getRowItem(6) != null) {
					String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
					for (int i = 0; i < tag.length; i++) {
						newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i]) : (newTag + "#" + tag[i] + ", "));
					}
				}

				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(7));
				course.setLanguageName(row.getRowItem(8));
				course.setCourseModifyDate(row.getRowItem(9));
				course.setCourseModifyTime(row.getRowItem(10));
				course.setSubTitle(row.getRowItem(11) != null ? row.getRowItem(11) : "");
				course.setEnrollCount(Integer.parseInt(row.getRowItem(12)));
				course.setIsSchedule(Integer.parseInt(row.getRowItem(13)));
				course.setSchedulePublishDate(row.getRowItem(14));
				course.setCourseType(Integer.parseInt(row.getRowItem(15)));
				String info = "";
				QueryData cont = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE,
						new Object[] { course.getCourseId(), isPublished });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " " + rowCont.getRowItem(1) + " ";
				}
				course.setCourseInfo(info.length() > 0 ? info : "0 Content");
				courseList.add(course);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getPublishedDraftedCourse method:::::", e);
		}

		return courseList;
	}

	/**
	 * This is used getting user list of groups.
	 * 
	 * @param groupList
	 * @param action
	 * 
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getUsersInGroup(String[] groupList, String action) {
		logger.log(Level.DEBUG, " Inside Course Service getUsersInGroup method:::::::::::");
		Map<String, Integer> userList = new HashMap<>();
		try {
			int subfunctionId = 0;
			if (action.equalsIgnoreCase("course")) {
				subfunctionId = 4;
			}
			if (action.equalsIgnoreCase("test")) {
				subfunctionId = 6;
			}
			for (int i = 0; i < groupList.length; i++) {
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_GROUP_LIST_BY_CREATERID_AND_SUBFUNCTION,
						new Object[] { groupList[i], subfunctionId });
				for (QueryData.Row row : data.getRows()) {
					userList.put(row.getRowItem(0), Integer.parseInt(row.getRowItem(1)));
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getUsersInGroup method:::::", e);
		}
		return userList;
	}

	/**
	 * This is used for updating the status for share the course.
	 * 
	 * @param courseId
	 * @return boolean
	 */
	public boolean updateSharedCourseStatus(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service updateSharedCourseStatus method:::::::::::");
		/**
		 * A boolean for identify that course's published share status has been update.
		 */
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_SHARE_STATUS_COURSE,
					new Object[] { Status.SHARED.getValue(), courseId });
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updateSharedCourseStatus method:::::", e);
		}
		return status;
	}

	/**
	 * <p>
	 * This is used for updating publish status of course and its all section.
	 * </p>
	 * 
	 * @param courseId        This is first parameter which defines course id.
	 * @param selectedSection This is second parameter which describes selected
	 *                        section by user for publishing.
	 * @param userId
	 * 
	 * @return boolean This returns true if course and its all section's status has
	 *         been updated from unpublished to published otherwise false.
	 * @exception IOException On input error.
	 */
	public boolean savepublishedCourse(Integer courseId, String selectedSection[], int userId) {
		logger.log(Level.DEBUG, " Inside Course Service savepublishedCourse method:::::::::::");
		/**
		 * A boolean for identify that course's publish status has been update.
		 */
		boolean status = false;
		try {
			/**
			 * traversing on all selected id of section for publish.
			 */
			for (String sectionId : selectedSection) {
				/**
				 * update the publish status of section.
				 */
				QueryManager.execuateUpdate(QueryStrings.UPDATE_PUBLISH_STATUS_SECTION,
						new Object[] { CourseEnum.PUBLISHED.getValue(), sectionId });
			}
			/**
			 * A boolean for identify that course is immediate publish or schedule publish.
			 */
			Boolean isSchedule = checkCourseIsSchedule(courseId);
			/**
			 * A String which identify the publish type of course.
			 */
			String publishType = "0";
			/**
			 * if course is scheduled publish.
			 */
			if (isSchedule) {
				/**
				 * assign the publish type value for schedule course.
				 */
				publishType = CourseEnum.SCHEDULED.getValue();
			} else {
				/**
				 * assign the publish type value for published course.
				 */
				publishType = CourseEnum.PUBLISHED.getValue();
			}
			/**
			 * update course's publish status according to publish type, here 0 means i.e.
			 * drafted,1 means i.e. immediate published and 2 means i.e. scheduled publish.
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_PUBLISH_STATUS_COURSE,
					new Object[] { publishType, courseId });
			/**
			 * if course's publish status has been update.
			 */
			if (id > 0) {
				/**
				 * make true for identify that course has been update.
				 */
				status = true;
				/**
				 * if course's update status is immediate publish.
				 */
				if (publishType.equals(CourseEnum.PUBLISHED.getValue())) {
					/**
					 * getting all user list.
					 */
					final List<User> userlist = new UserService().getUserList();
					/**
					 * if user list size is not zero.
					 */
					if (userlist != null) {
						/**
						 * getting course detail.
						 */
						Course course = new CourseService().getCourseDetailsById(courseId, userId, 1);
						/**
						 * set a text message for sending to these users as notifications.
						 */
						final String textMsg = (ConstantUtil.PUBLISH_COURSE.replace("#NAME", course.getCourseName()));
						/**
						 * assign course id as final.
						 */
						final int courseid = course.getCourseId();
						/**
						 * start thread for sending notification to all user that course has been
						 * published.
						 */
						(new Thread() {
							@Override
							public void run() {
								/**
								 * make object of Map type data structure for storing the user id as key and
								 * registration id as value.
								 */
								Map<Integer, String> notification = new LinkedHashMap<Integer, String>();
								/**
								 * Initialize string type array for getting all user ids.
								 */
								String[] targetIds = new String[userlist.size()];
								/**
								 * traversing on user list for getting user id and registration id.
								 */
								for (int i = 0; i < userlist.size(); i++) {
									/**
									 * if registration id is not null.
									 */
									if (userlist.get(i).getRegistrationId() != null) {
										/**
										 * put data in notification type hash map.
										 */
										notification.put(userlist.get(i).getUserId(),
												userlist.get(i).getRegistrationId());
									}
									/**
									 * put user id in string array.
									 */
									targetIds[i] = userlist.get(i).getUserId().toString();
								}
								/**
								 * make the action which would be done when user click on that notification.
								 */
								String url = "studentCourse?action=coursedetail&courseId=" + courseid;
								/**
								 * save notification entry.
								 */
								NotificationService.saveNotifcation(textMsg, url, targetIds);
								/**
								 * when notification type hash map has any data.
								 */
								if (notification.size() > 0) {
									NotificationManagement notificationManagement = new NotificationManagement();
									/**
									 * calling method for sending notification.
									 */
									notificationManagement.sendNotificationPublishCourse(notification, textMsg,
											courseid);
								}
							}
						}).start();
					}
				}
				/**
				 * When course has been scheduled for publishing.
				 */
				else {
					/**
					 * getting all user list.
					 */
					final List<User> userlist = new UserService().getUserList();
					/**
					 * if user list size is not zero.
					 */
					if (userlist != null) {
						/**
						 * getting course detail.
						 */
						Course course = new CourseService().getCourseDetailsById(courseId, userId, 2);
						/**
						 * set a text message for sending to these users as notifications.
						 */
						final String textMsg = ((ConstantUtil.UPCOMING_COURSE.replace("#NAME", course.getCourseName()))
								.replace("#DATE", course.getCourseName()));
						/**
						 * assign course id as final.
						 */
						final int courseid = course.getCourseId();
						/**
						 * start thread for sending notification to all user that course has been
						 * published.
						 */
						(new Thread() {

							@Override
							public void run() {
								/**
								 * make object of Map type data structure for storing the user id as key and
								 * registration id as value.
								 */
								Map<Integer, String> notification = new LinkedHashMap<Integer, String>();
								/**
								 * Initialize string type array for getting all user ids.
								 */
								String[] targetIds = new String[userlist.size()];
								/**
								 * traversing on user list for getting user id and registration id.
								 */
								for (int i = 0; i < userlist.size(); i++) {
									/**
									 * if registration id is not null.
									 */
									if (userlist.get(i).getRegistrationId() != null) {
										/**
										 * put data in notification type hash map.
										 */
										notification.put(userlist.get(i).getUserId(),
												userlist.get(i).getRegistrationId());
									}
									/**
									 * put user id in string array.
									 */
									targetIds[i] = userlist.get(i).getUserId().toString();
								}
								/**
								 * make the action which would be done when user click on that notification.
								 */
								String url = "studentCourse?action=coursedetail&courseId=" + courseid;
								/**
								 * save notification entry.
								 */
								NotificationService.saveNotifcation(textMsg, url, targetIds);
								/**
								 * when notification type hash map has any data.
								 */
								if (notification.size() > 0) {
									NotificationManagement notificationManagement = new NotificationManagement();
									/**
									 * calling method for sending notification.
									 */
									notificationManagement.sendNotificationUpcomingCourse(notification, textMsg,
											courseid);
								}
							}
						}).start();
					}

				}
			}
		} catch (

		Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service savepublishedCourse method:::::", e);
		}
		return status;
	}

	/**
	 * <p>
	 * This is used for getting details about drafted course for edit.
	 * </p>
	 * 
	 * @param courseId This is first parameter which defines course id.
	 * @param userId   This is second parameter which defines user id.
	 * @return Course This returns course details based on course id and user id.
	 * @exception IOException On input error.
	 */
	public Course getCourseDetails(int courseId, int userId) {
		logger.log(Level.DEBUG, " Inside Course Service getCourseDetails method:::::::::::");
		Course course = new Course();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_DETAILS_BY_COURSE_ID,
					new Object[] { courseId, userId, Integer.parseInt(CourseEnum.DRAFTED.getValue()) });
			for (QueryData.Row row : data.getRows()) {
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setCoursehighlights(row.getRowItem(3));
				course.setPromoVideoUrl(row.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
				course.setImageName(row.getRowItem(5));
				course.setCourseTag(row.getRowItem(6));
				course.setLevelName(row.getRowItem(7));
				course.setLanguageName(row.getRowItem(8));
				course.setLevelId(Integer.parseInt(row.getRowItem(11)));
				course.setLanguageId(Integer.parseInt(row.getRowItem(12)));
				course.setSubTitle(row.getRowItem(15));
				course.setIsSchedule(Integer.parseInt(row.getRowItem(16)));
				course.setSchedulePublishDate(
						row.getRowItem(17) != null ? QbisUtils.convertMysqlDateFormatToDate(row.getRowItem(17)) : null);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getCourseDetails method:::::", e);
		}
		return course;
	}

	/**
	 * <p>
	 * This is used for making a link between test and course.
	 * </p>
	 * 
	 * @param sectioncontent This is only parameter which defines all details about
	 *                       section's content.
	 * @return Boolean This returns true if test has been linked with course
	 *         otherwise false.
	 * @exception IOException On input error.
	 */
	public Boolean linkTestWithCourse(SectionContent sectioncontent) {
		logger.log(Level.DEBUG, " Inside Course Service linkTestWithCourse method:::::::::::");
		boolean status = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.IS_SECTION_LINK_WITH_COURSE, new Object[] {
					sectioncontent.getSectionId(), sectioncontent.getCourseId(), sectioncontent.getUserId() });
			if (data.getRows().size() > 0) {
				sectioncontent.setContentOrder(
						(new CourseService().maxSectionContentOrder(sectioncontent.getSectionId())) + 1);
				savecoursesectioncontent(sectioncontent);
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service linkTestWithCourse method:::::", e);
		}
		return status;
	}

	/**
	 * <p>
	 * This is used for updating modify date after changes in course details.
	 * </p>
	 * 
	 * @param courseId This is only parameter on which based,course's modify date
	 *                 would be updated with new one.
	 * @return boolean This returns true if date would be successfully updated
	 *         otherwise false.
	 * @exception IOException On input error
	 */
	public boolean updatelastmodifyCourseDate(int courseId) {
		logger.log(Level.DEBUG, " Inside Course Service updatelastmodifyCourseDate method:::::::::::");
		boolean isUpdated = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_MODIFY_DATE_OF_COURSE,
					new Object[] { courseId });
			if (id > 0) {
				isUpdated = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updatelastmodifyCourseDate method:::::", e);
		}
		return isUpdated;
	}

	/**
	 * <p>
	 * This is used for updating content of course inside section.
	 * </p>
	 * 
	 * @param sectionContent This is only parameter on which based,Required data for
	 *                       updating course content would be extracted.
	 * @return boolean This returns true if content would be successfully updated
	 *         with new one otherwise false.
	 * @exception IOException On input error
	 */
	public boolean updatecoursesectioncontent(SectionContent sectionContent) {
		logger.log(Level.DEBUG, " Inside Course Service updatecoursesectioncontent method:::::::::::");
		boolean isUpdated = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_CONTENT,
					new Object[] { sectionContent.getContentName(), sectionContent.getContent(),
							sectionContent.getContentTypeId(), sectionContent.getContentId() });
			if (id > 0) {
				isUpdated = true;
				saveRenameTitle(sectionContent);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updatecoursesectioncontent method:::::", e);
		}
		return isUpdated;
	}

	/**
	 * <p>
	 * This is used for deleting content of course inside section.
	 * </p>
	 * 
	 * @param contentId This is only parameter on which based,content would be
	 *                  deleted from course.
	 * @return boolean This returns true if content would be successfully deleted
	 *         otherwise false.
	 * @exception IOException On input error
	 */
	public boolean deleteCourseContent(Integer contentId, Integer sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service deleteCourseContent method:::::::::::");
		boolean successfullydelete = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.DELETE_CONTENT_SECTION_MAPPING_BY_CONTENTID,
					new Object[] { contentId, sectionId });
			if (id > 0) {
				successfullydelete = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service deleteCourseContent method:::::", e);
		}
		return successfullydelete;
	}

	/**
	 * <p>
	 * This is used for deleting section of course.
	 * </p>
	 * 
	 * @param sectionId This is only parameter on which based,section would be
	 *                  deleted from course.
	 * @return boolean This returns true if section and its content would be
	 *         successfully deleted otherwise false.
	 * @exception IOException On input error
	 */
	public boolean deleteCourseSection(Integer sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service deleteCourseSection method:::::::::::");
		boolean successfullydelete = false;
		try {
			QueryManager.execuateUpdate(QueryStrings.DELETE_CONTENT_SECTION_MAPPING_BY_SECTIONID,
					new Object[] { sectionId });
			Integer sectionid = QueryManager.execuateUpdate(QueryStrings.DELETE_COURSE_SECTION,
					new Object[] { sectionId });
			if (sectionid > 0) {
				successfullydelete = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service deleteCourseSection method:::::", e);
		}
		return successfullydelete;
	}

	/**
	 * <p>
	 * This is used for getting maximum value of content's order.
	 * </p>
	 * 
	 * @param sectionId This is only parameter on which based,maximum order value
	 *                  would be fetched.
	 * @return Integer.
	 * @exception IOException          On input error
	 * @exception NullPointerException
	 */
	public Integer maxSectionContentOrder(int sectionId) {
		logger.log(Level.DEBUG, " Inside Course Service maxSectionContentOrder method:::::::::::");
		Integer maxOrder = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.MAX_SECTION_CONTENT_ORDER,
					new Object[] { sectionId });
			for (QueryData.Row row : data.getRows()) {
				maxOrder = Integer.parseInt(row.getRowItem(0) != null ? row.getRowItem(0) : "0");
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service maxSectionContentOrder method:::::", e);
		}
		return maxOrder;
	}

	/**
	 * <p>
	 * This is used for updating content's order.
	 * </p>
	 * 
	 * @param contentId   This is first parameter for updating order based on this.
	 * @param orderNumber This is second parameter for describing the order of
	 *                    content.
	 * @return boolean return true is successfully updated otherwise not.
	 * @exception IOException On input error
	 */
	public boolean updateContentOrder(int contentId, int orderNumber) {
		logger.log(Level.DEBUG, " Inside Course Service updateContentOrder method:::::::::::");
		boolean isUpdated = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_SECTION_CONTENT_ORDER,
					new Object[] { orderNumber, contentId });
			if (id > 0) {
				isUpdated = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updateContentOrder method:::::", e);
		}
		return isUpdated;
	}

	/**
	 * This function is used for perform operation for share the course to existing
	 * user and new user.
	 * 
	 * @param courseId
	 * @param userList
	 * @param message
	 * @param user
	 * @param path
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean performUserCourseMapping(Integer courseId, Map<String, Integer> userList, String message, User user,
			String path) {
		logger.log(Level.DEBUG, " Inside Course Service performUserCourseMapping method:::::::::::");
		boolean status = false;
		try {
			Iterator entries = userList.entrySet().iterator();
			/**
			 * getting all email.
			 */
			while (entries.hasNext()) {

				Map.Entry entry = (Map.Entry) entries.next();
				/**
				 * An Integer for identify the user id.
				 */
				Integer userId = (Integer) entry.getValue();
				/**
				 * An Integer for identify the emailId.
				 */
				String email = (String) entry.getKey();

				/**
				 * if user exists in our system.
				 */
				if (userId > 0) {
					/**
					 * call function for sharing the course and adding its mapping.
					 */
					status = addUserCourseMapping(courseId, userId);
					if (status) {
						/**
						 * send share link on user's mail.
						 */
						sendCourseShareLinkToExistUser(courseId, email, message, path);
					}
				}
				/**
				 * user does not exist in our system.
				 */
				else {
					/**
					 * create account of user.
					 */
					user.setEmail(email);
					user.setRoleId(ConstantUtil.TRAINEE_ROLE_ID);
					Integer newUserId = new UserService().createTraineeAccountWithCouseSharing(email, user, path,
							courseId, message);

					/**
					 * if account has been created successfully
					 */
					if (newUserId > 0) {
						/**
						 * call function for sharing the test.
						 */
						status = addUserCourseMapping(courseId, newUserId);
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service performUserCourseMapping method:::::", e);
		}
		return status;
	}

	/**
	 * 
	 * This is performing operation for saving course mapping and share the course.
	 * 
	 * @param courseId
	 * @param userId
	 * @return boolean This returns true.
	 * @exception IOException On input error.
	 */
	public boolean addUserCourseMapping(int courseId, int userId) {
		logger.log(Level.DEBUG, " Inside Course Service addUserCourseMapping method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_COURSE_MAPPING,
					new Object[] { courseId, userId });
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service addUserCourseMapping method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for sending link of shared course to exist user.
	 * 
	 * @param courseId
	 * @param email
	 * @param message
	 * @param path
	 */
	public void sendCourseShareLinkToExistUser(final int courseId, final String email, final String message,
			String path) {
		logger.log(Level.DEBUG, " Inside Course Service sendCourseShareLinkToExistUser method:::::::::::");
		final Map<Object, Object> dataobject = new HashMap<Object, Object>();
		String sharedLink = path + "/previewSharedCourse?courseId=" + courseId;
		if (message != null && message.length() > 0) {

			dataobject.put("messsage", message);
		} else {

			dataobject.put("messsage", "You have been invited for Course.");
		}
		dataobject.put("buttonText", "Course");
		dataobject.put("typename", "course");
		dataobject.put("link", sharedLink);
		(new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.sendEmail(email, "Invitation for accessing course",
							EmailSender.generateSharedMsgForExistUser(dataobject));
				} catch (Exception e) {
					logger.log(Level.ERROR,
							"Exception Inside Course Service sendCourseShareLinkToExistUser method:::::", e);
				}
			}
		}).start();
	}

	/**
	 * This method is used for uploading the course icon on server.
	 * 
	 * @param file
	 * @param courseId
	 * @param userId
	 * @return String
	 */
	public String uploadCourseIcon(MultipartFile file, Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, " Inside Course Service uploadCourseIcon method:::::::::::");
		String newCourseIconName = "";
		String PROFILE_IMAGE_PATH = ConstantUtil.PROFILE_IMAGE_PATH;
		String IMAGE_DIRECTORY = ConstantUtil.ICON_PATH;
		String uploadPath = PROFILE_IMAGE_PATH + IMAGE_DIRECTORY;
		if (!file.isEmpty()) {
			try {
				// Creating the directory to store file
				File dir = new File(uploadPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
				File dest = new File(uploadPath + file.getOriginalFilename());
				String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				dest.renameTo(new File(uploadPath + "course_" + courseId + "_" + fileName + extension));
				newCourseIconName = "course_" + courseId + "_" + fileName + extension;
				deleteIconFromDirectory(courseId, userId);
				setCourseIconName(newCourseIconName, courseId, userId);
			} catch (Exception e) {
				logger.log(Level.ERROR, "Exception Inside Course Service uploadCourseIcon method:::::", e);
			}
		}
		return newCourseIconName;
	}

	public void setCourseIconName(String newIconName, Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service setCourseIconName method:::::::::::");
		try {
			QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_ICON_NAME,
					new Object[] { newIconName, courseId, userId });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service setCourseIconName method:::::", e);
		}
	}

	/**
	 * This is used for delete the previous course icon after uploading new.
	 * 
	 * @param courseId
	 * @param userId
	 */
	public void deleteIconFromDirectory(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, " Inside Course Service deleteIconFromDirectory method:::::::::::");
		String PROFILE_IMAGE_PATH = ConstantUtil.PROFILE_IMAGE_PATH;
		String IMAGE_DIRECTORY = ConstantUtil.ICON_PATH;
		String uploadPath = PROFILE_IMAGE_PATH + IMAGE_DIRECTORY;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_DETAILS_BY_COURSE_ID,
					new Object[] { courseId, userId, 0 });
			for (QueryData.Row row : data.getRows()) {
				if (row.getRowItem(5) != null || row.getRowItem(5) != "") {
					File dest = new File(uploadPath + row.getRowItem(5));
					dest.delete();
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service deleteIconFromDirectory method:::::", e);
		}
	}

	/**
	 * This is used for getting all section's content details for saving or
	 * updating.
	 * 
	 * @param req
	 * @param userId
	 * @return String
	 */
	public String saveCourseContentOperation(SectionContent req, Integer userId) {
		logger.log(Level.DEBUG, " Inside Course Service saveCourseContentOperation method:::::::::::");
		boolean contentStatus = false;
		String res = "";
		int isTest = 0;
		if (req.getTestId() != null) {
			isTest = 1;
			SectionContent sectionContent = new SectionContent();
			String contentData = new CourseService().checkFileTypeValid("test");
			String str[] = contentData.split("####");
			int contentTypeId = Integer.parseInt(str[0]);
			res = res + contentTypeId + "####";
			if (contentTypeId > 0) {
				sectionContent.setContentName(req.getTestName());
				sectionContent.setSectionId(req.getSectionId());
				sectionContent.setContent(req.getTestId().toString());
				sectionContent.setContentTypeId(contentTypeId);
				sectionContent.setContentType(str[1]);
				sectionContent.setContentIconPath(str[2]);
				sectionContent.setUserId(userId);
				sectionContent.setAttemptId(req.getAttemptId());
				;
				sectionContent.setContentOrder(
						(new CourseService().maxSectionContentOrder(sectionContent.getSectionId())) + 1);
				res = res + "0####";
				contentStatus = new CourseService().savecoursesectioncontent1(sectionContent, req.getSessionId());
				if (contentStatus) {
					res = res + sectionContent.getContentName() + "####" + sectionContent.getContentType() + "####"
							+ sectionContent.getSectionId() + "####" + ConstantUtil.SERVER_IP
							+ ConstantUtil.PROJECT_NAME + "testpreview?action=frame&testId="
							+ sectionContent.getContent() + "####" + sectionContent.getContentId() + "####" + isTest
							+ "####" + sectionContent.getContentIconPath() + "####";
				}
			}
		}

		else {
			SectionContent sectionContent = new SectionContent();
			String filepath = req.getContentPath();
			String contentId = req.getContentId().toString();
			String courseId = req.getCourseId().toString();
			String sectionId = req.getSectionId().toString();
			String sourcePath = ConstantUtil.FILE_MANAGER_PATH;
			String destPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			File source = new File(sourcePath + filepath);
			File dest = new File(destPath + filepath.substring(filepath.lastIndexOf("/") + 1));
			try {
				String extension = filepath.substring(filepath.lastIndexOf(".") + 1);
				String contentData = new CourseService()
						.checkFileTypeValid(filepath.substring(filepath.lastIndexOf(".") + 1));
				String str[] = contentData.split("####");
				int contentTypeId = Integer.parseInt(str[0]);
				res = res + contentTypeId + "####";
				if (contentTypeId > 0) {
					sectionContent.setContentName(
							filepath.substring(filepath.lastIndexOf("/") + 1, filepath.lastIndexOf(".")));
					sectionContent.setSectionId(Integer.parseInt(sectionId));
					sectionContent.setContent(
							courseId + "-" + sectionContent.getSectionId() + "-" + UUID.randomUUID() + "." + extension);
					sectionContent.setContentTypeId(contentTypeId);
					sectionContent.setContentType(str[1]);
					sectionContent.setContentIconPath(str[2]);
					if (Integer.parseInt(contentId) > 0) {
						res = res + "1####";
						sectionContent.setContentId(Integer.parseInt(contentId));
						contentStatus = new CourseService().updatecoursesectioncontent(sectionContent);
					} else {
						res = res + "0####";
						sectionContent.setContentOrder(
								(new CourseService().maxSectionContentOrder(sectionContent.getSectionId())) + 1);
						contentStatus = new CourseService().savecoursesectioncontent1(sectionContent,
								req.getSessionId());
					}
					if (contentStatus) {
						res = res + sectionContent.getContentName() + "####" + sectionContent.getContentType() + "####"
								+ sectionContent.getSectionId() + "####" + ConstantUtil.SERVER_IP
								+ ConstantUtil.COURSE_FILE_DIRECTORY + sectionContent.getContent() + "####"
								+ sectionContent.getContentId() + "####" + isTest + "####"
								+ sectionContent.getContentIconPath() + "####";
					}
					File file = new File(destPath + sectionContent.getContent());
					if (!file.exists()) {
						FileUtils.copyFile(source, dest);
						dest.renameTo(new File(destPath + sectionContent.getContent()));
					}

				}
			} catch (IOException e) {
				logger.log(Level.ERROR, "Exception Inside Course Service saveCourseContentOperation method:::::", e);
			}
		}
		return res;
	}

	/**
	 * This is used for saving test details as course's content.
	 * 
	 * @param test
	 * @return boolean
	 */
	public boolean saveTestAsCourseContent(Test test) {
		logger.log(Level.DEBUG, " Inside Course Service saveTestAsCourseContent method:::::::::::");
		boolean status = false;
		try {
			SectionContent sectionContent = new SectionContent();
			sectionContent.setContentName(test.getTestName());
			sectionContent.setCourseId(test.getCourseId());
			sectionContent.setSectionId(test.getSectionId());
			sectionContent.setTestId(test.getTestId());
			sectionContent.setUserId(test.getUserId());
			sectionContent.setContent(test.getTestId().toString());
			String contentData = new CourseService().checkFileTypeValid("test");
			String str[] = contentData.split("####");
			sectionContent.setContentTypeId(Integer.parseInt(str[0]));
			status = new CourseService().linkTestWithCourse(sectionContent);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveTestAsCourseContent method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for updating course's content if this is test type.
	 * 
	 * @param test
	 */
	public void updateTestAsCourseContent(Test test) {
		logger.log(Level.DEBUG, " Inside Course Service updateTestAsCourseContent method:::::::::::");
		try {
			SectionContent sectionContent = new SectionContent();
			sectionContent.setContentName(test.getTestName());
			sectionContent.setCourseId(test.getTestId());
			sectionContent.setSectionId(test.getSectionId());
			sectionContent.setTestId(test.getTestId());
			sectionContent.setUserId(test.getUserId());
			sectionContent.setContent(test.getTestId().toString());
			String contentData = new CourseService().checkFileTypeValid("test");
			String str[] = contentData.split("####");
			sectionContent.setContentTypeId(Integer.parseInt(str[0]));
			sectionContent.setContentId(test.getContentId());
			new CourseService().updatecoursesectioncontent(sectionContent);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service updateTestAsCourseContent method:::::", e);
		}
	}

	/**
	 * This is used for updating the title of course's content.
	 * 
	 * @param content
	 * @return Boolean
	 */
	public Boolean saveRenameTitle(SectionContent content) {
		logger.log(Level.DEBUG, " Inside Course Service saveRenameTitle method:::::::::::");
		boolean status = true;
		try {
			QueryManager.execuateUpdate(QueryStrings.UPDATE_TITLE_CONTENT_SECTION_MAPPING,
					new Object[] { content.getContentName(), content.getContentId(), content.getSectionId() });

		} catch (Exception e) {
			status = false;
			logger.log(Level.ERROR, "Exception Inside Course Service saveRenameTitle method:::::", e);
		}
		return status;
	}

	/**
	 * This function is used for saving the title for course.
	 * 
	 * @param course
	 * @param userid
	 * @return Course
	 */
	public Course saveCourseTitle(Course course, Integer userid) {
		logger.log(Level.DEBUG, " Inside Course Service saveCourseTitle method:::::::::::");
		try {
			int id = QueryManager.execuateUpdate(QueryStrings.INSERT_COURSE_TITLE,
					new Object[] { course.getCourseName(), course.getSubTitle(), userid });
			if (id > 0) {
				course.setCourseId(id);
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service saveCourseTitle method:::::", e);
		}
		return course;
	}

	/**
	 * This function is used deleting the drafted course.
	 * 
	 * @param courseId
	 * @return Boolean
	 */
	public Boolean deleteDraftedCourse(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, " Inside Course Service deleteDraftedCourse method:::::::::::");
		boolean flag = true;
		try {
			QueryManager.execuateUpdate(QueryStrings.DEL_CON_SEC_MAP_BY_COURSEID, new Object[] { courseId });
			QueryManager.execuateUpdate(QueryStrings.DEL_SECTION_BY_COURSEID, new Object[] { courseId });
			Course course = getCourseDetailsById(courseId, userId, 0);
			if (course.getCourseType() != null && course.getCourseType() == 1) {
				String courseRootPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.SCORM_COURSE_DIRECTORY
						+ File.separator + course.getScormRootPath();
				File file = new File(courseRootPath);
				FileUtils.deleteDirectory(file);
			}
			QueryManager.execuateUpdate(QueryStrings.DEL_COURSE_BY_COURSEID, new Object[] { courseId });

		} catch (Exception e) {
			flag = false;
			logger.log(Level.ERROR, "Exception Inside Course Service deleteDraftedCourse method:::::", e);
		}
		return flag;
	}

	/**
	 * This function is used for getting list of all sections of courses.
	 * 
	 * @param userId
	 * @return List<Section>
	 */
	public List<Section> getAllSectionList(Integer userId, Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service getAllSectionList method:::::::::::");
		List<Section> sectionList = new ArrayList<Section>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION_LIST_ALL,
					new Object[] { userId, courseId });
			for (QueryData.Row row : data.getRows()) {
				Section section = new Section();
				if (row.getRowItem(1).contains("(Practice)") == false) {
					section.setSectionId(Integer.parseInt(row.getRowItem(0)));
					section.setSectionName(row.getRowItem(1));
					section.setCourseName(row.getRowItem(2));
				}
				String info = "";
				QueryData cont = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_CONTENT_OF_SECTION,
						new Object[] { section.getSectionId() });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " " + rowCont.getRowItem(1) + " ";
				}
				section.setContentInfo(info.length() > 0 ? info : "0 Content");
				if (info.length() > 0) {
					sectionList.add(section);
				}

			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getAllSectionList method:::::", e);
		}

		return sectionList;
	}

	/**
	 * This function is used importing sections and their contents.
	 * 
	 * @param sections
	 * @param courseId
	 */
	public void importSectionContents(int[] sections, Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service importSectionContents method:::::::::::");
		try {
			for (int i = 0; i < sections.length; i++) {
				QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_ONE_SECTION,
						new Object[] { sections[i], sections[i] });
				Section section = null;
				for (QueryData.Row row : data.getRows()) {
					section = new Section();
					section.setSectionName(row.getRowItem(1));
					section.setCourseId(courseId);
					section.setContentInfo(row.getRowItem(3));
					section.setIsQuizMandatory(Integer.parseInt(row.getRowItem(8)));
					section.setPassingCriteria(row.getRowItem(9) != null ? Integer.parseInt(row.getRowItem(9)) : null);
					section.setMinTimeSpent(Integer.parseInt(row.getRowItem(10)));
				}
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_COURSE_SECTION_DETAILS_WITH_SETTING,
						new Object[] { section.getSectionName(), section.getCourseId(), section.getIsQuizMandatory(),
								section.getPassingCriteria(), section.getMinTimeSpent(), null });
				if (id > 0) {
					section.setSectionId(id);
					savePracticeSessionDetails(section, id);
					if (section.getContentInfo() != null) {
						String[] contentids = section.getContentInfo().split(",");
						for (int j = 0; j < contentids.length; j++) {
							SectionContent content = null;
							content = new UploadContentService().findOneContent(Integer.parseInt(contentids[i]));
							QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_SECTION_MAPPING,
									new Object[] { Integer.parseInt(contentids[j]), id, content.getContentName() });
						}
					}
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service importSectionContents method:::::", e);
		}
	}

	/**
	 * This function is used for counting total number of drafted course.
	 * 
	 * @param courseId
	 * @return Boolean
	 */
	public Boolean getDraftedTestCountByCourseid(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service getDraftedTestCountByCourseid method:::::::::::");
		boolean status = true;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_DRAFTED_CONTENT_IN_COURSE,
					new Object[] { courseId });
			if (data.getRows().size() > 0) {
				status = false;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getDraftedTestCountByCourseid method:::::", e);
		}
		return status;
	}

	/**
	 * This method id used for checking that your course's published time has been
	 * expired or not.
	 * 
	 * @param courseId
	 * @return Boolean
	 */
	public Boolean checkCourseScheduleTimeStatus(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service checkCourseScheduleTimeStatus method:::::::::::");
		Boolean status = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_COURSE_SCHEDULE_TIME_EXPIRE,
					new Object[] { courseId });
			if (data.getRows().size() > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service checkCourseScheduleTimeStatus method:::::", e);
		}
		return status;
	}

	/**
	 * Function is used for checking that course is schedule or not.
	 * 
	 * @param courseId
	 * @return Boolean
	 */
	public Boolean checkCourseIsSchedule(Integer courseId) {
		logger.log(Level.DEBUG, " Inside Course Service checkCourseIsSchedule method:::::::::::");
		Boolean isSchedule = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_COURSE_IS_SCHEDULE,
					new Object[] { courseId });
			if (data.getRows().size() > 0) {
				isSchedule = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service checkCourseIsSchedule method:::::", e);
		}
		return isSchedule;
	}

	/**
	 * This make will be used for published the scheduled test.
	 */
	public static void makePublishedtoScheduleCourse() {
		try {
			QueryManager.execuateUpdate(QueryStrings.CHANGE_PUBLISH_STATUS_OF_SCHEDULE_COURSE, new Object[] {});
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service makePublishedtoScheduleCourse method:::::", e);
		}
	}

	/**
	 * Function to get list of all courses of a user.
	 * 
	 * @param userId
	 * @return List<Course>
	 */
	public List<Course> getAllCourseList(Integer userId) {
		logger.log(Level.DEBUG, " Inside Course Service getAllCourseList method:::::::::::");
		List<Course> list = new ArrayList<Course>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ALL_COURSE_LIST_BY_USERID,
					new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setPublish(Integer.parseInt(row.getRowItem(2)));
				list.add(course);
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service getAllCourseList method:::::", e);
		}

		return list;
	}

	/**
	 * This is used getting setting information of a particular section in course.
	 * 
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @return SectionContent
	 */
	public Section getCourseSectionSettingInfo(Integer userId, Integer courseId, Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Course Service getCourseSectionSettingInfo method:::::::::::");
		Section section = new Section();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_SECTION_SETTING_INFO,
					new Object[] { userId, courseId, sectionId });
			for (QueryData.Row row : data.getRows()) {
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				section.setIsQuizMandatory(Integer.parseInt(row.getRowItem(1)));
				section.setPassingCriteria(row.getRowItem(2) != null ? Integer.parseInt(row.getRowItem(2)) : null);
				section.setMinTimeSpent(Integer.parseInt(row.getRowItem(3)));
				section.setSectionId(sectionId);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Course Service getCourseSectionSettingInfo method:::::::::::" + e);
		}
		return section;
	}

	/**
	 * This is used getting setting information of a particular section in course.
	 * 
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @return SectionContent
	 */
	public Boolean updateCourseSectionSettingInfo(Section section) {
		logger.log(Level.DEBUG, "Inside Course Service updateCourseSectionSettingInfo method:::::::::::");
		Boolean isUpdated = false;
		try {
			if (section.getIsQuizMandatory() == 0) {
				section.setPassingCriteria(null);
			}
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_SECTION_SETTING_INFO,
					new Object[] { section.getIsQuizMandatory(), section.getPassingCriteria(),
							section.getMinTimeSpent(), section.getCourseId(), section.getSectionId() });
			if (id > 0) {
				isUpdated = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Course Service updateCourseSectionSettingInfo method:::::::::::" + e);
		}
		return isUpdated;
	}

	/**
	 * This is used for validating the section's setting.
	 * 
	 * @param courseId
	 * @param userId
	 * @return map
	 */
	public Map<String, Object> checkSectionSettingValidation(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service checkSectionSettingValidation method:::::::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Section> sectionList = getSectionSettingBasedOnCourseId(courseId, userId);
			List<Section> errorSectionList = new ArrayList<Section>();
			for (int sec = 0; sec < sectionList.size(); sec++) {
				Section section = new Section();
				if (sectionList.get(sec).getIsQuizMandatory() == 1) {
					Integer nOfQuiz = noOfQuizInSection(sectionList.get(sec).getSectionId());
					if (nOfQuiz == 0) {
						section.setSectionId(sectionList.get(sec).getSectionId());
						section.setSectionName(sectionList.get(sec).getSectionName());
						errorSectionList.add(section);
					}
				}
			}
			if (errorSectionList.size() > 0) {
				map.put("errorSectionList", errorSectionList);
				map.put("msg", "Validation Failed");
				map.put("status", 401);
			} else {
				map.put("msg", "Success");
				map.put("status", 200);
			}
		} catch (Exception e) {
			map.put("errorSectionList", new ArrayList<Section>());
			map.put("msg", "Validation Failed");
			map.put("status", 401);
			logger.log(Level.ERROR,
					"Exception Inside Course Service checkSectionSettingValidation method:::::::::::" + e);
		}
		return map;
	}

	/**
	 * This is used getting section's setting details based on course id.
	 * 
	 * @param courseId
	 * @param userId
	 * @return sectionList
	 */
	public List<Section> getSectionSettingBasedOnCourseId(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionSettingBasedOnCourseId method:::::::::::");
		List<Section> sectionList = new ArrayList<Section>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_WITH_SETTING_BASED_ON_COURSE_ID,
					new Object[] { courseId, userId });
			for (QueryData.Row row : data.getRows()) {
				Section section = new Section();
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				section.setSectionName(row.getRowItem(1));
				section.setIsQuizMandatory(row.getRowItem(2) != null ? Integer.parseInt(row.getRowItem(2)) : 0);
				section.setPassingCriteria(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : 0);
				section.setMinTimeSpent(row.getRowItem(4) != null ? Integer.parseInt(row.getRowItem(4)) : 0);
				sectionList.add(section);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Course Service getSectionSettingBasedOnCourseId method:::::::::::" + e);
		}
		return sectionList;
	}

	/**
	 * This is used for counting the total quizzes inside a section in course.
	 * 
	 * @param sectionId
	 * @return noOfQuiz
	 */
	public Integer noOfQuizInSection(Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Course Service noOfQuizInSection method:::::::::::");
		Integer noOfQuiz = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_NO_OF_QUIZ_IN_SECTION,
					new Object[] { sectionId, CONTENT_TYPE_ID_FOR_QUIZ });
			for (QueryData.Row row : data.getRows()) {
				noOfQuiz = row.getRowItem(0) != null ? Integer.parseInt(row.getRowItem(0)) : 0;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Course Service noOfQuizInSection method:::::::::::" + e);
		}
		return noOfQuiz;
	}

	public Integer getSectionCountByCourseId(Integer courseId) {
		Integer countOfSection = null;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTIONS_WITHIN_SAME_COURSE,
					new Object[] { courseId });

			for (QueryData.Row row : data.getRows()) {
				countOfSection = Integer.parseInt(row.getRowItem(0));
			}

		} catch (Exception e) {

		}
		return countOfSection;

	}

	// --------------------------start----course-----details---api---------------------------
	/**
	 * This method is used for getting course details.
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public Course getCourseDetails(Integer courseId) {
		logger.log(Level.DEBUG, "Inside Course Service getCourseDetails method:::::::::::");
		Course course = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_DETAILS_FOR_ATTEMPT,
				new Object[] { courseId, Integer.parseInt(CourseEnum.PUBLISHED.getValue()) });
		for (QueryData.Row row : data.getRows()) {
			course = new Course();
			course.setCourseId(Integer.parseInt(row.getRowItem(0)));
			course.setCourseName(row.getRowItem(1));
			course.setCourseDesc(row.getRowItem(2));
			course.setPromoVideoUrl(row.getRowItem(4));
			course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
			course.setImageName(row.getRowItem(5));
			course.setCourseTag(row.getRowItem(6));
			List<Attempts> attempts = getAttemptList(null);
			course.setSectionList(getSectionListForAttempt(courseId, attempts));
		}
		return course;
	}

	public Minute getTotalViewSession(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getTotalViewSession method:::::::::::");
		int count = 0;
		int min = 0;
		Integer sec = 0;
		String time = "0:0";
		Minute minute = new Minute();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TOTAL_VIDEO_SESSION,
					new Object[] { courseId, userId });
			for (QueryData.Row row : data.getRows()) {

				count = Integer.parseInt(row.getRowItem(0));
				time = row.getRowItem(1);
				if (time != null) {
					String sa[] = time.split(":");
					min = min + Integer.parseInt(sa[0]);
					sec = sec + Integer.parseInt(sa[1]);
					min = min * count;
					sec = sec * count;
					if (sec > 60) {
						min = min + sec / 60;
						sec = sec % 60;
					}
				}
			}
			System.out.println("==========" + sec + "==" + min);
			minute.setMin(min);
			minute.setSecond(sec);
			minute.setSessionView(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minute;
	}

	public Integer getSessionId(Integer courseId) {
		logger.log(Level.DEBUG, "Inside Course Service getSessionId method:::::::::::");
		Integer sessionId = 1;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SESSIONID, new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {

				sessionId = Integer.parseInt(row.getRowItem(0));

			}
			System.out.println("========" + sessionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionId;
	}

	/**
	 * This method is used for getting section list for attempt.
	 * 
	 * @param courseId {@link Integer}
	 * @return {@link List}
	 */
	private List<Section> getSectionListForAttempt(Integer courseId, List<Attempts> attempts) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionListForAttempt method:::::::::::");
		List<Section> sectionList = new ArrayList<Section>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_FOR_ATTEMPT,
				new Object[] { courseId, false });
		for (QueryData.Row row : data.getRows()) {
			Section section = new Section();
			section.setSectionId(Integer.parseInt(row.getRowItem(0)));
			section.setSectionName(row.getRowItem(1));
			List<SectionAttempt> attemptList = new ArrayList<SectionAttempt>();
			attempts.forEach(attempt -> {
				SectionAttempt sectionAttempt = new SectionAttempt();
				sectionAttempt.setAttemptId(attempt.getId());
				sectionAttempt.setIsPractice(false);
				sectionAttempt.setContentList(getSectionContentList(attempt.getId(), section.getSectionId()));
				attemptList.add(sectionAttempt);
			});
			/**
			 * for practice
			 */
			SectionAttempt practice = new SectionAttempt();
			practice.setAttemptId(null);
			practice.setIsPractice(true);
			practice.setContentList(getPracticeSectionContent(section.getSectionId()));
			attemptList.add(practice);
			section.setAttemptList(attemptList);
			sectionList.add(section);
		}
		return sectionList;
	}

	/**
	 * This method is used for getting section content list.
	 * 
	 * @param attemptId
	 * @param sectionId
	 * @return contentList {@link List}
	 */
	private List<SectionContent> getSectionContentList(Integer attemptId, Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionContentList method:::::::::::");
		QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CONTENT_LIST_FOR_ATTEMPT,
				new Object[] { sectionId, attemptId });
		List<SectionContent> contentList = new ArrayList<SectionContent>();
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		for (QueryData.Row row : contentdata.getRows()) {
			SectionContent content = new SectionContent();
			content.setContentId(Integer.parseInt(row.getRowItem(0)));
			content.setContentName(row.getRowItem(1) != null ? row.getRowItem(1) : "");
			content.setContentTypeId(Integer.parseInt(row.getRowItem(2)));
			content.setContentType(row.getRowItem(3) != null ? row.getRowItem(3) : "");
			content.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + row.getRowItem(4) != null
					? AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + row.getRowItem(4)
					: "");
			contentList.add(content);
		}
		return contentList;
	}

	/**
	 * This method is used for getting practice content list.
	 * 
	 * @param sectionId
	 * @return contentList {@link List}
	 */
	private List<SectionContent> getPracticeSectionContent(Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Course Service getPracticeSectionContent method:::::::::::");
		QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CONTENT_LIST_FOR_PRACTICE,
				new Object[] { sectionId });
		List<SectionContent> contentList = new ArrayList<SectionContent>();
		for (QueryData.Row row : contentdata.getRows()) {
			SectionContent content = new SectionContent();
			content.setContentId(Integer.parseInt(row.getRowItem(0)));
			content.setContentName(row.getRowItem(1) != null ? row.getRowItem(1) : "");
			content.setContentTypeId(Integer.parseInt(row.getRowItem(2)));
			content.setContentType(row.getRowItem(3) != null ? row.getRowItem(3) : "");
			contentList.add(content);
		}
		return contentList;
	}

	// --------------------------end----course-----details---api---------------------------

	// --------------------------start----course-----details---api---------------------------
	/**
	 * This method is used for getting course details.
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public Course getCourseDetails(Integer courseId, Integer sectionId, Integer attemptId,Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getCourseDetails method:::::::::::");
		Course course = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_DETAILS_FOR_ATTEMPT,
				new Object[] { courseId, Integer.parseInt(CourseEnum.PUBLISHED.getValue()) });
		for (QueryData.Row row : data.getRows()) {
			course = new Course();
			course.setCourseId(Integer.parseInt(row.getRowItem(0)));
			course.setCourseName(row.getRowItem(1));
			course.setCourseDesc(row.getRowItem(2));
			course.setPromoVideoUrl(row.getRowItem(4));
			course.setCourseImageUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(5));
			course.setImageName(row.getRowItem(5));
			course.setCourseTag(row.getRowItem(6));
			List<Attempts> attempts = getAttemptList(attemptId);
			course.setSectionList(getSectionListForAttempt1(courseId, attempts, sectionId, attemptId,userId));
		}
		return course;
	}

	/**
	 * This method is used for check video status.
	 * 
	 * @param courseId  {@link Integer}
	 * 
	 * @param sectionId
	 * @return {@link Boolean}
	 */
	public List<Integer> videoCompleted(Integer courseId, Integer sectionId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service List videoCompleted method:::::::::::");
		List<Integer> isStatus = new ArrayList<>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_VIDEO_STATUS1,
				new Object[] { courseId, sectionId, userId });
		for (QueryData.Row row : data.getRows()) {
			isStatus.add(Integer.parseInt(row.getRowItem(0)));
			System.out.println("statusList--=======" + isStatus);
		}

		return isStatus;
	}

	public Integer videoCompleted(Integer courseId, Integer sectionId, Integer attemptId, Long sessionId,
			Integer contentId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service videoCompleted method:::::::::::");
		Integer isStatus = 0;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SINGLE_VIDEO_STATUS,
				new Object[] { userId, courseId, sectionId, sessionId});
		for (QueryData.Row row : data.getRows()) {
			isStatus = Integer.parseInt(row.getRowItem(0));
			System.err.println("status=======" + isStatus);
		}

		return isStatus;
	}

	/**
	 * This method is used for getting section list for attempt.
	 * 
	 * @param courseId {@link Integer}
	 * @return {@link List}
	 */
	private List<Section> getSectionListForAttempt1(Integer courseId, List<Attempts> attempts, Integer sectionId,
			Integer attemptId,Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionListForAttempt method:::::::::::");
		List<Section> sectionList = new ArrayList<Section>();
		QueryData data = null;
		if (sectionId != null && sectionId > 0) {
			data = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_FOR_ATTEMPT,
					new Object[] { courseId, false });
		} else {
			data = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_FOR_ATTEMPT,
					new Object[] { courseId, sectionId, false });
		}
		for (QueryData.Row row : data.getRows()) {
			Section section = new Section();
			section.setSectionId(Integer.parseInt(row.getRowItem(0)));
			section.setSectionName(row.getRowItem(1));
			List<SectionAttempt> attemptList = new ArrayList<SectionAttempt>();
			attempts.forEach(attempt -> {
				SectionAttempt sectionAttempt = new SectionAttempt();
				sectionAttempt.setAttemptId(attempt.getId());
				sectionAttempt.setIsPractice(false);
				sectionAttempt.setSessionList(getCourseSessionList1(courseId,section.getSectionId(), attempt.getId(), userId));
				sectionAttempt.setAllAttempt1Status(checkAllAttempt1TestStatusOldApi(userId, courseId, sectionId,sectionAttempt.getSessionList()));
				// sectionAttempt.setContentList(getSectionContentList(attempt.getId(),
				// section.getSectionId()));
				attemptList.add(sectionAttempt);
			});
			if (!(attemptId != null && attemptId > 0)) {
				/**
				 * for practice
				 */
				SectionAttempt practice = new SectionAttempt();
				practice.setAttemptId(null);
				practice.setIsPractice(true);
				practice.setContentList(getPracticeSectionContent1(section.getSectionId()));
				attemptList.add(practice);
			}
			section.setAttemptList(attemptList);
			sectionList.add(section);
		}
		return sectionList;
	}

	/**
	 * This method is used for getting course session list.
	 * 
	 * @param sectionId
	 * @param attemptId
	 * @return
	 */
	private List<CourseSession> getCourseSessionList1(Integer courseId,Integer sectionId, Integer attemptId,Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getCourseSessionList method:::::::::::");
		QueryData sessiondata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_SESSION_LIST,
				new Object[] { sectionId });
		List<CourseSession> sessionList = new ArrayList<>();
		for (QueryData.Row row : sessiondata.getRows()) {
			CourseSession courseSession = new CourseSession();
			courseSession.setId(Long.parseLong(row.getRowItem(0)));
			courseSession.setName(row.getRowItem(1));
			courseSession.setIsfree(Integer.parseInt(row.getRowItem(2)));
			courseSession.setVideoStatus(videoCompleted(courseId,sectionId,attemptId, courseSession.getId(),null,userId));
			UserSessionLisence userSessionLisence=studentCourseService.getUserSessionLisenceDetails1(courseSession.getId(),userId);
		    courseSession.setUserSessionStatus(userSessionLisence.getUserSessionStatus());
			courseSession.setContentList(getSectionContentList1(attemptId, sectionId, courseSession.getId(),courseId,userId));
			//courseSession.setAllAttempt1Status(checkAllAttempt1TestStatusOldApi(userId, courseId, sectionId,courseSession.getContentList()));
			sessionList.add(courseSession);
		}
		return sessionList;
	}

	/**
	 * This method is used for getting section content list.
	 * 
	 * @param attemptId
	 * @param sectionId
	 * @param sessionId
	 * @return contentList {@link List}
	 */
	private List<SectionContent> getSectionContentList1(Integer attemptId, Integer sectionId, Long sessionId,Integer courseId,Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionContentList method:::::::::::");
		QueryData contentdata = QueryManager.execuateQuery(
				QueryStrings.GET_COURSE_CONTENT_LIST_FOR_ATTEMPT_WITH_SESSION,
				new Object[] { sectionId, sessionId, attemptId });
		List<SectionContent> contentList = new ArrayList<SectionContent>();
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		for (QueryData.Row row : contentdata.getRows()) {
			boolean flag=false;
			SectionContent content = new SectionContent();
			content.setSectionId(sectionId);
			content.setSessionId(sessionId);
			QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_ISCHAPTER,
					new Object[] {sectionId});
			for (QueryData.Row row1 : data1.getRows()) {
				content.setIsChapterTest(Integer.parseInt(row1.getRowItem(0)));
			}
			content.setContentId(Integer.parseInt(row.getRowItem(0)));
			content.setContentName(row.getRowItem(1) != null ? row.getRowItem(1) : "");
			content.setContentTypeId(Integer.parseInt(row.getRowItem(2)));
			content.setContentType(row.getRowItem(3) != null ? row.getRowItem(3) : "");
			content.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + row.getRowItem(4) != null
					? AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + row.getRowItem(4)
					: "");
			content.setAttemptId(Integer.parseInt(row.getRowItem(5)));
			contentList.add(content);
			ContentView  view=findAlreadyCourseContentViewDetails(userId, courseId, sectionId, content.getAttemptId(),new Long(sessionId),content.getContentId());
			if(view !=null)
			{
		    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
			System.out.println("status flag"+flag);
			}
			content.setTestStatus(flag);
		}
		Collections.sort(contentList, (o1, o2) -> o2.getContentTypeId() - o1.getContentTypeId());
		return contentList;
	}

	/**
	 * This method is used for getting practice content list.
	 * 
	 * @param sectionId
	 * @return contentList {@link List}
	 */
	private List<SectionContent> getPracticeSectionContent1(Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Course Service getPracticeSectionContent method:::::::::::");
		QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CONTENT_LIST_FOR_PRACTICE,
				new Object[] { sectionId });
		List<SectionContent> contentList = new ArrayList<SectionContent>();
		for (QueryData.Row row : contentdata.getRows()) {
			SectionContent content = new SectionContent();
			content.setContentId(Integer.parseInt(row.getRowItem(0)));
			content.setContentName(row.getRowItem(1) != null ? row.getRowItem(1) : "");
			content.setContentTypeId(Integer.parseInt(row.getRowItem(2)));
			content.setContentType(row.getRowItem(3) != null ? row.getRowItem(3) : "");
			contentList.add(content);
		}
		return contentList;
	}

	/**
	 * This method is used for getting section content based on attempt;
	 * 
	 * @param courseId
	 * @param sectionId
	 * @param attemptId
	 * @param sessionId
	 * @param contentId
	 * @param userId
	 * @return
	 */
	public SectionContent getSectionContentForAttempt(Integer courseId, Integer sectionId, Integer attemptId,
			Long sessionId, Integer contentId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getSectionContentForAttempt method:::::::::::");
		SectionContent content = null;
		try
		{
	   Integer isPractice=0;
	   Integer isChapterTest=0;
        if(sessionId == null)
			sessionId=0l;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION,
				new Object[] {sectionId});
		for (QueryData.Row row : data.getRows()) {
			isPractice=Integer.parseInt(row.getRowItem(0));
		}
		QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_ISCHAPTER,
				new Object[] {sectionId});
		for (QueryData.Row row1 : data1.getRows()) {
			isChapterTest=Integer.parseInt(row1.getRowItem(0));
		}
		
		QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CONTENT_FOR_ATTEMPT_WITH_SESSION,
				new Object[] { sectionId, sessionId, attemptId, contentId });
		List<SectionContent> contentList = new ArrayList<SectionContent>();
		for (QueryData.Row row : contentdata.getRows()) {
			content = new SectionContent();
			content.setIsPractice(isPractice);
			content.setIsChapterTest(isChapterTest);
			content.setSessions(studentCourseService.getSessionDetailsForApiNew(sectionId, courseId, userId, null, isChapterTest));
			content.setAllAttempt1Status(checkAllAttempt1TestStatus(userId, courseId, sectionId, content.getSessions()));
			System.err.println("Nananananananananananananana"+content.getAllAttempt1Status());
			content.setContentId(Integer.parseInt(row.getRowItem(0)));
			content.setContentName(row.getRowItem(1) != null ? row.getRowItem(1) : "");
			content.setContentTypeId(Integer.parseInt(row.getRowItem(2)));
			content.setContentType(row.getRowItem(3) != null ? row.getRowItem(3) : "");
			content.setStreamingUrl(row.getRowItem(5) != null ? row.getRowItem(5) : "");
			content.setSessionId(sessionId);
			content.setAttemptId(attemptId);
			content.setSectionId(sectionId);
			content.setIsStatus(videoCompleted(courseId, sectionId, attemptId, sessionId, contentId, userId));
			ContentView view = findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attemptId, sessionId,
					contentId);
			/**
			 * When user's content view id is already existed.
			 */
			if (view != null) {
				content.setContentViewId(view.getId());
				if (content.getContentType().equals("TEST")) {
					boolean isSubmitted = studentTestService.checkTestSubmitted(view.getTestAttemptId());
					if (isSubmitted) {
						System.out.println("in submit  true-------------");
							
							  Test test = studentTestService.getAttemptedQuestionAnswerByUser(view.getTestAttemptId(),
							  userId); test.setIsReview(1); content.setTest(test);
							 
					} else {
						System.out.println("in submit  else-------------");
						
							
							  Integer testId = Integer.parseInt(row.getRowItem(4)); content.setTest(
							  studentTestService.testDetailforGivenTest(testId, userId,
							  view.getTestAttemptId())); updateCourseContentViewActivity(view);
							 
					}
				} else if (content.getContentType().equals("VIDEO")) {
					content.setContent(
							"https://s3.us-east-1.amazonaws.com/ondemandtest-source-10o600jqb6ibb/VID-20160615-WA0000_2.mp4");
					updateCourseContentViewActivity(view);
				}
			} else {
				if (content.getContentType().equals("TEST")) {
					Integer testId = Integer.parseInt(row.getRowItem(4));
					content.setTest(studentTestService.testDetailforGivenTest(testId, userId, null));
					Integer viewId = insertCourseContentViewActivity(userId, courseId, sectionId, attemptId, sessionId,
							contentId, content.getTest().getUserTestAttemptId());
					content.setContentViewId(viewId);
				} else if (content.getContentType().equals("VIDEO")) {
					content.setContent(
							"https://s3.us-east-1.amazonaws.com/ondemandtest-source-10o600jqb6ibb/VID-20160615-WA0000_2.mp4");
					Integer viewId = insertCourseContentViewActivity(userId, courseId, sectionId, attemptId, sessionId,
							contentId, null);
					content.setContentViewId(viewId);
					content.setIsStatus(videoCompleted(courseId, sectionId, attemptId, sessionId, contentId, userId));
				}
			}
			content.setSessionName(getSessionName(sessionId));
			contentList.add(content);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	public List<TestStatus> checkStatusTestSubmit(Integer userId,Integer courseId,Integer sectionId,List<Session> sessionList)
	{
		 logger.log(Level.DEBUG, "Inside Course Service checkStatusTestSubmit method:::::::::::");
			
		List<TestStatus> testAttempt=new ArrayList<>();
	
		 for (Iterator iterator = sessionList.iterator(); iterator.hasNext();) {
			Session session = (Session) iterator.next();
			List<Attempts> attempts= session.getAttempts();
			 for (Iterator iterator2 = attempts.iterator(); iterator2.hasNext();) {
					boolean flag=false;
				 TestStatus testStatus=new TestStatus();
				Attempts attempts2 = (Attempts) iterator2.next();
				
				ContentView view =findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attempts2.getId(),new Long(session.getSessonId()),
						attempts2.getContentId());
				if(view !=null)
				{
			    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
				System.out.println("status flag"+flag);
				}
				testStatus.setAttemptId(attempts2.getId());
				testStatus.setStatus(flag);
				testStatus.setSessionId(session.getSessonId());
				testStatus.setContentId(attempts2.getContentId());
				testAttempt.add(testStatus);
				
			}
			
		}
		 System.out.println("boolean==========="+testAttempt);
		 testAttempt.forEach(m->{
			 System.out.println(m.getAttemptId()+""+m.getStatus()+""+m.getSessionId()+""+m.getContentId());
		 });
		 return testAttempt;
	}
	
	public UserTestAttempt getTestSummary(Integer userId,Integer courseId,Integer sectionId,Integer attemptId,Long sessionId,Integer contentId)
	{
		 logger.log(Level.DEBUG, "Inside Course Service checkStatusTestSubmit method:::::::::::");	
				ContentView view =findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attemptId,sessionId,
						contentId);
				 UserTestAttempt userTestAttempt=getTestDetails(view.getTestAttemptId());
		
		 return userTestAttempt;
	}
	
	public UserTestAttempt getTestDetails(Integer testAttemptId)
	{
		 logger.log(Level.DEBUG, "Inside Course Service getTestDetails method:::::::::::");	
		 UserTestAttempt userTestAttempt=null;
			QueryData contentdata = QueryManager.execuateQuery(QueryStrings.GET_TEST_DETAIL,
					new Object[] {testAttemptId }); 
			for (QueryData.Row row : contentdata.getRows())
			{
				userTestAttempt=new UserTestAttempt();
				userTestAttempt.setTestId(Integer.parseInt(row.getRowItem(0)));
				userTestAttempt.setTimeTaken(row.getRowItem(1));
				userTestAttempt.setObtainMarks(Float.parseFloat(row.getRowItem(2)));
			    userTestAttempt.setCorrectQueAttempt(Integer.parseInt(row.getRowItem(3)));
			    userTestAttempt.setWrongQueAttempt(Integer.parseInt(row.getRowItem(4)));
			    userTestAttempt.setUnAttemptQue(Integer.parseInt(row.getRowItem(5)));
			    userTestAttempt.setCorrectQueScore(Float.parseFloat(row.getRowItem(6)));
			    userTestAttempt.setWrongQueScore(Float.parseFloat(row.getRowItem(7)));
			    userTestAttempt.setEveryquestionmark(Integer.parseInt(row.getRowItem(8)));
			}
			System.out.println(userTestAttempt.getTestId()+"="+userTestAttempt.getTimeTaken()+"="+userTestAttempt.getObtainMarks());	
		return userTestAttempt;
	}
	
	
	
	 public List<String> getTagList(Integer testId)
		{	
			 logger.log(Level.DEBUG, "Inside Course Service getTagList method:::::::::::");
			 List<Tag> tagList=new ArrayList<>();
				List<String> allValue=new ArrayList<String>();
		 	 QueryData contentdata1 = QueryManager.execuateQuery(QueryStrings.GET_TAG_LIST_BY_TESTID,
						new Object[] { testId}); 
			
			  for (QueryData.Row row : contentdata1.getRows())
			  {
				  Tag tag =new Tag();
			 	    tag.setId(row.getRowItem(0));
					tag.setValue(row.getRowItem(1));
					tag.setType(row.getRowItem(2));
					tagList.add(tag);
			  }
			  tagList.forEach(m->{
				  System.out.println(m.getValue());
			  });
			  if(tagList.size()>0 && tagList != null)
			  {
				  allValue= tagList.stream().map(m->m.getValue()).collect(Collectors.toList());
			  }
			     
			  return allValue;
			
		 }
		



	 
	
	
	 public   List<Question>  getRandomQuestion(List<String> allValue,Integer TestId)
	 {
	logger.log(Level.DEBUG, "Inside Course Service getDetailTagDetail method:::::::::::");
	     List<Integer> questionIdList=new ArrayList<>();
	     List<Question> questionList=new ArrayList<>();
	     List<Question> allQuestionList=new ArrayList<>();
	     int maxquestion=0;
	     Integer start=1;
	     ObjectMapper mapper = new ObjectMapper();
	     
	     try {
	    	 if(allValue.size()>0 && allValue != null)
	    	 {
	    		 
				QueryData contentdata1 = QueryManager.execuateQuery(QueryStrings.GET_RANDOM_QUESTION_ID1,
						new Object[] { allValue.get(0),allValue.get(1),allValue.get(2),allValue.get(3),allValue.get(4),allValue.get(5)});
				for (QueryData.Row row : contentdata1.getRows()) {
					
					if(Integer.parseInt(row.getRowItem(0)) == 6)
					{
						System.out.println("------------");
						Question question=new Question();
						question.setQuestionId(Integer.parseInt(row.getRowItem(2)));
						questionList.add(question);	
					}
			 	} 
			}
	    	 
	    		QueryData contentdata1 = QueryManager.execuateQuery(QueryStrings.GET_TOTAL_NO_QUESTION,
						new Object[] { TestId});
                  for (QueryData.Row row : contentdata1.getRows()) {
                	  maxquestion=Integer.parseInt(row.getRowItem(0));
			 	}
			
	    	 
	    	 int i=1;
	    	 
	    	 questionList=getQuestion(questionList,questionList.size(),maxquestion);
	    	 questionList.forEach(m->{
	    		 System.out.println("data==="+m.getQuestionId());
	    	 });
	    	 for (Question questionId : questionList) {
	    		 
					
					//System.out.println("question id==="+questId);
			    	QueryData data= QueryManager.execuateQuery(QueryStrings.GET_QUESTION,
							new Object[] {questionId.getQuestionId()});
			    	
			    	for (QueryData.Row row : data.getRows()) {
			    	
			    		Question question=new Question();
			    		question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
			    		System.out.println("----------------");
						question.setQuestionName(row.getRowItem(1));
						question.setExplanation(row.getRowItem(2));
						question.setQuestionType(Integer.parseInt(row.getRowItem(3)));
						question.setQuestionNo(i);
						question.setIsFormula(Integer.parseInt(row.getRowItem(4)));
						if (row.getRowItem(6) != null) {
							QuestionSetting questionSetting = mapper.readValue(row.getRowItem(6),
									new TypeReference<QuestionSetting>() {
									});
							question.setQuestionSetting(questionSetting);		
					    }
						String givenAnswer = row.getRowItem(7);
						System.out.println("Given Answer=="+givenAnswer);
						switch (question.getQuestionType()) {
						/**
						 * If question is multiple choice type.
						 */
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							question.setOption(getOptionsForMultipleTypeQuestion(question));
							break;
						/**
						 * If question is single choice type.
						 */
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							question.setOption(getOptionsForMultipleTypeQuestion(question));
							break;
						/**
						 * If question is sort list type.
						 */
						case ConstantUtil.SORT_LIST_TYPE:
						//	getOptionsForSortListTypeQuestion(question, givenAnswer);
							break;
						/**
						 * If question is choice matrix type.
						 */
						case ConstantUtil.CHOICE_MATRIX_TYPE:
						//	getOptionsForChoiceMatrixTypeQuestion(question, givenAnswer);
							break;
						/**
						 * If question is classification type.
						 */
						case ConstantUtil.CLASSIFICATION_TYPE:
						//	getOptionsForCommonStructrueQuestion(question, givenAnswer);
							break;
						/**
						 * If question is match list type.
						 */
						case ConstantUtil.MATCH_LIST:
						//	getOptionsForCommonStructrueQuestion(question, givenAnswer);
							break;
						}
						allQuestionList.add(question);
						i++;
					
				}
				
		      }
			
		
	     }
	     catch (Exception e) {
			// TODO: handle exception
	    	 e.printStackTrace();
		}
	     allQuestionList.forEach(m->{
	    	 System.out.println(m.getQuestionId()+"=="+m.getQuestionName()+"=="+m.getExplanation()+"=="+m.getQuestionType()+"=="+m.getIsFormula()+"=="+m.getQuestionSetting());
	    	 
	     }); 
	     return allQuestionList;
	 } 
	



	 
	 
	
	
	 public Option[] getOptionsForMultipleTypeQuestion(Question question) {
			logger.log(Level.DEBUG, "Inside Course Service getOptionsForMultipleTypeQuestion method ::::::");
			List<Option> optionlist = new ArrayList<Option>();
			try {
				/**
				 * getting option's detail for this question.
				 */
				QueryData dataOption = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID,
						new Object[] { question.getQuestionId() });

				
				/**
				 * set option details.
				 */
				for (QueryData.Row row : dataOption.getRows()) {
					Option option = new Option();
					option.setOptionId(Integer.parseInt(row.getRowItem(0)));
					option.setOptionName(row.getRowItem(1));
					option.setOptionPosition(row.getRowItem(2).charAt(0));
					option.setAnswerStatus(Integer.parseInt(row.getRowItem(3)));
					optionlist.add(option);
				}
			//	question.setOptionList(optionlist);
			} catch (Exception e) {
				logger.log(Level.ERROR,
						"Exception Inside Course Service getOptionsForMultipleTypeQuestion method ::::::", e);
			}
			return optionlist.toArray(new Option[optionlist.size()]);

		}

	 
	public String getSessionName(Long sessionId) {
		logger.log(Level.DEBUG, "Inside Course Service getSessionName method:::::::::::");
		String sessionName = "";
		try
		{
		QueryData contentdata1 = QueryManager.execuateQuery(QueryStrings.GET_COURSE_SESSION_NAME,
				new Object[] { sessionId });
		for (QueryData.Row row : contentdata1.getRows()) {
			sessionName = row.getRowItem(0) != null ? row.getRowItem(0) : "";
		}
		// System.out.println("============"+sessionName);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		return sessionName;
	}

	/**
	 * This method is used for inserting course content view activity id.
	 * 
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @param attemptId
	 * @param sessionId
	 * @param contentId
	 * @param testAttemptId
	 * @return
	 */
	private Integer insertCourseContentViewActivity(Integer userId, Integer courseId, Integer sectionId,
			Integer attemptId, Long sessionId, Integer contentId, Integer testAttemptId) {
		logger.log(Level.DEBUG, "Inside Course Service insertCourseContentViewActivity method:::::::::::");
		return QueryManager.execuateUpdate(QueryStrings.INSERT_COURSE_CONTENT_VIEW_ACTIVITY,
				new Object[] { userId, courseId, sectionId, attemptId, sessionId, contentId, testAttemptId, 1 });
	}

	/**
	 * This method is used for getting already attempt content view details.
	 * 
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @param attemptId
	 * @param sessionId
	 * @param contentId
	 * @return
	 */
	private ContentView findAlreadyCourseContentViewDetails(Integer userId, Integer courseId, Integer sectionId,
			Integer attemptId, Long sessionId, Integer contentId) {
		logger.log(Level.DEBUG, "Inside Course Service findAlreadyCourseContentViewDetails method:::::::::::");
		ContentView view = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_COURSE_CONTENT_VIEW_ACTIVITY,
				new Object[] { userId, courseId, sectionId, attemptId, sessionId, contentId });
		for (QueryData.Row row : data.getRows()) {
			view = new ContentView();
			view.setId(Integer.parseInt(row.getRowItem(0)));
			view.setTestAttemptId(row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : null);
			view.setView(Integer.parseInt(row.getRowItem(2)));
		}
		return view;
	}

	/**
	 * This method is used for getting content view details.
	 * 
	 * @param userId
	 * @param viewId
	 * @return
	 */
	private ContentView findContentViewById(Integer userId, Integer viewId) {
		logger.log(Level.DEBUG, "Inside Course Service findAlreadyCourseContentViewDetails method:::::::::::");
		ContentView view = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_COURSE_CONTENT_VIEW_BY_ID,
				new Object[] { userId, viewId });
		for (QueryData.Row row : data.getRows()) {
			view = new ContentView();
			view.setId(Integer.parseInt(row.getRowItem(0)));
			view.setTestAttemptId(row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : null);
			view.setView(Integer.parseInt(row.getRowItem(2)));
			view.setContentId(Integer.parseInt(row.getRowItem(3)));
			view.setSessionId(Long.valueOf(row.getRowItem(4)));
		}
		return view;
	}

	/**
	 * This method is used for updating the course content view details.
	 * 
	 * @param view
	 */
	private void updateCourseContentViewActivity(ContentView view) {
		logger.log(Level.DEBUG, "Inside Course Service updateCourseContentViewActivity method:::::::::::");
		Integer viewNo = view.getView() + 1;
		QueryManager.execuateUpdate(QueryStrings.UPDATE_COURSE_CONTENT_VIEW_ACTIVITY,
				new Object[] { viewNo, view.getId() });
	}

	/**
	 * This method is used for getting course attempt response
	 * 
	 * @param courseId
	 * @param sectionId
	 * @param attemptId
	 * @param sessionId
	 * @param contentId
	 * @param userId
	 * @return
	 */
	public CourseAttemptResponse getCourseAttemptResponse(Integer courseId, Integer sectionId, Integer attemptId,
			Long sessionId, Integer contentId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Course Service getCourseAttemptResponse method:::::::::::");
		CourseAttemptResponse response = new CourseAttemptResponse();
		response.setCourse(getCourseDetails(courseId, sectionId, attemptId,userId));
		response.setContent(getSectionContentForAttempt(courseId, sectionId, attemptId, sessionId, contentId, userId));
		return response;
	}

	/**
	 * This method is used for submitting test details.
	 * 
	 * @param viewId
	 * @param test
	 * @param userId
	 */
	public UserTestAttempt submitTestAsContent(Integer viewId, Test test, Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitTestAsContent method ::::::");
		ContentView view = findContentViewById(userId, viewId);
		try
		{
		if (view != null) {
			boolean isSubmitted = studentTestService.checkTestSubmitted(view.getTestAttemptId());
			if (!isSubmitted) {
				test.setUserTestAttemptId(view.getTestAttemptId());
				test.setUserId(userId);
				test.setTestStatus(1);
				UserTestAttempt attempt = studentTestService.submitTestDetails1(test,userId);
				if (attempt != null) {
					attempt.setContentId(view.getContentId());
					attempt.setSessionId(view.getSessionId());
				}
				return attempt;
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public Boolean saveSessionCheckbox(Session session) {
		Boolean status = false;
		if (session.getSessonId() != null) {
			Integer id = QueryManager.execuateUpdate(QueryStrings.INSERT_SESSION_CHECKBOX, new Object[] {
					session.getIsLive(), session.getIsEnable(), session.getIsFree(), session.getSessonId() });
			if (id > 0) {
				status = true;

			}
			System.out.println("saveSessionCheckbox" + status);
		}

		return status;

	}
	
	
	
	
	public Boolean checkAllTestStatus(Integer userId,Integer courseId,Integer sectionId,List<Session> sessionList)
	{
		 logger.log(Level.DEBUG, "Inside Course Service checkStatusTestSubmit method:::::::::::");
			
	      Boolean allTestStatus=true;
		 for (Iterator iterator = sessionList.iterator(); iterator.hasNext();) {
			Session session = (Session) iterator.next();
			List<Attempts> attempts= session.getAttempts();
			 for (Iterator iterator2 = attempts.iterator(); iterator2.hasNext();) {
					boolean flag=false;
				 TestStatus testStatus=new TestStatus();
				Attempts attempts2 = (Attempts) iterator2.next();
				
				ContentView view =findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attempts2.getId(),new Long(session.getSessonId()),
						attempts2.getContentId());
				if(view !=null)
				{
			    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
				System.out.println("status flag"+flag);
				}
				if(flag == false)
				{
					allTestStatus=false;
				}
				
			}
			
		}
		 return allTestStatus;
	}
	
public Boolean checkAllAttempt1TestStatus(Integer userId,Integer courseId,Integer sectionId,List<Session> sessionList)
	{
		 logger.log(Level.DEBUG, "Inside Course Service checkStatusTestSubmit method:::::::::::");
		
	      Boolean allAttempt1Status=true;
		 for (Iterator iterator = sessionList.iterator(); iterator.hasNext();) {
			Session session = (Session) iterator.next();
			List<Attempts> attempts= session.getAttempts();
			 for (Iterator iterator2 = attempts.iterator(); iterator2.hasNext();) {
					boolean flag=false;
				 TestStatus testStatus=new TestStatus();
				Attempts attempts2 = (Attempts) iterator2.next();
				if(attempts2.getId() == 1)
				{
				ContentView view =findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attempts2.getId(),new Long(session.getSessonId()),
						attempts2.getContentId());
				if(view !=null)
				{
			    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
				System.out.println("status flag"+flag);
				}
				if(flag == false)
				{
					allAttempt1Status=false;
				}
				}
				
			}
			
		}
		 return allAttempt1Status;
	}

public Boolean checkAllAttempt1TestStatusOldApi(Integer userId,Integer courseId,Integer sectionId,List<CourseSession> sessionList)
{
	 logger.log(Level.DEBUG, "Inside Course Service checkStatusTestSubmit method:::::::::::");
	
      Boolean allAttempt1Status=true;
	 for (Iterator iterator = sessionList.iterator(); iterator.hasNext();) {
		 CourseSession courseSession = (CourseSession) iterator.next();
			List<SectionContent> sectionContentList= courseSession.getContentList();
			 for (Iterator iterator2 = sectionContentList.iterator(); iterator2.hasNext();) {
				 SectionContent session = (SectionContent) iterator2.next();
		  	boolean flag=false;
			if(session.getAttemptId() == 1 && session.getIsChapterTest() != 1 && session.getContentType().equalsIgnoreCase("test"))
			{
				
				System.err.println("----------------"+session.getSectionId()+"attempt id"+session.getAttemptId()+"="+session.getSessionId()+"="+session.getContentId());
			ContentView view =findAlreadyCourseContentViewDetails(userId, courseId, sectionId, session.getAttemptId(),new Long(session.getSessionId()),
					 session.getContentId());
			if(view !=null)
			{
		    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
			System.out.println("status flag=="+flag);
			}
			if(flag == false)
			{
				allAttempt1Status=false;
			}
			}
			 }
	
	}
	 return allAttempt1Status;
}



public boolean checkCourseExists(Integer CourseId)
{
	boolean flag = false;
	try {
		QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_USERID_IN_USER, new Object[] { CourseId });
		User user = new User();
		for (QueryData.Row row : data.getRows()) {
			user.setEmail((row.getRowItem(0)));

		}
		if (user.getEmail() != null) {
			flag = true;
		} else {
			flag = false;
		}

	} catch (Exception e) {
	}
	return flag;
}


public Boolean saveSessionCheckbox(Integer val,Integer sessonId,String attr) {
	Boolean status = false;
	
	if(attr.equals("isLive"+sessonId)) {
		Integer id =
				  QueryManager.execuateUpdate(QueryStrings.INSERT_SESSION_CHECKBOX_ISLIVE, new
				  Object[] { val,sessonId });
		if (id > 0) { status = true;}
	}	
	else if(attr.equals("isEnable"+sessonId)) {
		System.out.println("........"+attr);
		Integer id =
				  QueryManager.execuateUpdate(QueryStrings.INSERT_SESSION_CHECKBOX_ISENABLE, new
				  Object[] { val,sessonId });
		if (id > 0) { status = true;}
		System.out.println(val);
	}	
	else if(attr.equals("isFree"+sessonId)) {
		System.out.println(attr);
		System.out.println(sessonId);
		Integer id =
				  QueryManager.execuateUpdate(QueryStrings.INSERT_SESSION_CHECKBOX_ISFREE, new
				  Object[] { val,sessonId });
		if (id > 0) { status = true;}
	}	
	return status;

}
public List<Session> fetchData(){ 
	  QueryData   data = QueryManager.execuateQuery(QueryStrings.FIND_SESSION_CHECKBOX,new
	  Object[] {});
	  List<Session> sessionList = new ArrayList<Session>();
	  for(QueryData.Row row : data.getRows()) 
	  {
	   Session session=new Session();
	  session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	  session.setIsLive(Integer.parseInt(row.getRowItem(1)));
	  session.setIsEnable(Integer.parseInt(row.getRowItem(2)));
	  session.setIsFree(Integer.parseInt(row.getRowItem(3)));
	  
	  sessionList.add(session);
	  
	  } 
	  return sessionList; }

	
public List<Question> getQuestion(List<Question> list, int totalItems,Integer maxQuestion) {
	Random rand = new Random();

//create a temporary list for storing 
//selected element 
	if(maxQuestion==0)
		maxQuestion=5;
	List<Question> newList = new ArrayList<>();
	for (int i = 1; i <= totalItems; i++) {

//take a raundom index between 0 to size  
//of given List 
		int randomIndex = rand.nextInt(list.size());

//add element in temporary list 
		if(i<=maxQuestion)
			newList.add(list.get(randomIndex));
		else
			break;
//Remove selected element from orginal list 
		list.remove(randomIndex);
	}
	return newList;
}


public Integer getIsPublishedByCourseId(Integer courseId) {
	int isPublished=0;
	QueryData   data = QueryManager.execuateQuery(QueryStrings.GET_ISPUBLISHED,new
			  Object[] {courseId});
	for(QueryData.Row row : data.getRows()) {
		isPublished=Integer.parseInt(row.getRowItem(0));
	}
	return isPublished;
}



}


