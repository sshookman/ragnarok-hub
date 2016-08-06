package golem.mud.hub.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.hub.telnet.SessionContext;
import golem.mud.hub.telnet.TelnetRenderer;

public class ConfigCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;
    private final List<String> colors;

    public ConfigCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
        colors = new ArrayList<>();
        colors.add("RED");
        colors.add("BLUE");
        colors.add("GREEN");
    }

//TODO: SEAN - Abstract this logic out - all commands will be doing roughly the same thing
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

            }
        }
    }

    private void list() {
        renderer.write("All Configs:\n\n");
        renderer.write("text-color  : WHITE");
        renderer.write("bg-color    : BLACK");
        renderer.write("write-speed : INSTANT");
        renderer.write("auto-clear  : NO");
    }

    private void setTextColor(final String color) {
        if (colors.contains(color)) {
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
