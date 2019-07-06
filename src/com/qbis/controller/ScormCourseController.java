package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.model.Course;
import com.qbis.model.ScormCMI;
import com.qbis.model.User;
import com.qbis.services.CourseService;
import com.qbis.services.ScormCourseService;

@Controller
public class ScormCourseController {
	private static final Logger logger = Logger
			.getLogger(ScormCourseController.class);

	@Autowired
	private ScormCourseService scormCourseService;

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CourseService courseService;

	/**
	 * redirect on page for uploading the scorm zip file.
	 * 
	 * @return model
	 */
	@RequestMapping(value = "/uploadscormcourse", method = RequestMethod.GET)
	public ModelAndView uploadScormCourse() {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in uploadscormcourse method.........");
		ModelAndView model = new ModelAndView();
		model.setViewName("uploadscormcourse");
		return model;
	}

	/**
	 * redirect on edit page for scorm course.
	 * 
	 * @param courseId
	 * @return model
	 */
	@RequestMapping(value = "/addeditscormcourse", method = RequestMethod.GET)
	public ModelAndView addEditScormCourse(
			@RequestParam(value = "courseId") Integer courseId) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in addEditScormCourse method.........");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		if (courseId != null && courseId > 0) {
			Course course = courseId != null ? (courseService.getCourseDetails(
					courseId, user.getUserId())) : new Course();
			model.addObject("course", course);
		}
		model.setViewName("addeditscormcourse");
		return model;
	}

	/**
	 * This is used for updating the scorm course information.
	 * 
	 * @param file
	 * @param req
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updatescormcourse", method = RequestMethod.POST)
	public ModelAndView updateScormCourse(
			@RequestParam("imageName") MultipartFile file,
			@ModelAttribute("courseform") Course req, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException, ParseException {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in updateScormCourse method.........");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		if (req.getCourseId() != null) {
			req.setUserId(user.getUserId());
			courseService.updateCourseDetails(req);
			if (file.getOriginalFilename().length() != 0) {
				courseService.uploadCourseIcon(file, req.getCourseId(),
						user.getUserId());
			}
		}
		redirectAttributes.addAttribute("courseId", req.getCourseId());
		redirectAttributes.addAttribute("isPublish", 0);
		model.setViewName("redirect:scormcoursepreview");
		return model;
	}

	/**
	 * This is used for redirecting the preview page for scorm course.
	 * 
	 * @param courseId
	 * @param isPublish
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/scormcoursepreview", method = RequestMethod.GET)
	public ModelAndView scormCoursePreview(
			@RequestParam(value = "courseId") Integer courseId,
			@RequestParam(value = "isPublish") Integer isPublish)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException, ParseException {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in updateScormCourse method.........");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		Course course = courseService.getCourseDetailsById(courseId,
				user.getUserId(), isPublish);
		model.addObject("course", course);
		model.setViewName("scormcoursepreview");
		return model;
	}

	/**
	 * This is used for publishing and sharing the course.
	 * 
	 * @param action
	 * @param courseId
	 * @param selectedSection
	 * @param emailList
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/publishedscormcourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean publishedScormCourse(
			@RequestParam(value = "action") String action,
			@RequestParam(value = "courseId") Integer courseId,
			@RequestParam(value = "emailList", required = false) String[] emailList,
			@RequestParam(value = "groupList", required = false) String[] groupList,
			@RequestParam(value = "emailMap", required = false) String emailMap,
			@RequestParam(value = "message", required = false) String message)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller publishedScormCourse method ::::: ");

		User user = (User) request.getSession().getAttribute("userSession");
		Boolean status = false;
		if (action.equalsIgnoreCase("share")) {
			String path = request.getRequestURL().toString();
			path = path.substring(0, path.lastIndexOf('/'));
			Map<String, Integer> userList = new HashMap<>();
			Map<String, Integer> userListtemp = new HashMap<>();
			ObjectMapper mapper = new ObjectMapper();
			userListtemp = mapper.readValue(emailMap,
					new TypeReference<Map<String, Integer>>() {
					});
			if (groupList.length > 0)
				userList = courseService.getUsersInGroup(groupList, "course");
			userList.putAll(userListtemp);
			courseService.updateSharedCourseStatus(courseId);
			status = courseService.performUserCourseMapping(courseId, userList,
					message, user, path);
		} else {
			if (courseId > 0) {
				String sectionarr[] = new String[0];
				status = courseService.savepublishedCourse(courseId,
						sectionarr, user.getUserId());
			}
		}
		return status;
	}

	/**
	 * upload scorm course zip file.
	 * 
	 * @param file
	 * @return courseId
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadscormzipfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer uploadScormZipFile(
			@RequestParam("zipFile") MultipartFile file)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller uploadScormZipFile method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		Integer courseId = scormCourseService.performOperationOnScormFile(file,
				user);
		return courseId;
	}

	/**
	 * launch the scorm course for trainee.
	 * 
	 * @param courseId
	 * @return model
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/viewscormcoursecontent", method = RequestMethod.GET)
	public ModelAndView viewScormCourseContent(
			@RequestParam("courseId") Integer courseId)
			throws JsonProcessingException {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in viewScormCourseContent method.........");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("courseId", courseId);
		model.addObject("userId", user.getUserId());
		model.setViewName("student/scormcourselauncher");
		//model.setViewName("working");
		return model;
	}

	/**
	 * show the scorm attempted course result.
	 * 
	 * @param cmi
	 * @param result
	 * @return model
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/scormcourseresult")
	public ModelAndView scormCourseResult(
			@ModelAttribute("scormCMIform") ScormCMI cmi, BindingResult result)
			throws JsonProcessingException {
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in scormCourseResult method.........");
		ModelAndView model = new ModelAndView();
		System.out.println("cmi result "
				+ new ObjectMapper().writeValueAsString(cmi));
		model.addObject("cmi", cmi);
		model.setViewName("student/scormcourseresult");
		return model;
	}
	
	/**
	 * working here
	 */
	@RequestMapping(value = "/working", method = RequestMethod.GET)
	public ModelAndView viewScormCourseContent1(){
		logger.log(Level.DEBUG,
				"Inside ScormCourseController in viewScormCourseContent method.........");
		ModelAndView model = new ModelAndView();
		model.addObject("courseId", 1);
		model.addObject("userId", 6);
		model.setViewName("working");
		return model;
	}
}
