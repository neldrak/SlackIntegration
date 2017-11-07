package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PassList {

	@JsonProperty("station")
	private Station station;
	@JsonProperty("arrival")
	private String arrival;
	@JsonProperty("arrivalTimestamp")
	private Integer arrivalTimestamp;
	@JsonProperty("departure")
	private Object departure;
	@JsonProperty("departureTimestamp")
	private Object departureTimestamp;
	@JsonProperty("delay")
	private Object delay;
	@JsonProperty("platform")
	private Object platform;
	@JsonProperty("prognosis")
	private Prognosis prognosis;
	@JsonProperty("realtimeAvailability")
	private Object realtimeAvailability;
	@JsonProperty("location")
	private Location location;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public PassList() {
	}

	/**
	 * 
	 * @param platform
	 * @param arrivalTimestamp
	 * @param departureTimestamp
	 * @param location
	 * @param station
	 * @param realtimeAvailability
	 * @param departure
	 * @param arrival
	 * @param delay
	 * @param prognosis
	 */
	public PassList(Station station, String arrival, Integer arrivalTimestamp, Object departure,
			Object departureTimestamp, Object delay, Object platform, Prognosis prognosis,
			Object realtimeAvailability, Location location) {
		super();
		this.station = station;
		this.arrival = arrival;
		this.arrivalTimestamp = arrivalTimestamp;
		this.departure = departure;
		this.departureTimestamp = departureTimestamp;
		this.delay = delay;
		this.platform = platform;
		this.prognosis = prognosis;
		this.realtimeAvailability = realtimeAvailability;
		this.location = location;
	}

	@JsonProperty("station")
	public Station getStation() {
		return station;
	}

	@JsonProperty("station")
	public void setStation(Station station) {
		this.station = station;
	}

	public PassList withStation(Station station) {
		this.station = station;
		return this;
	}

	@JsonProperty("arrival")
	public String getArrival() {
		return arrival;
	}

	@JsonProperty("arrival")
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public PassList withArrival(String arrival) {
		this.arrival = arrival;
		return this;
	}

	@JsonProperty("arrivalTimestamp")
	public Integer getArrivalTimestamp() {
		return arrivalTimestamp;
	}

	@JsonProperty("arrivalTimestamp")
	public void setArrivalTimestamp(Integer arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}

	public PassList withArrivalTimestamp(Integer arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
		return this;
	}

	@JsonProperty("departure")
	public Object getDeparture() {
		return departure;
	}

	@JsonProperty("departure")
	public void setDeparture(Object departure) {
		this.departure = departure;
	}

	public PassList withDeparture(Object departure) {
		this.departure = departure;
		return this;
	}

	@JsonProperty("departureTimestamp")
	public Object getDepartureTimestamp() {
		return departureTimestamp;
	}

	@JsonProperty("departureTimestamp")
	public void setDepartureTimestamp(Object departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
	}

	public PassList withDepartureTimestamp(Object departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
		return this;
	}

	@JsonProperty("delay")
	public Object getDelay() {
		return delay;
	}

	@JsonProperty("delay")
	public void setDelay(Object delay) {
		this.delay = delay;
	}

	public PassList withDelay(Object delay) {
		this.delay = delay;
		return this;
	}

	@JsonProperty("platform")
	public Object getPlatform() {
		return platform;
	}

	@JsonProperty("platform")
	public void setPlatform(Object platform) {
		this.platform = platform;
	}

	public PassList withPlatform(Object platform) {
		this.platform = platform;
		return this;
	}

	@JsonProperty("prognosis")
	public Prognosis getPrognosis() {
		return prognosis;
	}

	@JsonProperty("prognosis")
	public void setPrognosis(Prognosis prognosis) {
		this.prognosis = prognosis;
	}

	public PassList withPrognosis(Prognosis prognosis) {
		this.prognosis = prognosis;
		return this;
	}

	@JsonProperty("realtimeAvailability")
	public Object getRealtimeAvailability() {
		return realtimeAvailability;
	}

	@JsonProperty("realtimeAvailability")
	public void setRealtimeAvailability(Object realtimeAvailability) {
		this.realtimeAvailability = realtimeAvailability;
	}

	public PassList withRealtimeAvailability(Object realtimeAvailability) {
		this.realtimeAvailability = realtimeAvailability;
		return this;
	}

	@JsonProperty("location")
	public Location getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(Location location) {
		this.location = location;
	}

	public PassList withLocation(Location location) {
		this.location = location;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public PassList withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}