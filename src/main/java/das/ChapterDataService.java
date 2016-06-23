package das;

import java.util.Map;
import java.util.HashMap;
import model.Chapter;

public class ChapterDataService {

	private Map<Integer, Chapter> chapters;

	public ChapterDataService() {
		chapters = new HashMap<Integer, Chapter>();

		//TODO: Remove - Testing Code Only
		Chapter chapter0 = new Chapter("Shadows Come Out to Play", "Thedarkness approaches. You shall suffer and dies slowly puny mortal flesh-bag!");
		Chapter chapter1 = new Chapter("Darkness Still There", "Yup! It's still dark and scary and shit.");
			
		chapters.put(0, chapter0);
		chapters.put(1, chapter1);
	}

	public Chapter getChapter(Integer id) {
		return chapters.get(id);
	}

	public void addChapter(Integer id, Chapter chapter) {
		chapters.put(id, chapter);
	}
}
