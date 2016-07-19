package golem.mud.hub.model;

import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestChapter {

	@Test
	public void testGetTable() {
		Chapter chapter = new Chapter();
		assertEquals("chapter", chapter.getTable());
	}
}
