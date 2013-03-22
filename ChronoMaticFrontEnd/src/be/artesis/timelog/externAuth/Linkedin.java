package be.artesis.timelog.externAuth;

public class Linkedin extends SocialMedia {
	
	public Linkedin() {}
	
	private final String clientID = "ug9otjgvfn3c";
	private final String clientSecret = "oxJcsAhL1NQTjQ8U";
	private final String redirectUrl = "http://www.linkedin.com/chronomatic";
	private final String scope = "r_emailaddress";
	private final String responseType = "code";
	private final String authorizeTokenUrl = "https://www.linkedin.com/uas/oauth2/authorization?";
	private final String accesTokenUrl = "https://www.linkedin.com/uas/oauth2/accessToken?";
	private final String userInfoUrl = "https://api.linkedin.com/v1/people/~/email-address?oauth2_";
	private final String state = "DCEEFWF45453sdffef424";
	
	public String getState() {
		return state;
	}
	
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
		return "linkedin";
	}
}
