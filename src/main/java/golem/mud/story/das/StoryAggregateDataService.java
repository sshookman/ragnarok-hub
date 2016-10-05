package golem.mud.story.das;

import golem.mud.common.das.ConnectionManager;
import golem.mud.story.das.model.*;
import java.sql.Connection;
import java.util.List;

public class StoryAggregateDataService {

	public StateDataService stateService;
	public EntityDataService entityService;
	public EntryComponentDataService entryService;
	public DisplayComponentDataService displayService;
	public PathComponentDataService pathService;

	public StoryAggregateDataService(String storyPath) throws Exception {
		Connection story = ConnectionManager.establishConnection(storyPath);
		this.stateService = new StateDataService(story);
		this.entityService = new EntityDataService(story);
		this.entryService = new EntryComponentDataService(story);
		this.displayService = new DisplayComponentDataService(story);
		this.pathService = new PathComponentDataService(story);
	}

	public DisplayComponentDO getStartingLocation() {

		EntryComponentDO entry = new EntryComponentDO();
		entry.setPosition(1);
		List<EntryComponentDO> entries = entryService.read(entry.toMap());
		if (entries != null && !entries.isEmpty()) {
			entry = entries.get(0);
		} else {
			return null;
		}

		DisplayComponentDO display = new DisplayComponentDO();
		display.setEntityId(entry.getEntityId());
		List<DisplayComponentDO> displays = displayService.read(display.toMap());
		if (displays != null && !displays.isEmpty()) {
			return displays.get(0);
		} else {
			return null;
		}
	}

	public DisplayComponentDO getCurrentLocation() {
		DisplayComponentDO location = null;
		List<StateDO> displays = stateService.read(new StateDO().toMap());

		if (displays != null && !displays.isEmpty()) {
			DisplayComponentDO search = new DisplayComponentDO();
			search.setEntityId(displays.get(0).getLocationEntityId());
			List<DisplayComponentDO> displayComponents = displayService.read(search.toMap());

			if (displayComponents != null && !displayComponents.isEmpty()) {
				location = displayComponents.get(0);
			}
		}

		return location;
	}
}
