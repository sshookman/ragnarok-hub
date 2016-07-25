package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import golem.mud.hub.util.ConnectionUtil;
import golem.mud.hub.model.PlayerDO;

public class PlayerDataServiceTest {

	private static PlayerDataService playerDataService;

	@BeforeClass
	public static void setup() throws Exception {
		Connection golemMudHub = ConnectionUtil.establishConnection("test/GolemMudHubTest.gmh");
		golemMudHub = ConnectionUtil.initGolemMudHub(golemMudHub);
		playerDataService = new PlayerDataService(golemMudHub);
	}

	@Test 
	public void testPlayerDataService() throws Exception {
		testCreate();
		testReadSearch();
		testRead();
		testUpdate();
		testRead();
		testDelete();
		testReadSearch();
	}

	public void testReadSearch() throws Exception {
		List<PlayerDO> players = playerDataService.read(new HashMap<String, String>());
		assertNotNull(players);
	}

	public void testRead() throws Exception {

	}

	public void testCreate() throws Exception {
		PlayerDO player = new PlayerDO();
		player.setUsername("Sean");
		player.setPassword("plaintext");

		playerDataService.create(player);
	}

	public void testUpdate() throws Exception {

	}

	public void testDelete() throws Exception {

	}
}
