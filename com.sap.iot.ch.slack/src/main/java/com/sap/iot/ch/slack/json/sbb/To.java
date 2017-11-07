package com.sap.iot.ch.slack.json.sbb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class To {

	@JsonProperty("arrival")
	private String arrival;
	@JsonProperty("arrivalTimestamp")
	private Integer arrivalTimestamp;
	@JsonProperty("departure")
	private Object departure;
	@JsonProperty("departureTimestamp")
	private Object departureTimestamp;
	@JsonProperty("platform")
	private String platform;
	@JsonProperty("prognosis")
	private Prognosis prognosis;
	@JsonProperty("station")
	private Station station;

	@JsonProperty("arrival")
	public String getArrival() {
		return arrival;
	}

	@JsonProperty("arrival")
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	@JsonProperty("arrivalTimestamp")
	public Integer getArrivalTimestamp() {
		return arrivalTimestamp;
	}

	@JsonProperty("arrivalTimestamp")
	public void setArrivalTimestamp(Integer arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}

	@JsonProperty("departure")
	public Object getDeparture() {
		return departure;
	}

	@JsonProperty("departure")
	public void setDeparture(Object departure) {
		this.departure = departure;
	}

	@JsonProperty("departureTimestamp")
	public Object getDepartureTimestamp() {
		return departureTimestamp;
	}

	@JsonProperty("departureTimestamp")
	public void setDepartureTimestamp(Object departureTimestamp) {
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