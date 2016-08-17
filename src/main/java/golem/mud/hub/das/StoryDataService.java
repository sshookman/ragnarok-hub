package golem.mud.hub.das;

import java.sql.Connection;

import golem.mud.hub.das.model.StoryDO;

public class StoryDataService extends AbstractDataService<StoryDO> {

	public StoryDataService(final Connection story) {
		super(new StoryDO(), story);
	}
}
