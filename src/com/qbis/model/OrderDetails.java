package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetails {
	private Long id;
	private User customer;
	private String total;
	private String created_date;
	private String updated_date;
	private Boolean payment_status;
	private List<String> eluminate_order_items;
	private String transaction_id;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final User getCustomer() {
		return customer;
	}

	public final void setCustomer(User customer) {
		this.customer = customer;
	}

	public final String getTotal() {
		return total;
	}

	public final void setTotal(String total) {
		this.total = total;
	}

	public final String getCreated_date() {
		return created_date;
	}

	public final void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public final String getUpdated_date() {
		return updated_date;
	}

	public final void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public final Boolean getPayment_status() {
		return payment_status;
	}

	public final void setPayment_status(Boolean payment_status) {
		this.payment_status = payment_status;
	}

	public final List<String> getEluminate_order_items() {
		return eluminate_order_items;
	}

	public final void setEluminate_order_items(List<String> eluminate_order_items) {
		this.eluminate_order_items = eluminate_order_items;
	}

	public final String getTransaction_id() {
		return transaction_id;
	}

	public final void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
}
