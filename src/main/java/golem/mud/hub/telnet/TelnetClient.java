package golem.mud.hub.telnet;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.command.CommandFactory;
import golem.mud.hub.exception.CommandException;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());

    private final TelnetSession session;
	private final TelnetRenderer renderer;

    public TelnetClient(final Socket socket) throws Exception {
        this.session = TelnetSession.instance(socket);
 		this.renderer = session.getRenderer();
    }

    @Override
    public void run() {
        try {
            PlayerDO player = TelnetLogin.login(session);
            mainLoop(player);
        } catch (IOException exception) {
            LOGGER.severe(exception.getMessage());
        } finally {
            renderer.write("Thanks for Playing!\n");
            session.closeSession();
        }
    }

    private void mainLoop(final PlayerDO player) throws IOException {
        if (player == null) {
            return;
        }

        showWelcome();
        CommandFactory commandFactory = new CommandFactory(session);

        String commandString = "";
        while (!"quit".equals(commandString)) {
            renderer.write(player.getUsername());
            renderer.write(" > ");
            commandString = renderer.read();
            
            try {
                commandFactory.buildCommand(commandString).execute();
            } catch (CommandException exception) {
                renderer.write(exception.getMessage());
            }

            renderer.endl(2);
        }
    }

    private void showWelcome() {
        renderer.write("================================\n");
        renderer.write("Welcome to the Dragonfly Library\n");
        renderer.write("================================\n\n");
    }
}

