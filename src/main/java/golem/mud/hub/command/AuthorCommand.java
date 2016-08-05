package golem.mud.hub.command;

import golem.mud.hub.client.SessionContext;
import golem.mud.hub.rendering.TelnetRenderer;

public class AuthorCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public AuthorCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing Author Command: ");
        renderer.write(command);
        renderer.write("\n");

        String[] commandArray = command.split(" ");
        switch (commandArray[1].toUpperCase()) {
            case "LS":
                list();
                break;
            default:
                details(commandArray[1]);
                break;
        }
    }

    private void list() {
        renderer.write("Listing all Authors\n\n");
    }

    private void details(final String name) {
        renderer.write("Details for Author " + name + "\n\n");
    }
}
