package golem.mud.story.das;

import java.util.List;
import java.sql.Connection;

import golem.mud.common.das.ConnectionManager;
import golem.mud.story.das.model.*;

public class StoryAggregateDataService {

    public EntityDataService entityService;
    public EntryComponentDataService entryService;
    public DisplayComponentDataService displayService;
    public PathComponentDataService pathService;

    public StoryAggregateDataService(String storyPath) throws Exception {
        Connection story = ConnectionManager.establishConnection(storyPath); 
        this.entityService = new EntityDataService(story);
        this.entryService = new EntryComponentDataService(story);
        this.displayService = new DisplayComponentDataService(story);
        this.pathService = new PathComponentDataService(story);
    }

    public DisplayComponentDO getStartingPoint() {

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
}
