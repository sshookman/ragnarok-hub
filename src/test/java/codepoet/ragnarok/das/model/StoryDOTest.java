package codepoet.ragnarok.das.model;

import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StoryDOTest {

	private static final StoryDO storyClass = new StoryDO();
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
		assertEquals("story", storyClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		StoryDO instance = storyClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getName());
		assertEquals("PATH", instance.getPath());
	}

	@Test
	public void testToMap() throws Exception {
		StoryDO story = storyClass.instance(resultSet);
		Map<String, String> storyMap = story.toMap();

		assertNotNull(storyMap);
		assertEquals("\"NAME\"", storyMap.get("name"));
		assertEquals("\"PATH\"", storyMap.get("path"));
	}
}
