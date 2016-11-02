package golem.mud.hub.view;

import golem.mud.common.telnet.TelnetRenderer;
import golem.mud.common.telnet.TelnetSession;
import java.util.List;
import java.util.Map;

public class WelcomeView {

	private final TelnetRenderer renderer;
	//TODO: [SEAN] Read from template file
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

	public WelcomeView(TelnetSession session) {
		renderer = session.getRenderer();
	}

	public void welcome(Map<String, Object> model) {
		renderer.write(buildWelcomeView(model));
	}

	public String buildWelcomeView(Map<String, Object> model) {

		String welcomeView = welcomeTemplate;

		if (model != null && model.containsKey("options")) {

			Object optionsObject = model.get("options");
			if (optionsObject instanceof List) {

				StringBuilder optionsBuilder = new StringBuilder();
				List<String> optionList = (List<String>) optionsObject;
				int index = 1;

				for (String option : optionList) {
					optionsBuilder
							.append("    [").append(index++).append("] ")
							.append(option).append("\n");
				}

				welcomeView = welcomeView.replace("{{OPTIONS}}", optionsBuilder.toString());
			}
		}

		return welcomeView;
	}
}
