package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	public void sendEmail(String toEmail, String subject, String body, String attachmentPath) {

		// Recipient's email ID needs to be mentioned.
		String to = toEmail;

		// Sender's email ID needs to be mentioned
		String from = "automationciam@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = new Properties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("automationciam@gmail.com", "louj qxtd bcap yjcb");

			}

		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(attachmentPath);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void sendEmailWithAttachment(String toEmail,String Subject,String message,String filePath) {

		// Recipient's email ID needs to be mentioned.
		String to = toEmail;

		// Sender's email ID needs to be mentioned
		String from = "automationciam@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = new Properties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("automationciam@gmail.com", "louj qxtd bcap yjcb");

			}

		});

		try {
			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);

			// Set From: header field of the header.
			msg.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			msg.setSubject(Subject);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source=new FileDataSource(filePath);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName("AutomationReport.pdf");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);
			msg.setContent(multipart);
			Transport.send(msg);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
