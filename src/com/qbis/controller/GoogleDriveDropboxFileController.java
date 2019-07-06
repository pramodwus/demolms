package com.qbis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.model.DropboxFile;
import com.qbis.model.GoogleDriveFile;
import com.qbis.model.User;
import com.qbis.services.GoogleDriveDropboxFileService;

/**
 * This controller is used for performing operation related to google drive and
 * dropbox file.
 * 
 * @author ankur
 * 
 */
@Controller
@RequestMapping("/googledriveanddropboxservice")
public class GoogleDriveDropboxFileController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(GoogleDriveDropboxFileController.class);

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link GoogleDriveDropboxFileService}
	 */
	@Autowired
	private GoogleDriveDropboxFileService googleDriveDropboxFileService;

	/*
	 * @RequestMapping(value="/getgoogleauth") public ModelAndView
	 * getAccessToken(){ ModelAndView model = new ModelAndView();
	 * UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
	 * "https://accounts.google.com/o/oauth2/v2/auth") .queryParam("scope",
	 * "email") .queryParam("state", "profile") .queryParam("redirect_uri",
	 * "http://ankur.qbis.com/Qbis_Spring_Deb/googleservice/googleTokenData")
	 * .queryParam("response_type", "token") .queryParam("client_id",
	 * "587678799583-s4p3582d476sdldcfi55a2fftomce7bg.apps.googleusercontent.com"
	 * );
	 * model.setViewName("redirect:"+builder.build().encode().toUri().toString
	 * ()); return model; }
	 */

	/**
	 * This is used for downloading the file on server from google drive and
	 * saved into content list table.
	 * 
	 * @param token
	 * @param googleFileData
	 * @return contentId
	 */
	@RequestMapping(value = "/downloadGoogleDriveFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer downloadFileFromGoogleDrive(
			@RequestParam(value = "token") String token,
			@RequestBody GoogleDriveFile googleDriveFile) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService downloadGoogleDriveFile method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		/*
		 * Integer contentId = googleDriveDropboxFileService
		 * .downloadFileFromGoogleDrive(token.trim(), googleDriveFile,
		 * user.getUserId());
		 */
		String orgName = user.getSubdomain();
		Integer contentId = googleDriveDropboxFileService
				.uploadFileInS3BucketFromGoogleDrive(token.trim(),
						googleDriveFile, user.getUserId(), orgName);
		return contentId;
	}

	/**
	 * This is used for downloading the file on server from google drive and
	 * saved into content list table.
	 * 
	 * @param token
	 * @param googleFileData
	 * @return contentId
	 */
	@RequestMapping(value = "/downloadDropboxFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer downloadFileFromDropbox(@RequestBody DropboxFile dropboxFile) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService downloadFileFromDropbox method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		/*
		 * Integer contentId = googleDriveDropboxFileService
		 * .downloadFileFromDropbox(dropboxFile, user.getUserId());
		 */
		String orgName = user.getSubdomain();
		Integer contentId = googleDriveDropboxFileService
				.uploadFileInS3BucketFromDropbox(dropboxFile, user.getUserId(),
						orgName);
		return contentId;
	}

	/**
	 * This is used redirecting on page for getting google token using oauth.
	 * 
	 * @param absPath
	 * @return model
	 */
	@RequestMapping(value = "/googleoauth", method = RequestMethod.GET)
	public ModelAndView getGoogleOauthToken(
			@RequestParam(value = "absPath", required = false) String absPath) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService getGoogleOauthToken method ::::: ");
		ModelAndView model = new ModelAndView();
		model.addObject("absPath", absPath);
		model.setViewName("googleoauth");
		return model;
	}
}
