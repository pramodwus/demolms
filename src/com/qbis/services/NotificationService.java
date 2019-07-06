package com.qbis.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Notification;

/**
 * This class is used for push the notifications.
 * 
 * @author neeraj
 *
 */
@Component
public class NotificationService {

	/**
	 * Method to save notifications of user
	 * 
	 * @param notification
	 * @param url
	 * @param targetIds
	 */
	public static void saveNotifcation(String notification, String url,
			String[] targetIds) {
		try {

			Integer id = null;

			id = QueryManager.execuateUpdate(QueryStrings.INSERT_NOTIFICATION,
					new Object[] { notification, url, null });

			if (id > 0) {
				String newurl = url.contains("?") == true ? (url + "&nid=" + id)
						: (url + "?nid=" + id);
				QueryManager.execuateUpdate(QueryStrings.UPDATE_NOTIFICATION,
						new Object[] { newurl, id });
			}
			String[] tIdslength = (String[]) targetIds;
			for (int i = 0; i < tIdslength.length; i++) {
				String tIdslength1 = tIdslength[i];
				QueryManager.execuateUpdate(
						QueryStrings.INSERT_NOTIFICATION_TARGET, new Object[] {
								id, tIdslength1 });
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * Method to get list of notifications of particular user
	 * 
	 * @param userId
	 * @return list of notification
	 */
	public List<Notification> getNotificationList(int userid) {
		List<Notification> list = new ArrayList<Notification>();
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.NOTIFICATION_LIST_BY_USERID,
				new Object[] { userid });
		for (QueryData.Row row : data.getRows()) {
			Notification notify = new Notification();
			notify.setId(Integer.parseInt(row.getRowItem(3)));
			notify.setUrl(row.getRowItem(2));
			notify.setNotifyText(row.getRowItem(1));
			if (row.getRowItem(4) != null) {
				notify.setImage(ConstantUtil.SERVER_IP
						+ ConstantUtil.NOTIFICATION_FILE + row.getRowItem(4));
			} else {
				notify.setImage((ConstantUtil.SERVER_IP
						+ ConstantUtil.IMAGE_DIRECTORY + "qbislogo.png"));
			}

			list.add(notify);
		}
		return list;
	}

	/**
	 * Method to update read status of notification
	 * 
	 * @param nid
	 * @param status
	 * @return int
	 */
	public static int updateNotificationReadStatus(int nid, int status) {

		int k = QueryManager.execuateUpdate(
				QueryStrings.UPDATE_NOTIFICATION_STATUS, new Object[] { status,
						nid });
		return k;
	}

	/**
	 * Method to save notifications of user with notification image
	 * 
	 * @param notification
	 * @param url
	 * @param targetIds
	 * @param image
	 */
	public static void saveNotifcation(String notification, String url,
			String[] targetIds, String image) {
		try {

			Integer id = null;

			id = QueryManager.execuateUpdate(QueryStrings.INSERT_NOTIFICATION,
					new Object[] { notification, url, image });

			if (id > 0) {
				String newurl = url.contains("?") == true ? (url + "&nid=" + id)
						: (url + "?nid=" + id);
				QueryManager.execuateUpdate(QueryStrings.UPDATE_NOTIFICATION,
						new Object[] { newurl, id });
			}
			String[] tIdslength = (String[]) targetIds;
			for (int i = 0; i < tIdslength.length; i++) {
				String tIdslength1 = tIdslength[i];
				QueryManager.execuateUpdate(
						QueryStrings.INSERT_NOTIFICATION_TARGET, new Object[] {
								id, tIdslength1 });
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
