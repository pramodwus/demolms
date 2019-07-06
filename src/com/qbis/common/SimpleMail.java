package com.qbis.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

/**
 * 20131219 MKP Modified for appending header and footer in User Signup Cycle
 * Emails. 20131219 MKP Modified Design of header and footer in Signup Emails.
 * 20131227 MKP Modified for removing debug prints from login cycle emails.
 * 20140101 MKP Modified emails for using current domain. 20140110 MKP Updated
 * header image for adding text. 20140121 MKP Updated for not displaying image
 * in signup confirmation email 20140129 MKP JIRA 412 Updated html for removing
 * overlapping of header image and body text in ipad
 * */
@Component
public class SimpleMail {

	/*protected static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	protected static final String SMTP_AUTH_USER = "Bellurbis";
	protected static final String SMTP_AUTH_PWD = "Bellurbis123";*/

	/*protected static final String SMTP_HOST_NAME = "smtp.gmail.com";
	protected static final String SMTP_AUTH_USER = "info@bellurbis.com";
	protected static final String SMTP_AUTH_PWD = "Password123.";*/
	protected static final String SMTP_HOST_NAME = "smtp.zoho.com";
	protected static final String SMTP_AUTH_USER = "info@qbis.in";
	protected static final String SMTP_AUTH_PWD = "DU4j6XMGubZh";
	
	public void test() throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		// uncomment for debugging infos to stdout
		// mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		Multipart multipart = new MimeMultipart();

		BodyPart part1 = new MimeBodyPart();
		part1.setText("Greetings from Mowingo,"
				+ "Thank you for signing up for Mowingo. "
				+ "In order to complete your signup process, please click on the link below"
				+ "Sincerely," + "The Mowingo Team");

		// BodyPart part2 = new MimeBodyPart();
		// part2.setContent("<b>This is multipart mail and u read part2......</b>",
		// "text/html");

		multipart.addBodyPart(part1);
		// multipart.addBodyPart(part2);

		message.setContent(multipart);

		message.setFrom(new InternetAddress("signup@mowingo.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"ehud@mowingo.com"));
		message.setSubject("Mowingo Signup");

		transport.connect();
		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public void sendSignup(String addr, String key) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		// uncomment for debugging infos to stdout
		mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		Multipart multipart = new MimeMultipart();

		BodyPart part2 = new MimeBodyPart();
		part2.setContent(
				"<p><b>Greetings from Mowingo,</b></p><br/>"
						+ "<p>Thank you for signing up for the Mowingo network.</p>"
						+ "<p>In order to complete your signup process, please click on the link below:</p><br/>"
						+ "<p><a href=\"http://mowingosrv1.dyndns.biz:9999/nchant/signconf.jsp?key="
						+ key
						+ "\">confirm signup</a></p><br/>"
						+ "<p>This link will be available for the next 48 hours</p><br/>"
						+ "<p>Sincerely,</p><br/>" + "<p>The Mowingo Team</p>",
				"text/html");

		multipart.addBodyPart(part2);

		message.setContent(multipart);

		message.setFrom(new InternetAddress("<info@mowingo.com>"));
		message.addRecipient(Message.RecipientType.TO,
				new InternetAddress(addr));
		message.setSubject("Mowingo Signup");

		transport.connect();
		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public void sendPasswordConfirm(String addr, String key) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		// uncomment for debugging infos to stdout
		// mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		Multipart multipart = new MimeMultipart();

		BodyPart part2 = new MimeBodyPart();
		part2.setContent(
				"<p><b>Greetings from Mowingo,</b></p><br/>"
						+ "<p>We received your request to reset your Mowingo password.</p>"
						+ "<p>In order to be emailed with a new password, please click on the link below:</p><br/>"
						+ "<p><a href=\"http://srv.mowingo.com:9999/nchant/passconf.jsp?key="
						+ key + "\">reset password</a></p><br/>"
						+ "<p>Sincerely,</p><br/>" + "<p>The Mowingo Team</p>",
				"text/html");

		multipart.addBodyPart(part2);

		message.setContent(multipart);

		message.setFrom(new InternetAddress("<info@mowingo.com>"));
		message.addRecipient(Message.RecipientType.TO,
				new InternetAddress(addr));
		message.setSubject("Mowingo Signup");

		transport.connect();
		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public void sendPassword(String addr, String pw) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		// uncomment for debugging infos to stdout
		// mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		Multipart multipart = new MimeMultipart();

		BodyPart part2 = new MimeBodyPart();
		part2.setContent(
				"<p><b>Greetings from Mowingo,</b></p><br/>"
						+ "<p>To your request, we are changed your Mowingo password</p>"
						+ "<p>Your new password is:</p><br/>" + "<p><b>" + pw
						+ "</b></p><br>" + "<p>Sincerely,</p><br/>"
						+ "<p>The Mowingo Team</p>", "text/html");

		multipart.addBodyPart(part2);

		message.setContent(multipart);

		message.setFrom(new InternetAddress("<info@mowingo.com>"));
		message.addRecipient(Message.RecipientType.TO,
				new InternetAddress(addr));
		message.setSubject("Mowingo Signup");

		transport.connect();
		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	/**
	 * @author Munish Pathak
	 * @usage Sends the sign up email
	 * @modified MKP 20131219 Updated for appending header and footer in mail
	 *           content.
	 * @modified MKP 20131223 Updated design of headers and footers.
	 * @modified MKP 20131227 Modified for removing debug prints.
	 * @modified MKP 20140101 Modified for email template images.
	 * @modified MKP 20140110 Updated header image for adding text.
	 * @modified MKP 20140121 Updated for not displaying image in signup
	 *           confirmation email
	 * @modified MKP 20140129 JIRA 412 Updated html for removing overlapping of
	 *           header image and body text in ipad
	 * */
	public void sendEmail(String addr, String content, int mode, String baseUrl) {

		/**
		 * Mode : 1 - New Registration 2 - Forget Password 3 - Change Password 4
		 * - Forget Password - Password in mail after mail verified 5 - New
		 * Registration - Mail Verified
		 * */
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");

			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(props, auth);
			// uncomment for debugging infos to stdout
			// mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			Multipart multipart = new MimeMultipart();

			BodyPart part2 = new MimeBodyPart();
			// 20140121 Munish Updated for not displaying image in signup
			// confirmation email
			// 20140129 Munish JIRA 412 Updated html for displaying image
			// properly in header section i.e. not hiding body text by header
			// image in ipad
			content = "<!doctype html><html lang=\"en\" style=\"margin:0;padding:0;\"><head><meta charset=\"UTF-8\"><title></title></head><body style=\"margin:0;padding:0;font-family:helvetica;\"><table cellspacing='0' cellpadding='0' width='100%'><tr width='100%'><td width='100%' height='20px' style='background-color:#FEC431;font-family:Times New Roman;font-size:2.5vw;color:#DD1E35;text-align:left;padding-top:10px;padding-bottom:10px;'><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Offers on Your Mobile.</strong></td></tr><tr><td><img src=\""
					+ baseUrl
					+ "/img/m/head1.png\" width='100%' style='margin:0;padding:0'/></td></tr><tr><td style='padding:2% 5% 2% 5%;display:block;margin:0;font:normal 12 Arial BLACK;'>"
					+ content
					+ "</td></tr><tr><td><br/><span style=\"font-color:black; font-face:Arial;\"><small>This email was sent to "
					+ addr
					+ " by Mowingo, Inc. P.O. Box 3522, Los Altos, CA 94024.</small></span></td></tr></table></body></html>";

			part2.setContent(content, "text/html");

			multipart.addBodyPart(part2);

			message.setContent(multipart);

			message.setFrom(new InternetAddress(
					"McDAppEmailService@mowingo.com", "McD App"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					addr));
			switch (mode) {
			case 1:
				message.setSubject("Confirmation Needed for McD App");
				break;
			case 2:
				message.setSubject("Forgot your McD App Password?");
				break;
			case 3:
				message.setSubject("Your McD App Password has been Changed");
				break;
			case 4:
				message.setSubject("Welcome to McD App!");
				break;
			case 5:
				message.setSubject("Your New McD App Password");
				break;
			case 6:
				message.setSubject("Your account was locked");
				break;
			}

			// System.out.println("Mail Being Sent :\nTo\n "+addr+" \nSubject:\n"+message.getSubject()+", \nMessage:\n "+content);
			transport.connect();
			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			transport.close();

			// System.out.println("sendMail :String "+addr+", String "+content+" , Ex : ");
		} catch (Exception ex) {
			// System.out.println("sendMail :String "+addr+", String "+content+" , Ex : "+ex.toString());
			// LogManager.error(SimpleMail.class.getName(),
			// "sendMail :String "+addr+", String "+content+" , Ex : "+ex.toString());
			ex.printStackTrace();
		}
	}

	public class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
/**
 * This is used for sending mail related to qbis.
 * @param from
 * @param to
 * @param Subject
 * @param content
 */
	public void sendEmail(String from, String to, String Subject, String content) {

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			
			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(props, auth);
			// uncomment for debugging infos to stdout
			mailSession.setDebug(false);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			Multipart multipart = new MimeMultipart();

			BodyPart part2 = new MimeBodyPart();

			System.out.println(content);
			part2.setContent(content, "text/html");

			multipart.addBodyPart(part2);

			message.setContent(multipart);

			message.setFrom(new InternetAddress(from, "Qbis"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(Subject);

			transport.connect();
			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception ex) {
			// LogManager.error(SimpleMail.class.getName(),
			// "sendMail :String "+addr+", String "+content+" , Ex : "+ex.toString());
		}
	}

	public String sendCampaignEmail(String fromAddr, String fromName,
			String to, String Subject, String content) {

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");

			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(props, auth);
			// uncomment for debugging infos to stdout
			// mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			Multipart multipart = new MimeMultipart();

			BodyPart part2 = new MimeBodyPart();
			part2.setContent(content, "text/html");

			multipart.addBodyPart(part2);

			message.setContent(multipart);

			message.setFrom(new InternetAddress(fromAddr, fromName));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			message.setSubject(Subject);

			transport.connect();
			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception ex) {
			System.out.println("sendMail : String " + content + " , Ex : "
					+ ex.toString());
		}

		return "Success";
	}

}
