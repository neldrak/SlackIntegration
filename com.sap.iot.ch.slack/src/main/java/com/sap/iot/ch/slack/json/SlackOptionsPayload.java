package com.sap.iot.ch.slack.json;

import com.sap.iot.ch.slack.jBot.models.Channel;
import com.sap.iot.ch.slack.jBot.models.Team;
import com.sap.iot.ch.slack.jBot.models.User;

public class SlackOptionsPayload {
	private String name;
	private String value;
	private String callback_id;
	private Team team;
	private Channel channel;
	private User user;
	private String action_ts;
	private String message_ts;
	private String token;
	private String attachment_id;

	public SlackOptionsPayload() {

	}

	public SlackOptionsPayload(String name, String value, String callback_id, Team team, Channel channel,
			User user, String action_ts, String message_ts, String token, String attachment_id) {
		super();
		this.name = name;
		this.value = value;
		this.callback_id = callback_id;
		this.team = team;
		this.channel = channel;
		this.user = user;
		this.action_ts = action_ts;
		this.message_ts = message_ts;
		this.token = token;
		this.attachment_id = attachment_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCallback_id() {
		return callback_id;
	}

	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction_ts() {
		return action_ts;
	}

	public void setAction_ts(String action_ts) {
		this.action_ts = action_ts;
	}

	public String getMessage_ts() {
		return message_ts;
	}

	public void setMessage_ts(String message_ts) {
		this.message_ts = message_ts;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}

}
