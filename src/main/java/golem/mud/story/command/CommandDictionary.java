package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.enums.CommandType;
import golem.mud.story.das.model.PathComponentDO;

import static golem.mud.common.enums.CommandType.*;

public class CommandDictionary {

    private List<CommandWord> global;
    private List<CommandWord> context;

    public CommandDictionary() {
        global = new ArrayList<>();
        context = new ArrayList<>();
        addGlobals();
    }

    public List<CommandWord> getGlobal() {
        return global;
    }

    public List<CommandWord> getContext() {
        return context;
    }

    public void clearContext() {
        context = new ArrayList<>();
    }

    public void addContext(List<PathComponentDO> paths) {
        paths.stream()
            .forEach((path) -> context.addAll(CommandWordFactory.buildCommandWords(path)));
    }

    private void addGlobals() {
        //Movement Globals
        global.add(new CommandWord("go", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        global.add(new CommandWord("walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        global.add(new CommandWord("run", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        global.add(new CommandWord("move", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false));
        global.add(new CommandWord("to", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
        global.add(new CommandWord("toward", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false));
    }
}
