package golem.mud.hub.command;

import golem.mud.hub.exception.CommandException;

public interface CommandInterface {

    void execute() throws CommandException;
}
