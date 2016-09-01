package golem.mud.story.reader;

import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class StoryReader {

    public void play(TelnetSession session) {
        TelnetRenderer renderer = session.getRenderer();
        renderer.write("Let's Play!");
    }
}
