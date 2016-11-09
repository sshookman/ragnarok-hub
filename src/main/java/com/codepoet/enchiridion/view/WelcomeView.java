package com.codepoet.enchiridion.view;

import com.codepoet.enchiridion.render.Renderer;
import java.util.List;
import java.util.Map;

public class WelcomeView {

	private static final String WELCOME_TEMPLATE = ""
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

	public static String render(final Renderer renderer, Map<String, Object> model) {
		String welcome = buildWelcomeView(model);
		renderer.write(welcome, Renderer.PURPLE);
		return renderer.prompt();
	}

	private static String buildWelcomeView(Map<String, Object> model) {
		String welcomeView = WELCOME_TEMPLATE;

		if (model != null && model.containsKey("options")) {

			Object optionsObject = model.get("options");
			if (optionsObject instanceof List) {
				StringBuilder optionsBuilder = buildOptionsList(optionsObject);
				welcomeView = welcomeView.replace("{{OPTIONS}}", optionsBuilder.toString());
			}
		}

		return welcomeView;
	}

	private static StringBuilder buildOptionsList(Object optionsObject) {
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
