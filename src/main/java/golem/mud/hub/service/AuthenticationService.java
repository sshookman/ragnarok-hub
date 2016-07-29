package golem.mud.hub.service;

import java.io.IOException;
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

	public boolean exists(final String username) throws IOException {
        Map<String, String> search = new HashMap<String, String>();
        search.put("username", username);
        List<PlayerDO> players = playerDataService.read(search);
        return players != null && !players.isEmpty();
	}
}
