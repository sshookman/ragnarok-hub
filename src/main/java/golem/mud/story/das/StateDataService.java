package golem.mud.story.das;

import golem.mud.common.das.AbstractDataService;
import golem.mud.story.das.model.StateDO;
import java.sql.Connection;

public class StateDataService extends AbstractDataService<StateDO> {

	public StateDataService(final Connection story) {
		super(new StateDO(), story);
	}
}
