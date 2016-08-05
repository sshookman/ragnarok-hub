package golem.mud.hub.das;

import java.sql.Connection;
import golem.mud.hub.das.model.CommandDO;

public class CommandDataService extends AbstractDataService<CommandDO> {

	public CommandDataService(final Connection story) {
		super(new CommandDO(), story);
	}
}
