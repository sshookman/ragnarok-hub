package golem.mud.story.action;

import golem.mud.common.enums.Direction;
import golem.mud.story.das.model.PathComponentDO;

public class MoveDirectionAction extends AbstractMoveAction {

	private final String direction;

	public MoveDirectionAction(String command) {
		this.direction = command.split(" ")[1];
	}

	@Override
	protected PathComponentDO getPathSearch() {
		PathComponentDO search = new PathComponentDO();
		search.setDirection(Direction.valueOf(direction.toUpperCase()));
		return search;
	}
}
