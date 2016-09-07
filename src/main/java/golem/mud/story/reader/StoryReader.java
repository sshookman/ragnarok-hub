package golem.mud.story.reader;

import java.sql.Connection;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import golem.mud.common.das.ConnectionManager;
import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;
import golem.mud.story.das.EntityDataService;
import golem.mud.story.das.DisplayComponentDataService;
import golem.mud.story.das.model.EntityDO;
import golem.mud.story.das.model.DisplayComponentDO;;

public class StoryReader {

    private TelnetRenderer renderer;
    private EntityDataService entityService;
    private DisplayComponentDataService displayService;

    public StoryReader(TelnetSession session, String storyPath) throws Exception {
        Connection story = ConnectionManager.establishConnection(storyPath); 
        this.renderer = session.getRenderer();
        this.entityService = new EntityDataService(story);
        this.displayService = new DisplayComponentDataService(story);
    }

    public void play() throws IOException {

        List<EntityDO> entities = entityService.read(new HashMap<>());
        for (EntityDO entity : entities) {
            DisplayComponentDO display = new DisplayComponentDO();
            display.setEntityId(entity.getRowId());
            List<DisplayComponentDO> displayComponents = displayService.read(display.toMap());
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
