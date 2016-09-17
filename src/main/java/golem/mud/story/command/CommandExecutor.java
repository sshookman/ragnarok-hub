package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;

public class CommandExecutor {

    public static void execute(String command, CommandType type) {

        switch (type) {
            case MOVEMENT_NAMED:
                break;
            case MOVEMENT_DIRECTIONAL:
                break;
            case OBJECT_PICKUP:
                break;
            default:
                break;
        }
    }
}
