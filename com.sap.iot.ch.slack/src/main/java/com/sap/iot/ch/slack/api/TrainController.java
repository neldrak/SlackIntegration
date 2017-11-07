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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.sap.iot.ch.slack.dao.SlackUserDAO;
import com.sap.iot.ch.slack.jBot.models.Action;
import com.sap.iot.ch.slack.jBot.models.Attachment;
import com.sap.iot.ch.slack.jBot.models.Field;
import com.sap.iot.ch.slack.jBot.models.RichMessage;
import com.sap.iot.ch.slack.json.SlackActionPayload;
import com.sap.iot.ch.slack.json.SlackActionResponse;
import com.sap.iot.ch.slack.json.SlackOptionsPayload;
import com.sap.iot.ch.slack.json.SlackOptionsResponse;
import com.sap.iot.ch.slack.json.sbb.Connection;
import com.sap.iot.ch.slack.json.sbb.Connections;
import com.sap.iot.ch.slack.json.sbb.Station;
import com.sap.iot.ch.slack.json.sbb.StationTable;
import com.sap.iot.ch.slack.json.sbb.Stationboard;
import com.sap.iot.ch.slack.json.sbb.Stations;
import com.sap.iot.ch.slack.model.SlackUser;

@RestController
@RequestMapping("/api/train")
public class TrainController {
	private static final Logger LOG = LoggerFactory.getLogger(TrainController.class);

	/**
	 * The token you get while creating a new Slash Command. You should paste
	 * the token in application.properties file.
	 */
	@Value("${slashCommandTokenTrain}")
	private String slackToken;

	private final String BASE_URL = "https://transport.opendata.ch";

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

			// Try to get User from DB
			LOG.error("UserId: " + userId + " UserName: " + userName);
			SlackUser slackUser = userDAO.findUserByUserId(userId);
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm MMM dd");
			// 2017-11-07T10:00:00+0100
			DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXX");
			inputDateFormatter.withResolverStyle(ResolverStyle.SMART);
			
			if(text==null)
				text = "";
			StringTokenizer stringTokens = new StringTokenizer(text);

			// Set Home Station
			if (stringTokens != null && !text.isEmpty()) {
				if (stringTokens.countTokens() == 1 && stringTokens.nextToken().equals(":home:")) {
					// Return Select Home Response
					RichMessage richMessage = new RichMessage("Set Home Station");
					richMessage.setResponseType("ephemeral");
					// set attachments
					Attachment[] attachments = new Attachment[1];
					attachments[0] = new Attachment();
					attachments[0].setCallbackId("setHome");
					attachments[0]
							.addAction(new Action("station", "Select Station near Home", "select", "external", 3));
					richMessage.setAttachments(attachments);
					return richMessage.encodedMessage();
				}

				// Set Office Station
				stringTokens = new StringTokenizer(text);
				if (stringTokens.countTokens() == 1 && stringTokens.nextToken().equals(":office:")) {
					// Return Select Home Response
					RichMessage richMessage = new RichMessage("Set Office Station");
					richMessage.setResponseType("ephemeral");
					// set attachments
					Attachment[] attachments = new Attachment[1];
					attachments[0] = new Attachment();
					attachments[0].setCallbackId("setOffice");
					attachments[0]
							.addAction(new Action("station", "Select Station near Office", "select", "external", 3));
					richMessage.setAttachments(attachments);
					return richMessage.encodedMessage();
				}

				// Station Schedule
				stringTokens = new StringTokenizer(text);
				String firstToken = stringTokens.nextToken();
				if (!stringTokens.hasMoreTokens() && (firstToken.equals("home") || firstToken.equals("office"))) {

					String stationId = "8503526"; // Default Regensdorf

					if (text.contains("home") && slackUser.getHomeStation() != 0) {
						LOG.error("Home found: " + slackUser.getHomeStation());
						stationId = "" + slackUser.getHomeStation();
					}
					if (text.contains("office") && slackUser.getOfficeStation() != 0) {
						stationId = "" + slackUser.getOfficeStation();
						LOG.error("Office found: " + slackUser.getOfficeStation());
					}

					// Call API
					URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL).path("/v1/stationboard")
							.queryParam("id", stationId).queryParam("limit", "5").build().encode().toUri();
					RestTemplate restTemplate = new RestTemplate();
					StationTable stationTable = restTemplate.getForObject(targetUrl, StationTable.class);

					RichMessage richMessage = new RichMessage("Train Schedule");
					richMessage.setResponseType("ephemeral");
					// set attachments
					Attachment[] attachments = new Attachment[1];
					attachments[0] = new Attachment();
					attachments[0].setTitle(":station:");
					attachments[0].setTitleLink("https://www.sbb.ch/de/fahrplan.html");
					List<String> mrkdwn = new ArrayList<String>();
					mrkdwn.add("pretext");
					mrkdwn.add("fields");
					attachments[0].setMarkdownIn(mrkdwn);
					List<Field> fields = new ArrayList<Field>();

					for (Stationboard stationboard : stationTable.getStationboard()) {
						if (stationboard.getCategory().equals("BUS")) {
							continue;
						}
						Field field = new Field();
						LocalDateTime date = LocalDateTime.parse(stationboard.getStop().getDeparture(),
								inputDateFormatter);
						String dateText = dateFormatter.format(date);
						field.setTitle(dateText);
						field.setValue(stationboard.getTo() + " | _" + stationboard.getName() + "_" + " | Platform *"
								+ stationboard.getStop().getPlatform() + "*");
						field.setShortEnough(false);
						fields.add(field);
					}

					attachments[0].setFields(fields.toArray(new Field[0]));
					richMessage.setAttachments(attachments);
					return richMessage.encodedMessage();
				}

				// Connection
				stringTokens = new StringTokenizer(text);
				if (stringTokens.countTokens() == 2) {
					try {
						int stationFrom = resolveStation(stringTokens.nextToken(), slackUser);
						int stationTo = resolveStation(stringTokens.nextToken(), slackUser);

						// Read Connections
						URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL).path("/v1/connections")
								.queryParam("from", stationFrom).queryParam("to", stationTo).build().encode().toUri();
						RestTemplate restTemplate = new RestTemplate();
						Connections connections = restTemplate.getForObject(targetUrl, Connections.class);
						if (connections.getConnections().isEmpty()) {
							return new RichMessage("No Connections available");
						}

						RichMessage richMessage = new RichMessage("Connections");
						richMessage.setResponseType("ephemeral");
						// set attachments
						Attachment[] attachments = new Attachment[1];
						attachments[0] = new Attachment();
						attachments[0].setTitle("");
						attachments[0].setTitleLink("https://www.sbb.ch/de/fahrplan.html");
						List<String> mrkdwn = new ArrayList<String>();
						mrkdwn.add("pretext");
						mrkdwn.add("fields");
						attachments[0].setMarkdownIn(mrkdwn);
						List<Field> fields = new ArrayList<Field>();
						for (Connection connection : connections.getConnections()) {

							Field fieldFrom = new Field();
							LocalDateTime dateDep = LocalDateTime.parse(connection.getFrom().getDeparture(),
									inputDateFormatter);
							String depDateText = dateFormatter.format(dateDep);
							fieldFrom.setTitle(depDateText);
							fieldFrom.setValue(connection.getFrom().getStation().getName() + " | Platform *"
									+ connection.getFrom().getPlatform() + "*" + "\n"
									+ formatDuration(connection.getDuration()) + "\n"
									+ connection.getProductString());
							fieldFrom.setShortEnough(true);
							fields.add(fieldFrom);
							Field fieldTo = new Field();
							LocalDateTime dateArr = LocalDateTime.parse(connection.getTo().getArrival(),
									inputDateFormatter);
							String arrDateText = dateFormatter.format(dateArr);
							fieldTo.setTitle(arrDateText);
							fieldTo.setValue(connection.getTo().getStation().getName() + " | Platform *"
									+ connection.getTo().getPlatform() + "*" + "\n" + connection.getTransfers()
									+ " transfers");
							fieldTo.setShortEnough(true);
							fields.add(fieldTo);
						}

						attachments[0].setFields(fields.toArray(new Field[0]));
						richMessage.setAttachments(attachments);
						return richMessage.encodedMessage();

					} catch (Exception e) {
						return new RichMessage(e.getMessage());
					}
				}
			}

			// Help Text
			RichMessage richMessage = new RichMessage("");
			richMessage.setResponseType("ephemeral");
			// set attachments
			Attachment[] attachments = new Attachment[1];
			attachments[0] = new Attachment();
			attachments[0].setTitle("How to use /train");
			attachments[0].setPretext(":tram: by SAP CH");
			attachments[0].setText(
					"`/train [station]`\nGet Station Schedule e.g. _/train Bern_\n" + 
					"`/train [from] [to]`\nGet Connection e.g. _/train Genf Bern_\n" + 
					"`/train :home:|:office:`\nSet Office/Home Station\nThose can be used later as station e.g. _/train home_");
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
		String id = payloadObject.getActions().get(0).getSelected_options().get(0).getValue();
		SlackUser slackUser = new SlackUser();
		slackUser = userDAO.findUserByUserId(payloadObject.getUser().getId());

		switch (payloadObject.getCallback_id()) {
		case "setOffice":
			response.setResponse_type("ephemeral");
			response.setReplace_original(true);
			response.setText(":office: updated");
			slackUser.setOfficeStation(Integer.parseInt(id));
			break;
		case "setHome":
			response.setReplace_original(true);
			response.setResponse_type("ephemeral");
			slackUser.setHomeStation(Integer.parseInt(id));
			response.setText(":home: updated");
			break;
		}

		userDAO.save(slackUser);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/options-load-endpoint", method = RequestMethod.POST)
	public ResponseEntity<SlackOptionsResponse> optionsEndpoint(@RequestParam("payload") String payload) {
		SlackOptionsPayload payloadObject = new Gson().fromJson(payload, SlackOptionsPayload.class);
		SlackOptionsResponse response = new SlackOptionsResponse();
		switch (payloadObject.getName()) {
		case "station":
			// Read Stations
			URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL).path("/v1/locations")
					.queryParam("query", payloadObject.getValue()).queryParam("type", "station").build().encode()
					.toUri();
			RestTemplate restTemplate = new RestTemplate();
			Stations stations = restTemplate.getForObject(targetUrl, Stations.class);
			for (Station station : stations.getStations()) {
				response.addOption(station.getName(), station.getId());
			}
			break;
		default:
			break;
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private int resolveStation(String stationText, SlackUser user) throws Exception {
		switch (stationText) {
		case "home":
			int stationH = user.getHomeStation();
			if (stationH == 0) {
				throw new Exception("Please set Home Station `/train :home:`");
			}
			return stationH;
		case "office":
			int stationO = user.getOfficeStation();
			if (stationO == 0) {
				throw new Exception("Please set Office Station `/train :home:`");
			}
			return stationO;
		default:
			// Read Stations
			URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL).path("/v1/locations")
					.queryParam("query", stationText).queryParam("type", "station").build().encode().toUri();
			RestTemplate restTemplate = new RestTemplate();
			Stations stations = restTemplate.getForObject(targetUrl, Stations.class);
			if (!stations.getStations().isEmpty())
				return Integer.parseInt(stations.getStations().get(0).getId());
		}
		throw new Exception("Can't find Station `" + stationText + "`");
	}

	private String formatDuration(String duration) {
		String[] tokens = duration.split("(d)|(:)");
		if (tokens.length != 4)
			return "No Duration";
		String result = "";
		if (!tokens[0].equals("00")) {
			result += tokens[0] + "days ";
		}
		if (!tokens[1].equals("00")) {
			result += tokens[1] + "h ";
		}
		if (!tokens[2].equals("00")) {
			result += tokens[2] + "m ";
		}
		if (!tokens[3].equals("00")) {

		}
		return ":timer_clock: " + result;
	}

}