package golem.mud.story.das;

import java.sql.Connection;

import golem.mud.story.das.model.DisplayComponentDO;
import golem.mud.common.das.ConnectionManager;

public class StoryAggregateDataService {

    public EntityDataService entityService;
    public DisplayComponentDataService displayService;
    public PathComponentDataService pathService;

    public StoryAggregateDataService(String storyPath) throws Exception {
        Connection story = ConnectionManager.establishConnection(storyPath); 
        this.entityService = new EntityDataService(story);
        this.displayService = new DisplayComponentDataService(story);
        this.pathService = new PathComponentDataService(story);
    }

    public DisplayComponentDO getStartingPoint() {

        //TODO: Need a good way of marking an entity as the starting point
        //Grab starting point and obtain entityId
        //Get display data based off given entityId
        return null;
    }
}
