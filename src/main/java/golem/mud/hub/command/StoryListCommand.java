package golem.mud.hub.command;

import java.util.List;
import java.util.HashMap;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.das.StoryDataService;
import golem.mud.hub.das.model.StoryDO;
import golem.mud.common.command.CommandResponse;

public class StoryListCommand extends AbstractStoryCommand {

    private final TelnetRenderer renderer;
    private final StoryDataService storyDataService;

    public StoryListCommand(final TelnetSession session) {
        this.renderer = session.getRenderer();
        this.storyDataService = new StoryDataService(session.getConnection());
    }

    public String getRegex() {
        return CMD + " (LS|LIST)";
    }

    public CommandResponse execute() {
        renderer.write("Stories:");
        List<StoryDO> stories = storyDataService.read(new HashMap<>());
        for (StoryDO story : stories) {
            renderer.endl(1);
            renderer.write(story.getName());
        }
        return new CommandResponse(null);
    }
}
