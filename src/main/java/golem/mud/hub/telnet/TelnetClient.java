package golem.mud.hub.telnet;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.das.ConnectionManager;
import golem.mud.hub.das.PlayerDataService;
import golem.mud.hub.command.CommandInterpreter;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

	private final TelnetRenderer renderer;
	private final PlayerDataService playerDS;
    private final Connection hubDatabase;

    private final SessionContext sessionContext;

    public TelnetClient(final Socket socket) throws Exception {
        this.sessionContext = SessionContext.instance(socket);
 		this.renderer = sessionContext.getRenderer();
        this.hubDatabase = ConnectionManager.establishConnection(HUB_DB_PATH);
		this.playerDS = new PlayerDataService(hubDatabase);
    }

    @Override
    public void run() {
        try {
            PlayerDO player = login();
            mainLoop(player);
        } catch (IOException exception) {
            LOGGER.severe(exception.getMessage());
        } finally {
            renderer.write("Thanks for Playing!\n");
            sessionContext.closeSession();
        }
    }

    private PlayerDO login() throws IOException {
        renderer.write("Enter Username: ");
        String username = renderer.read();
        PlayerDO player = playerDS.getPlayer(username);
        if (player != null) {
            renderer.write("Enter Password: ");
            playerDS.authenticate(username, renderer.read());
        } else {
            renderer.write("User does not exist. Create new user? (y/n): ");
            if (!"y".equalsIgnoreCase(renderer.read())) {
                return login();
            }

            renderer.write("Create password: ");

            player = new PlayerDO();
            player.setUsername(username);
            player.setPassword(renderer.read());
            if (playerDS.create(player)) {
                renderer.write("Created Successfully!\n\n");
            } else {
                renderer.write("Unable to Create Player\n\n");
                throw new RuntimeException("Failed to Create Player");
            }
        }

        return player;
    }

    private void mainLoop(final PlayerDO player) throws IOException {
        if (player == null) {
            return;
        }

        showWelcome();

        String command = "";
        while (!"quit".equals(command)) {
            renderer.write(player.getUsername());
            renderer.write(" > ");
            
            command = renderer.read();
            CommandInterpreter.getCommand(sessionContext, command).execute();
        }
    }

    private void showWelcome() {
        renderer.write("================================\n");
        renderer.write("Welcome to the Dragonfly Library\n");
        renderer.write("================================\n\n");
    }
}

