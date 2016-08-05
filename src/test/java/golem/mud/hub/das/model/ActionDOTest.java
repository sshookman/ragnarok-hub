package golem.mud.hub.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActionDOTest {

	private static final ActionDO actionClass = new ActionDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("type")).thenReturn("TYPE");
		when(resultSet.getInt("screen_id")).thenReturn(2);
		when(resultSet.getInt("golem_id")).thenReturn(3);
		when(resultSet.getString("property")).thenReturn("PROPERTY");
		when(resultSet.getString("value")).thenReturn("VALUE");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("action", actionClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		ActionDO instance = actionClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("TYPE", instance.getType());
		assertEquals(Integer.valueOf(2), instance.getScreenId());
		assertEquals(Integer.valueOf(3), instance.getGolemId());
		assertEquals("PROPERTY", instance.getProperty());
		assertEquals("VALUE", instance.getValue());
	}

	@Test
	public void testToMap() throws Exception {
		ActionDO action = actionClass.instance(resultSet);
		Map<String, String> actionMap = action.toMap();

		assertNotNull(actionMap);
		assertEquals("\"TYPE\"", actionMap.get("type"));
		assertEquals("2", actionMap.get("screen_id"));
		assertEquals("3", actionMap.get("golem_id"));
		assertEquals("\"PROPERTY\"", actionMap.get("property"));
		assertEquals("\"VALUE\"", actionMap.get("value"));
	}
}
