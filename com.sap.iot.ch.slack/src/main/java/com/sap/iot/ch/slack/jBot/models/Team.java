package com.sap.iot.ch.slack.jBot.models;

public class Team {
	private String id;
	private String domain;

	public Team() {
	}

	public Team(String id, String domain) {
		super();
		this.id = id;
		this.domain = domain;
	}

	public String getId() {
		return id;
	}

	public String getDomain() {
		return domain;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
