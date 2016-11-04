package golem.mud.story.command;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;
import static golem.mud.common.enums.Direction.*;
import golem.mud.story.das.model.PathComponentDO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommandDictionaryTest {

	private static CommandDictionary dictionary;

	@BeforeClass
	public static void setup() {
		dictionary = new CommandDictionary();
	}

	@Test
	public void testSearch() {
		List<CommandType> types = new ArrayList<>();
		types.add(MOVEMENT_NAMED);
		List<CommandWord> results = dictionary.search("walk", 1, types);
		assertNotNull(results);
		assertEquals(1, results.size());
		assertCommandWord(results.get(0), "walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);

		PathComponentDO path = new PathComponentDO();
		path.setName("door");
		path.setDirection(SOUTH);

		List<PathComponentDO> paths = new ArrayList<>(1);
		paths.add(path);

		dictionary.clearContext();
		dictionary.addContextuals(paths);

		List<CommandWord> contextuals = dictionary.search("door", 3, types);
		assertNotNull(contextuals);
		assertEquals(1, contextuals.size());
		assertCommandWord(contextuals.get(0), "door", new Integer[]{3}, new CommandType[]{MOVEMENT_NAMED}, true);
	}

	@Test
	public void testMovementGlobals() {
		List<CommandWord> globals = dictionary.getGlobals();

		int x = 0;
		assertNotNull(globals);
		assertEquals(7, globals.size());
		assertCommandWord(globals.get(x++), "quit", new Integer[]{1}, new CommandType[]{QUIT}, true);
		assertCommandWord(globals.get(x++), "go", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
		assertCommandWord(globals.get(x++), "walk", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
		assertCommandWord(globals.get(x++), "run", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
		assertCommandWord(globals.get(x++), "move", new Integer[]{1}, new CommandType[]{MOVEMENT_DIRECTIONAL, MOVEMENT_NAMED}, false);
		assertCommandWord(globals.get(x++), "to", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false);
		assertCommandWord(globals.get(x++), "toward", new Integer[]{2}, new CommandType[]{MOVEMENT_NAMED}, false);
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

		dictionary.clearContext();
		List<CommandWord> emptyContext = dictionary.getContextuals();
		assertNotNull(emptyContext);
		assertTrue(emptyContext.isEmpty());
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
