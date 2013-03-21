package be.artesis.timelog.externAuth;

public abstract class SocialMedia {
	public abstract String getClientID();
	public abstract String getClientSecret();
	public abstract String getRedirectUrl();
	public abstract String getScope();
	public abstract String getResponseType();
	public abstract String getAuthorizeTokenUrl();
	public abstract String getAccessTokenUrl();
	public abstract String getUserInfoUrl();
	
	
}
