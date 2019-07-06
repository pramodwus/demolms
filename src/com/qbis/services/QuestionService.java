package com.qbis.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.dynamodbv2.xspec.NULL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.Exceptions.AuthException;
import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Datatable;
import com.qbis.model.Option;
import com.qbis.model.Question;
import com.qbis.model.QuestionSetting;
import com.qbis.model.ServiceResult.TagMappingType;
import com.qbis.model.ServiceResult.TagTypeKey;
import com.qbis.model.Tag;

/**
 * This would be used for all operations related to question.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class QuestionService {

	/**
	 * This is used for saving the question details.
	 * 
	 * @param questionList
	 * @param userId
	 * @return boolean
	 */
	public boolean saveQuestions(Question questionList[], Integer userId) {
		boolean status = false;
		Integer questionId = 0;
		try {
			for (Question question : questionList) {

				switch (question.getQuestionType()) {
				/**
				 * make cases for saving the question details based on question
				 * type.
				 */
				case ConstantUtil.MULTIPLE_CHOICE_TYPE:
					questionId = saveQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.SINGLE_CHOICE_TYPE:
					questionId = saveQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.SORT_LIST_TYPE:
					questionId = saveQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.CHOICE_MATRIX_TYPE:
					questionId = saveChoiceMatrixTypeQuestion(userId, question);
					break;
				case ConstantUtil.CLASSIFICATION_TYPE:
					questionId = saveQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.MATCH_LIST:
					questionId = saveQuesDetailForCommonTypeQues(userId, question);
					break;
				}
				if (questionId > 0) {
					status = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return status;
	}

	/**
	 * This is used for saving all options for a particular question.
	 * 
	 * @param questionId
	 * @param optionList
	 * @return no
	 */
	public void saveQuestionOptions(Integer questionId, Option optionList[]) {
		try {
			for (Option option : optionList) {
				QueryManager.execuateQuery(QueryStrings.QUESTION_INSERT_OPTION, new Object[] { questionId,
						option.getOptionName().replace("\n", ""), option.getAnswerStatus(), option.getOptionOrder() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is used to getting all question list based on user id.
	 * 
	 * @param userId
	 * @return List
	 */
	public Map<String, Object> getQuestionList(Integer userId, Datatable req, String action) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Object> questionList = new ArrayList<Object>();
			result.put("draw", req.getDraw());
			String searchValue = req.getSearch().get(Datatable.SearchCriterias.value);
			Integer totalRecord = 0;
			String query = action.equals("questionlibrary")
					? QueryManager.QueryStrings.GET_QUESTIONS_BY_USER_ID_FOR_SERVERSIDE_PROCESSING.getQuery()
					: QueryManager.QueryStrings.GET_QUESTIONS_BY_USER_ID_FOR_IMPORT_FOR_SERVERSIDE_PROCESSING
							.getQuery();

			String queryData = query + " GROUP BY qbis_quest.question_id";
			QueryData totalRecorddata = QueryManager.execuateQuery(queryData, new Object[] { userId });
			totalRecord = totalRecorddata.getRows().size();
			if (searchValue != null && searchValue.length() > 0) {
				query = query + " AND (qbis_quest.question_txt like ? OR qbis_quest.question_txt like ? "
						+ " OR question_type.type_name like ?"
						+ " OR DATE_FORMAT(qbis_quest.created_date,'%d-%m-%Y') like ?";
				/* + " OR qbis_test.test_title like ?" + ")"; */
				query = query + (action.equals("questionlibrary") ? " OR qbis_test.test_title like ? )" : ")");
			}
			query = query + " GROUP BY qbis_quest.question_id";
			if (req.getOrder() != null) {
				Integer columnIndex = Integer.parseInt(req.getOrder().get(0).get(Datatable.OrderCriterias.column));
				String orderType = req.getOrder().get(0).get(Datatable.OrderCriterias.dir);
				switch (columnIndex) {
				case 0:
					query = query + " ORDER BY qbis_quest.created_date " + orderType;
					break;
				case 1:
					query = query + " ORDER BY qbis_quest.question_txt " + orderType;
					break;
				case 2:
					query = query + " ORDER BY qbis_test.test_title " + orderType;
					break;
				case 3:
					query = query + " ORDER BY question_type.type_name " + orderType;
					break;
				default:
					query = query + " ORDER BY qbis_quest.created_date DESC";
				}
			} else {
				query = query + " ORDER BY qbis_quest.created_date DESC";
			}
			QueryData data;
			if (searchValue != null && searchValue.length() > 0) {
				data = QueryManager.execuateQuery(query,
						new Object[] { userId, "%" + searchValue.trim() + "%", "%" + searchValue.trim() + "%",
								"%" + searchValue.trim() + "%", "%" + searchValue.trim() + "%",
								"%" + searchValue.trim() + "%" });
			} else {
				data = QueryManager.execuateQuery(query, new Object[] { userId });
			}
			result.put("recordsTotal", totalRecord);
			result.put("recordsFiltered", data.getRows().size());
			int i = 0;
			for (QueryData.Row row : data.getRows()) {
				if (i < (req.getStart() + req.getLength()) && i >= req.getStart()) {
					Question question = new Question();
					question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
					question.setQuestionName(row.getRowItem(1));
					question.setQuestionTypeName(row.getRowItem(2));
					question.setCreatedDate(row.getRowItem(3));
					question.setQuestionIsMap(Integer.parseInt(row.getRowItem(4)) > 0 ? 1 : 0);
					question.setMappedTest(row.getRowItem(5));
					question.setQuestionType(Integer.parseInt(row.getRowItem(6)));
					questionList.add(question);
				}
				i++;
			}
			result.put("data", questionList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * public List<Question> getQuestionList(Integer userId) { List<Question>
	 * questionList = new ArrayList<Question>(); try { QueryData data =
	 * QueryManager.execuateQuery( QueryStrings.GET_QUESTIONS_BY_USER_ID, new
	 * Object[] { userId }); for (QueryData.Row row : data.getRows()) { Question
	 * question = new Question();
	 * question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
	 * question.setQuestionName(row.getRowItem(1));
	 * question.setQuestionTypeName(row.getRowItem(2));
	 * question.setCreatedDate(row.getRowItem(3));
	 * question.setQuestionIsMap(Integer.parseInt(row.getRowItem(4)) > 0 ? 1 :
	 * 0); question.setMappedTest(row.getRowItem(5));
	 * question.setQuestionType(Integer.parseInt(row.getRowItem(6)));
	 * questionList.add(question); } } catch (Exception e) {
	 * e.printStackTrace(); } return questionList; }
	 */

	/**
	 * This is used to getting all question list based on user id.
	 * 
	 * @param userId
	 * @return List
	 */
	public List<Question> getQuestionBankList(Integer userId) {
		List<Question> questionList = new ArrayList<Question>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTIONS_BY_USER_ID_FOR_IMPORT,
					new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				Question question = new Question();
				question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
				question.setQuestionName(row.getRowItem(1));
				question.setQuestionTypeName(row.getRowItem(2));
				question.setCreatedDate(row.getRowItem(3));
				question.setQuestionIsMap(Integer.parseInt(row.getRowItem(4)) > 0 ? 1 : 0);
				questionList.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	/**
	 * This is used for getting question details based on question_id
	 * 
	 * @param questionId
	 * @param userId
	 * @return List
	 */
	public List<Question> getQuestionDetail(Integer questionId, Integer userId) {
		List<Question> questionList = new ArrayList<Question>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_BY_QUESTION_ID,
					new Object[] { questionId, userId });
			for (QueryData.Row row : data.getRows()) {
				Question question = new Question();
				question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
				question.setQuestionName(row.getRowItem(1));
				question.setExplanation(row.getRowItem(2));
				question.setQuestionType(Integer.parseInt(row.getRowItem(3)));
				question.setIsFormula(Integer.parseInt(row.getRowItem(4)));
				question.setMathFormula(row.getRowItem(5));
				question.setAnswerValue(row.getRowItem(6));
				/**
				 * checking that question's setting is not null.
				 */
				if (row.getRowItem(7) != null) {
					QuestionSetting questionSetting = mapper.readValue(row.getRowItem(7),
							new TypeReference<QuestionSetting>() {
							});
					question.setQuestionSetting(questionSetting);
				}
				switch (question.getQuestionType()) {
				case ConstantUtil.MULTIPLE_CHOICE_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.SINGLE_CHOICE_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.SORT_LIST_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.CHOICE_MATRIX_TYPE:
					getOptionForChoiceMatrixQuestion(question);
					break;
				case ConstantUtil.CLASSIFICATION_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.MATCH_LIST:
					getOptionForMultiChoiceQuestion(question);
					break;
				}
				questionList.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	/**
	 * This is used for updating the question data.
	 * 
	 * @param questionId
	 * @param questionList
	 * @param userId
	 * @return boolean
	 */
	public boolean updateQuestionData(Integer questionId, Question questionList[], Integer userId) {
		boolean status = false;
		Integer id = 0;
		try {
			for (Question question : questionList) {
				/**
				 * set question id in question object.
				 */
				question.setQuestionId(questionId);
				/**
				 * make cases for update the question details based on question
				 * type.
				 */
				switch (question.getQuestionType()) {
				case ConstantUtil.MULTIPLE_CHOICE_TYPE:

					id = updateQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.SINGLE_CHOICE_TYPE:
					id = updateQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.SORT_LIST_TYPE:
					id = updateQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.CHOICE_MATRIX_TYPE:
					id = updateChoiceMatrixTypeQuestion(userId, question);
					break;
				case ConstantUtil.CLASSIFICATION_TYPE:
					id = updateQuesDetailForCommonTypeQues(userId, question);
					break;
				case ConstantUtil.MATCH_LIST:
					id = updateQuesDetailForCommonTypeQues(userId, question);
					break;
				}
				if (id > 0) {
					status = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return status;
	}

	/**
	 * This is used for deleting data of a particular question.
	 * 
	 * @param questionId
	 * @return Boolean
	 */
	public Boolean deleteQuestionData(Integer questionId, Integer userId) {
		Boolean isDelete = false;
		try {
			QueryManager.execuateUpdate(QueryStrings.DELETE_SUB_OPTIONS_BY_QUES_ID, new Object[] { questionId });
			Integer id = QueryManager.execuateUpdate(QueryStrings.DELETE_QUESTIONDATA_BY_QUES_ID,
					new Object[] { questionId, userId });
			if (id > 0) {
				isDelete = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDelete;
	}

	/**
	 * This is used for getting a question details based on question id and user
	 * id.
	 * 
	 * @param questionId
	 * @param userId
	 * @return Question
	 */
	public Question getQuestionPreviewDetails(Integer questionId, Integer userId) {
		Question question = new Question();

		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_FOR_PREVIEW,
					new Object[] { questionId, userId });
			for (QueryData.Row row : data.getRows()) {
				question.setQuestionId(Integer.parseInt(row.getRowItem(0)));
				question.setQuestionName(row.getRowItem(1));
				question.setExplanation(row.getRowItem(2));
				question.setQuestionType(Integer.parseInt(row.getRowItem(3)));
				question.setIsFormula(Integer.parseInt(row.getRowItem(4)));
				question.setMathFormula(row.getRowItem(5));
				question.setIsParent(0);
				question.setAnswerValue(row.getRowItem(7));
				if (row.getRowItem(8) != null) {
					ObjectMapper mapper = new ObjectMapper();
					QuestionSetting questionSetting = mapper.readValue(row.getRowItem(8),
							new TypeReference<QuestionSetting>() {
							});
					question.setQuestionSetting(questionSetting);
				}
				switch (question.getQuestionType()) {
				case ConstantUtil.MULTIPLE_CHOICE_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.SINGLE_CHOICE_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.SORT_LIST_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.CHOICE_MATRIX_TYPE:
					getOptionForChoiceMatrixQuestion(question);
					break;
				case ConstantUtil.CLASSIFICATION_TYPE:
					getOptionForMultiChoiceQuestion(question);
					break;
				case ConstantUtil.MATCH_LIST:
					getOptionForMultiChoiceQuestion(question);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	/**
	 * This is used for extracting question data from excel sheet.
	 * 
	 * @param fileData
	 * @param fileName
	 * @param userId
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean fetchQuesionFromUploadFile(MultipartFile fileData, String fileName, Integer userId)
			throws Exception {
		System.err.println("=====fetch question from upload file======");
		Boolean status = true;
		ByteArrayInputStream bis;
		bis = new ByteArrayInputStream(fileData.getBytes());
		Workbook workbook = getWorkbook(bis, fileName);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		List<Question> questionList = new ArrayList<Question>();
		while (iterator.hasNext()) {

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if (nextRow.getRowNum() < 1) {
				continue;
			}

			Question question = new Question();
			Option option[] = new Option[6];
			List<Integer> correctAnswer = new ArrayList<Integer>();
			for (int i = 0; i < option.length; i++) {
				option[i] = new Option();
			}
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getColumnIndex()) {
				case 0:
					question.setQuestionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 1:
					option[0].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 2:
					option[0].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(1);
					}
					break;
				case 3:
					option[1].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 4:
					option[1].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(2);
					}
					break;
				case 5:
					option[2].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 6:
					option[2].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(3);
					}
					break;
				case 7:
					option[3].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 8:
					option[3].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(4);
					}
					break;
				case 9:
					option[4].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 10:
					option[4].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(5);
					}
					break;
				case 11:
					option[5].setOptionName(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 12:
					option[5].setAnswerStatus(getCellValue(cell) != null
							? (getCellValue(cell).toString().equalsIgnoreCase("Yes") ? 1 : 0) : 0);
					if (getCellValue(cell) != null && getCellValue(cell).toString().equalsIgnoreCase("Yes")) {
						correctAnswer.add(6);
					}
					break;
				case 13:
					question.setExplanation(getCellValue(cell) != null ? getCellValue(cell).toString() : null);
					break;
				case 14:
					System.out.println("Board " + getCellValue(cell));
					question.setBoard(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					// board
					break;
				case 15:
					System.out.println("Session " + getCellValue(cell));
					question.setSession(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 16:
					question.setClassTag(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 17:
					question.setSubject(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 18:
					question.setChapter(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;
				case 19:
					question.setAct(getCellValue(cell) != null ? getCellValue(cell).toString() : "");
					break;

				}
			}
			question.setOption(option);
			question.setCorrectAnswer(correctAnswer);
			question.setQuestionType(
					correctAnswer.size() >= 2 ? ConstantUtil.MULTIPLE_CHOICE_TYPE : ConstantUtil.SINGLE_CHOICE_TYPE);
			question.setIsFormula(0);
			if (question.getQuestionName().length() > 0) {
				questionList.add(question);
			}

		}
		List<Integer> optionLength = questionBankSheetValidation(questionList);
		initializeQuestionTag(questionList);
		status = saveQuestionBank(questionList, userId, optionLength);
		return status;
	}

	/**
	 * This method is used for initialize the question tag.
	 * 
	 * @param questionList
	 *            {@link List}
	 */
	private void initializeQuestionTag(List<Question> questionList) {
		for (int index = 0; index < questionList.size(); index++) {
			Question question = questionList.get(index);
			List<Tag> tagList = new ArrayList<Tag>();
			if (question.getBoard() != null) {
				tagList.add(getTagBasedOnValue(question.getBoard()));
			}
			if (question.getSession() != null) {
				tagList.add(getTagBasedOnValue(question.getSession()));
			}
			if (question.getClassTag() != null) {
				tagList.add(getTagBasedOnValue(question.getClassTag()));
			}
			if (question.getSubject() != null) {
				tagList.add(getTagBasedOnValue(question.getSubject()));
			}
			if (question.getChapter() != null) {
				String id = getConfigIdByKey(TagTypeKey.CHAPTER.getValue());
				Tag tag = new Tag();
				tag.setId(id);
				tag.setValue(question.getChapter());
				tagList.add(tag);
			}
			if (question.getAct() != null) {
				String id = getConfigIdByKey(TagTypeKey.ACT.getValue());
				Tag tag = new Tag();
				tag.setId(id);
				tag.setValue(question.getAct());
				tagList.add(tag);
			}
			question.setTagList(tagList);
		}
	}

	/**
	 * This method is used for getting tag based on value.
	 * 
	 * @param value
	 *            {@link String}
	 * @return tag {@link Tag}
	 */
	private Tag getTagBasedOnValue(String value) {
		Tag tag = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_CONFIG_BASED_VALUE, new Object[] { value });
		for (QueryData.Row row : data.getRows()) {
			tag = new Tag();
			tag.setValue(row.getRowItem(0));
			tag.setId(row.getRowItem(1));
		}
		return tag;
	}

	/**
	 * This method is used for getting config id based on key.
	 * 
	 * @param key
	 *            {@link String}
	 * @return id {@link String}
	 */
	private String getConfigIdByKey(String key) {
		String id = null;
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_CONFIG_ID_BASED_KEY, new Object[] { key });
		for (QueryData.Row row : data.getRows()) {
			id = row.getRowItem(0);
		}
		return id;
	}

	/**
	 * This is used getting workbook based on uploaded excel sheet.
	 * 
	 * @param inputStream
	 * @param excelFilePath
	 * @return Workbook
	 * @throws IOException
	 */
	private Workbook getWorkbook(ByteArrayInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	/**
	 * This is used for getting cell from sheet based on its type.
	 * 
	 * @param cell
	 * @return Object
	 */
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		}

		return null;
	}

	/**
	 * This is used for validated the upload excel sheet as per questions's
	 * requirement.
	 * 
	 * @param question
	 * @return List<Integer> This is used for describes how many option's
	 *         information is needed for store for each question.
	 * @throws Exception
	 */
	public List<Integer> questionBankSheetValidation(List<Question> question) throws AuthException {
		List<Integer> optionLength = new ArrayList<Integer>();
		for (int q = 0; q < question.size(); q++) {
			if (question.get(q).getQuestionName() == null || question.get(q).getQuestionName().length() == 0) {
				throw new AuthException(417, "Question number " + (q + 1) + " can't be empty");
			} else if (question.get(q).getQuestionName().trim().length() < 5) {
				throw new AuthException(417, "Minimum 5 characters required for Question number " + (q + 1));
			}
			int len = question.get(q).getOption().length;
			for (int i = question.get(q).getOption().length - 1; i >= 0; i--) {
				if ((question.get(q).getOption()[i].getOptionName() == null
						|| question.get(q).getOption()[i].getOptionName() == "")
						&& (question.get(q).getOption()[i].getAnswerStatus() == null
								|| question.get(q).getOption()[i].getAnswerStatus() == 0)) {
					len--;
				} else {
					break;
				}
			}

			for (int op = 0; op < len; op++) {
				if (question.get(q).getOption()[op].getOptionName() == null
						|| question.get(q).getOption()[op].getOptionName() == "") {
					throw new AuthException(417,
							"Answer Choice " + (op + 1) + " can't be empty for Question number  " + (q + 1));
				}
			}

			if (len < 2) {
				throw new AuthException(417, "Minimum two Answer Choice are required for Question number " + (q + 1));
			}

			if (question.get(q).getCorrectAnswer().size() < 1) {
				throw new AuthException(417, "Minimum One isCorrect is required for Question number " + (q + 1));
			}
			optionLength.add(len);

			if (question.get(q).getBoard() == null || question.get(q).getBoard().length() == 0) {
				throw new AuthException(417, "Board for Question Number " + q + 1 + " can't be empty");
			}

			if (question.get(q).getSession() == null || question.get(q).getSession().length() == 0) {
				throw new AuthException(417, "Session for Question Number " + q + 1 + " can't be empty");
			}

			if (question.get(q).getClassTag() == null || question.get(q).getClassTag().length() == 0) {
				throw new AuthException(417, "Class for Question Number " + q + 1 + " can't be empty");
			}

			if (question.get(q).getSubject() == null || question.get(q).getSubject().length() == 0) {
				throw new AuthException(417, "Subject for Question Number " + q + 1 + " can't be empty");
			}

			if (question.get(q).getChapter() == null || question.get(q).getChapter().length() == 0) {
				throw new AuthException(417, "Chapter for Question Number " + q + 1 + " can't be empty");
			}

			if (question.get(q).getAct() == null || question.get(q).getAct().length() == 0) {
				throw new AuthException(417, "Act for Question Number " + q + 1 + " can't be empty");
			}

		}
		return optionLength;
	}

	/**
	 * This is used for saving the question details of question's bank.
	 * 
	 * @param questionList
	 * @param userId
	 * @return boolean
	 */
	public boolean saveQuestionBank(List<Question> questionList, Integer userId, List<Integer> optionlength) {
		boolean status = false;
		try {
			ObjectMapper mapper = new ObjectMapper();
			for (int i = 0; i < questionList.size(); i++) {
				String quesSetting = mapper.writeValueAsString(questionList.get(i).getQuestionSetting());
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_QUESTION_IN_QUESTION_BANK,
						new Object[] { questionList.get(i).getQuestionName().replace("\n", ""),
								questionList.get(i).getQuestionType(), questionList.get(i).getExplanation(), userId,
								questionList.get(i).getIsFormula(), questionList.get(i).getMathFormula(), 0,
								questionList.get(i).getAnswerValue(), quesSetting });
				if (id > 0) {
					addUpdateQuestionTags(id, questionList.get(i).getTagList());
					saveQuestionBankOptions(id, questionList.get(i).getOption(), optionlength.get(i));
					status = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return status;
	}

	/**
	 * This is used for saving all options for a particular question from
	 * question bank.
	 * 
	 * @param questionId
	 * @param optionList
	 * @return no
	 */
	public void saveQuestionBankOptions(Integer questionId, Option optionList[], Integer optionLength) {
		try {
			for (int op = 0; op < optionLength; op++) {
				Integer ansStatus = optionList[op].getAnswerStatus() == null ? 0 : optionList[op].getAnswerStatus();
				QueryManager.execuateQuery(QueryStrings.QUESTION_INSERT_OPTION, new Object[] { questionId,
						optionList[op].getOptionName().replace("\n", ""), ansStatus, (op + 1) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addUpdateQuestionTags(Integer quesitonId, List<Tag> tagList) {
		removeQuestionTags(quesitonId);
		for (int i = 0; i < tagList.size(); i++) {
			Tag tag = tagList.get(i);
			QueryManager.execuateUpdate(QueryStrings.SAVE_TAGS_MAPPING,
					new Object[] { tag.getId(), tag.getValue(), quesitonId, TagMappingType.Question.getValue() });
		}
	}

	private void removeQuestionTags(Integer quesitonId) {
		QueryManager.execuateQuery(QueryStrings.REMOVE_TAGS_MAPPING,
				new Object[] { quesitonId, TagMappingType.Question.getValue() });
	}

	public Integer saveQuesDetailForCommonTypeQues(Integer userId, Question question) {
		Integer questionId = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String quesSetting = mapper.writeValueAsString(question.getQuestionSetting());
			questionId = QueryManager.execuateUpdate(QueryStrings.SAVE_QUESTION_IN_QUESTION_BANK,
					new Object[] { question.getQuestionName().replace("\n", ""), question.getQuestionType(),
							question.getExplanation(), userId, question.getIsFormula(), question.getMathFormula(),
							question.getIsParent() == null ? 0 : question.getQuestionId(), question.getAnswerValue(),
							quesSetting

					});
			if (questionId > 0) {
				addUpdateQuestionTags(questionId, question.getTagList());
				Option[] option = question.getOption();
				for (int j = 0; j < option.length; j++) {
					/**
					 * insert options details for a question.
					 */
					QueryManager.execuateUpdate(QueryStrings.QUESTION_INSERT_OPTION,
							new Object[] { questionId, option[j].getOptionName().replace("\n", ""),
									option[j].getAnswerStatus(), option[j].getOptionOrder() });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionId;
	}

	/**
	 * This method is used for getting question tag list.
	 * 
	 * @param testId
	 * @return
	 */
	public List<Tag> getQuestionTagList(Integer questionId) {
		List<Tag> tagList = new ArrayList<Tag>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TAGS_MAPPING,
				new Object[] { questionId, TagMappingType.Question.getValue() });
		for (QueryData.Row row : data.getRows()) {
			Tag tag = new Tag();
			tag.setId(row.getRowItem(0));
			tag.setValue(row.getRowItem(1));
			tagList.add(tag);
		}
		return tagList;
	}

	/**
	 * This is used for update question which has common details like multiple
	 * choice and sort list.
	 * 
	 * @param userId
	 * @param question
	 * @return Integer
	 */
	public Integer updateQuesDetailForCommonTypeQues(Integer userId, Question question) {
		Integer questionId = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String quesSetting = mapper.writeValueAsString(question.getQuestionSetting());

			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_DETAIL,
					new Object[] { question.getQuestionName().replace("\n", ""), question.getQuestionType(),
							question.getExplanation(), question.getIsFormula(), question.getMathFormula(),
							question.getAnswerValue(), quesSetting, question.getQuestionId(), userId });
			if (question.getQuestionId() > 0) {
				questionId = question.getQuestionId();
				addUpdateQuestionTags(questionId, question.getTagList());
			}
			if (id > 0) {

				/**
				 * delete options of this questions.
				 */
				QueryManager.execuateUpdate(QueryStrings.DELETE_OPTIONS_BY_QUES_ID,
						new Object[] { question.getQuestionId() });
				Option[] option = question.getOption();
				for (int j = 0; j < option.length; j++) {
					/**
					 * insert options details for a question.
					 */
					QueryManager
							.execuateUpdate(QueryStrings.QUESTION_INSERT_OPTION,
									new Object[] { question.getQuestionId(),
											option[j].getOptionName().replace("\n", ""), option[j].getAnswerStatus(),
											option[j].getOptionOrder() });
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionId;
	}

	/**
	 * This is used for saving choice matrix type question.
	 * 
	 * @param userId
	 * @param question
	 * @return Integer
	 */
	public Integer saveChoiceMatrixTypeQuestion(Integer userId, Question question) {
		Integer questionId = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String quesSetting = mapper.writeValueAsString(question.getQuestionSetting());
			questionId = QueryManager.execuateUpdate(QueryStrings.SAVE_QUESTION_IN_QUESTION_BANK,
					new Object[] { question.getQuestionName().replace("\n", ""), question.getQuestionType(),
							question.getExplanation(), userId, question.getIsFormula(), question.getMathFormula(),
							question.getIsParent() == null ? 0 : question.getQuestionId(), question.getAnswerValue(),
							quesSetting

					});
			if (questionId > 0) {
				Option[] option = question.getOption();
				Option[] subOption = question.getSubOption();
				for (int j = 0; j < option.length; j++) {
					/**
					 * insert options details for a question.
					 */
					QueryManager.execuateUpdate(QueryStrings.QUESTION_INSERT_OPTION,
							new Object[] { questionId, option[j].getOptionName().replace("\n", ""),
									option[j].getAnswerStatus(), option[j].getOptionOrder() });
				}
				for (int suboption = 0; suboption < subOption.length; suboption++) {
					/**
					 * insert sub options details for a question.
					 */
					QueryManager.execuateUpdate(QueryStrings.QUESTION_INSERT_SUB_OPTION,
							new Object[] { questionId, subOption[suboption].getOptionName().replace("\n", ""),
									subOption[suboption].getOptionOrder() });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionId;
	}

	/**
	 * This is used for update the details of choice matrix type question.
	 * 
	 * @param userId
	 * @param question
	 * @return Integer
	 */
	public Integer updateChoiceMatrixTypeQuestion(Integer userId, Question question) {
		Integer questionId = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String quesSetting = mapper.writeValueAsString(question.getQuestionSetting());
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_QUESTION_DETAIL,
					new Object[] { question.getQuestionName().replace("\n", ""), question.getQuestionType(),
							question.getExplanation(), question.getIsFormula(), question.getMathFormula(),
							question.getAnswerValue(), quesSetting, question.getQuestionId(), userId });
			if (id > 0) {
				questionId = question.getQuestionId();
				/**
				 * delete options of this questions.
				 */
				QueryManager.execuateUpdate(QueryStrings.DELETE_OPTIONS_BY_QUES_ID,
						new Object[] { question.getQuestionId() });
				/**
				 * delete sub options of this questions.
				 */
				QueryManager.execuateUpdate(QueryStrings.DELETE_SUB_OPTIONS_BY_QUES_ID,
						new Object[] { question.getQuestionId() });

				Option[] option = question.getOption();
				Option[] subOption = question.getSubOption();
				for (int j = 0; j < option.length; j++) {
					/**
					 * insert options details for a question.
					 */
					QueryManager
							.execuateUpdate(QueryStrings.QUESTION_INSERT_OPTION,
									new Object[] { question.getQuestionId(),
											option[j].getOptionName().replace("\n", ""), option[j].getAnswerStatus(),
											option[j].getOptionOrder() });
				}
				for (int suboption = 0; suboption < subOption.length; suboption++) {
					/**
					 * insert sub options details for a question.
					 */
					QueryManager.execuateUpdate(QueryStrings.QUESTION_INSERT_SUB_OPTION,
							new Object[] { question.getQuestionId(),
									subOption[suboption].getOptionName().replace("\n", ""),
									subOption[suboption].getOptionOrder() });
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionId;
	}

	/**
	 * This is question getting detail of question if question is multiple type.
	 * 
	 * @param question
	 */
	public void getOptionForMultiChoiceQuestion(Question question) {
		QueryData optionData = QueryManager.execuateQuery(QueryStrings.GET_OPTION_LIST_BY_QUES_ID,
				new Object[] { question.getQuestionId() });
		Option option[] = new Option[optionData.getRows().size()];
		int i = 0;
		for (QueryData.Row optionRow : optionData.getRows()) {
			option[i] = new Option();
			option[i].setOptionId(Integer.parseInt(optionRow.getRowItem(0)));
			option[i].setOptionName(optionRow.getRowItem(1));
			option[i].setAnswerStatus(Integer.parseInt(optionRow.getRowItem(2)));
			option[i].setOptionPosition((char)(Integer.parseInt(optionRow.getRowItem(3))+64));
			i++;
		}
		question.setOption(option);
	}

	/**
	 * This is question getting detail of question if question is choice matrix
	 * type.
	 * 
	 * @param question
	 */
	public void getOptionForChoiceMatrixQuestion(Question question) {
		try {
			QueryData optionData = QueryManager.execuateQuery(QueryStrings.GET_OPTION_LIST_BY_QUES_ID,
					new Object[] { question.getQuestionId() });
			Option option[] = new Option[optionData.getRows().size()];
			int i = 0;
			for (QueryData.Row optionRow : optionData.getRows()) {
				option[i] = new Option();
				option[i].setOptionId(Integer.parseInt(optionRow.getRowItem(0)));
				option[i].setOptionName(optionRow.getRowItem(1));
				option[i].setAnswerStatus(Integer.parseInt(optionRow.getRowItem(2)));
				option[i].setOptionOrder(Integer.parseInt(optionRow.getRowItem(3)));
				i++;
			}
			question.setOption(option);
			QueryData subOptionData = QueryManager.execuateQuery(QueryStrings.GET_SUB_OPTION_LIST_BY_QUES_ID,
					new Object[] { question.getQuestionId() });
			Option subOption[] = new Option[subOptionData.getRows().size()];
			int subOp = 0;
			for (QueryData.Row optionRow : subOptionData.getRows()) {
				subOption[subOp] = new Option();
				subOption[subOp].setOptionId(Integer.parseInt(optionRow.getRowItem(0)));
				subOption[subOp].setOptionName(optionRow.getRowItem(1));
				subOption[subOp].setOptionOrder(Integer.parseInt(optionRow.getRowItem(2)));
				subOp++;
			}
			question.setSubOption(subOption);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveVideoQuestion(Question question, int userId) {
		Integer questionId = 0;
		try {
			questionId = QueryManager.execuateUpdate(QueryStrings.SAVE_VIDEO_QUESTION,
					new Object[] { question.getContentTypeId(), question.getQuestionType(),
							question.getQuestionName().replace("\n", ""), userId, question.getTime() });
			if (questionId > 0) {
				Option[] option = question.getOption();
				for (int j = 0; j < option.length; j++) {
					/**
					 * insert options details for a question.
					 */
					QueryManager.execuateUpdate(QueryStrings.SAVE_VIDEO_QUESTION_OPTION, new Object[] { questionId,
							option[j].getOptionName().replace("\n", ""), option[j].getAnswerStatus() });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Question> findVideoQuestionByContentId(int contentId) {
		List<Question> questionList = new ArrayList<Question>();
		QueryData questionData = QueryManager.execuateQuery(QueryStrings.GET_QUESTION_BY_CONTENT_ID,
				new Object[] { contentId });
		for (QueryData.Row quesRow : questionData.getRows()) {
			Question question = new Question();
			question.setQuestionId(Integer.parseInt(quesRow.getRowItem(0)));
			question.setQuestionType(Integer.parseInt(quesRow.getRowItem(1)));
			question.setQuestionName(quesRow.getRowItem(2));
			question.setTime(quesRow.getRowItem(3));
			question.setQuestionTypeName(quesRow.getRowItem(4));
			question.setSeconds(quesRow.getRowItem(5));
			QueryData optionData = QueryManager.execuateQuery(QueryStrings.GET_OPTION_LIST_BY_QUSETION_ID,
					new Object[] { question.getQuestionId() });
			Option option[] = new Option[optionData.getRows().size()];
			int i = 0;
			for (QueryData.Row optionRow : optionData.getRows()) {
				option[i] = new Option();
				option[i].setOptionId(Integer.parseInt(optionRow.getRowItem(0)));
				option[i].setOptionName(optionRow.getRowItem(1));
				option[i].setAnswerStatus(Integer.parseInt(optionRow.getRowItem(2)));
				i++;
			}
			question.setOption(option);
			questionList.add(question);
		}
		return questionList;
	}
}
