package golem.mud.hub.das;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionFactoryTest {

	@Test
	public void testGetConnection() throws Exception {
		Connection game = ConnectionFactory.getConnection("server/GOLEM.gmh");
		assertNotNull(game);
		
		try {
			ConnectionFactory.getConnection("not-a-library/NotAGame.sqlite");
			fail("Exception Expected");
		} catch (Exception exception) {
			assertNotNull(exception);
		}
	}
}
