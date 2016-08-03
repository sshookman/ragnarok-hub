package golem.mud.hub.command;

import golem.mud.hub.client.SessionContext;
import golem.mud.hub.rendering.TelnetRenderer;

public class StoryCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public StoryCommand(final SessionContext context, final String command) {
        this.renderer = context.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing Story Command: ");
        renderer.write(command);
        renderer.write("\n\n");
    }
}
