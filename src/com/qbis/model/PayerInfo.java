package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayerInfo {
private String email;
private String first_name;
private String last_name;
private String payer_id;
private ShippingAddress shipping_address;
private BillingAddress billing_address;
private String country_code;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getPayer_id() {
	return payer_id;
}
public void setPayer_id(String payer_id) {
	this.payer_id = payer_id;
}
public ShippingAddress getShipping_address() {
	return shipping_address;
}
public void setShipping_address(ShippingAddress shipping_address) {
	this.shipping_address = shipping_address;
}
public String getCountry_code() {
	return country_code;
}
public void setCountry_code(String country_code) {
	this.country_code = country_code;
}
public BillingAddress getBilling_address() {
	return billing_address;
}
public void setBilling_address(BillingAddress billing_address) {
	this.billing_address = billing_address;
}

}
