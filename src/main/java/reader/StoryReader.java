package reader;

import java.util.List;
import java.sql.Connection;
import model.Page;
import model.Chapter;
import rendering.TerminalRenderer;
import das.ChapterDataService;
import das.PageDataService;

public class StoryReader {

	public void play(Connection story) {

		ChapterDataService chapterService = new ChapterDataService(story);
		PageDataService pageService = new PageDataService(story);

		List<Chapter> chapters = chapterService.readChapters(null);
		TerminalRenderer.renderTyped(chapters.get(0).getBody());
		TerminalRenderer.prompt();
		TerminalRenderer.clear();

		List<Page> pages = pageService.readPages(1);
		for (Page page : pages) {
			TerminalRenderer.render(page.getTitle() + "\n\n");
			TerminalRenderer.renderTyped(page.getBody());
			TerminalRenderer.prompt();
			TerminalRenderer.clear();
		}	
	}
}
