package com.sap.iot.ch.slack.json;

import java.util.ArrayList;
import java.util.List;

import com.sap.iot.ch.slack.jBot.models.Option;

public class SlackOptionsResponse {

	List<Option> options = new ArrayList<>();

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public void addOption(String text, String value){
		options.add(new Option(text, value));
	}
}
