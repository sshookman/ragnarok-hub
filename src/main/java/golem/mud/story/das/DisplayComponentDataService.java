package golem.mud.story.das;

import java.sql.Connection;

import golem.mud.story.das.model.DisplayComponentDO;
import golem.mud.common.das.AbstractDataService;

public class DisplayComponentDataService extends AbstractDataService<DisplayComponentDO> {

	public DisplayComponentDataService(final Connection story) {
		super(new DisplayComponentDO(), story);
	}
}
