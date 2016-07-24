package golem.mud.hub.das;

import java.sql.Connection;
import golem.mud.hub.model.PlayerDO;

public class PlayerDataService extends AbstractDataService<PlayerDO> {

	public PlayerDataService(final Connection story) {
		super(new PlayerDO(), story);
	}
}
