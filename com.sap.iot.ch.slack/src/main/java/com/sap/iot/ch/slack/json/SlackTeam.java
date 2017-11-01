package com.sap.iot.ch.slack.json;

public class SlackTeam {

	private String id;
	private String domain;

	public SlackTeam() {
	}

	public SlackTeam(String id, String domain) {
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
