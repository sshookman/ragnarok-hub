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
        renderer.write("\n\n");
    }
}
