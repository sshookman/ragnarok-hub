package golem.mud.hub.util;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionUtilTest {

	@Test
	public void testEstablishConnection() throws Exception {
		Connection game = ConnectionUtil.establishConnection("server/GOLEM.gmh");
		assertNotNull(game);
		
		try {
			ConnectionUtil.establishConnection("not-a-library/NotAGame.sqlite");
			fail("Exception Expected");
		} catch (Exception exception) {
			assertNotNull(exception);
		}
	}

	@Test
	public void testInitGolemMudHub() throws Exception {
		Connection hubTest = ConnectionUtil.establishConnection("test/GolemMudHubTest.gmh");

		hubTest = ConnectionUtil.initGolemMudHub(hubTest);
		assertNotNull(hubTest);
	}

	@Test
	public void testInitGolem() throws Exception {
		Connection glmTest = ConnectionUtil.establishConnection("test/GolemTest.glm");

		glmTest = ConnectionUtil.initGolem(glmTest);
		assertNotNull(glmTest);
	}
}
