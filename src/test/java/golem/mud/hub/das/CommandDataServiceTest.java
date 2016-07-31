package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.hub.util.ConnectionUtil;
import golem.mud.hub.model.CommandDO;

public class CommandDataServiceTest {

	private static CommandDataService commandDataService;

	@BeforeClass
	public static void setup() throws Exception {
		Connection golemMudHub = ConnectionUtil.establishConnection("test/GolemMudHubTest.gmh");
		golemMudHub = ConnectionUtil.initGolemMudHub(golemMudHub);
		commandDataService = new CommandDataService(golemMudHub);
	}

	@Test 
	public void testCommandDataService() throws Exception {
		testCreate();
		int rowId = testReadSearch();
		testUpdate(rowId);
        testRead(rowId);
        testDelete(rowId);
        testEmpty();
	}

	public Integer testReadSearch() throws Exception {
		Integer rowId;
		
		List<CommandDO> commands = commandDataService.read(new HashMap<String, String>());
		assertNotNull(commands);
		assertFalse(commands.isEmpty());

		CommandDO command = commands.get(0);
		assertNotNull(command);
		assertEquals("Save", command.getName());
		assertEquals("sean", command.getTargets());
		
		rowId = command.getRowId();

		List<CommandDO> commandsFiltered = commandDataService.read(command.toMap());
		assertNotNull(commandsFiltered);
		assertFalse(commandsFiltered.isEmpty());

		CommandDO commandFiltered = commandsFiltered.get(0);
		assertNotNull(commandFiltered);
		assertEquals("Save", commandFiltered.getName());
		assertEquals("sean", commandFiltered.getTargets());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<CommandDO> commandsBadFilter= commandDataService.read(badSearch);
		assertNotNull(commandsBadFilter);
		assertTrue(commandsBadFilter.isEmpty());

		return rowId;
	}

	public void testRead(Integer rowId) throws Exception {
        CommandDO command = (CommandDO) commandDataService.read(rowId);
        assertNotNull(command);
        assertEquals("Help", command.getName());
        assertEquals("commands,games", command.getTargets());

        CommandDO notFound = (CommandDO) commandDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        CommandDO failure = (CommandDO) commandDataService.read(nullId);
        assertNull(failure);
	}

	public void testCreate() throws Exception {
		CommandDO command = new CommandDO();
		command.setName("Save");
		command.setTargets("sean");
		boolean success = commandDataService.create(command);
		assertTrue(success);

		CommandDO badCommand = new CommandDO();
		boolean failure = commandDataService.create(badCommand);
		assertFalse(failure);

		boolean superFailure = commandDataService.create(null);
		assertFalse(superFailure);
	}

	public void testUpdate(Integer rowId) throws Exception {
		CommandDO command = new CommandDO();
		command.setName("Help");
		command.setTargets("commands,games");
		boolean success = commandDataService.update(rowId, command);
		assertTrue(success);

		boolean failure = commandDataService.update(rowId, new CommandDO());
		assertFalse(failure);

		boolean failObject = commandDataService.update(rowId, null);
		assertFalse(failObject);

		boolean failId = commandDataService.update(null, command);
		assertFalse(failId);
	}

	public void testDelete(Integer rowId) throws Exception {
        boolean success = commandDataService.delete(rowId);
        assertTrue(success);

        boolean failure = commandDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<CommandDO> commands = commandDataService.read(new HashMap<String, String>());
		assertNotNull(commands);
		assertTrue(commands.isEmpty());
    }
}
