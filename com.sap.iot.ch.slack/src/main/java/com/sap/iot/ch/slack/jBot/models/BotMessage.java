package com.sap.iot.ch.slack.jBot.models;

public class BotMessage extends RichMessage {

	private String user;
	private String token;
	private String channel;
	private boolean as_user;
	
	public BotMessage() {
		super();
	}
	public BotMessage(String text) {
		super(text);
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public boolean isAs_user() {
		return as_user;
	}
	public void setAs_user(boolean as_user) {
		this.as_user = as_user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
