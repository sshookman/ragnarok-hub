package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;
import golem.mud.story.das.model.PathComponentDO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandDictionary {

	private final List<CommandWord> globals;
	private List<CommandWord> contextuals;

	public CommandDictionary() {
		globals = new ArrayList<>();
		contextuals = new ArrayList<>();
		addGlobals();
	}

	public List<CommandWord> getGlobals() {
		return globals;
	}

	public List<CommandWord> getContextuals() {
		return contextuals;
	}

	public List<CommandWord> search(String word, Integer position, List<CommandType> types) {
		List<CommandWord> results = new ArrayList<>();
		results.addAll(search(globals, word, position, types));
		results.addAll(search(contextuals, word, position, types));

		return results;
	}

	private List<CommandWord> search(List<CommandWord> commandWords, String word, Integer position, List<CommandType> types) {
		return commandWords.stream()
				.filter((commandWord) -> commandWord.matches(word, position, types))
				.collect(Collectors.toList());
	}

	public void clearContext() {
		contextuals = new ArrayList<>();
	}

	public void addContextuals(List<PathComponentDO> paths) {
		paths.stream()
				.forEach((path) -> contextuals.addAll(CommandWordFactory.buildCommandWords(path)));
	}

	private void addGlobals() {
		globals.add(new CommandWord("go", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
		globals.add(new CommandWord("walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
		globals.add(new CommandWord("run", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
		globals.add(new CommandWord("move", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
		globals.add(new CommandWord("to", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
		globals.add(new CommandWord("toward", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
	}
}
