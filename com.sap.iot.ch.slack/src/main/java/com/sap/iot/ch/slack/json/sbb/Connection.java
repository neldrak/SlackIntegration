package com.sap.iot.ch.slack.json.sbb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Connection {

	@JsonProperty("from")
	private From from;

	@JsonProperty("to")
	private To to;

	@JsonProperty("duration")
	private String duration;

	@JsonProperty("transfers")
	private int transfers;

	@JsonProperty("from")
	public From getFrom() {
		return from;
	}

	@JsonProperty("from")
	public void setFrom(From from) {
		this.from = from;
	}

	@JsonProperty("to")
	public To getTo() {
		return to;
	}

	@JsonProperty("to")
	public void setTo(To to) {
		this.to = to;
	}

	@JsonProperty("duration")
	public String getDuration() {
		return duration;
	}

	@JsonProperty("duration")
	public void setDuration(String duration) {
		this.duration = duration;
	}

	@JsonProperty("transfers")
	public int getTransfers() {
		return transfers;
	}

	@JsonProperty("transfers")
	public void setTransfers(int transfers) {
		this.transfers = transfers;
	}

}