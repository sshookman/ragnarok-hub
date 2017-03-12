package codepoet.ragnarok.das;

import java.io.File;
import java.sql.Connection;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ConnectionManagerTest {

	private static final String PATH = "src/test/resources/library/";

	@AfterClass
	public static void teardown() {
		File file1 = new File(PATH + "/GolemMudHubTest.gmh");
		File file2 = new File(PATH + "/GolemTest.glm");
		File file3 = new File(PATH + "/Save.sglm");
		file1.delete();
		file2.delete();
		file3.delete();
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

		try {
			ConnectionManager.establishConnectionInMemory("not-a-library/NotAGame.sqlite");
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

	@Test
	public void testInitSaveGame() throws Exception {
		Connection sglmTest = ConnectionManager.establishConnection(PATH + "Save.sglm");

		sglmTest = ConnectionManager.initSaveGame(sglmTest);
		assertNotNull(sglmTest);
	}

	@Test
	public void testEstablishConnectionInMemory() throws Exception {
		Connection memory = ConnectionManager.establishConnectionInMemory(PATH + "GolemTest.glm");
		assertNotNull(memory);
	}
}
