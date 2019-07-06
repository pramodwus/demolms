package com.qbis.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This is used for verify the re-captcha response provided by user from client
 * side.
 * 
 * @author ankur kumar
 *
 */
public class VerifyRecaptcha {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6LflDggUAAAAAEsXU3qTQu6MM3Tu_Tu2OnnZr_na";
	private final static String USER_AGENT = "Mozilla/5.0";
	private final static Logger logger = Logger
			.getLogger(VerifyRecaptcha.class);

	/**
	 * This is used for verify re-captcha response.
	 * 
	 * @param gRecaptchaResponse
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		logger.log(Level.DEBUG, "Inside VerifyRecaptcha verify method::::");
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}

		try {
			/**
			 * create url
			 */
			URL obj = new URL(url);
			/**
			 * open connection for verify captcha.
			 */
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			/**
			 * set request type post.
			 */
			con.setRequestMethod("POST");
			/**
			 * set request agent in request.
			 */
			con.setRequestProperty("User-Agent", USER_AGENT);
			/**
			 * set request agent in Accept-Language.
			 */
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			/**
			 * create body data for post.
			 */
			String postParams = "secret=" + secret + "&response="
					+ gRecaptchaResponse;
			/**
			 * send post request.
			 */
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonReader jsonReader = Json.createReader(new StringReader(response
					.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();

			return jsonObject.getBoolean("success");

		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside VerifyRecaptcha verify method::::", e);
			return false;
		}
	}
}
