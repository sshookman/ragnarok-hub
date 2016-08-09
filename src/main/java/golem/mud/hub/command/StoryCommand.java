package golem.mud.hub.command;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class StoryCommand implements CommandInterface {

    private final TelnetRenderer renderer;
    private final String command;

    public StoryCommand(final TelnetSession session, final String command) {
        this.renderer = session.getRenderer();
        this.command = command;
    }

    public void execute() {
        renderer.write("Executing Story Command: ");
        renderer.write(command);
        renderer.write("\n\n");
    }
}
