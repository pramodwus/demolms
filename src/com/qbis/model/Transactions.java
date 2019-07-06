package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transactions {

	private Amount amount;
	private Payee payee;
	private String description;
	private RelatedResources[] related_resources;
	private ItemList item_list;
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ItemList getItem_list() {
		return item_list;
	}
	public void setItem_list(ItemList item_list) {
		this.item_list = item_list;
	}
	public Payee getPayee() {
		return payee;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public RelatedResources[] getRelated_resources() {
		return related_resources;
	}
	public void setRelated_resources(RelatedResources[] related_resources) {
		this.related_resources = related_resources;
	}
}
