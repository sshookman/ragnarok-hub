package golem.mud.hub.das;

import java.sql.Connection;
import golem.mud.hub.model.Player;

public class PlayerDataService extends AbstractDataService<Player> {

	public PlayerDataService(final Connection story) {
		super(new Player(), story);
	}
}
