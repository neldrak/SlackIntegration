package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionResponse {
	@JsonProperty("FlightStatusResource")
	private FlightStatusResource flightStatusResource;

	/**
	* No args constructor for use in serialization
	* 
	*/
	public ConnectionResponse() {
	}

	/**
	* 
	* @param flightStatusResource
	*/
	public ConnectionResponse(FlightStatusResource flightStatusResource) {
	super();
	this.flightStatusResource = flightStatusResource;
	}

	@JsonProperty("FlightStatusResource")
	public FlightStatusResource getFlightStatusResource() {
		return flightStatusResource;
	}

	@JsonProperty("FlightStatusResource")
	public void setFlightStatusResource(FlightStatusResource flightStatusResource) {
		this.flightStatusResource = flightStatusResource;
	}
}
