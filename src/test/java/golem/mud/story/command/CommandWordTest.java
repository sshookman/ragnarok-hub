package golem.mud.story.command;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;

public class CommandWordTest {

    @Test
    public void testInstance() {
        CommandWord word = new CommandWord("run", new Integer[]{1,2}, new CommandType[]{MOVEMENT_NAMED, MOVEMENT_DIRECTIONAL}, false);

        assertNotNull(word);
        assertEquals("run", word.getWord());
        assertEquals(Integer.valueOf(1), word.getPositions()[0]);
        assertEquals(Integer.valueOf(2), word.getPositions()[1]);
        assertEquals(MOVEMENT_NAMED, word.getTypes()[0]);
        assertEquals(MOVEMENT_DIRECTIONAL, word.getTypes()[1]);
        assertFalse(word.isLastWord());
    }
}
