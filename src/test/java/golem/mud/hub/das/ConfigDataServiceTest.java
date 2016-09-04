package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.das.ConnectionManager;
import golem.mud.hub.das.model.ConfigDO;

public class ConfigDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

    private static Connection golemMudHub;
	private static ConfigDataService configDataService;

	@BeforeClass
	public static void setup() throws Exception {
		golemMudHub = ConnectionManager.establishConnection(PATH + "GolemMudHubTest.gmh");
		golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
		configDataService = new ConfigDataService(golemMudHub);
	}

    @AfterClass
    public static void teardown() throws Exception {
        File file = new File(PATH + "GolemMudHubTest.gmh");
        file.delete();
    }

	@Test 
	public void testConfigDataService() throws Exception {
		testCreate();
		int rowId = testReadSearch();
		testUpdate(rowId);
        testRead(rowId);
        testDelete(rowId);
        testEmpty();
	}

	public Integer testReadSearch() throws Exception {
		Integer rowId;
		
		List<ConfigDO> configs = configDataService.read(new HashMap<String, String>());
		assertNotNull(configs);
		assertFalse(configs.isEmpty());

		ConfigDO config = configs.get(0);
		assertNotNull(config);
        assertEquals(Integer.valueOf(123), config.getPlayerId());
        assertEquals(Integer.valueOf(2), config.getTextSpeed());
        assertEquals("#aa00aa", config.getTextColor());
		
		rowId = config.getRowId();

		List<ConfigDO> configsFiltered = configDataService.read(config.toMap());
		assertNotNull(configsFiltered);
		assertFalse(configsFiltered.isEmpty());

		ConfigDO configFiltered = configsFiltered.get(0);
		assertNotNull(configFiltered);
        assertEquals(Integer.valueOf(123), config.getPlayerId());
        assertEquals(Integer.valueOf(2), config.getTextSpeed());
        assertEquals("#aa00aa", config.getTextColor());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<ConfigDO> configsBadFilter= configDataService.read(badSearch);
		assertNotNull(configsBadFilter);
		assertTrue(configsBadFilter.isEmpty());

		return rowId;
	}

	public void testRead(Integer rowId) throws Exception {
        ConfigDO config = (ConfigDO) configDataService.read(rowId);
        assertNotNull(config);
        assertEquals(Integer.valueOf(321), config.getPlayerId());
        assertEquals(Integer.valueOf(3), config.getTextSpeed());
        assertEquals("#ff00ff", config.getTextColor());

        ConfigDO notFound = (ConfigDO) configDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        ConfigDO failure = (ConfigDO) configDataService.read(nullId);
        assertNull(failure);
	}

	public void testCreate() throws Exception {
		ConfigDO config = new ConfigDO();
		config.setPlayerId(123);
		config.setTextSpeed(2);
		config.setTextColor("#aa00aa");
		boolean success = configDataService.create(config);
		assertTrue(success);

		ConfigDO badConfig = new ConfigDO();
		boolean failure = configDataService.create(badConfig);
		assertFalse(failure);

		boolean superFailure = configDataService.create(null);
		assertFalse(superFailure);
	}

	public void testUpdate(Integer rowId) throws Exception {
		ConfigDO config = new ConfigDO();
		config.setPlayerId(321);
		config.setTextSpeed(3);
		config.setTextColor("#ff00ff");
		boolean success = configDataService.update(rowId, config);
		assertTrue(success);

		boolean failure = configDataService.update(rowId, new ConfigDO());
		assertFalse(failure);

		boolean failObject = configDataService.update(rowId, null);
		assertFalse(failObject);

		boolean failId = configDataService.update(null, config);
		assertFalse(failId);
	}

	public void testDelete(Integer rowId) throws Exception {
        boolean success = configDataService.delete(rowId);
        assertTrue(success);

        boolean failure = configDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<ConfigDO> configs = configDataService.read(new HashMap<String, String>());
		assertNotNull(configs);
		assertTrue(configs.isEmpty());
    }
}
