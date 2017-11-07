package com.sap.iot.ch.slack.json.sbb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stations {
	

	@JsonProperty("stations")
	private List<Station> stations;
	
	public Stations() {
		super();
	}


	public Stations(List<Station> stations) {
		super();
		this.stations = stations;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	
}
