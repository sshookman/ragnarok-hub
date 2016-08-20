package golem.mud.hub.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavedDOTest {

	private static final SavedDO savedClass = new SavedDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("name")).thenReturn("NAME");
		when(resultSet.getString("path")).thenReturn("PATH");
		when(resultSet.getInt("player_id")).thenReturn(2);
		when(resultSet.getInt("golem_id")).thenReturn(3);
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("saved", savedClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		SavedDO instance = savedClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getName());
		assertEquals("PATH", instance.getPath());
		assertEquals(Integer.valueOf(2), instance.getPlayerId());
		assertEquals(Integer.valueOf(3), instance.getGolemId());
	}

	@Test
	public void testToMap() throws Exception {
		SavedDO saved = savedClass.instance(resultSet);
		Map<String, String> savedMap = saved.toMap();

		assertNotNull(savedMap);
		assertEquals("\"NAME\"", savedMap.get("name"));
		assertEquals("\"PATH\"", savedMap.get("path"));
		assertEquals("2", savedMap.get("player_id"));
		assertEquals("3", savedMap.get("golem_id"));
	}

	@Test
	public void testToMapNulls() throws Exception {
		SavedDO saved = new SavedDO();
        saved.setName("NAME");
        saved.setPlayerId(2);
		Map<String, String> savedMap = saved.toMap();

		assertNotNull(savedMap);
		assertEquals("\"NAME\"", savedMap.get("name"));
		assertNull(savedMap.get("path"));
		assertEquals("2", savedMap.get("player_id"));
		assertNull(savedMap.get("golem_id"));
	}
}
