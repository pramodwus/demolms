package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaypalResponseBody {
private String id;
private String create_time;
private String update_time;
private String state;
private String cart;
private String intent;
private Payer payer;
private Transactions[] transactions;
private Links[] links;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String create_time) {
	this.create_time = create_time;
}
public String getUpdate_time() {
	return update_time;
}
public void setUpdate_time(String update_time) {
	this.update_time = update_time;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getIntent() {
	return intent;
}
public void setIntent(String intent) {
	this.intent = intent;
}
public Payer getPayer() {
	return payer;
}
public void setPayer(Payer payer) {
	this.payer = payer;
}
public Transactions[] getTransactions() {
	return transactions;
}
public void setTransactions(Transactions[] transactions) {
	this.transactions = transactions;
}
public Links[] getLinks() {
	return links;
}
public void setLinks(Links[] links) {
	this.links = links;
}
public String getCart() {
	return cart;
}
public void setCart(String cart) {
	this.cart = cart;
}

}
