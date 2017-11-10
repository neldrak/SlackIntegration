package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flight {

	@JsonProperty("Departure")
	private Departure departure;
	@JsonProperty("Arrival")
	private Arrival arrival;
	@JsonProperty("Carrier")
	private Carrier marketingCarrier;
	@JsonProperty("OperatingCarrier")
	private Carrier operatingCarrier;
	@JsonProperty("Equipment")
	private Equipment equipment;
	@JsonProperty("FlightStatus")
	private FlightStatus flightStatus;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Flight() {
	}

	/**
	 * 
	 * @param operatingCarrier
	 * @param marketingCarrier
	 * @param arrival
	 * @param departure
	 * @param equipment
	 * @param flightStatus
	 */
	public Flight(Departure departure, Arrival arrival, Carrier marketingCarrier,
			Carrier operatingCarrier, Equipment equipment, FlightStatus flightStatus) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.marketingCarrier = marketingCarrier;
		this.operatingCarrier = operatingCarrier;
		this.equipment = equipment;
		this.flightStatus = flightStatus;
	}

	@JsonProperty("Departure")
	public Departure getDeparture() {
		return departure;
	}

	@JsonProperty("Departure")
	public void setDeparture(Departure departure) {
		this.departure = departure;
	}

	@JsonProperty("Arrival")
	public Arrival getArrival() {
		return arrival;
	}

	@JsonProperty("Arrival")
	public void setArrival(Arrival arrival) {
		this.arrival = arrival;
	}

	@JsonProperty("Carrier")
	public Carrier getMarketingCarrier() {
		return marketingCarrier;
	}

	@JsonProperty("Carrier")
	public void setMarketingCarrier(Carrier marketingCarrier) {
		this.marketingCarrier = marketingCarrier;
	}

	@JsonProperty("OperatingCarrier")
	public Carrier getOperatingCarrier() {
		return operatingCarrier;
	}

	@JsonProperty("OperatingCarrier")
	public void setOperatingCarrier(Carrier operatingCarrier) {
		this.operatingCarrier = operatingCarrier;
	}

	@JsonProperty("Equipment")
	public Equipment getEquipment() {
		return equipment;
	}

	@JsonProperty("Equipment")
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@JsonProperty("FlightStatus")
	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	@JsonProperty("FlightStatus")
	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

}