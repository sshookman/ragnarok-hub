package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void testMatches() {
        List<CommandType> typeNamed = new ArrayList<>();
        List<CommandType> typeDirectional = new ArrayList<>();
        List<CommandType> typeEmpty = new ArrayList<>();
        List<CommandType> typePickup = new ArrayList<>();

        typeNamed.add(MOVEMENT_NAMED);
        typeDirectional.add(MOVEMENT_DIRECTIONAL);
        typePickup.add(OBJECT_PICKUP);

        CommandWord word = new CommandWord("walk", new Integer[]{1,2}, new CommandType[]{MOVEMENT_NAMED, MOVEMENT_DIRECTIONAL}, false);

        assertTrue(word.matches("walk", 2, typeNamed));
        assertTrue(word.matches("walk", 1, typeNamed));
        assertTrue(word.matches("walk", 1, typeDirectional));
        assertTrue(word.matches("walk", 1, typeEmpty));
        assertTrue(word.matches("walk", 1, null));

        assertFalse(word.matches("run", 1, typeDirectional));
        assertFalse(word.matches("walk", 3, typeDirectional));
        assertFalse(word.matches("walk", 2, typePickup));

        CommandWord nullNameWord = new CommandWord(null, new Integer[]{1,2}, new CommandType[]{MOVEMENT_NAMED, MOVEMENT_DIRECTIONAL}, false);
        assertFalse(nullNameWord.matches("walk", 2, typeNamed));

        CommandWord nullPositionsWord = new CommandWord("walk", null, new CommandType[]{MOVEMENT_NAMED, MOVEMENT_DIRECTIONAL}, false);
        
        assertFalse(nullPositionsWord.matches("walk", 2, typeNamed));

        CommandWord nullTypesWord = new CommandWord(null, new Integer[]{1,2}, null, false);
        assertFalse(nullTypesWord.matches("walk", 2, typeNamed));
    }
}
