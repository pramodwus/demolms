package com.qbis.model;

public class ConfigMapping {

	private Integer id;
	private Integer configId;
	private String value;
	private Integer order;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getConfigId() {
		return configId;
	}

	public final void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}

	public final Integer getOrder() {
		return order;
	}

	public final void setOrder(Integer order) {
		this.order = order;
	}
}
