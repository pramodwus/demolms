package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemList {

	private Items[] items;
    private ShippingAddress shipping_address;
	public Items[] getItems() {
		return items;
	}

	public void setItems(Items[] items) {
		this.items = items;
	}

	public ShippingAddress getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(ShippingAddress shipping_address) {
		this.shipping_address = shipping_address;
	}
}
