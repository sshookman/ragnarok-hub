package golem.mud.hub.command;

import golem.mud.hub.telnet.SessionContext;

public class CommandInterpreter {

    public static CommandInterface getCommand(final SessionContext context, final String commandText) {

        CommandInterface command = null;

        String[] commandArray = commandText.split(" ");
        switch (commandArray[0].toUpperCase()) {
            case "AUTHOR":
                command = new AuthorCommand(context, commandText);
                break;
            case "CONFIG":
                command = new ConfigCommand(context, commandText);
                break;
            case "HELP":
                command = new HelpCommand(context, commandText);
                break;
            case "STORY":
                command = new StoryCommand(context, commandText);
                break;
        }

        return command;
    }
}
