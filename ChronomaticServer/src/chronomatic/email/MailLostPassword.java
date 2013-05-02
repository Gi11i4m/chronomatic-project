package chronomatic.email;

public class MailLostPassword extends Mailer {

	public MailLostPassword(String email, String password) {
		super(email);
		
		messageContent = ("This is an automatic message from the Chronomatic registration system" +
				"\n\nYour new password has been reset to:" +
				"\n\n------------------" +
				"\nPassword: " + password + "" +
				"\n------------------" +
				"\n\n Kind regards, the Chronomatic team");
	}
}
