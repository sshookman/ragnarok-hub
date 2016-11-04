package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PickupComponentDOTest {

	private static final PickupComponentDO displayComponentClass = new PickupComponentDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("entity_id")).thenReturn(3);
		when(resultSet.getBoolean("stackable")).thenReturn(true);
		when(resultSet.getInt("quantity")).thenReturn(5);
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("c_pickup", displayComponentClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		PickupComponentDO instance = displayComponentClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(3), instance.getEntityId());
		assertTrue(instance.isStackable());
		assertEquals(Integer.valueOf(5), instance.getQuantity());
	}

	@Test
	public void testToMap() throws Exception {
		PickupComponentDO displayComponent = displayComponentClass.instance(resultSet);
		Map<String, String> displayComponentMap = displayComponent.toMap();

		assertNotNull(displayComponentMap);
		assertEquals("3", displayComponentMap.get("entity_id"));
		assertEquals("True", displayComponentMap.get("stackable"));
		assertEquals("5", displayComponentMap.get("quantity"));
	}
}
