package golem.mud.story.action;

import golem.mud.story.das.model.PathComponentDO;

public class MoveNamedAction extends AbstractMoveAction {

	private final String pathName;

	public MoveNamedAction(String command) {
		this.pathName = command.split(" ")[2];
	}

	@Override
	protected PathComponentDO getPathSearch() {
		PathComponentDO search = new PathComponentDO();
		search.setName(pathName);
		return search;
	}
}
