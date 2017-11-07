package com.sap.iot.ch.slack.json;

public class SlackActionResponse {

	private boolean delete_original = false;
	private boolean replace_original = false;
	private String response_type;
	private String text;
	
	public SlackActionResponse(){
		
	}
	
	public SlackActionResponse(boolean delete_original) {
		super();
		this.delete_original = delete_original;
	}

	public boolean isDelete_original() {
		return delete_original;
	}

	public boolean isReplace_original() {
		return replace_original;
	}

	public void setReplace_original(boolean replace_original) {
		this.replace_original = replace_original;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDelete_original(boolean delete_original) {
		this.delete_original = delete_original;
	}

}
