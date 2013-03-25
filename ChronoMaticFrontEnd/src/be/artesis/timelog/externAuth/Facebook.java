package be.artesis.timelog.externAuth;

public class Facebook extends SocialMedia {

	public Facebook() {}
	
	private final String clientID = "346106655506499";
	private final String clientSecret = "N2CHWpyn35OdOE1KqU_lXNx6";
	private final String redirectUrl = "https://www.facebook.com/connect/login_success.html";
	private final String scope = "user_about_me";
	private final String responseType = "token";
	private final String authorizeTokenUrl = "https://www.facebook.com/dialog/oauth?";
	private final String accesTokenUrl = "";
	private final String userInfoUrl = "https://graph.facebook.com/me?fields=first_name,last_name,email&";
	
	public String getClientID() {
		return clientID;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getScope() {
		return scope;
	}

	public String getResponseType() {
		return responseType;
	}

	public String getAuthorizeTokenUrl() {
		return authorizeTokenUrl;
	}

	public String getAccessTokenUrl() {
		return accesTokenUrl;
	}

	public String getUserInfoUrl() {
		return userInfoUrl;
	}
	
	public String toString() {
		return "facebook";
	}
}
