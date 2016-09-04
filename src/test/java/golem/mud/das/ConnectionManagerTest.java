package golem.mud.das;

import java.sql.Connection;
import java.io.File;
import org.junit.Test;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionManagerTest {

    private static final String PATH = "src/test/resources/library/";

    @AfterClass
    public static void teardown() {
        File file1 = new File(PATH + "/GolemMudHubTest.gmh");
        File file2 = new File(PATH + "/GolemTest.glm");
        file1.delete();
        file2.delete();
    }

    @Test
    public void testInstantiate() {
        assertNotNull(new ConnectionManager());
    }

	@Test
	public void testNotFoundException() throws Exception {
		try {
			ConnectionManager.establishConnection("not-a-library/NotAGame.sqlite");
			fail("Exception Expected");
		} catch (Exception exception) {
			assertNotNull(exception);
		}
	}

	@Test
	public void testInitGolemMudHub() throws Exception {
		Connection hubTest = ConnectionManager.establishConnection(PATH + "GolemMudHubTest.gmh");
		assertNotNull(hubTest);

		hubTest = ConnectionManager.initGolemMudHub(hubTest);
		assertNotNull(hubTest);
	}

	@Test
	public void testInitGolem() throws Exception {
		Connection glmTest = ConnectionManager.establishConnection(PATH + "GolemTest.glm");

		glmTest = ConnectionManager.initGolem(glmTest);
		assertNotNull(glmTest);
	}
}
