package chronomatic.email;

public class MailNewUser extends Mailer {

	public MailNewUser(String username, String email, String hash) {
		super(email);
		
		this.hash = hash;
		this.username = username;
		
		messageContent = ("Dear Chronomatic user. Thanks for signing up! " +
				"\n\nYour account has been created, " +
				"you can login with the following credentials after you have activated your account by pressing the url below." +
				"\n\n------------------" +
				"\nUsername: " + username +
				"\n\nPassword: ***********" +
				"\n------------------" +
				"\n\n Please click this link to activate your account:" +
				"\n\n " + host + "email/verificatie/" + email + "/" + hash +
				"\n\n Kind regards, the Chronomatic team");
	}

	

}
