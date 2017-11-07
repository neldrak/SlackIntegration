package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stop {

	@JsonProperty("station")
	private Station station;
	@JsonProperty("arrival")
	private Object arrival;
	@JsonProperty("arrivalTimestamp")
	private Object arrivalTimestamp;
	@JsonProperty("departure")
	private String departure;
	@JsonProperty("departureTimestamp")
	private Integer departureTimestamp;
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
	public Stop() {
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
	public Stop(Station station, Object arrival, Object arrivalTimestamp, String departure,
			Integer departureTimestamp, Object delay, Object platform, Prognosis prognosis,
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

	public Stop withStation(Station station) {
		this.station = station;
		return this;
	}

	@JsonProperty("arrival")
	public Object getArrival() {
		return arrival;
	}

	@JsonProperty("arrival")
	public void setArrival(Object arrival) {
		this.arrival = arrival;
	}

	public Stop withArrival(Object arrival) {
		this.arrival = arrival;
		return this;
	}

	@JsonProperty("arrivalTimestamp")
	public Object getArrivalTimestamp() {
		return arrivalTimestamp;
	}

	@JsonProperty("arrivalTimestamp")
	public void setArrivalTimestamp(Object arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}

	public Stop withArrivalTimestamp(Object arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
		return this;
	}

	@JsonProperty("departure")
	public String getDeparture() {
		return departure;
	}

	@JsonProperty("departure")
	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public Stop withDeparture(String departure) {
		this.departure = departure;
		return this;
	}

	@JsonProperty("departureTimestamp")
	public Integer getDepartureTimestamp() {
		return departureTimestamp;
	}

	@JsonProperty("departureTimestamp")
	public void setDepartureTimestamp(Integer departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
	}

	public Stop withDepartureTimestamp(Integer departureTimestamp) {
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

	public Stop withDelay(Object delay) {
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

	public Stop withPlatform(Object platform) {
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

	public Stop withPrognosis(Prognosis prognosis) {
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

	public Stop withRealtimeAvailability(Object realtimeAvailability) {
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

	public Stop withLocation(Location location) {
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

	public Stop withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}