package chronomatic.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	
	String messageContent;
	String username;
	String email;
	String hash;
	final String host = "http://localhost:8080/ChronomaticServer/";
	//final String host = "http://chronomatic-artesisti.rhcloud.com/ChronomaticServer/";
	final String SMTPusername = "dvlsanit";
	final String SMTPpassword = "bZDtNd7f";
	
	public Mailer(String email) {
		this.email = email;
	}
	
	public void setMessageContent(String message) {
		this.messageContent = message;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	
	public void sendMail() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.dvl-sanitair.be"); // Dit is de mail server van mijn hosting
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SMTPusername,SMTPpassword);
				}
			});
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@chronomatic.be"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email)); // email to
			message.setSubject("Chronomatic - Nieuwe gebruiker");
			message.setText(messageContent);
 
			Transport.send(message);
 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}



