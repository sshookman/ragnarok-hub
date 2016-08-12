package golem.mud.hub.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.hub.telnet.TelnetSession;

public class CommandFactory {

    private TelnetSession session;

    public CommandFactory(final TelnetSession session) {
        this.session = session;
    }

    public CommandInterface buildCommand(final String commandText) {

        if (session == null || commandText == null) {
            return null;
        }
        
        List<CommandInterface> commands = new ArrayList<>();
        commands.add(new AuthorListCommand(session));

        for (CommandInterface commandInterface : commands) {
            if (commandInterface.matches(commandText.toUpperCase())) {
                return commandInterface;
            }
        }

        return null;
    }
}
