package be.artesis.timelog.externAuth;

public class Microsoft extends SocialMedia {

	public Microsoft() {}
	
	private String clientID = "00000000440E9C2C";
	private String clientSecret = "jVoaFby0cKdmnTkm-CaVon6NTa85Ib1u";
	private String redirectUrl = "http://www.dvl-sanitair.be/";
	private String scope = "wl.emails";
	private String responseType = "code";
	private String authorizeTokenUrl = "https://login.live.com/oauth20_authorize.srf?";
	private String accesTokenUrl = "https://login.live.com/oauth20_token.srf?";
	private String userInfoUrl = "https://apis.live.net/v5.0/me/contacts?";
	
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
}
