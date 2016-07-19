package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.ResultSet;

public class TestPage {

	private static final Page pageClass = new Page();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("chapter_id")).thenReturn(5);
		when(resultSet.getString("title")).thenReturn("TITLE");
		when(resultSet.getString("body")).thenReturn("BODY");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("page", pageClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		Page instance = pageClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getId());
		assertEquals(Integer.valueOf(5), instance.getChapterId());
		assertEquals("TITLE", instance.getTitle());
		assertEquals("BODY", instance.getBody());
	}
}
