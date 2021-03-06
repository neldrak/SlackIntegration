package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightStatus {

	@JsonProperty("Code")
	private String code;
	@JsonProperty("Definition")
	private String definition;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public FlightStatus() {
	}

	/**
	 * 
	 * @param definition
	 * @param code
	 */
	public FlightStatus(String code, String definition) {
		super();
		this.code = code;
		this.definition = definition;
	}

	@JsonProperty("Code")
	public String getCode() {
		return code;
	}

	@JsonProperty("Code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("Definition")
	public String getDefinition() {
		return definition;
	}

	@JsonProperty("Definition")
	public void setDefinition(String definition) {
		this.definition = definition;
	}

}