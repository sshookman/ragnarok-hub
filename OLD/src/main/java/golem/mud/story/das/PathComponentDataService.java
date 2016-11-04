package golem.mud.story.das;

import java.sql.Connection;

import golem.mud.story.das.model.PathComponentDO;
import golem.mud.common.das.AbstractDataService;

public class PathComponentDataService extends AbstractDataService<PathComponentDO> {

	public PathComponentDataService(final Connection story) {
		super(new PathComponentDO(), story);
	}
}
