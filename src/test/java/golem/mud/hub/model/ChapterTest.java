package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.ResultSet;

public class ChapterTest {

	private static final Chapter chapterClass = new Chapter();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("prev_chapter_id")).thenReturn(5);
		when(resultSet.getString("title")).thenReturn("TITLE");
		when(resultSet.getString("body")).thenReturn("BODY");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("chapter", chapterClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		Chapter instance = chapterClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(5), instance.getPrevChapterRowId());
		assertEquals("TITLE", instance.getTitle());
		assertEquals("BODY", instance.getBody());
	}
}
