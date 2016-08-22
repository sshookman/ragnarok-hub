package golem.mud.hub.command;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class CommandListCommand extends AbstractCommandCommand {

    private final TelnetRenderer renderer;

    public CommandListCommand(final TelnetSession session) {
        this.renderer = session.getRenderer();
    }

    public String getRegex() {
        return CMD + " (LS|LIST)";
    }

    public void execute() {
        renderer.write("Commands:");
        renderer.endl(1);
        renderer.write("COMMAND LIST");
        renderer.endl(1);
        renderer.write("AUTHOR LIST");
        renderer.endl(1);
        renderer.write("STORY LIST");
    }
}
