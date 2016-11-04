package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StateDOTest {

    private static final StateDO STATE_CLASS = new StateDO();
    private static ResultSet resultSet;

    @BeforeClass
    public static void setup() throws Exception {
        resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("location_entity_id")).thenReturn(3);
    }

    @Test
    public void testGetTable() throws Exception {
        assertEquals("state", STATE_CLASS.getTable());
    }

    @Test
    public void testInstance() throws Exception {
        StateDO instance = STATE_CLASS.instance(resultSet);

        assertNotNull(instance);
        assertEquals(Integer.valueOf(1), instance.getRowId());
        assertEquals(Integer.valueOf(3), instance.getLocationEntityId());
    }

    @Test
    public void testToMap() throws Exception {
        StateDO state = STATE_CLASS.instance(resultSet);
        Map<String, String> stateMap = state.toMap();

        assertNotNull(stateMap);
        assertEquals("3", stateMap.get("location_entity_id"));
    }
}
