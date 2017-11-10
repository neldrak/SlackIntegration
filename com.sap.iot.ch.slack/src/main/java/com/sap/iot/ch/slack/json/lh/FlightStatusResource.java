package com.sap.iot.ch.slack.json.lh;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightStatusResource {

	@JsonProperty("Flights")
	private List<Flight> flights;
	@JsonProperty("Meta")
	private Meta meta;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public FlightStatusResource() {
	}

	/**
	 * 
	 * @param flights
	 * @param meta
	 */
	public FlightStatusResource(List<Flight> flights, Meta meta) {
		super();
		this.flights = flights;
		this.meta = meta;
	}

	@JsonProperty("Flights")
	public List<Flight> getFlights() {
		return flights;
	}

	@JsonProperty("Flights")
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@JsonProperty("Meta")
	public Meta getMeta() {
		return meta;
	}

	@JsonProperty("Meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

}