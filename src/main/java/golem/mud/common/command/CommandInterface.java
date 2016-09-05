package golem.mud.common.command;

import golem.mud.common.exception.CommandException;

public interface CommandInterface {

    String getRegex();
    boolean matches(final String text);
    CommandResponse execute() throws CommandException;
}
