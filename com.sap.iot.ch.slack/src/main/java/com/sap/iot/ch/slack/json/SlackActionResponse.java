package com.sap.iot.ch.slack.json;

public class SlackActionResponse {

	private final boolean delete_original;
	
	public SlackActionResponse(boolean delete_original) {
		super();
		this.delete_original = delete_original;
	}

	public boolean isDelete_original() {
		return delete_original;
	}
	
	
}
