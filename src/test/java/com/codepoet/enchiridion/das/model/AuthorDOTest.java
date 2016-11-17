package com.codepoet.enchiridion.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorDOTest {

	private static final AuthorDO authorClass = new AuthorDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("username")).thenReturn("NAME");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("author", authorClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		AuthorDO instance = authorClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals("NAME", instance.getUsername());
	}

	@Test
	public void testToMap() throws Exception {
		AuthorDO author = authorClass.instance(resultSet);
		Map<String, String> authorMap = author.toMap();

		assertNotNull(authorMap);
		assertEquals("\"NAME\"", authorMap.get("username"));
	}
}
