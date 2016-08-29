package golem.mud.hub.telnet;

import java.io.IOException;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.command.HubCommandFactory;
import golem.mud.hub.exception.CommandException;

public class TelnetMain {

    public static void mainLoop(final TelnetSession session) throws IOException {

        PlayerDO player = session.getPlayer();
        TelnetRenderer renderer = session.getRenderer();

        if (player == null) {
            return;
        }

        renderer.write("================================\n");
        renderer.write("Welcome to the Dragonfly Library\n");
        renderer.write("================================\n\n");

        HubCommandFactory commandFactory = new HubCommandFactory(session);
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
}
