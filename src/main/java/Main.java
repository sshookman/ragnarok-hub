import java.util.List;
import java.sql.Connection;
import model.Page;
import model.Chapter;
import rendering.TerminalRenderer;
import menus.PlayerMenu;
import menus.StoryMenu;
import das.SQLiteDataService;
import das.ChapterDataService;
import das.PageDataService;
import reader.StoryReader;
import server.TelnetServer;

public class Main {

/*
	private static final StoryReader READER = new StoryReader();
	private static final PlayerMenu PLAYER_MENU = new PlayerMenu();
	private static final StoryMenu STORY_MENU = new StoryMenu();
*/
	public static void main(String[] args) {
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}

	/*public static void main(String[] args) {

		TerminalRenderer.clear();
		TerminalRenderer.setPlayer(PLAYER_MENU.select());

		final Connection story = SQLiteDataService.getConnection(STORY_MENU.select());

		READER.play(story);
	}*/
}
