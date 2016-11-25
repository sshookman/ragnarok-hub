package com.codepoet.enchiridion.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FileUtil {

	private final static Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

	public static Map<String, String> list(final String folder) {
		return list(folder, null);
	}

	public static Map<String, String> list(final String folder, final String extension) {
		Map<String, String> files = new HashMap<String, String>();

		try {
			Files.walk(Paths.get(folder)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					String fileTitle = getFileTitle(folder, filePath.toString(), extension);
					if (fileTitle != null) {
						files.put(fileTitle, filePath.toString());
					}
				}
			});
		} catch (IOException exception) {
			LOGGER.severe("Failed to load files: " + exception.getMessage());
		}

		return files;
	}

	private static String getFileTitle(final String folder, final String filePath, final String extension) {
		String fileTitle = filePath;
		if (extension != null) {
			if (!fileTitle.contains(extension)) {
				return null;
			}
			fileTitle = fileTitle.replace(extension, "");
		}
		fileTitle = fileTitle.replace(folder + "/", "");
		fileTitle = fileTitle.replaceAll("_", " ");
		return fileTitle;
	}
}
