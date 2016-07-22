package golem.mud.hub.das;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QueryBuilderTest {

	@Test
	public void testSelectStatement() throws Exception {
		String selectWhere = new QueryBuilder
			.SelectQuery("my_table")
			.addCondition("foo", "bar")
			.addCondition("top", "paz")
			.build();

		assertEquals("SELECT * FROM my_table WHERE top = paz AND foo = bar;", selectWhere);

		String select = new QueryBuilder
			.SelectQuery("my_table")
			.build();

		assertEquals("SELECT * FROM my_table;", select);
	}	

	@Test
	public void testInsertStatement() throws Exception {
		String insert = new QueryBuilder
			.InsertQuery("my_table")
			.addFieldValue("foo", "bar")
			.addFieldValue("top", "paz")
			.build();

		assertEquals("INSERT INTO my_table (top, foo) VALUES (paz, bar);", insert);
	}
}
