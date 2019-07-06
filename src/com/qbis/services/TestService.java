package com.qbis.services;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.ConstantUtil;
import com.qbis.common.EmailSender;
import com.qbis.common.QbisUtils;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.Status;
import com.qbis.gcm.NotificationManagement;
import com.qbis.model.Question;
import com.qbis.model.QuestionSetting;
import com.qbis.model.Section;
import com.qbis.model.ServiceResult.TagMappingType;
import com.qbis.model.Tag;
import com.qbis.model.Test;
import com.qbis.model.User;

/**
 * This is used for performing all operations related to test.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class TestService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(TestService.class);
       
	@Autowired
	private CourseService courseService;
	/**
	 * This is used for saving the test details.
	 * 
	 * @param test
	 * @return Boolean
	 */
	public Boolean saveTestDetails(Test test) {
		logger.log(Level.DEBUG, "Inside Test Service saveTestDetails method:::::::::::");
		boolean status = false;
		try {
			Integer testId = QueryManager.execuateUpdate(QueryStrings.SAVE_TEST_DETAIL,
					new Object[] { test.getTestName(), test.getTestDesc(), test.getTestInstruct(), test.getTestTag(),
							test.getNegMark(), test.getTestTime(), test.getTestPause(), test.getTestAdaptive(),
							test.getUserId(), test.getCompanyId(), test.getView(), test.getEqualMarkTest(),
							test.getEveryQuestionMark(), test.getMaxAttempts(), test.getIsReview(),
							test.getReviewWithCorrect(),
							test.getShuffleSection() == null ? 0 : test.getShuffleSection(),
							test.getShuffleQuestion() == null ? 0 : test.getShuffleQuestion(),
							test.getShuffleOption() == null ? 0 : test.getShuffleOption(), test.getIsSchedule(),
							test.getSchedulePublishDate(), test.getIsRandom(), test.getMaxQuestions() });
			if (testId > 0) {
				test.setTestId(testId);
				addUpdateTestTags(testId, test.getTagList());
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service saveTestDetails method:::::", e);
		}
		return status;
	}

	/**
	 * This method is used for adding and updating the test tags.
	 * 
	 * @param testId
	 *            {@link Integer}
	 * @param tagList
	 *            {@link List}
	 */
	public void addUpdateTestTags(Integer testId, List<Tag> tagList) {
		logger.log(Level.DEBUG, "Inside Test Service addUpdateTestTags method:::::::::::");
		removeTestTags(testId);
		for (int i = 0; i < tagList.size(); i++) {
			Tag tag = tagList.get(i);
			QueryManager.execuateUpdate(QueryStrings.SAVE_TAGS_MAPPING,
					new Object[] { tag.getId(), tag.getValue(), testId, TagMappingType.TEST.getValue() });
		}
	}

	/**
	 * This method is used for removing the test tags.
	 * 
	 * @param testId
	 *            {@link Integer}
	 */
	public void removeTestTags(Integer testId) {
		logger.log(Level.DEBUG, "Inside Test Service removeTestTags method:::::::::::");
		QueryManager.execuateQuery(QueryStrings.REMOVE_TAGS_MAPPING,
				new Object[] { testId, TagMappingType.TEST.getValue() });
	}

	/**
	 * A Method for updating the test Details.
	 * 
	 * @param test
	 * @return Boolean
	 */
	public Boolean updateTestDetails(Test test) {
		logger.log(Level.DEBUG, "Inside Test Service updateTestDetails method:::::::::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_TEST_DETAIL, new Object[] { test.getTestName(),
					test.getTestDesc(), test.getTestInstruct(), test.getTestTag(), test.getNegMark(),
					test.getTestTime(), test.getTestPause(), test.getTestAdaptive(), test.getView(),
					test.getEqualMarkTest(), test.getEveryQuestionMark(), test.getMaxAttempts(), test.getIsReview(),
					test.getReviewWithCorrect(), test.getShuffleSection() == null ? 0 : test.getShuffleSection(),
					test.getShuffleQuestion() == null ? 0 : test.getShuffleQuestion(),
					test.getShuffleOption() == null ? 0 : test.getShuffleOption(), test.getIsSchedule(),
					test.getSchedulePublishDate(), test.getIsRandom(), test.getMaxQuestions(), test.getTestId() });
			if (id > 0) {
				addUpdateTestTags(test.getTestId(), test.getTagList());
				/**
				 * Update data in question mapping according to test's data
				 * changes.
				 */
				if (test.getEqualMarkTest() == 1) {
					Double negMark = (test.getEveryQuestionMark() * test.getNegMark()) / 100.0;
					negMark = Double.parseDouble(new DecimalFormat("##.####").format(negMark));
					QueryManager.execuateUpdate(QueryStrings.UPDATE_QUES_MAPPING_DETAIL_ON_TEST_UPDATE,
							new Object[] { test.getEveryQuestionMark(), negMark, test.getTestId() });
				} else {
					/**
					 * Update negative mark of each question in question table.
					 */
					QueryManager.execuateUpdate(QueryStrings.UPDATE_QUES_NEG_MARK_ON_TEST_UPDATE,
							new Object[] { test.getNegMark(), test.getTestId() });
				}
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service updateTestDetails method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for getting test details based on user id
	 * 
	 * @param userId
	 * @return List<Test>
	 */
	public List<Test> getTestDetails(int userId) {
		logger.log(Level.DEBUG, "Inside Test Service getTestDetails method:::::::::::");
		List<Test> testList = new ArrayList<Test>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.TEST_LIST_BY_USER_ID, new Object[] { userId });
		for (QueryData.Row row : data.getRows()) {
			Test test = new Test();
			test.setTestId(Integer.parseInt(row.getRowItem(0)));
			test.setTestCreatedDate(row.getRowItem(1));
			test.setTestName(row.getRowItem(2).substring(0, 1).toUpperCase() + row.getRowItem(2).substring(1));
			test.setTestTag(row.getRowItem(3));
			if (row.getRowItem(4) != null) {
				test.setTotalAppearStudents(Integer.parseInt(row.getRowItem(4)));
			} else {
				test.setTotalAppearStudents(0);
			}

			if (row.getRowItem(5) != null) {
				test.setMaxMark(Double.valueOf(row.getRowItem(5)));
			} else {
				test.setMaxMark(0.0);
			}
			if (row.getRowItem(6) != null) {
				test.setMinMark(Double.valueOf(row.getRowItem(6)));
			} else {
				test.setMinMark(0.0);
			}
			if (row.getRowItem(7) != null) {
				test.setAvgMark(Double.valueOf(row.getRowItem(7)));
			} else {
				test.setAvgMark(0.0);
			}
			test.setView(Integer.parseInt(row.getRowItem(8)));
			test.setTestPublishStatus(Integer.parseInt(row.getRowItem(9)));
			testList.add(test);
		}
		return testList;
	}

	/**
	 * This is used for getting test details based on user id
	 * 
	 * @param userId
	 * @param isPublish
	 * @return List<Test>
	 */
	public List<Test> getTestPublishedDraftedDetails(int userId, Integer isPublish) {
		logger.log(Level.DEBUG, "Inside Test Service getTestPublishedDraftedDetails method:::::::::::");
		List<Test> testList = new ArrayList<Test>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.ALL_TEST_LIST_BY_USER_ID,
				new Object[] { userId, isPublish });
		for (QueryData.Row row : data.getRows()) {
			Test test = new Test();
			test.setTestId(Integer.parseInt(row.getRowItem(0)));
			test.setTestCreatedDate(row.getRowItem(1));
			test.setTestName(row.getRowItem(2).substring(0, 1).toUpperCase() + row.getRowItem(2).substring(1));
			String tag = "";
			if ((row.getRowItem(3)).contains(",")) {
				String tagArray[] = (row.getRowItem(3)).replaceAll(" ", "").split(",");
				for (int s = 0; s < tagArray.length; s++) {
					if (s == 0) {
						tag = tag + "#" + tagArray[s];
					} else {
						tag = tag + ", #" + tagArray[s];
					}
				}
			} else {
				tag = "#" + row.getRowItem(3);
			}
			test.setTestTag(tag);
			if (row.getRowItem(4) != null) {
				test.setTotalAppearStudents(Integer.parseInt(row.getRowItem(4)));
			} else {
				test.setTotalAppearStudents(0);
			}

			if (row.getRowItem(5) != null) {
				test.setMaxMark(Double.valueOf(row.getRowItem(5)));
			} else {
				test.setMaxMark(0.0);
			}
			if (row.getRowItem(6) != null) {
				test.setMinMark(Double.valueOf(row.getRowItem(6)));
			} else {
				test.setMinMark(0.0);
			}
			if (row.getRowItem(7) != null) {
				test.setAvgMark(Double.valueOf(row.getRowItem(7)));
			} else {
				test.setAvgMark(0.0);
			}
			test.setView(Integer.parseInt(row.getRowItem(8)));
			test.setTestPublishStatus(Integer.parseInt(row.getRowItem(9)));
			test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(10));
			test.setIsSchedule(Integer.parseInt(row.getRowItem(11)));
			test.setSchedulePublishDate(row.getRowItem(12));
			testList.add(test);
		}
		return testList;
	}

	/**
	 * This is used for getting list of test based on status,limit,offset and
	 * user id.
	 * 
	 * @param userId
	 * @param isPublish
	 * @param limit
	 * @param offset
	 * @return List<Test>
	 */
	public List<Test> getTestPublishedDraftedList(Integer userId, Integer isPublish, Integer limit, Integer offset) {
		logger.log(Level.DEBUG, "Inside Test Service getTestPublishedDraftedList method:::::::::::");
		List<Test> testList = new ArrayList<Test>();
		int skip = offset * limit;
		QueryData data = QueryManager.execuateQuery(QueryStrings.ALL_TEST_LIST_BY_USER_ID_WITH_PAGINATION,
				new Object[] { userId, isPublish, limit, skip });
		for (QueryData.Row row : data.getRows()) {
			Test test = new Test();
			test.setTestId(Integer.parseInt(row.getRowItem(0)));
			test.setTestCreatedDate(row.getRowItem(1));
			test.setTestName(row.getRowItem(2).substring(0, 1).toUpperCase() + row.getRowItem(2).substring(1));
			String tag = "";
			if ((row.getRowItem(3)).contains(",")) {
				String tagArray[] = (row.getRowItem(3)).replaceAll(" ", "").split(",");
				for (int s = 0; s < tagArray.length; s++) {
					if (s == 0) {
						tag = tag + "#" + tagArray[s];
					} else {
						tag = tag + ", #" + tagArray[s];
					}
				}
			} else {
				tag = "#" + row.getRowItem(3);
			}
			test.setTestTag(tag);
			if (row.getRowItem(4) != null) {
				test.setTotalAppearStudents(Integer.parseInt(row.getRowItem(4)));
			} else {
				test.setTotalAppearStudents(0);
			}

			if (row.getRowItem(5) != null) {
				test.setMaxMark(Double.valueOf(row.getRowItem(5)));
			} else {
				test.setMaxMark(0.0);
			}
			if (row.getRowItem(6) != null) {
				test.setMinMark(Double.valueOf(row.getRowItem(6)));
			} else {
				test.setMinMark(0.0);
			}
			if (row.getRowItem(7) != null) {
				test.setAvgMark(Double.valueOf(row.getRowItem(7)));
			} else {
				test.setAvgMark(0.0);
			}
			test.setView(Integer.parseInt(row.getRowItem(8)));
			test.setTestPublishStatus(Integer.parseInt(row.getRowItem(9)));
			test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(10));
			test.setIsSchedule(Integer.parseInt(row.getRowItem(11)));
			test.setSchedulePublishDate(row.getRowItem(12));
			testList.add(test);
		}
		return testList;
	}

	/**
	 * A method for getting section list with questions for edit.
	 * 
	 * @param testId
	 * @return Section[]
	 */
	public Section[] getQuestionDetailforEdit(int testId) {
		logger.log(Level.DEBUG, "Inside Test Service getQuestionDetailforEdit method:::::::::::");
		Section section[] = null;
		try {
			QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_FOR_EDIT,
					new Object[] { testId });
			section = new Section[sectiondata.getRows().size()];
			QuestionService questionService = new QuestionService();
			int z = 0;
			for (QueryData.Row sectionRow : sectiondata.getRows()) {
				section[z] = new Section();
				section[z].setTestId(testId);
				section[z].setSectionId(Integer.parseInt(sectionRow.getRowItem(0)));
				section[z].setSectionName(sectionRow.getRowItem(1));
				section[z].setTestId(Integer.parseInt(sectionRow.getRowItem(2)));
				section[z].setSectionSortOrder(1);
				section[z].setIsUpdate(1);
				section[z].setIsDelete(0);
				int sectionScore = 0;
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONLIST_FOR_EDIT,
						new Object[] { section[z].getSectionId() });
				Question question[] = new Question[data.getRows().size()];
				int j = 0;
				for (QueryData.Row row : data.getRows()) {
					question[j] = new Question();
					question[j].setQuestionId(Integer.parseInt(row.getRowItem(0)));
					question[j].setQuestionName(row.getRowItem(1));
					question[j].setExplanation(row.getRowItem(2));
					question[j].setQuestionNo(Integer.parseInt(row.getRowItem(3)));
					question[j].setQuestionMark(Integer.parseInt(row.getRowItem(4)));
					question[j].setQuestionType(Integer.parseInt(row.getRowItem(5)));
					question[j].setNegMark(row.getRowItem(6) == null ? 0 : Float.parseFloat(row.getRowItem(6)));
					question[j].setIsFormula(Integer.parseInt(row.getRowItem(7)));
					question[j].setMathFormula(row.getRowItem(8));
					question[j].setIsParent(row.getRowItem(9) == null ? 0 : Integer.parseInt(row.getRowItem(9)));
					question[j].setIsNew(0);
					question[j].setAnswerValue(row.getRowItem(10));
					/**
					 * checking that question's setting is not null
					 */
					if (row.getRowItem(11) != null) {
						ObjectMapper mapper = new ObjectMapper();
						QuestionSetting questionSetting = mapper.readValue(row.getRowItem(11),
								new TypeReference<QuestionSetting>() {
								});
						question[j].setQuestionSetting(questionSetting);
					}
					sectionScore = sectionScore + Integer.parseInt(row.getRowItem(4));
					switch (question[j].getQuestionType()) {
					case ConstantUtil.MULTIPLE_CHOICE_TYPE:
						questionService.getOptionForMultiChoiceQuestion(question[j]);
						break;
					case ConstantUtil.SINGLE_CHOICE_TYPE:
						questionService.getOptionForMultiChoiceQuestion(question[j]);
						break;
					case ConstantUtil.SORT_LIST_TYPE:
						questionService.getOptionForMultiChoiceQuestion(question[j]);
						break;
					case ConstantUtil.CHOICE_MATRIX_TYPE:
						questionService.getOptionForChoiceMatrixQuestion(question[j]);
						break;
					case ConstantUtil.CLASSIFICATION_TYPE:
						questionService.getOptionForMultiChoiceQuestion(question[j]);
						break;
					case ConstantUtil.MATCH_LIST:
						questionService.getOptionForMultiChoiceQuestion(question[j]);
						break;
					}
					j++;
				}
				section[z].setSectionScore(sectionScore);
				section[z].setQuestionList(question);
				z++;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getQuestionDetailforEdit method:::::", e);
		}
		return section;
	}

	
	public Integer getTestQuestion(Integer testId,Integer userId)
	{
		logger.log(Level.DEBUG, "Inside Test Service getTestDetailsforEdit method:::::::::::");
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TEST_ID_DETAIL1,
				new Object[] { testId,userId});
		List<Question> questionList=new ArrayList();
		Integer isRandom=0;
		Integer no=0;
		for (QueryData.Row row : data.getRows()) {
		       isRandom=Integer.parseInt(row.getRowItem(0));
		    
		}
		if(isRandom == 1)
		{
			List<String> allValue=courseService.getTagList(testId);
			questionList = courseService.getRandomQuestion(allValue,testId);
			System.out.println("size================="+questionList.size());
			no=questionList.size();
		}
		return no;
	}
	/**
	 * A method for getting test details for updating the test details.
	 * 
	 * @param testId
	 * @param userId
	 * @return Test
	 */
	public Test getTestDetailsforEdit(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Test Service getTestDetailsforEdit method:::::::::::");
		Test test = new Test();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TESTDETAILS_FOR_EDIT,
					new Object[] { testId, userId });
			for (QueryData.Row row : data.getRows()) {
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestDesc(row.getRowItem(2));
				test.setTestInstruct(row.getRowItem(3));
				test.setTestTag(row.getRowItem(4));
				test.setMaxAttempts(row.getRowItem(5) == null ? 0 : Integer.parseInt(row.getRowItem(5)));
				test.setNegMark(Integer.parseInt(row.getRowItem(6)));
				test.setTestTime(row.getRowItem(7) == null ? "0" : row.getRowItem(7));
				test.setTestAdaptive(Integer.parseInt(row.getRowItem(8)));
				test.setTestPause(Integer.parseInt(row.getRowItem(9)));
				test.setView(Integer.parseInt(row.getRowItem(10)));
				test.setEqualMarkTest(Integer.parseInt(row.getRowItem(11)));
				test.setEveryQuestionMark(row.getRowItem(12) == null ? 0 : Integer.parseInt(row.getRowItem(12)));
				test.setIsReview(Integer.parseInt(row.getRowItem(13)));
				test.setReviewWithCorrect(row.getRowItem(14) != null ? Integer.parseInt(row.getRowItem(14)) : null);
				test.setShuffleSection(row.getRowItem(15) != null ? Integer.parseInt(row.getRowItem(15)) : 0);
				test.setShuffleQuestion(row.getRowItem(16) != null ? Integer.parseInt(row.getRowItem(16)) : 0);
				test.setShuffleOption(row.getRowItem(17) != null ? Integer.parseInt(row.getRowItem(17)) : 0);
				test.setTestIcon(row.getRowItem(18));
				test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(18));
				test.setIsSchedule(Integer.parseInt(row.getRowItem(19)));
				test.setSchedulePublishDate(
						row.getRowItem(20) != null ? QbisUtils.convertMysqlDateFormatToDate(row.getRowItem(20)) : null);
				test.setIsRandom(row.getRowItem(21) != null ? Integer.parseInt(row.getRowItem(21)) : 0);
				test.setMaxQuestions(row.getRowItem(22) != null ? Integer.parseInt(row.getRowItem(22)) : 0);
				test.setTagList(getTestTagList(testId));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getTestDetailsforEdit method:::::", e);
		}
		return test;
	}

	/**
	 * This method is used for getting tag list.
	 * 
	 * @param testId
	 * @return
	 */
	public List<Tag> getTestTagList(Integer testId) {
		logger.log(Level.DEBUG, "Inside Test Service getTestTagList method:::::::::::");
		List<Tag> tagList = new ArrayList<Tag>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TAGS_MAPPING,
				new Object[] { testId, TagMappingType.TEST.getValue() });
		for (QueryData.Row row : data.getRows()) {
			Tag tag = new Tag();
			tag.setId(row.getRowItem(0));
			tag.setValue(row.getRowItem(1));
			tagList.add(tag);
		}
		return tagList;
	}

	/**
	 * This is used for extracting the test details for test saving operation.
	 * 
	 * @param test
	 * @return Integer
	 * @throws ParseException
	 */
	public Integer extractTestDetailsForSaving(Test test) throws ParseException {
		logger.log(Level.DEBUG, "Inside Test Service extractTestDetailsForSaving method:::::::::::");

		Integer testId = 0;
		/**
		 * A boolean for identify that test's details has been update or save.
		 */
		boolean testStatus = false;
		/**
		 * set negative mark for test.
		 */
		test.setNegMark(test.getNegMark() == null ? 0 : test.getNegMark());
		/**
		 * set that test is equal mark test or not.
		 */
		test.setEqualMarkTest(test.getEqualMarkTest() == null ? 0 : test.getEqualMarkTest());
		/**
		 * get time in minute for test..
		 */
		if (test.getTimeMinute() != null) {
			int MM = test.getTimeMinute();
			int HH = MM / 60;
			MM = MM % 60;
			int SS = 0;
			String timeStamp = HH + ":" + MM + ":" + SS;
			test.setTestTime(timeStamp);
		}
		/**
		 * set tag for test.
		 */
		test.setTestTag(test.getTestTag().replaceAll(",", ", "));
		/**
		 * if test's review is enable.
		 */
		if (test.getIsReview() != null) {
			/**
			 * set for identify that test is review enabled.
			 */
			test.setIsReview(1);
		} else {
			/**
			 * set for identify that test is not review enabled.
			 */
			test.setIsReview(0);
			test.setReviewWithCorrect(null);
		}
		/**
		 * isRandom
		 */
		if (test.getIsRandom() != null && test.getIsRandom() == 1) {
			test.setIsRandom(1);
		} else {
			test.setIsRandom(0);
			test.setMaxQuestions(0);
		}
		/**
		 * if test's publish type is schedule.
		 */
		if (test.getIsSchedule() != null && test.getIsSchedule() == 1 && test.getSchedulePublishDate() != null) {
			String date = QbisUtils.convertDatetoMysqlDateFormat(test.getSchedulePublishDate().trim());
			test.setSchedulePublishDate(date);
		}
		/**
		 * if test's publish type is immediate.
		 */
		else {
			test.setIsSchedule(0);
			test.setSchedulePublishDate(null);
		}
		/**
		 * identify that test has in saving mode.
		 */
		if (test.getTestId() == null) {
			/**
			 * save test details
			 */
			testStatus = saveTestDetails(test);
			/**
			 * identify that this test is in course as content
			 */
			if (test.getCourseId() != null && test.getSectionId() != null) {
				/**
				 * save test info in course as content
				 */
				new CourseService().saveTestAsCourseContent(test);
			}
		}
		/**
		 * identify that test has in edit mode so needed update the test info.
		 */
		else if (test.getTestId() > 0) {
			/**
			 * update the test details
			 */
			testStatus = updateTestDetails(test);
			/**
			 * identify that this test is in course as content
			 */
			if (test.getCourseId() != null && test.getSectionId() != null && test.getContentId() != null) {
				/**
				 * update test info in course as content
				 */
				new CourseService().updateTestAsCourseContent(test);
			}
		}
		/**
		 * if test has been successfully saved or updated
		 */
		if (testStatus) {
			/**
			 * set test id
			 */
			testId = test.getTestId();
		}
		return testId;
	}

	/**
	 * A Method for saving the questions details with answer
	 * 
	 * @param section
	 * @param test
	 * @return boolean
	 */
	public boolean saveSectionDetails(Section section, Test test) {
		logger.log(Level.DEBUG, "Inside Test Service saveSectionDetails method:::::::::::");
		boolean status = false;
		int sectionMark = 0;
		QuestionService questionService = new QuestionService();
		try {
			/**
			 * check section is new created.
			 */
			if (section.getIsUpdate() == 0) {
				/**
				 * save new created section details.
				 */
				Integer sectionId = QueryManager.execuateUpdate(QueryStrings.SAVE_SECTION_DETAIL,
						new Object[] { section.getSectionName(), section.getTestId() });
				section.setSectionId(sectionId);
			} else {
				/**
				 * Update data of already created section.
				 */
				QueryManager.execuateUpdate(QueryStrings.UPDATE_SECTION_DETAILS,
						new Object[] { section.getSectionName(), section.getSectionId(), section.getTestId() });
				/**
				 * remove section question mapping from test.
				 */
				QueryManager.execuateQuery(QueryStrings.REMOVE_QUESTION_MAPPING,
						new Object[] { section.getSectionId() });
			}
			if (section.getSectionId() > 0) {

				for (int i = 0; i < section.getQuestionList().length; i++) {
					/**
					 * add question mark in section score.
					 */
					sectionMark = sectionMark + section.getQuestionList()[i].getQuestionMark();
					Integer questionId = 0;
					/**
					 * check question is new created in this test.
					 */
					boolean isVal = false;
					if(isVal) {
					//if (section.getQuestionList()[i].getIsParent() == null
					//		|| section.getQuestionList()[i].getIsParent() == 0) {
						/**
						 * make cases for saving the question details based on
						 * question type.
						 */
						switch (section.getQuestionList()[i].getQuestionType()) {
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							questionId = questionService.saveQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							questionId = questionService.saveQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.SORT_LIST_TYPE:
							questionId = questionService.saveQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.CHOICE_MATRIX_TYPE:
							questionId = questionService.saveChoiceMatrixTypeQuestion(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.CLASSIFICATION_TYPE:
							questionId = questionService.saveQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.MATCH_LIST:
							questionId = questionService.saveQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						}

					} else {
						/**
						 * make cases for update the question details based on
						 * question type.
						 */
						switch (section.getQuestionList()[i].getQuestionType()) {
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							questionId = questionService.updateQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							questionId = questionService.updateQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.SORT_LIST_TYPE:
							questionId = questionService.updateQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.CHOICE_MATRIX_TYPE:
							questionId = questionService.updateChoiceMatrixTypeQuestion(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.CLASSIFICATION_TYPE:
							questionId = questionService.updateQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						case ConstantUtil.MATCH_LIST:
							questionId = questionService.updateQuesDetailForCommonTypeQues(test.getUserId(),
									section.getQuestionList()[i]);
							break;
						}
					}
					if (questionId > 0) {
						/**
						 * insert mapping of this question with section.
						 */
						QueryManager.execuateUpdate(QueryStrings.INSERT_QUESTION_MAP,
								new Object[] { section.getSectionId(), questionId,
										section.getQuestionList()[i].getQuestionNo(),
										section.getQuestionList()[i].getQuestionMark(),
										section.getQuestionList()[i].getNegMark() });
						status = true;
					}
				}

				/**
				 * Update section marks of already created section.
				 */
				QueryManager.execuateQuery(QueryStrings.SECTION_MARK_UPDATE,
						new Object[] { sectionMark, section.getSectionId() });
				test.setTestId(section.getTestId());
				test.setTestMark(sectionMark + test.getTestMark());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service saveSectionDetails method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for extracting question's data for saving.
	 * 
	 * @param sectionJson
	 * @param userId
	 * @return Boolean
	 */
	public Boolean performQuestionSaving(String sectionJson, Integer userId) {
		logger.log(Level.DEBUG, "Inside Test Service performQuestionSaving method:::::::::::");
		boolean status = false;
		Test test = new Test();
		test.setTestMark(0);
		try {
			ObjectMapper mapper = new ObjectMapper();
			Section section[] = mapper.readValue(sectionJson, new TypeReference<Section[]>() {
			});
			test.setUserId(userId);
			for (Section sec : section) {
				/**
				 * checking the section is deleted or not.
				 */
				if (sec.getIsDelete() == 1) {
					/**
					 * checking that section was already created.
					 */
					if (sec.getIsUpdate() == 1) {
						/**
						 * remove question mapping.
						 */
						QueryManager.execuateUpdate(QueryStrings.REMOVE_QUESTION_MAPPING,
								new Object[] { sec.getSectionId() });
						/**
						 * Delete section data from test.
						 */
						QueryManager.execuateUpdate(QueryStrings.DELETE_TEST_SECTION,
								new Object[] { sec.getTestId(), sec.getSectionId() });
						status = true;
						test.setTestId(sec.getTestId());
					}

				} else {
					/**
					 * perform operation for section details.
					 */
					saveSectionDetails(sec, test);
					status = true;
				}
			}
			if (status) {
				saveTestMark(test);
				status = true;
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service performQuestionSaving method:::::", e);
		}
		return status;
	}

	/**
	 * A Method for updating marks for section and a test after submitting the
	 * questions details.
	 * 
	 * @param test
	 * @return boolean
	 */
	public boolean saveTestMark(Test test) {
		logger.log(Level.DEBUG, "Inside Test Service saveTestMark method:::::::::::");
		boolean status = false;
		try {
			QueryManager.execuateQuery(QueryStrings.TEST_MARK_UPDATE,
					new Object[] { test.getTestMark(), test.getTestId() });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service saveTestMark method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for updating the share status of test.
	 * 
	 * @param testId
	 * @return Boolean
	 */
	public Boolean updateTestSharedStatus(Integer testId) {
		logger.log(Level.DEBUG, "Inside Test Service updateTestSharedStatus method:::::::::::");
		Boolean isUpdated = false;
		try {
			/**
			 * update publish status of test.
			 */
			int id = QueryManager.execuateUpdate(QueryStrings.TEST_SHARE_STATUS_UPDATE,
					new Object[] { Status.SHARED.getValue(), testId });
			if (id > 0) {
				isUpdated = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service updateTestSharedStatus method:::::", e);
		}
		return isUpdated;
	}

	/**
	 * A method for saving the status of a test that is only save or publish or
	 * shared.
	 * 
	 * @param test
	 * @return boolean
	 */
	public boolean saveTestStatus(Test test) {
		logger.log(Level.DEBUG, "Inside Test Service saveTestStatus method:::::::::::");
		boolean status = false;
		try {
			/**
			 * if user wants only save the test.
			 */
			if (test.getTestStatus() == 1) {
				/**
				 * saving the test status.
				 */
				QueryManager.execuateQuery(QueryStrings.TEST_SAVE_STATUS_UPDATE, new Object[] { test.getTestId() });
			}
			/**
			 * if user wants publish the test.
			 */
			if (test.getTestStatus() == 2) {
				/**
				 * getting schedule information about test.
				 */
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TEST_SCHEDULE_INFO,
						new Object[] { test.getTestId() });
				/**
				 * set the test info after getting.
				 */
				for (QueryData.Row row : data.getRows()) {
					test.setIsSchedule(Integer.parseInt(row.getRowItem(0)));
					test.setSchedulePublishDate(row.getRowItem(1));
					test.setTestName(row.getRowItem(2));
				}
				/**
				 * if test is not immediate publish means user has scheduled to
				 * this test for publish on a particular date.
				 */
				if (test.getIsSchedule() == 1) {
					/**
					 * change publish status from 0 to 2 where 2 means i.e.
					 * scheduled publish and 1 means i.e. immediate publish.
					 */
					test.setTestPublishStatus(2);
					/**
					 * update publish status of test.
					 */
					int k = QueryManager.execuateUpdate(QueryStrings.TEST_PUBLISH_STATUS_UPDATE,
							new Object[] { test.getTestPublishStatus(), test.getTestId() });
					/**
					 * if test has been published/scheduled update successfully.
					 */
					if (k > 0) {
						/**
						 * update status for identify that test's publish status
						 * has been update.
						 */
						status = true;
						/**
						 * get all user's details.
						 */
						final List<User> userlist = new UserService().getUserList();
						/**
						 * set a text message for sending to these users as
						 * notifications.
						 */
						final String textMsg = (ConstantUtil.UPCOMING_TEST.replace("#NAME", test.getTestName()))
								.replace("#DATE", test.getSchedulePublishDate());
						/**
						 * assign test id as final.
						 */
						final int testId = test.getTestId();
						/**
						 * if user list is not null then.
						 */
						if (userlist != null) {
							/**
							 * start thread for sending notification to all
							 * user.
							 */
							(new Thread() {
								@Override
								public void run() {
									/**
									 * make object of HashMap type data
									 * structure for storing the user id as key
									 * and registration id as value.
									 */
									Map<Integer, String> notification = new LinkedHashMap<Integer, String>();
									/**
									 * Initialize string type array for getting
									 * all user ids.
									 */
									String[] targetIds = new String[userlist.size()];
									/**
									 * traversing on user list for getting user
									 * id and registration id.
									 */
									for (int i = 0; i < userlist.size(); i++) {
										/**
										 * if registration id is not null.
										 */
										if (userlist.get(i).getRegistrationId() != null) {
											/**
											 * put data in notification type
											 * hash map.
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
									 * make the action which would be done when
									 * user click on that notification.
									 */
									String url = "studentGivenTest?testId=" + testId;
									/**
									 * save notification entry.
									 */
									NotificationService.saveNotifcation(textMsg, url, targetIds);
									/**
									 * when notification type hash map has any
									 * data.
									 */
									if (notification.size() > 0) {
										NotificationManagement notificationManagement = new NotificationManagement();
										/**
										 * calling method for sending
										 * notification.
										 */
										notificationManagement.sendNotificationUpcomingTest(notification, textMsg,
												testId);
									}
								}
							}).start();
						}
					}
				} else {
					/**
					 * change publish status from 0 to 2 where 0 means i.e.
					 * drafted and 1 means i.e. immediate publish.
					 */
					test.setTestPublishStatus(1);
					/**
					 * update publish status of test.
					 */
					int k = QueryManager.execuateUpdate(QueryStrings.TEST_PUBLISH_STATUS_UPDATE,
							new Object[] { test.getTestPublishStatus(), test.getTestId() });
					/**
					 * if test's publish has been update successfully.
					 */
					if (k > 0) {
						/**
						 * update status for identify that test's publish status
						 * has been update.
						 */
						status = true;
						/**
						 * get all user's details.
						 */
						final List<User> userlist = new UserService().getUserList();
						/**
						 * set a text message for sending to these users as
						 * notifications.
						 */
						final String textMsg = (ConstantUtil.PUBLISH_TEST.replace("#NAME", test.getTestName()));
						/**
						 * assign test id as final.
						 */
						final int testId = test.getTestId();
						/**
						 * if user list is not null then.
						 */
						if (userlist != null) {
							/**
							 * start thread for sending notification to all
							 * user.
							 */
							(new Thread() {
								@Override
								public void run() {
									/**
									 * make object of HashMap type data
									 * structure for storing the user id as key
									 * and registration id as value.
									 */
									Map<Integer, String> notification = new LinkedHashMap<Integer, String>();
									/**
									 * Initialize string type array for getting
									 * all user ids.
									 */
									String[] targetIds = new String[userlist.size()];
									/**
									 * traversing on user list for getting user
									 * id and registration id.
									 */
									for (int i = 0; i < userlist.size(); i++) {
										/**
										 * if registration id is not null.
										 */
										if (userlist.get(i).getRegistrationId() != null) {
											/**
											 * put data in notification type
											 * hash map.
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
									 * make the action which would be done when
									 * user click on that notification.
									 */
									String url = "studentGivenTest?testId=" + testId;
									/**
									 * save notification entry.
									 */
									NotificationService.saveNotifcation(textMsg, url, targetIds);
									/**
									 * when notification type hash map has any
									 * data.
									 */
									if (notification.size() > 0) {
										NotificationManagement notificationManagement = new NotificationManagement();
										/**
										 * calling method for sending
										 * notification.
										 */
										notificationManagement.sendNotificationPublishedTest(notification, textMsg,
												testId);
									}
								}
							}).start();
						}
					}

				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service saveTestStatus method:::::", e);
		}
		return status;
	}

	/**
	 * Method to get test Object by testId
	 * 
	 * @param testId
	 * @param userId
	 * @return Test
	 */
	public Test getTestByUserId(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Test Service getTestByUserId method:::::::::::");
		Test test = new Test();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_TEST_DETAIL,
					new Object[] { testId, userId });
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
				test.setShuffleSection(row.getRowItem(24) == null ? 0 : Integer.parseInt(row.getRowItem(24)));
				test.setShuffleQuestion(row.getRowItem(25) == null ? 0 : Integer.parseInt(row.getRowItem(25)));
				test.setShuffleOption(row.getRowItem(26) == null ? 0 : Integer.parseInt(row.getRowItem(26)));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getTestByUserId method:::::", e);
		}
		return test;
	}

	/**
	 * <p>
	 * A method for get details of test and its questions details by testId.
	 * </p>
	 * 
	 * @param testId
	 *            This is first parameter which defines test id.
	 * @param userId
	 *            This is second parameter which defines user id.
	 * @return Test.
	 * @exception IOException
	 *                On input error.
	 */
	public Test previewTestDetails(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Test Service previewTestDetails method:::::::::::");
		Test test = new Test();
		QuestionService questionService = new QuestionService();
		try {
			QueryData testdata = QueryManager.execuateQuery(QueryStrings.GET_TEST_FOR_PREVIEW,
					new Object[] { testId, userId });
			for (QueryData.Row row : testdata.getRows()) {
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));

				test.setTestDesc(row.getRowItem(2));
				test.setTestInstruct(row.getRowItem(3));
				String tag = "";
				if ((row.getRowItem(4)).contains(",")) {
					String tagArray[] = (row.getRowItem(4)).replaceAll(" ", "").split(",");
					for (int s = 0; s < tagArray.length; s++) {
						if (s == 0) {
							tag = tag + "#" + tagArray[s];
						} else {
							tag = tag + ", #" + tagArray[s];
						}
					}
				} else {
					tag = "#" + row.getRowItem(4);
				}
				test.setTestTag(tag);
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
				test.setShuffleSection(row.getRowItem(24) == null ? 0 : Integer.parseInt(row.getRowItem(24)));
				test.setShuffleQuestion(row.getRowItem(25) == null ? 0 : Integer.parseInt(row.getRowItem(25)));
				test.setShuffleOption(row.getRowItem(26) == null ? 0 : Integer.parseInt(row.getRowItem(26)));
				test.setIsSchedule(Integer.parseInt(row.getRowItem(27)));
				test.setSchedulePublishDate(
						row.getRowItem(28) != null ? QbisUtils.convertMysqlDateFormatToDate(row.getRowItem(28)) : null);
				test.setIsRandom(row.getRowItem(29) != null ? Integer.parseInt(row.getRowItem(29)) : 0);
				test.setMaxQuestions(row.getRowItem(30) != null ? Integer.parseInt(row.getRowItem(30)) : 0);
				test.setTagList(getTestTagList(testId));
			}
			QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTION_LIST_FOR_PREVIEW,
					new Object[] { testId, userId });
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
					question[ques].setAnswerValue(row.getRowItem(9));
					if (row.getRowItem(10) != null) {
						ObjectMapper mapper = new ObjectMapper();
						QuestionSetting questionSetting = mapper.readValue(row.getRowItem(10),
								new TypeReference<QuestionSetting>() {
								});
						question[ques].setQuestionSetting(questionSetting);
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
				section[sec].setQuestionList(question);
				sec++;
			}
			test.setSectionlist(section);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service previewTestDetails method:::::", e);
		}
		return test;
	}

	/**
	 * A method for deleting the drafted test.
	 * 
	 * @param testId
	 * @param userId
	 * @return boolean
	 */
	public boolean deleteTest(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Test Service deleteTest method:::::::::::");
		boolean flag = true;
		try {
			deleteIconFromDirectory(testId, userId);
			QueryManager.execuateQuery(QueryStrings.REMOVE_QUES_MAPPING_FROM_TEST, new Object[] { testId, userId });
			QueryManager.execuateQuery(QueryStrings.DELETE_SECTIONS_BY_TEST_ID, new Object[] { testId, userId });
			QueryManager.execuateQuery(QueryStrings.DELETE_TEST_BY_TEST_ID, new Object[] { testId, userId });

		} catch (Exception e) {
			flag = false;
			logger.log(Level.ERROR, "Exception Inside Test Service deleteTest method:::::", e);
		}
		return flag;
	}

	/**
	 * method for getting question list of imported questions from question Bank
	 * 
	 * @param userId
	 * @param test
	 * @return List<Question>
	 */
	public List<Question> importQuestionList(Integer userId, Test test) {

		logger.log(Level.DEBUG, "Inside Test Service importQuestionList method:::::::::::");
		List<Question> questionList = new ArrayList<Question>();
		QuestionService questionService = new QuestionService();
		int questionOrder = test.getTotalQuestion() + 1;
		int quesMark = 0;
		float negMark = 0;
		if (test.getEqualMarkTest() == 1) {
			quesMark = test.getEveryQuestionMark();
			negMark = test.getNegMark() == null ? 0 : (float) test.getEveryQuestionMark() * test.getNegMark() / 100;
		}
		try {
			for (Integer questionId : test.getSelectedQuestionIds()) {
				Question question = questionService.getQuestionPreviewDetails(questionId, userId);
				question.setQuestionNo(questionOrder++);
				question.setQuestionMark(quesMark);
				question.setNegMark(negMark);
				question.setIsNew(0);
				question.setIsParent(0);
				questionList.add(question);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service importQuestionList method:::::", e);
		}
		return questionList;
	}

	/**
	 * A method for getting test setting for importing.
	 * 
	 * @param testId
	 * @param userId
	 * @return Test
	 */
	public Test getTestSetting(int testId, int userId) {
		logger.log(Level.DEBUG, "Inside Test Service getTestSetting method:::::::::::");
		Test test = new Test();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TEST_SETTING, new Object[] { testId, userId });
			for (QueryData.Row row : data.getRows()) {
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestDesc(row.getRowItem(2));
				test.setTestInstruct(row.getRowItem(3));
				test.setTestTag(row.getRowItem(4));
				test.setMaxAttempts(row.getRowItem(5) == null ? 0 : Integer.parseInt(row.getRowItem(5)));
				test.setNegMark(Integer.parseInt(row.getRowItem(6)));
				test.setTestTime(row.getRowItem(7) == null ? "0" : row.getRowItem(7));
				test.setTestAdaptive(Integer.parseInt(row.getRowItem(8)));
				test.setTestPause(Integer.parseInt(row.getRowItem(9)));
				test.setView(Integer.parseInt(row.getRowItem(10)));
				test.setEqualMarkTest(Integer.parseInt(row.getRowItem(11)));
				test.setEveryQuestionMark(row.getRowItem(12) == null ? 0 : Integer.parseInt(row.getRowItem(12)));
				test.setIsReview(Integer.parseInt(row.getRowItem(13)));
				test.setReviewWithCorrect(row.getRowItem(14) != null ? Integer.parseInt(row.getRowItem(14)) : null);
				test.setShuffleSection(row.getRowItem(15) != null ? Integer.parseInt(row.getRowItem(15)) : 0);
				test.setShuffleQuestion(row.getRowItem(16) != null ? Integer.parseInt(row.getRowItem(16)) : 0);
				test.setShuffleOption(row.getRowItem(17) != null ? Integer.parseInt(row.getRowItem(17)) : 0);
				test.setTestIcon(row.getRowItem(18));
				test.setTestIconUrl(ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(18));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getTestSetting method:::::", e);
		}
		return test;
	}

	/**
	 * This method is used for uploading the test icon on server.
	 * 
	 * @param file
	 * @param testId
	 * @param userId
	 * @return String
	 */
	public String uploadTestIcon(MultipartFile file, Integer testId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Test Service uploadTestIcon method:::::::::::");
		String newTestIconName = "";
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
				dest.renameTo(new File(uploadPath + "test_" + testId + "_" + fileName + extension));
				newTestIconName = "test_" + testId + "_" + fileName + extension;
				deleteIconFromDirectory(testId, userId);
				setTestIcon(newTestIconName, testId, userId);
			} catch (Exception e) {
				logger.log(Level.ERROR, "Exception Inside Test Service uploadTestIcon method:::::", e);
			}
		}
		return newTestIconName;
	}

	/**
	 * This is used to update the test icon name.
	 * 
	 * @param newTestIconName
	 * @param testId
	 * @param userId
	 */
	public void setTestIcon(String newTestIconName, Integer testId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Test Service setTestIcon method:::::::::::");
		try {
			QueryManager.execuateUpdate(QueryStrings.UPDATE_TEST_ICON,
					new Object[] { newTestIconName, testId, userId });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service setTestIcon method:::::", e);
		}
	}

	/**
	 * This is used for delete the previous test icon after uploading new.
	 * 
	 * @param testId
	 * @param userId
	 */
	public void deleteIconFromDirectory(Integer testId, Integer userId) {
		logger.log(Level.DEBUG, "Inside Test Service deleteIconFromDirectory method:::::::::::");
		String PROFILE_IMAGE_PATH = ConstantUtil.PROFILE_IMAGE_PATH;
		String IMAGE_DIRECTORY = ConstantUtil.ICON_PATH;
		String uploadPath = PROFILE_IMAGE_PATH + IMAGE_DIRECTORY;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TESTDETAILS_FOR_EDIT,
					new Object[] { testId, userId });
			for (QueryData.Row row : data.getRows()) {
				if (row.getRowItem(18) != null || row.getRowItem(18) != "") {
					File dest = new File(uploadPath + row.getRowItem(18));
					dest.delete();
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service deleteIconFromDirectory method:::::", e);
		}
	}

	/**
	 * This is used for getting all sections details.
	 * 
	 * @param userId
	 * @param testId
	 * @return Section List
	 */
	public Section[] getSectionListForImport(Integer userId, Integer testId) {
		logger.log(Level.DEBUG, "Inside Test Service getSectionListForImport method:::::::::::");
		Section section[] = null;
		try {
			QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTIONLIST_BY_USERID,
					new Object[] { userId, testId });
			section = new Section[sectiondata.getRows().size()];
			int z = 0;
			for (QueryData.Row sectionRow : sectiondata.getRows()) {
				section[z] = new Section();
				section[z].setSectionId(Integer.parseInt(sectionRow.getRowItem(0)));
				section[z].setSectionName(sectionRow.getRowItem(1));
				section[z].setTestId(Integer.parseInt(sectionRow.getRowItem(2)));
				section[z].setTestName(sectionRow.getRowItem(3));
				section[z].setSectionSortOrder(1);
				QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONLIST_FOR_EDIT,
						new Object[] { section[z].getSectionId() });
				section[z].setTotalQuestions(data.getRows().size());
				z++;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getSectionListForImport method:::::", e);
		}
		return section;
	}

	/**
	 * A method for getting section list for importing in test.
	 * 
	 * @param test
	 * @return Section[]
	 */
	public Section[] getSectionListForImport(Test test) {
		logger.log(Level.DEBUG, "Inside Test Service getSectionListForImport method:::::::::::");
		int quesMark = 0;
		float negMark = 0;
		if (test.getEqualMarkTest() == 1) {
			quesMark = test.getEveryQuestionMark();
			negMark = test.getNegMark() == null ? 0 : (float) test.getEveryQuestionMark() * test.getNegMark() / 100;
		}
		Section section[] = null;
		QuestionService questionService = new QuestionService();
		try {
			section = new Section[test.getSelectedSectionIds().size()];
			int z = 0;
			for (Integer sectionId : test.getSelectedSectionIds()) {
				int sectionScore = 0;
				QueryData sectiondata = QueryManager.execuateQuery(QueryStrings.GET_SECTION_DETAIL_BY_SECTION_ID,
						new Object[] { sectionId });
				for (QueryData.Row sectionRow : sectiondata.getRows()) {
					section[z] = new Section();
					section[z].setTestId(test.getTestId());
					section[z].setSectionId(Integer.parseInt(sectionRow.getRowItem(0)));
					section[z].setSectionName(sectionRow.getRowItem(1));
					section[z].setSectionSortOrder(1);
					section[z].setIsUpdate(0);
					section[z].setIsDelete(0);
					QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONLIST_FOR_EDIT,
							new Object[] { section[z].getSectionId() });
					Question question[] = new Question[data.getRows().size()];
					int j = 0;
					for (QueryData.Row row : data.getRows()) {
						question[j] = new Question();
						question[j].setQuestionId(Integer.parseInt(row.getRowItem(0)));
						question[j].setQuestionName(row.getRowItem(1));
						question[j].setExplanation(row.getRowItem(2));
						question[j].setQuestionNo(j + 1);
						question[j].setQuestionMark(quesMark);
						question[j].setQuestionType(Integer.parseInt(row.getRowItem(5)));
						question[j].setNegMark(negMark);
						question[j].setIsFormula(Integer.parseInt(row.getRowItem(7)));
						question[j].setMathFormula(row.getRowItem(8));
						question[j].setIsParent(0);
						question[j].setIsNew(0);
						question[j].setAnswerValue(row.getRowItem(10));
						if (row.getRowItem(11) != null) {
							ObjectMapper mapper = new ObjectMapper();
							QuestionSetting questionSetting = mapper.readValue(row.getRowItem(11),
									new TypeReference<QuestionSetting>() {
									});
							question[j].setQuestionSetting(questionSetting);
						}
						sectionScore = sectionScore + quesMark;
						switch (question[j].getQuestionType()) {
						case ConstantUtil.MULTIPLE_CHOICE_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[j]);
							break;
						case ConstantUtil.SINGLE_CHOICE_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[j]);
							break;
						case ConstantUtil.SORT_LIST_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[j]);
							break;
						case ConstantUtil.CHOICE_MATRIX_TYPE:
							questionService.getOptionForChoiceMatrixQuestion(question[j]);
							break;
						case ConstantUtil.CLASSIFICATION_TYPE:
							questionService.getOptionForMultiChoiceQuestion(question[j]);
							break;
						case ConstantUtil.MATCH_LIST:
							questionService.getOptionForMultiChoiceQuestion(question[j]);
							break;
						}
						j++;
					}
					section[z].setSectionScore(sectionScore);
					section[z].setQuestionList(question);
				}
				z++;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service getSectionListForImport method:::::", e);
		}
		return section;
	}

	/**
	 * This make will be used for published the scheduled test.
	 */
	public static void makePublishedtoScheduleTest() {
		try {
			QueryManager.execuateUpdate(QueryStrings.CHANGE_PUBLISH_STATUS_OF_SCHEDULE_TEST, new Object[] {});
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service makePublishedtoScheduleTest method:::::", e);
		}
	}

	/**
	 * This method id used for checking that your test's published time has been
	 * expired or not.
	 * 
	 * @param testId
	 * @return Boolean
	 */
	public Boolean checkTestScheduleTimeStatus(Integer testId) {
		logger.log(Level.DEBUG, "Inside Test Service checkTestScheduleTimeStatus method:::::::::::");
		Boolean status = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_TEST_SCHEDULE_TIME_EXPIRE,
					new Object[] { testId });
			if (data.getRows().size() > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service checkTestScheduleTimeStatus method:::::", e);
		}
		return status;
	}

	/**
	 * This is used for share the test link to all users.
	 * 
	 * @param testId
	 * @param userList
	 * @param user
	 * @param path
	 * @param emailList
	 * @param message
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean addUserTestMapping(final int testId, Map<String, Integer> userList, User user, String path,
			String[] emailList, final String message) {
		logger.log(Level.DEBUG, "Inside Test Service addUserTestMapping method:::::::::::");
		boolean status = false;
		try {
			Test test = new Test();
			test.setTestId(testId);
			test.setTestStatus(2); // for published.
			/**
			 * Save test status.
			 */
			boolean testStatus = saveTestStatus(test);
			/**
			 * perform mapping and sending mail operation after successfully
			 * test published.
			 */
			if (testStatus) {
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
						 * call function for sharing the test.
						 */
						status = addTestUserMapping(userId, testId);
						if (status) {
							sendTestShareLinkToExistUser(testId, email, message, path);
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
						Integer newUserId = new UserService().createTraineeAccountWithTestSharing(email, user, path,
								testId, message);
						/**
						 * if account has been created successfully.
						 */
						if (newUserId > 0) {
							/**
							 * call function for mapping the test with user.
							 */
							status = addTestUserMapping(newUserId, testId);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service addUserTestMapping method:::::", e);
		}
		return status;
	}

	/**
	 * This is performing operation for saving test mapping and share the test
	 * 
	 * @param userId
	 * @param testId
	 * @return Boolean
	 */
	public Boolean addTestUserMapping(int userId, int testId) {
		logger.log(Level.DEBUG, "Inside Test Service addTestUserMapping method:::::::::::");
		Boolean status = false;
		try {
			/**
			 * if user and test mapping
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_USER_TEST_MAPPING,
					new Object[] { userId, testId });
			/**
			 * sending mail to user on successfully mapping of user with test.
			 */
			if (id > 0) {

				/**
				 * user has been mapped with this test.
				 */
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service addTestUserMapping method:::::", e);
		}
		return status;
	}

	/**
	 * This is used sending shared assessment link to user.
	 * 
	 * @param testId
	 * @param email
	 * @param message
	 * @param path
	 */
	public void sendTestShareLinkToExistUser(final int testId, final String email, final String message, String path) {

		logger.log(Level.DEBUG, "Inside Test Service sendTestShareLinkToExistUser method:::::::::::");

		final Map<Object, Object> dataobject = new HashMap<Object, Object>();
		String sharedLink = path + "/previewSharedTest?testId=" + testId;
		if (message != null && message.length() > 0) {

			dataobject.put("messsage", message);
		} else {

			dataobject.put("messsage", "You have been invited for Assessment.");
		}
		dataobject.put("buttonText", "Assessment");
		dataobject.put("typename", "assessment");
		dataobject.put("link", sharedLink);
		(new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.sendEmail(email, "Invitation for accessing assessment",
							EmailSender.generateSharedMsgForExistUser(dataobject));
				} catch (Exception e) {
					logger.log(Level.ERROR, "Exception Inside Test Service sendTestShareLinkToExistUser method:::::",
							e);
				}
			}
		}).start();
	}

	/**
	 * This is used for deleting the section data from test.
	 * 
	 * @param testId
	 * @param sectionId
	 * @return Boolean
	 */
	public Boolean deleteTestSection(Integer testId, Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Test Service deleteTestSection method:::::::::::");
		Boolean isDeleted = false;
		try {
			/**
			 * remove question mapping.
			 */
			QueryManager.execuateUpdate(QueryStrings.REMOVE_QUESTION_MAPPING, new Object[] { sectionId });
			/**
			 * Delete section data from test.
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.DELETE_TEST_SECTION,
					new Object[] { testId, sectionId });
			if (id > 0) {
				isDeleted = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Test Service deleteTestSection method:::::", e);
		}
		return isDeleted;
	}

}
