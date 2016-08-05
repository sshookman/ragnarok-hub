package golem.mud.hub.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScreenDOTest {

	private static final ScreenDO screenClass = new ScreenDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("message")).thenReturn("MESSAGE");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("screen", screenClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		ScreenDO instance = screenClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("MESSAGE", instance.getMessage());
	}

	@Test
	public void testToMap() throws Exception {
		ScreenDO screen = screenClass.instance(resultSet);
		Map<String, String> screenMap = screen.toMap();

		assertNotNull(screenMap);
		assertEquals("\"MESSAGE\"", screenMap.get("message"));
	}
}
