package golem.mud.hub.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.exception.CommandException;

public class ConfigCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;
    private final List<String> colors;

    public ConfigCommand(final TelnetSession session, final String command) {
        this.renderer = session.getRenderer();
        this.command = command;
        colors = new ArrayList<>();
        colors.add("RED");
        colors.add("BLUE");
        colors.add("GREEN");
    }

    public void execute() {
        String[] commandArray = command.split(" ");
        int wordCount = commandArray.length;
        if (wordCount >= 2) {
            switch (commandArray[1].toUpperCase()) {
                case "LS":
                    list();
                    break;
                case "TEXT-COLOR":
                    if (wordCount >= 3) {
                        setTextColor(commandArray[2]);
                    }
                    break;
                default:
                    throw new CommandException("Operation Not Found", command);
            }
        }
    }

    private void list() {
        renderer.write("\n=====ALL CONFIGS=====\n");
        renderer.write("text-color  : WHITE\n");
        renderer.write("bg-color    : BLACK\n");
        renderer.write("write-speed : INSTANT\n");
        renderer.write("auto-clear  : NO\n\n");
    }

    private void setTextColor(final String color) {
        if (colors.contains(color.toUpperCase())) {
            renderer.write("Setting Text Color : ");       
            renderer.write(color);
            renderer.write("\n\n");
        } else {
            renderer.write("Text Color ");
            renderer.write(color);
            renderer.write("Not Found\n\n");
        }
    }
}
