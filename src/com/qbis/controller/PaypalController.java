package com.qbis.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.common.CommonUtil;
import com.qbis.model.Body;
import com.qbis.model.License;
import com.qbis.model.Links;
import com.qbis.model.PaymentInfo;
import com.qbis.model.PaypalResponseBody;
import com.qbis.model.Sale;
import com.qbis.model.User;
import com.qbis.services.OrganizationService;
import com.qbis.services.PaypalService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.UserService;

/**
 * This is used for paypal api.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
public class PaypalController {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger
			.getLogger(PaypalController.class);

	/**
	 * Instance of {@link PaypalService}
	 */
	@Autowired
	private PaypalService paypalService;

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link QbisLicenseService}
	 */
	@Autowired
	private QbisLicenseService qbisLicenseService;

	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userservice;

	/**
	 * This is used for getting orgId and making payment for buying license.
	 * 
	 * @return
	 */
	@RequestMapping(value = "makePayment", method = RequestMethod.GET)
	public ModelAndView makePayment() {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in makePayment method :::::::");
		ModelAndView model = new ModelAndView();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		model.setViewName("redirect:/regsuccess");
		if (orgStatus) {
			Integer orgId = OrganizationService.getOrganizationId(orgName);
			if (orgId != null && orgId > 0) {
				model.setViewName("forward:/setExpressCheckout?orgID=" + orgId);
			}
		}
		return model;
	}

	/**
	 * This is used for calling SetExpressCheckout method in paypal api.
	 * 
	 * @param orgId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "setExpressCheckout", method = RequestMethod.GET)
	public ModelAndView setExpressCheckout(
			@RequestParam(value = "orgID") Integer orgId) throws IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in setExpressCheckout method ::::::::::");
		ModelAndView model = new ModelAndView();
		Map<String, Object> licenseDetail = paypalService
				.getLicensePaymentInfo(orgId);
		String licenseResult = licenseDetail.get("result").toString();
		String viewName = "redirect:/regsuccess";
		if (licenseResult.equals("SUCCESS")) {
			Integer paymentStatus = Integer.parseInt(licenseDetail.get(
					"paymentstatus").toString());
			if (paymentStatus == 0) {
				PaymentInfo payment = new PaymentInfo();
				License license = (License) licenseDetail.get("license");
				String path = request.getRequestURL().toString();
				int index = path.lastIndexOf("/");
				String newPath = path.substring(0, index);
				payment.setPaymentAction("SALE");
				payment.setTotalPaymentCurrency(license.getCost().toString());
				payment.setLogoImage(newPath + "/resources/images/logo.png");
				payment.setPaymentCurrencyType("USD");
				payment.setCancelUrl(newPath + "/paymentcancel");
				payment.setReturnUrl(newPath + "/paymentConfirmPage");

				Map<String, String> result = paypalService
						.getPaypalToken(payment);
				if (result != null) {
					String ack = result.get("ACK").toString().toUpperCase();

					if (ack != null
							&& (ack.equals("SUCCESS") || ack
									.equals("SUCCESSWITHWARNING"))) {
						request.getSession().setAttribute("orgIdForPayment",
								orgId);
						request.getSession().setAttribute(
								"licenseIdForPayment", license.getLicenseId());
						viewName = "redirect:https://www.sandbox.paypal.com/checkoutnow?token="
								+ result.get("TOKEN").toString();
						/*
						 * model.setViewName(
						 * "redirect:https://www.sandbox.paypal.com/checkoutnow?token="
						 * + result.get("TOKEN").toString());
						 */
					} else {
						// Display a user friendly Error on the page using any
						// of the following error information returned by PayPal
						String ErrorCode = result.get("L_ERRORCODE0")
								.toString();
						String ErrorShortMsg = result.get("L_SHORTMESSAGE0")
								.toString();
						String ErrorLongMsg = result.get("L_LONGMESSAGE0")
								.toString();
						String ErrorSeverityCode = result
								.get("L_SEVERITYCODE0").toString();

						String errorString = "SetExpressCheckout API call failed. "
								+ "<br>Detailed Error Message: "
								+ ErrorLongMsg
								+ "<br>Short Error Message: "
								+ ErrorShortMsg
								+ "<br>Error Code: "
								+ ErrorCode
								+ "<br>Error Severity Code: "
								+ ErrorSeverityCode;
						model.addObject("error", errorString);
						viewName = "paymenterror";
					}
				}
			}
		}
		model.setViewName(viewName);
		return model;
	}

	/**
	 * This is used redirect user to confirm payment page and showing payment
	 * details before proceed.
	 * 
	 * @param PayerID
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "paymentConfirmPage")
	public ModelAndView returnPaymentConfirmPage(
			@RequestParam("PayerID") String PayerID,
			@RequestParam("token") String token) throws IOException {
		ModelAndView model = new ModelAndView();
		String viewName = "redirect:/regsuccess";
		Map<String, String> checkoutdetails = paypalService
				.getExpressCheckoutDetails(PayerID, token);
		request.getSession().setAttribute("checkoutDetails", checkoutdetails);
		Integer orgId = (Integer) request.getSession().getAttribute(
				"orgIdForPayment");
		Map<String, Object> licenseDetail = paypalService
				.getLicensePaymentInfo(orgId);
		String licenseResult = licenseDetail.get("result").toString();
		if (licenseResult.equals("SUCCESS")) {
			Integer paymentStatus = Integer.parseInt(licenseDetail.get(
					"paymentstatus").toString());
			if (paymentStatus == 0) {
				try {
					License license = (License) licenseDetail.get("license");
					model.addObject("licensedetails",
							qbisLicenseService.extractLicenseDetails(license));
					model.addObject("checkoutDetails", checkoutdetails);
					model.addObject("PayerID", PayerID);
					model.addObject("token", token);
					viewName = "confirmpayment";
				} catch (Exception e) {
					viewName = "paymenterror";
				}
			}
		}
		model.setViewName(viewName);
		return model;
	}

	/**
	 * This is used for complete the payment and save this transaction details.
	 * 
	 * @param PayerID
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "doExpressCheckoutDetails", method = RequestMethod.POST)
	public ModelAndView getExpressCheckoutDetails(
			@RequestParam("PayerID") String PayerID,
			@RequestParam("token") String token) throws IOException {
		ModelAndView model = new ModelAndView();
		Integer orgIdForPayment = (Integer) request.getSession().getAttribute(
				"orgIdForPayment");
		Integer licenseIdForPayment = (Integer) request.getSession()
				.getAttribute("licenseIdForPayment");
		Map<String, Object> licenseDetail = paypalService
				.getLicensePaymentInfo(orgIdForPayment);
		String licenseResult = licenseDetail.get("result").toString();
		String viewpage = "redirect:/login";
		if (licenseResult.equals("SUCCESS")) {
			Integer paymentStatus = Integer.parseInt(licenseDetail.get(
					"paymentstatus").toString());
			if (paymentStatus == 0) {
				Map<String, String> checkoutdetails = (Map<String, String>) request
						.getSession().getAttribute("checkoutDetails");
				if (PayerID.equals(checkoutdetails.get("PAYERID").toString())
						&& token.equals(checkoutdetails.get("TOKEN").toString())) {
					Map<String, String> transactionDetails = paypalService
							.doExpressCheckoutDetails(checkoutdetails);
					String strAck = transactionDetails.get("ACK").toString();
					if (strAck != null
							&& (strAck.equalsIgnoreCase("Success") || strAck
									.equalsIgnoreCase("SuccessWithWarning"))) {
						qbisLicenseService.updateLicenseIdInOrg(
								orgIdForPayment, licenseIdForPayment);
						qbisLicenseService.saveTransectionDetails(
								orgIdForPayment, licenseIdForPayment,
								transactionDetails
										.get("PAYMENTINFO_0_PAYMENTSTATUS"),
								transactionDetails
										.get("PAYMENTINFO_0_TRANSACTIONID"),
								transactionDetails.get("PAYMENTINFO_0_AMT"));
						License license = qbisLicenseService
								.getLicenseDetails(orgIdForPayment);
						User user = userservice
								.getSuperUserDetailsByOrgId(orgIdForPayment);
						model.addObject("transactionId", transactionDetails
								.get("PAYMENTINFO_0_TRANSACTIONID"));
						model.addObject("amount",
								transactionDetails.get("PAYMENTINFO_0_AMT"));
						model.addObject("useremail", user.getEmail());
						model.addObject("subdomainname", user.getSubdomain());
						model.addObject("licenseName", license.getLicenseName());
						viewpage = "paymentsuccess";
					}
				} else {
					viewpage = "paymenterror";
				}
			}
		}
		model.setViewName(viewpage);
		return model;
	}

	/**
	 * This is used redirect on login page.
	 * 
	 * @return
	 */
	@RequestMapping(value = "doExpressCheckoutDetails", method = RequestMethod.GET)
	public ModelAndView getExpressCheckoutDetails() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:login");
		return model;
	}

	/**
	 * This is used redirect on error page when cancelled the payment.
	 * 
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	@RequestMapping(value = "paymentcancel")
	public ModelAndView errorPage() {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in paypalpaymentcancel method ::::::::::");
		ModelAndView model = new ModelAndView();
		model.addObject("cancelmsg", "cancel");
		model.setViewName("paymenterror");
		return model;
	}

	/**
	 * Here is code of controller handling paypal payment using Rest Api
	 * 
	 */

	/**
	 * This is used process paypal payment request.
	 * 
	 * @return
	 */
	@RequestMapping(value = "processPaymentRequest")
	public ModelAndView processPaymentRequest() {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in processPaymentRequest method :::::::");
		ModelAndView model = new ModelAndView();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		model.setViewName("redirect:/regsuccess");
		if (orgStatus) {
			Integer orgId = OrganizationService.getOrganizationId(orgName);
			if (orgId != null && orgId > 0) {
				model.setViewName("forward:/getTokenForPayment?orgID=" + orgId);
			}
		}
		return model;
	}

	/**
	 * This is used for getting token for processing of payment.
	 * 
	 * @param orgId
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "getTokenForPayment", method = RequestMethod.GET)
	public ModelAndView getTokenForPayment(
			@RequestParam(value = "orgID") Integer orgId) throws IOException,
			JSONException {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in getTokenForPayment method ::::::::::");
		ModelAndView model = new ModelAndView();
		String viewName = "redirect:/regsuccess";
		try {
			Map<String, Object> licenseDetail = paypalService
					.getLicensePaymentInfo(orgId);
			String licenseResult = licenseDetail.get("result").toString();
			License license = (License) licenseDetail.get("license");
			if (licenseResult.equals("SUCCESS")) {
				Integer paymentStatus = Integer.parseInt(licenseDetail.get(
						"paymentstatus").toString());
				if (paymentStatus == 0) {
					Body responseBodyForAccessToken = paypalService
							.getAccessTokenForPayment();
					if (responseBodyForAccessToken.getAccess_token() != null) {
						String path = request.getRequestURL().toString();
						int index = path.lastIndexOf("/");
						String url = path.substring(0, index);
						PaypalResponseBody responseBody = paypalService
								.createPaypalPayment(
										responseBodyForAccessToken, url,
										license);
						request.getSession().setAttribute("paypalResponseBody",
								responseBody);
						request.getSession().setAttribute(
								"responseBodyForAccessToken",
								responseBodyForAccessToken);
						Links links[] = responseBody.getLinks();
						for (int i = 0; i < links.length; i++) {
							if (links[i].getRel().equalsIgnoreCase(
									"approval_url")
									&& links[i].getMethod().equalsIgnoreCase(
											"REDIRECT")) {
								request.getSession().setAttribute(
										"orgIdForPayment", orgId);
								request.getSession().setAttribute(
										"licenseIdForPayment",
										license.getLicenseId());
								viewName = "redirect:" + links[i].getHref();
							}
						}
					} else {
						viewName = "paypalpaymenterror";
					}
				}
			}
		} catch (Exception e) {
			viewName = "paypalpaymenterror";
			logger.log(Level.ERROR,
					"Exception inside Paypal Controller in getTokenForPayment method ::::::::::"
							+ e);
		}
		model.setViewName(viewName);
		return model;
	}

	/**
	 * This is used redirect on error page when cancelled the payment.
	 * 
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	@RequestMapping(value = "paypalpaymentcancel")
	public ModelAndView paypalErrorPage() {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in paypalpaymentcancel method ::::::::::");
		ModelAndView model = new ModelAndView();
		model.addObject("cancelmsg", "cancel");
		model.setViewName("paypalpaymenterror");
		return model;
	}

	/**
	 * This is used redirecting on confirm payment.
	 * 
	 * @param paymentId
	 * @param PayerID
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "confirmpaypalpayment")
	public ModelAndView confirmPaypalPayment(
			@RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String PayerID,
			@RequestParam("token") String token) throws IOException {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in confirmpaypalpayment method ::::::::::");
		ModelAndView model = new ModelAndView();
		String viewName = "redirect:/regsuccess";
		try {
			Integer orgId = (Integer) request.getSession().getAttribute(
					"orgIdForPayment");
			Map<String, Object> licenseDetail = paypalService
					.getLicensePaymentInfo(orgId);
			String licenseResult = licenseDetail.get("result").toString();
			if (licenseResult.equals("SUCCESS")) {
				Integer paymentStatus = Integer.parseInt(licenseDetail.get(
						"paymentstatus").toString());
				if (paymentStatus == 0) {
					PaypalResponseBody paypalResponseBody = (PaypalResponseBody) request
							.getSession().getAttribute("paypalResponseBody");
					License license = (License) licenseDetail.get("license");
					model.addObject("licensedetails",
							qbisLicenseService.extractLicenseDetails(license));
					model.addObject("transactionsDetails",
							paypalResponseBody.getTransactions());
					model.addObject("paymentId", paymentId);
					model.addObject("PayerID", PayerID);
					model.addObject("token", token);
					viewName = "confirmpaypalpayment";
					model.setViewName("confirmpaypalpayment");
				}
			}
		} catch (Exception e) {
			viewName = "paypalpaymenterror";
			logger.log(Level.ERROR,
					"Exception inside Paypal Controller in confirmpaypalpayment method :::::::::: "
							+ e);
		}
		model.setViewName(viewName);
		return model;
	}

	/**
	 * This is used execute the payment.
	 * 
	 * @param paymentId
	 * @param PayerID
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "executePayPalPayment", method = RequestMethod.POST)
	public ModelAndView executePayPalPayment(
			@RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String PayerID,
			@RequestParam("token") String token) throws IOException,
			JSONException {
		logger.log(Level.DEBUG,
				"Inside Paypal Controller in executePayPalPayment method ::::::::::");
		ModelAndView model = new ModelAndView();
		String viewpage = "redirect:/login";
		try {
			Integer orgIdForPayment = (Integer) request.getSession()
					.getAttribute("orgIdForPayment");
			Integer licenseIdForPayment = (Integer) request.getSession()
					.getAttribute("licenseIdForPayment");
			Map<String, Object> licenseDetail = paypalService
					.getLicensePaymentInfo(orgIdForPayment);
			String licenseResult = licenseDetail.get("result").toString();
			if (licenseResult.equals("SUCCESS")) {
				Integer paymentStatus = Integer.parseInt(licenseDetail.get(
						"paymentstatus").toString());
				if (paymentStatus == 0) {
					Body responseBodyForAccessToken = (Body) request
							.getSession().getAttribute(
									"responseBodyForAccessToken");
					PaypalResponseBody paypalResponseBody = (PaypalResponseBody) request
							.getSession().getAttribute("paypalResponseBody");
					PaypalResponseBody finalResponseBody = paypalService
							.executePaypalPayement(PayerID, paypalResponseBody,
									responseBodyForAccessToken);
					if (finalResponseBody != null
							&& finalResponseBody.getState().equalsIgnoreCase(
									"approved")) {
						Sale sale = paypalService
								.paypalSaleInformation(finalResponseBody);
						qbisLicenseService.updateLicenseIdInOrg(
								orgIdForPayment, licenseIdForPayment);
						qbisLicenseService.saveTransectionDetails(
								orgIdForPayment, licenseIdForPayment,
								sale.getState(), finalResponseBody.getId(),
								sale.getAmount().getTotal());
						License license = qbisLicenseService
								.getLicenseDetails(orgIdForPayment);
						User user = userservice
								.getSuperUserDetailsByOrgId(orgIdForPayment);
						model.addObject("useremail", user.getEmail());
						model.addObject("subdomainname", user.getSubdomain());
						model.addObject("licenseName", license.getLicenseName());
						model.addObject("sale", sale);
						model.addObject("transactionId",
								finalResponseBody.getId());
						viewpage = "paypalpaymentsuccess";
					} else {
						viewpage = "paypalpaymenterror";
					}
				}
			}
		} catch (Exception e) {
			viewpage = "paypalpaymenterror";
			logger.log(Level.ERROR,
					"Exception inside Paypal Controller in executePayPalPayment method :::::::::: "
							+ e);
		}
		model.setViewName(viewpage);
		return model;
	}
    
	/**
	 * This is used redirect on login page.
	 * @return
	 */
	@RequestMapping(value = "executePayPalPayment", method = RequestMethod.GET)
	public ModelAndView executePayPalPayment(){
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
}
