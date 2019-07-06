package com.qbis.api;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbis.model.ContentProcessingResponse;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.services.UploadContentService;
import com.qbis.validators.ContentStatusValidator;

/**
 * This is used for performing all operations related to product api.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
@RequestMapping(value = "/api/webhook/", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebhookWebService {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(WebhookWebService.class);
	/**
	 * Instance of {@link ContentStatusValidator}
	 */
	@Autowired
	private ContentStatusValidator contentValidator;
	/**
	 * Instance of {@link UploadContentService}
	 */
	@Autowired
	private UploadContentService contentService;

	/**
	 * This method is used for updating the content status after finishing the
	 * processing of video.
	 * 
	 * @param content
	 *            {@link ContentProcessingResponse}
	 * @param result
	 *            {@link BindingResult}
	 * @return {@link ServiceResult}
	 */
	@RequestMapping(value = "content", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object updateContentStatus(@RequestBody ContentProcessingResponse content, BindingResult result,HttpServletResponse response) {
		logger.debug("Inside WebhookWebService in updateContentStatus method::::::::::");
		boolean isUpdated = contentService.updateContentprestatus(response,content);
		if (isUpdated) {
			return new ServiceResult(StatusCode.SUCCESS, isUpdated);
		}
		return new ServiceResult(StatusCode.ERROR, isUpdated);
	}
	
	
}
