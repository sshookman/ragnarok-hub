package golem.mud.hub.command;

import java.util.List;
import java.util.HashMap;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.das.AuthorDataService;
import golem.mud.hub.das.model.AuthorDO;

public class AuthorListCommand extends AbstractAuthorCommand {

    private final TelnetRenderer renderer;

    private final AuthorDataService authorDataService;

    public AuthorListCommand(final TelnetSession session) {
        this.renderer = session.getRenderer();
        this.authorDataService = new AuthorDataService(session.getConnection());
    }

    public String getRegex() {
        return CMD + " (LS|LIST)";
    }

    public void execute() {
        renderer.write("All Authors:");
        List<AuthorDO> authors = authorDataService.read(new HashMap<>());
        for (AuthorDO author : authors) {
            renderer.endl(1);
            renderer.write(author.getUsername());
        }
    }
}
