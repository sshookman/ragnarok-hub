package golem.mud.hub.util;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileUtilTest {

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
        Map<String, String> files = FileUtil.list("test");
        assertNotNull(files);
        assertEquals(2, files.size());
        assertEquals("test/GolemMudHubTest.gmh", files.get("GolemMudHubTest.gmh"));
        assertEquals("test/GolemTest.glm", files.get("GolemTest.glm"));
	}

	@Test
	public void testListFolderExtensionGLM() throws Exception {
        Map<String, String> files = FileUtil.list("test", ".glm");
        assertNotNull(files);
        assertEquals(1, files.size());
        assertEquals("test/GolemTest.glm", files.get("GolemTest"));
	}
    
	@Test
	public void testListFolderExtensionGMH() throws Exception {
        Map<String, String> files = FileUtil.list("test", ".gmh");
        assertNotNull(files);
        assertEquals(1, files.size());
        assertEquals("test/GolemMudHubTest.gmh", files.get("GolemMudHubTest"));
	}
}
