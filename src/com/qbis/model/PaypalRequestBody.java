package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaypalRequestBody {
	private String intent;
	private Payer payer;
	private Transactions[] transactions;
	private RedirectUrls redirect_urls;
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
	public RedirectUrls getRedirect_urls() {
		return redirect_urls;
	}
	public void setRedirect_urls(RedirectUrls redirect_urls) {
		this.redirect_urls = redirect_urls;
	}
	public Transactions[] getTransactions() {
		return transactions;
	}
	public void setTransactions(Transactions[] transactions) {
		this.transactions = transactions;
	}
}
