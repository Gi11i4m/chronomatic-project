package be.artesis.timelog.externAuth;

public abstract class SocialMedia {
	public abstract String getClientID();
	public abstract String getClientSecret();
	public abstract String getRedirectUri();
	public abstract String getScope();
	public abstract String getResponseType();
}
