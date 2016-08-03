package golem.mud.hub.command;

import golem.mud.hub.client.SessionContext;
import golem.mud.hub.rendering.TelnetRenderer;

public class HelpCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public HelpCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing command: ");
        renderer.write(command);
        renderer.write("\n\n");
    }
}
