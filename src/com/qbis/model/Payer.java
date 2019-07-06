package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payer {

	private String payment_method;
	private String status;
	private PayerInfo payer_info;
    private Boolean use_vendor_currency_conversion;
	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Boolean getUse_vendor_currency_conversion() {
		return use_vendor_currency_conversion;
	}

	public void setUse_vendor_currency_conversion(
			Boolean use_vendor_currency_conversion) {
		this.use_vendor_currency_conversion = use_vendor_currency_conversion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PayerInfo getPayer_info() {
		return payer_info;
	}

	public void setPayer_info(PayerInfo payer_info) {
		this.payer_info = payer_info;
	}
}
