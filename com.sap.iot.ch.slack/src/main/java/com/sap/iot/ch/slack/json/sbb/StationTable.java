package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class StationTable {

	@JsonProperty("station")
	private Station station;
	@JsonProperty("stationboard")
	private List<Stationboard> stationboard = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public StationTable() {
	}

	/**
	 * 
	 * @param station
	 * @param stationboard
	 */
	public StationTable(Station station, List<Stationboard> stationboard) {
		super();
		this.station = station;
		this.stationboard = stationboard;
	}

	@JsonProperty("station")
	public Station getStation() {
		return station;
	}

	@JsonProperty("station")
	public void setStation(Station station) {
		this.station = station;
	}

	public StationTable withStation(Station station) {
		this.station = station;
		return this;
	}

	@JsonProperty("stationboard")
	public List<Stationboard> getStationboard() {
		return stationboard;
	}

	@JsonProperty("stationboard")
	public void setStationboard(List<Stationboard> stationboard) {
		this.stationboard = stationboard;
	}

	public StationTable withStationboard(List<Stationboard> stationboard) {
		this.stationboard = stationboard;
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

	public StationTable withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}