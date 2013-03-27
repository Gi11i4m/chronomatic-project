package be.artesis.timelog.externAuth;

public class Github extends SocialMedia {
	
public Github() {}
	
	private final String clientID = "7d14d8b4c0e94b8afef3";
	private final String clientSecret = "533f1182ea7f8a2f7a0109053adbb9ed515a8718";
	private final String redirectUrl = "";
	private final String scope = "user:email";
	private final String responseType = "";
	private final String authorizeTokenUrl = "https://github.com/login/oauth/authorize?";
	private final String accesTokenUrl = "https://github.com/login/oauth/access_token";
	private final String userInfoUrl = "https://api.github.com/user?";
	
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
