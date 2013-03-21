package be.artesis.timelog.externAuth;

public class Google extends SocialMedia {

	public Google() {}
	
	private final String clientID = "131195431047.apps.googleusercontent.com";
	private final String clientSecret = "N2CHWpyn35OdOE1KqU_lXNx6";
	private final String redirectUrl = "https://www.google.be/oauth2callback";
	private final String scope = "https://www.googleapis.com/auth/userinfo.email";
	private final String responseType = "code";
	private final String authorizeTokenUrl = "https://accounts.google.com/o/oauth2/auth?";
	private final String accesTokenUrl = "https://accounts.google.com/o/oauth2/token";
	private final String userInfoUrl = "https://www.googleapis.com/oauth2/v1/userinfo?";
	
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
		return "google";
	}
}
