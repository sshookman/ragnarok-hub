package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.hub.das.ConnectionManager;
import golem.mud.hub.das.model.PlayerDO;

public class PlayerDataServiceTest {

    private static Connection golemMudHub;
	private static PlayerDataService playerDataService;

	@BeforeClass
	public static void setup() throws Exception {
		golemMudHub = ConnectionManager.establishConnection("test/GolemMudHubTest.gmh");
		golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
		playerDataService = new PlayerDataService(golemMudHub);
	}

    @AfterClass
    public static void teardown() throws Exception {
        golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
    }

	@Test 
	public void testPlayerDataService() throws Exception {
		testCreate();
        testAuthenticate();
		int playerRowId = testReadSearch();
		testUpdate(playerRowId);
        testRead(playerRowId);
        testDelete(playerRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		PlayerDO player = new PlayerDO();
		player.setUsername("Sean");
		player.setPassword("plaintext");
		boolean success = playerDataService.create(player);
		assertTrue(success);

		PlayerDO badPlayer = new PlayerDO();
		boolean failure = playerDataService.create(badPlayer);
		assertFalse(failure);

		boolean superFailure = playerDataService.create(null);
		assertFalse(superFailure);
	}

    public void testAuthenticate() throws Exception {
        boolean success = playerDataService.authenticate("Sean", "plaintext");
        assertTrue(success);

        boolean failure = playerDataService.authenticate("Sean", "plaintexsdf");
        assertFalse(failure);

        boolean megaFailure = playerDataService.authenticate("ME", "BAD");
        assertFalse(megaFailure);

        boolean wtFailure = playerDataService.authenticate("Hi", null);
        assertFalse(wtFailure);

        boolean nullFailure = playerDataService.authenticate(null, null);
        assertFalse(nullFailure);
    }

	public Integer testReadSearch() throws Exception {
		Integer playerRowId;
		
		List<PlayerDO> players = playerDataService.read(new HashMap<String, String>());
		assertNotNull(players);
		assertFalse(players.isEmpty());

		PlayerDO player = players.get(0);
		assertNotNull(player);
		assertEquals("Sean", player.getUsername());
		assertEquals("plaintext", player.getPassword());
		
		playerRowId = player.getRowId();

		List<PlayerDO> playersFiltered = playerDataService.read(player.toMap());
		assertNotNull(playersFiltered);
		assertFalse(playersFiltered.isEmpty());

		PlayerDO playerFiltered = playersFiltered.get(0);
		assertNotNull(playerFiltered);
		assertEquals("Sean", playerFiltered.getUsername());
		assertEquals("plaintext", playerFiltered.getPassword());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<PlayerDO> playersBadFilter= playerDataService.read(badSearch);
		assertNotNull(playersBadFilter);
		assertTrue(playersBadFilter.isEmpty());

		return playerRowId;
	}

	public void testUpdate(Integer playerRowId) throws Exception {
		PlayerDO player = new PlayerDO();
		player.setUsername("Link");
		player.setPassword("pass");
		boolean success = playerDataService.update(playerRowId, player);
		assertTrue(success);

		boolean failure = playerDataService.update(playerRowId, new PlayerDO());
		assertFalse(failure);

		boolean failObject = playerDataService.update(playerRowId, null);
		assertFalse(failObject);

		boolean failId = playerDataService.update(null, player);
		assertFalse(failId);
	}

	public void testRead(Integer playerRowId) throws Exception {
        PlayerDO player = (PlayerDO) playerDataService.read(playerRowId);
        assertNotNull(player);
        assertEquals("Link", player.getUsername());
        assertEquals("pass", player.getPassword());

        PlayerDO notFound = (PlayerDO) playerDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        PlayerDO failure = (PlayerDO) playerDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer playerRowId) throws Exception {
        boolean success = playerDataService.delete(playerRowId);
        assertTrue(success);

        boolean failure = playerDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<PlayerDO> players = playerDataService.read(new HashMap<String, String>());
		assertNotNull(players);
		assertTrue(players.isEmpty());
    }
}
