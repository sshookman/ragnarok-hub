package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import golem.mud.common.enums.Direction;

public class PathComponentDOTest {

	private static final PathComponentDO pathComponentClass = new PathComponentDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("entity_id")).thenReturn(3);
		when(resultSet.getInt("dest_entity_id")).thenReturn(5);
		when(resultSet.getString("name")).thenReturn("NAME");
		when(resultSet.getString("direction")).thenReturn("NORTH");
		when(resultSet.getString("display_message")).thenReturn("DISPLAY MESSAGE");
		when(resultSet.getString("transition_message")).thenReturn("TRANSITION MESSAGE");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("c_path", pathComponentClass.getTable());
	}

    @Test
    public void testDefaultMessages() {
        PathComponentDO path = new PathComponentDO();
        path.setName("door");
        path.setDirection(Direction.SOUTHEAST);

        assertEquals("There is a door to the southeast.", path.getDisplayMessage());
        assertEquals("You go southeast.", path.getTransitionMessage());
    }

	@Test
	public void testInstance() throws Exception {
		PathComponentDO instance = pathComponentClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(3), instance.getEntityId());
		assertEquals(Integer.valueOf(5), instance.getDestEntityId());
		assertEquals("NAME", instance.getName());
		assertEquals(Direction.NORTH, instance.getDirection());
		assertEquals("DISPLAY MESSAGE", instance.getDisplayMessage());
		assertEquals("TRANSITION MESSAGE", instance.getTransitionMessage());
	}

	@Test
	public void testToMap() throws Exception {
		PathComponentDO pathComponent = pathComponentClass.instance(resultSet);
		Map<String, String> pathComponentMap = pathComponent.toMap();

		assertNotNull(pathComponentMap);
		assertEquals("3", pathComponentMap.get("entity_id"));
		assertEquals("5", pathComponentMap.get("dest_entity_id"));
		assertEquals("\"NAME\"", pathComponentMap.get("name"));
		assertEquals("\"NORTH\"", pathComponentMap.get("direction"));
		assertEquals("\"DISPLAY MESSAGE\"", pathComponentMap.get("display_message"));
		assertEquals("\"TRANSITION MESSAGE\"", pathComponentMap.get("transition_message"));
	}
}
