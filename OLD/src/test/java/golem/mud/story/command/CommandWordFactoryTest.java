
package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import golem.mud.common.enums.CommandType;
import golem.mud.story.das.model.PathComponentDO;
import static golem.mud.common.enums.CommandType.*;
import static golem.mud.common.enums.Direction.*;

public class CommandWordFactoryTest {

    @Test
    public void testInstantiate() {
        assertNotNull(new CommandWordFactory());
    }

    @Test
    public void testBuildCommandWordsFromPath() {
        PathComponentDO path = new PathComponentDO();
        path.setName("door");
        path.setDirection(SOUTH);

        List<CommandWord> words = CommandWordFactory.buildCommandWords(path);
        assertNotNull(words);
        assertEquals(2, words.size());

        CommandWord namedWord = words.get(0);
        CommandWord directWord = words.get(1);

        assertNotNull(namedWord);
        assertEquals("door", namedWord.getWord());
        assertEquals(1, namedWord.getPositions().length);
        assertEquals(Integer.valueOf(3), namedWord.getPositions()[0]);
        assertEquals(1, namedWord.getTypes().length);
        assertEquals(MOVEMENT_NAMED, namedWord.getTypes()[0]);
        assertTrue(namedWord.isLastWord());

        assertNotNull(directWord);
        assertEquals("south", directWord.getWord());
        assertEquals(1, directWord.getPositions().length);
        assertEquals(Integer.valueOf(2), directWord.getPositions()[0]);
        assertEquals(1, directWord.getTypes().length);
        assertEquals(MOVEMENT_DIRECTIONAL, directWord.getTypes()[0]);
        assertTrue(directWord.isLastWord());
    }
}
