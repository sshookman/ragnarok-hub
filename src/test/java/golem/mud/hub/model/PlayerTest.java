package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.ResultSet;

public class PlayerTest {

	private static final Player playerClass = new Player();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("name")).thenReturn("NAME");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("player", playerClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		Player instance = playerClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getName());
	}
}
