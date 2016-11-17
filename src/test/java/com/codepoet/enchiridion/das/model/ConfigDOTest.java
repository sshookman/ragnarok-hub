package com.codepoet.enchiridion.das.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConfigDOTest {

	private static final ConfigDO configClass = new ConfigDO();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("player_id")).thenReturn(2);
		when(resultSet.getInt("text_speed")).thenReturn(3);
		when(resultSet.getString("text_color")).thenReturn("#aa00aa");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("config", configClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		ConfigDO instance = configClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(2), instance.getPlayerId());
		assertEquals(Integer.valueOf(3), instance.getTextSpeed());
		assertEquals("#aa00aa", instance.getTextColor());
	}

	@Test
	public void testToMap() throws Exception {
		ConfigDO config = configClass.instance(resultSet);
		Map<String, String> configMap = config.toMap();

		assertNotNull(configMap);
		assertEquals("2", configMap.get("player_id"));
		assertEquals("3", configMap.get("text_speed"));
		assertEquals("\"#aa00aa\"", configMap.get("text_color"));
	}
}
