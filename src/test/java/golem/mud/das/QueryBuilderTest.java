package golem.mud.das;

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QueryBuilderTest {

    @Test
    public void testInstantiate() {
        assertNotNull(new QueryBuilder());
    }

	@Test
	public void testSelectQuery() throws Exception {
		String selectWhere = new QueryBuilder
			.SelectQuery("my_table")
			.whereEquals("foo", "bar")
			.whereEquals("top", "paz")
			.build();

		assertEquals("SELECT * FROM my_table WHERE top=paz AND foo=bar;", selectWhere);

		String select = new QueryBuilder
			.SelectQuery("my_table")
			.build();

		assertEquals("SELECT * FROM my_table;", select);

		Map<String, String> fieldValues = new HashMap<String, String>();
		fieldValues.put("foo", "bar");
		fieldValues.put("woot", "boot");
		String selectWithMap = new QueryBuilder
			.SelectQuery("my_table")
			.whereEquals(fieldValues)
			.build();

		assertEquals("SELECT * FROM my_table WHERE woot=boot AND foo=bar;", selectWithMap);
	}	

	@Test
	public void testInsertQuery() throws Exception {
		String insertSingle = new QueryBuilder
			.InsertQuery("my_table")
			.value("foo", "bar")
			.build();

		assertEquals("INSERT INTO my_table (foo) VALUES (bar);", insertSingle);

		String insert = new QueryBuilder
			.InsertQuery("my_table")
			.value("top", "paz")
			.value("foo", "bar")
			.build();

		assertEquals("INSERT INTO my_table (top,foo) VALUES (paz,bar);", insert);

		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("top", "paz");
		mapData.put("foo", "bar");
		String insertMap = new QueryBuilder
			.InsertQuery("my_table")
			.value(mapData)
			.build();

		assertEquals("INSERT INTO my_table (top,foo) VALUES (paz,bar);", insertMap);
	}

	@Test
	public void testUpdateQuery() throws Exception {
		String updateWhere = new QueryBuilder
			.UpdateQuery("my_table")
			.set("top", "paz")
			.set("wam", "bam")
			.whereEquals("foo", "bar")
			.whereEquals("doh", "ray")
			.build();

		assertEquals("UPDATE my_table SET top=paz,wam=bam WHERE foo=bar AND doh=ray;", updateWhere);

		String update = new QueryBuilder
			.UpdateQuery("my_table")
			.set("top", "paz")
			.build();

		assertEquals("UPDATE my_table SET top=paz;", update);

		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("top", "paz");
		mapData.put("wam", "bam");
		String updateMap = new QueryBuilder
			.UpdateQuery("my_table")
			.set(mapData)
			.build();

		assertEquals("UPDATE my_table SET wam=bam,top=paz;", updateMap);
	}

	@Test
	public void testDeleteQuery() throws Exception {
		String deleteWhere = new QueryBuilder
			.DeleteQuery("my_table")
			.whereEquals("foo", "bar")
			.whereEquals("top", "paz")
			.build();

		assertEquals("DELETE FROM my_table WHERE top=paz AND foo=bar;", deleteWhere);

		String delete = new QueryBuilder
			.DeleteQuery("my_table")
			.build();

		assertEquals("DELETE FROM my_table;", delete);
	}
}
