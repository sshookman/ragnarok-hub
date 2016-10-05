package golem.mud.story.action;

import golem.mud.story.das.PathComponentDataService;
import golem.mud.story.das.StateDataService;
import golem.mud.story.das.model.PathComponentDO;
import golem.mud.story.das.model.StateDO;
import golem.mud.story.reader.StoryContext;
import java.util.List;

public class MoveNamedAction extends AbstractAction {

	private final String pathName;

	public MoveNamedAction(String command) {
		this.pathName = command.split(" ")[2];
	}

	@Override
	public void execute(StoryContext context) {

		PathComponentDataService pathService = context.services.pathService;
		StateDataService stateService = context.services.stateService;

		PathComponentDO search = new PathComponentDO();
		search.setName(pathName);

		List<PathComponentDO> paths = pathService.read(search.toMap());

		if (paths != null && !paths.isEmpty()) {
			PathComponentDO path = paths.get(0);
			List<StateDO> states = stateService.read(new StateDO().toMap());

			if (states != null && !states.isEmpty()) {
				StateDO state = states.get(0);
				state.setLocationEntityId(path.getDestEntityId());
				stateService.update(state.getRowId(), state);
			}
		}
	}
}
