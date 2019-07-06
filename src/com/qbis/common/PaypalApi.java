package com.qbis.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This is used related paypal api calling.
 * 
 * @author ankur kumar
 *
 */
@Component
public class PaypalApi {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger.getLogger(PaypalApi.class);

	private String SBN_CODE;
	private String USER; // = "akbajwaakgec-facilitator_api1.gmail.com";
	private String PWD; // = "Q5K6W9E5PDS5HGHF";
	private String SIGNATURE; // =
								// "AFcWxV21C7fd0v3bYYYRCpSSRl31ATWK4zZdG.sirCj21SCNCnqFhaT7";
	private String VERSION;// = "109.0";
	private String INIT_ENDPOINT_URL;

	public PaypalApi() {
		final Properties prop = new Properties();
		InputStream input = null;
		try {
			final String filename = "database.properties";

			input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			// get the property value from config.properties file

			String strSandbox = "";
			if (prop.getProperty("SANDBOX_FLAG").equals("true")) {
				strSandbox = "_SANDBOX";
			}
			// ButtonSource Tracker Code
			SBN_CODE = prop.getProperty("SBN_CODE");
			USER = prop.getProperty("PP_USER" + strSandbox);
			PWD = prop.getProperty("PP_PASSWORD" + strSandbox);
			SIGNATURE = prop.getProperty("PP_SIGNATURE" + strSandbox);
			INIT_ENDPOINT_URL = prop
					.getProperty("PP_NVP_ENDPOINT" + strSandbox);
			// CHECKOUT_URL = prop.getProperty("PP_CHECKOUT_URL"+strSandbox);
			VERSION = prop.getProperty("API_VERSION");
			java.lang.System.setProperty("https.protocols",
					prop.getProperty("SSL_VERSION_TO_USE"));
		} catch (IOException e) {
			logger.log(Level.ERROR,
					"Exception inside Paypal Api in static block first catch:::::::"
							+ e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.log(Level.ERROR,
							"Exception inside Paypal Api in static block second catch:::::::"
									+ e);
				}
			}
		}

	}

	/**
	 * This is used for making call to paypal rest api.
	 * 
	 * @param methodName
	 * @param bodyParam
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> httpPaypalCall(String methodName,
			String bodyParam) throws IOException {

		logger.log(Level.DEBUG,
				"Inside Paypal Api in httpPaypalCall method:::::::");
		Map<String, String> result = null;

		try {
			/**
			 * create paypal url
			 */
			URL obj = new URL(INIT_ENDPOINT_URL);
			/**
			 * open connection.
			 */
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			/**
			 * set request type post.
			 */
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty("User-Agent", "Mozilla/4.0");
			/**
			 * create body data for post.
			 */
			String postParams = "USER=" + USER + "&PWD=" + PWD + "&SIGNATURE="
					+ SIGNATURE + "&METHOD=" + methodName + "&VERSION="
					+ VERSION + bodyParam + "&BUTTONSOURCE=" + SBN_CODE;
			con.setRequestProperty("Content-Length",
					String.valueOf(postParams.length()));
			/**
			 * send post request.
			 */
			con.setDoInput(true);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int rcode = con.getResponseCode();
			if (rcode != -1) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				result = deformatNVP(response.toString());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception inside Paypal Api in httpPaypalCall method:::::::"
							+ e);
		}
		return result;
	}

	/**
	 * This is used decode the response of paypal api.
	 * 
	 * @param pPayload
	 * @return
	 */
	public static Map<String, String> deformatNVP(String pPayload) {
		logger.log(Level.DEBUG,
				"Inside Paypal Api in deformatNVP method:::::::");
		Map<String, String> nvp = new HashMap<String, String>();
		StringTokenizer stTok = new StringTokenizer(pPayload, "&");

		while (stTok.hasMoreTokens()) {
			StringTokenizer sinToken = new StringTokenizer(stTok.nextToken(),
					"=");
			if (sinToken.countTokens() == 2) {
				String key;
				try {
					key = URLDecoder.decode(sinToken.nextToken(), "UTF-8");
					String value;
					value = URLDecoder.decode(sinToken.nextToken(), "UTF-8");
					nvp.put(key.toUpperCase(), value);
				} catch (UnsupportedEncodingException e) {
					logger.log(Level.ERROR,
							"Exception inside Paypal Api in deformatNVP method:::::::"
									+ e);
				}

			}
		}
		return nvp;
	}

	/**
	 * This is used for calling paypal rest api.
	 * 
	 * @param requestUrl
	 * @param entity
	 * @param methodType
	 * @return
	 */
	public ResponseEntity<String> callPayaplRestApi(String requestUrl,
			HttpEntity<String> entity, String methodType) {
		logger.log(Level.DEBUG,
				"Inside Paypal Api in callPayaplRestApi method:::::::");
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		if (methodType != null && methodType.equalsIgnoreCase("GET")) {
			response = restTemplate.exchange(requestUrl, HttpMethod.GET,
					entity, String.class);
		} else if (methodType != null && methodType.equalsIgnoreCase("POST")) {
			response = restTemplate.exchange(requestUrl, HttpMethod.POST,
					entity, String.class);
		}

		return response;
	}

}
