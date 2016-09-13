package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.enums.CommandType;
import golem.mud.story.das.model.PathComponentDO;

import static golem.mud.common.enums.CommandType.*;

public class CommandDictionary {

    private List<CommandWord> globals;
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

    public List<CommandWord> search(String word, Integer position, CommandType[] types) {

        return null;
    }

    public void clearContext() {
        contextuals = new ArrayList<>();
    }

    public void addContextuals(List<PathComponentDO> paths) {
        paths.stream()
            .forEach((path) -> contextuals.addAll(CommandWordFactory.buildCommandWords(path)));
    }

    private void addGlobals() {
        //Movement Globals
        globals.add(new CommandWord("go", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        globals.add(new CommandWord("walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        globals.add(new CommandWord("run", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        globals.add(new CommandWord("move", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        globals.add(new CommandWord("to", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
        globals.add(new CommandWord("toward", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
    }
}
