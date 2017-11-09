package com.sap.iot.ch.slack.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.iot.ch.slack.jBot.models.Attachment;
import com.sap.iot.ch.slack.jBot.models.Field;
import com.sap.iot.ch.slack.jBot.models.RichMessage;

@RestController
@RequestMapping("/api/meal")
public class MealController {
	private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

	/**
	 * The token you get while creating a new Slash Command. You should paste
	 * the token in application.properties file.
	 */
	@Value("${slashCommandTokenMeal}")
	private String slackToken;

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
			String url = "http://www.meal-and-more.ch";
			if (text.contains("next")) {
				url = "http://www.meal-and-more.ch/home?page=1";
			}

			boolean week = false;
			int dayOfWeek = 0;
			if (text.contains("week")) {
				week = true;
			} else {
				dayOfWeek = LocalDateTime.now().getDayOfWeek().getValue() - 1;
				if (text.contains("tomorrow")) {
					dayOfWeek++;
				} else if(LocalDateTime.now().getHour() >= 14) {
					// Check if it is after 14:00 -> show next day
					dayOfWeek++;
				}
			}

			/** build response */
			RichMessage richMessage = new RichMessage("Lunch Menu Regensdorf");
			richMessage.setResponseType("ephemeral");
			// set attachments
			Attachment[] attachments = new Attachment[1];
			attachments[0] = new Attachment();
			attachments[0].setTitle(":knife_fork_plate: Meal & More");
			attachments[0].setTitleLink(url);
			List<String> mrkdwn = new ArrayList<String>();
			mrkdwn.add("pretext");
			mrkdwn.add("fields");
			attachments[0].setMarkdownIn(mrkdwn);
			int count = 15;
			if (!week)
				count = 3;
			Field[] fields = new Field[count];

			Document doc = Jsoup.connect(url).get();
			Elements e1 = doc.select("#wochenmenu");
			Elements dates = e1.select(".date-display-single");
			if(dates.isEmpty()){
				RichMessage richMessage2 = new RichMessage("Lunch Menu not available yet");
				richMessage2.setResponseType("ephemeral");
				return richMessage2.encodedMessage();
			}
			if (week) {
				attachments[0].setPretext("_" + dates.get(0).text().substring(0, dates.get(0).text().length() - 6)
						+ " - " + dates.get(4).text() + "_");
			}
			Elements menus = e1.select("p");
			Elements menuClean = new Elements();
			Element textP = null;
			for (Element menu : menus) {
				if (menu.hasText()) {
					if (menu.text().contains("MEAL") || menu.text().contains("CHF")) {
						if (textP != null)
							menuClean.add(textP);
						textP = null;
						menuClean.add(menu);
					} else if (menu.text().length() > 8) {
						if (textP == null) {
							textP = menu;
						} else {
							textP.text(textP.text() + " " + menu.text());
						}
					}
				}
			}
			if (textP != null)
				menuClean.add(textP);

			if (week) {
				int p = 0;
				int i = 0;
				for (Element date : dates) {
					fields[i] = new Field();
					fields[i].setTitle(":small_blue_diamond: " + date.text() + " :small_blue_diamond:");
					fields[i].setShortEnough(false);
					i++;
					fields[i] = new Field();
					fields[i].setTitle(menuClean.get(p).text());
					fields[i].setShortEnough(true);
					p++;
					fields[i].setValue(menuClean.get(p).text() + "\n _" + menuClean.get((p + 1)).text() + "_");
					p = p + 2;
					i++;
					fields[i] = new Field();
					fields[i].setTitle(menuClean.get(p).text());
					fields[i].setShortEnough(true);
					p++;
					fields[i].setValue(menuClean.get(p).text() + "\n _" + menuClean.get((p + 1)).text() + "_");
					p = p + 2;
					i++;
				}
			} else {
				int p = dayOfWeek * 3;
				fields[0] = new Field();
				fields[0].setTitle(":small_blue_diamond: " + dates.get(dayOfWeek).text() + " :small_blue_diamond:");
				fields[0].setShortEnough(false);
				fields[1] = new Field();
				fields[1].setTitle(menuClean.get(p).text());
				fields[1].setShortEnough(true);
				fields[1].setValue(menuClean.get(p+1).text() + "\n _" + menuClean.get((p + 2)).text() + "_");
				fields[2] = new Field();
				fields[2].setTitle(menuClean.get(p+3).text());
				fields[2].setShortEnough(true);
				fields[2].setValue(menuClean.get(p+4).text() + "\n _" + menuClean.get((p + 5)).text() + "_");
			}

			attachments[0].setFields(fields);
			richMessage.setAttachments(attachments);
			return richMessage.encodedMessage(); // don't forget to send the
													// encoded message to
													// SlackhMessage.setAttachments(attachments)
		} catch (IOException e1) {
			return new RichMessage("Exception: " + e1.getMessage());
		}
	}

}