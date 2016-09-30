package golem.mud.story.reader;

import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.hub.telnet.TelnetSession;
import golem.mud.story.das.StoryAggregateDataService;
import golem.mud.story.das.model.DisplayComponentDO;
import java.io.IOException;

public class StoryReader {

    private final TelnetRenderer renderer;
    private final StoryAggregateDataService services;

    public StoryReader(TelnetSession session, String storyPath) throws Exception {
        this.renderer = session.getRenderer();
        this.services = new StoryAggregateDataService(storyPath);
    }

    public void play() throws IOException {

        DisplayComponentDO entry = services.getStartingPoint();
        renderer.write(entry.getMessage());
        renderer.endl(1);

        String commandString = "";
        while (!"quit".equals(commandString)) {
            renderer.endl(1);
            renderer.write(" > ");
            commandString = renderer.read();
        }
    }
}
