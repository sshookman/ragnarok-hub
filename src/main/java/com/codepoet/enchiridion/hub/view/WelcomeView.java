package com.codepoet.enchiridion.hub.view;

import com.codepoet.enchiridion.common.telnet.TelnetRenderer;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WelcomeView {

	private final String welcomeTemplate = ""
			+ "----------------------------------------------------------------------------------------------------\n"
			+ "The______  _        _______          _________ _______ _________ ______  _________ _______  _\n"
			+ " (  ____ \\( (    /|(  ____ \\|\\     /|\\__   __/(  ____ )\\__   __/(  __  \\ \\__   __/(  ___  )( (    /|\n"
			+ " | (    \\/|  \\  ( || (    \\/| )   ( |   ) (   | (    )|   ) (   | (  \\  )   ) (   | (   ) ||  \\  ( |\n"
			+ " | (__    |   \\ | || |      | (___) |   | |   | (____)|   | |   | |   ) |   | |   | |   | ||   \\ | |\n"
			+ " |  __)   | (\\ \\) || |      |  ___  |   | |   |     __)   | |   | |   | |   | |   | |   | || (\\ \\) |\n"
			+ " | (      | | \\   || |      | (   ) |   | |   | (\\ (      | |   | |   ) |   | |   | |   | || | \\   |\n"
			+ " | (____/\\| )  \\  || (____/\\| )   ( |___) (___| ) \\ \\_____) (___| (__/  )___) (___| (___) || )  \\  |\n"
			+ " (_______/|/    )_)(_______/|/     \\|\\_______/|/   \\__/\\_______/(______/ \\_______/(_______)|/    )_)\n"
			+ "  _       _________ ______   _______  _______  _______\n"
			+ " ( \\      \\__   __/(  ___ \\ (  ____ )(  ___  )(  ____ )|\\     /|\n"
			+ " | (         ) (   | (   ) )| (    )|| (   ) || (    )|( \\   / )\n"
			+ " | |         | |   | (__/ / | (____)|| (___) || (____)| \\ (_) /\n"
			+ " | |         | |   |  __ (  |     __)|  ___  ||     __)  \\   /\n"
			+ " | |         | |   | (  \\ \\ | (\\ (   | (   ) || (\\ (      ) (\n"
			+ " | (____/\\___) (___| )___) )| ) \\ \\__| )   ( || ) \\ \\__   | |\n"
			+ " (_______/\\_______/|/ \\___/ |/   \\__/|/     \\||/   \\__/   \\_/   of Interactive Fiction\n"
			+ "----------------------------------------------------------------------------------------------------\n"
			+ "\n"
			+ "{{OPTIONS}}"
			+ "\n";

	public String render(final TelnetRenderer renderer, Map<String, Object> model) {
		String welcome = buildWelcomeView(model);
		renderer.write(welcome, TelnetRenderer.PURPLE);
		return renderer.read();
	}

	private String buildWelcomeView(Map<String, Object> model) {
		String welcomeView = welcomeTemplate;

		if (model != null && model.containsKey("options")) {

			Object optionsObject = model.get("options");
			if (optionsObject instanceof List) {
				StringBuilder optionsBuilder = buildOptionsList(optionsObject);
				welcomeView = welcomeView.replace("{{OPTIONS}}", optionsBuilder.toString());
			}
		}

		return welcomeView;
	}

	private StringBuilder buildOptionsList(Object optionsObject) {
		StringBuilder optionsBuilder = new StringBuilder();
		List<String> optionList = (List<String>) optionsObject;

		int index = 1;
		for (String option : optionList) {
			optionsBuilder
					.append("    [").append(index++).append("] ")
					.append(option).append("\n");
		}

		return optionsBuilder;
	}
}
