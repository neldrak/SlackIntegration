package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduledTime {

	@JsonProperty("DateTime")
	private String dateTime;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ScheduledTime() {
	}

	/**
	 * 
	 * @param dateTime
	 */
	public ScheduledTime(String dateTime) {
		super();
		this.dateTime = dateTime;
	}

	@JsonProperty("DateTime")
	public String getDateTime() {
		return dateTime;
	}

	@JsonProperty("DateTime")
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}