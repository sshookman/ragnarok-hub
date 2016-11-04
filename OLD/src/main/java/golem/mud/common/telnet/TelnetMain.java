package golem.mud.common.telnet;

import golem.mud.common.command.CommandResponse;
import golem.mud.common.exception.CommandException;
import golem.mud.hub.command.HubCommandFactory;
import golem.mud.hub.das.model.PlayerDO;
import golem.mud.story.reader.StoryReader;

public class TelnetMain {

	public static void mainLoop(final TelnetSession session) throws Exception {

		PlayerDO player = session.getPlayer();
		TelnetRenderer renderer = session.getRenderer();

		if (player == null) {
			return;
		}

		renderer.write("Welcome to\n");
		renderer.write("===================================\n");
		renderer.write("| The Dragonfly IF Telnet Library |\n");
		renderer.write("===================================\n\n");

		HubCommandFactory commandFactory = new HubCommandFactory(session);
		String commandString = "";

		while (!"quit".equals(commandString)) {
			renderer.write(player.getUsername());
			renderer.write(" > ");
			commandString = renderer.read();

			try {
				CommandResponse response = commandFactory.buildCommand(commandString).execute();
				handleResponse(session, response);
			} catch (CommandException exception) {
				renderer.write(exception.getMessage());
			}

			renderer.endl(2);
		}
	}

	private static void handleResponse(TelnetSession session, CommandResponse response) throws Exception {
		TelnetRenderer renderer = session.getRenderer();
		if (response != null && response.getData() != null) {
			renderer.write("Do you wish to play ");
			renderer.write(response.getStringData());
			renderer.write("? (y/n):");
			String play = renderer.read();
			if ("Y".equalsIgnoreCase(play) || "YES".equalsIgnoreCase(play)) {
				StoryReader reader = new StoryReader(session, response.getStringData());
				reader.start();
			}
		}
	}
}
