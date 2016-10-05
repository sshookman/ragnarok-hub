package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;
import golem.mud.story.action.AbstractAction;
import golem.mud.story.action.MoveNamedAction;

public class CommandExecutor {

	public static AbstractAction execute(String command, CommandType type) {

		switch (type) {
			case MOVEMENT_NAMED:
				return new MoveNamedAction(command);
			case MOVEMENT_DIRECTIONAL:
			case OBJECT_PICKUP:
			default:
				return null;
		}
	}
}
