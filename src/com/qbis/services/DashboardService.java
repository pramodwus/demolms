package com.qbis.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.qbis.common.ConstantUtil;
import com.qbis.common.LicenseEnum;
import com.qbis.common.QbisUtils;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.License;
import com.qbis.model.LicenseSubDetails;
import com.qbis.model.Test;
import com.qbis.model.UserTestAttempt;

/**
 * This class contain operations related to Dashboard service.
 * 
 * @author Neeraj
 *
 */
@Component
public class DashboardService {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(DashboardService.class);

	/**
	 * This is for count the no of total created test of a particular user.
	 * 
	 * @param userId
	 * @return int returns total no of created test by user.
	 */
	public int getTotalTestCount(int userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalTestCount method:::::");
		int count = 0;
		try {
			QueryData data = null;
			if (userId == 0) {
				data = QueryManager.execuateQuery(
						QueryStrings.TOTAL_TEST_COUNT, new Object[] { userId });
			} else {
				data = QueryManager.execuateQuery(
						QueryStrings.GET_TOTAL_TEST_COUNT,
						new Object[] { userId });
			}

			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalTestCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * This is used for count total number of courses created by a particular
	 * user.
	 * 
	 * @param userId
	 * @return int this returns total number of course of a user.
	 */
	public int getTotalCourseCount(int userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalCourseCount method:::::");
		int count = 0;
		try {
			QueryData data = null;
			if (userId == 0) {
				data = QueryManager.execuateQuery(
						QueryStrings.TOTAL_COURSE_COUNT,
						new Object[] { userId });
			} else {
				data = QueryManager.execuateQuery(
						QueryStrings.GET_TOTAL_COURSE_COUNT,
						new Object[] { userId });
			}

			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalCourseCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * Method to get count of all user by creater UserId
	 * 
	 * @param userId
	 * @return int .
	 */
	public int getTotalUsersCount(int userid) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalUsersCount method:::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_TOTAL_USER_COUNT_CREATED_BY,
					new Object[] { userid });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalUsersCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * A method which is used to getting 5 latest attempted test list
	 * 
	 * @param userId
	 * @return List
	 */
	public List<Test> getMyTestByUserId(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getMyTestByUserId method:::::");
		List<Test> list = new ArrayList<Test>();
		Test test;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_MY_TESTS_BY_USERID_DASHBOARD,
					new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				test = new Test();
				test.setUserId(Integer.parseInt(row.getRowItem(0)));
				test.setTestId(Integer.parseInt(row.getRowItem(1)));
				test.setTestName(row.getRowItem(2).substring(0, 1)
						.toUpperCase()
						+ row.getRowItem(2).substring(1));
				test.setTestTime(row.getRowItem(3));
				test.setTestMark(Integer.parseInt(row.getRowItem(10)));
				/**
				 * obtained marks by users
				 */
				test.setMaxMark(Double.parseDouble(row.getRowItem(11)));
				/**
				 * user email id who attempt the test
				 */
				test.setTeacherName(row.getRowItem(5));
				list.add(test);
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getMyTestByUserId method:::::",
					e);
		}
		return list;
	}

	/**
	 * This is used getting attempted test list.
	 * 
	 * @param userId
	 * @return List
	 */
	public List<UserTestAttempt> getAttemptedTestByUserId(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getAttemptedTestByUserId method:::::");
		QueryData givendata = QueryManager.execuateQuery(
				QueryStrings.GET_ATTEMPTED_TESTS_BY_USERID,
				new Object[] { userId });
		List<UserTestAttempt> usertest = new ArrayList<UserTestAttempt>();
		UserTestAttempt userTestAttempt;
		for (QueryData.Row givenRow : givendata.getRows()) {
			userTestAttempt = new UserTestAttempt();
			userTestAttempt.setTestId(Integer.parseInt(givenRow.getRowItem(0)));
			userTestAttempt.setObtainMarks(Float.parseFloat(givenRow
					.getRowItem(1)));
			userTestAttempt.setTotalMarks(Integer.parseInt(givenRow
					.getRowItem(2)));
			int second = givenRow.getRowItem(3) == null ? 0 : Integer
					.parseInt(givenRow.getRowItem(3)) % 60;
			int minute = givenRow.getRowItem(3) == null ? 0 : (Integer
					.parseInt(givenRow.getRowItem(3)) - second) / 60;
			userTestAttempt.setTimeTaken(minute + ":"
					+ (second < 10 ? ("0" + second) : second));
			userTestAttempt.setTestGivenTime(givenRow.getRowItem(4));
			userTestAttempt.setUserTestAttemptId(Integer.parseInt(givenRow
					.getRowItem(5)));
			userTestAttempt.setSubmitStatus(Integer.parseInt(givenRow
					.getRowItem(6)));
			userTestAttempt.setTestName(givenRow.getRowItem(7).substring(0, 1)
					.toUpperCase()
					+ givenRow.getRowItem(7).substring(1));
			double flag = (userTestAttempt.getObtainMarks() * 100)
					/ userTestAttempt.getTotalMarks();
			if (flag < 33.0) {
				userTestAttempt.setGrade("Fail");
			} else {
				userTestAttempt.setGrade("Pass");
			}
			usertest.add(userTestAttempt);
		}
		return usertest;
	}

	/**
	 * Method to get status of attempted test in last n months by trainee. This
	 * method returns a list of String. This String is in the format month#pass
	 * count#fail count.
	 *
	 * @param lastNMonths
	 * @return
	 */
	public List<String> attemptedPassFailCount(Integer userId,
			Integer lastNMonths) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service attemptedPassFailCount method:::::");
		List<String> result = new ArrayList<String>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		for (int i = 0; i < lastNMonths; i++) {
			Calendar l_calendar = Calendar.getInstance();
			if (i == 0) {
				l_calendar.set(Calendar.MONTH, l_calendar.get(Calendar.MONTH));
			} else {
				l_calendar.set(Calendar.MONTH, l_calendar.get(Calendar.MONTH)
						- i);
			}

			l_calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date startDate = l_calendar.getTime();

			startDate = QbisUtils.convertDatetoBOD(startDate);

			l_calendar.set(Calendar.DATE,
					l_calendar.getActualMaximum(Calendar.DATE));
			Date endDate = l_calendar.getTime();

			endDate = QbisUtils.convertDatetoEOD(endDate);
			int countPass = 0, countFail = 0;
			try {
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_ATTEMPT_RESULT_USER_BY_MONTH,
						new Object[] { userId, formatter.format(startDate),
								formatter.format(endDate) });
				if (data.getRows().size() > 0) {
					for (QueryData.Row row : data.getRows()) {
						if (row.getRowItem(0).equals("pass"))
							countPass++;
						else
							countFail++;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			result.add(new SimpleDateFormat("MMM").format(l_calendar.getTime())
					+ "#" + countPass + "#" + countFail);

		}
		Collections.reverse(result);
		return result;
	}

	/**
	 * This method for counting the active users by creater userId
	 * 
	 * 
	 * @param userId
	 * @return int .
	 */
	public int getTotalActiveStudentCount(int userid) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalActiveStudentCount method:::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_TOTAL_ACTIVE_USER_COUNT_BY_USERID,
					new Object[] { userid });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalActiveStudentCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * Get count of teams/groups by userId
	 * 
	 * 
	 * @param userId
	 * @return int .
	 */
	public Object getTotalTeamsCount(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalTeamsCount method:::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_TOTAL_GROUPS_BY_USERID,
					new Object[] { userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalTeamsCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * Get count of teams/groups by userId
	 * 
	 * 
	 * @param userId
	 * @return int .
	 */
	public Object getTotalTraineeCount(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalTraineeCount method:::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_TOTAL_TRAINEES_BY_USERID, new Object[] {
							ConstantUtil.TRAINEE_ROLE_ID, userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getTotalTraineeCount method:::::",
					e);
		}
		return count;
	}

	/**
	 * Get list of latest activities at teacher side
	 * 
	 * 
	 * @param userId
	 * @return list of activity.
	 */
	public List<String> getLatestActivitiesOfTrainer(Integer userId) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getLatestActivitiesOfTrainer method:::::");
		List<String> list = new ArrayList<String>();
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_ACTIVITY_LIST_OF_TRAINER, new Object[] {
							userId, userId, userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					switch (row.getRowItem(2)) {
					case "COURSE_ENROLL":
						list.add("Course " + row.getRowItem(0)
								+ " is enrolled by " + row.getRowItem(1));
						break;
					case "TEST_START":
						list.add("Assessment " + row.getRowItem(0)
								+ " is started by " + row.getRowItem(1));
						break;
					case "TEST_DONE":
						list.add("Assessment " + row.getRowItem(0)
								+ " is completed by " + row.getRowItem(1));
						break;
					default:
						break;
					}
				}
			}

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getLatestActivitiesOfTrainer method:::::",
					e);
		}
		return list;
	}

	/**
	 * Get percentage of all types of media contents upload by user
	 * 
	 * @param userId
	 * @param p_licenseobj
	 * @return map
	 */
	public Map<String, Object> getPercentageOfContent(Integer userId,
			License p_licenseobj) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getPercentageOfContent method:::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			long totalsize = 0;
			DecimalFormat df = new DecimalFormat("####0.00");
			for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
				if (p_licenseobj.getLicenseFeatureList().get(i)
						.getFeatureName()
						.equalsIgnoreCase(LicenseEnum.SPACE.getValue())) {
					List<LicenseSubDetails> list = p_licenseobj
							.getLicenseFeatureList().get(i)
							.getLicenseSubDetails();
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j)
								.getSubFeatureName()
								.equalsIgnoreCase(
										LicenseEnum.COURSECONTENTSPACE
												.getValue())) {
							totalsize = Long.parseLong(list.get(j)
									.getMaxCount());
							break;
						}
					}
				}
			}

			double imgsize = 0, videosize = 0, pdfsize = 0, pptsize = 0, freesize = 0;
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_CONTENTS_TOTAL_SIZE_BY_USERID,
					new Object[] { userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					switch (row.getRowItem(0)) {
					case "IMAGE":
						imgsize = (double) Long
								.parseLong(row.getRowItem(1) == null ? "0"
										: row.getRowItem(1))
								* 100 / totalsize;
						map.put("image", df.format(imgsize));
						break;
					case "VIDEO":
						videosize = (double) Long
								.parseLong(row.getRowItem(1) == null ? "0"
										: row.getRowItem(1))
								* 100 / totalsize;
						map.put("video", df.format(videosize));
						break;
					case "PDF":
						pdfsize = (double) Long
								.parseLong(row.getRowItem(1) == null ? "0"
										: row.getRowItem(1))
								* 100 / totalsize;
						map.put("pdf", df.format(pdfsize));
						break;
					case "PPT":
						pptsize = (double) Long
								.parseLong(row.getRowItem(1) == null ? "0"
										: row.getRowItem(1))
								* 100 / totalsize;
						map.put("ppt", df.format(pptsize));
						break;
					default:
						break;
					}
				}

			}
			freesize = 100 - (imgsize + videosize + pdfsize + pptsize);
			map.put("free", df.format(freesize));

		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside Dashboard service getPercentageOfContent method:::::",
					e);
		}
		return map;
	}

	/**
	 * Get count of active users,course active users,test active users in last n
	 * months
	 * 
	 * @param userId
	 * @param lastNMonths
	 * @return map
	 */
	public Map<String, Object> getTotalActiveUsersStatistics(Integer userId,
			Integer lastNMonths) {
		logger.log(Level.DEBUG,
				"Inside Dashboard service getTotalActiveUsersStatistics method:::::");
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> activeUser = new ArrayList<String>();
		List<String> courseActiveUser = new ArrayList<String>();
		List<String> testActiveUser = new ArrayList<String>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		for (int i = 0; i < lastNMonths; i++) {
			Calendar l_calendar = Calendar.getInstance();
			if (i == 0) {
				l_calendar.set(Calendar.MONTH, l_calendar.get(Calendar.MONTH));
			} else {
				l_calendar.set(Calendar.MONTH, l_calendar.get(Calendar.MONTH)
						- i);
			}

			l_calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date startDate = l_calendar.getTime();

			startDate = QbisUtils.convertDatetoBOD(startDate);

			l_calendar.set(Calendar.DATE,
					l_calendar.getActualMaximum(Calendar.DATE));
			Date endDate = l_calendar.getTime();

			endDate = QbisUtils.convertDatetoEOD(endDate);

			try {
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_TOTAL_ACTIVE_USERS_BY_MONTH,
						new Object[] { userId, formatter.format(startDate),
								formatter.format(endDate) });
				if (data.getRows().size() > 0) {
					for (QueryData.Row row : data.getRows()) {
						activeUser.add(new SimpleDateFormat("MMM")
								.format(l_calendar.getTime())
								+ "#"
								+ row.getRowItem(0));
					}
				}

			} catch (Exception e) {
				logger.log(
						Level.ERROR,
						"Exception Inside Dashboard service getTotalActiveUsersStatistics method:::::",
						e);
			}

			try {
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_TEST_ACTIVE_USERS_BY_MONTH,
						new Object[] { userId, formatter.format(startDate),
								formatter.format(endDate) });
				if (data.getRows().size() > 0) {
					for (QueryData.Row row : data.getRows()) {
						testActiveUser.add(row.getRowItem(0));
					}
				}

			} catch (Exception e) {
				logger.log(
						Level.ERROR,
						"Exception Inside Dashboard service getTotalActiveUsersStatistics method:::::",
						e);
			}

			try {
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_COURSE_ACTIVE_USERS_BY_MONTH,
						new Object[] { userId, formatter.format(startDate),
								formatter.format(endDate) });
				if (data.getRows().size() > 0) {
					for (QueryData.Row row : data.getRows()) {
						courseActiveUser.add(row.getRowItem(0));
					}
				}

			} catch (Exception e) {
				logger.log(
						Level.ERROR,
						"Exception Inside Dashboard service getTotalActiveUsersStatistics method:::::",
						e);
			}

		}
		Collections.reverse(activeUser);
		Collections.reverse(testActiveUser);
		Collections.reverse(courseActiveUser);
		result.put("activeUser", activeUser);
		result.put("courseActiveUser", courseActiveUser);
		result.put("testActiveUser", testActiveUser);
		return result;
	}
}
