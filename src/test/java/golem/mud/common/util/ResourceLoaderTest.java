package golem.mud.common.util;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ResourceLoaderTest {

	private static final ResourceLoader RESOURCE_LOADER = new ResourceLoader();

	@Test
	public void testLoad() throws Exception {
		File file = RESOURCE_LOADER.load("GOLEM.sql");
		assertNotNull(file);
		
		File nullFile = RESOURCE_LOADER.load("NO_FILE.bad");
		assertNull(nullFile);
	}
}
