package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandDOTest {

	private static final CommandDO commandClass = new CommandDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("screen_id")).thenReturn(2);
		when(resultSet.getInt("action_id")).thenReturn(3);
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("command", commandClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		CommandDO instance = commandClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(2), instance.getScreenId());
		assertEquals(Integer.valueOf(3), instance.getActionId());
	}

	@Test
	public void testToMap() throws Exception {
		CommandDO player = commandClass.instance(resultSet);
		Map<String, String> playerMap = player.toMap();

		assertNotNull(playerMap);
		assertEquals("2", playerMap.get("screen_id"));
		assertEquals("3", playerMap.get("action_id"));
	}
}
