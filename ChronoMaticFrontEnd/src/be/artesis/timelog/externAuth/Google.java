package be.artesis.timelog.externAuth;

public class Google extends SocialMedia {

	public Google() {}
	
	private String clientID = "131195431047.apps.googleusercontent.com";
	private String clientSecret = "N2CHWpyn35OdOE1KqU_lXNx6";
	private String redirectUri = "https://www.google.be/oauth2callback";
	private String scope = "https://www.googleapis.com/auth/userinfo.email";
	private String responseType = "code";
	
	public String getClientID() {
		return clientID;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public String getScope() {
		return scope;
	}

	public String getResponseType() {
		return responseType;
	}
}
