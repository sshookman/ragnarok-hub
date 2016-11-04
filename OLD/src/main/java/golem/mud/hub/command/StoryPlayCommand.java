package golem.mud.hub.command;

import java.util.List;

import golem.mud.common.telnet.TelnetSession;
import golem.mud.hub.das.StoryDataService;
import golem.mud.hub.das.model.StoryDO;
import golem.mud.common.command.CommandResponse;

public class StoryPlayCommand extends AbstractStoryCommand {

    private final StoryDataService storyDataService;
    private String command;

    public StoryPlayCommand(final TelnetSession session) {
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

    public CommandResponse execute() {
        String[] parts = command.split(" ");
        String storyName = "";
        for (int x = 2; x < parts.length; x++) {
            if (x != 2) {
                storyName += " ";
            }
            storyName += parts[x];
        }

        StoryDO search = new StoryDO();
        search.setName(storyName);
        List<StoryDO> stories = storyDataService.read(search.toMap());
        String storyPath = null;
        if (stories != null && !stories.isEmpty()) {
            StoryDO story = stories.get(0);
            storyPath = story.getPath();
        }

        return new CommandResponse(storyPath);
    }
}
