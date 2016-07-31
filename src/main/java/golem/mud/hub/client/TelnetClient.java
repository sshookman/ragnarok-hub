package golem.mud.hub.client;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.rendering.TelnetRenderer;
import golem.mud.hub.util.ConnectionUtil;
import golem.mud.hub.model.PlayerDO;
import golem.mud.hub.das.PlayerDataService;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

    private final Socket socket;
	private final TelnetRenderer renderer;
	private final PlayerDataService playerDS;

    private final Connection hubDatabase;

    public TelnetClient(final Socket socket) throws IOException, Exception {
        this.socket = socket;
 		this.renderer = new TelnetRenderer(socket);
        this.hubDatabase = ConnectionUtil.establishConnection(HUB_DB_PATH);
		this.playerDS = new PlayerDataService(hubDatabase);
    }

    @Override
    public void run() {
        login();
    }

    private void login() {
        try {
            renderer.write("================================\n");
            renderer.write("Welcome to the Dragonfly Mud-Hub\n");
            renderer.write("================================\n\n");

            renderer.write("Enter Username: ");
            String username = renderer.read();
            PlayerDO player = playerDS.getPlayer(username);
            if (player != null) {
                renderer.write("Enter Password: ");
                playerDS.authenticate(username, renderer.read());
            } else {
                renderer.write("User does not exist. Create new user? (y/n): ");
                if (!"y".equalsIgnoreCase(renderer.read())) {
                    login();
                    return;
                }

                renderer.write("Create password: ");

                player = new PlayerDO();
                player.setUsername(username);
                player.setPassword(renderer.read());
                if (playerDS.createPlayer(player)) {
                    renderer.write("Created Successfully!\n\n");
                } else {
                    renderer.write("Unable to Create Player\n\n");
                }
            }
            
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
		} finally {
			try {
                renderer.write("Thanks for Playing!\n");
				socket.close();
			} catch (IOException exception) {
				LOGGER.info("Failed to close socket");
			}
		}
    }
}

