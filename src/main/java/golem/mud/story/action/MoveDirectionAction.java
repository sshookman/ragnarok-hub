package golem.mud.story.action;

import golem.mud.common.enums.Direction;
import golem.mud.story.das.PathComponentDataService;
import golem.mud.story.das.StateDataService;
import golem.mud.story.das.model.PathComponentDO;
import golem.mud.story.das.model.StateDO;
import golem.mud.story.reader.StoryContext;
import java.util.List;

public class MoveDirectionAction extends AbstractAction {

	private final String direction;

	public MoveDirectionAction(String command) {
		this.direction = command.split(" ")[1];
	}

	@Override
	public void execute(StoryContext context) {

		PathComponentDataService pathService = context.services.pathService;
		StateDataService stateService = context.services.stateService;

		List<StateDO> states = stateService.read(new StateDO().toMap());
		if (states != null && !states.isEmpty()) {
			StateDO state = states.get(0);

			PathComponentDO search = new PathComponentDO();
			search.setDirection(Direction.valueOf(direction.toUpperCase()));
			search.setEntityId(state.getLocationEntityId());

			List<PathComponentDO> paths = pathService.read(search.toMap());

			if (paths != null && !paths.isEmpty()) {
				PathComponentDO path = paths.get(0);
				state.setLocationEntityId(path.getDestEntityId());
				stateService.update(state.getRowId(), state);
			}
		}

	}
}
