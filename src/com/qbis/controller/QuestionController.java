package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.ConstantUtil;
import com.qbis.common.LicenseEnum;
import com.qbis.model.Datatable;
import com.qbis.model.License;
import com.qbis.model.Question;
import com.qbis.model.Section;
import com.qbis.model.Tag;
import com.qbis.model.User;
import com.qbis.services.ConfigService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.QuestionService;

/**
 * controller for handling request related to question.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
public class QuestionController {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(QuestionController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ConfigService configService;

	/**
	 * This is used for getting question List
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public ModelAndView getQuestionList() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside QuestionController questionList method ::::::");
		ModelAndView model = new ModelAndView();
		/*
		 * User user = (User) request.getSession().getAttribute("userSession");
		 */
		/*
		 * model.addObject("questionList",
		 * questionService.getQuestionList(user.getUserId()));
		 */
		License license = (License) request.getSession().getAttribute("licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		License licensenew = new QbisLicenseService().validateLicense(license, orgId,
				LicenseEnum.QUESTION_TYPE.getValue());
		model.addObject("quesTypeList", licensenew.getListQuesType());
		model.addObject("excelSheet", ConstantUtil.SERVER_IP + ConstantUtil.EXCEL_SHEET_PATH);
		model.setViewName("question");
		return model;
	}

	/**
	 * This is used getting question list in library.
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getQuestionListData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getQuesitionListResponse(@ModelAttribute("userDetails") Datatable req,
			@RequestParam("action") String action) {
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			result = questionService.getQuestionList(user.getUserId(), req, action);
		} else {
			result.put("error", "your session has been expired.");
		}
		return result;
	}

	/**
	 * Redirect to add edit question page.
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addeditquestion", method = RequestMethod.GET)
	public ModelAndView addEditQuestion(@RequestParam(value = "questionId", required = false) Integer questionId,
			@RequestParam(value = "type", required = false) Integer questionType)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside QuestionController addeditquestion method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		List<Question> questionList = new ArrayList<Question>();
		if (questionId != null) {
			List<Tag> listOFTags = questionService.getQuestionTagList(questionId);
			model.addObject("listOfTags", listOFTags);
			Map<String, Object> map = new HashMap<String, Object>();
			questionList = questionService.getQuestionDetail(questionId, user.getUserId());
			if (questionList.size() > 0) {
				map.put("questionList", questionList);
				ObjectMapper mapper = new ObjectMapper();
				String questionListJson = mapper.writeValueAsString(map);

				model.addObject("questionId", questionId);
				model.addObject("questionJson", questionListJson);
			} else {
				model.addObject("questionJson", questionList);
			}
		} else {
			model.addObject("questionJson", questionList);
		}

		model.addObject("testTags",
				configService.getConfigTags(new Object[] { "board", "session", "class", "subject", "chapter", "act" }));

		model.addObject("type", questionType);
		model.setViewName("addeditquestion");
		return model;
	}

	/**
	 * This is used for saving question details.
	 * 
	 * @param questionJson
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean submitQuestionDetails(@ModelAttribute("sectionData") String questionJson, BindingResult result,
			@RequestParam(value = "questionId", required = false) Integer questionId) throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException, JsonParseException, JsonMappingException {
		logger.log(Level.DEBUG, "Inside QuestionController saveQuestion method ::::::");
		User user = (User) request.getSession().getAttribute("userSession");
		ObjectMapper mapper = new ObjectMapper();
		Boolean status = false;
		Section section[] = mapper.readValue(questionJson, new TypeReference<Section[]>() {
		});
		if (questionId != null && questionId > 0) {
			status = questionService.updateQuestionData(questionId, section[0].getQuestionList(), user.getUserId());
		} else {
			status = questionService.saveQuestions(section[0].getQuestionList(), user.getUserId());

		}
		return status;
	}

	/**
	 * Used for deleting the question Data.
	 * 
	 * @param questionId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteQuestionData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteQuestion(@RequestParam(value = "questionId") Integer questionId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside QuestionController deleteQuestionData method ::::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Boolean status = false;
		status = questionService.deleteQuestionData(questionId, user.getUserId());
		return status;
	}

	/**
	 * This is used for getting question data for preview.
	 * 
	 * @param questionId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/questionPreview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Question getQuestionPreview(@RequestParam(value = "questionId") Integer questionId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside QuestionController questionPreview method ::::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Question question = questionService.getQuestionPreviewDetails(questionId, user.getUserId());
		return question;
	}

	/**
	 * not used This is used for question list in question bank.
	 * 
	 * @param search
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getQuestionBank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Question> getQuestionBank(@RequestParam(value = "action", required = false) String action)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside QuestionController getQuestionBank method ::::::");
		List<Question> questionList = new ArrayList<Question>();
		User user = (User) request.getSession().getAttribute("userSession");
		questionList = questionService.getQuestionBankList(user.getUserId());
		return questionList;
	}

	/**
	 * This is used for uploading the excel sheet for question bank.
	 * 
	 * @param req
	 * @param fileName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadQuestionFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean uploadQuestionFile(@RequestParam(value = "questionFile") MultipartFile req,
			@RequestParam(value = "fileName") String fileName)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, Exception {
		User user = (User) request.getSession().getAttribute("userSession");
		Boolean status = questionService.fetchQuesionFromUploadFile(req, fileName, user.getUserId());
		return status;
	}

	/**
	 * This is used getting question type list.
	 * 
	 * @return
	 */
	@RequestMapping(value = "questiontypelist")
	public ModelAndView questionTypeList() {
		ModelAndView model = new ModelAndView();
		License license = (License) request.getSession().getAttribute("licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		License licensenew = new QbisLicenseService().validateLicense(license, orgId,
				LicenseEnum.QUESTION_TYPE.getValue());
		model.addObject("quesTypeList", licensenew.getListQuesType());
		model.setViewName("questiontypelist");
		return model;
	}

}
