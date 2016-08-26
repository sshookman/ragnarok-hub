package golem.mud.hub.das;

import java.sql.Connection;
import golem.mud.hub.das.model.ConfigDO;

public class ConfigDataService extends AbstractDataService<ConfigDO> {

	public ConfigDataService(final Connection story) {
		super(new ConfigDO(), story);
	}
}
