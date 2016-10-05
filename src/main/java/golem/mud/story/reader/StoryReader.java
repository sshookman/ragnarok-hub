package golem.mud.story.reader;

import golem.mud.common.exception.CommandException;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.telnet.TelnetSession;
import golem.mud.story.action.AbstractAction;
import golem.mud.story.command.CommandDictionary;
import golem.mud.story.command.CommandParser;
import golem.mud.story.das.StoryAggregateDataService;
import golem.mud.story.das.model.DisplayComponentDO;
import java.io.IOException;

public class StoryReader {

	private final StoryContext context;
	private final CommandDictionary commandDictionary;
	private final CommandParser commandParser;

	public StoryReader(TelnetSession session, String storyPath) throws Exception {
		TelnetRenderer renderer = session.getRenderer();
		StoryAggregateDataService services = new StoryAggregateDataService(storyPath);

		this.commandDictionary = new CommandDictionary();
		this.context = new StoryContext(renderer, services);
		this.commandParser = new CommandParser(this.commandDictionary);
	}

	public void play() throws IOException {

		DisplayComponentDO location = context.services.getStartingLocation();
		String command = "";

		while (!"quit".equals(command)) {
			try {
				context.renderer.write(location.getMessage());
				context.renderer.endl(2);
				context.renderer.write(" > ");
				command = context.renderer.read();

				AbstractAction action = commandParser.evaluate(command);
				action.execute(context);

				DisplayComponentDO newLocation = context.services.getCurrentLocation();
				location = (newLocation != null) ? newLocation : location;
			} catch (IOException | CommandException exception) {
				context.renderer.write("ERROR: " + exception.getMessage());
			}
		}
	}
}
