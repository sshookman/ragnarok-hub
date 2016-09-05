package golem.mud.hub.telnet;

import java.io.IOException;

import golem.mud.hub.das.model.PlayerDO;
import golem.mud.hub.command.HubCommandFactory;
import golem.mud.common.exception.CommandException;
import golem.mud.common.command.CommandResponse;

public class TelnetMain {

    public static void mainLoop(final TelnetSession session) throws IOException {

        PlayerDO player = session.getPlayer();
        TelnetRenderer renderer = session.getRenderer();

        if (player == null) {
            return;
        }

        renderer.write("Welcome to\n");
        renderer.write("===================================\n");
        renderer.write("| The Dragonfly IF Telnet Library |\n");
        renderer.write("===================================\n\n");

        HubCommandFactory commandFactory = new HubCommandFactory(session);
        String commandString = "";

        while (!"quit".equals(commandString)) {
            renderer.write(player.getUsername());
            renderer.write(" > ");
            commandString = renderer.read();
            
            try {
                CommandResponse response = commandFactory.buildCommand(commandString).execute();
                handleResponse(renderer, response);
            } catch (CommandException exception) {
                renderer.write(exception.getMessage());
            }

            renderer.endl(2);
        }
    }

    private static void handleResponse(TelnetRenderer renderer, CommandResponse response) throws IOException {
        if (response != null && response.getData() != null) {
            renderer.write("Do you wish to play ");
            renderer.write(response.getStringData());
            renderer.write("? (y/n):");
            String play = renderer.read();
            if ("Y".equalsIgnoreCase(play) || "YES".equalsIgnoreCase(play)) {
                renderer.write("START GAME");
            }
        }
    }
}
