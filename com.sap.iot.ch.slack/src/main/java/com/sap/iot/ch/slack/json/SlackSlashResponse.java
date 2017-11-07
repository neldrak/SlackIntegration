package com.sap.iot.ch.slack.json;

import java.util.ArrayList;
import java.util.List;

import com.sap.iot.ch.slack.jBot.models.Attachment;

public class SlackSlashResponse {

	private final String text;
	private final String response_type;
	private final List<Attachment> attachments = new ArrayList<>();
	
	public SlackSlashResponse(String text) {
		super();
		response_type = "ephemeral";
		this.text = text;
	}
	
	public void addAttachment(Attachment attachment){
		attachments.add(attachment);
	}
	
	public String getText() {
		return text;
	}
	
	public String getResponse_type() {
		return response_type;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}
}
