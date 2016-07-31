package golem.mud.hub.das;

import java.util.Map;
import java.util.List;
import java.sql.Connection;
import golem.mud.hub.model.PlayerDO;

public class PlayerDataService extends AbstractDataService<PlayerDO> {

	public PlayerDataService(final Connection story) {
		super(new PlayerDO(), story);
	}

    public boolean authenticate(final String username, final String password) {
        PlayerDO player = getPlayer(username);
        return player != null && password != null && password.equals(player.getPassword());
    }

    public PlayerDO getPlayer(final String username) {
        PlayerDO player = new PlayerDO();
        player.setUsername(username);
        Map<String, String> search = player.toMap();
        List<PlayerDO> players = read(search);
        if (!players.isEmpty()) {
            return players.get(0);
        } else {
            return null;
        }
    }
}
