package com.qbis.model;

import java.util.ArrayList;

/**
 * Data Object for holding details of notification created by admin in asaan web
 * application.
 */
public class Notification {
	/**
	 * Variable to store list of userId.
	 */
	private ArrayList<Integer> users;
	/**
	 * Variable for notification message.
	 */
	private String notificationMessage;
	private Integer id;
	private String notifyText;
	private String url;
    private String created_date;
    private String createdDate;    
    private short type;
    private String image;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotifyText() {
		return notifyText;
	}

	public void setNotifyText(String notifyText) {
		this.notifyText = notifyText;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public ArrayList<Integer> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Integer> users) {
		this.users = users;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
