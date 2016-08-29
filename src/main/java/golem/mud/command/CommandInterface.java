package golem.mud.command;

import golem.mud.hub.exception.CommandException;

public interface CommandInterface {

    String getRegex();
    boolean matches(final String text);
    void execute() throws CommandException;
}
