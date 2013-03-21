package be.artesis.timelog.externAuth;

public class Twitter extends SocialMedia {

	private final String clientID = "Xj4mD97cHYAa5TRgDc37g";
	private final String clientSecret = "6CeWGfo99pJoWTKITbJsMWDiLJRmyErh1C9w4CYi2E";
	private final String redirectUrl = "http://www.twitter.com/#";
	private final String scope = "https://www.googleapis.com/auth/userinfo.email";
	private final String responseType = "code";
	private final String authorizeTokenUrl = "https://api.twitter.com/oauth/authorize";
	private final String accesTokenUrl = "https://api.twitter.com/oauth/request_token";
	private final String userInfoUrl = "https://api.twitter.com/oauth/";
	
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
		return "twitter";
	}
}
