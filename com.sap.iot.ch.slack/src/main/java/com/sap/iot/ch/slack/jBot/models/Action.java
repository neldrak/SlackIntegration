package com.sap.iot.ch.slack.jBot.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Action {
	private String name;
	private String text;
	private String type;
	@JsonProperty("data_source")
	private String dataSource;

	@JsonProperty("min_query_length")
	private int minQueryLength;

	@JsonProperty("selected_options")
	private List<SelectOption> selected_options;

	public Action() {
		super();
	}

	public Action(String name, List<SelectOption> selected_options) {
		super();
		this.name = name;
		this.selected_options = selected_options;
	}

	public Action(String name, String text, String type) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
	}

	public Action(String name, String text, String type, String dataSource, int minQueryLength) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
		this.dataSource = dataSource;
		this.minQueryLength = minQueryLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public int getMinQueryLength() {
		return minQueryLength;
	}

	public void setMinQueryLength(int minQueryLength) {
		this.minQueryLength = minQueryLength;
	}

	public List<SelectOption> getSelected_options() {
		return selected_options;
	}

	public void setSelected_options(List<SelectOption> selected_options) {
		this.selected_options = selected_options;
	}

}
