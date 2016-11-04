package golem.mud.story.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntityDOTest {

	private static final EntityDO entityClass = new EntityDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("entity", entityClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		EntityDO instance = entityClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
	}

	@Test
	public void testToMap() throws Exception {
		EntityDO entity = entityClass.instance(resultSet);
		Map<String, String> entityMap = entity.toMap();

		assertNotNull(entityMap);
		assertEquals("null", entityMap.get("id"));
	}
}
