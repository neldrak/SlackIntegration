package com.sap.iot.ch.slack.jBot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ramswaroop on 12/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
	private String title;
	private String value;
	@JsonProperty("short")
	private boolean shortEnough;

	public Field() {
		super();
	}

	public Field(String title, String value, boolean shortEnough) {
		super();
		this.title = title;
		this.value = value;
		this.shortEnough = shortEnough;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isShortEnough() {
		return shortEnough;
	}

	public void setShortEnough(boolean shortEnough) {
		this.shortEnough = shortEnough;
	}
}
