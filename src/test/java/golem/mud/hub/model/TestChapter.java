package golem.mud.hub.model;

import java.sql.ResultSet;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestChapter {

	@Test
	public void testGetTable() {
		Chapter chapter = new Chapter();
		assertEquals("chapter", chapter.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("prev_chapter_id")).thenReturn(5);
		when(resultSet.getString("title")).thenReturn("TITLE");
		when(resultSet.getString("body")).thenReturn("BODY");

		Chapter chapterClass = new Chapter();
		Chapter instance = chapterClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getId());
		assertEquals(Integer.valueOf(5), instance.getPrevChapterId());
		assertEquals("TITLE", instance.getTitle());
		assertEquals("BODY", instance.getBody());
	}
}
