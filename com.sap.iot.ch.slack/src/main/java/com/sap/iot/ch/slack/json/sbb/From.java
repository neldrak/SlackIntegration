package com.sap.iot.ch.slack.json.sbb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class From {

	@JsonProperty("arrival")
	private Object arrival;
	@JsonProperty("arrivalTimestamp")
	private Object arrivalTimestamp;
	@JsonProperty("departure")
	private String departure;
	@JsonProperty("departureTimestamp")
	private Integer departureTimestamp;
	@JsonProperty("platform")
	private String platform;
	@JsonProperty("prognosis")
	private Prognosis prognosis;
	@JsonProperty("station")
	private Station station;

	@JsonProperty("arrival")
	public Object getArrival() {
		return arrival;
	}

	@JsonProperty("arrival")
	public void setArrival(Object arrival) {
		this.arrival = arrival;
	}

	@JsonProperty("arrivalTimestamp")
	public Object getArrivalTimestamp() {
		return arrivalTimestamp;
	}

	@JsonProperty("arrivalTimestamp")
	public void setArrivalTimestamp(Object arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}

	@JsonProperty("departure")
	public String getDeparture() {
		return departure;
	}

	@JsonProperty("departure")
	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@JsonProperty("departureTimestamp")
	public Integer getDepartureTimestamp() {
		return departureTimestamp;
	}

	@JsonProperty("departureTimestamp")
	public void setDepartureTimestamp(Integer departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
	}

	@JsonProperty("platform")
	public String getPlatform() {
		if(platform==null)
			return "n/a";
		return platform;
	}

	@JsonProperty("platform")
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@JsonProperty("prognosis")
	public Prognosis getPrognosis() {
		return prognosis;
	}

	@JsonProperty("prognosis")
	public void setPrognosis(Prognosis prognosis) {
		this.prognosis = prognosis;
	}

	@JsonProperty("station")
	public Station getStation() {
		return station;
	}

	@JsonProperty("station")
	public void setStation(Station station) {
		this.station = station;
	}

}