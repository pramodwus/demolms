package com.qbis.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.ConstantUtil;
import com.qbis.common.PaypalApi;
import com.qbis.model.Amount;
import com.qbis.model.Body;
import com.qbis.model.Details;
import com.qbis.model.ItemList;
import com.qbis.model.Items;
import com.qbis.model.License;
import com.qbis.model.Links;
import com.qbis.model.Payer;
import com.qbis.model.PaymentInfo;
import com.qbis.model.PaypalRequestBody;
import com.qbis.model.PaypalResponseBody;
import com.qbis.model.RedirectUrls;
import com.qbis.model.Sale;
import com.qbis.model.Transactions;

/**
 * This class is used perform operation related to paypal api.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class PaypalService {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger.getLogger(PaypalService.class);

	/**
	 * Instance of {@link PaypalApi}
	 */
	@Autowired
	private PaypalApi paypalApi;

	/**
	 * This is used getting token.
	 * 
	 * @param payment
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getPaypalToken(PaymentInfo payment)
			throws IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in getPaypalToken method :::::::");
		String bodyParam = "";
		if (isSet(payment.getTotalPaymentCurrency())) {
			bodyParam += "&PAYMENTREQUEST_0_AMT="
					+ payment.getTotalPaymentCurrency();
		}
		if (isSet(payment.getPaymentCurrencyType())) {
			bodyParam += "&PAYMENTREQUEST_0_PAYMENTACTION="
					+ payment.getPaymentCurrencyType();
		}

		if (isSet(payment.getLogoImage())) {
			bodyParam += "&LOGOIMG=" + payment.getLogoImage();
		}
		if (isSet(payment.getReturnUrl())) {
			bodyParam += "&RETURNURL=" + payment.getReturnUrl();
		}

		if (isSet(payment.getCancelUrl())) {
			bodyParam += "&CANCELURL=" + payment.getCancelUrl();
		}
		/* Bill Me Later Specific Parameters */
		bodyParam += "&LANDINGPAGE=Billing";
		bodyParam += "&USERSELECTEDFUNDINGSOURCE=BML";
		Map<String, String> initResult = paypalApi.httpPaypalCall(
				"SetExpressCheckout", bodyParam);
		return initResult;
	}

	/**
	 * This is used getting checkout details.
	 * 
	 * @param BuyerId
	 * @param token
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getExpressCheckoutDetails(String BuyerId,
			String token) throws IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in getExpressCheckoutDetails method :::::::");
		Map<String, String> checkoutDetails = paypalApi.httpPaypalCall(
				"GetExpressCheckoutDetails", "&TOKEN=" + token);
		Map<String, String> result = new HashMap<String, String>();
		String strAck = checkoutDetails.get("ACK");
		if (strAck != null
				&& (strAck.equalsIgnoreCase("SUCCESS") || strAck
						.equalsIgnoreCase("SUCCESSWITHWARNING"))) {
			result.putAll(checkoutDetails);
			/*
			 * The information that is returned by the GetExpressCheckoutDetails
			 * call should be integrated by the partner into his Order Review
			 * page
			 */
			/*
			 * String email = checkoutDetails.get("EMAIL"); // ' Email address
			 * of payer. String payerId = checkoutDetails.get("PAYERID"); // '
			 * Unique PayPal customer account identification number. String
			 * payerStatus = checkoutDetails.get("PAYERSTATUS"); // ' Status of
			 * payer. Character length and limitations: 10 single-byte
			 * alphabetic characters. String firstName =
			 * checkoutDetails.get("FIRSTNAME"); // ' Payer's first name. String
			 * lastName = checkoutDetails.get("LASTNAME"); // ' Payer's last
			 * name. String cntryCode = checkoutDetails.get("COUNTRYCODE"); // '
			 * Payer's country of residence in the form of ISO standard 3166
			 * two-character country codes. String shipToName =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTONAME"); // ' Person's
			 * name associated with this address. String shipToStreet =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTOSTREET"); // ' First
			 * street address. String shipToCity =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTOCITY"); // ' Name of
			 * city. String shipToState =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTOSTATE"); // ' State
			 * or province String shipToCntryCode =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE"); // '
			 * Country code. String shipToZip =
			 * checkoutDetails.get("PAYMENTREQUEST_0_SHIPTOZIP"); // ' U.S. Zip
			 * code or other country-specific postal code. String addressStatus
			 * = checkoutDetails.get("ADDRESSSTATUS"); // ' Status of street
			 * address on file with PayPal String totalAmt =
			 * checkoutDetails.get("PAYMENTREQUEST_0_AMT"); // ' Total Amount to
			 * be paid by buyer System.out.println("Total Amount "+totalAmt+
			 * "  payerId+++++++++++++++"
			 * +getec.get("L_PAYMENTREQUEST_0_NUMBER0")); String currencyCode =
			 * checkoutDetails.get("CURRENCYCODE"); // 'Currency being used
			 */
		} else {
			// Display a user friendly Error on the page using any of the
			// following error information returned by PayPal
			/*
			 * String ErrorCode =
			 * checkoutDetails.get("L_ERRORCODE0").toString(); String
			 * ErrorShortMsg =checkoutDetails.get("L_SHORTMESSAGE0").toString();
			 * String ErrorLongMsg =
			 * checkoutDetails.get("L_LONGMESSAGE0").toString(); String
			 * ErrorSeverityCode =
			 * checkoutDetails.get("L_SEVERITYCODE0").toString();
			 * 
			 * String errorString = "SetExpressCheckout API call failed. "+
			 * 
			 * "<br>Detailed Error Message: " + ErrorLongMsg +
			 * "<br>Short Error Message: " + ErrorShortMsg + "<br>Error Code: "
			 * + ErrorCode + "<br>Error Severity Code: " + ErrorSeverityCode;
			 */
			// request.setAttribute("error", errorString);
		}

		// System.out.println("payment info " + new
		// ObjectMapper().writeValueAsString(checkoutDetails));
		return checkoutDetails;
	}

	/**
	 * This is used doing complete transaction.
	 * 
	 * @param checkoutDetails
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> doExpressCheckoutDetails(
			Map<String, String> checkoutDetails) throws IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in doExpressCheckoutDetails method :::::::");
		String requestBody = "";
		if (isSet(checkoutDetails.get("TOKEN").toString())) {
			requestBody = "&TOKEN="
					+ encode(checkoutDetails.get("TOKEN").toString());
		}

		if (isSet(checkoutDetails.get("PAYERID").toString())) {
			requestBody += "&PAYERID="
					+ encode(checkoutDetails.get("PAYERID").toString());
		}

		if (isSet("SALE")) {
			requestBody += "&PAYMENTREQUEST_0_PAYMENTACTION=" + encode("SALE");
		}

		if (isSet("localhost")) {
			requestBody += "&IPADDRESS=" + encode("localhost");
		}

		requestBody += "&PAYMENTREQUEST_0_AMT="
				+ checkoutDetails.get("PAYMENTREQUEST_0_AMT").toString();

		// Check for additional parameters that can be passed in
		// DoExpressCheckoutPayment API call
		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_CURRENCYCODE")
				.toString())) {
			requestBody += "&PAYMENTREQUEST_0_CURRENCYCODE="
					+ encode(checkoutDetails.get(
							"PAYMENTREQUEST_0_CURRENCYCODE").toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_ITEMAMT="
					+ encode(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT")
							.toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_TAXAMT="
					+ encode(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT")
							.toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_SHIPPINGAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_SHIPPINGAMT="
					+ encode(checkoutDetails
							.get("PAYMENTREQUEST_0_SHIPPINGAMT").toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_HANDLINGAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_HANDLINGAMT="
					+ encode(checkoutDetails
							.get("PAYMENTREQUEST_0_HANDLINGAMT").toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_SHIPDISCAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_SHIPDISCAMT="
					+ encode(checkoutDetails
							.get("PAYMENTREQUEST_0_SHIPDISCAMT").toString());
		}

		if (isSet(checkoutDetails.get("PAYMENTREQUEST_0_INSURANCEAMT"))) {
			requestBody += "&PAYMENTREQUEST_0_INSURANCEAMT="
					+ encode(checkoutDetails.get(
							"PAYMENTREQUEST_0_INSURANCEAMT").toString());
		}

		/*
		 * Make the call to PayPal to finalize payment If an error occurred,
		 * show the resulting errors
		 */
		Map<String, String> docheckoutresult = paypalApi.httpPaypalCall(
				"DoExpressCheckoutPayment", requestBody);
		/*
		 * Display the API response back to the browser. If the response from
		 * PayPal was a success, display the response parameters' If the
		 * response was an error, display the errors.
		 */
		// System.out.println("do checkout result : " + new
		// ObjectMapper().writeValueAsString(docheckoutresult));

		return docheckoutresult;
	}

	/**
	 * This is used that given value is valid or not.
	 * 
	 * @param value
	 * @return
	 */
	private boolean isSet(Object value) {
		return (value != null && value.toString().length() != 0);
	}

	/**
	 * This is used encode the object.
	 * 
	 * @param object
	 * @return
	 */
	private String encode(Object object) {
		try {
			return URLEncoder.encode((String) object, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.log(Level.ERROR,
					"Exception Inside Paypal Service in encode method :::::::"
							+ e);
		}
		return (String) object;
	}

	/**
	 * This is used decode the object.
	 * 
	 * @param object
	 * @return
	 */
	/*
	 * private String decode(Object object){ try { return
	 * URLDecoder.decode((String) object, "UTF-8"); } catch
	 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return (String) object; }
	 */

	/**
	 * This is used license details for payment based on organization id.
	 * 
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> getLicensePaymentInfo(Integer orgId) {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in getLicensePaymentInfo method :::::::");
		Map<String, Object> licenseDetails = new HashMap<String, Object>();
		License license = QbisLicenseService.companyLicenseDetails(orgId);
		if (license.getLicenseId() != null && license.getLicenseId() != 1
				&& license.getLicenseId() > 0) {
			if (license.getPaymentStatus() == 1) {
				licenseDetails.put("paymentstatus", 1);
				licenseDetails.put("msg", "already done");
				licenseDetails.put("result", "SUCCESS");
			} else {
				licenseDetails.put("paymentstatus", 0);
				licenseDetails.put("license", license);
				licenseDetails.put("msg", "not done");
				licenseDetails.put("result", "SUCCESS");
			}
		} else {
			licenseDetails.put("msg", "license did not found");
			licenseDetails.put("result", "ERROR");
		}
		return licenseDetails;
	}

	/**
	 * Here code is for Paypal Rest APi.
	 */

	/**
	 * This is used getting access token using basic auth from paypal.
	 * 
	 * @return Body
	 * @throws JSONException
	 * @throws IOException
	 */
	public Body getAccessTokenForPayment() throws JSONException, IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in getAccessTokenForPayment method :::::::");
		ObjectMapper mapper = new ObjectMapper();
		String requestbody = "grant_type=client_credentials";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", "application/json");
		headers.add("Accept-Language", "en_US");
		String auth = ConstantUtil.PAYPAL_APP_CLIENT_ID + ":"
				+ ConstantUtil.PAYPAL_APP_SECRET;
		byte[] plainCredsBytes = auth.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> entity = new HttpEntity<String>(requestbody, headers);
		ResponseEntity<String> response = paypalApi.callPayaplRestApi(
				ConstantUtil.PAYPAL_TOKEN_URL, entity, "POST");
		Body responseBody = new Body();
		if (response.getBody() != null) {
			responseBody = mapper.readValue(response.getBody(),
					new TypeReference<Body>() {
					});
			// System.out.println("step 1paypal response body for accesstoken = "+
			// mapper.writeValueAsString(responseBody));
		}
		return responseBody;
	}

	/**
	 * This is used create payment.
	 * 
	 * @param body
	 * @param url
	 * @param license
	 * @return PaypalResponseBody
	 * @throws JSONException
	 * @throws IOException
	 */
	public PaypalResponseBody createPaypalPayment(Body body, String url,
			License license) throws JSONException, IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in createPaypalPayment method :::::::");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-Type", "application/json");
		headers.add("Authorization",
				body.getToken_type() + " " + body.getAccess_token());
		PaypalRequestBody requestbody = new PaypalRequestBody();
		RedirectUrls redirect_urls = new RedirectUrls();
		redirect_urls.setReturn_url(url + "/confirmpaypalpayment");
		redirect_urls.setCancel_url(url + "/paypalpaymentcancel");
		requestbody.setRedirect_urls(redirect_urls);
		Payer payer = new Payer();
		payer.setPayment_method("paypal");
		requestbody.setPayer(payer);
		Transactions transactions[] = new Transactions[1];
		transactions[0] = new Transactions();
		final Double itemCost = license.getCost();
		final Double shippingCharge = 0.00;
		final Double handlingCharge = 0.00;
		final Double shippingDiscount = 0.00;
		final Double tax = 0.00;
		final Double amountTotal = itemCost + shippingCharge + handlingCharge
				+ shippingDiscount;
		Amount amount = new Amount();
		amount.setTotal(amountTotal.toString());
		amount.setCurrency("USD");
		Details details = new Details();
		details.setShipping(shippingCharge.toString());
		details.setHandling_fee(handlingCharge.toString());
		details.setShipping_discount(shippingDiscount.toString());
		details.setTax(tax.toString());
		details.setSubtotal(license.getCost().toString());
		// amount.setDetails(details);
		transactions[0].setAmount(amount);
		transactions[0]
				.setDescription("Your have choosen advance licence so you have to need pay for it.");
		ItemList item_list = new ItemList();
		Items items[] = new Items[1];
		items[0] = new Items();
		items[0].setCurrency("USD");
		items[0].setDescription("This is advance licence for your account.");
		items[0].setName(license.getLicenseName());
		items[0].setPrice(license.getCost().toString());
		items[0].setQuantity("1");
		items[0].setSku("1");
		items[0].setTax(tax.toString());
		item_list.setItems(items);
		// transactions[0].setItem_list(item_list);
		requestbody.setIntent("sale");
		requestbody.setTransactions(transactions);
		ObjectMapper mapper = new ObjectMapper();
		String request = mapper.writeValueAsString(requestbody);
		HttpEntity<String> entity = new HttpEntity<String>(request, headers);
		ResponseEntity<String> response = paypalApi.callPayaplRestApi(
				ConstantUtil.PAYPAL_CREATE_PAYMENT_URL, entity, "POST");
		PaypalResponseBody responseBody = mapper.readValue(response.getBody(),
				new TypeReference<PaypalResponseBody>() {
				});
		// System.out.println("step 2 create payment : " +
		// mapper.writeValueAsString(responseBody));
		return responseBody;
	}

	/**
	 * This is used execute paypal payment.
	 * 
	 * @param payerId
	 * @param paypalResponseBody
	 * @param body
	 * @return PaypalResponseBody
	 * @throws JSONException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public PaypalResponseBody executePaypalPayement(String payerId,
			PaypalResponseBody paypalResponseBody, Body body)
			throws JSONException, JsonParseException, JsonMappingException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in executePaypalPayement method :::::::");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-Type", "application/json");
		headers.add("Authorization",
				body.getToken_type() + " " + body.getAccess_token());
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("payer_id", payerId);
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody.toString(),
				headers);
		String EXECUTE_URL = null;
		Links links[] = paypalResponseBody.getLinks();
		for (int i = 0; i < links.length; i++) {
			if (links[i].getRel().equalsIgnoreCase("execute")
					&& links[i].getMethod().equalsIgnoreCase("POST")) {
				EXECUTE_URL = links[i].getHref();
			}
		}
		ResponseEntity<String> response = paypalApi.callPayaplRestApi(
				EXECUTE_URL, entity, "POST");
		logger.log(Level.DEBUG,
				"Inside Paypal Service in executePaypalPayement method response body :::::::" + response.getBody());
		PaypalResponseBody finalResponseBody = mapper.readValue(
				response.getBody(), new TypeReference<PaypalResponseBody>() {
				});
		return finalResponseBody;
	}

	/**
	 * This is used getting sale information from response of execute payment.
	 * 
	 * @param paypalResponseBody
	 * @return Sale
	 */
	public Sale paypalSaleInformation(PaypalResponseBody paypalResponseBody) {
		logger.log(Level.DEBUG,
				"Inside Paypal Service in paypalSaleInformation method :::::::");
		Sale sale = new Sale();
		for (int i = 0; i < paypalResponseBody.getTransactions().length; i++) {
			for (int j = 0; paypalResponseBody.getTransactions()[i]
					.getRelated_resources() != null
					&& j < paypalResponseBody.getTransactions()[i]
							.getRelated_resources().length; j++) {
				sale = paypalResponseBody.getTransactions()[i]
						.getRelated_resources()[j].getSale();
			}

		}
		return sale;
	}
}
