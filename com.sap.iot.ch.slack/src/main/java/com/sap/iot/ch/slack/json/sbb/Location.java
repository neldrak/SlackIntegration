package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private Object name;
	@JsonProperty("score")
	private Object score;
	@JsonProperty("coordinate")
	private Coordinate coordinate;
	@JsonProperty("distance")
	private Object distance;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Location() {
	}

	/**
	 * 
	 * @param id
	 * @param distance
	 * @param coordinate
	 * @param name
	 * @param score
	 */
	public Location(String id, Object name, Object score, Coordinate coordinate, Object distance) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.coordinate = coordinate;
		this.distance = distance;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public Location withId(String id) {
		this.id = id;
		return this;
	}

	@JsonProperty("name")
	public Object getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(Object name) {
		this.name = name;
	}

	public Location withName(Object name) {
		this.name = name;
		return this;
	}

	@JsonProperty("score")
	public Object getScore() {
		return score;
	}

	@JsonProperty("score")
	public void setScore(Object score) {
		this.score = score;
	}

	public Location withScore(Object score) {
		this.score = score;
		return this;
	}

	@JsonProperty("coordinate")
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@JsonProperty("coordinate")
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Location withCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
		return this;
	}

	@JsonProperty("distance")
	public Object getDistance() {
		return distance;
	}

	@JsonProperty("distance")
	public void setDistance(Object distance) {
		this.distance = distance;
	}

	public Location withDistance(Object distance) {
		this.distance = distance;
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

	public Location withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}