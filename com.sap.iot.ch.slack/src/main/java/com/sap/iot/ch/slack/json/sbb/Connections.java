package com.sap.iot.ch.slack.json.sbb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Connections {

	@JsonProperty("connections")
	private List<Connection> connections = null;

	@JsonProperty("connections")
	public List<Connection> getConnections() {
		return connections;
	}

	@JsonProperty("connections")
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

}