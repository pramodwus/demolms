package com.qbis.model;

/**
 * This define the payment info field.
 * 
 * @author Ankur Kumar
 *
 */
public class PaymentInfo {

	/*
	 * This define total payment currency.
	 */
	private String totalPaymentCurrency;

	/*
	 * This define payment action.
	 */
	private String paymentAction;

	/*
	 * This define currency type.
	 */
	private String paymentCurrencyType;

	/*
	 * This define return url.
	 */
	private String returnUrl;

	/*
	 * This define cancel url.
	 */
	private String cancelUrl;

	/*
	 * This define url of logo image.
	 */
	private String logoImage;

	public String getTotalPaymentCurrency() {
		return totalPaymentCurrency;
	}

	public void setTotalPaymentCurrency(String totalPaymentCurrency) {
		this.totalPaymentCurrency = totalPaymentCurrency;
	}

	public String getPaymentAction() {
		return paymentAction;
	}

	public void setPaymentAction(String paymentAction) {
		this.paymentAction = paymentAction;
	}

	public String getPaymentCurrencyType() {
		return paymentCurrencyType;
	}

	public void setPaymentCurrencyType(String paymentCurrencyType) {
		this.paymentCurrencyType = paymentCurrencyType;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

}
