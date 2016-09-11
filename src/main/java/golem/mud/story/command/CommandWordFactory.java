package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.enums.CommandType;
import golem.mud.story.das.model.PathComponentDO;

import static golem.mud.common.enums.CommandType.*;

public class CommandWordFactory {

    public static List<CommandWord> buildCommandWords(PathComponentDO path) {
       List<CommandWord> words = new ArrayList<>(2); 
       words.add(new CommandWord(path.getName(), new Integer[]{3}, new CommandType[]{MOVEMENT_NAMED}, true));
       words.add(new CommandWord(path.getDirectionDisplay(), new Integer[]{2}, new CommandType[]{MOVEMENT_DIRECTIONAL}, true));

       return words;
    }
}
