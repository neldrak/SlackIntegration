package com.sap.iot.ch.slack.api;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sap.iot.ch.slack.json.SlackAction;
import com.sap.iot.ch.slack.json.SlackActionPayload;
import com.sap.iot.ch.slack.json.SlackActionResponse;
import com.sap.iot.ch.slack.json.SlackAttachment;
import com.sap.iot.ch.slack.json.SlackSlashResponse;

@RestController
@RequestMapping("/api/fabi")
public class FABIController {
	private static final Logger LOG = LoggerFactory.getLogger(FABIController.class);

	private final static String SLACK_FABI_TOKEN = "YoWkVkKMcRlEkPipUiK4BUNz";

	@RequestMapping(value = "/slash-command", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SlackSlashResponse> onReceiveSlashCommand(@RequestParam("token") String token,
			@RequestParam("team_id") String teamId, @RequestParam("team_domain") String teamDomain,
			@RequestParam("channel_id") String channelId, @RequestParam("channel_name") String channelName,
			@RequestParam("user_id") String userId, @RequestParam("user_name") String userName,
			@RequestParam("command") String command, @RequestParam("text") String text,
			@RequestParam("response_url") String responseUrl) {
		// validate token
		if (!token.equals(SLACK_FABI_TOKEN)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		SlackSlashResponse defaultResponse = new SlackSlashResponse("");
		SlackAttachment attachment = new SlackAttachment("", "Unkown command",
				"Please use /fabi *office* | *home* | *customer*\nTo set another day than today use *yesterday* | *tomorrow*",
				"Unkown command");
		attachment.addMarkdownIn("pretext");
		attachment.addMarkdownIn("text");
		attachment.addMarkdownIn("title");
		attachment.addMarkdownIn("fields");
		attachment.setColor("warning");
		attachment.setCallbackId("fabiCallback");
		attachment.addAction("dismiss", "dismiss", "Dismiss", "button");

		defaultResponse.addAttachment(attachment);

		switch (command) {
		case "/fabi":
			if (text == null || text.isEmpty()) {
				return new ResponseEntity<SlackSlashResponse>(defaultResponse, HttpStatus.OK);
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
				return new ResponseEntity<SlackSlashResponse>(defaultResponse, HttpStatus.OK);
			}

			if (scanner.hasNext()) {
				String time = scanner.next();
				switch (time) {
				case "week":
					date = LocalDate.now().with(DayOfWeek.MONDAY);
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String dateText = "<!date^" + date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) + "^{date_short}|"
					+ formatter.format(date) + ">";
			SlackSlashResponse response = new SlackSlashResponse("");
			SlackAttachment attachmentResponse = new SlackAttachment("FABI", "Update successful", "",
					"Update successful");
			attachmentResponse.addMarkdownIn("pretext");
			attachmentResponse.addMarkdownIn("text");
			attachmentResponse.addMarkdownIn("title");
			attachmentResponse.addMarkdownIn("fields");
			attachmentResponse.setTitleLink("https://fiorilaunchpad.sap.com/sites#fabi-Display");
			attachmentResponse.setColor("good");
			attachmentResponse.addField("Date", dateText, true);
			attachmentResponse.addField("Type", typeText, true);
			attachmentResponse.setCallbackId("fabiCallback");
			attachmentResponse.addAction("dismiss", "dismiss", "Dismiss", SlackAction.TYPE_BUTTON);

			response.addAttachment(attachmentResponse);

			return new ResponseEntity<SlackSlashResponse>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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