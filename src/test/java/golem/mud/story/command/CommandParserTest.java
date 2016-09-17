package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.story.das.model.PathComponentDO;
import static golem.mud.common.enums.Direction.*;

public class CommandParserTest {

    @Test
    public void testEvaluate() {
        CommandDictionary dictionary = new CommandDictionary();
        List<PathComponentDO> paths = new ArrayList<>();
        PathComponentDO path = new PathComponentDO();
        path.setName("door");
        path.setDirection(SOUTH);
        paths.add(path);
        dictionary.clearContext();
        dictionary.addContextuals(paths);
        CommandParser parser = new CommandParser(dictionary);

        assertFalse(parser.evaluate("walk"));
        assertTrue(parser.evaluate("walk south"));
        assertTrue(parser.evaluate("walk to door"));
    }
}
