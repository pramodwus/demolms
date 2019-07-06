package com.qbis.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qbis.model.HlsurlRequest;

import eu.medsea.mimeutil.MimeUtil;

public class CommonUtil {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * This method is used for generating the random string.
	 * 
	 * @param random
	 * @param strName
	 * @param length
	 * @return
	 */
	public static String generateRandomString(Random random, String strName, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = strName.charAt(random.nextInt(strName.length()));
		}
		return new String(text);
	}

	/**
	 * Method to get organization id from URL.
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static String getOrgIdFromURL(HttpServletRequest httpRequest) {

		/*
		 * String orgId = ""; String serverName = httpRequest.getServerName();
		 * String domainName = ConstantUtil.DOMAIN_NAME; int indexOfDomain =
		 * serverName.indexOf(domainName); if (indexOfDomain != -1 &&
		 * indexOfDomain > 0) { orgId = serverName.substring(0, indexOfDomain -
		 * 1); }
		 */
		return ConstantUtil.STATIC_ORG_ID;
	}

	/**
	 * Method to validate email address.
	 * 
	 * @param emailid
	 * @return true valid email, false invalid email
	 */
	public static Boolean validateEmailAddress(String email) {

		Pattern pattern;
		Matcher matcher;

		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(email);
		return matcher.matches();

	}

	/**
	 * This is used getting workbook based on uploaded excel sheet.
	 * 
	 * @param inputStream
	 * @param excelFilePath
	 * @return Workbook
	 * @throws IOException
	 */
	public static Workbook getWorkbook(ByteArrayInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	/**
	 * This is used for getting cell from sheet based on its type.
	 * 
	 * @param cell
	 * @return Object
	 */
	public static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		}

		return null;
	}

	/**
	 * This method is used for convert bytes ti mb.
	 * 
	 * @param p_bytes
	 * @return
	 */
	public static Long convertBytesToMB(Long p_bytes) {
		long inKB = p_bytes / 1024;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		long inMB = inKB / 1024;
		return inMB;
	}

	/**
	 * This method is used for convert bytes to gb
	 * 
	 * @param p_bytes
	 * @return
	 */
	public static Long convertBytesToGB(Long p_bytes) {
		long inMB = convertBytesToMB(p_bytes);
		long inGB = inMB / 1024;
		return inGB;
	}

	/**
	 * This method is used for getting token from user cookie.
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return token {@link String}
	 */
	public static String getTokenFromCookie(HttpServletRequest request) {
		logger.debug("Inside CommonUtil in getTokenFromCookie method ::::::::");
		String token = null;
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (ConstantUtil.USER_COOKIE_KEY.equals(cookie.getName())) {
						token = cookie.getValue();
						break;
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Inside CommonUtil getTokenFromCookie method:::::::: ", ex);
		}
		return token;
	}

	/**
	 * This method is used for deleting token from user cookie.
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return {@link Boolean}
	 */
	public static boolean deleteUserCookie(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Inside CommonUtil in deleteUserCookie method ::::::::");
		boolean isDeleted = false;
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (ConstantUtil.USER_COOKIE_KEY.equals(cookie.getName())) {
						cookie.setMaxAge(0);
						cookie.setDomain(".eluminate.in");
						cookie.setPath("/");
						cookie.setValue(null);
						response.addCookie(cookie);
						isDeleted = true;
						break;
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Inside CommonUtil in deleteUserCookie method:::::::: ", ex);
		}
		return isDeleted;
	}

	/**
	 * This method is used for adding token in the cookie
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return {@link Boolean}
	 */
	public static boolean setUserCookie(String token, HttpServletResponse response) {
		logger.debug("Inside CommonUtil in setUserCookie method ::::::::");
		boolean isAdded = false;
		try {
			Cookie cookie = new Cookie(ConstantUtil.USER_COOKIE_KEY, token);
			cookie.setMaxAge(60 * 60);
			cookie.setDomain(".eluminate.in");
			cookie.setPath("/");
			response.addCookie(cookie);
			isAdded = true;
		} catch (Exception ex) {
			logger.error("Inside CommonUtil in deleteUserCookie method:::::::: ", ex);
		}
		return isAdded;
	}
	
	
	public static boolean setHlsPLayerCookie(HlsurlRequest request, HttpServletResponse response) {
		logger.debug("Inside CommonUtil in setHlsPLayerCookie method ::::::::");
		boolean isAdded = false;
		try {
			Cookie cookie = new Cookie(ConstantUtil.Cloud_Front_policy, request.getCloudFrontPolicy());
            Cookie cookie2=new Cookie(ConstantUtil.Cloud_Front_signature, request.getCloudFrontSignature());
            Cookie cookie3=new Cookie(ConstantUtil.Cloud_Front_key_pair_id, request.getCloudFrontKeyPairId());
			cookie.setMaxAge(60 * 60);
			cookie.setDomain(".eluminate.in");
			cookie.setPath("/");
			cookie2.setMaxAge(60 * 60);
			cookie2.setDomain(".eluminate.in");
			cookie2.setPath("/");
			cookie3.setMaxAge(60 * 60);
			cookie3.setDomain(".eluminate.in");
			cookie3.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
			isAdded = true;
		} catch (Exception ex) {
			logger.error("Inside CommonUtil in deleteUserCookie method:::::::: ", ex);
		}
		System.out.println("Cookie"+isAdded);
		return isAdded;
	}

	/**
	 * This method is used for getting OS and browser info.
	 * 
	 * @param request
	 * @return
	 */
	public static String getOSAndBrowser(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";

		// =================OS=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/",
			// "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		// log.info("Operating System======>"+os);
		// log.info("Browser Name==========>"+browser);
		return (os + "##" + browser);
	}

	/**
	 * This is used getting the mime type of file using file link.
	 * 
	 * @param link
	 * @return mimeType
	 */
	public static String getMimeType(String link) {
		String mimeType = "";
		try {
			MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
			URL url = new URL(link);
			Collection<?> mimeTypes = MimeUtil.getMimeTypes(url);
			mimeType = MimeUtil.getFirstMimeType(mimeTypes.toString()).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mimeType;
	}

	/**
	 * This is used for unzip the zip file.
	 * 
	 * @param sourcePath
	 * @param destPath
	 * @return isUnZip
	 */
	public static Boolean unZipTheFile(String sourcePath, String destPath) {
		Boolean isUnZip = false;
		try {
			ZipFile zipFile = new ZipFile(sourcePath);
			File zipSrc = new File(sourcePath);
			if (zipFile.isEncrypted()) {
				isUnZip = false;
			} else {
				zipFile.extractAll(destPath);
				File zipDir = new File(destPath);
				int totalDir = 0;
				String singleDirName = null;
				for (File file : zipDir.listFiles()) {
					if (file.isDirectory()) {
						singleDirName = file.getName();
						totalDir++;
					}
				}
				if (totalDir == 1) {
					File src = new File(destPath + File.separator + singleDirName);
					File des = new File(destPath);
					if (src.isDirectory()) {
						FileUtils.copyDirectory(src, des);
						FileUtils.deleteDirectory(src);
					}
				}

				isUnZip = true;
			}
			if (zipSrc.exists()) {
				zipSrc.delete();
			}

		} catch (ZipException | IOException e) {
			e.printStackTrace();
		}
		return isUnZip;
	}
}
