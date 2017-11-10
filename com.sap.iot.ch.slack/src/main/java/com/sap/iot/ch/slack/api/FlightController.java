package com.sap.iot.ch.slack.api;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.sap.iot.ch.slack.dao.SlackUserDAO;
import com.sap.iot.ch.slack.jBot.models.Attachment;
import com.sap.iot.ch.slack.jBot.models.Field;
import com.sap.iot.ch.slack.jBot.models.RichMessage;
import com.sap.iot.ch.slack.json.SlackActionPayload;
import com.sap.iot.ch.slack.json.SlackActionResponse;
import com.sap.iot.ch.slack.json.SlackOptionsPayload;
import com.sap.iot.ch.slack.json.SlackOptionsResponse;
import com.sap.iot.ch.slack.json.lh.ConnectionResponse;
import com.sap.iot.ch.slack.json.lh.Flight;
import com.sap.iot.ch.slack.json.lh.TokenResponse;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
	private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

	private static enum COMMAND_TYPE {
		AIRPORT, FLIGHT_NO, UNKOWN
	}

	/**
	 * The token you get while creating a new Slash Command. You should paste
	 * the token in application.properties file.
	 */
	@Value("${slashCommandTokenFlight}")
	private String slackToken;

	@Value("${lufthansaClientId}")
	private String lufthansaClientId;

	@Value("${lufthansaClientSecret}")
	private String lufthansaClientSecret;

	private static String lufthansaClientToken;

	private static LocalDateTime lufthansaTokenExpDate;

	private final String BASE_URL = "https://api.lufthansa.com";

	DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
			.withResolverStyle(ResolverStyle.SMART);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");

	@Autowired
	private SlackUserDAO userDAO;

	/**
	 * Slash Command handler. When a user types for example "/app help" then
	 * slack sends a POST request to this endpoint. So, this endpoint should
	 * match the url you set while creating the Slack Slash Command.
	 *
	 * @param token
	 * @param teamId
	 * @param teamDomain
	 * @param channelId
	 * @param channelName
	 * @param userId
	 * @param userName
	 * @param command
	 * @param text
	 * @param responseUrl
	 * @return
	 */
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

		try {
			if (text == null)
				text = "";
			StringTokenizer stringTokens = new StringTokenizer(text);

			if (stringTokens != null && !text.isEmpty()) {
				switch (stringTokens.countTokens()) {
				case 1:
					String singleToken = stringTokens.nextToken();
					CommandToken commandToken = resolveCommandToken(singleToken);
					switch (commandToken.getType()) {
					case AIRPORT:
						return getAirportSchedule(commandToken.getValue());
					case FLIGHT_NO:
						return getFlightStatus(commandToken.getValue());
					default:
					}
				case 2:
					String firstToken = stringTokens.nextToken();
					CommandToken commandToken1 = resolveCommandToken(firstToken);
					String secondToken = stringTokens.nextToken();
					CommandToken commandToken2 = resolveCommandToken(secondToken);
					if (commandToken1.getType() == COMMAND_TYPE.AIRPORT
							&& commandToken2.getType() == COMMAND_TYPE.AIRPORT) {
						return getConnections(commandToken1.getValue(), commandToken2.getValue());
					}
				}
			}

			// Help Text
			RichMessage richMessage = new RichMessage("");
			richMessage.setResponseType("ephemeral");
			// set attachments
			Attachment[] attachments = new Attachment[1];
			attachments[0] = new Attachment();
			attachments[0].setTitle("How to use /flight");
			attachments[0].setPretext(":airplane: by SAP CH");
			attachments[0].setText("`/flight [airport]`\nGet Flight Schedule e.g. _/flight ZRH_\n"
					+ "`/flight [from] [to]`\nGet Connection e.g. _/flight ZRH MAD_\n"
					+ "`/flight [FlightNumber]`\nGet Flight Status e..g _/flight LH403");
			List<String> mrkdwn = new ArrayList<String>();
			mrkdwn.add("text");
			attachments[0].setMarkdownIn(mrkdwn);
			richMessage.setAttachments(attachments);
			return richMessage.encodedMessage();
		} catch (Exception e1) {
			return new RichMessage("Exception: " + e1.getMessage());
		}

	}

	@RequestMapping(value = "/action-endpoint", method = RequestMethod.POST)
	public ResponseEntity<SlackActionResponse> actionEndpoint(@RequestParam("payload") String payload) {
		SlackActionPayload payloadObject = new Gson().fromJson(payload, SlackActionPayload.class);
		SlackActionResponse response = new SlackActionResponse();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/options-load-endpoint", method = RequestMethod.POST)
	public ResponseEntity<SlackOptionsResponse> optionsEndpoint(@RequestParam("payload") String payload) {
		SlackOptionsPayload payloadObject = new Gson().fromJson(payload, SlackOptionsPayload.class);
		SlackOptionsResponse response = new SlackOptionsResponse();
		switch (payloadObject.getName()) {

		// TODO

		default:
			break;
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String getLufthansaToken() {
		if (lufthansaClientToken == null || lufthansaClientToken.isEmpty() || lufthansaTokenExpDate == null
				|| lufthansaTokenExpDate.isBefore(LocalDateTime.now())) {
			LOG.error("Resolve Token " + lufthansaClientId + " - " + lufthansaClientSecret);

			URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL).path("/v1/oauth/token").build().encode()
					.toUri();
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("client_id", lufthansaClientId);
			map.add("client_secret", lufthansaClientSecret);
			map.add("grant_type", "client_credentials");
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map,
					requestHeaders);
			TokenResponse tokenResponse = restTemplate.postForObject(targetUrl, httpEntity, TokenResponse.class);

			lufthansaClientToken = tokenResponse.getAccessToken();
			lufthansaTokenExpDate = LocalDateTime.now().plusSeconds(tokenResponse.getExpiresIn());

		}
		LOG.error("Token: " + lufthansaClientToken);
		return lufthansaClientToken;
	}

	private RichMessage getConnections(String from, String to) {
		// Read Connections
		String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		URI url = UriComponentsBuilder.fromUriString(BASE_URL)
				.path("/operations/schedules/" + from + "/" + to + "/" + dateString).queryParam("directFlights", true)
				.build().encode().toUri();
		String token = getLufthansaToken();
		LOG.error("Token: " + token);
		LOG.debug("Url: " + url.toString());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Authorization", "Bearer " + token);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		ResponseEntity<ConnectionResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				ConnectionResponse.class);

		RichMessage response = new RichMessage();
		response.setResponseType("ephemeral");
		// set attachments
		Attachment[] attachments = new Attachment[1];
		attachments[0] = new Attachment();
		attachments[0].setTitle("");
		attachments[0].setTitleLink("");
		List<String> mrkDown = new ArrayList<String>();
		mrkDown.add("pretext");
		mrkDown.add("fields");
		attachments[0].setMarkdownIn(mrkDown);
		List<Field> fields = new ArrayList<Field>();
		for (Flight flight : responseEntity.getBody().getFlightStatusResource().getFlights()) {

			Field fieldFrom = new Field();
			fieldFrom.setTitle(
					flight.getOperatingCarrier().getAirlineID() + " " + flight.getOperatingCarrier().getFlightNumber());
			LocalDateTime dateDep = LocalDateTime.parse(flight.getDeparture().getScheduledTimeUTC().getDateTime(),
					inputDateFormatter);
			String depDateText = dateFormatter.format(dateDep);
			String depValue = ":airplane_departure: " + depDateText;
			if (flight.getDeparture().getTerminal() != null)
				depValue += "\nTerminal: " + flight.getDeparture().getTerminal();
			fieldFrom.setValue(depValue);
			fieldFrom.setShortEnough(true);
			fields.add(fieldFrom);
			Field fieldTo = new Field();
			fieldTo.setTitle(flight.getDeparture().getTimeStatus().getDefinition());
			LocalDateTime dateArr = LocalDateTime.parse(flight.getArrival().getScheduledTimeUTC().getDateTime(),
					inputDateFormatter);
			String arrDateText = dateFormatter.format(dateArr);
			String arrValue = ":airplane_arriving: " + arrDateText;
			if (flight.getArrival().getTerminal() != null)
				arrValue += "\nTerminal: " + flight.getArrival().getTerminal();
			fieldTo.setValue(arrValue);
			fieldTo.setShortEnough(true);
			fields.add(fieldTo);
		}

		attachments[0].setFields(fields.toArray(new Field[0]));
		response.setAttachments(attachments);
		return response;
	}

	private RichMessage getAirportSchedule(String airport) {
		RichMessage response = new RichMessage();

		return response;
	}

	private RichMessage getFlightStatus(String flight) {
		RichMessage response = new RichMessage();

		return response;
	}

	private CommandToken resolveCommandToken(String text) {
		switch (text.length()) {
		case 3:
			return new CommandToken(text, COMMAND_TYPE.AIRPORT);
		case 6:
			return new CommandToken(text, COMMAND_TYPE.FLIGHT_NO);
		default:
			return new CommandToken(text, COMMAND_TYPE.UNKOWN);
		}
	}

	private class CommandToken {

		public CommandToken(String value, COMMAND_TYPE type) {
			super();
			this.value = value;
			this.type = type;
		}

		private String value;
		private COMMAND_TYPE type;

		public String getValue() {
			return value;
		}

		public COMMAND_TYPE getType() {
			return type;
		}
	}

}