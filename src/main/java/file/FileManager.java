package file;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileManager {
	
	private String folder = "";
	private Map<String, String> files;

	public FileManager(String folder) {
		this.folder = folder;

		files = new HashMap<String, String>();
		try {
		Files.walk(Paths.get(folder)).forEach(filePath -> {
    		if (Files.isRegularFile(filePath)) {
        		files.put(getFileTitle(filePath.toString()), filePath.toString());
    		}
		});
		} catch (IOException exception) {
			System.out.println("Failed to load files: " + exception.getMessage());
		}
	}

	public List<String> getFiles() {
		List<String> fileTitles = new ArrayList<String>();
		fileTitles.addAll(files.keySet());
		return fileTitles;
	}

	public String getFilePath(String key) {
		return files.get(key);
	}

	private String getFileTitle(final String filePath) {
		String fileTitle = filePath;
		fileTitle = fileTitle.replace(".sqlite", "");
		fileTitle = fileTitle.replace(folder + "/", "");
		fileTitle = fileTitle.replaceAll("_", " ");
		return fileTitle;
	}
}
