package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import golem.mud.common.exception.CommandException;
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

        assertTrue(parser.evaluate("walk south"));
        assertTrue(parser.evaluate("walk to door"));

        try {
            parser.evaluate("walk");
            fail("CommandException expected");
        } catch (CommandException exception) {
            assertNotNull(exception);
            assertEquals("I do not understand.", exception.getMessage());
            assertEquals("walk", exception.getCommand());
        }
    }
}
