package com.qbis.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.StudentEnum;
import com.qbis.model.Option;
import com.qbis.model.Question;
import com.qbis.model.QuestionSetting;
import com.qbis.model.Section;
import com.qbis.model.Test;
import com.qbis.model.UserSectionAttempt;
import com.qbis.model.UserTestAttempt;

/**
 * This is used for performing all operations related to test on student side.
 * 
 * @author Ankur Kumar
 *
 */

@Component
public class StudentTestService {
	/**
	 * Instance of {@linl Logger}
	 */
	private static final Logger logger = Logger.getLogger(StudentTestService.class);

	/**
	 * A Method for getting list of all published test.
	 * 
	 * @param offset
	 * @return Map<String,Object>
	 */
	@Autowired
	private CourseService courseService;

	public Map<String, Object> getTestList(Integer userId, int offset) {
		logger.log(Level.DEBUG, "Inside Student Test Service getTestList method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Test> list = new ArrayList<Test>();
		Test test;
		int skip = offset * ConstantUtil.LIMIT;
		Integer organizationId = UserService.getOrgId(userId);
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TEST_LIST,
					new Object[] { organizationId, userId, ConstantUtil.LIMIT, skip });
			for (QueryData.Row row : data.getRows()) {
				test = new Test();
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestTime(row.getRowItem(2));
				test.setTotalQuestion(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : 0);
				test.setTeacherName(row.getRowItem(16) == null ? row.getRowItem(4) : row.getRowItem(16));
				test.setTestCreatedDate(row.getRowItem(5));
				test.setMaxAttempts(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
				test.setTestDesc(row.getRowItem(7));
				test.setTestTag(row.getRowItem(8));
				test.setTestInstruct(row.getRowItem(9));
				test.setIsPublic(Integer.parseInt(row.getRowItem(10)));
				test.setIsReview(Integer.parseInt(row.getRowItem(11)));
				test.setReviewWithCorrect(row.getRowItem(12) != null ? Integer.parseInt(row.getRowItem(12)) : null);
				test.setNegMark((row.getRowItem(13) != null ? Integer.parseInt(row.getRowItem(13)) : 0));
				test.setMaxMark(Double.parseDouble(row.getRowItem(14)));
				test.setTestPause(Integer.parseInt(row.getRowItem(15)));
				test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(17));
				list.add(test);
			}
			if (data.getRows().size() > 0) {
				map.put("status", 200);
				map.put("msg", "Test List");
				map.put("testlist", list);
			} else {
				map.put("status", 201);
				map.put("msg", "No data available");
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getTestList method ::::::", e);
			map.put("status", 401);
			map.put("msg", "Operation failed");
		}
		return map;
	}

	/**
	 * A Method for getting list of upcoming test.
	 * 
	 * @param offset
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getUpcomingTestList(Integer userId, int offset) {
		logger.log(Level.DEBUG, "Inside Student Test Service getUpcomingTestList method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Test> list = new ArrayList<Test>();
		Test test;
		int skip = offset * ConstantUtil.LIMIT;
		Integer organizationId = UserService.getOrgId(userId);
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_UPCOMING_TEST_LIST,
					new Object[] { organizationId, userId, ConstantUtil.LIMIT, skip });
			for (QueryData.Row row : data.getRows()) {
				test = new Test();
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestTime(row.getRowItem(2));
				test.setTotalQuestion(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : 0);
				test.setTeacherName(row.getRowItem(16) == null ? row.getRowItem(4) : row.getRowItem(16));
				test.setSchedulePublishDate(row.getRowItem(5));
				test.setMaxAttempts(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
				test.setTestDesc(row.getRowItem(7));
				test.setTestTag(row.getRowItem(8));
				test.setTestInstruct(row.getRowItem(9));
				test.setIsPublic(Integer.parseInt(row.getRowItem(10)));
				test.setIsReview(Integer.parseInt(row.getRowItem(11)));
				test.setReviewWithCorrect(row.getRowItem(12) != null ? Integer.parseInt(row.getRowItem(12)) : null);
				test.setNegMark((row.getRowItem(13) != null ? Integer.parseInt(row.getRowItem(13)) : 0));
				test.setMaxMark(Double.parseDouble(row.getRowItem(14)));
				test.setTestPause(Integer.parseInt(row.getRowItem(15)));
				test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(17));
				list.add(test);
			}
			if (data.getRows().size() > 0) {
				map.put("status", 200);
				map.put("msg", "Test List");
				map.put("testlist", list);
			} else {
				map.put("status", 201);
				map.put("msg", "No data available");
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getUpcomingTestList method ::::::", e);
			map.put("status", 401);
			map.put("msg", "Operation failed");
		}
		return map;
	}

	/**
	 * A Method for getting question list. not used
	 * 
	 * @param testId
	 * @param accessToken
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getQuestionDetail(int testId, String accessToken) {
		logger.log(Level.DEBUG, "Inside Student Test Service getQuestionDetail method ::::::");
		List<Question> questionlist = new ArrayList<Question>();
		Map<String, Object> map = new HashMap<String, Object>();
		int userId = 0;
		try {
			QueryData userIddata = QueryManager.execuateQuery(QueryStrings.FIND_USER_ID, new Object[] { accessToken });
			for (QueryData.Row useridrow : userIddata.getRows()) {
				userId = Integer.parseInt(useridrow.getRowItem(0));
			}
			if (userId > 0) {
				Integer userTestAttemptId = QueryManager.execuateUpdate(QueryStrings.TEST_PROGRESS,
						new Object[] { userId, testId });
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUES_LIST_BY_TEST_ID,
						new Object[] { testId });
				for (QueryData.Row row : data.getRows()) {
					Question question = new Question();
					List<Integer> answerStatus = new ArrayList<Integer>();
					question.setUserTestAttemptId(userTestAttemptId);
					question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
					question.setQuestionName(row.getRowItem(1));
					question.setExplanation(row.getRowItem(2));
					question.setQuestionNo(Integer.parseInt(row.getRowItem(3)));
					question.setQuestionMark(Integer.parseInt(row.getRowItem(4)));
					question.setQuestionType(Integer.parseInt(row.getRowItem(5)));
					question.setNegMark(
							(Float.parseFloat(row.getRowItem(4)) * Integer.parseInt(row.getRowItem(6))) / 100);
					QueryData optionData = QueryManager.execuateQuery(QueryStrings.GET_OPTION_LIST_BY_QUES_ID,
							new Object[] { question.getQuestionId() });
					Option option[] = new Option[optionData.getRows().size()];
					int i = 0;
					for (QueryData.Row optionRow : optionData.getRows()) {
						option[i] = new Option();
						option[i].setOptionId(Integer.parseInt(optionRow.getRowItem(0)));
						option[i].setOptionName(optionRow.getRowItem(1));
						if (Integer.parseInt(optionRow.getRowItem(2)) == 1) {
							answerStatus.add(option[i].getOptionId());
						}
						option[i].setOptionOrder(Integer.parseInt(optionRow.getRowItem(3)));
						i++;
					}
					question.setCorrectAnswer(answerStatus);
					question.setOption(option);
					questionlist.add(question);
					map.put("questions", questionlist);
				}
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(Level.ERROR, "Exception Inside Student Test Service getQuestionDetail method ::::::", e);
		}
		return map;
	}

	/**
	 * A Method for getting question list with all section for given test.
	 * 
	 * @param testId
	 * @param userId
	 * @return Test
	 */
	public Test testDetailforGivenTest(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service testDetailforGivenTest method ::::::");
		Test test = new Test();
		QuestionService questionService = new QuestionService();
		ObjectMapper mapper = new ObjectMapper();
		try {
			QueryData testdata = QueryManager.execuateQuery(QueryStrings.FIND_ONE_TEST, new Object[] { testId });
			for (QueryData.Row testRow : testdata.getRows()) {
				test.setTestId(Integer.parseInt(testRow.getRowItem(0)));
				test.setTestTime(testRow.getRowItem(7));
				test.setTestPause(Integer.parseInt(testRow.getRowItem(11)));
				test.setShuffleSection(testRow.getRowItem(19) != null ? Integer.parseInt(testRow.getRowItem(19)) : 0);
				test.setShuffleQuestion(testRow.getRowItem(20) != null ? Integer.parseInt(testRow.getRowItem(20)) : 0);
				test.setShuffleOption(testRow.getRowItem(21) != null ? Integer.parseInt(testRow.getRowItem(21)) : 0);
			}
			if (test.getTestId() != null) {
				Integer userTestAttemptId = QueryManager.execuateUpdate(QueryStrings.TEST_PROGRESS,
						new Object[] { userId, testId });
				test.setUserTestAttemptId(userTestAttemptId);

				QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTION_LIST_BY_TEST_ID,
						new Object[] { testId });
				Section section[] = new Section[sectiondata.getRows().size()];
				int sec = 0;
				for (QueryData.Row secRow : sectiondata.getRows()) {
					section[sec] = new Section();
					section[sec].setSectionId(Integer.parseInt(secRow.getRowItem(0)));
					section[sec].setSectionName(secRow.getRowItem(1));
					QueryData quesdata = QueryManager.execuateQuery(QueryStrings.GET_QUES_LIST_BY_SECTION_ID,
							new Object[] { section[sec].getSectionId() });
					Question question[] = new Question[quesdata.getRows().size()];
					int ques = 0;
					for (QueryData.Row row : quesdata.getRows()) {
						question[ques] = new Question();
						question[ques].setQuestionId(Integer.parseInt(row.getRowItem(0)));
						question[ques].setQuestionName(row.getRowItem(1));
						question[ques].setExplanation(row.getRowItem(2));
						question[ques].setQuestionNo(Integer.parseInt(row.getRowItem(3)));
						question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(4)));
						question[ques].setQuestionType(Integer.parseInt(row.getRowItem(5)));
						question[ques].setNegMark(row.getRowItem(6) == null ? 0 : Float.parseFloat(row.getRowItem(6)));
						question[ques].setIsFormula(Integer.parseInt(row.getRowItem(7)));
						question[ques].setMathFormula(row.getRowItem(8));
						/**
						 * checking that question setting is not null.
						 */
						if (row.getRowItem(10) != null) {
							QuestionSetting questionSetting = mapper.readValue(row.getRowItem(10),
									new TypeReference<QuestionSetting>() {
									});
							question[ques].setQuestionSetting(questionSetting);
						}
						QueryData favData = QueryManager.execuateQuery(QueryStrings.CHECK_FAVORITE_QUES,
								new Object[] { question[ques].getQuestionId(), userId });

						if (favData.getRows().size() > 0) {
							for (QueryData.Row favrow : favData.getRows()) {
								question[ques].setFavoriateId(Integer.parseInt(favrow.getRowItem(0)));
								question[ques].setFavorites(Integer.parseInt(favrow.getRowItem(1)));
							}
						} else {
							question[ques].setFavoriateId(0);
						}
						switch (question[ques].getQuestionType()) {
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[ques]);
							break;
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[ques]);
							break;
						case ConstantUtil.SORT_LIST_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[ques]);
							break;
						case ConstantUtil.CHOICE_MATRIX_TYPE:
							questionService.getOptionForChoiceMatrixQuestion(question[ques]);
							break;
						case ConstantUtil.CLASSIFICATION_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[ques]);
							break;
						case ConstantUtil.MATCH_LIST:
							questionService.getOptionForMultiChoiceQuestion(question[ques]);
							break;
						}
						ques++;
					}
					if (test.getShuffleQuestion() == 1) {
						Collections.shuffle(Arrays.asList(question));
					}
					section[sec].setQuestionList(question);
					sec++;
				}
				test.setSectionlist(section);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service testDetailforGivenTest method ::::::", e);
		}
		return test;
	}

	/**
	 * A Method for getting question list with all section for given test.
	 * 
	 * @param testId
	 * @param userId
	 * @param testAttemptId
	 * @return Test
	 */
	public Test testDetailforGivenTest(int testId, int userId, Integer testAttemptId) {
		logger.log(Level.DEBUG, "Inside Student Test Service testDetailforGivenTest method ::::::");
		Test test = new Test();
		QuestionService questionService = new QuestionService();
		ObjectMapper mapper = new ObjectMapper();
		try {
			QueryData testdata = QueryManager.execuateQuery(QueryStrings.FIND_ONE_TEST, new Object[] { testId });
			for (QueryData.Row testRow : testdata.getRows()) {
				test.setTestId(Integer.parseInt(testRow.getRowItem(0)));
				test.setTestTime(testRow.getRowItem(7));
				test.setTestPause(Integer.parseInt(testRow.getRowItem(11)));
				test.setShuffleSection(testRow.getRowItem(19) != null ? Integer.parseInt(testRow.getRowItem(19)) : 0);
				test.setShuffleQuestion(testRow.getRowItem(20) != null ? Integer.parseInt(testRow.getRowItem(20)) : 0);
				test.setShuffleOption(testRow.getRowItem(21) != null ? Integer.parseInt(testRow.getRowItem(21)) : 0);
				test.setIsRandom(testRow.getRowItem(22) != null ? Integer.parseInt(testRow.getRowItem(22)) : 0);
				System.out.println("test random " + test.getIsRandom() + "testId" + testId);
			}
			if (test.getTestId() != null) {
				if (test.getIsRandom() != 1) {
					if (testAttemptId != null) {
						test.setUserTestAttemptId(testAttemptId);
					} else {
						Integer userTestAttemptId = QueryManager.execuateUpdate(QueryStrings.TEST_PROGRESS,
								new Object[] { userId, testId });
						test.setUserTestAttemptId(userTestAttemptId);
					}
					QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTION_LIST_BY_TEST_ID,
							new Object[] { testId });
					Section section[] = new Section[sectiondata.getRows().size()];
					int sec = 0;
					for (QueryData.Row secRow : sectiondata.getRows()) {
						section[sec] = new Section();
						section[sec].setSectionId(Integer.parseInt(secRow.getRowItem(0)));
						section[sec].setSectionName(secRow.getRowItem(1));
						QueryData quesdata = QueryManager.execuateQuery(QueryStrings.GET_QUES_LIST_BY_SECTION_ID,
								new Object[] { section[sec].getSectionId() });
						Question question[] = new Question[quesdata.getRows().size()];
						int ques = 0;
						for (QueryData.Row row : quesdata.getRows()) {
							question[ques] = new Question();
							question[ques].setQuestionId(Integer.parseInt(row.getRowItem(0)));
							question[ques].setQuestionName(row.getRowItem(1));
							question[ques].setExplanation(row.getRowItem(2));
							question[ques].setQuestionNo(Integer.parseInt(row.getRowItem(3)));
							question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(4)));
							question[ques].setQuestionType(Integer.parseInt(row.getRowItem(5)));
							question[ques]
									.setNegMark(row.getRowItem(6) == null ? 0 : Float.parseFloat(row.getRowItem(6)));
							question[ques].setIsFormula(Integer.parseInt(row.getRowItem(7)));
							question[ques].setMathFormula(row.getRowItem(8));
							/**
							 * checking that question setting is not null.
							 */
							if (row.getRowItem(10) != null) {
								QuestionSetting questionSetting = mapper.readValue(row.getRowItem(10),
										new TypeReference<QuestionSetting>() {
										});
								question[ques].setQuestionSetting(questionSetting);
							}
							QueryData favData = QueryManager.execuateQuery(QueryStrings.CHECK_FAVORITE_QUES,
									new Object[] { question[ques].getQuestionId(), userId });

							if (favData.getRows().size() > 0) {
								for (QueryData.Row favrow : favData.getRows()) {
									question[ques].setFavoriateId(Integer.parseInt(favrow.getRowItem(0)));
									question[ques].setFavorites(Integer.parseInt(favrow.getRowItem(1)));
								}
							} else {
								question[ques].setFavoriateId(0);
							}
							switch (question[ques].getQuestionType()) {
							case ConstantUtil.MULTIPLE_CHOICE_TYPE:
								questionService.getOptionForMultiChoiceQuestion(question[ques]);
								break;
							case ConstantUtil.SINGLE_CHOICE_TYPE:
								questionService.getOptionForMultiChoiceQuestion(question[ques]);
								break;
							case ConstantUtil.SORT_LIST_TYPE:
								questionService.getOptionForMultiChoiceQuestion(question[ques]);
								break;
							case ConstantUtil.CHOICE_MATRIX_TYPE:
								questionService.getOptionForChoiceMatrixQuestion(question[ques]);
								break;
							case ConstantUtil.CLASSIFICATION_TYPE:
								questionService.getOptionForMultiChoiceQuestion(question[ques]);
								break;
							case ConstantUtil.MATCH_LIST:
								questionService.getOptionForMultiChoiceQuestion(question[ques]);
								break;
							}
							ques++;
						}
						if (test.getShuffleQuestion() == 1) {
							Collections.shuffle(Arrays.asList(question));
						}
						section[sec].setQuestionList(question);
						sec++;
					}
					test.setSectionlist(section);
				}

				else {
					System.out.println("hiiii-----------------------");
					List<String> allValue = courseService.getTagList(testId);
					List<Question> questionList = courseService.getRandomQuestion(allValue, testId);
					if (testAttemptId != null) {
						test.setUserTestAttemptId(testAttemptId);
					} else {
						Integer userTestAttemptId = QueryManager.execuateUpdate(QueryStrings.TEST_PROGRESS,
								new Object[] { userId, testId });
						test.setUserTestAttemptId(userTestAttemptId);
					}

					System.out.println("AttemptId=" + testAttemptId);
					System.out.println("Test id=" + testId);

					QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTION_DETAIL,
							new Object[] { testId });
					Section section[] = new Section[sectiondata.getRows().size()];
					int sec = 0;

					for (QueryData.Row secRow : sectiondata.getRows()) {
						section[sec] = new Section();
						section[sec].setSectionId(Integer.parseInt(secRow.getRowItem(0)));
						section[sec].setSectionName(secRow.getRowItem(1));

						section[sec].setQuestionList(questionList.toArray((new Question[questionList.size()])));
						sec++;
					}
					test.setSectionlist(section);

				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service testDetailforGivenTest method ::::::", e);
		}
		return test;
	}

	/**
	 * This is used for saving the test's json.
	 * 
	 * @param test
	 * @return Test
	 */
	public Test saveGivenTestJson(Test test) {
		logger.log(Level.DEBUG, "Inside Student Test Service saveGivenTestJson method ::::::");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonObject = mapper.writeValueAsString(test.getAnswerJson());
			QueryManager.execuateUpdate(QueryStrings.SAVE_GIVING_TEST_JSON,
					new Object[] { jsonObject, test.getUserTestAttemptId() });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service saveGivenTestJson method ::::::", e);
		}
		return test;
	}

	/**
	 * A method for getting result of all attempted tests by a particular user.
	 * 
	 * @param userTestAttemptId
	 * @param userId
	 * @return UserTestAttempt
	 */
	public UserTestAttempt getAttemptedTestResult(Integer userTestAttemptId, int userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getAttemptedTestResult method ::::::");
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPTED_TEST_RESULT,
					new Object[] { userTestAttemptId, userId });

			for (QueryData.Row row : data.getRows()) {
				userTestAttempt.setUserTestAttemptId(Integer.parseInt(row.getRowItem(0)));
				userTestAttempt
						.setCorrectQueAttempt(row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : 0);
				userTestAttempt.setWrongQueAttempt(row.getRowItem(2) != null ? Integer.parseInt(row.getRowItem(2)) : 0);
				userTestAttempt.setUnAttemptQue(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : 0);
				userTestAttempt.setTimeTaken(row.getRowItem(4));
				userTestAttempt.setTestGivenTime(row.getRowItem(5) != null ? row.getRowItem(5) : "");
				userTestAttempt.setObtainMarks(row.getRowItem(6) != null ? Float.parseFloat(row.getRowItem(6)) : 0);
				userTestAttempt.setTotalMarks(row.getRowItem(7) != null ? Integer.parseInt(row.getRowItem(7)) : 0);
				userTestAttempt.setTestId(Integer.parseInt(row.getRowItem(8)));
				userTestAttempt.setIsReview(row.getRowItem(9));
				userTestAttempt.setReviewWithCorrect(row.getRowItem(10));
				userTestAttempt.setTestName(row.getRowItem(11));
				double flag = (userTestAttempt.getObtainMarks() * 100) / userTestAttempt.getTotalMarks();

				if (flag < 33.0) {
					userTestAttempt.setGrade("Fail");
				} else {
					userTestAttempt.setGrade("Pass");
				}
				int totalque = userTestAttempt.getCorrectQueAttempt() + userTestAttempt.getWrongQueAttempt()
						+ userTestAttempt.getUnAttemptQue();
				userTestAttempt.setTotalQuestion(totalque);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getAttemptedTestResult method ::::::", e);
		}
		return userTestAttempt;
	}

	/**
	 * A method for getting latest five attempted test details.
	 * 
	 * @param userTestAttempt
	 * @param userId
	 * @return List<UserTestAttempt>
	 */
	public List<UserTestAttempt> getAttemptedTestHistory(UserTestAttempt userTestAttempt, int userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getAttemptedTestHistory method ::::::");
		List<UserTestAttempt> list = new ArrayList<UserTestAttempt>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPTED_TEST_HISTORY,
					new Object[] { userId, userTestAttempt.getTestId() });
			for (QueryData.Row row : data.getRows()) {
				userTestAttempt = new UserTestAttempt();
				userTestAttempt.setTestGivenTime(row.getRowItem(0));
				userTestAttempt
						.setCorrectQueAttempt(row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : 0);
				userTestAttempt.setUserTestAttemptId(Integer.parseInt(row.getRowItem(3)));
				double flag = Double.parseDouble(row.getRowItem(2));
				if (flag < 33.0) {
					userTestAttempt.setGrade("Fail");
				} else {
					userTestAttempt.setGrade("Pass");
				}

				list.add(userTestAttempt);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getAttemptedTestHistory method ::::::", e);
		}
		return list;
	}

	/**
	 * This is used for getting all attempted test's details.
	 * 
	 * @param userTestAttempt
	 * @param userId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getAttemptedTestSummary(UserTestAttempt userTestAttempt, int userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getAttemptedTestSummary method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<UserTestAttempt> list = new ArrayList<UserTestAttempt>();
		int countpass = 0;
		int countfail = 0;
		double avg = 0.0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ATTEMPTED_TEST_SUMMARY,
					new Object[] { userId, userTestAttempt.getTestId() });
			for (QueryData.Row row : data.getRows()) {
				userTestAttempt = new UserTestAttempt();
				userTestAttempt.setTestGivenTime(row.getRowItem(0));
				userTestAttempt.setCorrectQueAttempt(Integer.parseInt(row.getRowItem(1)));
				userTestAttempt.setUserTestAttemptId(Integer.parseInt(row.getRowItem(3)));
				double flag = Double.parseDouble(row.getRowItem(2));
				avg += flag;
				userTestAttempt.setPercentage(flag);
				if (flag < 33.0) {
					countfail++;
					userTestAttempt.setGrade("Fail");
				} else {
					countpass++;
					userTestAttempt.setGrade("Pass");
				}
				avg = avg >= 0 ? avg : 0;
				list.add(userTestAttempt);
				map.put("countpass", countpass);
				map.put("countfail", countfail);
				map.put("average", avg / list.size());
				map.put("attemptList", list);
				map.put("countattempt", list.size());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getAttemptedTestSummary method ::::::", e);
		}
		return map;
	}

	/**
	 * Submit given test Details by Student.
	 * 
	 * @param test
	 * @return UserTestAttempt
	 */
	public UserTestAttempt submitTestDetails(Test test) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitTestDetails method ::::::");
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		try {
			int unAttemptQue = 0;
			int totalQuestions = test.getTotalQuestion();
			int correctQuestion = 0;
			int wrongQuestion = 0;
			float correctQueScore = 0;
			float wrongQueScore = 0;
			float obtainMarks = 0.0f;
			int everyQuestionMark = 0;
			int maxQuestion = 0;
			int isRandom = 0;
			int negMark = 0;
			String testTitel = null;
			Integer sectionid = null;
			/**
			 * submit section details of attempted test.
			 */
			Section section[] = test.getSectionlist();

			QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_TEST_ID,
					new Object[] { test.getUserTestAttemptId(), test.getUserId() });

			for (QueryData.Row row : data1.getRows()) {
				test.setTestId((Integer.parseInt(row.getRowItem(0))));
			}

			QueryData data2 = QueryManager.execuateQuery(QueryStrings.GET_TEST_ID_DETAIL,
					new Object[] { test.getTestId() });
			for (QueryData.Row row : data2.getRows()) {
				everyQuestionMark = Integer.parseInt(row.getRowItem(0));
				maxQuestion = Integer.parseInt(row.getRowItem(1));
				isRandom = Integer.parseInt(row.getRowItem(2));
				negMark = Integer.parseInt(row.getRowItem(3));
				testTitel = row.getRowItem(4);
			}

			if (isRandom == 1) {
				/*
				 * List<String> allValue=courseService.getTagList(test.getTestId());
				 * List<Question> questionList =
				 * courseService.getRandomQuestion(allValue,test.getTestId());
				 */

				System.out.println("==================dataa" + section[0].getSectionName() + " " + test.getTestId()
						+ "=" + everyQuestionMark * maxQuestion + " " + 0);
				
				QueryData sectionId = QueryManager.execuateQuery(QueryStrings.GET_QBIS_RANDOM_SECTION_ID,
						new Object[] { section[0].getSectionId() });
				for (QueryData.Row row : sectionId.getRows()) {
					sectionid = Integer.parseInt(row.getRowItem(0));
				}
			
				int id = QueryManager.execuateUpdate(QueryStrings.INSERT_QBIS_SECTION,
						new Object[] { section[0].getSectionId(), "Random test", test.getTestId(),
								everyQuestionMark * maxQuestion, 0 });
				for (int sec = 0; sec < section.length; sec++) {
					Question question[] = section[sec].getQuestionList();
					for (int ques = 0; ques < question.length; ques++) {
						{
               System.out.println("hiiii");
							int secId1 = QueryManager.execuateUpdate(QueryStrings.INSERT_QUESTION_SECTION,
									new Object[] { section[0].getSectionId() , question[ques].getQuestionId(), ques + 1,
											everyQuestionMark, negMark,test.getTestId() });
						}
					}
				 }
				
				/*
				 * else { QueryManager.execuateUpdate(QueryStrings.UPDATE_QBIS_SECTION, new
				 * Object[] { section[0].getSectionId(), "Random test", test.getTestId(),
				 * everyQuestionMark * maxQuestion, 0 }); for (int sec = 0; sec <
				 * section.length; sec++) { Question question[] =
				 * section[sec].getQuestionList(); for (int ques = 0; ques < question.length;
				 * ques++) { {
				 * 
				 * int secId1 =
				 * QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_SECTION, new
				 * Object[] { sectionid, question[ques].getQuestionId(), ques + 1,
				 * everyQuestionMark, negMark }); } } } }
				 */

				/*
				 * QueryData sectionId =
				 * QueryManager.execuateQuery(QueryStrings.GET_RANDOM_SECTION_ID, new Object[] {
				 * id }); for (QueryData.Row row : sectionId.getRows()) { sectionid =
				 * Integer.parseInt(row.getRowItem(0)); }
				 */
				
				
					
				

				for (int sec = 0; sec < section.length; sec++) {

					/**
					 * save attempted section details
					 */
					System.out.println("inside loop=================");
					System.out.println("data save===" + test.getUserTestAttemptId() + "=" + section[sec].getSectionId()
							+ "=" + test.getUserId());
					Integer secId = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_SECTION_ATTEMPT, new Object[] {
							test.getUserTestAttemptId(), section[sec].getSectionId(), test.getUserId() });
					/**
					 * perform operations on successfully saved section's details.
					 */
					if (secId > 0) {
						System.out.println("section id====" + secId);
						/**
						 * UserSectionAttempt type object
						 */
						UserSectionAttempt userSectionAttempt = new UserSectionAttempt();
						/**
						 * set user' attempted section id.
						 */
						section[sec].setUserSectionAttemptId(secId);
						/**
						 * get every question details of this section.
						 */
						Question question[] = section[sec].getQuestionList();
						/**
						 * set required values.
						 */
						userSectionAttempt.setCorrectQueAttempt(0);
						userSectionAttempt.setCorrectQueScore(0.0f);
						userSectionAttempt.setWrongQueAttempt(0);
						userSectionAttempt.setWrongQueScore(0.0f);
						int totalQuestionsInSection = section[sec].getTotalQuestions();

						for (int ques = 0; ques < question.length; ques++) {
							/**
							 * Get given answer for a particular question in a section.
							 */
							List<Integer> givenAnswer = question[ques].getGivenAnswer();

							/**
							 * fetching question details like marks e.t.c from test based on question id and
							 * section id.
							 */
							System.out.println("questionId1 ========" + question[ques].getQuestionId());
							QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_DETAIL1,
									new Object[] { question[ques].getQuestionId(), section[sec].getSectionId() });

							for (QueryData.Row row : data.getRows()) {
								question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(1)));
								question[ques].setNegMark(
										row.getRowItem(2) == null ? 0 : Float.parseFloat(row.getRowItem(2)));
								question[ques].setQuestionType(Integer.parseInt(row.getRowItem(3)));
								question[ques].setAnswerValue(row.getRowItem(4));
							}
							/**
							 * perform action according to question type.
							 */
							switch (question[ques].getQuestionType()) {
							/**
							 * if question is multiple choice type
							 */
							case ConstantUtil.MULTIPLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									System.out.println("questionId ========" + question[ques].getQuestionId());
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is single choice type.
							 */
							case ConstantUtil.SINGLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is sort list type.
							 */
							case ConstantUtil.SORT_LIST_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									submitSortListTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;

							/**
							 * If question is choice matrix type.
							 */
							case ConstantUtil.CHOICE_MATRIX_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedChoiceMatrixQuestion(question[ques].getChoiceMatrixGivenAnswer())) {
									submitChoiceMatrixTypeQuestionResult(section[sec], question[ques],
											question[ques].getChoiceMatrixGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * If question is classification type.
							 */
							case ConstantUtil.CLASSIFICATION_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedClassificationQuestion(question[ques].getUserGivenAnswer())) {
									submitClassificationTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * if question is match list type.
							 */
							case ConstantUtil.MATCH_LIST:
								/**
								 * checking If user attempted this question.
								 */
								if (isAttemptedMatchListQuestion(question[ques].getUserGivenAnswer())) {
									submitMatchListTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							}
						}

						/**
						 * update attempted section related details.
						 */
						userSectionAttempt.setUserSectionAttemptId(section[sec].getUserSectionAttemptId());
						userSectionAttempt.setObtainMarks(
								userSectionAttempt.getCorrectQueScore() - userSectionAttempt.getWrongQueScore());
						userSectionAttempt
								.setUnAttemptQue(totalQuestionsInSection - (userSectionAttempt.getCorrectQueAttempt()
										+ userSectionAttempt.getWrongQueAttempt()));
						userSectionAttempt.setSectionTakenTime(0);
						userSectionAttempt.setSubmitStatus(1);
						/**
						 * update attempted section details.
						 */
						updateAttemptedSection(userSectionAttempt);
						/**
						 * increase given test details w.r.t section.
						 */
						correctQueScore = correctQueScore + userSectionAttempt.getCorrectQueScore();
						wrongQueScore = wrongQueScore + userSectionAttempt.getWrongQueScore();
						correctQuestion = correctQuestion + userSectionAttempt.getCorrectQueAttempt();
						wrongQuestion = wrongQuestion + userSectionAttempt.getWrongQueAttempt();

					}
				}

			} else {
				for (int sec = 0; sec < section.length; sec++) {

					/**
					 * save attempted section details
					 */
					System.out.println("inside loop else=================");
					System.out.println("data save===" + test.getUserTestAttemptId() + "=" + section[sec].getSectionId()
							+ "=" + test.getUserId());
					Integer secId = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_SECTION_ATTEMPT, new Object[] {
							test.getUserTestAttemptId(), section[sec].getSectionId(), test.getUserId() });
					/**
					 * perform operations on successfully saved section's details.
					 */
					if (secId > 0) {
						System.out.println("section id====" + secId);
						/**
						 * UserSectionAttempt type object
						 */
						UserSectionAttempt userSectionAttempt = new UserSectionAttempt();
						/**
						 * set user' attempted section id.
						 */
						section[sec].setUserSectionAttemptId(secId);
						/**
						 * get every question details of this section.
						 */
						Question question[] = section[sec].getQuestionList();
						/**
						 * set required values.
						 */
						userSectionAttempt.setCorrectQueAttempt(0);
						userSectionAttempt.setCorrectQueScore(0.0f);
						userSectionAttempt.setWrongQueAttempt(0);
						userSectionAttempt.setWrongQueScore(0.0f);
						int totalQuestionsInSection = section[sec].getTotalQuestions();

						for (int ques = 0; ques < question.length; ques++) {
							/**
							 * Get given answer for a particular question in a section.
							 */
							List<Integer> givenAnswer = question[ques].getGivenAnswer();

							/**
							 * fetching question details like marks e.t.c from test based on question id and
							 * section id.
							 */
							System.out.println("=======questionId1=" + question[ques].getQuestionId());
							QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_DETAIL,
									new Object[] { question[ques].getQuestionId(), section[sec].getSectionId() });

							for (QueryData.Row row : data.getRows()) {
								question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(1)));
								question[ques].setNegMark(
										row.getRowItem(2) == null ? 0 : Float.parseFloat(row.getRowItem(2)));
								question[ques].setQuestionType(Integer.parseInt(row.getRowItem(3)));
								question[ques].setAnswerValue(row.getRowItem(4));
							}
							/**
							 * perform action according to question type.
							 */
							switch (question[ques].getQuestionType()) {
							/**
							 * if question is multiple choice type
							 */
							case ConstantUtil.MULTIPLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									System.out.println("=======questionId=" + question[ques].getQuestionId());
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is single choice type.
							 */
							case ConstantUtil.SINGLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is sort list type.
							 */
							case ConstantUtil.SORT_LIST_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									submitSortListTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;

							/**
							 * If question is choice matrix type.
							 */
							case ConstantUtil.CHOICE_MATRIX_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedChoiceMatrixQuestion(question[ques].getChoiceMatrixGivenAnswer())) {
									submitChoiceMatrixTypeQuestionResult(section[sec], question[ques],
											question[ques].getChoiceMatrixGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * If question is classification type.
							 */
							case ConstantUtil.CLASSIFICATION_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedClassificationQuestion(question[ques].getUserGivenAnswer())) {
									submitClassificationTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * if question is match list type.
							 */
							case ConstantUtil.MATCH_LIST:
								/**
								 * checking If user attempted this question.
								 */
								if (isAttemptedMatchListQuestion(question[ques].getUserGivenAnswer())) {
									submitMatchListTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							}
						}

						/**
						 * update attempted section related details.
						 */
						userSectionAttempt.setUserSectionAttemptId(section[sec].getUserSectionAttemptId());
						userSectionAttempt.setObtainMarks(
								userSectionAttempt.getCorrectQueScore() - userSectionAttempt.getWrongQueScore());
						userSectionAttempt
								.setUnAttemptQue(totalQuestionsInSection - (userSectionAttempt.getCorrectQueAttempt()
										+ userSectionAttempt.getWrongQueAttempt()));
						userSectionAttempt.setSectionTakenTime(0);
						userSectionAttempt.setSubmitStatus(1);
						/**
						 * update attempted section details.
						 */
						updateAttemptedSection(userSectionAttempt);
						/**
						 * increase given test details w.r.t section.
						 */
						correctQueScore = correctQueScore + userSectionAttempt.getCorrectQueScore();
						wrongQueScore = wrongQueScore + userSectionAttempt.getWrongQueScore();
						correctQuestion = correctQuestion + userSectionAttempt.getCorrectQueAttempt();
						wrongQuestion = wrongQuestion + userSectionAttempt.getWrongQueAttempt();

					}
				}
			}

			obtainMarks = correctQueScore - wrongQueScore;
			System.err.println("obtained marks" + (Float) obtainMarks);
			unAttemptQue = totalQuestions - (correctQuestion + wrongQuestion);
			userTestAttempt.setUserTestAttemptId(test.getUserTestAttemptId());
			userTestAttempt.setCorrectQueAttempt(correctQuestion);
			userTestAttempt.setWrongQueAttempt(wrongQuestion);
			userTestAttempt.setCorrectQueScore(correctQueScore);
			userTestAttempt.setWrongQueScore(wrongQueScore);
			userTestAttempt.setObtainMarks((Float) obtainMarks);
			userTestAttempt.setUnAttemptQue(unAttemptQue);
			userTestAttempt.setTestTakenTime(Integer.parseInt(test.getTestTime()));
			userTestAttempt.setSubmitStatus(test.getTestStatus());
			userTestAttempt.setEveryquestionmark(everyQuestionMark);
			/**
			 * update test details.
			 */
			updateSubmitTest(userTestAttempt);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service submitTestDetails method ::::::", e);
		}
		return userTestAttempt;
	}

	public UserTestAttempt submitTestDetails1(Test test,Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitTestDetails1 method ::::::");
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		try {
			int unAttemptQue = 0;
			int totalQuestions = test.getTotalQuestion();
			int correctQuestion = 0;
			int wrongQuestion = 0;
			float correctQueScore = 0;
			float wrongQueScore = 0;
			float obtainMarks = 0.0f;
			int everyQuestionMark = 0;
			int maxQuestion = 0;
			int isRandom = 0;
			int negMark = 0;
			String testTitel = null;
			Integer sectionid = null;
			/**
			 * submit section details of attempted test.
			 */
			Section section[] = test.getSectionlist();

			QueryData data1 = QueryManager.execuateQuery(QueryStrings.GET_TEST_ID,
					new Object[] { test.getUserTestAttemptId(), test.getUserId() });

			for (QueryData.Row row : data1.getRows()) {
				test.setTestId((Integer.parseInt(row.getRowItem(0))));
			}

			QueryData data2 = QueryManager.execuateQuery(QueryStrings.GET_TEST_ID_DETAIL,
					new Object[] { test.getTestId() });
			for (QueryData.Row row : data2.getRows()) {
				everyQuestionMark = Integer.parseInt(row.getRowItem(0));
				maxQuestion = Integer.parseInt(row.getRowItem(1));
				isRandom = Integer.parseInt(row.getRowItem(2));
				negMark = Integer.parseInt(row.getRowItem(3));
				testTitel = row.getRowItem(4);
			}

			if (isRandom == 1) {
				/*
				 * List<String> allValue=courseService.getTagList(test.getTestId());
				 * List<Question> questionList =
				 * courseService.getRandomQuestion(allValue,test.getTestId());
				 */

				System.out.println("==================dataa" + section[0].getSectionName() + " " + test.getTestId()
						+ "=" + everyQuestionMark * maxQuestion + " " + 0);
				
				QueryData sectionId = QueryManager.execuateQuery(QueryStrings.GET_QBIS_RANDOM_SECTION_ID,
						new Object[] { section[0].getSectionId() });
				for (QueryData.Row row : sectionId.getRows()) {
					sectionid = Integer.parseInt(row.getRowItem(0));
				}
			
				int id = QueryManager.execuateUpdate(QueryStrings.INSERT_QBIS_SECTION1,
						new Object[] { section[0].getSectionId(), "Random test", test.getTestId(),
								everyQuestionMark * maxQuestion, 0,userId });
				for (int sec = 0; sec < section.length; sec++) {
					Question question[] = section[sec].getQuestionList();
					for (int ques = 0; ques < question.length; ques++) {
						{
               System.out.println("hiiii");
							int secId1 = QueryManager.execuateUpdate(QueryStrings.INSERT_QUESTION_SECTION1,
									new Object[] { section[0].getSectionId() , question[ques].getQuestionId(), ques + 1,
											everyQuestionMark, negMark,userId,test.getTestId() });
						}
					}
				 }
				
				/*
				 * else { QueryManager.execuateUpdate(QueryStrings.UPDATE_QBIS_SECTION, new
				 * Object[] { section[0].getSectionId(), "Random test", test.getTestId(),
				 * everyQuestionMark * maxQuestion, 0 }); for (int sec = 0; sec <
				 * section.length; sec++) { Question question[] =
				 * section[sec].getQuestionList(); for (int ques = 0; ques < question.length;
				 * ques++) { {
				 * 
				 * int secId1 =
				 * QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_SECTION, new
				 * Object[] { sectionid, question[ques].getQuestionId(), ques + 1,
				 * everyQuestionMark, negMark }); } } } }
				 */

				/*
				 * QueryData sectionId =
				 * QueryManager.execuateQuery(QueryStrings.GET_RANDOM_SECTION_ID, new Object[] {
				 * id }); for (QueryData.Row row : sectionId.getRows()) { sectionid =
				 * Integer.parseInt(row.getRowItem(0)); }
				 */
				
				
					
				

				for (int sec = 0; sec < section.length; sec++) {

					/**
					 * save attempted section details
					 */
					System.out.println("inside loop=================");
					System.out.println("data save===" + test.getUserTestAttemptId() + "=" + section[sec].getSectionId()
							+ "=" + test.getUserId());
					Integer secId = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_SECTION_ATTEMPT, new Object[] {
							test.getUserTestAttemptId(), section[sec].getSectionId(), test.getUserId() });
					/**
					 * perform operations on successfully saved section's details.
					 */
					if (secId > 0) {
						System.out.println("section id====" + secId);
						/**
						 * UserSectionAttempt type object
						 */
						UserSectionAttempt userSectionAttempt = new UserSectionAttempt();
						/**
						 * set user' attempted section id.
						 */
						section[sec].setUserSectionAttemptId(secId);
						/**
						 * get every question details of this section.
						 */
						Question question[] = section[sec].getQuestionList();
						/**
						 * set required values.
						 */
						userSectionAttempt.setCorrectQueAttempt(0);
						userSectionAttempt.setCorrectQueScore(0.0f);
						userSectionAttempt.setWrongQueAttempt(0);
						userSectionAttempt.setWrongQueScore(0.0f);
						int totalQuestionsInSection = section[sec].getTotalQuestions();

						for (int ques = 0; ques < question.length; ques++) {
							/**
							 * Get given answer for a particular question in a section.
							 */
							List<Integer> givenAnswer = question[ques].getGivenAnswer();

							/**
							 * fetching question details like marks e.t.c from test based on question id and
							 * section id.
							 */
							System.out.println("questionId1 ========" + question[ques].getQuestionId());
							QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_DETAIL1,
									new Object[] { question[ques].getQuestionId(), section[sec].getSectionId() });

							for (QueryData.Row row : data.getRows()) {
								question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(1)));
								question[ques].setNegMark(
										row.getRowItem(2) == null ? 0 : Float.parseFloat(row.getRowItem(2)));
								question[ques].setQuestionType(Integer.parseInt(row.getRowItem(3)));
								question[ques].setAnswerValue(row.getRowItem(4));
							}
							/**
							 * perform action according to question type.
							 */
							switch (question[ques].getQuestionType()) {
							/**
							 * if question is multiple choice type
							 */
							case ConstantUtil.MULTIPLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									System.out.println("questionId ========" + question[ques].getQuestionId());
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is single choice type.
							 */
							case ConstantUtil.SINGLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is sort list type.
							 */
							case ConstantUtil.SORT_LIST_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									submitSortListTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;

							/**
							 * If question is choice matrix type.
							 */
							case ConstantUtil.CHOICE_MATRIX_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedChoiceMatrixQuestion(question[ques].getChoiceMatrixGivenAnswer())) {
									submitChoiceMatrixTypeQuestionResult(section[sec], question[ques],
											question[ques].getChoiceMatrixGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * If question is classification type.
							 */
							case ConstantUtil.CLASSIFICATION_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedClassificationQuestion(question[ques].getUserGivenAnswer())) {
									submitClassificationTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * if question is match list type.
							 */
							case ConstantUtil.MATCH_LIST:
								/**
								 * checking If user attempted this question.
								 */
								if (isAttemptedMatchListQuestion(question[ques].getUserGivenAnswer())) {
									submitMatchListTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							}
						}

						/**
						 * update attempted section related details.
						 */
						userSectionAttempt.setUserSectionAttemptId(section[sec].getUserSectionAttemptId());
						userSectionAttempt.setObtainMarks(
								userSectionAttempt.getCorrectQueScore() - userSectionAttempt.getWrongQueScore());
						userSectionAttempt
								.setUnAttemptQue(totalQuestionsInSection - (userSectionAttempt.getCorrectQueAttempt()
										+ userSectionAttempt.getWrongQueAttempt()));
						userSectionAttempt.setSectionTakenTime(0);
						userSectionAttempt.setSubmitStatus(1);
						/**
						 * update attempted section details.
						 */
						updateAttemptedSection(userSectionAttempt);
						/**
						 * increase given test details w.r.t section.
						 */
						correctQueScore = correctQueScore + userSectionAttempt.getCorrectQueScore();
						wrongQueScore = wrongQueScore + userSectionAttempt.getWrongQueScore();
						correctQuestion = correctQuestion + userSectionAttempt.getCorrectQueAttempt();
						wrongQuestion = wrongQuestion + userSectionAttempt.getWrongQueAttempt();

					}
				}

			} else {
				for (int sec = 0; sec < section.length; sec++) {

					/**
					 * save attempted section details
					 */
					System.out.println("inside loop else=================");
					System.out.println("data save===" + test.getUserTestAttemptId() + "=" + section[sec].getSectionId()
							+ "=" + test.getUserId());
					Integer secId = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_SECTION_ATTEMPT, new Object[] {
							test.getUserTestAttemptId(), section[sec].getSectionId(), test.getUserId() });
					/**
					 * perform operations on successfully saved section's details.
					 */
					if (secId > 0) {
						System.out.println("section id====" + secId);
						/**
						 * UserSectionAttempt type object
						 */
						UserSectionAttempt userSectionAttempt = new UserSectionAttempt();
						/**
						 * set user' attempted section id.
						 */
						section[sec].setUserSectionAttemptId(secId);
						/**
						 * get every question details of this section.
						 */
						Question question[] = section[sec].getQuestionList();
						/**
						 * set required values.
						 */
						userSectionAttempt.setCorrectQueAttempt(0);
						userSectionAttempt.setCorrectQueScore(0.0f);
						userSectionAttempt.setWrongQueAttempt(0);
						userSectionAttempt.setWrongQueScore(0.0f);
						int totalQuestionsInSection = section[sec].getTotalQuestions();

						for (int ques = 0; ques < question.length; ques++) {
							/**
							 * Get given answer for a particular question in a section.
							 */
							List<Integer> givenAnswer = question[ques].getGivenAnswer();

							/**
							 * fetching question details like marks e.t.c from test based on question id and
							 * section id.
							 */
							System.out.println("=======questionId1=" + question[ques].getQuestionId());
							QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_DETAIL,
									new Object[] { question[ques].getQuestionId(), section[sec].getSectionId() });

							for (QueryData.Row row : data.getRows()) {
								question[ques].setQuestionMark(Integer.parseInt(row.getRowItem(1)));
								question[ques].setNegMark(
										row.getRowItem(2) == null ? 0 : Float.parseFloat(row.getRowItem(2)));
								question[ques].setQuestionType(Integer.parseInt(row.getRowItem(3)));
								question[ques].setAnswerValue(row.getRowItem(4));
							}
							/**
							 * perform action according to question type.
							 */
							switch (question[ques].getQuestionType()) {
							/**
							 * if question is multiple choice type
							 */
							case ConstantUtil.MULTIPLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									System.out.println("=======questionId=" + question[ques].getQuestionId());
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is single choice type.
							 */
							case ConstantUtil.SINGLE_CHOICE_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									saveMultipleTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;
							/**
							 * if question is sort list type.
							 */
							case ConstantUtil.SORT_LIST_TYPE:
								/**
								 * checking If user attempted this question.
								 */
								if (givenAnswer.size() > 0) {
									submitSortListTypeQuestionResult(section[sec], question[ques], givenAnswer,
											userSectionAttempt);
								}
								break;

							/**
							 * If question is choice matrix type.
							 */
							case ConstantUtil.CHOICE_MATRIX_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedChoiceMatrixQuestion(question[ques].getChoiceMatrixGivenAnswer())) {
									submitChoiceMatrixTypeQuestionResult(section[sec], question[ques],
											question[ques].getChoiceMatrixGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * If question is classification type.
							 */
							case ConstantUtil.CLASSIFICATION_TYPE:
								/**
								 * checking if user attempted this question.
								 */
								if (isAttemptedClassificationQuestion(question[ques].getUserGivenAnswer())) {
									submitClassificationTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							/**
							 * if question is match list type.
							 */
							case ConstantUtil.MATCH_LIST:
								/**
								 * checking If user attempted this question.
								 */
								if (isAttemptedMatchListQuestion(question[ques].getUserGivenAnswer())) {
									submitMatchListTypeQuestionResult(section[sec], question[ques],
											question[ques].getUserGivenAnswer(), userSectionAttempt);
								}
								break;
							}
						}

						/**
						 * update attempted section related details.
						 */
						userSectionAttempt.setUserSectionAttemptId(section[sec].getUserSectionAttemptId());
						userSectionAttempt.setObtainMarks(
								userSectionAttempt.getCorrectQueScore() - userSectionAttempt.getWrongQueScore());
						userSectionAttempt
								.setUnAttemptQue(totalQuestionsInSection - (userSectionAttempt.getCorrectQueAttempt()
										+ userSectionAttempt.getWrongQueAttempt()));
						userSectionAttempt.setSectionTakenTime(0);
						userSectionAttempt.setSubmitStatus(1);
						/**
						 * update attempted section details.
						 */
						updateAttemptedSection(userSectionAttempt);
						/**
						 * increase given test details w.r.t section.
						 */
						correctQueScore = correctQueScore + userSectionAttempt.getCorrectQueScore();
						wrongQueScore = wrongQueScore + userSectionAttempt.getWrongQueScore();
						correctQuestion = correctQuestion + userSectionAttempt.getCorrectQueAttempt();
						wrongQuestion = wrongQuestion + userSectionAttempt.getWrongQueAttempt();

					}
				}
			}

			obtainMarks = correctQueScore - wrongQueScore;
			System.err.println("obtained marks" + (Float) obtainMarks);
			unAttemptQue = totalQuestions - (correctQuestion + wrongQuestion);
			userTestAttempt.setUserTestAttemptId(test.getUserTestAttemptId());
			userTestAttempt.setCorrectQueAttempt(correctQuestion);
			userTestAttempt.setWrongQueAttempt(wrongQuestion);
			userTestAttempt.setCorrectQueScore(correctQueScore);
			userTestAttempt.setWrongQueScore(wrongQueScore);
			userTestAttempt.setObtainMarks((Float) obtainMarks);
			userTestAttempt.setUnAttemptQue(unAttemptQue);
			userTestAttempt.setTestTakenTime(Integer.parseInt(test.getTestTime()));
			userTestAttempt.setSubmitStatus(test.getTestStatus());
			userTestAttempt.setEveryquestionmark(everyQuestionMark);
			userTestAttempt.setIsPractice(test.getIsPractice());
			if(test.getIsPractice() == 1)
			{
				System.err.println("its practice test");
				userTestAttempt.setSubmitStatus(0);	
			}
			/**
			 * update test details.
			 */
			updateSubmitTest(userTestAttempt);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service submitTestDetails method ::::::", e);
		}
		return userTestAttempt;
	}

	/**
	 * This method is user for saving the submitted result of multiple choice type
	 * question.
	 * 
	 * @param section
	 * @param question
	 * @param givenAnswer
	 * @param userSectionAttempt
	 */
	public void saveMultipleTypeQuestionResult(Section section, Question question, List<Integer> givenAnswer,
			UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service saveMultipleTypeQuestionResult method ::::::");
		try {
			/**
			 * Initialize question Score.
			 */
			float questionScore = 0;
			/**
			 * checking user's attempted question is correct or wrong.
			 */
			if (givenAnswerIsCorrect(question.getQuestionId(), givenAnswer)) {
				/**
				 * user's attempted question is correct so increase the total number of correct
				 * question for this section.
				 */
				userSectionAttempt.setCorrectQueAttempt(userSectionAttempt.getCorrectQueAttempt() + 1);
				/**
				 * user's attempted question is correct so added the obtains marks in section
				 * obtains marks.
				 */
				userSectionAttempt
						.setCorrectQueScore(userSectionAttempt.getCorrectQueScore() + question.getQuestionMark());
				questionScore = question.getQuestionMark();
				question.setIsCorrect(1);
			} else {
				/**
				 * user's attempted question is wrong so increase the total number of wrong
				 * question for this section.
				 */
				userSectionAttempt.setWrongQueAttempt(userSectionAttempt.getWrongQueAttempt() + 1);
				/**
				 * user's attempted question is wrong so added the negative marks in section's
				 * total wrong marks.
				 */
				userSectionAttempt.setWrongQueScore(userSectionAttempt.getWrongQueScore() + question.getNegMark());
				questionScore = question.getNegMark();
				question.setIsCorrect(0);
			}
			/**
			 * Save details of attempted question.
			 */
			Integer userQuestionAttemptId = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_QUESTION_ATTEMPT,
					new Object[] { section.getUserSectionAttemptId(), question.getQuestionId(), questionScore,
							question.getIsCorrect(), question.getFavorite() != "" ? question.getFavorite() : null,
							question.getNotes() != "" ? question.getNotes() : null, question.getAnswerValue() });
			/**
			 * Save details of attempted options for a particular question.
			 */
			if (userQuestionAttemptId > 0) {
				for (int opt = 0; opt < question.getGivenAnswer().size(); opt++) {
					QueryManager.execuateUpdate(QueryStrings.SAVE_USER_ANSWER_ATTEMPT,
							new Object[] { userQuestionAttemptId, question.getGivenAnswer().get(opt), "" });
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service saveMultipleTypeQuestionResult method ::::::", e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is user for saving the submitted result of sort list type
	 * question.
	 * 
	 * @param section
	 * @param question
	 * @param givenAnswer
	 * @param userSectionAttempt
	 */
	public void submitSortListTypeQuestionResult(Section section, Question question, List<Integer> givenAnswer,
			UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitSortListTypeQuestionResult method ::::::");
		try {
			/**
			 * Initialize question Score.
			 */
			float questionScore = 0;
			/**
			 * correct answer value of this question.
			 */
			ArrayList<Integer> answerValue = new ArrayList<Integer>();
			/**
			 * make object of Object Mapper class.
			 */
			ObjectMapper mapper = new ObjectMapper();
			/**
			 * convert json into List of Integer type using object mapper class.
			 */
			answerValue = mapper.readValue(question.getAnswerValue(), new TypeReference<List<Integer>>() {
			});
			/**
			 * checking user's attempted question is correct or wrong.
			 */
			if (givenAnswer.equals(answerValue)) {
				/**
				 * user's attempted question is correct so increase the total number of correct
				 * question for this section.
				 */
				userSectionAttempt.setCorrectQueAttempt(userSectionAttempt.getCorrectQueAttempt() + 1);
				/**
				 * user's attempted question is correct so added the obtains marks in section
				 * obtains marks.
				 */
				userSectionAttempt
						.setCorrectQueScore(userSectionAttempt.getCorrectQueScore() + question.getQuestionMark());
				questionScore = question.getQuestionMark();
				question.setIsCorrect(1);
			} else {
				/**
				 * user's attempted question is wrong so increase the total number of wrong
				 * question for this section.
				 */
				userSectionAttempt.setWrongQueAttempt(userSectionAttempt.getWrongQueAttempt() + 1);
				/**
				 * user's attempted question is wrong so added the negative marks in section's
				 * total wrong marks.
				 */
				userSectionAttempt.setWrongQueScore(userSectionAttempt.getWrongQueScore() + question.getNegMark());
				questionScore = question.getNegMark();
				question.setIsCorrect(0);
			}
			ObjectMapper object = new ObjectMapper();
			/**
			 * convert array list of given answer into string.
			 */
			String givenAnsweJSON = object.writeValueAsString(givenAnswer);

			/**
			 * Save details of attempted question.
			 */
			QueryManager.execuateUpdate(QueryStrings.SAVE_USER_QUESTION_ATTEMPT,
					new Object[] { section.getUserSectionAttemptId(), question.getQuestionId(), questionScore,
							question.getIsCorrect(), question.getFavorite() != "" ? question.getFavorite() : null,
							question.getNotes() != "" ? question.getNotes() : null, givenAnsweJSON });
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service submitSortListTypeQuestionResult method ::::::", e);
		}
	}

	/**
	 * This method is used for submitted result of choice matrix type question.
	 * 
	 * @param section
	 * @param question
	 * @param choiceMatrixGivenAnswer
	 * @param userSectionAttempt
	 */
	public void submitChoiceMatrixTypeQuestionResult(Section section, Question question,
			List<Object> choiceMatrixGivenAnswer, UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitChoiceMatrixTypeQuestionResult method ::::::");
		try {
			/**
			 * Initialize question Score.
			 */
			float questionScore = 0;
			/**
			 * correct answer of this question
			 */
			List<Object> correctAnswer = new ArrayList<Object>();
			/**
			 * make object of ObjectMapper class.
			 */
			ObjectMapper mapper = new ObjectMapper();
			/**
			 * convert json string into list using object mapper.
			 */
			correctAnswer = mapper.readValue(question.getAnswerValue(), new TypeReference<List<Object>>() {
			});
			/**
			 * checking that question's given answer is correct or wrong.
			 */
			boolean isCorrect = givenChoiceMatrixAnswerIsCorrect(choiceMatrixGivenAnswer, correctAnswer);
			/**
			 * checking user's attempted question is correct or wrong.
			 */
			if (isCorrect) {
				/**
				 * user's attempted question is correct so increase the total number of correct
				 * question for this section.
				 */
				userSectionAttempt.setCorrectQueAttempt(userSectionAttempt.getCorrectQueAttempt() + 1);
				/**
				 * user's attempted question is correct so added the obtains marks in section
				 * obtains marks.
				 */
				userSectionAttempt
						.setCorrectQueScore(userSectionAttempt.getCorrectQueScore() + question.getQuestionMark());
				questionScore = question.getQuestionMark();
				question.setIsCorrect(1);
			} else {
				/**
				 * user's attempted question is wrong so increase the total number of wrong
				 * question for this section.
				 */
				userSectionAttempt.setWrongQueAttempt(userSectionAttempt.getWrongQueAttempt() + 1);
				/**
				 * user's attempted question is wrong so added the negative marks in section's
				 * total wrong marks.
				 */
				userSectionAttempt.setWrongQueScore(userSectionAttempt.getWrongQueScore() + question.getNegMark());
				questionScore = question.getNegMark();
				question.setIsCorrect(0);
			}
			ObjectMapper object = new ObjectMapper();
			/**
			 * convert array list of given answer into string.
			 */
			String givenAnsweJSON = object.writeValueAsString(choiceMatrixGivenAnswer);

			/**
			 * Save details of attempted question.
			 */
			QueryManager.execuateUpdate(QueryStrings.SAVE_USER_QUESTION_ATTEMPT,
					new Object[] { section.getUserSectionAttemptId(), question.getQuestionId(), questionScore,
							question.getIsCorrect(), question.getFavorite() != "" ? question.getFavorite() : null,
							question.getNotes() != "" ? question.getNotes() : null, givenAnsweJSON });
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service submitChoiceMatrixTypeQuestionResult method ::::::", e);
		}
	}

	/**
	 * This method is used for submitted result of classification type question.
	 * 
	 * @param section
	 * @param question
	 * @param userGivenAnswer
	 * @param userSectionAttempt
	 */
	public void submitClassificationTypeQuestionResult(Section section, Question question, List<Object> userGivenAnswer,
			UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitClassificationTypeQuestionResult method ::::::");
		try {
			/**
			 * Initialize question Score.
			 */
			float questionScore = 0;
			/**
			 * correct answer of this question
			 */
			List<Object> correctAnswer = new ArrayList<Object>();
			/**
			 * make object of ObjectMapper class.
			 */
			ObjectMapper mapper = new ObjectMapper();
			/**
			 * convert json string into list using object mapper.
			 */
			correctAnswer = mapper.readValue(question.getAnswerValue(), new TypeReference<List<Object>>() {
			});
			/**
			 * checking that question's given answer is correct or wrong.
			 */
			boolean isCorrect = givenClassificationAnswerIsCorrect(userGivenAnswer, correctAnswer);
			/**
			 * checking user's attempted question is correct or wrong.
			 */
			if (isCorrect) {
				/**
				 * user's attempted question is correct so increase the total number of correct
				 * question for this section.
				 */
				userSectionAttempt.setCorrectQueAttempt(userSectionAttempt.getCorrectQueAttempt() + 1);
				/**
				 * user's attempted question is correct so added the obtains marks in section
				 * obtains marks.
				 */
				userSectionAttempt
						.setCorrectQueScore(userSectionAttempt.getCorrectQueScore() + question.getQuestionMark());
				questionScore = question.getQuestionMark();
				question.setIsCorrect(1);
			} else {
				/**
				 * user's attempted question is wrong so increase the total number of wrong
				 * question for this section.
				 */
				userSectionAttempt.setWrongQueAttempt(userSectionAttempt.getWrongQueAttempt() + 1);
				/**
				 * user's attempted question is wrong so added the negative marks in section's
				 * total wrong marks.
				 */
				userSectionAttempt.setWrongQueScore(userSectionAttempt.getWrongQueScore() + question.getNegMark());
				questionScore = question.getNegMark();
				question.setIsCorrect(0);
			}
			ObjectMapper object = new ObjectMapper();
			/**
			 * convert array list of given answer into string.
			 */
			String givenAnsweJSON = object.writeValueAsString(userGivenAnswer);

			/**
			 * Save details of attempted question.
			 */
			QueryManager.execuateUpdate(QueryStrings.SAVE_USER_QUESTION_ATTEMPT,
					new Object[] { section.getUserSectionAttemptId(), question.getQuestionId(), questionScore,
							question.getIsCorrect(), question.getFavorite() != "" ? question.getFavorite() : null,
							question.getNotes() != "" ? question.getNotes() : null, givenAnsweJSON });
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service submitClassificationTypeQuestionResult method ::::::", e);
		}
	}

	/**
	 * This method is user for saving the submitted result of match list type
	 * question.
	 * 
	 * @param section
	 * @param question
	 * @param givenAnswer
	 * @param userSectionAttempt
	 */
	public void submitMatchListTypeQuestionResult(Section section, Question question, List<Object> userGivenAnswer,
			UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service submitMatchListTypeQuestionResult method ::::::");
		try {
			/**
			 * Initialize question Score.
			 */
			float questionScore = 0;
			/**
			 * correct answer value of this question.
			 */
			ArrayList<Integer> answerValue = new ArrayList<Integer>();
			/**
			 * make object of Object Mapper class.
			 */
			ObjectMapper mapper = new ObjectMapper();
			/**
			 * convert json into List of Integer type using object mapper class.
			 */
			answerValue = mapper.readValue(question.getAnswerValue(), new TypeReference<List<Integer>>() {
			});
			/**
			 * checking user's attempted question is correct or wrong.
			 */
			if (userGivenAnswer.equals(answerValue)) {
				/**
				 * user's attempted question is correct so increase the total number of correct
				 * question for this section.
				 */
				userSectionAttempt.setCorrectQueAttempt(userSectionAttempt.getCorrectQueAttempt() + 1);
				/**
				 * user's attempted question is correct so added the obtains marks in section
				 * obtains marks.
				 */
				userSectionAttempt
						.setCorrectQueScore(userSectionAttempt.getCorrectQueScore() + question.getQuestionMark());
				questionScore = question.getQuestionMark();
				question.setIsCorrect(1);
			} else {
				/**
				 * user's attempted question is wrong so increase the total number of wrong
				 * question for this section.
				 */
				userSectionAttempt.setWrongQueAttempt(userSectionAttempt.getWrongQueAttempt() + 1);
				/**
				 * user's attempted question is wrong so added the negative marks in section's
				 * total wrong marks.
				 */
				userSectionAttempt.setWrongQueScore(userSectionAttempt.getWrongQueScore() + question.getNegMark());
				questionScore = question.getNegMark();
				question.setIsCorrect(0);
			}
			ObjectMapper object = new ObjectMapper();
			/**
			 * convert array list of given answer into string.
			 */
			String givenAnsweJSON = object.writeValueAsString(userGivenAnswer);

			/**
			 * Save details of attempted question.
			 */
			QueryManager.execuateUpdate(QueryStrings.SAVE_USER_QUESTION_ATTEMPT,
					new Object[] { section.getUserSectionAttemptId(), question.getQuestionId(), questionScore,
							question.getIsCorrect(), question.getFavorite() != "" ? question.getFavorite() : null,
							question.getNotes() != "" ? question.getNotes() : null, givenAnsweJSON });
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service submitMatchListTypeQuestionResult method ::::::", e);
		}
	}

	/**
	 * A method which is used for saving the user's given test details
	 * 
	 * @param userTestAttempt
	 */
	public void updateAttemptedSection(UserSectionAttempt userSectionAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service updateAttemptedSection method ::::::");
		try {
			QueryManager.execuateQuery(QueryStrings.UPDATE_USER_SECTION_ATTEMPT,
					new Object[] { userSectionAttempt.getObtainMarks(), userSectionAttempt.getCorrectQueAttempt(),
							userSectionAttempt.getWrongQueAttempt(), userSectionAttempt.getCorrectQueScore(),
							userSectionAttempt.getWrongQueScore(), userSectionAttempt.getUnAttemptQue(),
							userSectionAttempt.getSectionTakenTime(), userSectionAttempt.getSubmitStatus(),
							userSectionAttempt.getUserSectionAttemptId() });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service updateAttemptedSection method ::::::", e);
		}
	}

	/**
	 * A method which is used for saving the user's given test details
	 * 
	 * @param userTestAttempt
	 */
	public void updateSubmitTest(UserTestAttempt userTestAttempt) {
		logger.log(Level.DEBUG, "Inside Student Test Service updateSubmitTest method ::::::");
		try {
			QueryManager.execuateQuery(QueryStrings.SAVE_USER_TEST_ATTEMPT_REST,
					new Object[] { userTestAttempt.getObtainMarks(), userTestAttempt.getCorrectQueAttempt(),
							userTestAttempt.getWrongQueAttempt(), userTestAttempt.getCorrectQueScore(),
							userTestAttempt.getWrongQueScore(), userTestAttempt.getUnAttemptQue(),
							userTestAttempt.getTestTakenTime(), userTestAttempt.getSubmitStatus(),
							userTestAttempt.getUserTestAttemptId() });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service updateSubmitTest method ::::::", e);
		}
	}

	/**
	 * A function for checking that user has submitted right answer for a particular
	 * question or not in case of multiple choice type question.
	 * 
	 * @param quesId
	 * @param givenAnswer
	 * @return
	 */
	public Boolean givenAnswerIsCorrect(int quesId, List<Integer> givenAnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service givenAnswerIsCorrect method ::::::");
		boolean isCorrect = false;
		List<Integer> correctAnswer = new ArrayList<Integer>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID,
					new Object[] { quesId });
			for (QueryData.Row row : data.getRows()) {
				if (Integer.parseInt(row.getRowItem(3)) == 1) {
					correctAnswer.add(Integer.parseInt(row.getRowItem(0)));
				}
			}
			if (correctAnswer.size() == givenAnswer.size() && givenAnswer.containsAll(correctAnswer)) {
				isCorrect = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service givenAnswerIsCorrect method ::::::", e);
		}
		return isCorrect;
	}

	/**
	 * A function for checking that user has submitted right answer for a particular
	 * question or not which is choice matrix type.
	 * 
	 * @param choiceMatrixGivenAnswer
	 * @param correctAnswer
	 * @return isCorrect
	 */
	@SuppressWarnings("unchecked")
	public Boolean givenChoiceMatrixAnswerIsCorrect(List<Object> choiceMatrixGivenAnswer, List<Object> correctAnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service givenChoiceMatrixAnswerIsCorrect method ::::::");
		boolean isCorrect = false;
		try {
			if (choiceMatrixGivenAnswer.size() == correctAnswer.size()) {
				int correctFlag = 0;
				for (int i = 0; i < correctAnswer.size(); i++) {
					List<Integer> subCorrectAnswer = (List<Integer>) correctAnswer.get(i);
					List<Integer> subGivenAnswer = (List<Integer>) choiceMatrixGivenAnswer.get(i);
					if (subCorrectAnswer.containsAll(subGivenAnswer) && subGivenAnswer.size() != 0) {
						correctFlag++;
					}
				}
				if (correctFlag == correctAnswer.size()) {
					isCorrect = true;
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service givenChoiceMatrixAnswerIsCorrect method ::::::", e);
		}
		return isCorrect;
	}

	/**
	 * A function for checking that user has submitted right answer for a particular
	 * question or not which is classification type.
	 * 
	 * @param userGivenAnswer
	 * @param correctAnswer
	 * @return isCorrect
	 */
	@SuppressWarnings("unchecked")
	public Boolean givenClassificationAnswerIsCorrect(List<Object> userGivenAnswer, List<Object> correctAnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service givenClassificationAnswerIsCorrect method ::::::");
		boolean isCorrect = true;
		try {
			if (userGivenAnswer.size() == correctAnswer.size()) {
				for (int i = 0; i < correctAnswer.size() && isCorrect; i++) {
					List<Object> correctAnswerArray = (List<Object>) correctAnswer.get(i);
					List<Object> givenAnswerArray = (List<Object>) userGivenAnswer.get(i);
					if (correctAnswerArray.size() == givenAnswerArray.size()) {
						for (int j = 0; j < correctAnswerArray.size() && isCorrect; j++) {
							List<Integer> subCorrectAnswer = (List<Integer>) correctAnswerArray.get(j);
							List<Integer> subGivenAnswer = (List<Integer>) givenAnswerArray.get(j);
							if ((subCorrectAnswer.size() == subGivenAnswer.size())
									&& (subCorrectAnswer.containsAll(subGivenAnswer))) {
								isCorrect = true;
							} else {
								isCorrect = false;
							}
						}
					} else {
						isCorrect = false;
					}
				}
			} else {
				isCorrect = false;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service givenClassificationAnswerIsCorrect method ::::::", e);
		}
		return isCorrect;
	}

	/**
	 * This method is used for checking that user has attempted choice matrix type
	 * question or skipped.
	 * 
	 * @param choiceMatrixGivenAnnswer
	 * @return isAttempted
	 */
	public Boolean isAttemptedChoiceMatrixQuestion(List<Object> choiceMatrixGivenAnnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service isAttemptedChoiceMatrixQuestion method ::::::");
		Boolean isAttempted = false;
		int givenAnswerlength = 0;
		try {
			for (int i = 0; i < choiceMatrixGivenAnnswer.size(); i++) {
				@SuppressWarnings("unchecked")
				List<Integer> givenAnswer = (List<Integer>) choiceMatrixGivenAnnswer.get(i);
				if (givenAnswer.size() > 0) {
					givenAnswerlength++;
					break;
				}
			}
			if (givenAnswerlength > 0) {
				isAttempted = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service isAttemptedChoiceMatrixQuestion method ::::::", e);
		}
		return isAttempted;
	}

	/**
	 * This method is used for checking that user has attempted classification type
	 * question or skipped.
	 * 
	 * @param userGivenAnnswer
	 * @return isAttempted
	 */
	@SuppressWarnings("unchecked")
	public Boolean isAttemptedClassificationQuestion(List<Object> userGivenAnnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service isAttemptedClassificationQuestion method ::::::");
		Boolean isAttempted = false;
		int givenAnswerlength = 0;
		try {
			for (int i = 0; i < userGivenAnnswer.size(); i++) {
				List<Object> givenAnswerArray = (List<Object>) userGivenAnnswer.get(i);
				for (int j = 0; j < givenAnswerArray.size(); j++) {
					List<Object> givenAnswer = (List<Object>) userGivenAnnswer.get(i);
					if (givenAnswer.size() > 0) {
						givenAnswerlength++;
						break;
					}
				}
			}
			if (givenAnswerlength > 0) {
				isAttempted = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service isAttemptedClassificationQuestion method ::::::", e);
		}
		return isAttempted;
	}

	/**
	 * This method is used for checking that user has attempted match list type
	 * question or skipped.
	 * 
	 * @param userGivenAnnswer
	 * @return isAttempted
	 */
	public Boolean isAttemptedMatchListQuestion(List<Object> userGivenAnnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service isAttemptedMatchListQuestion method ::::::");
		Boolean isAttempted = false;
		int givenAnswerlength = 0;
		try {
			for (int i = 0; i < userGivenAnnswer.size(); i++) {
				userGivenAnnswer.get(i).equals("0");
				if (!userGivenAnnswer.get(i).equals(0)) {
					givenAnswerlength++;
				}
			}
			if (givenAnswerlength > 0) {
				isAttempted = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service isAttemptedMatchListQuestion method ::::::",
					e);
		}
		return isAttempted;
	}

	/**
	 * A method which is used to getting all attempted test list of student with
	 * paging(limit) for application side.
	 * 
	 * @param userId
	 * @param offset
	 * @return List<Test>
	 */
	public List<Test> getMyTestByUserId(Integer userId, int offset) {
		logger.log(Level.DEBUG, "Inside Student Test Service getMyTestByUserId method ::::::");
		List<Test> list = new ArrayList<Test>();
		Test test;
		int skip = offset * ConstantUtil.LIMIT;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_MY_TESTS_BY_USERID_WITH_PAGING,
					new Object[] { userId, ConstantUtil.LIMIT, skip });
			for (QueryData.Row row : data.getRows()) {
				test = new Test();
				test.setUserId(Integer.parseInt(row.getRowItem(0)));
				test.setTestId(Integer.parseInt(row.getRowItem(1)));
				test.setTestName(row.getRowItem(2));
				test.setTestTime(row.getRowItem(3));

				test.setTeacherName(row.getRowItem(4) == null ? row.getRowItem(5) : row.getRowItem(4));
				test.setTestCreatedDate(row.getRowItem(6));
				test.setMaxAttempts(row.getRowItem(7) == null ? 0 : Integer.parseInt(row.getRowItem(7)));
				String tag[] = row.getRowItem(8).replaceAll(" ", "").split(",");
				String newTag = "";
				for (int i = 0; i < tag.length; i++) {
					newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i]) : (newTag + "#" + tag[i] + ", "));
				}
				test.setTestTag(newTag);
				test.setNoOfAttempted(Integer.parseInt(row.getRowItem(9)));
				test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(10));
				QueryData totalQues = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONS_NUMBER,
						new Object[] { test.getTestId() });
				for (QueryData.Row ques : totalQues.getRows()) {
					test.setTotalQuestion(ques.getRowItem(0) == null ? 0 : Integer.parseInt(ques.getRowItem(0)));
				}
				QueryData givendata = QueryManager.execuateQuery(QueryStrings.GET_MY_TESTS_BY_USERID_TESTID,
						new Object[] { test.getUserId(), test.getTestId() });
				List<UserTestAttempt> usertest = new ArrayList<UserTestAttempt>();
				UserTestAttempt userTestAttempt;
				for (QueryData.Row givenRow : givendata.getRows()) {
					userTestAttempt = new UserTestAttempt();
					userTestAttempt.setTestId(Integer.parseInt(givenRow.getRowItem(0)));
					userTestAttempt.setObtainMarks(Float.parseFloat(givenRow.getRowItem(1)));
					userTestAttempt.setTotalMarks(Integer.parseInt(givenRow.getRowItem(2)));
					int second = givenRow.getRowItem(3) == null ? 0 : Integer.parseInt(givenRow.getRowItem(3)) % 60;
					int minute = givenRow.getRowItem(3) == null ? 0
							: (Integer.parseInt(givenRow.getRowItem(3)) - second) / 60;
					userTestAttempt.setTimeTaken(minute + ":" + (second < 10 ? ("0" + second) : second));
					userTestAttempt.setTestGivenTime(givenRow.getRowItem(4));
					userTestAttempt.setUserTestAttemptId(Integer.parseInt(givenRow.getRowItem(5)));
					userTestAttempt.setSubmitStatus(Integer.parseInt(givenRow.getRowItem(6)));
					userTestAttempt.setJsonStatus(givenRow.getRowItem(7) == null ? 0 : 1);
					usertest.add(userTestAttempt);
				}
				test.setUserTestAttempt(usertest);
				list.add(test);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getMyTestByUserId method ::::::", e);
		}
		return list;
	}

	/**
	 * Method to get test Object by testId
	 * 
	 * @param testId
	 * @return Test
	 */
	public Test getTestById(String testId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getTestById method ::::::");
		Test test = new Test();
		Integer isRandom=null;
		try {
			QueryData data1=QueryManager.execuateQuery(QueryStrings.IS_RANDOM,
					new Object[] { Integer.parseInt(testId) });
			for (QueryData.Row row : data1.getRows()) {
				isRandom=Integer.parseInt(row.getRowItem(0));
				
			}
			
			if(isRandom==0)
			{
				System.err.println("NotRandom");
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ONE_TEST_DETAIL,
					new Object[] { Integer.parseInt(testId) });
			for (QueryData.Row row : data.getRows()) {
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestDesc(row.getRowItem(2));
				test.setTestInstruct(row.getRowItem(3));
				test.setTestTag(row.getRowItem(4));
				test.setMaxAttempts((row.getRowItem(5) != null ? Integer.parseInt(row.getRowItem(5)) : 0));
				test.setNegMark((row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0));
				test.setTestTime(row.getRowItem(7));
				test.setTestAdaptive(Integer.parseInt(row.getRowItem(8)));
				test.setOrgName(row.getRowItem(9));
				test.setTeacherName(row.getRowItem(10));
				test.setTestPause(Integer.parseInt(row.getRowItem(11)));
				test.setMaxMark(Double.parseDouble(row.getRowItem(15)));
				test.setTestPublishStatus(Integer.parseInt(row.getRowItem(17)));
				test.setIsPublic(Integer.parseInt(row.getRowItem(19)));
				test.setIsReview(Integer.parseInt(row.getRowItem(20)));
				test.setReviewWithCorrect(row.getRowItem(21) != null ? Integer.parseInt(row.getRowItem(21)) : null);
				test.setTotalQuestion(Integer.parseInt(row.getRowItem(22)));
				test.setTimeMinute(row.getRowItem(23) != null ? Integer.parseInt(row.getRowItem(23)) : null);
				test.setUserId(Integer.parseInt(row.getRowItem(24)));
			}
			}
			
			else {
				System.err.println("Random");
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TEST_DESC_FOR_RANDOM,	new Object[] { Integer.parseInt(testId) });
				for (QueryData.Row row : data.getRows()) {
					test.setTestDesc(row.getRowItem(0));
			}
		}
		}catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getTestById method ::::::", e);
		}
		return test;
	}

	/**
	 * A method for getting questions and options list attempted by a particular
	 * user for review.
	 * 
	 * @param userTestAttemptPk
	 * @param userId
	 * @return Test
	 */
	public Test getAttemptedQuestionAnswerByUser(Integer userTestAttemptPk, Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getAttemptedQuestionAnswerByUser method ::::::");
		Test test = new Test();
		ObjectMapper mapper = new ObjectMapper();
		try {
			/**
			 * getting attempted test details using userTestAttemptPk.
			 */
			QueryData testdata = QueryManager.execuateQuery(QueryStrings.TEST_DETAIL_BY_ATTEMPTID,
					new Object[] { userTestAttemptPk });
			/**
			 * set the fetch details in test object.
			 */
			for (QueryData.Row testRow : testdata.getRows()) {
				test.setTestId(Integer.parseInt(testRow.getRowItem(0)));
				test.setTestName(testRow.getRowItem(1));
				test.setTestTime(testRow.getRowItem(2) != null ? testRow.getRowItem(2) : "");
				test.setReviewWithCorrect(testRow.getRowItem(3) != null ? Integer.parseInt(testRow.getRowItem(3)) : 0);
				test.setIsRandom(testRow.getRowItem(4) != null ? Integer.parseInt(testRow.getRowItem(4)) : 0);
			}

			if (test.getIsRandom() == 1) {
				System.out.println("get ============");

				/**
				 * getting section's details
				 */
				QueryData secData = QueryManager.execuateQuery(QueryStrings.GET_SECTIONS_BY_TEST_ATTEMPT_PK1,
						new Object[] { userTestAttemptPk,userId,test.getTestId() });

				Section section[] = new Section[secData.getRows().size()];
				/**
				 * set section's details.
				 */
				int sec = 0;
				for (QueryData.Row secRow : secData.getRows()) {
					section[sec] = new Section();
					section[sec].setUserSectionAttemptId(Integer.parseInt(secRow.getRowItem(0)));
					section[sec].setSectionId(Integer.parseInt(secRow.getRowItem(1)));
					section[sec].setSectionName(secRow.getRowItem(2));

					/**
					 * fetching question's details.
					 */
					System.out.println("getdata ============"+	section[sec].getUserSectionAttemptId()+"="+	section[sec].getSectionId());
					
					  QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONS_BY_SECTION_ATTEMPT_PK1,
					  new Object[] { section[sec].getUserSectionAttemptId(), userId, test.getTestId(),userId});
					 
					/**
					 * creating question list.
					 */
					 Question question[] = new Question[data.getRows().size()]; 
					int ques = 0;
					/**
					 * set fetch question's details.
					 */
					
					  for (QueryData.Row row : data.getRows())
					  { 
					   question[ques] = new Question();
					  question[ques].setQuestionId(Integer.parseInt(row.getRowItem(0)));
					  question[ques].setQuestionName(row.getRowItem(1));
					  question[ques].setQuestionType(Integer.parseInt(row.getRowItem(2)));
					  question[ques].setIsCorrect(row.getRowItem(3) != null ?
					  Integer.parseInt(row.getRowItem(3)) : -1);
					  question[ques].setQuestionNo(Integer.parseInt(row.getRowItem(4)));
					  question[ques] .setUserQueAttemptId(row.getRowItem(5) != null ?(Integer.parseInt(row.getRowItem(5))) : 0);
					  question[ques].setExplanation(row.getRowItem(6));
			           question[ques].setFavorite(row.getRowItem(7) != null ? row.getRowItem(7) :
		               ""); question[ques].setNotes(row.getRowItem(8) != null ? row.getRowItem(8) :
					  ""); question[ques].setAnswerValue(row.getRowItem(9)); if (row.getRowItem(11)
					  != null) 
					  {
					  QuestionSetting questionSetting =mapper.readValue(row.getRowItem(11), new TypeReference<QuestionSetting>() {
					  }); question[ques].setQuestionSetting(questionSetting); } String givenAnser =
					  row.getRowItem(10);
					 /**
						 * getting option list based on question type.
						 */
					
					  switch (question[ques].getQuestionType()) {
					 /**
						 * If question is multiple choice type.
						 */
					
					  case ConstantUtil.MULTIPLE_CHOICE_TYPE:
					  getOptionsForMultipleTypeQuestion(question[ques]); break;
					 /**
						 * If question is single choice type.
						 */
					
					  case ConstantUtil.SINGLE_CHOICE_TYPE:
					  getOptionsForMultipleTypeQuestion(question[ques]); break;
					 /**
						 * If question is sort list type.
						 */
					
					  case ConstantUtil.SORT_LIST_TYPE:
					  getOptionsForSortListTypeQuestion(question[ques], givenAnser); break;
					 /**
						 * If question is choice matrix type.
						 */
					
					  case ConstantUtil.CHOICE_MATRIX_TYPE:
					  getOptionsForChoiceMatrixTypeQuestion(question[ques], givenAnser); break;
					 /**
						 * If question is classification type.
						 */
					
					  case ConstantUtil.CLASSIFICATION_TYPE:
					  getOptionsForCommonStructrueQuestion(question[ques], givenAnser); break;
					 /**
						 * If question is match list type.
						 */
							  case ConstantUtil.MATCH_LIST:
							  getOptionsForCommonStructrueQuestion(question[ques], givenAnser); break; }
							  ques++; }
							 
					section[sec].setQuestionList(question);
					sec++;
				}
				test.setSectionlist(section);
			} else {

				/**
				 * getting section's details
				 */
				QueryData secData = QueryManager.execuateQuery(QueryStrings.GET_SECTIONS_BY_TEST_ATTEMPT_PK,
						new Object[] { userTestAttemptPk });

				Section section[] = new Section[secData.getRows().size()];
				/**
				 * set section's details.
				 */
				int sec = 0;
				for (QueryData.Row secRow : secData.getRows()) {
					section[sec] = new Section();
					section[sec].setUserSectionAttemptId(Integer.parseInt(secRow.getRowItem(0)));
					section[sec].setSectionId(Integer.parseInt(secRow.getRowItem(1)));
					section[sec].setSectionName(secRow.getRowItem(2));

					/**
					 * fetching question's details.
					 */
					QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONS_BY_SECTION_ATTEMPT_PK,
							new Object[] { section[sec].getUserSectionAttemptId(), userId });
					/**
					 * creating question list.
					 */
					Question question[] = new Question[data.getRows().size()];
					int ques = 0;
					/**
					 * set fetch question's details.
					 */
					for (QueryData.Row row : data.getRows()) {
						question[ques] = new Question();
						question[ques].setQuestionId(Integer.parseInt(row.getRowItem(0)));
						question[ques].setQuestionName(row.getRowItem(1));
						question[ques].setQuestionType(Integer.parseInt(row.getRowItem(2)));
						question[ques]
								.setIsCorrect(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : -1);
						question[ques].setQuestionNo(Integer.parseInt(row.getRowItem(4)));
						question[ques].setUserQueAttemptId(
								row.getRowItem(5) != null ? (Integer.parseInt(row.getRowItem(5))) : 0);
						question[ques].setExplanation(row.getRowItem(6));
						question[ques].setFavorite(row.getRowItem(7) != null ? row.getRowItem(7) : "");
						question[ques].setNotes(row.getRowItem(8) != null ? row.getRowItem(8) : "");
						question[ques].setAnswerValue(row.getRowItem(9));
						if (row.getRowItem(11) != null) {
							QuestionSetting questionSetting = mapper.readValue(row.getRowItem(11),
									new TypeReference<QuestionSetting>() {
									});
							question[ques].setQuestionSetting(questionSetting);
						}
						String givenAnser = row.getRowItem(10);
						/**
						 * getting option list based on question type.
						 */
						switch (question[ques].getQuestionType()) {
						/**
						 * If question is multiple choice type.
						 */
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							getOptionsForMultipleTypeQuestion(question[ques]);
							break;
						/**
						 * If question is single choice type.
						 */
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							getOptionsForMultipleTypeQuestion(question[ques]);
							break;
						/**
						 * If question is sort list type.
						 */
						case ConstantUtil.SORT_LIST_TYPE:
							getOptionsForSortListTypeQuestion(question[ques], givenAnser);
							break;
						/**
						 * If question is choice matrix type.
						 */
						case ConstantUtil.CHOICE_MATRIX_TYPE:
							getOptionsForChoiceMatrixTypeQuestion(question[ques], givenAnser);
							break;
						/**
						 * If question is classification type.
						 */
						case ConstantUtil.CLASSIFICATION_TYPE:
							getOptionsForCommonStructrueQuestion(question[ques], givenAnser);
							break;
						/**
						 * If question is match list type.
						 */
						case ConstantUtil.MATCH_LIST:
							getOptionsForCommonStructrueQuestion(question[ques], givenAnser);
							break;
						}
						ques++;
					}
					section[sec].setQuestionList(question);
					sec++;
				}
				test.setSectionlist(section);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service getAttemptedQuestionAnswerByUser method ::::::", e);
		}
		return test;
	}

	/**
	 * This is used getting option list for multiple choice type question review.
	 * 
	 * @param question
	 */
	public void getOptionsForMultipleTypeQuestion(Question question) {
		logger.log(Level.DEBUG, "Inside Student Test Service getOptionsForMultipleTypeQuestion method ::::::");
		try {
			/**
			 * getting option's detail for this question.
			 */
			QueryData dataOption = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID_QUEATTEMPTPK,
					new Object[] { question.getUserQueAttemptId(), question.getQuestionId() });

			List<Option> optionlist = new ArrayList<Option>();
			/**
			 * set option details.
			 */
			for (QueryData.Row row : dataOption.getRows()) {
				Option option = new Option();
				option.setOptionId(Integer.parseInt(row.getRowItem(0)));
				option.setOptionName(row.getRowItem(1));
				option.setOptionPosition(row.getRowItem(2).charAt(0));
				option.setAnswerStatus(Integer.parseInt(row.getRowItem(3)));
				option.setOptionPk(row.getRowItem(4) != null ? (Integer.parseInt(row.getRowItem(4))) : 0);
				optionlist.add(option);
			}
			question.setOptionList(optionlist);
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service getOptionsForMultipleTypeQuestion method ::::::", e);
		}
	}

	/**
	 * This is used getting option list for sort list type question review.
	 * 
	 * @param question
	 * @param givenAnswerJSON
	 */
	public void getOptionsForSortListTypeQuestion(Question question, String givenAnswerJSON) {
		logger.log(Level.DEBUG, "Inside Student Test Service getOptionsForSortListTypeQuestion method ::::::");
		try {
			/**
			 * getting option's detail for this question.
			 */
			QueryData dataOption = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID_QUEATTEMPTPK,
					new Object[] { question.getUserQueAttemptId(), question.getQuestionId() });

			List<Option> optionlist = new ArrayList<Option>();
			/**
			 * Initialize array list.
			 */
			List<Integer> givenAnswer = new ArrayList<Integer>();
			/**
			 * checking givenAnswer is not null.
			 */
			if (givenAnswerJSON != null) {
				/**
				 * make object of object mapper class.
				 */
				ObjectMapper mapper = new ObjectMapper();
				/**
				 * convert given answer json into list using object mapper class.
				 */
				givenAnswer = mapper.readValue(givenAnswerJSON, new TypeReference<List<Integer>>() {
				});
			}
			/**
			 * set given answer for question by user.
			 */
			question.setGivenAnswer(givenAnswer);
			/**
			 * set option details.
			 */
			for (QueryData.Row row : dataOption.getRows()) {
				Option option = new Option();
				option.setOptionId(Integer.parseInt(row.getRowItem(0)));
				option.setOptionName(row.getRowItem(1));
				option.setOptionPosition(row.getRowItem(2).charAt(0));
				optionlist.add(option);
			}
			question.setOptionList(optionlist);

		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service getOptionsForSortListTypeQuestion method ::::::", e);
		}
	}

	/**
	 * This is used getting option list for choice matrix type question review.
	 * 
	 * @param question
	 * @param givenAnswer
	 */
	public void getOptionsForChoiceMatrixTypeQuestion(Question question, String givenAnswer) {
		logger.log(Level.DEBUG, "Inside Student Test Service getOptionsForChoiceMatrixTypeQuestion method ::::::");
		try {
			/**
			 * initialize list for given answer.
			 */
			List<Object> choiceMatrixGivenAnswer = new ArrayList<Object>();
			/**
			 * checking given Answer is null.
			 */
			if (givenAnswer != null) {
				/**
				 * make object of object mapper class.
				 */
				ObjectMapper mapper = new ObjectMapper();
				/**
				 * convert given answer json into list using object mapper class.
				 */
				choiceMatrixGivenAnswer = mapper.readValue(givenAnswer, new TypeReference<List<Object>>() {
				});
			}
			/**
			 * getting option's detail for this question.
			 */
			QueryData dataOption = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID_QUEATTEMPTPK,
					new Object[] { question.getUserQueAttemptId(), question.getQuestionId() });
			List<Option> optionlist = new ArrayList<Option>();
			for (QueryData.Row row : dataOption.getRows()) {
				Option option = new Option();
				option.setOptionId(Integer.parseInt(row.getRowItem(0)));
				option.setOptionName(row.getRowItem(1));
				option.setOptionPosition(row.getRowItem(2).charAt(0));
				optionlist.add(option);
			}
			/**
			 * getting sub option's detail for this question.
			 */
			QueryData subOptionData = QueryManager.execuateQuery(QueryStrings.GET_SUB_OPTION_LIST_BY_QUES_ID,
					new Object[] { question.getQuestionId() });
			List<Option> suboptionlist = new ArrayList<Option>();
			for (QueryData.Row row : subOptionData.getRows()) {
				Option option = new Option();
				option.setOptionId(Integer.parseInt(row.getRowItem(0)));
				option.setOptionName(row.getRowItem(1));
				option.setOptionPosition(row.getRowItem(2).charAt(0));
				suboptionlist.add(option);
			}
			question.setChoiceMatrixGivenAnswer(choiceMatrixGivenAnswer);
			question.setOptionList(optionlist);
			question.setSubOptionList(suboptionlist);
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service getOptionsForChoiceMatrixTypeQuestion method ::::::", e);
		}
	}

	/**
	 * This is used getting option list for common structure type question review.
	 * 
	 * @param question
	 * @param givenAnswerJSON
	 */
	public void getOptionsForCommonStructrueQuestion(Question question, String givenAnswerJSON) {
		logger.log(Level.DEBUG, "Inside Student Test Service getOptionsForCommonStructrueQuestion method ::::::");
		try {
			/**
			 * getting option's detail for this question.
			 */
			QueryData dataOption = QueryManager.execuateQuery(QueryStrings.GET_OPTIONS_BY_QUESTIONID_QUEATTEMPTPK,
					new Object[] { question.getUserQueAttemptId(), question.getQuestionId() });

			List<Option> optionlist = new ArrayList<Option>();
			/**
			 * Initialize array list.
			 */
			List<Object> givenAnswer = new ArrayList<Object>();
			/**
			 * checking givenAnswer is not null.
			 */
			if (givenAnswerJSON != null) {
				/**
				 * make object of object mapper class.
				 */
				ObjectMapper mapper = new ObjectMapper();
				/**
				 * convert given answer json into list using object mapper class.
				 */
				givenAnswer = mapper.readValue(givenAnswerJSON, new TypeReference<List<Object>>() {
				});
			}
			/**
			 * set given answer for question by user.
			 */
			question.setUserGivenAnswer(givenAnswer);
			/**
			 * set option details.
			 */
			for (QueryData.Row row : dataOption.getRows()) {
				Option option = new Option();
				option.setOptionId(Integer.parseInt(row.getRowItem(0)));
				option.setOptionName(row.getRowItem(1));
				option.setOptionPosition(row.getRowItem(2).charAt(0));
				optionlist.add(option);
			}
			question.setOptionList(optionlist);

		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Student Test Service getOptionsForCommonStructrueQuestion method ::::::", e);
		}
	}

	/**
	 * This is used for counting all published test.
	 * 
	 * @param isPublished
	 * @return Integer
	 */
	public Integer countPublishedTest(Integer userId, Integer orgId, Integer isPublished) {
		logger.log(Level.DEBUG, "Inside Student Test Service countPublishedTest method ::::::");
		Integer totalTest = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_PUBLISHED_TEST,
					new Object[] { isPublished, orgId, userId });
			for (QueryData.Row row : data.getRows()) {
				totalTest = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service countPublishedTest method ::::::", e);
		}
		return totalTest;
	}

	/**
	 * This is used for counting all upcoming test.
	 * 
	 * @param isSchedule
	 * @return Integer
	 */
	public Integer countUpcomingTest(Integer userId, Integer orgId, Integer isSchedule) {
		logger.log(Level.DEBUG, "Inside Student Test Service countUpcomingTest method ::::::");
		Integer totalTest = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_UPCOMING_TEST,
					new Object[] { isSchedule, orgId, userId });
			for (QueryData.Row row : data.getRows()) {
				totalTest = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service countUpcomingTest method ::::::", e);
		}
		return totalTest;
	}

	/**
	 * A method for getting count of total attempted of a test by user based on
	 * userId and testId.
	 * 
	 * @param userid
	 * @param testId
	 * @return int
	 */
	public int getCountAttemptedTestByUser(Integer userid, Integer testId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getCountAttemptedTestByUser method ::::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COUNT_ATTEMPTED_TEST_BY_USER,
					new Object[] { userid, testId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getCountAttemptedTestByUser method ::::::",
					e);
		}
		return count;
	}

	/**
	 * A method for getting count of attempted test by user based on userId.
	 * 
	 * @param userid
	 * @return int
	 */
	public int countTotalLibrary(Integer userid) {
		logger.log(Level.DEBUG, "Inside Student Test Service countTotalLibrary method ::::::");
		int count = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_GIVEN_TEST_BY_USERID,
					new Object[] { userid });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					count = Integer.parseInt(row.getRowItem(0));
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service countTotalLibrary method ::::::", e);
		}
		return count;
	}

	/**
	 * Method is used for submitting given drafted test details after extracting
	 * from given test json.
	 * 
	 * @param userTestAttemptId
	 * @return UserTestAttempt
	 */
	public UserTestAttempt extractGivenTestData(int userTestAttemptId) {
		logger.log(Level.DEBUG, "Inside Student Test Service extractGivenTestData method ::::::");
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		try {
			String givenTestJson = "";
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_GIVEN_TEST_ANS_JSON,
					new Object[] { userTestAttemptId });
			for (QueryData.Row row : data.getRows()) {
				givenTestJson = row.getRowItem(0);
			}
			if (givenTestJson.length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				Test test = mapper.readValue(givenTestJson, Test.class);
				test.setTestStatus(Integer.parseInt(StudentEnum.SUBMIT.getValue()));
				userTestAttempt = submitTestDetails(test);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service extractGivenTestData method ::::::", e);
		}
		return userTestAttempt;
	}

	/**
	 * <p>
	 * Service for save note given by user for question at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId,testAttemptId,notes
	 * @return map
	 */
	public Map<String, Object> saveNotesToQuestion(Question question) {
		logger.log(Level.DEBUG, "Inside Student Test Service saveNotesToQuestion method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		try {
			if (question.getNoteId() == null) {
				id = QueryManager.execuateUpdate(QueryStrings.SAVE_QUESTION_NOTES_BY_USER,
						new Object[] { question.getQuestionId(), question.getUserId(), question.getUserTestAttemptId(),
								question.getNotes() });
			} else {
				id = QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_NOTES_BY_USER,
						new Object[] { question.getNotes(), question.getNoteId() });
			}

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
			logger.log(Level.ERROR, "Exception Inside Student Test Service saveNotesToQuestion method ::::::", e);
		}
		return map;
	}

	/**
	 * <p>
	 * Service for get note given by user for question at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId,testAttemptId
	 * @return map
	 */
	public Map<String, Object> getNoteOfQuestion(Question question) {
		logger.log(Level.DEBUG, "Inside Student Test Service getNoteOfQuestion method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {

			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_NOTES_BY_USER,
					new Object[] { question.getUserId(), question.getQuestionId(), question.getUserTestAttemptId() });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					map.put("note", row.getRowItem(0) != null ? row.getRowItem(0) : "");
					map.put("noteId", row.getRowItem(1) != null ? Integer.parseInt(row.getRowItem(1)) : "");
				}
			} else {
				map.put("note", "");
				map.put("noteId", "");
			}

		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Test Service getNoteOfQuestion method ::::::", e);
		}
		return map;
	}

	/**
	 * <p>
	 * Service for set favorite question by user at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId and favorites status (1
	 *                 for yes,0 for no)
	 * @return map
	 */
	public Map<String, Object> setResetFavoriateQuestion(Question question) {
		logger.log(Level.DEBUG, "Inside Student Test Service setResetFavoriateQuestion method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer id = null;
		int favoriateId = 0;
		try {
			if (question.getFavoriateId() == 0) {
				if (question.getFavorites() == 1) {
					id = QueryManager.execuateUpdate(QueryStrings.SAVE_QUESTION_FAVORIATE_BY_USER,
							new Object[] { question.getQuestionId(), question.getUserId(), question.getFavorites() });
					favoriateId = id;
				} else {
					id = QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_FAVORIATE_BY_USER,
							new Object[] { question.getFavorites(), question.getQuestionId(), question.getUserId(), });
					favoriateId = getQuestionFavoriateId(question.getQuestionId(), question.getUserId(),
							question.getFavorites());
				}
			} else {
				id = QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_FAVORIATE_BY_USER,
						new Object[] { question.getFavorites(), question.getQuestionId(), question.getUserId(), });
				favoriateId = question.getFavoriateId();
			}

			if (id > 0) {
				map.put("status", 200);
				map.put("flag", question.getFavorites());
				map.put("favoriateId", favoriateId);
				map.put("msg", "Success");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation Failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Test Service setResetFavoriateQuestion method ::::::", e);
		}
		return map;
	}

	/**
	 * <p>
	 * method for get favoriateId base on userId and questionId,favorites
	 * </p>
	 * 
	 * @param questionId ,userId,favorites
	 * @return favoriateId
	 */
	private int getQuestionFavoriateId(Integer questionId, Integer userId, int favorites) {
		logger.log(Level.DEBUG, "Inside Student Test Service getQuestionFavoriateId method ::::::");
		int favid = 0;
		try {

			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_FAVORIATEID,
					new Object[] { questionId, userId, favorites });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					favid = Integer.parseInt(row.getRowItem(0));
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service getQuestionFavoriateId method ::::::", e);
		}
		return favid;
	}

	/**
	 * <p>
	 * Service for get notes list by user
	 * </p>
	 * 
	 * @param userId
	 * @return map
	 */
	public Map<String, Object> getAllQuesNotesList(Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getAllQuesNotesList method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<Question> quelist = new ArrayList<Question>();
			QueryData data = QueryManager.execuateQuery(QueryStrings.LIST_QUESTION_NOTES_BY_USERID,
					new Object[] { userId });
			Question que = null;
			for (QueryData.Row row : data.getRows()) {
				que = new Question();
				que.setQuestionName(row.getRowItem(0));
				que.setNotes(row.getRowItem(1));
				quelist.add(que);
			}
			map.put("notelist", quelist);
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Test Service getAllQuesNotesList method ::::::", e);
		}
		return map;
	}

	/**
	 * <p>
	 * Method for get favorite questions list by user
	 * </p>
	 * 
	 * @param userId
	 * @return map
	 */
	public Map<String, Object> getFavoriteQuestionList(Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getFavoriteQuestionList method ::::::");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<Question> quelist = new ArrayList<Question>();
			QueryData data = QueryManager.execuateQuery(QueryStrings.LIST_FAVORITE_QUESTION_BY_USERID,
					new Object[] { userId });
			Question que = null;
			for (QueryData.Row row : data.getRows()) {
				que = new Question();
				que.setQuestionId(Integer.parseInt(row.getRowItem(0)));
				que.setQuestionName(row.getRowItem(1));
				quelist.add(que);
			}
			map.put("favoritequelist", quelist);
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(Level.ERROR, "Exception Inside Student Test Service getFavoriteQuestionList method ::::::", e);
		}
		return map;
	}

	/**
	 * This is used for counting all published test of a particular user.
	 * 
	 * @param isPublished
	 * @param userId
	 * @return Integer
	 */
	public Integer countPublishedTestByUserId(Integer isPublished, int userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service countPublishedTestByUserId method ::::::");
		Integer totalTest = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.COUNT_TOTAL_PUBLISHED_TEST_BY_USERID,
					new Object[] { isPublished, userId });
			for (QueryData.Row row : data.getRows()) {
				totalTest = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Student Test Service countPublishedTestByUserId method ::::::",
					e);
		}
		return totalTest;
	}

	/**
	 * This method is used for checking test is submitted or not.
	 * 
	 * @param testAttemptedId
	 * @return
	 */
	public boolean checkTestSubmitted(Integer testAttemptedId) {
		QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_TEST_SUBMITTED,
				new Object[] { testAttemptedId, 1 });
		if (data.getRows().size() > 0) {
			return true;
		}
		return false;
	}

	public Double getTestPerformance(Integer userId, Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getTestPerformance method ::::::");

		Float correct_marks = 0.0f;
		Float wrong_marks = 0.0f;
		Float result = 0.0f;
		Float performance_result = 0.0f;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_OVERALL_PERFORMANCE,
					new Object[] { userId, sectionId });

			for (QueryData.Row sectionRow : data.getRows()) {
				correct_marks = correct_marks + Float.parseFloat(sectionRow.getRowItem(0));
				wrong_marks = wrong_marks + Float.parseFloat(sectionRow.getRowItem(1));
				result = result + Float.parseFloat(sectionRow.getRowItem(2));
			}
			performance_result = result * 100 / correct_marks + wrong_marks;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return Math.floor(performance_result);
	}

	public Float getPerformanceTillNow(Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getTestPerformanceTillNOw method ::::::");
		System.out.println("userId" + userId);
		{
			float correctque = 0.0f;
			float wrongque = 0.0f;
			float unattemptedque = 0.0f;
			float overallperformance = 0.0f;
			try {
				QueryData cont = QueryManager.execuateQuery(QueryStrings.GET_PERFORMANCE_TILL_DATE,
						new Object[] { userId });
				for (QueryData.Row rowCont : cont.getRows()) {
					Integer corr = Integer.parseInt(rowCont.getRowItem(0) != null ? rowCont.getRowItem(0) : "0");
					correctque = correctque + corr;
					Integer wrong = Integer.parseInt(rowCont.getRowItem(1) != null ? rowCont.getRowItem(1) : "0");
					wrongque = wrongque + wrong;
					Integer unattempt = Integer.parseInt(rowCont.getRowItem(2) != null ? rowCont.getRowItem(2) : "0");
					unattemptedque = unattemptedque + unattempt;
				}
				System.out.println("Correct" + correctque + "Wrong" + wrongque + "UAttempted" + unattemptedque);
				overallperformance = (correctque / (correctque + wrongque + unattemptedque)) * 100;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (float) Math.round(overallperformance);
		}
	}

	public Integer getoverallperformance(Integer courseId, Integer user_id) {
		logger.log(Level.DEBUG, "Inside Student Test Service getOverAllPerformance method ::::::");
		Integer sessionCount = 0;
		Integer viewedSession = 0;
		Integer overallperformance = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_OVERALL_SESSIONS_IN_COURSE,
					new Object[] { courseId });

			for (QueryData.Row sectionRow : data.getRows()) {
				sessionCount = Integer.parseInt(sectionRow.getRowItem(0));
			}
			viewedSession = getTotalViewSession1(courseId, user_id);
			overallperformance = viewedSession * 100 / sessionCount;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return overallperformance;
	}

	public Integer getTotalViewSession1(Integer courseId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Student Test Service getTotalViewSession1 method:::::::::::");
		Integer count = 0;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TOTAL_VIDEO_SESSION1,
				new Object[] { courseId, userId });
		for (QueryData.Row row : data.getRows()) {

			count = Integer.parseInt(row.getRowItem(0));

		}
		return count;
	}

}
