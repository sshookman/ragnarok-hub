package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PathComponentDOTest {

	private static final PathComponentDO pathComponentClass = new PathComponentDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("entity_id")).thenReturn(3);
		when(resultSet.getString("name")).thenReturn("NAME");
		when(resultSet.getString("message")).thenReturn("MESSAGE");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("c_path", pathComponentClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		PathComponentDO instance = pathComponentClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(3), instance.getEntityId());
		assertEquals("NAME", instance.getName());
		assertEquals("MESSAGE", instance.getMessage());
	}

	@Test
	public void testToMap() throws Exception {
		PathComponentDO pathComponent = pathComponentClass.instance(resultSet);
		Map<String, String> pathComponentMap = pathComponent.toMap();

		assertNotNull(pathComponentMap);
		assertEquals("3", pathComponentMap.get("entity_id"));
		assertEquals("\"NAME\"", pathComponentMap.get("name"));
		assertEquals("\"MESSAGE\"", pathComponentMap.get("message"));
	}
}
