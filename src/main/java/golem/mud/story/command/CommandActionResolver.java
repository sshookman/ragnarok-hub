package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;
import golem.mud.story.action.AbstractAction;
import golem.mud.story.action.MoveDirectionAction;
import golem.mud.story.action.MoveNamedAction;

public class CommandActionResolver {

	public static AbstractAction execute(String command, CommandType type) {

		switch (type) {
			case MOVEMENT_NAMED:
				return new MoveNamedAction(command);
			case MOVEMENT_DIRECTIONAL:
				return new MoveDirectionAction(command);
			case OBJECT_PICKUP:
			case QUIT:
			default:
				return null;
		}
	}
}
