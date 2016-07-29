package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GolemDOTest {

	private static final GolemDO golemClass = new GolemDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("name")).thenReturn("NAME");
		when(resultSet.getString("path")).thenReturn("PATH");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("golem", golemClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		GolemDO instance = golemClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getName());
		assertEquals("PATH", instance.getPath());
	}

	@Test
	public void testToMap() throws Exception {
		GolemDO golem = golemClass.instance(resultSet);
		Map<String, String> golemMap = golem.toMap();

		assertNotNull(golemMap);
		assertEquals("\"NAME\"", golemMap.get("name"));
		assertEquals("\"PATH\"", golemMap.get("path"));
	}
}
