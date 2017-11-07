package com.sap.iot.ch.slack.jBot.models;

public class Option {
	private String text;
	private String value;

	public Option() {

	}

	public Option(String text, String value) {
		super();
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
