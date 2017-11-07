package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Prognosis {

	@JsonProperty("platform")
	private Object platform;
	@JsonProperty("arrival")
	private Object arrival;
	@JsonProperty("departure")
	private Object departure;
	@JsonProperty("capacity1st")
	private Object capacity1st;
	@JsonProperty("capacity2nd")
	private Object capacity2nd;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Prognosis() {
	}

	/**
	 * 
	 * @param platform
	 * @param capacity2nd
	 * @param departure
	 * @param arrival
	 * @param capacity1st
	 */
	public Prognosis(Object platform, Object arrival, Object departure, Object capacity1st, Object capacity2nd) {
		super();
		this.platform = platform;
		this.arrival = arrival;
		this.departure = departure;
		this.capacity1st = capacity1st;
		this.capacity2nd = capacity2nd;
	}

	@JsonProperty("platform")
	public Object getPlatform() {
		return platform;
	}

	@JsonProperty("platform")
	public void setPlatform(Object platform) {
		this.platform = platform;
	}

	public Prognosis withPlatform(Object platform) {
		this.platform = platform;
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

	public Prognosis withArrival(Object arrival) {
		this.arrival = arrival;
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

	public Prognosis withDeparture(Object departure) {
		this.departure = departure;
		return this;
	}

	@JsonProperty("capacity1st")
	public Object getCapacity1st() {
		return capacity1st;
	}

	@JsonProperty("capacity1st")
	public void setCapacity1st(Object capacity1st) {
		this.capacity1st = capacity1st;
	}

	public Prognosis withCapacity1st(Object capacity1st) {
		this.capacity1st = capacity1st;
		return this;
	}

	@JsonProperty("capacity2nd")
	public Object getCapacity2nd() {
		return capacity2nd;
	}

	@JsonProperty("capacity2nd")
	public void setCapacity2nd(Object capacity2nd) {
		this.capacity2nd = capacity2nd;
	}

	public Prognosis withCapacity2nd(Object capacity2nd) {
		this.capacity2nd = capacity2nd;
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

	public Prognosis withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}