package golem.mud.hub.command;

import golem.mud.hub.telnet.TelnetSession;

//TODO: SEAN - Find a way to test this code
public class CommandInterpreter {

    public static CommandInterface getCommand(final TelnetSession session, final String commandText) {

        CommandInterface command = null;

        String[] commandArray = commandText.split(" ");
        switch (commandArray[0].toUpperCase()) {
            case "AUTHOR":
                command = new AuthorCommand(session, commandText);
                break;
            case "CONFIG":
                command = new ConfigCommand(session, commandText);
                break;
            case "HELP":
                command = new HelpCommand(session, commandText);
                break;
            case "STORY":
                command = new StoryCommand(session, commandText);
                break;
        }

        return command;
    }
}
