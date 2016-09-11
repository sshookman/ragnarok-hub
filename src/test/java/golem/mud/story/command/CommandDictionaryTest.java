package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import golem.mud.common.enums.CommandType;
import golem.mud.story.das.model.PathComponentDO;

import static golem.mud.common.enums.CommandType.*;
import static golem.mud.common.enums.Direction.*;

public class CommandDictionaryTest {

    private static CommandDictionary dictionary;

    @BeforeClass
    public static void setup() {
        dictionary = new CommandDictionary();
    }

    @Test
    public void testMovementGlobals() {
        List<CommandWord> globals = dictionary.getGlobals();

        assertNotNull(globals);
        assertEquals(6, globals.size());
        assertCommandWord(globals.get(0), "go", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
        assertCommandWord(globals.get(1), "walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
        assertCommandWord(globals.get(2), "run", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
        assertCommandWord(globals.get(3), "move", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
        assertCommandWord(globals.get(4), "to", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false);
        assertCommandWord(globals.get(5), "toward", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false);
    }

    @Test
    public void testContextuals() {

        PathComponentDO path1 = new PathComponentDO();
        path1.setName("door");
        path1.setDirection(SOUTH);

        PathComponentDO path2 = new PathComponentDO();
        path2.setName("window");
        path2.setDirection(EAST);

        List<PathComponentDO> paths = new ArrayList<>(2);
        paths.add(path1);
        paths.add(path2);

        dictionary.clearContext();
        dictionary.addContextuals(paths);

        List<CommandWord> contextuals = dictionary.getContextuals();
        assertNotNull(contextuals);
        assertEquals(4, contextuals.size());
        assertCommandWord(contextuals.get(0), "door", new Integer[]{3}, new CommandType[]{MOVEMENT_NAMED}, true);
        assertCommandWord(contextuals.get(1), "south", new Integer[]{2}, new CommandType[]{MOVEMENT_DIRECTIONAL}, true);
        assertCommandWord(contextuals.get(2), "window", new Integer[]{3}, new CommandType[]{MOVEMENT_NAMED}, true);
        assertCommandWord(contextuals.get(3), "east", new Integer[]{2}, new CommandType[]{MOVEMENT_DIRECTIONAL}, true);
    }

    private void assertCommandWord(CommandWord commandWord, String word, Integer[] positions, CommandType[] types, Boolean lastWord) {
        assertNotNull(commandWord);
        assertEquals(word, commandWord.getWord());
        assertEquals(positions.length, commandWord.getPositions().length);
        for (int x = 0; x < positions.length; x++) {
            assertEquals(positions[x], commandWord.getPositions()[x]);
        }
        assertEquals(types.length, commandWord.getTypes().length);
        for (int x = 0; x < types.length; x++) {
            assertEquals(types[x], commandWord.getTypes()[x]);
        }
        assertEquals(lastWord, commandWord.isLastWord());
    }
}
