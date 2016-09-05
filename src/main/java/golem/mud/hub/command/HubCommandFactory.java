package golem.mud.hub.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.exception.CommandException;
import golem.mud.common.command.CommandInterface;
import golem.mud.hub.telnet.TelnetSession;

public class HubCommandFactory {

    private TelnetSession session;

    public HubCommandFactory(final TelnetSession session) {
        this.session = session;
    }

    public CommandInterface buildCommand(final String commandText) {

        if (session == null || commandText == null) {
            throw new CommandException("Invalid Command/Session", commandText);
        }
        
        List<CommandInterface> commands = new ArrayList<>();
        commands.add(new CommandListCommand(session));
        commands.add(new AuthorListCommand(session));
        commands.add(new StoryListCommand(session));
        commands.add(new StoryPlayCommand(session));

        for (CommandInterface commandInterface : commands) {
            if (commandInterface.matches(commandText.toUpperCase())) {
                return commandInterface;
            }
        }

        throw new CommandException("Command Not Found", commandText);
    }
}
