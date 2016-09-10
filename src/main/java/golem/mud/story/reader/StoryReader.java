package golem.mud.story.reader;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.story.das.StoryAggregateDataService;
import golem.mud.story.das.model.EntityDO;
import golem.mud.story.das.model.DisplayComponentDO;;

public class StoryReader {

    private TelnetRenderer renderer;
    private StoryAggregateDataService services;

    public StoryReader(TelnetSession session, String storyPath) throws Exception {
        this.renderer = session.getRenderer();
        this.services = new StoryAggregateDataService(storyPath);
    }

    public void play() throws IOException {

        List<EntityDO> entities = services.entityService.read(new HashMap<>());
        for (EntityDO entity : entities) {
            DisplayComponentDO display = new DisplayComponentDO();
            display.setEntityId(entity.getRowId());
            List<DisplayComponentDO> displayComponents = services.displayService.read(display.toMap());
            renderer.write(displayComponents.get(0).getMessage());
            renderer.endl(1);
        }

        String commandString = ""; 
        while (!"quit".equals(commandString)) {
            renderer.endl(1);
            renderer.write(" > ");
            commandString = renderer.read();
        }
    }
}
