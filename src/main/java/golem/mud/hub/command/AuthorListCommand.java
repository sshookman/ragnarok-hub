package golem.mud.hub.command;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class AuthorListCommand extends AbstractAuthorCommand {

    private final TelnetRenderer renderer;

    public AuthorListCommand(final TelnetSession session) {
        this.renderer = session.getRenderer();
    }

    public String getRegex() {
        return CMD + " (LS|LIST)";
    }

    public void execute() {
        renderer.write("Listing all Authors");
    }
}
