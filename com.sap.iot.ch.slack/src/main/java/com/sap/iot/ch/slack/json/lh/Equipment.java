package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipment {

	@JsonProperty("AircraftCode")
	private Integer aircraftCode;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Equipment() {
	}

	/**
	 * 
	 * @param aircraftCode
	 */
	public Equipment(Integer aircraftCode) {
		super();
		this.aircraftCode = aircraftCode;
	}

	@JsonProperty("AircraftCode")
	public Integer getAircraftCode() {
		return aircraftCode;
	}

	@JsonProperty("AircraftCode")
	public void setAircraftCode(Integer aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

}