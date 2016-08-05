package golem.mud.hub.command;

import golem.mud.hub.client.SessionContext;
import golem.mud.hub.rendering.TelnetRenderer;

public class ConfigCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public ConfigCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing Config Command: ");
        renderer.write(command);
        renderer.write("\n");
 
        String[] commandArray = command.split(" ");
        switch (commandArray[1].toUpperCase()) {
            case "LS":
                list();
                break;
        }
    }

    private void list() {
        renderer.write("Listing all configs\n\n");
    }
}
