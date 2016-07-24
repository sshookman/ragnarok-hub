package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerDataServiceTest {

	private static PlayerDataService playerDataService;

	@BeforeClass
	public static void setup() throws Exception {
		//Connection golemMudHub = ConnectionFactory.getConnection("test/playerDataServiceTest.gmh");
		//playerDataService = new PlayerDataService(golemMudHub);
	}

	@Test 
	public void testPlayerDataService() throws Exception {
		testInsert();
		testReadSearch();
		testRead();
		testUpdate();
		testRead();
		testDelete();
		testReadSearch();
	}

	public void testReadSearch() throws Exception {
		
	}

	public void testRead() throws Exception {

	}

	public void testInsert() throws Exception {

	}

	public void testUpdate() throws Exception {

	}

	public void testDelete() throws Exception {

	}
}
