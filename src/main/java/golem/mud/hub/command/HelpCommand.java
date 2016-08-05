package golem.mud.hub.command;

import golem.mud.hub.telnet.SessionContext;
import golem.mud.hub.telnet.TelnetRenderer;

public class HelpCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public HelpCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing Help Command: ");
        renderer.write(command);
        renderer.write("\n");
 
        String[] commandArray = command.split(" ");
        switch (commandArray[1].toUpperCase()) {
            case "COMMANDS":
                commands();
                break;
            case "STORY":
                story();
                break;
            default:
                all();
                break;
        }
    }

    private void commands() {
        renderer.write("How to use Commands\n\n");
    }

    private void story() {
        renderer.write("All about Stories\n\n");
    }

    private void all() {
        renderer.write("All help sections\n\n");
    }
}
