package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

	@JsonProperty("type")
	private String type;
	@JsonProperty("x")
	private Double x;
	@JsonProperty("y")
	private Double y;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Coordinate() {
	}

	/**
	 * 
	 * @param type
	 * @param y
	 * @param x
	 */
	public Coordinate(String type, Double x, Double y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public Coordinate withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("x")
	public Double getX() {
		return x;
	}

	@JsonProperty("x")
	public void setX(Double x) {
		this.x = x;
	}

	public Coordinate withX(Double x) {
		this.x = x;
		return this;
	}

	@JsonProperty("y")
	public Double getY() {
		return y;
	}

	@JsonProperty("y")
	public void setY(Double y) {
		this.y = y;
	}

	public Coordinate withY(Double y) {
		this.y = y;
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

	public Coordinate withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}