package com.sap.iot.ch.slack.json;

import java.util.List;

import com.sap.iot.ch.slack.jBot.models.Action;
import com.sap.iot.ch.slack.jBot.models.Channel;
import com.sap.iot.ch.slack.jBot.models.Team;
import com.sap.iot.ch.slack.jBot.models.User;

public class SlackActionPayload {

	private String callback_id;

	private List<Action> actions;
	private Channel channel;
	private User user;
	private Team team;
	private String action_ts;
	private String message_ts;
	private String token;
	private String attachment_id;
	private boolean is_app_unfurl;
	private String type;
	private String response_url;
	private String trigger_id;
	
	public SlackActionPayload() {
		super();
	}
	public SlackActionPayload(String callback_id, String token, Channel channel, User user, Team team,
			String action_ts, String message_ts, String attachment_id, boolean is_app_unfurl, String type,
			String response_url, String trigger_id) {
		super();
		this.callback_id = callback_id;
		this.token = token;
		this.channel = channel;
		this.user = user;
		this.team = team;
		this.action_ts = action_ts;
		this.message_ts = message_ts;
		this.attachment_id = attachment_id;
		this.is_app_unfurl = is_app_unfurl;
		this.type = type;
		this.response_url = response_url;
		this.trigger_id = trigger_id;
	}
	
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public String getCallback_id() {
		return callback_id;
	}
	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
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
	public String getAttachment_id() {
		return attachment_id;
	}
	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}
	public boolean isIs_app_unfurl() {
		return is_app_unfurl;
	}
	public void setIs_app_unfurl(boolean is_app_unfurl) {
		this.is_app_unfurl = is_app_unfurl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResponse_url() {
		return response_url;
	}
	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}
	public String getTrigger_id() {
		return trigger_id;
	}
	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}
	
	
}
