package golem.mud.exception;

public class CommandException extends RuntimeException {

    private String command;

    public CommandException(final String message, final String command) {
        super(message);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
