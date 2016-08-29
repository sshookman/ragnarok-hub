package golem.mud.util;

import java.io.File;
import java.util.logging.Logger;

public class ResourceLoader {
	private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());

	public File load(final String resource) {
		File file = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			file = new File(classLoader.getResource(resource).getFile());
		} catch (Exception exception) {
			LOGGER.severe("Failed to load resouce: " + resource);
		}
		
		return file;
	}
}
