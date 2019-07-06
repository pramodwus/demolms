package com.qbis.services;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.EmailSender;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.SimpleMail;
import com.qbis.controller.LoginController;
import com.qbis.gcm.NotificationManagement;
import com.qbis.model.Attempts;
import com.qbis.model.ContentActivity;
import com.qbis.model.ContentView;
import com.qbis.model.Course;
import com.qbis.model.CourseActivity;
import com.qbis.model.Mailsender;
import com.qbis.model.Minute;
import com.qbis.model.Section;
import com.qbis.model.SectionContent;
import com.qbis.model.Session;
import com.qbis.model.SessionFeedback;
import com.qbis.model.StudentGraph;
import com.qbis.model.Test;
import com.qbis.model.User;
import com.qbis.model.UserSessionLisence;
import com.qbis.model.UserTestAttempt;

/**
 * This is used for performing all operations related to course in student side.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class StudentCourseService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	/**
	 * An Integer which describes the content type id for quiz in course
	 * content.
	 */
	private static final Integer CONTENT_TYPE_ID_FOR_QUIZ = 2;

	/**
	 * An Integer which describes that quiz is mandatory for a section.
	 */
	private static final Integer QUIZ_MANDATORY_ENABLE = 1;
    
	
	@Autowired
	CourseService courseservise;
	@Autowired
	StudentTestService  studentTestService;
	
	@Autowired
	UserService userService;
	/**
	 * <p>
	 * This is used for fetching all section list for a particular course.
	 * </p>
	 * 
	 * @param courseId
	 *            This is only parameter on which based , all section details
	 *            are getting.
	 * @return List<Section> This returns A List of Sections which has all
	 *         information about every section for a test.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */

	public Map<String, Object> getSectionList(int courseId, int userId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getSectionList method::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Section> sectionList = new ArrayList<Section>();
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		try {
			Course course = getCourseDetail(courseId);
			map.put("courseName", course.getCourseName());
			/**
			 * get latest attempted content
			 */
			CourseActivity courseActivity = getLatestAttemptedContentDetails(
					courseId, userId);
			map.put("courseActivity", courseActivity);

			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_SECTION_LIST_FOR_COURSE, new Object[] {
							userId, courseId });
			for (QueryData.Row row : data.getRows()) {
				Section section = new Section();
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				section.setSectionName(row.getRowItem(1));
				section.setSectionSortOrder(row.getRowItem(2) != null ? Integer
						.parseInt(row.getRowItem(2)) : 0);
				section.setIsQuizMandatory(row.getRowItem(3) != null ? Integer
						.parseInt(row.getRowItem(3)) : 0);
				section.setPassingCriteria(row.getRowItem(4) != null ? Integer
						.parseInt(row.getRowItem(4)) : null);
				section.setMinTimeSpent(row.getRowItem(5) != null ? Integer
						.parseInt(row.getRowItem(5)) : 0);
				section.setTotalSpentTime(row.getRowItem(6) != null ? Integer
						.parseInt(row.getRowItem(6)) : 0);
				QueryData contentdata = QueryManager.execuateQuery(
						QueryStrings.GET_COURSE_CONTENT_LIST,
						new Object[] { section.getSectionId() });
				SectionContent sectionContent[] = new SectionContent[contentdata
						.getRows().size()];
				int sec = 0;

				/**
				 * This describes that in how many quiz have you been passed
				 * according to section's passing criteria if quiz is mandatory
				 * for that section.
				 */
				int totalNumberOfPassed = 0;

				/**
				 * This describes that how many quizzes are in this section for
				 * which quiz is mandatory.
				 */
				int numberOfQuizzes = 0;
				for (QueryData.Row content : contentdata.getRows()) {
					sectionContent[sec] = new SectionContent();
					sectionContent[sec].setContentId(Integer.parseInt(content
							.getRowItem(0)));
					sectionContent[sec]
							.setContentName(content.getRowItem(8) == null ? content
									.getRowItem(1) : content.getRowItem(8));
					sectionContent[sec].setSectionId(Integer.parseInt(content
							.getRowItem(2)));
					sectionContent[sec].setContent(content.getRowItem(3));
					sectionContent[sec].setContentTypeId(Integer
							.parseInt(content.getRowItem(4)));
					sectionContent[sec].setContentType(content.getRowItem(5));
					sectionContent[sec].setContentIconPath(content
							.getRowItem(6));
					String iconpath = ConstantUtil.SERVER_IP
							+ ConstantUtil.ICON_PATH + content.getRowItem(6);
					sectionContent[sec].setContentIconURL(iconpath);
					String contentURL = ConstantUtil.SERVER_IP
							+ ConstantUtil.COURSE_FILE_DIRECTORY
							+ content.getRowItem(3);
					sectionContent[sec].setIsExternalURL(Integer
							.parseInt(content.getRowItem(10)));

					/**
					 * If quiz is mandatory and this content is test type.
					 */
					if (section.getIsQuizMandatory() == 1
							&& sectionContent[sec].getContentType()
									.equalsIgnoreCase("TEST")
							&& section.getPassingCriteria() != null) {
						/**
						 * increase value of number for quiz for this section by
						 * 1.
						 */
						numberOfQuizzes = numberOfQuizzes + 1;

						/**
						 * fetch all attempt detail for this quiz if user has
						 * been successfully passed the passing criteria for
						 * this section.
						 */
						QueryData quiz = QueryManager
								.execuateQuery(
										QueryStrings.GET_ATTEMPTED_TEST_DETAILS_BASED_ON_PASSING_CRITERIA_FOR_SECTION,
										new Object[] {
												sectionContent[sec]
														.getContentId(),
												section.getSectionId(),
												courseId,
												section.getPassingCriteria() });

						/**
						 * If user has passed the passing criteria of section in
						 * any attempt.
						 */
						if (quiz.getRows().size() > 0) {

							/**
							 * increase the passed attempt by one.
							 */
							totalNumberOfPassed = totalNumberOfPassed + 1;
						}
					}

					if (sectionContent[sec].getIsExternalURL() == 0) {
						if (content.getRowItem(5).equalsIgnoreCase("TEST")) {
							sectionContent[sec]
									.setContentPath(ConstantUtil.SERVER_IP
											+ ConstantUtil.PROJECT_NAME
											+ "testpreview?action=frame&testId="
											+ content.getRowItem(3));
						} else{
							sectionContent[sec]
									.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH
											+ "/" + content.getRowItem(3));
						}
					} else {
						sectionContent[sec].setContentType("URL");
						sectionContent[sec].setContentPath(content
								.getRowItem(3));
					}
					if (content.getRowItem(5).equalsIgnoreCase("VIDEO")
							|| content.getRowItem(5).equalsIgnoreCase("PDF")) {
						sectionContent[sec]
								.setQuestionList(new QuestionService()
										.findVideoQuestionByContentId(sectionContent[sec]
												.getContentId()));
					}
					if (content.getRowItem(5).equalsIgnoreCase("PPT")
							|| content.getRowItem(5).equalsIgnoreCase("PPTX")) {
						sectionContent[sec]
								.setQuestionList(new UploadContentService()
										.findAudioByContentId(sectionContent[sec]
												.getContentId()));
					}
					sectionContent[sec].setContentURL(content.getRowItem(5)
							.equalsIgnoreCase("TEST") ? content.getRowItem(3)
							: contentURL);
					sectionContent[sec]
							.setContentOrder(content.getRowItem(7) != null ? Integer
									.parseInt(content.getRowItem(7)) : 0);
					sectionContent[sec].setVisiblity(Integer.parseInt(content
							.getRowItem(9)));

					QueryData favData = QueryManager.execuateQuery(
							QueryStrings.CHECK_FAVORITE_CONTENT,
							new Object[] { sectionContent[sec].getContentId(),
									userId });

					if (favData.getRows().size() > 0) {
						for (QueryData.Row favrow : favData.getRows()) {
							sectionContent[sec].setFavoriateId(Integer
									.parseInt(favrow.getRowItem(0)));
							sectionContent[sec].setFavorites(Integer
									.parseInt(favrow.getRowItem(1)));
						}
					} else {
						sectionContent[sec].setFavoriateId(0);
					}

					QueryData abuseData = QueryManager.execuateQuery(
							QueryStrings.CHECK_ABUSE_CONTENT,
							new Object[] { sectionContent[sec].getContentId(),
									userId });

					if (abuseData.getRows().size() > 0) {
						for (QueryData.Row abuserow : abuseData.getRows()) {
							sectionContent[sec].setReportId(Integer
									.parseInt(abuserow.getRowItem(0)));
							sectionContent[sec].setAbuseReport(abuserow
									.getRowItem(1));
						}
					} else {
						sectionContent[sec].setReportId(0);
						sectionContent[sec].setAbuseReport("");
					}
					if (content.getRowItem(11) != null) {
						sectionContent[sec].setNumPages(Integer
								.parseInt(content.getRowItem(11)));
					}
					sec++;
				}

				/**
				 * if number of quizzes and passes attempts are equal then user
				 * has successfully passed the criteria for all quizzes in this
				 * section.
				 */
				if (numberOfQuizzes == totalNumberOfPassed) {

					/**
					 * set true value that indicates that user has passed the
					 * passing criteria for section.
					 */
					section.setIsPassedTheCriteria(true);
				}
				/**
				 * if number of quizzes and passes attempts are not equal.
				 */
				else {
					/**
					 * set false value that indicates that user has not passed
					 * the passing criteria for section.
					 */
					section.setIsPassedTheCriteria(false);
				}
				section.setSectionContent(sectionContent);
				sectionList.add(section);
				map.put("status", 200);
				map.put("msg", "sectionList");
				map.put("sectionList", sectionList);
			}

			/**
			 * If data is received then insert course view entry in course
			 * activity table.
			 */
			if (data.getRows().size() > 0) {
				int courseActivityId = saveCourseViewDetailsByUser(userId,
						courseId);
				map.put("courseActivityId", courseActivityId);
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getSectionList method::::::",
					e);
			map.put("status", 401);
			map.put("msg", "Operation failed");
		}

		return map;
	}

	/**
	 * This is used getting latest attempted content details of course.
	 * 
	 * @param courseId
	 * @param userId
	 * @return CourseActivity
	 */
	public CourseActivity getLatestAttemptedContentDetails(Integer courseId,
			Integer userId) {
		logger.log(
				Level.DEBUG,
				"Inside Student Course Service in getLatestAttemptedContentDetails method::::::");
		CourseActivity courseActivity = new CourseActivity();
		try {
			QueryData data = QueryManager
					.execuateQuery(
							QueryStrings.GET_LATEST_ATTEMPTED_CONTENT_DETAILS_IN_COURSE,
							new Object[] { courseId, userId, courseId, userId });
			for (QueryData.Row row : data.getRows()) {
				courseActivity.setCourseId(Integer.parseInt(row.getRowItem(0)));
				courseActivity
						.setCourseActivityId(row.getRowItem(1) != null ? Integer
								.parseInt(row.getRowItem(1)) : 0);
				courseActivity.setSectionId(row.getRowItem(2) != null ? Integer
						.parseInt(row.getRowItem(2)) : 0);
				courseActivity.setContentId(row.getRowItem(3) != null ? Integer
						.parseInt(row.getRowItem(3)) : 0);
				courseActivity.setStartTime(row.getRowItem(4));
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getLatestAttemptedContentDetails method::::::",
					e);
		}
		return courseActivity;
	}

	/**
	 * <p>
	 * This is used for getting list of all courses based on paging.
	 * </p>
	 * 
	 * @param isPublished
	 *            This is first parameter which is used for getting published
	 *            course's list.
	 * @param offset
	 *            This is second parameter
	 * @return List This returns a Course type List which contains information
	 *         about course's.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public Map<String, Object> getPublishedCourseList(Integer userId,
			int isPublished, int offset) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getPublishedCourseList method::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courseList = new ArrayList<Course>();
		Integer orgId = UserService.getOrgId(userId);
		try {
			int skip = offset * ConstantUtil.LIMIT;
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_PUBLISHED_COURSE_LIST_FOR_APP,
					new Object[] { isPublished, orgId, userId,
							ConstantUtil.MAX_LIMIT,  });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setPromoVideoUrl(row.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP
						+ ConstantUtil.ICON_PATH + row.getRowItem(5));

				String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
				String newTag = "";
				for (int i = 0; i < tag.length; i++) {
					newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i])
							: (newTag + "#" + tag[i] + ", "));
				}
				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(8));
				course.setLanguageName(row.getRowItem(9));
				course.setSessions(Integer.parseInt(row.getRowItem(10)));
				Map<String, String> maph = new HashMap<String, String>();
				if (row.getRowItem(3) != null) {
					String highlights = row.getRowItem(3);
					for (int i = 0; i < highlights.split("####").length; i++) {
						maph.put("Highlight" + (i + 1),
								highlights.split("####")[i]);
					}
					course.setCourseHighlight(maph);
				} else {
					course.setCourseHighlight(maph);
				}
				course.setStatus(row.getRowItem(7));
				course.setIsEnroll(row.getRowItem(11) != null ? Integer
						.parseInt(row.getRowItem(11)) : 0);
				course.setTeacherName(row.getRowItem(12));
				String info = "";
				QueryData cont = QueryManager.execuateQuery(
						QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE,
						new Object[] { course.getCourseId(), isPublished });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " "
							+ rowCont.getRowItem(1) + " ";
				}
				course.setCourseInfo(info.length() > 0 ? info : "0 Content");
				courseList.add(course);

			}
			if (data.getRows().size() > 0) {
				map.put("status", 200);
				map.put("msg", "Course List");
				map.put("courselist", courseList);
			} else {
				map.put("status", 201);
				map.put("msg", "No data available");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getPublishedCourseList method::::::",
					e);
		}

		return map;
	}

	/**
	 * <p>
	 * This is used for getting list of all upcoming courses based on paging.
	 * </p>
	 * 
	 * @param isSchedule
	 *            This is first parameter which is used for getting published
	 *            course's list.
	 * @param offset
	 *            This is second parameter
	 * @return List This returns a Course type List which contains information
	 *         about course's.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public Map<String, Object> getUpcomingCourseList(Integer userId,
			int isSchedule, int offset) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getUpcomingCourseList method::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courseList = new ArrayList<Course>();
		Integer orgId = UserService.getOrgId(userId);
		try {
			int skip = offset * ConstantUtil.LIMIT;
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_UPCOMING_COURSE_LIST_FOR_APP,
					new Object[] { isSchedule, orgId, userId,
							ConstantUtil.LIMIT, skip });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setPromoVideoUrl(row.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP
						+ ConstantUtil.ICON_PATH + row.getRowItem(5));

				String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
				String newTag = "";
				for (int i = 0; i < tag.length; i++) {
					newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i])
							: (newTag + "#" + tag[i] + ", "));
				}
				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(8));
				course.setLanguageName(row.getRowItem(9));
				course.setSessions(Integer.parseInt(row.getRowItem(10)));
				Map<String, String> maph = new HashMap<String, String>();
				if (row.getRowItem(3) != null) {
					String highlights = row.getRowItem(3);
					for (int i = 0; i < highlights.split("####").length; i++) {
						maph.put("Highlight" + (i + 1),
								highlights.split("####")[i]);
					}
					course.setCourseHighlight(maph);
				} else {
					course.setCourseHighlight(maph);
				}
				course.setStatus(row.getRowItem(7));
				course.setIsEnroll(row.getRowItem(11) != null ? Integer
						.parseInt(row.getRowItem(11)) : 0);
				course.setSchedulePublishDate(row.getRowItem(12));
				course.setTeacherName(row.getRowItem(13));
				String info = "";
				QueryData cont = QueryManager.execuateQuery(
						QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE,
						new Object[] { course.getCourseId(), isSchedule });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " "
							+ rowCont.getRowItem(1) + " ";
				}
				course.setCourseInfo(info.length() > 0 ? info : "0 Content");
				courseList.add(course);

			}
			if (data.getRows().size() > 0) {
				map.put("status", 200);
				map.put("msg", "Course List");
				map.put("courselist", courseList);
			} else {
				map.put("status", 201);
				map.put("msg", "No data available");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getUpcomingCourseList method::::::",
					e);
		}

		return map;
	}

	/**
	 * <p>
	 * This is for counting the all published course of all user.
	 * </p>
	 *
	 * @param isPublished
	 *            This is second parameter for getting published course details.
	 * @return int This returns total number of published courses.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public int countPublishedCourses(Integer userId, Integer orgId,
			int isPublished) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in countPublishedCourses method::::::");
		int totalCourse = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.TOTAL_NUMBER_OF_PUBLISHED_COURSE,
					new Object[] { isPublished, userId, orgId });
			for (QueryData.Row row : data.getRows()) {
				totalCourse = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in countPublishedCourses method::::::",
					e);
		}
		return totalCourse;
	}

	/**
	 * <p>
	 * This is for counting the all Upcoming course of all user.
	 * </p>
	 *
	 * @param isSchedule
	 *            This is second parameter for getting Upcoming course details.
	 * @return int This returns total number of Upcoming courses.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public int countUpcomingCourses(Integer userId, Integer orgId,
			int isSchedule) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in countUpcomingCourses method::::::");
		int totalCourse = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.TOTAL_NUMBER_OF_UPCOMING_COURSE, new Object[] {
							isSchedule, userId, orgId });
			for (QueryData.Row row : data.getRows()) {
				totalCourse = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in countUpcomingCourses method::::::",
					e);
		}
		return totalCourse;
	}

	/**
	 * <p>
	 * This is used getting a particular course details based on course id.
	 * </p>
	 * 
	 * @param courseId
	 *            This is first parameter for getting course details based on
	 *            course Id.
	 * @param isPublished
	 *            This is second parameter for getting published course details.
	 * @return Course This returns A Course Object which has all information
	 *         about course.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public Course getCourseDetail(int courseId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getCourseDetail method::::::");
		List<Section> sectionList=new ArrayList<Section>();
		Course course = new Course();
		Integer isPublished = Integer.parseInt(CourseEnum.PUBLISHED.getValue());
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_PUBLISHED_COURSE_DETAIL, new Object[] {
							courseId, isPublished });
			for (QueryData.Row row : data.getRows()) {
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				String highlights = row.getRowItem(3) == null ? "NA####" : row
						.getRowItem(3);
				course.setHighlights(highlights.split("####"));
				course.setPromoVideoUrl(row.getRowItem(4) == null
						|| row.getRowItem(4).equals("") ? "NA" : row
						.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP
						+ ConstantUtil.ICON_PATH + row.getRowItem(5));
				String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
				String newTag = "";
				for (int i = 0; i < tag.length; i++) {
					newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i])
							: (newTag + "#" + tag[i] + ", "));
				}
				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(7));
				course.setLanguageName(row.getRowItem(8));
				course.setCourseModifyDate(row.getRowItem(9));
				course.setCourseModifyTime(row.getRowItem(10));
				course.setActive(Integer.parseInt(row.getRowItem(13)));
				course.setPublish(Integer.parseInt(row.getRowItem(14)));
				course.setIsEnroll(row.getRowItem(15) != null ? Integer
						.parseInt(row.getRowItem(15)) : 0);
				course.setCourseType(Integer.parseInt(row.getRowItem(16)));
				
			}
			String info = "";
			QueryData cont = QueryManager.execuateQuery(
					QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE, new Object[] {
							courseId, isPublished });
			for (QueryData.Row rowCont : cont.getRows()) {
				info = info + rowCont.getRowItem(0) + " "
						+ rowCont.getRowItem(1) + " ";
			}
			course.setCourseInfo(info.length() > 0 ? info : "0 Content");
			
			Section section=getSectionContentList(courseId);
			sectionList.add(section);
			course.setSectionList(sectionList);
             
             
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getCourseDetail method::::::",
					e);
		}
		return course;
	}

	public   Section  getSectionContentList(int courseId )
	{
		Section section=new Section();
		
		List<SectionContent> contents=new ArrayList<SectionContent>();
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_CONTENT_SECTION_LIST, new Object[] {
						courseId});
		for (QueryData.Row row : data.getRows()) {
			SectionContent sectionContent=new SectionContent();
			section.setSectionName(row.getRowItem(0));
			sectionContent.setContentId(Integer.parseInt(row.getRowItem(1)));
			sectionContent.setContentName(row.getRowItem(2));
			sectionContent.setContentType(row.getRowItem(3));
			if(row.getRowItem(4)==null)
			{
				
				sectionContent.setAttemptId(0);
			}
				else
			{
				sectionContent.setAttemptId(Integer.parseInt(row.getRowItem(4)));
			}
			contents.add(sectionContent);
			section.setContents(contents);
		}
		return section;
	}
	
	
	
	public Map<String, Object> getAttemptDetailsForApi(Integer courseId){
		Course course=getCourseDetail(courseId);
		int sessionIndex=1;
        int exerciseIndex=1;
	     Object sectionContent[]=course.getSectionList().stream().map(m->m.getContents()).findFirst().get().toArray();
	     Map<String,Object> map = new LinkedHashMap<>();
	     map.put("Course Name", course.getCourseName());
	   
	     for(int i=0;i<sectionContent.length;i++)
	     {
	    	 
	    	 SectionContent sec=(SectionContent)sectionContent[i];
	    	 if(sec.getAttemptId()==0 || sec.getAttemptId()==1)
	    	 {
	    	if(sec.getContentType().equals("VIDEO")) 
	    	{
	    		map.put("Session"+(sessionIndex),sec.getContentName());
	    		sessionIndex++;
	    	}
	    	if(sec.getContentType().equals("TEST")) 
	    	{
	    		map.put("exercise"+(exerciseIndex),sec.getContentName());
	    		exerciseIndex++;
	    	}
	     }
	     }
	     
	     return map;
		
	}
	
	public Course getSectionDetails(Integer courseId)
	{
	logger.log(Level.DEBUG,
	"Inside Student Course Service in getSectionDetails method::::::");

	Map< String, Object> sectionMap= new HashMap<String, Object>();
	List<Section> sections=new ArrayList<Section>();
	Course course=new Course();
	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_COURSE_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {
	List<Session> sessions=new ArrayList<Session>(); 
	
	course.setCourseId(Integer.parseInt(row.getRowItem(0)));
	course.setCourseName(row.getRowItem(1));
	course.setCourseDesc(row.getRowItem(2));
	course.setPromoVideoUrl(row.getRowItem(3));
	course.setSectionList(getSectionDetailsForApi(courseId));
	System.out.println("course====="+course.getCourseName());
	sectionMap.put("CourseDetail", course);
	/*
	* course.setCourseDesc(row.getRowItem(10)); Section section= new Section();
	* Session session=new Session();
	* section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	* section.setSectionName(row.getRowItem(1));
	* session.setSessonId(Integer.parseInt(row.getRowItem(2)));
	* session.setSessionName(row.getRowItem(3));
	* session.setIsLive(Integer.parseInt(row.getRowItem(4)));
	* session.setIsEnable(Integer.parseInt(row.getRowItem(5)));
	* session.setIsFree(Integer.parseInt(row.getRowItem(6)));
	* session.setIsChapterTest(Integer.parseInt(row.getRowItem(7)));
	* session.setAttempts(getAttemptDetails(session.getSessonId()));
	* session.setVideContent(getSessionVideo(session.getSessonId()));
	* sessions.add(session); section.setSession(sessions); sections.add(section);
	* course.setSectionList(sections); sectionMap.put("CourseDetails", course);
	*/

	}
	}

	catch (Exception e) {
	// TODO: handle exception
	}
	return course;
	}
	
	public List<Section> getSectionDetailsForApi(Integer courseId)
	{
	List<Section> sections = new ArrayList<Section>();

	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_SECTION_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {

	Section section=new Section();
	section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	section.setSectionName(row.getRowItem(1));
	section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	section.setParentId(Integer.parseInt(row.getRowItem(0)));
	section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	if(section.getIsPractice()!=1)
	{
	section.setSession(getSessionDetailsForApi(section.getSectionId()));
	}
	sections.add(section);

	}
	}
	catch (Exception e) {
	// TODO: handle exception
	}
	return sections;
	}
	
	public List<Session> getSessionDetailsForApi(Integer sectionId)
	{
	List<Session> sessions=new ArrayList<Session>();

	try {
	QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_DETAILS,new Object[] {
	sectionId
	});

	for (QueryData.Row row : data.getRows()) {
	Session session=new Session();
	session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	session.setSessionName(row.getRowItem(1));
	session.setCreatedDate(row.getRowItem(2));
	session.setIsLive(Integer.parseInt(row.getRowItem(3)));
	session.setIsChapterTest(Integer.parseInt(row.getRowItem(3)));
	session.setIsEnable(Integer.parseInt(row.getRowItem(4)));
	session.setIsFree(Integer.parseInt(row.getRowItem(5)));
	                   
	session.setAttempts(getAttemptsForAPI(session.getSessonId()));
	session.setVideContent(getSessionVideo(session.getSessonId()));
	sessions.add(session);
	}
	}
	catch (Exception e) {
	// TODO: handle exception
	}

	return sessions;

	}
	
	public List<Attempts> getAttemptsForAPI(Integer sessionId)
	{
	List<Attempts>attempts=new ArrayList<Attempts>();
	try { QueryData data=QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_DETAILS, new Object[] {sessionId});
	for (QueryData.Row row : data.getRows()) {
	Attempts attempt=new Attempts();
	attempt.setId(Integer.parseInt(row.getRowItem(0)));
	attempt.setQuizTitle(row.getRowItem(1));
	attempt.setContentId(Integer.parseInt(row.getRowItem(2)));
	attempt.setSessionId(Integer.parseInt(row.getRowItem(3)));
	attempts.add(attempt);

	}

	}

	catch (Exception e) {
	// TODO: handle exception
	}
	return attempts;
	}
	
	
	
	public String getSessionVideo(Integer sessionId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getSessionVideo method::::::");
		String video=null;
		
		try
		{
			List<SectionContent> contents=new ArrayList<SectionContent>();
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_VIDEO_LIST_FROM_SESSION, new Object[] {
							sessionId});
			for (QueryData.Row row : data.getRows()) {
				video=row.getRowItem(0);
			}
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return video;
	
	}
	
	public List<Attempts> getAttemptDetails(Integer sessionId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getAttemptDetails method::::::");
		
		
		List<Attempts> attempts = new ArrayList<Attempts>();
		Attempts attempt=new Attempts();
		SectionContent content=new SectionContent();
		
		try
		{
			List<SectionContent> contents=new ArrayList<SectionContent>();
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_ATTEMPT_CONTENT_SESSION_DETAILS, new Object[] {
							sessionId});
			for (QueryData.Row row : data.getRows()) {
				
				attempt.setContents(contents);
				attempt.setId(Integer.parseInt(row.getRowItem(5)));
				if(attempt.getId()==null)
				{
					System.out.println("heyyy");
				}
				content.setContentId(Integer.parseInt(row.getRowItem(1)));
				content.setContentName(row.getRowItem(3));
				content.setContentType(row.getRowItem(4));
				contents.add(content);
				attempt.setContents(contents);
				attempts.add(attempt);
				System.out.println(row.getRowItem(5));
				
			}
				
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return attempts;
	}
	
	/**
	 * <p>
	 * Method for enable enrollment of course by user
	 * </p>
	 * 
	 * @param course
	 *            , object contains userId,courseId
	 * @return map
	 */
	public Map<String, Object> enableEnrollment(Course course) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in enableEnrollment method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Integer id = QueryManager.execuateUpdate(
					QueryStrings.SAVE_ENROLLMENT_IN_COURSE_ASSESSMENT,
					new Object[] { course.getCourseId(), course.getUserId(),
							CourseEnum.ENROLLED.getValue(), new Date() });
			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation Failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in enableEnrollment method::::::",
					e);
		}
		return map;
	}

	/**
	 * <p>
	 * Method for save Recently viewed course by user
	 * </p>
	 * 
	 * @param userId
	 * @param courseId
	 */
	public void saveRecentViewCourse(Integer userId, Integer courseId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveRecentViewCourse method::::::");
		try {
			QueryManager.execuateUpdate(QueryStrings.SAVE_RECENT_VIEW_COURSES,
					new Object[] { userId, courseId });
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveRecentViewCourse method::::::",
					e);
		}

	}

	/**
	 * This is used for sending mail to user for download the shared content.
	 * 
	 * @param email
	 * @param path
	 * @return Boolean
	 */
	public Boolean shareContentLinkToUser(final String email, final String path) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in shareContentLinkToUser method::::::");
		Boolean status = false;
		try {
			(new Thread() {
				@Override
				public void run() {
					SimpleMail smail = new SimpleMail();
					final String content = "<p>Dear User,</p><br/>"
							+ "<p>Course Cotent has been shared to you.</p>"
							+ "<p>View <a href='"
							+ path
							+ "' style='text_decoration:true'>here</a></p><br/><br/>"
							+ "<p>Regards,</p>" + "<p>QBis Team</p>";
					smail.sendEmail("info@qbis.com", email, "Course Content",
							content);
				}
			}).start();
			status = true;
		} catch (Exception e) {
			status = false;
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in shareContentLinkToUser method::::::",
					e);
		}
		return status;
	}

	/**
	 * This is used set and reset favorite Content.
	 * 
	 * @param content
	 * @return Map
	 */
	public Map<String, Object> setResetFavoriateContent(SectionContent content) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in setResetFavoriateContent method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		int favoriateId = 0;
		try {
			if (content.getFavoriateId() == 0) {
				if (content.getFavorites() == 1) {
					id = QueryManager
							.execuateUpdate(
									QueryStrings.SAVE_CONTENT_FAVORIATE_BY_USER,
									new Object[] { content.getContentId(),
											content.getUserId(),
											content.getFavorites() });
					favoriateId = id;
				} else {
					id = QueryManager.execuateUpdate(
							QueryStrings.UPDATE_CONTENT_FAVORIATE_BY_USER,
							new Object[] { content.getFavorites(),
									content.getContentId(),
									content.getUserId(), });
					favoriateId = getContentFavoriateId(content.getContentId(),
							content.getUserId(), content.getFavorites());
				}
			} else {
				id = QueryManager.execuateUpdate(
						QueryStrings.UPDATE_CONTENT_FAVORIATE_BY_USER,
						new Object[] { content.getFavorites(),
								content.getContentId(), content.getUserId(), });
				favoriateId = content.getFavoriateId();
			}

			if (id > 0) {
				map.put("status", 200);
				map.put("flag", content.getFavorites());
				map.put("favoriateId", favoriateId);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation Failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in setResetFavoriateContent method::::::",
					e);
		}
		return map;
	}

	/**
	 * <p>
	 * method for get favoriateId base on userId and contentId,favorites
	 * </p>
	 * 
	 * @param contentId
	 *            ,userId,favorites
	 * @return favoriateId
	 */
	private int getContentFavoriateId(Integer contentId, Integer userId,
			int favorites) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getContentFavoriateId method::::::");
		int favid = 0;
		try {

			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_CONTENT_FAVORIATEID, new Object[] {
							contentId, userId, favorites });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					favid = Integer.parseInt(row.getRowItem(0));
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getContentFavoriateId method::::::",
					e);
		}
		return favid;
	}

	/**
	 * <p>
	 * Service for get favorite contents list by user
	 * </p>
	 * 
	 * @param userId
	 * @return map
	 */
	public Map<String, Object> getFavoriteContentsList(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getFavoriteContentsList method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<SectionContent> list = new ArrayList<SectionContent>();
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.LIST_FAVORITE_CONTENT_BY_USERID,
					new Object[] { userId });
			SectionContent que = null;
			for (QueryData.Row row : data.getRows()) {
				que = new SectionContent();
				que.setContentName(row.getRowItem(0));
				que.setContent(row.getRowItem(1));
				que.setContentType(row.getRowItem(2));
				if (que.getContentType().equals("TEST")) {
					int testid = Integer.parseInt(row.getRowItem(1));
					que.setContentURL(ConstantUtil.SERVER_IP
							+ ConstantUtil.PROJECT_NAME
							+ "testpreview?action=frame&testId=" + testid);

				} else {
					que.setContentURL(ConstantUtil.SERVER_IP
							+ ConstantUtil.COURSE_FILE_DIRECTORY
							+ row.getRowItem(1));
				}
				list.add(que);
			}
			map.put("favcontentlist", list);
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getFavoriteContentsList method::::::",
					e);
		}
		return map;
	}

	/**
	 * This is used save abuse report for content.
	 * 
	 * @param content
	 * @return
	 */
	public Map<String, Object> saveAbuseReportonContent(SectionContent content) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveAbuseReportonContent method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			if (content.getReportId() == 0) {
				id = QueryManager.execuateUpdate(
						QueryStrings.SAVE_CONTENT_ABUSETEXT_BY_USER,
						new Object[] { content.getAbuseReport(),
								content.getContentId(), content.getUserId(),
								content.getCourseId() });
				if (id != null) {
					content.setReportId(id);
					NotificationManagement
							.abuseReportNotificationToTeacher(content);
				}
			} else {
				id = QueryManager.execuateUpdate(
						QueryStrings.UPDATE_CONTENT_ABUSETEXT_BY_USER,
						new Object[] { content.getAbuseReport(),
								content.getReportId() });
			}

			if (id > 0) {
				map.put("status", 200);
				map.put("reportId", id);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation Failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveAbuseReportonContent method::::::",
					e);
		}
		return map;
	}

	/**
	 * <p>
	 * This is for counting the all published course of all user.
	 * </p>
	 *
	 * @param isPublished
	 *            This is second parameter for getting published course details.
	 * @return int This returns total number of published courses.
	 * @exception IOException
	 *                On input error.
	 * @exception NullPointerException
	 */
	public int countPublishedTestByUserId(int isPublished, int userId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in countPublishedTestByUserId method::::::");
		int totalCourse = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.TOTAL_NUMBER_OF_PUBLISHED_COURSE_BY_USERID,
					new Object[] { isPublished, userId });
			for (QueryData.Row row : data.getRows()) {
				totalCourse = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in countPublishedTestByUserId method::::::",
					e);
		}
		return totalCourse;
	}

	public boolean getCourseSubscribe(Integer userId,List<Integer> courseIdList)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getCourseSubscribe method::::::");
	    boolean flag=false; 
	    int courseId =0;
	    List<Integer> listCourseId=new ArrayList<>();
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_COURSE_SUBSCRIBE,
				new Object[] {  userId, courseIdList.get(0),courseIdList.get(1)});
		for (QueryData.Row row : data.getRows()) {
			courseId = Integer.parseInt(row.getRowItem(0));
			listCourseId.add(courseId);
		}		
		if(listCourseId.size()==courseIdList.size())
		{
			flag=true;
		}
		System.out.println("flag=========="+flag);
		return flag;
	}
	/**
	 * This method is used for saving the attempted question data inside course
	 * content.
	 * 
	 * @param userId
	 * @param sectionId
	 * @param questionId
	 * @param attemptedAnswer
	 * @param attemptCounter
	 * @return attemptCount
	 */
	public Integer saveAttemptContentQuestion(Integer userId,
			Integer sectionId, Integer questionId, String attemptedAnswer,
			Integer attemptCounter) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveAttemptContentQuestion method::::::");
		Integer attemptCount = 1;
		try {
			if (attemptCounter == null) {
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_ATTEMPT_COUNT, new Object[] { userId,
								sectionId, questionId });
				for (QueryData.Row row : data.getRows()) {
					if (row.getRowItem(0) != null) {
						attemptCount = Integer.parseInt(row.getRowItem(0)) + 1;
					}
				}
			} else {
				attemptCount = attemptCounter;
			}
			QueryManager.execuateUpdate(QueryStrings.SAVE_VIDEO_CONTENT_ANSWER,
					new Object[] { userId, sectionId, questionId,
							attemptedAnswer, attemptCount });
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveAttemptContentQuestion method::::::",
					e);
		}
		return attemptCount;
	}

	/**
	 * <p>
	 * Method for save view content activity of user
	 * </p>
	 * 
	 * @param content
	 *            object contains userId,contentId,courseId
	 * @return map
	 */
	public Map<String, Object> saveViewContentActivity(
			ContentActivity contentActivity) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveViewContentActivity method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {

			id = QueryManager.execuateUpdate(
					QueryStrings.SAVE_ACTIVITY_CONTENT_VIEW_BY_STUDENT,
					new Object[] { contentActivity.getUserId(),
							contentActivity.getContentId(),
							contentActivity.getSectionId(),
							contentActivity.getCourseId(),
							contentActivity.getCourseActivityId() });

			if (id > 0) {
				map.put("status", 200);
				map.put("contentActivityId", id);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Data Not Saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveViewContentActivity method::::::",
					e);
		}
		return map;
	}

	public Integer getCompleteStatus(ContentActivity contentActivity,Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Course Service in getCompleteStatus method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer status = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_STATUS_COMPLETE,
					new Object[] { userId, contentActivity.getSectionId(),contentActivity.getCourseId(),contentActivity.getSessionId()});
			for (QueryData.Row row : data.getRows()) {
				if (row.getRowItem(0) != null) {
					status = Integer.parseInt(row.getRowItem(0));
				}
			} 
		}
		catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Course Service in saveViewContentActivity method::::::",
					e);
		}
		return status;
	}
	public boolean updateVideoStatus(ContentActivity contentActivity,Integer userId)
	{
		logger.log(Level.DEBUG, "Inside Student Course Service in updateVideoStatus method::::::");
		System.out.println("update activity_user_course_content set is_resume=0,resume_time=0:0  where user_id="+userId+" AND section_id="+contentActivity.getSectionId()+" AND course_id="+contentActivity.getCourseId()+"  AND session_id="+contentActivity.getSessionId()+"");
		int id = QueryManager.execuateUpdate(
				QueryStrings.UPDATE_VIDEO_STATUS,
				new Object[] {0,"0:0",userId, contentActivity.getSectionId(),contentActivity.getCourseId(),contentActivity.getSessionId() });	
	     if(id>0)
	     {
	    	 return true;
	     }
	     return false;
	}
	public boolean updateVideoStatus1(ContentActivity contentActivity,Integer userId)
	{
		logger.log(Level.DEBUG, "Inside Student Course Service in updateVideoStatus1 method::::::"+userId);
		try
		{
		int id = QueryManager.execuateUpdate(
				QueryStrings.UPDATE_VIDEO_STATUS,
				new Object[] {1,contentActivity.getResumeTime(),userId, contentActivity.getSectionId(),contentActivity.getCourseId(),contentActivity.getSessionId() });	
	     if(id>0)
	     {
	    	 System.out.println("=========================="+id);
	    	 return true;
	     }
		 System.out.println("bhar=========================="+id);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return false;
	}
    
	
	public boolean getStudentSubscriptionStatus(Integer courseId, Integer userId) {
		int course = 0;
		boolean flag = false;
		try {

			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_SUBSCRIPTION_STATUS,
					new Object[] {  courseId,userId });
			for (QueryData.Row row : data.getRows()) {
				course = Integer.parseInt(row.getRowItem(0));
				String product_id = row.getRowItem(1);
				String user_Id = row.getRowItem(2);
			}
			if (course != 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
	public Map<String, Object> saveViewContentActivity1(ContentActivity contentActivity,Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Course Service in saveViewContentActivity1 method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			
			  id = QueryManager.execuateUpdate(
			  QueryStrings.SAVE_ACTIVITY_CONTENT_VIEW_BY_STUDENT1, new Object[] {
					  
					  userId, contentActivity.getContentId(),
			  contentActivity.getSectionId(), contentActivity.getCourseId(),contentActivity.getStartTime(),contentActivity.getSessionId(),contentActivity.getAttemptId(),1,
			  contentActivity.getSpentTime(),contentActivity.getIsMobile()
			  });
			  
			  if (id > 0)
			  {
	
				  map.put("status", 200); map.put("contentActivityId", id);
			  map.put("msg", "Success"); } 
			  else 
			  {
				  map.put("status", 401);
				  map.put("msg", "Data Not Saved"); }
			  } catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Course Service in saveViewContentActivity method::::::",
					e);
		}
		return map;
	}

	
	
	/**
	 * Method for updating end view date of content activity of user
	 * 
	 * @param content
	 * @return map
	 */
	public Map<String, Object> updateCourseContentViewEndDate(
			ContentActivity contentActivity) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveViewContentActivity method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.UPDATE_COURSE_CONTENT_END_DATETIME,
					new Object[] { contentActivity.getContentActivityId() });

			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Data Not Saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveViewContentActivity method::::::",
					e);
		}
		return map;
	}

	/**
	 * Method for updating the test attempted if for content activity of user
	 * 
	 * @param content
	 * @return map
	 */
	public Map<String, Object> updateAttemptedTestIdInContentActivity(
			ContentActivity contentActivity) {
		logger.log(
				Level.DEBUG,
				"Inside Student Course Service in updateAttemptedTestIdInContentActivity method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.UPDATE_TEST_ATTEMPTED_ID_IN_CONTENT_ACTIVITY,
					new Object[] { contentActivity.getAttemptedTestId(),
							contentActivity.getContentActivityId() });

			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Data Not Saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in updateAttemptedTestIdInContentActivity method::::::",
					e);
		}
		return map;
	}

	/**
	 * This is used update the course view end date based on course activity id.
	 * 
	 * @param courseActivity
	 * @return
	 */
	public Map<String, Object> updateCourseViewEndDate(
			CourseActivity courseActivity) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveViewContentActivity method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.UPDATE_COURSE_END_DATETIME,
					new Object[] { courseActivity.getCourseActivityId() });

			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Data Not Saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveViewContentActivity method::::::",
					e);
		}
		return map;
	}

	/**
	 * <p>
	 * Method for save view course activity of user
	 * </p>
	 * 
	 * @param userId
	 * @param courseId
	 * @return auto generated activity Id
	 */
	public int saveCourseViewDetailsByUser(Integer userId, int courseId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in saveCourseViewDetailsByUser method::::::");
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.SAVE_ACTIVITY_COURSE_VIEW_BY_TRAINEE,
					new Object[] { courseId, userId });

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in saveCourseViewDetailsByUser method::::::",
					e);
		}

		return id;
	}

	/**
	 * <p>
	 * Method for save end time of course view by user
	 * </p>
	 * 
	 * @param vcId
	 *            auto generated activity Id
	 * @return map
	 */
	public Map<String, Object> editCourseViewDetailsByUser(Integer vcId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in editCourseViewDetailsByUser method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.EDIT_ACTIVITY_COURSE_VIEW_BY_TRAINEE,
					new Object[] { vcId });
			map.put("id", id);
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in editCourseViewDetailsByUser method::::::",
					e);
		}

		return map;
	}

	/**
	 * This is used getting course attempt details.
	 * 
	 * @param userId
	 * @param courseId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getCourseAttemptDetails(Integer userId,
			Integer courseId) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getCourseAttemptDetails method::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<CourseActivity> courseActivityList = new ArrayList<CourseActivity>();
			QueryData data = QueryManager
					.execuateQuery(
							QueryStrings.GET_COURSE_ACTIVITY_LIST_BASED_ON_USERID_COURSEID,
							new Object[] { courseId, userId });
			for (QueryData.Row row : data.getRows()) {
				CourseActivity activity = new CourseActivity();
				activity.setCourseActivityId(Integer.parseInt(row.getRowItem(0)));
				activity.setCourseId(Integer.parseInt(row.getRowItem(1)));
				activity.setStartTime(row.getRowItem(2));
				activity.setEndTime(row.getRowItem(3));
				activity.setSpentTime(row.getRowItem(4) != null ? Integer
						.parseInt(row.getRowItem(4)) : null);
				courseActivityList.add(activity);
			}
			map.put("courseActivityList", courseActivityList);
			map.put("status", 200);
			map.put("msg", "Success");
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getCourseAttemptDetails method::::::",
					e);
		}
		return map;
	}

	/**
	 * This is used getting details of passed attempt of user if there is any
	 * quiz in this section.
	 * 
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @return map
	 */
	public Map<String, Object> sectionValidationforQuizManadatory(
			Integer userId, Integer courseId, Integer sectionId) {
		logger.log(
				Level.DEBUG,
				"Inside Student Course Service in sectionValidationforQuizManadatory method::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean isPassedTheCriteria = false;
			Integer totalNumberOfPassed = 0;
			QueryData contentData = QueryManager
					.execuateQuery(
							QueryStrings.GET_QUIZ_TYPE_CONTENT_LIST_IN_QUIZ_MANDATORY_SECTION,
							new Object[] { sectionId, courseId,
									CONTENT_TYPE_ID_FOR_QUIZ,
									QUIZ_MANDATORY_ENABLE });
			for (QueryData.Row row : contentData.getRows()) {
				if (row.getRowItem(0) != null && row.getRowItem(1) != null
						&& Integer.parseInt(row.getRowItem(1)) > 0) {
					QueryData data = QueryManager
							.execuateQuery(
									QueryStrings.GET_ATTEMPTED_TEST_DETAILS_BASED_ON_PASSING_CRITERIA_FOR_SECTION,
									new Object[] { row.getRowItem(0),
											sectionId, courseId,
											row.getRowItem(1) });
					if (data.getRows().size() > 0) {
						totalNumberOfPassed = totalNumberOfPassed + 1;
					}
				}
			}
			if (totalNumberOfPassed == contentData.getRows().size()) {
				isPassedTheCriteria = true;
			}
			map.put("isPassedTheCriteria", isPassedTheCriteria);
			map.put("sectionId", sectionId);
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in sectionValidationforQuizManadatory method::::::",
					e);
		}
		return map;
	}
	
	
	public List<Course> getPublishedCourseListeluminate(Integer userId,
			int isPublished) {
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getPublishedCourseList method::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courseList = new ArrayList<Course>();
		Integer orgId = UserService.getOrgId(userId);
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_PUBLISHED_COURSE_LIST_FOR_ELUMINATE,
					new Object[] { isPublished, orgId, userId,
							ConstantUtil.MAX_LIMIT,  });
			for (QueryData.Row row : data.getRows()) {
				
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setCourseDesc(row.getRowItem(2));
				course.setPromoVideoUrl(row.getRowItem(4));
				course.setCourseImageUrl(ConstantUtil.SERVER_IP
						+ ConstantUtil.ICON_PATH + row.getRowItem(5));

				String tag[] = row.getRowItem(6).replaceAll(" ", "").split(",");
				String newTag = "";
				for (int i = 0; i < tag.length; i++) {
					newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i])
							: (newTag + "#" + tag[i] + ", "));
				}
				course.setCourseTag(newTag);
				course.setLevelName(row.getRowItem(8));
				course.setLanguageName(row.getRowItem(9));
				course.setSessions(Integer.parseInt(row.getRowItem(10)));
				
				Map<String, String> maph = new HashMap<String, String>();
				if (row.getRowItem(3) != null) {
					String highlights = row.getRowItem(3);
					for (int i = 0; i < highlights.split("####").length; i++) {
						maph.put("Highlight" + (i + 1),
								highlights.split("####")[i]);
					}
					course.setCourseHighlight(maph);
				} else {
					course.setCourseHighlight(maph);
				}
				course.setStatus(row.getRowItem(7));
//				course.setIsEnroll(row.getRowItem(11) != null ? Integer
//						.parseInt(row.getRowItem(11)) : 0);
				course.setTeacherName(row.getRowItem(11));
				course.setCourseIcon(row.getRowItem(12));
				course.setCourseImageUrl(row.getRowItem(13));
				String info = "";
				QueryData cont = QueryManager.execuateQuery(
						QueryStrings.COUNT_TOTAL_CONTENT_OF_COURSE,
						new Object[] { course.getCourseId(), isPublished });
				for (QueryData.Row rowCont : cont.getRows()) {
					info = info + rowCont.getRowItem(0) + " "
							+ rowCont.getRowItem(1) + " ";
				}
				course.setCourseInfo(info.length() > 0 ? info : "0 Content");
				course.setIsKeepLearning(getLearningStatus(userId,course.getCourseId()));
 				courseList.add(course);

			}
			if (data.getRows().size() > 0) {
				map.put("status", 200);
				map.put("msg", "Course List");
				map.put("courselist", courseList);
			} else {
				map.put("status", 201);
				map.put("msg", "No data available");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside Student Course Service in getPublishedCourseList method::::::",
					e);
		}

		return courseList;
	}
 
	
	public Integer getLearningStatus(Integer userId, Integer courseId) {
		// TODO Auto-generated method stub
		boolean flag =false;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_LEARNING_STATUS,
					new Object[] { userId ,courseId});
			for(QueryData.Row row : data.getRows()) {
				if(Integer.parseInt(row.getRowItem(0))==1) {
					flag=true;
					break;
				}
			}
			if(flag==true)
				return 1;
			else
				return 0;
	}


	
	public List<Course> getPracticeTest(List<Course> courseList,Integer userId)
	{
		List<Course> courseAllList=new ArrayList<>();
		for (Iterator iterator = courseList.iterator(); iterator.hasNext();) {
			Course course1 = (Course) iterator.next();
			Course course=new Course();
			course.setCourseId(course1.getCourseId());
			course.setCourseName(course1.getCourseName());
			course.setCourseDesc(course1.getCourseDesc());
			course.setPromoVideoUrl(course1.getPromoVideoUrl());
			//course.setSectionList(getSectionDetailsForApi1(course.getCourseId(),userId));
			course.setSectionList(getPracticeSection(course.getCourseId(),userId,course));
			courseAllList.add(course);
			
		}
		return courseAllList;
	}
	
	public List<Section> getPracticeSection(Integer courseId,Integer userId,Course course)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getSectionDetailsForApi1 method::::::");
	List<Section> sections = new ArrayList<Section>();
  
	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_PRACTICE_SECTION_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {
 
	Section section=new Section();
	section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	section.setSectionName(row.getRowItem(1));
	section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	section.setParentId(row.getRowItem(3)!= null?Integer.parseInt(row.getRowItem(3)):null);
	section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	if(section.getIsPractice() == 1 && section.getIsChapterTest() == 0)
	{
		System.out.println("hiiiiiiiiiiii=======practice");
		section.setAttempts(getPracticeTest1(section.getSectionId(),courseId,userId,section,course));	
	}
	
	sections.add(section);

	}
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return sections;
	}
	
	
	
	
	public Map<String, Object> getSectionDetails1(Integer courseId,Integer userId)
	{
	logger.log(Level.DEBUG,
	"Inside Student Course Service in getSectionDetails method::::::");

	Map< String, Object> sectionMap= new HashMap<String, Object>();
	List<Section> sections=new ArrayList<Section>();

	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_COURSE_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {
	List<Session> sessions=new ArrayList<Session>(); 
	Course course=new Course();
	course.setCourseId(Integer.parseInt(row.getRowItem(0)));
	course.setCourseName(row.getRowItem(1));
	course.setCourseDesc(row.getRowItem(2));
	course.setPromoVideoUrl(row.getRowItem(3));
	course.setSectionList(getSectionDetailsForApi1(courseId,userId));
	System.out.println("course====="+course.getCourseName());
	sectionMap.put("CourseDetail", course);
	/*
	* course.setCourseDesc(row.getRowItem(10)); Section section= new Section();
	* Session session=new Session();
	* section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	* section.setSectionName(row.getRowItem(1));
	* session.setSessonId(Integer.parseInt(row.getRowItem(2)));
	* session.setSessionName(row.getRowItem(3));
	* session.setIsLive(Integer.parseInt(row.getRowItem(4)));
	* session.setIsEnable(Integer.parseInt(row.getRowItem(5)));
	* session.setIsFree(Integer.parseInt(row.getRowItem(6)));
	* session.setIsChapterTest(Integer.parseInt(row.getRowItem(7)));
	* session.setAttempts(getAttemptDetails(session.getSessonId()));
	* session.setVideContent(getSessionVideo(session.getSessonId()));
	* sessions.add(session); section.setSession(sessions); sections.add(section);
	* course.setSectionList(sections); sectionMap.put("CourseDetails", course);
	*/

	}
	}

	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return sectionMap;
	}
	
	public List<Section> getSectionDetailsForApi1(Integer courseId,Integer userId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getSectionDetailsForApi1 method::::::");
	List<Section> sections = new ArrayList<Section>();
  
	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_SECTION_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {
 
	Section section=new Section();
	section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	section.setSectionName(row.getRowItem(1));
	section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	section.setParentId(row.getRowItem(3)!= null?Integer.parseInt(row.getRowItem(3)):null);
	section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
//	if(section.getIsPractice()!=1)
//	{
	section.setSession(getSessionDetailsForApi1(section.getSectionId(),courseId,userId));
//	}
	if(section.getIsPractice() == 1 && section.getIsChapterTest() == 0)
	{
		System.out.println("hiiiiiiiiiiii=======practice");
		section.setAttempts(getPracticeTest(section.getSectionId(),courseId,userId));	
	}
	sections.add(section);

	}
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return sections;
	}
	
	
	public List<Attempts> getPracticeTest(Integer sectionId,Integer courseId,Integer userId)
	{
		logger.log(Level.DEBUG,"Inside Student Course Service in getPracticeTest method::::::");

		List<Attempts> attemptsList=new ArrayList<Attempts>();
	    try
	    {
		QueryData data=QueryManager.execuateQuery(QueryStrings.GET_PRACTICE_ATTEMPTS,new Object[] {
				sectionId });	
		for (QueryData.Row row : data.getRows()) {
		Boolean flag=false;
		Attempts attempts=new Attempts();
		attempts.setId(Integer.parseInt(row.getRowItem(0)));
		attempts.setContentId(Integer.parseInt(row.getRowItem(1)));
		attempts.setQuizTitle(row.getRowItem(2));
		attempts.setSessionId(Integer.parseInt(row.getRowItem(3)!=null?row.getRowItem(3):"0"));
		attempts.setTestId(Integer.parseInt(row.getRowItem(4)));
		ContentView  view=findAlreadyCourseContentViewDetails(userId, courseId, sectionId, 1,new Long(attempts.getSessionId()),attempts.getContentId());
		if(view !=null)
		{
	    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
		System.out.println("status flag"+flag);
		}
		attempts.setTotalQuestion(getTestDetail(attempts.getTestId()).getMaxQuestions());
		attempts.setTestMark(getTestDetail(attempts.getTestId()).getMaxMark());
		attempts.setTestStatus(flag);
		attemptsList.add(attempts);
		}
	    }
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return attemptsList;
		
	}
	public List<Attempts> getPracticeTest1(Integer sectionId,Integer courseId,Integer userId,Section section,Course course)
	{
		logger.log(Level.DEBUG,"Inside Student Course Service in getPracticeTest method::::::");

		List<Attempts> attemptsList=new ArrayList<Attempts>();
	    try
	    {
		QueryData data=QueryManager.execuateQuery(QueryStrings.GET_PRACTICE_ATTEMPTS,new Object[] {
				sectionId });	
		for (QueryData.Row row : data.getRows()) {
		Boolean flag=false;
		Attempts attempts=new Attempts();
		attempts.setCourseId(course.getCourseId()!=null?course.getCourseId():0 );
		attempts.setCourseName(course.getCourseName()!=null?course.getCourseName():"");
		attempts.setCourseDesc(course.getCourseDesc()!=null?course.getCourseDesc():"");
		attempts.setPromoVideoUrl(course.getPromoVideoUrl()!=null?course.getPromoVideoUrl():"");
		attempts.setSectionId(section.getSectionId()!=null?section.getSectionId():0);
		attempts.setSectionName(section.getSectionName()!=null?section.getSectionName():"");
		attempts.setIsChapterTest(section.getIsChapterTest()!=null?section.getIsChapterTest():0);
		attempts.setIspractice(section.getIsPractice()!=null?section.getIsPractice():0);
		attempts.setParentId(section.getParentId()!=null?section.getParentId():0);
		attempts.setId(Integer.parseInt(row.getRowItem(0)));
		attempts.setContentId(Integer.parseInt(row.getRowItem(1)));
		attempts.setQuizTitle(row.getRowItem(2));
		attempts.setSessionId(Integer.parseInt(row.getRowItem(3)!=null?row.getRowItem(3):"0"));
		attempts.setTestId(Integer.parseInt(row.getRowItem(4)));
		ContentView  view=findAlreadyCourseContentViewDetails(userId, courseId, sectionId, 1,new Long(attempts.getSessionId()),attempts.getContentId());
		if(view !=null)
		{
	    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
		System.out.println("status flag"+flag);
		}
		System.err.println("======="+getTestDetail(attempts.getTestId()).getMaxQuestions());
		attempts.setTotalQuestion(getTestDetail(attempts.getTestId()).getMaxQuestions());
		attempts.setTestMark(getTestDetail(attempts.getTestId()).getMaxMark());
		attempts.setTestStatus(flag);
		attemptsList.add(attempts);
		}
	    }
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return attemptsList;
		
	}
	
	
	 public Test getTestDetail(Integer testId)
	   {
		   logger.log(Level.DEBUG, "Inside Student Test Service getTestDetail method ::::::");
		   Test test =null;
			try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ONE_TEST_DETAIL1,
					new Object[] { testId });
			for (QueryData.Row row : data.getRows()) {
				test = new Test();
				test.setIsRandom(Integer.parseInt(row.getRowItem(0)));
				//test.setTestId(Integer.parseInt(row.getRowItem(0)));
				/*
				 * test.setTestName(row.getRowItem(1)); test.setTestDesc(row.getRowItem(2));
				 * test.setTestInstruct(row.getRowItem(3)); test.setTestTag(row.getRowItem(4));
				 * test.setMaxAttempts((row.getRowItem(5) != null ?
				 * Integer.parseInt(row.getRowItem(5)) : 0)); test.setNegMark((row.getRowItem(6)
				 * != null ? Integer.parseInt(row.getRowItem(6)) : 0));
				 * test.setTestTime(row.getRowItem(7));
				 * test.setTestAdaptive(Integer.parseInt(row.getRowItem(8)));
				 * test.setOrgName(row.getRowItem(9)); test.setTeacherName(row.getRowItem(10));
				 * test.setTestPause(Integer.parseInt(row.getRowItem(11)));
				 */
				if(test.getIsRandom()==0)
				{
				test.setMaxMark(Double.parseDouble(row.getRowItem(2)));
				test.setMaxQuestions(Integer.parseInt(row.getRowItem(4)));
				System.err.println("Normal max mark==========="+test.getMaxMark());
				System.err.println("Normal max ques==========="+test.getMaxQuestions());

				
				}
				else
				{
					test.setMaxMark(Double.parseDouble(row.getRowItem(1)));
					test.setMaxQuestions(Integer.parseInt(row.getRowItem(3)));
					System.err.println("Random max mark==========="+test.getMaxMark());
					System.err.println("Random max ques==========="+test.getMaxQuestions());
				}
				/*
				 * test.setTestPublishStatus(Integer.parseInt(row.getRowItem(17)));
				 * test.setIsPublic(Integer.parseInt(row.getRowItem(19)));
				 * test.setIsReview(Integer.parseInt(row.getRowItem(20)));
				 * test.setReviewWithCorrect(row.getRowItem(21) != null ?
				 * Integer.parseInt(row.getRowItem(21)) : null);
				 * 
				 * test.setTimeMinute(row.getRowItem(23) != null ?
				 * Integer.parseInt(row.getRowItem(23)) : null);
				 * test.setUserId(Integer.parseInt(row.getRowItem(24)));
				 */
			}
			}
			catch (Exception e) {
				// TODO: handle exception
			 e.printStackTrace();
			}
			return test;
			
	   }



	
	
	public List<Session> getSessionDetailsForApi1(Integer sectionId,Integer courseId,Integer userId)
	{
	List<Session> sessions=new ArrayList<Session>();

	try {
	QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_DETAILS,new Object[] {
	sectionId
	});

	for (QueryData.Row row : data.getRows()) {
		System.out.println("dsdsds");
	Session session=new Session();
	session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	session.setSessionName(row.getRowItem(1));
	session.setCreatedDate(row.getRowItem(2));
	session.setIsLive(Integer.parseInt(row.getRowItem(3)));
	session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	session.setIsEnable(Integer.parseInt(row.getRowItem(5)));
	session.setIsFree(Integer.parseInt(row.getRowItem(6)));
	session.setVideocompleted(getVideoCompletedStatus(courseId,sectionId,session.getSessonId(),userId));
	session.setVideoTime(getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getVideoTime()!=null?getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getVideoTime():"0:0");
	session.setIsResume(getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getIsResume()!= null?getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getIsResume():0);
	session.setAttempts(getAttemptsForAPI1(userId,courseId,sectionId,session.getSessonId()));
	session.setVideContent(getSessionVideo1(session.getSessonId()).getSessionName()!=null?getSessionVideo1(session.getSessonId()).getSessionName():"");
	session.setVideoContentId((getSessionVideo1(session.getSessonId()).getVideoContentId()!=null?getSessionVideo1(session.getSessonId()).getVideoContentId():0));
	session.setSessionVideoDuration((getSessionVideo1(session.getSessonId()).getSessionVideoDuration()!= null ?getSessionVideo1(session.getSessonId()).getSessionVideoDuration():"00:00"));
	session.setVideoUrl(row.getRowItem(7)!=null?row.getRowItem(7):"");
	sessions.add(session);
	}
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}

	return sessions;

	}
	
	public Integer getVideoCompletedStatus(Integer courseId, Integer sectionId,Integer sessionId, Integer userId) {
		
	logger.log(Level.DEBUG, "Inside Student Course Service videoCompleted method:::::::::::");
	Integer isStatus =0;
	System.out.println(courseId+"  "+sectionId+" "+sessionId+" "+userId);
	try
	{
		System.err.println("Inside vdo status");
     QueryData data = QueryManager.execuateQuery(QueryStrings.GET_VIDEO_STATUS,
			new Object[] { courseId, sectionId, sessionId,userId });
	for (QueryData.Row row : data.getRows()) {
		isStatus=Integer.parseInt(row.getRowItem(0));
		System.out.println("status=======" + isStatus);
		
	}
	}
	catch (Exception e) {
		// TODO: handle exception
	 e.printStackTrace();
	}
	System.out.println("IsStatus"+isStatus);
	return isStatus;
	}
	
	public Session getVideoCompletedStatus1(Integer courseId, Integer sectionId,Integer sessionId, Integer userId) {
		
		logger.log(Level.DEBUG, "Inside Student Course Service videoCompleted method:::::::::::");
		Integer isStatus =0;
		Session session=new Session();
		session.setVideoTime("0:0");
		session.setIsResume(0);
		System.out.println(courseId+"  "+sectionId+" "+sessionId+" "+userId);
		try
		{
		 	System.out.println("Inside vdo status");
	     QueryData data = QueryManager.execuateQuery(QueryStrings.GET_VIDEO_STATUS2,
				new Object[] { courseId, sectionId, sessionId,userId });
		for (QueryData.Row row : data.getRows()) {
			 session=new Session();
			session.setIsStatus(Integer.parseInt(row.getRowItem(0)));
			session.setIsResume(Integer.parseInt(row.getRowItem(1)!= null ?row.getRowItem(1):"0"));
			session.setVideoTime(row.getRowItem(2)!=null? row.getRowItem(2):"0:0");
			System.out.println("status=======" + isStatus);
			
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		 e.printStackTrace();
		}
		System.out.println("IsStatus"+isStatus);
		return session;
		}
	
	public List<Attempts> getAttemptsForAPI1(Integer userId,Integer courseId,Integer sectionId,Integer sessionId)
	{
		logger.log(Level.DEBUG, "Inside Student Course Service getAttemptsForAPI1 method:::::::::::");
		List<Attempts>attempts=new ArrayList<Attempts>();
	
	try { QueryData data=QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_DETAILS, new Object[] {sessionId});
	for (QueryData.Row row : data.getRows()) {
		boolean flag=false;
	Attempts attempt=new Attempts();
	attempt.setId(Integer.parseInt(row.getRowItem(0)));
	attempt.setQuizTitle(row.getRowItem(1));
	attempt.setContentId(Integer.parseInt(row.getRowItem(2)));
	attempt.setSessionId(Integer.parseInt(row.getRowItem(3)));
	attempt.setTestId(Integer.parseInt(row.getRowItem(4)));
	System.out.println("attemp id"+attempt.getId());
	System.out.println("attemp id"+attempt.getContentId());
	ContentView  view=findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attempt.getId(),new Long(sessionId),attempt.getContentId());
	if(view !=null)
	{
    flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
	System.out.println("status flag"+flag);
	}
	attempt.setTotalQuestion(getTestDetail(attempt.getTestId()).getMaxQuestions());
	attempt.setTestMark(getTestDetail(attempt.getTestId()).getMaxMark());
	attempt.setTestStatus(flag);
	attempts.add(attempt);

	}
	}

	catch (Exception e) {
		e.printStackTrace();
	// TODO: handle exception
	}
	return attempts;
	}
	
	private ContentView findAlreadyCourseContentViewDetails(Integer userId, Integer courseId, Integer sectionId,
			Integer attemptId, Long sessionId, Integer contentId) {
		logger.log(Level.DEBUG, "Inside Course Service findAlreadyCourseContentViewDetails method:::::::::::");
		
		ContentView view = null;
		try
		{
		QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_COURSE_CONTENT_VIEW_ACTIVITY,
				new Object[] { userId, courseId, sectionId, attemptId, sessionId, contentId });
		for (QueryData.Row row : data.getRows()) {
			view = new ContentView();
			view.setId(Integer.parseInt(row.getRowItem(0)));
			view.setTestAttemptId(row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : null);
			view.setView(Integer.parseInt(row.getRowItem(2)));
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return view;
	}
	




	
	
	public Session getSessionVideo1(Integer sessionId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getSessionVideo method::::::");
		Session session=new Session();
		try
		{
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_VIDEO_LIST_FROM_SESSION, new Object[] {
							sessionId});
			for (QueryData.Row row : data.getRows()) {
				session=new Session();
				 session.setSessionName(row.getRowItem(0));
				 session.setVideoContentId(Integer.parseInt(row.getRowItem(1)));
				 session.setSessionVideoDuration(row.getRowItem(2)!= null ? row.getRowItem(2):"00:00");
			}
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return session;
	
	}
	
	
	
	
	public List<UserSessionLisence> getUserSessionLisenceDetailsForAllSessions(Integer userId,Long sessionId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getUserSessionLisenceDetailsForAllSessions method::::::");
		Boolean status=false;
		List<UserSessionLisence> lisences=new ArrayList<UserSessionLisence>();
		Map<String, Object> map =new HashMap<String, Object>();
		
		try {
			QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_USER_LISENCE_DETAILS_FROM_ALL_SESSIONS, new Object[] {userId});
					for(QueryData.Row row :data.getRows()) {
					
						UserSessionLisence lisence=new UserSessionLisence();
						
						if(row.getRowItem(0)!=null)
						{
							status=true;
						lisence.setSessionId(Integer.parseInt(row.getRowItem(0)));
						lisence.setSessionName(row.getRowItem(1));
						lisence.setSectionId(Integer.parseInt(row.getRowItem(2)));
						lisence.setCourseId(Integer.parseInt(row.getRowItem(3)));
						lisence.setProductId(Integer.parseInt(row.getRowItem(4)));
						lisence.setUserSessionStatus(1);
						lisence.setUserId(userId);
						lisence.setMessage("Subscribed");
						System.out.println("============="+row.getRowItem(0));
						System.out.println("================="+lisence.getMessage());
						map.put("UserSessionLisence", lisence);
						lisences.add(lisence);
						}
					
						
					}
					if(status == false)
						
					{
						lisences.clear();
						map.clear();
					
					
			  QueryData data1=QueryManager.execuateQuery(QueryStrings.GET_FREE_CONTENTS_FROM_ALL_SESSIONS,
			  new Object[] {sessionId}); for(QueryData.Row row :data1.getRows()) {
				  UserSessionLisence lisence1=new UserSessionLisence();
			 System.out.println("Inside free contents");
			  lisence1.setSessionId(Integer.parseInt(row.getRowItem(0)));
			  lisence1.setSessionName(row.getRowItem(1));
			  lisence1.setSectionId(Integer.parseInt(row.getRowItem(2)));
			  lisence1.setCourseId(Integer.parseInt(row.getRowItem(3)));
			  lisence1.setUserSessionStatus(0); lisence1.setUserId(userId);
			  lisence1.setIsFree(Integer.parseInt(row.getRowItem(4)));
			  lisence1.setMessage("Allowed for free contents only");
			  map.put("UserSessionLisence", lisence1);
			  lisences.add(lisence1);}
			 
					}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return lisences;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<UserSessionLisence> getUserSessionLisenceDetails(Integer userId,Integer sessionId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getUserSessionLisenceDetails method::::::");
		Boolean status=false;
		List<UserSessionLisence> lisences=new ArrayList<UserSessionLisence>();
		Map<String, Object> map =new HashMap<String, Object>();
		
		try {
			QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_USER_LISENCE_DETAILS, new Object[] {sessionId,userId});
					for(QueryData.Row row :data.getRows()) {
					
						UserSessionLisence lisence=new UserSessionLisence();
						
						if(row.getRowItem(0)!=null)
						{
							status=true;
						lisence.setSessionId(Integer.parseInt(row.getRowItem(0)));
						lisence.setSessionName(row.getRowItem(1));
						lisence.setSectionId(Integer.parseInt(row.getRowItem(2)));
						lisence.setCourseId(Integer.parseInt(row.getRowItem(3)));
						lisence.setProductId(Integer.parseInt(row.getRowItem(4)));
						lisence.setUserSessionStatus(1);
						lisence.setUserId(userId);
						lisence.setMessage("Subscribed");
						System.out.println("============="+row.getRowItem(0));
						System.out.println("================="+lisence.getMessage());
						map.put("UserSessionLisence", lisence);
						lisences.add(lisence);
						}
					
						
					}
					if(status == false)
						
					{
						lisences.clear();
						map.clear();
					
					 UserSessionLisence lisence1=new UserSessionLisence();
			  QueryData data1=QueryManager.execuateQuery(QueryStrings.GET_FREE_CONTENTS,
			  new Object[] {sessionId}); for(QueryData.Row row :data1.getRows()) {
			  
			 System.out.println("Inside free contents");
			  lisence1.setSessionId(Integer.parseInt(row.getRowItem(0)));
			  lisence1.setSessionName(row.getRowItem(1));
			  lisence1.setSectionId(Integer.parseInt(row.getRowItem(2)));
			  lisence1.setCourseId(Integer.parseInt(row.getRowItem(3)));
			  lisence1.setUserSessionStatus(0); lisence1.setUserId(userId);
			  lisence1.setMessage("Allowed for free contents only");
			  map.put("UserSessionLisence", lisence1);
			  lisences.add(lisence1);}
			 
					}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return lisences;
	}
	
	
	public UserSessionLisence getUserSessionLisenceDetails1(Long sessionId,Integer userId)
	{
		logger.log(Level.DEBUG,
				"Inside Student Course Service in getUserSessionLisenceDetails method::::::");
		Boolean status=false;
		List<UserSessionLisence> lisences=new ArrayList<UserSessionLisence>();
		Map<String, Object> map =new HashMap<String, Object>();
		UserSessionLisence lisence=new UserSessionLisence();
		System.out.println("============UseId================"+userId);
		System.out.println("sessionId"+sessionId+"userid"+userId);
		try {
			QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_USER_LISENCE_DETAILS, new Object[] {sessionId,userId});
					for(QueryData.Row row :data.getRows()) {
					
					
						
						if(row.getRowItem(0)!=null)
						{
							status=true;
						lisence.setSessionId(Integer.parseInt(row.getRowItem(0)));
						lisence.setSessionName(row.getRowItem(1));
						lisence.setSectionId(Integer.parseInt(row.getRowItem(2)));
						//
						lisence.setCourseId(Integer.parseInt(row.getRowItem(3)));
						lisence.setProductId(Integer.parseInt(row.getRowItem(4)));
						lisence.setIsFree(Integer.parseInt(row.getRowItem(5)));
						System.out.println("isfree========"+Integer.parseInt(row.getRowItem(5)));
						lisence.setUserSessionStatus(1);
						System.out.println(lisence.getUserSessionStatus());
						lisence.setUserId(userId);
						
						}
					
						
					}
					if(status == false)
						
					{
						lisences.clear();
						map.clear();
					
					 UserSessionLisence lisence1=new UserSessionLisence();
			  QueryData data1=QueryManager.execuateQuery(QueryStrings.GET_FREE_CONTENTS,
			  new Object[] {sessionId}); for(QueryData.Row row :data1.getRows()) {
			  
              System.out.println("userId"+userId);
			  lisence.setSessionId(Integer.parseInt(row.getRowItem(0)));
			  lisence.setSessionName(row.getRowItem(1));
			  lisence.setSectionId(Integer.parseInt(row.getRowItem(2)));
			  lisence.setCourseId(Integer.parseInt(row.getRowItem(3)));
			  lisence.setIsFree(Integer.parseInt(row.getRowItem(4)));
			  lisence.setUserSessionStatus(0); 
			  lisence.setUserId(userId);
			  lisence.setMessage("Allowed for free contents only");
		
			  }
			 
					}
					System.out.println(lisence.getIsFree()+"==========");
					System.out.println(lisence.getUserSessionStatus());
					
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return lisence;
	}
	
	
	/*
	 * public Section getSectionDetailsForCourseChapterPage(Integer courseId) {
	 * logger.log(Level.DEBUG,
	 * "Inside Student Course Service in getSectionDetailsForCourseChapterPage method::::::"
	 * ); //List<Section> sections = new ArrayList<Section>(); Section section=new
	 * Section(); try { QueryData data = QueryManager.execuateQuery(
	 * QueryStrings.GET_SECTION_DETAILS, new Object[] { courseId});
	 * 
	 * for (QueryData.Row row : data.getRows()) {
	 * 
	 * 
	 * section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	 * section.setSectionName(row.getRowItem(1));
	 * section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	 * section.setParentId(Integer.parseInt(row.getRowItem(0)));
	 * section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	 * 
	 * 
	 * } } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * return section; }
	 */
	
	
	public Map<String, Object> getAllPublishedCourse(Integer userId)
	{
	logger.log(Level.DEBUG,
	"Inside Student Course Service in getSectionDetails method::::::");

	Map< String, Object> courseMap= new LinkedHashMap<String, Object>();
	List<Course> courseList=new ArrayList<Course>(); 
	try
	{
	courseMap.put("CourseDetail",getPublishedCourseListeluminate(userId,1));
	}
	catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}
	return courseMap;
	}
	
	
	public Map<String, Object> getSessionDetail(Integer courseId, Integer sectionId, Integer userId)
	{
	logger.log(Level.DEBUG,
	"Inside Student Course Service in getSectionDetails method::::::");
	Map< String, Object> sessionMap= new HashMap<String, Object>();
	List<Session> sessions=new ArrayList<Session>();

	try {
	QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_DETAILS_NEW,new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {
	System.out.println("dsdsds");
	Session session=new Session();
	session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	session.setSessionName(row.getRowItem(1));
	session.setCreatedDate(row.getRowItem(2));
	session.setIsLive(Integer.parseInt(row.getRowItem(3)));
	session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	session.setIsEnable(Integer.parseInt(row.getRowItem(4)));
	session.setIsFree(Integer.parseInt(row.getRowItem(5)));
	session.setVideocompleted(getVideoCompletedStatus(courseId,sectionId,session.getSessonId(),userId)); 
	//session.setAttempts(getAttemptsForAPI1(session.getSessonId()));
	session.setVideContent(getSessionVideo1(session.getSessonId()).getSessionName()!=null?getSessionVideo1(session.getSessonId()).getSessionName():"");
	session.setVideoContentId((getSessionVideo1(session.getSessonId()).getVideoContentId()!=null?getSessionVideo1(session.getSessonId()).getVideoContentId():0));
	sessions.add(session);
	}
	sessionMap.put("sessiondetails", sessions);
	}
	catch (Exception e) {
	// TODO: handle exception
	}
	return sessionMap;
	}
	
	
	public Map<String, Object> getAllSectionDetail(Integer courseId,Integer userId)
	{
	logger.log(Level.DEBUG,
	"Inside Student Course Service in getSectionDetails method::::::");

	Map< String, Object> sectionMap= new HashMap<String, Object>();
	List<Section> sections=new ArrayList<Section>();

	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_SECTION_DETAILS, new Object[] {
	courseId});

	for (QueryData.Row row : data.getRows()) {

	Section section=new Section();
	section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	section.setSectionName(row.getRowItem(1));
	section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	section.setParentId(Integer.parseInt(row.getRowItem(0)));
	section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	sections.add(section);

	}
	sectionMap.put("sectiondetail", sections);

	}
	catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}
	return sectionMap;
	}
	
	public Course getAllSectionDetailForApis(Integer courseId,Integer userId)
	{
		Course course=new Course();
		
		List<Section> sections=new ArrayList<Section>();
		
		try {
			QueryData data = QueryManager.execuateQuery(
			QueryStrings.GET_SECTION_DETAILS_FOR_API, new Object[] {
			courseId});

			for (QueryData.Row row : data.getRows()) {
				Section section=new Section();
				Minute minutes=courseservise.getTotalViewSession(courseId,userId);
			
			course.setCourseName(row.getRowItem(5));
			course.setVideocounts(minutes.getSessionView().toString());
			course.setPerformanceTillNow(studentTestService.getPerformanceTillNow(userId).toString());
			course.setTimeSpent(minutes.getMin()+":"+minutes.getSecond());
			course.setCourseImageUrl(row.getRowItem(6));
			course.setSectionList(sections);
			section.setSectionId(Integer.parseInt(row.getRowItem(0)));
			section.setSectionName(row.getRowItem(1));
			section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
			section.setParentId(Integer.parseInt(row.getRowItem(0)));
			section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
			//section.setVideoview(minutes.getSessionView());
			//section.setPerformnceTillDate(studentTestService.getPerformanceTillNow(userId).toString());
			//section.setTimeSpent(minutes.getMin()+":"+minutes.getSecond());
			section.setChapterProgress(studentTestService.getoverallperformance(courseId, userId));
			sections.add(section);
			}
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return course;
		
		
		
		
		
		
	}
	
	
	public List<Section> getAllSectionDetailsNew(Integer sectionId,Integer userId)
	{
	List<Section> sections = new ArrayList<Section>();
	try {
	QueryData data = QueryManager.execuateQuery(
	QueryStrings.GET_SECTION_DETAILSNEW, new Object[] {
			sectionId});

	for (QueryData.Row row : data.getRows()) {

	Section section=new Section();
	section.setSectionId(Integer.parseInt(row.getRowItem(0)));
	section.setSectionName(row.getRowItem(1));
	section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
	section.setParentId(Integer.parseInt(row.getRowItem(0)));
	section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	section.setCourseId(Integer.parseInt(row.getRowItem(5)));
	section.setContainsChapterTest(Integer.parseInt(row.getRowItem(6)));
	section.setChapterProgress(studentTestService.getoverallperformance(section.getCourseId(), userId));
	section.setChapterImageUrl(row.getRowItem(7));
	
	//Todo get ImageUrl
	String slugImageUrl=section.getChapterImageUrl();
	
	if(section.getIsPractice()!=1)
	{
	section.setSession(getSessionDetailsForApiNew(sectionId,section.getCourseId(),userId,slugImageUrl,section.getIsChapterTest()));
	}
	if(section.getContainsChapterTest()==1)
	{
	section.setChapterSection(getAllSectionDetailsNew1(sectionId, userId,section.getContainsChapterTest()));
	}
	section.setAllAttemptStatus(courseservise.checkAllTestStatus(userId, section.getCourseId(), sectionId,section.getSession()));
	section.setAllAttempt1Status(courseservise.checkAllAttempt1TestStatus(userId, section.getCourseId(), sectionId,section.getSession()));
	sections.add(section);

	}
			/*
			 * QueryData data1 = QueryManager.execuateQuery(
			 * QueryStrings.GET_SECTION_DETAILSNEW, new Object[] { sectionId+2});
			 * 
			 * for (QueryData.Row row : data1.getRows()) { Section section=new Section();
			 * section.setSectionId(Integer.parseInt(row.getRowItem(0)));
			 * section.setSectionName(row.getRowItem(1));
			 * section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
			 * section.setParentId(Integer.parseInt(row.getRowItem(3)));
			 * section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
			 * if(section.getIsPractice()!=1) {
			 * section.setSession(getSessionDetailsForApiNewForChapterSection(section.
			 * getSectionId(),section.getCourseId(),userId)); }
			 * System.out.println("courseInPreviousMethod="+section.getCourseId());
			 * sections.add(section);
			 * 
			 * }
			 */
	
			
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return sections;
	}
	
	
	public List<Section> getAllSectionDetailsNew1(Integer sectionId,Integer userId,Integer isChapterTest)
	{
	List<Section> sections = new ArrayList<Section>();
	try {
	
	QueryData data1 = QueryManager.execuateQuery(
			QueryStrings.GET_SECTION_DETAILS_FOR_CHAPTERTEST, new Object[] {
					sectionId});

			for (QueryData.Row row : data1.getRows()) {
				Section section=new Section();
			section.setSectionId(Integer.parseInt(row.getRowItem(0)));
			section.setSectionName(row.getRowItem(1));
			section.setIsPractice(Integer.parseInt(row.getRowItem(2)));
			section.setParentId(Integer.parseInt(row.getRowItem(3)));
			section.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
			section.setCourseId(Integer.parseInt(row.getRowItem(5)));
			if(section.getIsPractice()!=1)
			{
			section.setSession(getSessionDetailsForApiNewForChapterSection(section.getSectionId(),section.getCourseId(),userId,isChapterTest));
			}
			sections.add(section);

			}	
			
	}
	catch (Exception e) {
	// TODO: handle exception
	}
	return sections;
	}


	public List<Session> getSessionDetailsForApiNew(Integer sectionId,Integer courseId,Integer userId,String slugImageUrl,Integer isChapterTest)
	{
		System.out.println("getCourseId"+courseId);
	List<Session> sessions=new ArrayList<Session>();

	try {
	QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_DETAILSNEW,new Object[] {
	sectionId
	});
	System.out.println("========="+userId+"========="+courseId);
	for (QueryData.Row row : data.getRows()) {
	Session session=new Session();
	session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	session.setSessionName(row.getRowItem(1));
	session.setCreatedDate(row.getRowItem(2));
	session.setIsLive(Integer.parseInt(row.getRowItem(3)));
	session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	session.setIsEnable(Integer.parseInt(row.getRowItem(5)));
	session.setIsFree(Integer.parseInt(row.getRowItem(6)));                  
	session.setVideContent(getSessionVideo(session.getSessonId()));
	session.setVideocompleted(getVideoCompletedStatus(courseId,sectionId,session.getSessonId(),userId));
	session.setVideoTime(getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getVideoTime()!=null?getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getVideoTime():"0:0");
	session.setIsResume(getVideoCompletedStatus1(courseId,sectionId,session.getSessonId(),userId).getIsResume());
	session.setVideoContentId((getSessionVideo1(session.getSessonId()).getVideoContentId()!=null?getSessionVideo1(session.getSessonId()).getVideoContentId():0));
	session.setAttempts(getAttemptsForAPINew(sectionId,session.getSessonId(),userId,courseId,session.getVideocompleted(),isChapterTest));
	session.setSessionVideoDuration((getSessionVideo1(session.getSessonId()).getSessionVideoDuration()));
	session.setVideoUrl(row.getRowItem(7));
	session.setIsSubscriber(isSubsCribed(userId, session.getSessonId()));
	session.setSlugImageUrl(slugImageUrl);
	session.setVideoTitle("https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.en.vtt");
	System.out.println(session.getSessonId());
	sessions.add(session);
	}
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}

	return sessions;

	}

	public List<Attempts> getAttemptsForAPINew(Integer sectionId,Integer sessionId,Integer userId,Integer courseId,Integer videoCompleted,Integer isChapterTest)
	{

	List<Attempts>attempts=new ArrayList<Attempts>();
	try { QueryData data=QueryManager.execuateQuery(QueryStrings.GET_ATTEMPT_DETAILS, new Object[] {sessionId});
	for (QueryData.Row row : data.getRows()) {
		boolean flag=false;
		System.err.println("is chapter test"+isChapterTest);
	Attempts attempt=new Attempts();
	attempt.setSectionId(sectionId);
	attempt.setIsChapterTest(isChapterTest);
	attempt.setId(Integer.parseInt(row.getRowItem(0)));
	attempt.setQuizTitle(row.getRowItem(1));
	attempt.setContentId(Integer.parseInt(row.getRowItem(2)));
	attempt.setSessionId(Integer.parseInt(row.getRowItem(3)));
	attempt.setTestId(Integer.parseInt(row.getRowItem(4)));
	attempt.setVideocompleted(videoCompleted);
	System.out.println("Attempt id"+attempt.getId());
	System.out.println("content id"+attempt.getContentId());
	ContentView  view=findAlreadyCourseContentViewDetails(userId, courseId, sectionId, attempt.getId(),new Long(sessionId),attempt.getContentId());
	System.out.println("view"+view);
	if(view !=null)
	{
	flag=studentTestService.checkTestSubmitted(view.getTestAttemptId());
	System.out.println("status flag inner"+flag);
	}
	System.out.println("status flag outer"+flag);
	attempt.setTotalQuestion(getTestDetail(attempt.getTestId()).getMaxQuestions());
	attempt.setTestMark(getTestDetail(attempt.getTestId()).getMaxMark());
	attempt.setTestStatus(flag);
	attempts.add(attempt);

	}

	}

	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return attempts;
	}
	
	public List<Session> getSessionDetailsForApiNewForChapterSection(Integer sectionId,Integer courseId,Integer userId,Integer isChapterTest)
	{
	List<Session> sessions=new ArrayList<Session>();

	try {
	QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SESSION_DETAILS,new Object[] {
	sectionId
	});
	System.out.println("========="+userId+"========="+courseId);
	for (QueryData.Row row : data.getRows()) {
	Session session=new Session();
	session.setSessonId(Integer.parseInt(row.getRowItem(0)));
	session.setSessionName(row.getRowItem(1));
	session.setCreatedDate(row.getRowItem(2));
	session.setIsLive(Integer.parseInt(row.getRowItem(3)));
	session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
	session.setIsEnable(Integer.parseInt(row.getRowItem(5)));
	session.setIsFree(Integer.parseInt(row.getRowItem(6)));
	                  
	session.setAttempts(getAttemptsForAPINew(sectionId,session.getSessonId(),userId,courseId,null,isChapterTest));
	//session.setVideContent(getSessionVideo(session.getSessonId()));
	//session.setVideocompleted(getVideoCompletedStatus(courseId,sectionId,session.getSessonId(),userId));
	//session.setIsSubscriber(isSubsCribed(userId, session.getSessonId()));
	sessions.add(session);
	}
	}
	catch (Exception e) {
	// TODO: handle exception
	}

	return sessions;

	}
	
	@SuppressWarnings("unused")
	public Boolean isSubsCribed(Integer userId,Integer sessionId)
	{ 
		boolean flag=false;
		Integer data1=0;
		try { QueryData data=QueryManager.execuateQuery(QueryStrings.GET_SUBSCRIPTIONSTATUS_BY_SESSION, new Object[] {sessionId,userId});
		for (QueryData.Row row : data.getRows()) {
			data1=Integer.parseInt(row.getRowItem(0));
			if(data1!=null)
			{
				flag=true;
			}
			else {
				flag=false;
			}
		}
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	
	public List<Section> getChaptersForGraph(Integer courseId,Integer userId)
	{
		List<Section> sections = new ArrayList<Section>();	
		
		
		try {
			System.err.println("Inside get Chapters for graph");

			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION_WISE_CHARTS,
					new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {
				Section section = new Section();
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				System.out.println("chapters=========");
				if (Integer.parseInt(row.getRowItem(2)) == 0)
				{
					System.err.println("Ischaptertest==0");
					section.setSectionName(row.getRowItem(1));
				}
				else
				{
					System.err.println("isChapterTest==1");
					section.setSectionName("flag");
				}
				section.setIsChapterTest(Integer.parseInt(row.getRowItem(2)));
				section.setMarks(getSessions(section.getSectionId(), userId, courseId));
				sections.add(section);
			}
		}
	catch (Exception e) {
		// TODO: handle exception
	}
	return sections;
	
	
	}
	
	public Double getOverallChapterMarks(Integer userId,Integer courseId)
	{
		
		System.err.println("inside getoverallchaptermarks");
		DecimalFormat dc=new DecimalFormat("##.00");
		logger.log(Level.DEBUG, "Inside Student Test Service getOverallChapterMarks method:::::::::::");
		double overallmarks=0.0f;
		Double percentageofMarks=0.0;
		double totalMarks=0.0f;
		try {
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_OVERALL_MARKS_OF_COURSE,
				new Object[] {userId,courseId});
				for (QueryData.Row row : data.getRows()) {
					 
				Integer isRandom=Integer.parseInt(row.getRowItem(3));
			     if(isRandom == 1)
			     {
				QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_MARKS_FOR_STATIC_TEST,
						new Object[] {userId,courseId});
						for (QueryData.Row row1 : data1.getRows()) {
							overallmarks=overallmarks+Integer.parseInt(row1.getRowItem(0));
			                totalMarks=totalMarks+Integer.parseInt(row1.getRowItem(2));
			                System.out.println(row1.getRowItem(2));
			                System.err.println(totalMarks);
			                System.out.println("Random hai ye question");
						}
			     }	
			     else
			     {
			    	  System.out.println("simple hai ye question");
			    	 overallmarks=overallmarks+Integer.parseInt(row.getRowItem(0));
		                totalMarks=totalMarks+Integer.parseInt(row.getRowItem(2));
							 
			     }
				}
            	System.err.println("overallmarkssssss=="+overallmarks+"   total marks="+totalMarks);
				percentageofMarks=overallmarks*100/totalMarks;
				System.out.println("=========="+overallmarks+"=========="+totalMarks);
				if(percentageofMarks.isNaN())
				{
					percentageofMarks=0.0;
				}
		         System.out.println(percentageofMarks); 
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return Double.parseDouble(dc.format(percentageofMarks));
	}


    

	public List<Double> getSessions(Integer sectionId, Integer userId,Integer courseId) {
		System.err.println("inside getsession");
		DecimalFormat dc=new DecimalFormat("##.00");
		List<Session> sessions = new ArrayList<Session>();
		Double containsChapterTest =0.0;
		float totamarksforattempt1 = 0.0f; 
		float totamarksforattempt2 = 0.0f;
		float totalchaptertestmarksforattempt1 = 0.0f;
		float totalchaptertestmarksforattempt2 = 0.0f;
		float totaTestmarksforattempt1 = 0.0f;
		float totatestmarksforattempt2 = 0.0f;
		float totaltestchaptertestmarksforattempt1 = 0.0f;
		float totaltestchaptertestmarksforattempt2 = 0.0f;
		Double perforatt1 = 0.0;
		Double perforatt2 = 0.0;
		Double perforch1 = 0.0;
		Double perforch2 = 0.0;
		UserTestAttempt attemptfornormaltest = new UserTestAttempt();
		UserTestAttempt attemptforchaptertest = new UserTestAttempt();
		List<Double> doubles=new ArrayList<Double>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION_ID, new Object[] { sectionId });
			for (QueryData.Row row : data.getRows()) {
				
				Session session = new Session();
				session.setSessonId(Integer.parseInt(row.getRowItem(0)));
				session.setSessionName(row.getRowItem(1));
				session.setSectionId(Integer.parseInt(row.getRowItem(2)));
				session.setAttemptId(Integer.parseInt(row.getRowItem(3)));
				session.setIsChapterTest(Integer.parseInt(row.getRowItem(4)));
				session.setContentId(getContentId(session.getSessonId()));
			      for (int i = 0; i < session.getContentId().size(); i++) {
					
				
				System.out.println("session name " + session.getSessionName());
				System.err.println("CourseId " + courseId + " uid " + userId + " atttid" + session.getAttemptId()
						+ " SessionId" + session.getSessonId() + "contentId " + session.getContentId().get(i) + " SEctionid"
						+ session.getSectionId() + "==================");
				System.err.println("============"+session.getIsChapterTest());
				if (session.getIsChapterTest() == 1) {
					System.err.println("Chapter Test");
					containsChapterTest=1.0;
                      //System.out.println("Attempt id "+session.getAttemptId()+"ContentId"+session.getContentId());
					if (session.getAttemptId() == 1) {
						System.err.println("If attempt id==1"+sectionId + "=" + session.getSessonId() + "=" + session.getContentId().get(i) + "="
								+ session.getAttemptId() + "=" + session.getSectionId());
						attemptforchaptertest = getTestData(userId,courseId,session.getSectionId(),session.getSessonId() ,
								session.getContentId().get(i),session.getAttemptId() );
						totalchaptertestmarksforattempt1 += attemptforchaptertest.getObtainMarks();
						totaltestchaptertestmarksforattempt1 += attemptforchaptertest.getFloattotalmarks();
						System.err.println("attempt1 ka mark"+totalchaptertestmarksforattempt1+" total"+totaTestmarksforattempt1);


					}

					if (session.getAttemptId() == 2) {
						System.err.println("If attempt id==2");
						attemptforchaptertest = getTestData(userId,courseId,session.getSectionId(),session.getSessonId() ,
								session.getContentId().get(i),session.getAttemptId() );
						totalchaptertestmarksforattempt2 += attemptforchaptertest.getObtainMarks();
						totaltestchaptertestmarksforattempt2 += attemptforchaptertest.getFloattotalmarks();
						System.err.println("attempt2 ka mark"+totalchaptertestmarksforattempt2);
					}
				}

				if (session.getIsChapterTest() == 0) {
					System.err.println("Normal Test");
					  System.out.println("Attempt id "+session.getAttemptId());
					if (session.getAttemptId() == 1) {
						attemptfornormaltest = getTestData(userId,courseId,session.getSectionId(),session.getSessonId() ,
								session.getContentId().get(i),session.getAttemptId() );
						totamarksforattempt1 += attemptfornormaltest.getObtainMarks();
						totaTestmarksforattempt1 += attemptfornormaltest.getFloattotalmarks();
						System.err.println("attempt1 ka mark"+totamarksforattempt1);

					}

					if (session.getAttemptId() == 2) {
						attemptfornormaltest = getTestData(userId,courseId,session.getSectionId(),session.getSessonId() ,
								session.getContentId().get(i),session.getAttemptId() );
						totamarksforattempt2 += attemptfornormaltest.getObtainMarks();
						totatestmarksforattempt2 += attemptfornormaltest.getFloattotalmarks();
						System.err.println("attempt2 ka mark"+totamarksforattempt2);
					}

				}
				System.out.println(
						"courseId==" + sectionId + " userId==" + userId + "    sessionid==" + session.getSessonId()
								+ " contenid==" + session.getContentId().get(i) + "sectionId==" + session.getSectionId());
				sessions.add(session);

			}

			System.out.println("Total marks for attempt1 " + totamarksforattempt1 + " total marks for att1 "
					+ totaTestmarksforattempt1);
			System.out.println("Total marks for attempt2 " + totamarksforattempt2 + " total marks for att1 "
					+ totatestmarksforattempt2);
			System.out.println("Total chapter test marks for attempt1 " + totalchaptertestmarksforattempt1
					+ " total marks for att1 " + totaltestchaptertestmarksforattempt1);
			System.out.println("Total chapter test marks for attempt2 " + totalchaptertestmarksforattempt2
					+ " total marks for att2 " + totaltestchaptertestmarksforattempt2);
			perforatt1 = (totamarksforattempt1 * 100.0)/totaTestmarksforattempt1 ;
			System.err.println("====TMA1====="+totamarksforattempt1+"=====TTM====="+totaTestmarksforattempt1);
			perforatt2 = (totamarksforattempt2 * 100.0) / totatestmarksforattempt2;
			perforch1 = (totalchaptertestmarksforattempt1 * 100.0) / totaltestchaptertestmarksforattempt1;
			perforch2 = (totalchaptertestmarksforattempt2 * 100.0) / totaltestchaptertestmarksforattempt2;
			System.out.println("=============="+perforatt1+"  "+perforatt2+"  "+perforch1+" "+perforch2);
			if(perforatt1.isNaN())
				perforatt1=0.0;
			if(perforatt2.isNaN())
				perforatt2=0.0;
			if(perforch1.isNaN())
				perforch1=0.0;
			if(perforch2.isNaN())
				perforch2=0.0;
			/*
			 * perforatt1=(double) Math.round(perforatt1); perforatt2=(double)
			 * Math.round(perforatt2); perforch1=(double) Math.round(perforch1);
			 * perforch2=(double) Math.round(perforch2);
			 */
			perforatt1=Double.parseDouble(dc.format(perforatt1));
			perforatt2=Double.parseDouble(dc.format(perforatt2));
			perforch1=Double.parseDouble(dc.format(perforch1));
			perforch2=Double.parseDouble(dc.format(perforch2));
		   
			}
			doubles.add(perforatt1);
			doubles.add(perforatt2);
			doubles.add(perforch1);
			doubles.add(perforch2);
			doubles.add(containsChapterTest);
			System.out.println("data in percent="+perforatt1+"%"+perforatt2+"%"+perforch1+"%"+perforch2);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return doubles;
	}



	
	public List<Integer> getContentId(Integer sessionId)
	{
		List<Integer> list=new ArrayList<Integer>();
		Integer contentId=null;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SESSION_CONTENT, new Object[] { sessionId });
			for (QueryData.Row row : data.getRows()) {
				contentId=Integer.parseInt(row.getRowItem(0));
				System.err.println("contentid"+contentId);
				list.add(contentId);
			}
			}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	
	
	public UserTestAttempt getTestData(Integer userId,Integer courseId,Integer sectionId,Integer sessionId,
			Integer contentId,Integer attemptId) {
		
		System.out.println();
		UserTestAttempt attempt = new UserTestAttempt();
		attempt.setObtainMarks(0.0f);
		attempt.setTestId(0);
		attempt.setTotalMarks(0);
		attempt.setFloattotalmarks(0.0f);
		try {
			
			System.out.println("Inside test Data(Yaha par result nikaalte hain");
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_STUDENT_TEST_RESULT,
					new Object[] { userId, courseId, sectionId, sessionId, contentId, attemptId });
			for (QueryData.Row row : data.getRows()) {
				System.out.println("For loop ke andar");
				if(Integer.parseInt(row.getRowItem(3))==1)
				{
					QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_STUDENT_TEST_RESULT1,
							new Object[] { userId, courseId, sectionId, sessionId, contentId, attemptId });
					for (QueryData.Row row1 : data1.getRows()) {
						System.out.println("random");
						attempt.setObtainMarks(Float.parseFloat(row1.getRowItem(0)));
						attempt.setTestId(Integer.parseInt(row1.getRowItem(1)));
						attempt.setFloattotalmarks(Float.parseFloat(row1.getRowItem(2)));
						System.out.println("Random aagya");
						System.out.println("obtainmarks========"+attempt.getObtainMarks()+" totalmarks========"+attempt.getFloattotalmarks()+" SEssion name"+attempt.getSessionName());
						System.out.println("===="+attempt.getObtainMarks());
						
					
					}
			 	}
				else
				{
					System.out.println("simple");
				attempt.setObtainMarks(Float.parseFloat(row.getRowItem(0)));
				attempt.setTestId(Integer.parseInt(row.getRowItem(1)));
				attempt.setFloattotalmarks(Float.parseFloat(row.getRowItem(2)));
				System.out.println("simple aagya");
				System.out.println("dfdfdfobtainmarks========"+attempt.getObtainMarks()+"fdfdfd totalmarks========"+attempt.getFloattotalmarks()+" SEssion name"+attempt.getSessionName());
				System.out.println("===="+attempt.getObtainMarks());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return attempt;
	}



	public List<Integer> getCourseId()
	{
		List<Integer> allCourseId=new ArrayList<>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_PUBLISHED_COURSE_ID,
				new Object[] {  });
		for (QueryData.Row row : data.getRows()) {
		   Integer courseId=Integer.parseInt(row.getRowItem(0));
		   allCourseId.add(courseId);
	 	}
		return allCourseId;
		
	}

	public Integer updateProfilepicture(Integer userId,String imageUrl)
	{
	Integer id=0;
	try {
	id = QueryManager.execuateUpdate(
	QueryStrings.UPDATE_PROFILE_PICTURE_URL,
	new Object[] { imageUrl,userId });

	}
	catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
	}
	return id;
	}
	
	
	public boolean smileFeedback(SessionFeedback feedback)
	{
		
		System.err.println("is_good"+feedback.getIsGood()+" is_bad"+feedback.getIsBad()+" isExcellent"+feedback.getIsExcellent()+" courseId"+feedback.getCourseId()+" sectionId"+feedback.getSectionId()+" sessionId"+feedback.getSessionId()+" contentid"+feedback.getContentId());
		Integer uid = null;
		Integer id=0;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SMILE_DATA,new Object[] {feedback.getUserId(),feedback.getCourseId(),feedback.getSectionId(),feedback.getSessionId(),feedback.getContentId()});
		for (QueryData.Row row : data.getRows()) {
			uid=Integer.parseInt(row.getRowItem(0));
			
		}
		if(uid==null)
		{
			id=insertFeedBackSmiles(feedback);
		}
		if(uid!=null)
		{
			id=updateFeedbackSmiles(feedback);
		}
		return true;
		
	}

	public Integer insertFeedBackSmiles(SessionFeedback feedback)
	{
		System.out.println("Insert wale mein");
		Integer id = null;
			try {
				id = QueryManager.execuateUpdate(
						QueryStrings.INSERT_FEEDBACK_DATA,
						new Object[] { feedback.getUserId(), feedback.getCourseId(),feedback.getSectionId(),feedback.getSessionId(),feedback.getContentId(),feedback.getIsGood(),feedback.getIsBad(),feedback.getIsExcellent(),feedback.getUserFeedback() });

			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			return id;
	}
	
	
	public Integer updateFeedbackSmiles(SessionFeedback feedback)
	{
		System.out.println("update wale mein");
		Integer id=null;
		try {
			if(feedback.getUserFeedback()!=null)
			{
			id=QueryManager.execuateUpdate(QueryStrings.UPDATE_SMILES, new Object[] { feedback.getIsGood(),feedback.getIsBad(),feedback.getIsExcellent(),feedback.getUserFeedback(),feedback.getUserId(), feedback.getCourseId(),feedback.getSectionId(),feedback.getSessionId(),feedback.getContentId() });
			System.err.println("id......"+id+" "+feedback.getUserFeedback());
			}
			else {
				id=QueryManager.execuateUpdate(QueryStrings.UPDATE_SMILES_WITHOUT_FEEDBACK, new Object[] { feedback.getIsGood(),feedback.getIsBad(),feedback.getIsExcellent(),feedback.getUserId(), feedback.getCourseId(),feedback.getSectionId(),feedback.getSessionId(),feedback.getContentId() });
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return id;
		
	}
	
	
	public SessionFeedback getSmileDataForContent(SessionFeedback feedback)
	{
		try {
		System.out.println("Inside getSmileDataForContent");
		feedback.setGoodReviewCount(getGoodReviewCount(feedback.getSessionId()));
	       feedback.setBadReviewCount(badReviewCount(feedback.getSessionId()));
	       feedback.setExcellentReviewCount(excellentReviewCount(feedback.getSessionId()));
	       feedback.setEmailValidated(isEmailValidated(feedback.getUserId()));
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SMILE_DATA,new Object[] {feedback.getUserId(),feedback.getCourseId(),feedback.getSectionId(),feedback.getSessionId(),feedback.getContentId()});
		for (QueryData.Row row : data.getRows()) {
       feedback.setGood(Integer.parseInt(row.getRowItem(0))==1 ? true:false );
       feedback.setBad(Integer.parseInt(row.getRowItem(1))==1 ? true:false );
       feedback.setExcellent(Integer.parseInt(row.getRowItem(2))==1 ? true:false );
       feedback.setUserFeedback(row.getRowItem(3));
       if(feedback.getIsGood()==true)
		{
			feedback.setReview("Good");
		}
		if(feedback.getIsBad()==true)
		{
			feedback.setReview("Bad");
		}
		if(feedback.getIsExcellent()==true)
		{
			feedback.setReview("Excellent");
		}
			
		}
		System.err.println("new feedback=="+feedback);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return feedback;
		
	}

	public String getGoodReviewCount(Integer sessionId)
	{
		System.out.println("Inside get Good Review");
		String goodReview=null;
		try
		{
		System.out.println("goodre");
		
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_GOOD_COUNT,new Object[] {sessionId});
		for (QueryData.Row row : data.getRows()) {
			goodReview=row.getRowItem(0);
			if(goodReview.contains("0"))
			{
				goodReview="";
			}
		}
		System.err.println(goodReview);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return goodReview;
	}

	
	public String badReviewCount(Integer sessionId)
	{
		System.out.println("Inside get Bad Review");
		String badReview=null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_BAD_COUNT,new Object[] {sessionId});
		for (QueryData.Row row : data.getRows()) {
			badReview=row.getRowItem(0);
			if(badReview.contains("0"))
					{
				badReview="";
					}
			System.err.println(badReview);
		}
		return badReview;
	}

	public String excellentReviewCount(Integer sessionId)
	{
		System.out.println("Inside Excellent Good Review");
		String excellentReview=null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_EXCELLENT_COUNT,new Object[] {sessionId});
		for (QueryData.Row row : data.getRows()) {
			excellentReview=row.getRowItem(0);
			if(excellentReview.contains("0"))
			{
				excellentReview="";
			}
			System.err.println(excellentReview);
		}
		return excellentReview;
	}


	
	
	public boolean getUserValidated(Integer userId,String email)
	{Integer id =null;
	System.out.println("uservalidate ho gaya");
		try {
			id = QueryManager.execuateUpdate(
					QueryStrings.VALIDATE_USER_EMAIL,
					new Object[] { email,1,userId });

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	public Integer getEmailValidationFlag(Integer userId)
	{
		
	Integer validatedFlag=null;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_EMAIL_VALIDATION_FLAG,new Object[] {userId});
			for (QueryData.Row row : data.getRows()) {
				validatedFlag=Integer.parseInt(row.getRowItem(0));
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return validatedFlag;
	

	}
	
	

	public List<SessionFeedback> listOfFeedback(Integer courseId)
	{
	 
	 List<SessionFeedback> feedbacks=new ArrayList<SessionFeedback>();
	 try {
		 System.err.println("Inside listOfFeedbacks");
		 
		 QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ALL_FEEDBACKS,new Object[] {courseId});
			for (QueryData.Row row : data.getRows()) {
				SessionFeedback feedback = new SessionFeedback();
				feedback.setGood(Integer.parseInt(row.getRowItem(0))==1 ? true:false );
				feedback.setBad(Integer.parseInt(row.getRowItem(1))==1 ? true:false );
			       feedback.setExcellent(Integer.parseInt(row.getRowItem(2))==1 ? true:false );
				
				if(feedback.getIsGood()==true)
				{
					feedback.setReview("Good");
				}
				if(feedback.getIsBad()==true)
				{
					feedback.setReview("Bad");
				}
				if(feedback.getIsExcellent()==true)
				{
					feedback.setReview("Excellent");
				}
			       
			       feedback.setUserFeedback(row.getRowItem(3)==null ? "No":row.getRowItem(3));
			       feedback.setSessionId(Integer.parseInt(row.getRowItem(4)==null ? " ": row.getRowItem(4)));
			       feedback.setUserId(Integer.parseInt(row.getRowItem(5)== null ? " ": row.getRowItem(5)));
			       feedback.setSecondryEmail(row.getRowItem(6)==null ? "SecEmail":row.getRowItem(6));
			       System.err.println("hahahaha"+feedback.getReview()+"good"+feedback.getIsGood()+" bad"+feedback.getIsBad()+" ex"+feedback.getIsExcellent()+"====="+feedback.getUserFeedback()+"==="+feedback.getSecondryEmail());
			       feedbacks.add(feedback);
			      // System.out.println(feedback.getReview());
			}
		      // System.out.println("hahahaha"+feedback.getReview()+"good"+feedback.getIsGood()+" bad"+feedback.getIsBad()+" ex"+feedback.getIsExcellent()+"====="+feedback.getUserFeedback()+"==="+feedback.getSecondryEmail());
			feedbacks.forEach(m->{
				System.out.println(m.getSecondryEmail()+"userfeedback"+m.getUserFeedback()+" SESSIONID"+m.getSessionId());
				});
			}
	 
	 
	 catch (Exception e) {
e.printStackTrace();	}
	return feedbacks;

}
	
	
public boolean isEmailValidated(Integer userId)
{
	boolean flag=false;
	Integer isValidated=null;
	try {
		 QueryData data = QueryManager.execuateQuery(QueryStrings.IS_EMAIL_VALIDATED,new Object[] {userId});
			for (QueryData.Row row : data.getRows()) {
				isValidated=Integer.parseInt(row.getRowItem(0));
				if(isValidated==1)
				{
					flag=true;
				}
			}
		
	}
	catch (Exception e) {
		e.getStackTrace();
		// TODO: handle exception
	}
	return flag;
}
	

public boolean sendValidationEmail(User user,HttpServletRequest request,HttpServletResponse response)
{
	boolean flag=false;
	System.out.println("validate mail send ho gaya");
	String token = CommonUtil.getTokenFromCookie(request);
	try {
		System.out.println(user.getEmail());
		EmailSender emailSender=new EmailSender();
		Mailsender mailsender=new Mailsender();
		mailsender.setMail(user.getEmail());
		mailsender.setBody("https://qalms.eluminate.in/api/getmailvalidated?token="+token);
		mailsender.setSubject("Eluminate Validation");
		emailSender.sendEmaildoubt(mailsender);
		HttpSession session =request.getSession();
		/*
		 * session.setAttribute("token",token); session.setAttribute("email",
		 * user.getEmail());
		 */
		flag=true;
		new LoginController().logout(response);
	
}
catch (Exception e) {
	// TODO: handle exception
}
	return flag;

}


public boolean sendValidationEmailFromMObile(User user,HttpServletRequest request,HttpServletResponse response)
{
	boolean flag=false;
	System.out.println("validate mail send ho gaya");
	String token = CommonUtil.getTokenFromCookie(request);
	try {
		System.out.println(user.getEmail());
		EmailSender emailSender=new EmailSender();
		Mailsender mailsender=new Mailsender();
		mailsender.setMail(user.getEmail());
		mailsender.setBody("https://qalms.eluminate.in/api/getmailvalidated?token="+user.getToken());
		mailsender.setSubject("Eluminate Validation");
		emailSender.sendEmaildoubt(mailsender);
		HttpSession session =request.getSession();
		/*
		 * session.setAttribute("token",token); session.setAttribute("email",
		 * user.getEmail());
		 */
		flag=true;
		new LoginController().logout(response);
	
}
catch (Exception e) {
	// TODO: handle exception
}
	return flag;

}
}


