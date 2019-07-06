package com.qbis.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.common.ConstantUtil;
import com.qbis.gcm.NotificationManagement;
import com.qbis.model.Notification;
import com.qbis.model.User;
import com.qbis.services.NotificationService;
import com.qbis.services.UserService;

/**
 * This class is handle notification request created by admin.
 * 
 * @author Neeraj.
 *
 */
@Controller
public class NotificationController {
	private static final Logger logger = Logger
			.getLogger(NotificationController.class);

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notifyService;

	/**
	 * Open createnotification Page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createNotificationPage")
	public ModelAndView openNotificationPage() {
		logger.log(Level.DEBUG, "Create Notification page ");
		ModelAndView model = new ModelAndView();
		User user = (User)request.getSession().getAttribute("userSession");
		model.addObject("users", userService.getUserListByCreaterId(user.getUserId()));
		model.setViewName("createadminnotification");
		return model;
	}

	/**
	 * Send Notification created by admin
	 * 
	 * @param notification
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendNotificationCreatedByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public void sendNotificationCreatedByAdmin(
			@RequestPart("categ") Notification notification,
			@RequestParam(value = "categimg", required = false) MultipartFile file)
			throws IllegalStateException, IOException {
		NotificationManagement notificationManagement = new NotificationManagement();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		if (notification.getUsers() != null) {
			String[] targetIds = new String[notification.getUsers().size()];
			for (int i = 0; i < notification.getUsers().size(); i++) {
				targetIds[i] = notification.getUsers().get(i).toString();
				User appuser = userService.getUserProfile(notification
						.getUsers().get(i));
				if (appuser != null && appuser.getRegistrationId() != null) {
					map.put(appuser.getUserId(), appuser.getRegistrationId());
				}
			}
			String imageName = "";
			if (file != null) {
				imageName = file.getOriginalFilename();
				String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
						+ ConstantUtil.NOTIFICATION_FILE;
				if (!file.isEmpty()) {
					try {
						File dir = new File(uploadPath + File.separator);
						if (!dir.exists())
							dir.mkdirs();
						File serverFile = new File(dir.getAbsolutePath()
								+ File.separator + file.getOriginalFilename());
						file.transferTo(serverFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

			if (map.size() != 0) {
				notificationManagement.pushedNotificationCreatedByAdmin(map,
						notification, imageName);
			}

			String url = "url";
			NotificationService.saveNotifcation(
					notification.getNotificationMessage(), url, targetIds,
					imageName);
		}
	}

	/**
	 * Service to get notifications.
	 * 
	 * @param lastNMonths
	 * @return
	 */
	@RequestMapping(value = "/getnotifications", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getNotificationList() {
		logger.log(Level.DEBUG,
				"Notification Controller getNotificationList method ::::: ");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("userSession");
		List<Notification> list = notifyService.getNotificationList(user
				.getUserId());
		map.put("list", list);
		return map;
	}

}
