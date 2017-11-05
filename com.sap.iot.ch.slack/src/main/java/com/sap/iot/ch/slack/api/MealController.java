package com.sap.iot.ch.slack.api;

import java.io.IOException;
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
			if(text.contains("next")){
				url = "http://www.meal-and-more.ch/home?page=1";
			}

			/** build response */
			RichMessage richMessage = new RichMessage("Lunch Menu Regensdorf");
			richMessage.setResponseType("ephemeral");
			// set attachments
			Attachment[] attachments = new Attachment[1];
			attachments[0] = new Attachment();
			attachments[0].setTitle("Meal & More");
			attachments[0].setTitleLink(url);
			List<String> mrkdwn = new ArrayList<String>();
			mrkdwn.add("fields");
			attachments[0].setMarkdownIn(mrkdwn);
			Field[] fields = new Field[15];

			Document doc = Jsoup.connect(url).get();
			Elements e1 = doc.select("#wochenmenu");
			Elements dates = e1.select(".date-display-single");
			Elements menus = e1.select("p");
			Elements menuClean = new Elements();
			for(Element menu : menus){
				if(menu.hasText() && !menu.text().equals("&nbsp;"))
					menuClean.add(menu);
			}
			int p = 0;
			int i = 0;
			for (Element date : dates) {
				fields[i] = new Field();
				fields[i].setTitle(":knife_fork_plate: " + date.text());
				fields[i].setShortEnough(false);
				i++;
				fields[i] = new Field();
				fields[i].setTitle(menuClean.get(p).text());
				fields[i].setShortEnough(true);
				p++;
				fields[i].setValue(menuClean.get(p).text() + "\n*" + menuClean.get(p+1).text() + "*");
				p++;
				p++;
				i++;
				
				fields[i] = new Field();
				fields[i].setTitle(menuClean.get(p).text());
				fields[i].setShortEnough(true);
				p++;
				fields[i].setValue(menuClean.get(p).text() + "\n*" + menuClean.get(p+1).text() + "*");
				p++;
				p++;
				i++;
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