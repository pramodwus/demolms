package com.qbis.model;

import java.util.List;

public class Config {

	private Integer id;

	private String key;

	private String name;

	private String type;

	private List<ConfigMapping> configList;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final List<ConfigMapping> getConfigList() {
		return configList;
	}

	public final void setConfigList(List<ConfigMapping> configList) {
		this.configList = configList;
	}

}
