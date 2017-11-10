package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Arrival {

	@JsonProperty("AirportCode")
	private String airportCode;
	@JsonProperty("ScheduledTime")
	private ScheduledTime scheduledTimeLocal;
	@JsonProperty("ScheduledTimeUTC")
	private ScheduledTime scheduledTimeUTC;
	@JsonProperty("TimeStatus")
	private TimeStatus timeStatus;
	@JsonProperty("Terminal")
	private Terminal terminal;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Arrival() {
	}

	/**
	 * 
	 * @param scheduledTimeUTC
	 * @param terminal
	 * @param airportCode
	 * @param scheduledTimeLocal
	 * @param timeStatus
	 */
	public Arrival(String airportCode, ScheduledTime scheduledTimeLocal, ScheduledTime scheduledTimeUTC,
			TimeStatus timeStatus, Terminal terminal) {
		super();
		this.airportCode = airportCode;
		this.scheduledTimeLocal = scheduledTimeLocal;
		this.scheduledTimeUTC = scheduledTimeUTC;
		this.timeStatus = timeStatus;
		this.terminal = terminal;
	}

	@JsonProperty("AirportCode")
	public String getAirportCode() {
		return airportCode;
	}

	@JsonProperty("AirportCode")
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	@JsonProperty("ScheduledTime")
	public ScheduledTime getScheduledTimeLocal() {
		return scheduledTimeLocal;
	}

	@JsonProperty("ScheduledTime")
	public void setScheduledTimeLocal(ScheduledTime scheduledTimeLocal) {
		this.scheduledTimeLocal = scheduledTimeLocal;
	}

	@JsonProperty("ScheduledTimeUTC")
	public ScheduledTime getScheduledTimeUTC() {
		return scheduledTimeUTC;
	}

	@JsonProperty("ScheduledTimeUTC")
	public void setScheduledTimeUTC(ScheduledTime scheduledTimeUTC) {
		this.scheduledTimeUTC = scheduledTimeUTC;
	}

	@JsonProperty("TimeStatus")
	public TimeStatus getTimeStatus() {
		return timeStatus;
	}

	@JsonProperty("TimeStatus")
	public void setTimeStatus(TimeStatus timeStatus) {
		this.timeStatus = timeStatus;
	}

	@JsonProperty("Terminal")
	public Terminal getTerminal() {
		return terminal;
	}

	@JsonProperty("Terminal")
	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

}