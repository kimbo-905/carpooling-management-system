//package com.example.javafxtp.Repository;
//
//import javafx.scene.control.Alert;
//
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class MailUtil {
//
//	public static void sendEmail(String recipientEmail) {
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.port", "587");
//
//		String email = "khadimkbe@gmail.com";
//		String password = "v t m x n z b k e n o j i l q h";
//		String theMessage = "Vous avez fait une reservation";
//
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(email, password);
//			}
//		});
//
//		try {
//			Message message = prepareMessage(session, email, recipientEmail, theMessage);
//			if (message != null) {
//				Transport.send(message);
//				new Alert(Alert.AlertType.INFORMATION, "Sent successfully").show();
//			} else {
//				new Alert(Alert.AlertType.ERROR, "Failed to prepare message. Please try again").show();
//			}
//		} catch (MessagingException e) {
//			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
//			new Alert(Alert.AlertType.ERROR, "Failed to send email. Please try again").show();
//		}
//	}
//
//	private static Message prepareMessage(Session session, String email, String recipientEmail, String msg) {
//		try {
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//			message.setSubject("Messages");
//			message.setText(msg);
//			return message;
//		} catch (MessagingException e) {
//			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
//		}
//		return null;
//	}
//}
