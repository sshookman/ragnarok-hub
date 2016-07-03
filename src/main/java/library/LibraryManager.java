package library;

import java.util.List;
import java.util.ArrayList;

public class LibraryManager {

	private static final String LIBRARY = "library";
	private List<String> stories;

	public LibraryManager() {

		stories = new ArrayList<String>();
		Files.walk(Paths.get(LIBRARY)).forEach(filePath -> {
    		if (Files.isRegularFile(filePath)) {
        		stories.add(filePath.toString());
    		}
		});
	}

	public String getStory(Integer index) {
		return stories.get(index);
	}

	public String getStoryTitle(Integer index) {
		//TODO: Regex to grab title
		return stories.get(index);	
	}
}
