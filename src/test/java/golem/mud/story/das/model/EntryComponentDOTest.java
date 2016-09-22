package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntryComponentDOTest {

	private static final EntryComponentDO entryComponentClass = new EntryComponentDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("entity_id")).thenReturn(3);
		when(resultSet.getInt("position")).thenReturn(123);
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("c_entry", entryComponentClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		EntryComponentDO instance = entryComponentClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(3), instance.getEntityId());
		assertEquals(Integer.valueOf(123), instance.getPosition());
	}

	@Test
	public void testToMap() throws Exception {
		EntryComponentDO entryComponent = entryComponentClass.instance(resultSet);
		Map<String, String> entryComponentMap = entryComponent.toMap();

		assertNotNull(entryComponentMap);
		assertEquals("3", entryComponentMap.get("entity_id"));
		assertEquals("123", entryComponentMap.get("position"));
	}
}
