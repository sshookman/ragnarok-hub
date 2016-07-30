package golem.mud.hub.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import golem.mud.hub.model.PlayerDO;
import golem.mud.hub.das.PlayerDataService;

public class AuthenticationService {

    private final PlayerDataService playerDataService;

	public AuthenticationService(final Connection database) {
        playerDataService = new PlayerDataService(database);
	}

    public boolean authenticate(final String username, final String password) {
        PlayerDO player = getPlayer(username);
        return password.equals(player.getPassword());
    }

    public PlayerDO getPlayer(final String username) {
        Map<String, String> search = new HashMap<String, String>();
        search.put("username", username);
        List<PlayerDO> players = playerDataService.read(search);
        if (players != null && !players.isEmpty()) {
            return players.get(0);
        } else {
            return null;
        }
    }

    public boolean createPlayer(final PlayerDO player) {
        return playerDataService.create(player);
    }
}
