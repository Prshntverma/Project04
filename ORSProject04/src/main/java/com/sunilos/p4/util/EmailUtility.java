package com.sunilos.p4.util;


import java.util.Properties;
import java.util.ResourceBundle;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import com.sunilos.p4.exception.ApplicationException;

/**
 * Email Utility provides Email Services
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 * 
 */

public class EmailUtility {

	/**
	 * Create Resource Bundle to read properties file
	 */
	static ResourceBundle rb = ResourceBundle
			.getBundle("com.sunilos.p4.bundle.system");

	/**
	 * Email Server
	 */
	private static final String SMTP_HOST_NAME = rb.getString("smtp.server");

	/**
	 * Email Server Port
	 */
	private static final String SMTP_PORT = rb.getString("smtp.port");

	/**
	 * Session Factory, A session is a connection to email server.
	 */
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	/**
	 * Administrator's email id by which all messages are sent
	 */
	private static final String emailFromAddress = rb.getString("email.login");

	/**
	 * Administrator email's password
	 */
	private static final String emailPassword = rb.getString("email.pwd");

	/**
	 * Email server properties
	 */

	private static Properties props = new Properties();

	/**
	 * Static block to initialize static parameters
	 */
	static {
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
	}

	/**
	 * Sends an Email
	 * 
	 * @param emailMessageDTO
	 *            : Email message
	 * @throws ApplicationException
	 */
	public static void sendMail(EmailMessage emailMessageDTO)
			throws ApplicationException {

		try {

			// Connection to Mail Server
			Session session = Session.getDefaultInstance(props,
					new jakarta.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(emailFromAddress,
									emailPassword);
						}
					});

			// Make debug mode true to display debug messages at console
			session.setDebug(true);

			// Create a message
			Message msg = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(emailFromAddress);
			msg.setFrom(addressFrom);

			// Set TO addresses
			String[] emailIds = new String[0];

			if (emailMessageDTO.getTo() != null) {
				emailIds = emailMessageDTO.getTo().split(",");
			}

			// Set CC addresses
			String[] emailIdsCc = new String[0];

			if (emailMessageDTO.getCc() != null) {
				emailIdsCc = emailMessageDTO.getCc().split(",");
			}

			// Set BCC addresses
			String[] emailIdsBcc = new String[0];

			if (emailMessageDTO.getBcc() != null) {
				emailIdsBcc = emailMessageDTO.getBcc().split(",");
			}

			InternetAddress[] addressTo = new InternetAddress[emailIds.length];

			for (int i = 0; i < emailIds.length; i++) {
				addressTo[i] = new InternetAddress(emailIds[i]);
			}

			InternetAddress[] addressCc = new InternetAddress[emailIdsCc.length];

			for (int i = 0; i < emailIdsCc.length; i++) {
				addressCc[i] = new InternetAddress(emailIdsCc[i]);
			}

			InternetAddress[] addressBcc = new InternetAddress[emailIdsBcc.length];

			for (int i = 0; i < emailIdsBcc.length; i++) {
				addressBcc[i] = new InternetAddress(emailIdsBcc[i]);
			}

			if (addressTo.length > 0) {
				msg.setRecipients(Message.RecipientType.TO, addressTo);
			}

			if (addressCc.length > 0) {
				msg.setRecipients(Message.RecipientType.CC, addressCc);
			}

			if (addressBcc.length > 0) {
				msg.setRecipients(Message.RecipientType.BCC, addressBcc);
			}

			// Setting the Subject and Content Type
			msg.setSubject(emailMessageDTO.getSubject());

			// Set message MIME type
			if (emailMessageDTO.getAttachment() != null && !emailMessageDTO.getAttachment().isEmpty()) {

				MimeMultipart multipart = new MimeMultipart();

				// Email body
				BodyPart messageBodyPart = new MimeBodyPart();

				if (emailMessageDTO.getMessageType() == EmailMessage.HTML_MSG) {
					messageBodyPart.setContent(emailMessageDTO.getMessage(), "text/html; charset=UTF-8");
				} else {
					messageBodyPart.setText(emailMessageDTO.getMessage());
				}

				multipart.addBodyPart(messageBodyPart);

				// Attachment
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(emailMessageDTO.getAttachment());

				multipart.addBodyPart(attachmentPart);

				msg.setContent(multipart);

			} else {

				// Normal mail without attachment
				if (emailMessageDTO.getMessageType() == EmailMessage.HTML_MSG) {

					msg.setContent(emailMessageDTO.getMessage(), "text/html; charset=UTF-8");

				} else {

					msg.setContent(emailMessageDTO.getMessage(), "text/plain; charset=UTF-8");
				}
			}



			// Send the mail
			Transport.send(msg);

		} catch (Exception ex) {
			throw new ApplicationException("Email " + ex.getMessage());
		}
	}
}
