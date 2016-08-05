package golem.mud.hub.das;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionManagerTest {

	@Test
	public void testEstablishConnection() throws Exception {
		Connection game = ConnectionManager.establishConnection("server/GOLEM.gmh");
		assertNotNull(game);
		
		try {
			ConnectionManager.establishConnection("not-a-library/NotAGame.sqlite");
			fail("Exception Expected");
		} catch (Exception exception) {
			assertNotNull(exception);
		}
	}

	@Test
	public void testInitGolemMudHub() throws Exception {
		Connection hubTest = ConnectionManager.establishConnection("test/GolemMudHubTest.gmh");

		hubTest = ConnectionManager.initGolemMudHub(hubTest);
		assertNotNull(hubTest);
	}

	@Test
	public void testInitGolem() throws Exception {
		Connection glmTest = ConnectionManager.establishConnection("test/GolemTest.glm");

		glmTest = ConnectionManager.initGolem(glmTest);
		assertNotNull(glmTest);
	}
}
