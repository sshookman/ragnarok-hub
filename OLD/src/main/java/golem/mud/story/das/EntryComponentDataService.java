package golem.mud.story.das;

import java.sql.Connection;

import golem.mud.story.das.model.EntryComponentDO;
import golem.mud.common.das.AbstractDataService;

public class EntryComponentDataService extends AbstractDataService<EntryComponentDO> {

	public EntryComponentDataService(final Connection story) {
		super(new EntryComponentDO(), story);
	}
}
