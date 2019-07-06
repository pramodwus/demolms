package com.qbis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Device {
	/*
	 * A String which describes device's name.
	 */
	private String device_name;
	/*
	 * A String which describes Operating System information of device.
	 */
	private String device_os;
	/*
	 * A String which describes using app's version.
	 */
	private String app_version;
	/*
	 * A string which describes a token id
	 */
	private String register_pn_token;
	/*
	 * A String which describes the version of Operating system used by device.
	 */
	private String os_version;
	/*
	 * A String which describes the network type of a device.
	 */
	private String network;

	/*
	 * A String which describes device's ID.
	 */
	private String device_id;

	/*
	 * A String which describes userId.
	 */
	private String user_id;

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_os() {
		return device_os;
	}

	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getRegister_pn_token() {
		return register_pn_token;
	}

	public void setRegister_pn_token(String register_pn_token) {
		this.register_pn_token = register_pn_token;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
