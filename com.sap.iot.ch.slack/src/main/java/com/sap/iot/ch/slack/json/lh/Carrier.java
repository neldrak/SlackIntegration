package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Carrier {

	@JsonProperty("AirlineID")
	private String airlineID;
	@JsonProperty("FlightNumber")
	private Integer flightNumber;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Carrier() {
	}

	/**
	 * 
	 * @param airlineID
	 * @param flightNumber
	 */
	public Carrier(String airlineID, Integer flightNumber) {
		super();
		this.airlineID = airlineID;
		this.flightNumber = flightNumber;
	}

	@JsonProperty("AirlineID")
	public String getAirlineID() {
		return airlineID;
	}

	@JsonProperty("AirlineID")
	public void setAirlineID(String airlineID) {
		this.airlineID = airlineID;
	}

	@JsonProperty("FlightNumber")
	public Integer getFlightNumber() {
		return flightNumber;
	}

	@JsonProperty("FlightNumber")
	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

}