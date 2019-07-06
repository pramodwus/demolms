package com.qbis.common;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qbis.model.Mailsender;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * This utility class provides the interface to send email from the application.
 * 
 * @author Vivek Kumar.
 *
 */
public class EmailSender {

	/**
	 * Constant for SMTP server Port number.
	 */
	private static final int		SMTP_PORT_NO;
	/**
	 * Constant for SMTP server Host name.
	 */
	private static final String 	SMTP_HOST_NAME;
	/**
	 * Flag for enabling SMTP server authentication.
	 */
	private static final boolean 	SMTP_AUTH_FLAG;
	/**
	 * Constant for SMTP server authorization user.
	 */
	private static final String 	SMTP_AUTH_USER;
	/**
	 * Constant for SMTP server authorization password.
	 */
	private static final String 	SMTP_AUTH_PASS;
	/**
	 * Flag for debugging.
	 */
	private static final boolean 	SMTP_DEBUG_FLAG;
	/**
	 * Constant for Mail Protocol.
	 */
	private static final Protocol 	SMTP_PROTOCOL;
	/**
	 * Instance of Authenticator for SMTP user authentication.
	 */
	private static Authenticator serverAuthenticator;
	
	private static final boolean SMTP_STARTTLS_ENABLE;
	/**
	 * Static code block to initialize the property of SMTP server.
	 */
	static{
		
		
		SMTP_PORT_NO 	= ApplicationProperties.getIntValue("SMTP_PORT_NO");
		SMTP_HOST_NAME 	= ApplicationProperties.getPropValue("SMTP_HOST_NAME");
		SMTP_AUTH_FLAG 	= ApplicationProperties.getBooleanValue("SMTP_AUTH_FLAG");
		SMTP_AUTH_USER 	= ApplicationProperties.getPropValue("SMTP_AUTH_USER");
		SMTP_AUTH_PASS 	= ApplicationProperties.getPropValue("SMTP_AUTH_PASS");
		SMTP_DEBUG_FLAG = ApplicationProperties.getBooleanValue("SMTP_DEBUG_FLAG");
		SMTP_PROTOCOL 	= Protocol.valueOf(ApplicationProperties.getPropValue("SMTP_PROTOCOL"));
		SMTP_STARTTLS_ENABLE = ApplicationProperties.getBooleanValue("SMTP_STARTTLS_ENABLE");
		
		
	}
	/**
	 * Method to create instance of {@link javax.mail.Session}
	 * @return
	 * @throws JsonProcessingException 
	 */
	private static Session getSession(){
		
		Properties mailProp = new Properties();
		mailProp.put("mail.transport.protocol", "smtp");
		mailProp.put("mail.smtp.host", SMTP_HOST_NAME);
		mailProp.put("mail.smtp.port", SMTP_PORT_NO);
		mailProp.put("mail.smtp.starttls.enable", "true");
		mailProp.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		if(SMTP_AUTH_FLAG){
			mailProp.put("mail.smtp.auth", "true");
			serverAuthenticator = new Authenticator() {
				private PasswordAuthentication authenticator = new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PASS);
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return authenticator;
				}
			};
		}
		switch(SMTP_PROTOCOL){
			case SMTPS :
				mailProp.put("mail.smtp.ssl.enable", true);
				break;
			case TLS :
				mailProp.put("mail.smtp.starttls.enable", true);
				break;
		}
				
		Session session = null;
		
		if(SMTP_AUTH_FLAG){
			session = Session.getDefaultInstance(mailProp, serverAuthenticator);
		}else{
			session = Session.getDefaultInstance(mailProp);
		}
		
		if(SMTP_DEBUG_FLAG){
			session.setDebug(SMTP_DEBUG_FLAG);
		}
		
		return session;
	}
	/**
	 * Method to send email where message text is a simple text.
	 * @param to
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String to, String subject, String mailMessage
	) throws MessagingException{
		
		sendEmail(to, ApplicationProperties.getPropValue("FROM_MAIL_ID"), subject, mailMessage);
	
	}
	/**
	 * Method to send email where message text is a simple text.
	 * @param to
	 * @param from
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String to, String from, String subject, String mailMessage
	) throws MessagingException{
		
		sendEmail(new String[]{to}, new String[]{}, new String[]{}, from, subject, mailMessage);
	
	}	
	/**
	 * Method to send email where message text is a simple text.
	 * 
	 * @param toList
	 * @param ccList
	 * @param bccList
	 * @param from
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String[] toList, String[] ccList, String[] bccList, String from, String subject, String mailMessage
	) throws MessagingException{
		
		Multipart multipart 	= new MimeMultipart("alternative");		
		multipart.addBodyPart(getSimpleBody(mailMessage));
		sendEmail(toList, ccList, bccList, from, subject, multipart);
				
	}
	/**
	 * Method to send email where email can contain rich HTML text and attachments.
	 * 
	 * @param to
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String to, String subject, Multipart mailMessage
	) throws MessagingException{
		
		sendEmail(to, ApplicationProperties.getPropValue("FROM_MAIL_ID"), subject, mailMessage);
	
	}
	/**
	 * Method to send email where email can contain rich HTML text and attachments.
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String to, String from, String subject, Multipart mailMessage
	) throws MessagingException{
		
		sendEmail(new String[]{to}, new String[]{}, new String[]{}, from, subject, mailMessage);
	
	}	
	/**
	 * Method to send email where email can contain rich HTML text and attachments.
	 * 
	 * @param toList
	 * @param ccList
	 * @param bccList
	 * @param from
	 * @param subject
	 * @param mailMessage
	 * @throws MessagingException
	 */
	public static void sendEmail(
			String[] toList, String[] ccList, String[] bccList, String from, String subject, Multipart mailMessage
	) throws MessagingException{
		
		Session session = getSession();
		
		MimeMessage messg = new MimeMessage(session);
		
		messg.setFrom(new InternetAddress(from));
		
		List<Address> list = new ArrayList<Address>();
		for(String to : toList){
			list.add(new InternetAddress(to));
		}
		messg.setRecipients(Message.RecipientType.TO, list.toArray(new InternetAddress[0]));
		
		list = new ArrayList<Address>();
		for(String cc : ccList){
			list.add(new InternetAddress(cc));
		}
		messg.setRecipients(Message.RecipientType.CC, list.toArray(new InternetAddress[0]));
		
		list = new ArrayList<Address>();
		for(String bcc : bccList){
			list.add(new InternetAddress(bcc));
		}
		messg.setRecipients(Message.RecipientType.BCC, list.toArray(new InternetAddress[0]));
		
		messg.setSubject(subject);
		messg.setSentDate(new Date());
		messg.setContent(mailMessage);
		
		Transport.send(messg);
		
	}	
	/**
	 * Method to create multipart body for simple text
	 * @param mailMessage
	 * @return
	 * @throws MessagingException 
	 */
	public static MimeBodyPart getSimpleBody(String mailMessage) throws MessagingException{
		
		MimeBodyPart body 		= new MimeBodyPart();
		body.setText(mailMessage);		
		return body;
		
	}
	/**
	 * Method to create multipart body for rich HTML text.
	 * @param mailMessage
	 * @return
	 * @throws MessagingException
	 */
	public static MimeBodyPart getRichTextBody(String mailMessage) throws MessagingException{
		
		MimeBodyPart body 		= new MimeBodyPart();
		body.setContent(mailMessage, "text/html");		
		return body;
		
	}
	/**
	 * Method to create multipart body for file attachment.
	 * @param attachment
	 * @return
	 * @throws MessagingException
	 */
	
	  public static MimeBodyPart getFileBody(File attachment) throws
	  MessagingException{
	  
	  MimeBodyPart body = new MimeBodyPart();
	  
	  DataSource source = new FileDataSource(attachment); 
	  body.setDataHandler(new
	  DataHandler(source));
	  
	  
	  
	  return body; }
	 

	
	/**
	 * This method is to generate mail message content for user who requested for demo.
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public static Multipart generateRequestDemoWelcomeMessg(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailthankrequestdemo.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This method is to generate mail message content for user who requested for demo.
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public static Multipart generateContactUsWelcomeMessg(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailthankcontactus.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This method is to generate mail message content for verify user account.
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public static Multipart generateVerifyAccountMessg(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailverifyuseraccount.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This is used for sending mail on success of user registration with advance licence. 
	 * @param dataobject
	 * @return
	 * @throws MessagingException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("deprecation")
	public static Multipart generateVerifyAccountMessgForAdvLicence(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/signupsuccessmailtoadvancelicenceuser.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This method is to generate mail message content for reset password.
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public static Multipart generateResetPasswordMessg(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailresetpassword.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This method is to generate mail message content for reset password.
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public static Multipart generateQbisExpireAlertMessg(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailqbisexpiry.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This is used for generating mail for sharing course or assessment to existing user.  
	 * @param dataobject
	 * @return
	 * @throws MessagingException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static Multipart generateSharedMsgForExistUser(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/sharedassessmentcourse.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This is used for generating mail content for sharing course or assessment for new created user.
	 * @param dataobject
	 * @return
	 * @throws MessagingException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static Multipart generateSharedMessgForNewCreatedUser(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/setpasswithsharedcourseassessment.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * This is used for generating mail for new user account.
	 * @param dataobject
	 * @return
	 * @throws MessagingException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static Multipart generateMsgForNewAccountCreation(Map<Object, Object> dataobject) 
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException
			, IOException, TemplateException
	
	{
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(EmailSender.class, "/");
		Template temp = config.getTemplate("templates/mailsetuppassword.ftl");
		Writer out = new StringWriter();
		temp.process(dataobject, out);
		out.flush();		
		String messgtext = out.toString();
		out.close();
		
		Multipart mailMessage 	= new MimeMultipart();
		mailMessage.addBodyPart(getRichTextBody(messgtext));		
		return mailMessage;
		
	}
	
	/**
	 * Main method to test this class.
	 * @param args
	 */
	public static void main(String[] args){
		try {
			Multipart multipart 	= new MimeMultipart("alternative");
			multipart.addBodyPart(getSimpleBody("Hey Vivek !!! This is simple text"));
			//multipart.addBodyPart(getRichTextBody("<h1 style:{color:red}>This is rich text message</h1>"));
			//multipart.addBodyPart(getFileBody(new File("/home/vivek/Downloads/Java Interview Status.xlsx")));
			sendEmail(
					new String[]{"vivek.kumar@bellurbis.com"}, new String[]{"vivek.kumar@bellurbis.com"}
					, new String[]{"vivek.kumar@bellurbis.com"}, "vivek.kumar@bellurbis.com", "test message"
					, multipart
					);
			/*sendEmail(new String[]{"vivek.kumar@bellurbis.com"}, new String[]{"vivek.kumar@bellurbis.com"}
					, new String[]{"vivek.kumar@bellurbis.com"}, "vivek.kumar@bellurbis.com", "test message"
					, "Test Body of message"
					);*/
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Enum class to define protocol constants.
	 * 
	 * @author Vivek Kumar.
	 *
	 */
	public enum Protocol{
		SMTP,
		SMTPS,
		TLS
	}
	
	public void sendEmaildoubt(Mailsender mailsender) throws AddressException, MessagingException
	{
		 String smtpServer = "smtp.gmail.com";
	      int port = 587;
	      final String userid = SMTP_AUTH_USER;//change accordingly
	      final String password = SMTP_AUTH_PASS;//change accordingly
	      String contentType = "text/html";
	      String subject = mailsender.getSubject();
	      String from = SMTP_AUTH_USER;
	      String to = mailsender.getMail();//some invalid address
	     // String bounceAddr = "toaddress@gmail.com";//change accordingly

	      Properties props = new Properties();

	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", smtpServer);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.transport.protocol", "smtp");
	    //  props.put("mail.smtp.from", bounceAddr);

	      Session mailSession = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(userid, password);
	            }
	         });

	      MimeMessage message = new MimeMessage(mailSession);
	      message.addFrom(InternetAddress.parse(from));
	      message.setRecipients(Message.RecipientType.TO, to);
	      message.setSubject(mailsender.getSubject());
	      message.setContent(mailsender.getBody(), contentType);

	      Transport transport = mailSession.getTransport();
	      try {
	         System.out.println("Sending ....");
	         transport.connect(smtpServer, port, userid, password);
	         transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	         System.out.println("Sending done ...");
	      } catch (Exception e) {
	         System.err.println("Error Sending: ");
	         e.printStackTrace();

	      }
		
		
	}
	
	
}





