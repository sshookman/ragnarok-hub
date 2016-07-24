package golem.mud.hub.das;

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
}
