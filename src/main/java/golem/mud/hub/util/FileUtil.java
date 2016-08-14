package golem.mud.hub.util;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileUtil {
	private final static Logger LOGGER = Logger.getLogger(FileUtil.class.getName());
    
    public static Map<String, String> list(final String folder) {
        return list(folder, null);
    }

    public static Map<String, String> list(final String folderPath, final String extension) {
        Map<String, String> files = new HashMap<String, String>();

        try {
            Files.walk(Paths.get(folderPath)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.put(getFileTitle(folderPath, filePath.toString(), extension), filePath.toString());
                }
            });
        } catch (IOException exception) {
            LOGGER.severe("Failed to load files: " + exception.getMessage());
        }

        return files;
    }

    private static String getFileTitle(final String folderPath, final String filePath, final String extension) {
        String fileTitle = filePath;
        fileTitle = fileTitle.replace(extension, "");
        fileTitle = fileTitle.replace(folderPath + "/", "");
        fileTitle = fileTitle.replaceAll("_", " ");
        return fileTitle;
    }
}
