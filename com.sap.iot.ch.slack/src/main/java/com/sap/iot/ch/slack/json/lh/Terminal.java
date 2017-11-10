package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Terminal {

	@JsonProperty("Name")
	private Integer name;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Terminal() {
	}

	/**
	 * 
	 * @param name
	 */
	public Terminal(Integer name) {
		super();
		this.name = name;
	}

	@JsonProperty("Name")
	public Integer getName() {
		return name;
	}

	@JsonProperty("Name")
	public void setName(Integer name) {
		this.name = name;
	}

}