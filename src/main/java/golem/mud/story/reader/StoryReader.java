package golem.mud.story.reader;

import golem.mud.common.exception.CommandException;
import golem.mud.common.telnet.TelnetRenderer;
import golem.mud.common.telnet.TelnetSession;
import golem.mud.story.action.AbstractAction;
import golem.mud.story.command.CommandDictionary;
import golem.mud.story.command.CommandParser;
import golem.mud.story.das.StoryAggregateDataService;
import golem.mud.story.das.model.DisplayComponentDO;
import golem.mud.story.das.model.PathComponentDO;
import java.util.List;

public class StoryReader {

	private final TelnetRenderer renderer;
	private final StoryAggregateDataService services;
	private final CommandDictionary commandDictionary;
	private final StoryContext context;
	private final CommandParser commandParser;

	public StoryReader(TelnetSession session, String storyPath) throws Exception {
		this.renderer = session.getRenderer();
		this.services = new StoryAggregateDataService(storyPath);
		this.commandDictionary = new CommandDictionary();
		this.context = new StoryContext(renderer, services);
		this.commandParser = new CommandParser(this.commandDictionary);
	}

	public void start() {
		renderer.write("[N]EW GAME", TelnetRenderer.GREEN);
		renderer.endl(1);
		renderer.write("[L]OAD GAME", TelnetRenderer.YELLOW);
		renderer.endl(2);

		renderer.write(" > ");
		renderer.read();

		play(services.getStartingLocation());
	}

	private void play(DisplayComponentDO location) {

		while (true) {
			try {
				loadContextualData(location.getEntityId());

				renderer.write(location.getMessage());
				renderer.endl(2);
				renderer.write(" > ");

				String command = renderer.read();
				if (!evaluateCommand(command)) {
					break;
				}

				DisplayComponentDO newLocation = services.getCurrentLocation();
				location = (newLocation != null) ? newLocation : location;

			} catch (CommandException exception) {
				renderer.write(exception.getMessage());
				renderer.endl(2);
			} catch (Exception exception) {
				renderer.write("[DEBUG] " + exception.getMessage());
				renderer.endl(2);
			}
		}
	}

	private void loadContextualData(Integer entityId) {
		PathComponentDO search = new PathComponentDO();
		search.setEntityId(entityId);

		List<PathComponentDO> paths = services.pathService.read(search.toMap());
		commandDictionary.addContextuals(paths);
	}

	private boolean evaluateCommand(String command) {
		AbstractAction action = commandParser.evaluate(command);
		if (action != null) {
			action.execute(context);
			return true;
		} else {
			return false;
		}
	}
}
