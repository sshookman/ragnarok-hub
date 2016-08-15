package golem.mud.hub.telnet;

import java.io.IOException;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.das.PlayerDataService;

public class TelnetLogin {

    public static PlayerDO login(final TelnetSession session) throws IOException {
        TelnetRenderer renderer = session.getRenderer();
		PlayerDataService playerDataService = new PlayerDataService(session.getConnection());

        renderer.write("Enter Username: ");
        String username = renderer.read();
        PlayerDO player = playerDataService.getPlayer(username);
        if (player != null) {
            renderer.write("Enter Password: ");
            if (!playerDataService.authenticate(username, renderer.read())) {
                renderer.write("Invalid Password!\n\n");
                return login(session);
            }
        } else {
            renderer.write("User does not exist. Create new user? (y/n): ");
            if (!"y".equalsIgnoreCase(renderer.read())) {
                return login(session);
            }

            renderer.write("Create password: ");

            player = new PlayerDO();
            player.setUsername(username);
            player.setPassword(renderer.read());
            if (playerDataService.create(player)) {
                renderer.write("Created Successfully!\n\n");
            } else {
                renderer.write("Unable to Create Player\n\n");
                throw new RuntimeException("Failed to Create Player");
            }
        }

        return player;
    }
}
