package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DisplayComponentDOTest {

	private static final DisplayComponentDO displayComponentClass = new DisplayComponentDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("entity_id")).thenReturn(3);
		when(resultSet.getString("message")).thenReturn("MESSAGE");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("c_display", displayComponentClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		DisplayComponentDO instance = displayComponentClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(3), instance.getEntityId());
		assertEquals("MESSAGE", instance.getMessage());
	}

	@Test
	public void testToMap() throws Exception {
		DisplayComponentDO displayComponent = displayComponentClass.instance(resultSet);
		Map<String, String> displayComponentMap = displayComponent.toMap();

		assertNotNull(displayComponentMap);
		assertEquals("3", displayComponentMap.get("entity_id"));
		assertEquals("\"MESSAGE\"", displayComponentMap.get("message"));
	}
}
