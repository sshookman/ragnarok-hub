package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerDOTest {

	private static final PlayerDO playerClass = new PlayerDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("username")).thenReturn("NAME");
		when(resultSet.getString("password")).thenReturn("PASS");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("player", playerClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		PlayerDO instance = playerClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getUsername());
		assertEquals("PASS", instance.getPassword());
	}

	@Test
	public void testToMap() throws Exception {
		PlayerDO player = playerClass.instance(resultSet);
		Map<String, String> playerMap = player.toMap();

		assertNotNull(playerMap);
		assertEquals("1", playerMap.get("id"));
		assertEquals("NAME", playerMap.get("username"));
		assertEquals("PASS", playerMap.get("password"));
	}
}
