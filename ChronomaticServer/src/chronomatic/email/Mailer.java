package chronomatic.email;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/


public class Mailer {

	public Mailer() {
		
	}
	
	public static void sendMail(String username, String email, String hash) {
		
		final String host = "http://localhost:8080/ChronomaticServer/";
		final String SMTPusername = "dvlsanit";
		final String SMTPpassword = "bZDtNd7f";
 
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
			message.setText("Dear Chronomatic user. Thanks for signing up! " +
					"\n\nYour account has been created, " +
					"you can login with the following credentials after tou have activated your account by pressing the url below." +
					"\n\n------------------" +
					"\nUsername: " + username +
					"\n\nPassword: ***********" +
					"\n------------------" +
					"\n\n Please click this link to activate your account:" +
					"\n\n " + host + "email/verificatie/" + email + "/" + hash +
					"\n\n Kind regards, the Chronomatic team");
 
			Transport.send(message);
 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}



