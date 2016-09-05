package golem.mud.story.das;

import java.sql.Connection;

import golem.mud.story.das.model.EntityDO;
import golem.mud.common.das.AbstractDataService;

public class EntityDataService extends AbstractDataService<EntityDO> {

	public EntityDataService(final Connection story) {
		super(new EntityDO(), story);
	}
}
