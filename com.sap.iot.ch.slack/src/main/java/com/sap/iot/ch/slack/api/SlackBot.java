package com.sap.iot.ch.slack.api;

import java.util.regex.Matcher;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketSession;

import com.sap.iot.ch.slack.jBot.Bot;
import com.sap.iot.ch.slack.jBot.Controller;
import com.sap.iot.ch.slack.jBot.EventType;
import com.sap.iot.ch.slack.jBot.models.Attachment;
import com.sap.iot.ch.slack.jBot.models.BotMessage;
import com.sap.iot.ch.slack.jBot.models.Event;
import com.sap.iot.ch.slack.jBot.models.Field;
import com.sap.iot.ch.slack.jBot.models.Message;

/**
 * A Slack Bot sample. You can create multiple bots by just extending
 * {@link Bot} class like this one.
 *
 * @author ramswaroop
 * @version 1.0.0, 05/06/2016
 */
@Component
public class SlackBot extends Bot {

	private static final Logger LOG = LoggerFactory.getLogger(SlackBot.class);


	/**
	 * Slack token from application.properties file. You can get your slack
	 * token next <a href="https://my.slack.com/services/new/bot">creating a new
	 * bot</a>.
	 */
	@Value("${slackBotToken}")
	private String slackToken;

	@Override
	public String getSlackToken() {
		return slackToken;
	}

	@Override
	public Bot getSlackBot() {
		return this;
	}

	/**
	 * Invoked when the bot receives a direct mention (@botname: message) or a
	 * direct message. NOTE: These two event types are added by jbot to make
	 * your task easier, Slack doesn't have any direct way to determine these
	 * type of events.
	 *
	 * @param session
	 * @param event
	 */
	@Controller(events = { EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE })
	public void onReceiveDM(WebSocketSession session, Event event) {
		LOG.debug("Message: " + event.getText());
		if (event.getUserId() != null && !event.getUserId().equals("Concur") +  event.getText() != null && !event.getText().isEmpty())
			reply(session, event, new Message("Hi! I'm here to help you with your travel and expense management!"));
	}

	/**
	 * Invoked when bot receives an event of type message with text satisfying
	 * the pattern {@code ([a-z ]{2})(\d+)([a-z ]{2})}. For example, messages
	 * like "ab12xy" or "ab2bc" etc will invoke this method.
	 *
	 * @param session
	 * @param event
	 */
	@Controller(events = EventType.DIRECT_MESSAGE, pattern = "^:airplane:$")
	public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
		if (event.getUserId() != null && !event.getUserId().equals("Concur"))
			showFlight(event);
	}

	// /**
	// * Invoked when an item is pinned in the channel.
	// *
	// * @param session
	// * @param event
	// */
	// @Controller(events = EventType.PIN_ADDED)
	// public void onPinAdded(WebSocketSession session, Event event) {
	// reply(session, event, new Message("Thanks for the pin! You can find all
	// pinned items under channel details."));
	// }

	/**
	 * Invoked when bot receives an event of type file shared. NOTE: You can't
	 * reply to this event as slack doesn't send a channel id for this event
	 * type. You can learn more about
	 * <a href="https://api.slack.com/events/file_shared">file_shared</a> event
	 * from Slack's Api documentation.
	 *
	 * @param session
	 * @param event
	 */
	@Controller(events = EventType.FILE_SHARED)
	public void onFileShared(WebSocketSession session, Event event) {
		LOG.info("File shared: {}", event);
	}

	// /**
	// * Conversation feature of JBot. This method is the starting point of the
	// conversation (as it
	// * calls {@link Bot#startConversation(Event, String)} within it. You can
	// chain methods which will be invoked
	// * one after the other leading to a conversation. You can chain methods
	// with {@link Controller#next()} by
	// * specifying the method name to chain with.
	// *
	// * @param session
	// * @param event
	// */
	// @Controller(pattern = "(setup meeting)", next = "confirmTiming")
	// public void setupMeeting(WebSocketSession session, Event event) {
	// startConversation(event, "confirmTiming"); // start conversation
	// reply(session, event, new Message("Cool! At what time (ex. 15:30) do you
	// want me to set up the meeting?"));
	// }
	//
	// /**
	// * This method is chained with {@link
	// SlackBot#setupMeeting(WebSocketSession, Event)}.
	// *
	// * @param session
	// * @param event
	// */
	// @Controller(next = "askTimeForMeeting")
	// public void confirmTiming(WebSocketSession session, Event event) {
	// reply(session, event, new Message("Your meeting is set at " +
	// event.getText() +
	// ". Would you like to repeat it tomorrow?"));
	// nextConversation(event); // jump to next question in conversation
	// }
	//
	// /**
	// * This method is chained with {@link
	// SlackBot#confirmTiming(WebSocketSession, Event)}.
	// *
	// * @param session
	// * @param event
	// */
	// @Controller(next = "askWhetherToRepeat")
	// public void askTimeForMeeting(WebSocketSession session, Event event) {
	// if (event.getText().contains("yes")) {
	// reply(session, event, new Message("Okay. Would you like me to set a
	// reminder for you?"));
	// nextConversation(event); // jump to next question in conversation
	// } else {
	// reply(session, event, new Message("No problem. You can always schedule
	// one with 'setup meeting' command."));
	// stopConversation(event); // stop conversation only if user says no
	// }
	// }
	//
	// /**
	// * This method is chained with {@link
	// SlackBot#askTimeForMeeting(WebSocketSession, Event)}.
	// *
	// * @param session
	// * @param event
	// */
	// @Controller
	// public void askWhetherToRepeat(WebSocketSession session, Event event) {
	// if (event.getText().contains("yes")) {
	// reply(session, event, new Message("Great! I will remind you tomorrow
	// before the meeting."));
	// } else {
	// reply(session, event, new Message("Oh! my boss is smart enough to remind
	// himself :)"));
	// }
	// stopConversation(event); // stop conversation
	// }

	private void showFlight(Event event) {
		RestTemplate restTemplate = new RestTemplate();
		BotMessage botMessage = new BotMessage("");
		// set attachments
		Attachment[] attachments = new Attachment[1];
		attachments[0] = new Attachment();
		attachments[0].setPretext("Upcoming :airplane: Details");
		attachments[0].setTitle("Zurich ZRH to Madrid MAD");
		attachments[0].setColor("warning");
		Field[] fields = new Field[4];
		fields[0] = new Field();
		fields[0].setShortEnough(true);
		fields[0].setTitle(":airplane_departure:");
		fields[0].setValue("08:30 pm Thursday, November 9th");

		fields[1] = new Field();
		fields[1].setShortEnough(true);
		fields[1].setTitle(":airplane_arriving:");
		fields[1].setValue("10:30 pm Thursday, November 9th");

		fields[2] = new Field();
		fields[2].setShortEnough(true);
		fields[2].setTitle(":timer_clock: ");
		fields[2].setValue("2 hours");

		fields[3] = new Field();
		fields[3].setShortEnough(true);
		fields[3].setTitle(":seat:");
		fields[3].setValue("LX1090, seat 11C");

		attachments[0].setFields(fields);
		botMessage.setAttachments(attachments);

		botMessage.setToken(slackToken);
		LOG.debug("Channel: " + event.getChannelId());
		botMessage.setChannel(event.getChannelId());
		botMessage.setUser(event.getUserId());
		botMessage.setAs_user(false);

		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			requestHeaders.add("Authorization", "Bearer " + slackToken);
			HttpEntity<?> httpEntity = new HttpEntity<Object>(botMessage, requestHeaders);
			restTemplate.postForEntity("https://slack.com/api/chat.postEphemeral", httpEntity, String.class);
		} catch (RestClientException e) {
			LOG.error("Error posting to Slack Incoming Webhook: ", e);
		}
	}

	@PreDestroy
	public void destroy() {
		if (manager == null)
			return;
		if (manager.isRunning())
			try {
				manager.stopInternal();
			} catch (Exception e) {

			}
	}
}