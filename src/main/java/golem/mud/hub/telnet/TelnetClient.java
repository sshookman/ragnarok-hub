package golem.mud.hub.telnet;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.das.ConnectionManager;
import golem.mud.hub.das.PlayerDataService;
import golem.mud.hub.command.CommandInterpreter;
import golem.mud.hub.command.CommandInterface;
import golem.mud.hub.exception.CommandException;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

	private final TelnetRenderer renderer;
	private final PlayerDataService playerDS;
    private final Connection hubDatabase;

    private final TelnetSession session;

    public TelnetClient(final Socket socket) throws Exception {
        this.session = TelnetSession.instance(socket);
 		this.renderer = session.getRenderer();
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
            session.closeSession();
        }
    }

    private PlayerDO login() throws IOException {
        renderer.write("Enter Username: ");
        String username = renderer.read();
        PlayerDO player = playerDS.getPlayer(username);
        if (player != null) {
            renderer.write("Enter Password: ");
            if (!playerDS.authenticate(username, renderer.read())) {
                renderer.write("Invalid Password!\n\n");
                return login();
            }
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

        String commandString = "";
        while (!"quit".equals(commandString)) {
            renderer.write(player.getUsername());
            renderer.write(" > ");
            
            commandString = renderer.read();
            CommandInterface command = CommandInterpreter.getCommand(session, commandString);
            if (command != null) {
                try {
                    command.execute();
                } catch (CommandException exception) {
                    renderer.write("\n");
                    renderer.write(exception.getMessage());
                    renderer.write("\n\n");
                }
            } else {
                renderer.write("\nInvalid Command\n\n");
            }
        }
    }

    private void showWelcome() {
        renderer.write("================================\n");
        renderer.write("Welcome to the Dragonfly Library\n");
        renderer.write("================================\n\n");
    }
}

