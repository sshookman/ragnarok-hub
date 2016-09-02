package golem.mud.util;

import java.util.Map;
import java.io.File;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileUtilTest {

    private static final String PATH = "src/test/resources/library";
    private static File file1;
    private static File file2;

    @BeforeClass
    public static void setup() throws Exception {
        file1 = new File("src/test/resources/library/one.test");
        file2 = new File("src/test/resources/library/two.tst");
        file1.createNewFile();
        file2.createNewFile();
    }

    @AfterClass
    public static void teardown() throws Exception {
        file1.delete();
        file2.delete();
    }

    @Test
    public void testInstantiate() {
        assertNotNull(new FileUtil());
    }

	@Test
	public void testListInvalidFolder() throws Exception {
        Map<String, String> files = FileUtil.list("invalid");
        assertNotNull(files);
        assertTrue(files.isEmpty());
    }

	@Test
	public void testListFolder() throws Exception {
        Map<String, String> files = FileUtil.list("src/test/resources/library");
        assertNotNull(files);
        assertEquals(3, files.size());
        assertEquals("src/test/resources/library/one.test", files.get("one.test"));
        assertEquals("src/test/resources/library/two.tst", files.get("two.tst"));
        assertEquals("src/test/resources/library/.gitkeep", files.get(".gitkeep"));
	}

	@Test
	public void testListFolderExtensionGLM() throws Exception {
        Map<String, String> files = FileUtil.list(PATH, ".test");
        assertNotNull(files);
        assertEquals(1, files.size());
        assertEquals("src/test/resources/library/one.test", files.get("one"));
	}
    
	@Test
	public void testListFolderExtensionGMH() throws Exception {
        Map<String, String> files = FileUtil.list(PATH, ".tst");
        assertNotNull(files);
        assertEquals(1, files.size());
        assertEquals(PATH + "/two.tst", files.get("two"));
	}
}
