package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

	private static final Player playerClass = new Player();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("name")).thenReturn("NAME");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("player", playerClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		Player instance = playerClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getName());
	}

	@Test
	public void testToMap() throws Exception {
		Player player = playerClass.instance(resultSet);
		Map<String, String> playerMap = player.toMap();

		assertNotNull(playerMap);
		assertEquals("1", playerMap.get("id"));
		assertEquals("NAME", playerMap.get("name"));
	}
}
