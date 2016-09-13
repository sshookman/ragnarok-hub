package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;

public class CommandParser {


    public static void evaluate(String command) {
        evaluate(command, 1, new CommandType[]{});
    }

    public static void evaluate(String command, Integer position, CommandType[] types) {
        //TODO: Implement command evaluation recusively
    }
}
