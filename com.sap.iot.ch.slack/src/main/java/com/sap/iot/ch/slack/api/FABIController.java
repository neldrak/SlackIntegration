package com.sap.iot.ch.slack.api;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sap.iot.ch.slack.SlackUtil;
import com.sap.iot.ch.slack.jBot.models.Action;
import com.sap.iot.ch.slack.jBot.models.Attachment;
import com.sap.iot.ch.slack.jBot.models.Field;
import com.sap.iot.ch.slack.jBot.models.RichMessage;
import com.sap.iot.ch.slack.json.SlackActionPayload;
import com.sap.iot.ch.slack.json.SlackActionResponse;

@RestController
@RequestMapping("/api/fabi")
public class FABIController {
	private static final Logger LOG = LoggerFactory.getLogger(FABIController.class);

	@Value("${slachCommandTokenFabi}")
	private String slackToken;

	@RequestMapping(value = "/slash-command", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RichMessage onReceiveSlashCommand(@RequestParam("token") String token,
			@RequestParam("team_id") String teamId, @RequestParam("team_domain") String teamDomain,
			@RequestParam("channel_id") String channelId, @RequestParam("channel_name") String channelName,
			@RequestParam("user_id") String userId, @RequestParam("user_name") String userName,
			@RequestParam("command") String command, @RequestParam("text") String text,
			@RequestParam("response_url") String responseUrl) {
		// validate token
		if (!token.equals(slackToken)) {
			return new RichMessage("Sorry! You're not lucky enough to use our slack command.");
		}

		RichMessage defaultResponse = new RichMessage("");
		defaultResponse.setResponseType("ephemeral");
		// set attachments
		Attachment[] attachments = new Attachment[1];
		attachments[0] = new Attachment();
		attachments[0].setFallback("Unkown command");
		attachments[0].setTitle("FABI");
		attachments[0].setTitleLink("https://fiorilaunchpad.sap.com/sites#fabi-Display");
		attachments[0].setText("Please use /fabi *office* | *home* | *customer*\nTo set another day than today use *yesterday* | *tomorrow*");
		attachments[0].setPretext("Unkown command");
		attachments[0].setColor("warning");
		attachments[0].setCallbackId("fabiCallback");
		attachments[0].addAction(new Action("dismiss", "Dimiss", "button"));

		List<String> mrkdwn = new ArrayList<String>();
		mrkdwn.add("title");
		mrkdwn.add("text");
		mrkdwn.add("pretext");
		mrkdwn.add("fields");
		attachments[0].setMarkdownIn(mrkdwn);
		defaultResponse.setAttachments(attachments);

		switch (command) {
		case "/fabi":
			if (text == null || text.isEmpty()) {
				return defaultResponse.encodedMessage();
			}

			LocalDate date = null;
			int numberOfConsDays = 1;
			SlackUtil.FABI_TYPE fabiType = SlackUtil.FABI_TYPE.office;
			Scanner scanner = new Scanner(text);
			scanner.useDelimiter(" ");
			switch (scanner.next()) {
			case "office":
			case "sap":
				fabiType = SlackUtil.FABI_TYPE.office;
				break;
			case "home":
			case "homeoffice":
				fabiType = SlackUtil.FABI_TYPE.home;
				break;
			case "customer":
			case "notSAP":
			case "notSap":
			case "notsap":
				fabiType = SlackUtil.FABI_TYPE.customer;
				break;
			default:
				return defaultResponse.encodedMessage();
			}

			if (scanner.hasNext()) {
				String time = scanner.next();
				switch (time) {
				case "week":
					date = LocalDate.now().with(DayOfWeek.MONDAY);
					numberOfConsDays = 5;
					break;
				case "month":
					date = LocalDate.now().withDayOfMonth(1);
					numberOfConsDays = 5;
					break;
				case "yesterday":
					date = LocalDate.now().minusDays(1);
					break;
				case "tomorrow":
					date = LocalDate.now().plusDays(1);
					break;
				default:
					date = SlackUtil.parseDate(time);
				}
			} else {
				date = LocalDate.now();
			}

			String typeText = fabiType.toString();

			RichMessage richMessage = new RichMessage("");
			attachments = new Attachment[1];
			attachments[0] = new Attachment();
			attachments[0].setTitle("FABI");
			attachments[0].setText("");
			attachments[0].setTitleLink("https://fiorilaunchpad.sap.com/sites#fabi-Display");
			attachments[0].setFallback("Update successful");
			attachments[0].setPretext("Update successful");
			mrkdwn = new ArrayList<String>();
			mrkdwn.add("title");
			mrkdwn.add("pretext");
			mrkdwn.add("fields");
			attachments[0].setMarkdownIn(mrkdwn);
			attachments[0].setColor("good");
			List<Field> fields = new ArrayList<Field>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			for (int i = 0; i < numberOfConsDays; i++) {

				String dateText = "<!date^" + date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) + "^{date_short}|"
						+ formatter.format(date) + ">";

				fields.add(new Field("Date", dateText, true));
				fields.add(new Field("Type", typeText, true));

				date = date.plusDays(1);
			}

			attachments[0].setFields(fields.toArray(new Field[0]));
			attachments[0].setCallbackId("fabiCallback");
			attachments[0].addAction(new Action("dismiss", "Dimiss", "button"));
			richMessage.setAttachments(attachments);
			return richMessage;
		}
		return defaultResponse.encodedMessage();
	}

	@RequestMapping(value = "/action", method = RequestMethod.POST)
	public ResponseEntity<SlackActionResponse> action(@RequestParam("payload") String payload) {

		SlackActionPayload payloadObject = new Gson().fromJson(payload, SlackActionPayload.class);
		LOG.debug(payloadObject.getResponse_url());

		// "https://slack.com/api/chat.delete"
		return new ResponseEntity<>(new SlackActionResponse(true), HttpStatus.OK);
	}

	/**
	 * HTTP_STATUS - 500 -Service Unavailable.
	 * 
	 * @param exception
	 *            Catches the following: MyException
	 * @return
	 */
	@ExceptionHandler()
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public static ResponseEntity<?> handleConnectionErrorResponse(Exception exception) {
		LOG.error(exception.getMessage());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}