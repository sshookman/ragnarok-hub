import java.util.List;
import java.util.ArrayList;
import model.Page;
import model.Player;
import rendering.TerminalRenderer;
import das.SQLiteDataService;
import library.LibraryManager;
import player.PlayerManager;

/**
 * Dragonfly Reader main loop.
 *
 * The Dragonfly Reader allows for stories to be loaded from the library
 * and played through the terminal.
 */
public class Main {

	private static final TerminalRenderer TR = new TerminalRenderer();
	private static final LibraryManager LIB = new LibraryManager();
	private static final PlayerManager PLY = new PlayerManager();

	private static Player player;
	private static SQLiteDataService dataService;

	/**
	 * This is the main entrypoint for the application.
	 *
	 * This method is where all of the game data is loaded. Here the user
	 * selects the game they wish to play and the account they will be
	 * using.
	 */
	public static void main(String[] args) {
		
		selectPlayer();
		selectStory();
	}

	private static void selectPlayer() {
		
		try {
			TR.render("Select a Player: ");

			List<String> players = PLY.getPlayers();
			for (int i = 0; i < players.size(); i++) {
				TR.render("\n\t" + (i+1) + ". " + players.get(i));
			}

			String playerId = TR.prompt();
			String playerTitle = players.get(Integer.valueOf(playerId)-1);

			TR.setPlayer(playerTitle);
		} catch (Exception exception) {
			TR.render("!!! Invalid Selection !!!\n");
			selectPlayer();
		}
	}

	private static void selectStory() {

		try {
			TR.render("Select a Story: ");
			List<String> stories = LIB.getStories();
			for (int i = 0; i < stories.size(); i++) {
				TR.render("\n\t" + (i+1) + ". " + stories.get(i));
			}

			String storyId = TR.prompt();
			String storyTitle = stories.get(Integer.valueOf(storyId)-1);

			dataService = new SQLiteDataService(LIB.getStoryPath(storyTitle));
		} catch (Exception exception) {
			TR.render("!!! Invalid Selection !!!\n");
			selectStory();
		}

	}

	/**
	 * This is the main loop for the game.
	 * 
	 * @param pageIndex The Integer index value specifying which page to load
	 */
	private static void gameLoop(Integer pageIndex) {
	}
}
