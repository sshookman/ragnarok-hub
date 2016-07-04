package library;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class LibraryManager {

	private static final String FOLDER = "library";
	private Map<String, String> stories;

	public LibraryManager() {

		stories = new HashMap<String, String>();
		try {
		Files.walk(Paths.get(FOLDER)).forEach(filePath -> {
    		if (Files.isRegularFile(filePath)) {
        		stories.put(getStoryTitle(filePath.toString()), filePath.toString());
    		}
		});
		} catch (IOException exception) {
			System.out.println("Failed to load library: " + exception.getMessage());
		}
	}

	public List<String> getStories() {
		List<String> storyTitles = new ArrayList<String>();
		storyTitles.addAll(stories.keySet());
		return storyTitles;
	}

	public String getStoryPath(String key) {
		return stories.get(key);
	}

	private String getStoryTitle(final String storyPath) {
		String storyTitle = storyPath;
		storyTitle = storyTitle.replace(".sqlite", "");
		storyTitle = storyTitle.replace(FOLDER + "/", "");
		storyTitle = storyTitle.replaceAll("_", " ");

		return storyTitle;
	}
}
