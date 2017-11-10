package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("token_type")
	private String accessType;

	@JsonProperty("expires_in")
	private long expiresIn;

	public TokenResponse() {

	}

	public TokenResponse(String accessToken, String accessType, long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.accessType = accessType;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
