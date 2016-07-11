import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import model.Page;
import model.Player;
import model.Chapter;
import rendering.TerminalRenderer;
import das.SQLiteDataService;
import file.FileManager;
import menus.PlayerMenu;
import menus.StoryMenu;
import das.ChapterDataService;
import das.PageDataService;

/**
 * Dragonfly Reader main loop.
 *
 * The Dragonfly Reader allows for stories to be loaded from the library
 * and played through the terminal.
 */
public class Main {

	private static final TerminalRenderer TR = new TerminalRenderer();

	private static Connection story;
	private static PlayerMenu playerMenu;
	private static StoryMenu storyMenu;

	private static void init() {
		//TODO: SEAN - Use Spring (maybe something less heavy) for Dependency Injection?
		playerMenu = new PlayerMenu(TR);
		storyMenu = new StoryMenu(TR);
	}

	/**
	 * This is the main entrypoint for the application.
	 *
	 * This method is where all of the game data is loaded. Here the user
	 * selects the game they wish to play and the account they will be
	 * using.
	 */
	public static void main(String[] args) {
		init();
		
		//Select Player
		String player = playerMenu.select();
		TR.setPlayer(player);

		//Select Story
		String storyPath = storyMenu.select();
		story = SQLiteDataService.getConnection(storyPath);

		//Start Game
		play();
	}

	/**
	 * This should not live in the main class
	 */
	private static void play() {
		ChapterDataService chapterService = new ChapterDataService(story);
		PageDataService pageService = new PageDataService(story);

		List<Chapter> chapters = chapterService.readChapters(null);
		TR.render(chapters.get(0).getBody() + "\n\n");

		List<Page> pages = pageService.readPages(1);
		for (Page page : pages) {
			TR.render(page.getTitle() + "\n\n");
			TR.render(page.getBody() + "\n\n");
		}	
	}
}
