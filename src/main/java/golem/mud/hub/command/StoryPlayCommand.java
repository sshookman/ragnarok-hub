package golem.mud.hub.command;

import java.util.List;
import java.util.HashMap;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.das.StoryDataService;
import golem.mud.hub.das.model.StoryDO;

public class StoryPlayCommand extends AbstractStoryCommand {

    private final TelnetRenderer renderer;
    private final StoryDataService storyDataService;
    private String command;

    public StoryPlayCommand(final TelnetSession session) {
        this.renderer = session.getRenderer();
        this.storyDataService = new StoryDataService(session.getConnection());
    }

    public String getRegex() {
        return CMD + " PLAY .+";
    }

    @Override
    public boolean matches(final String text) {
        boolean isMatch = super.matches(text);
        this.command = text;
        return isMatch;
    }

    public void execute() {
        String[] parts = command.split(" ");
        String story = "";
        for (int x = 2; x < parts.length; x++) {
            if (x != 2) {
                story += " ";
            }
            story += parts[x];
        }
        renderer.write("Searching for Story: " + story);
        List<StoryDO> stories = storyDataService.read(new HashMap<>());
        for (StoryDO s : stories) {
            renderer.endl(1);
            renderer.write(s.getName());
        }
    }
}
