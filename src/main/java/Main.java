import java.util.List;
import java.util.ArrayList;
import model.Page;
import model.Player;
import rendering.TerminalRenderer;
import das.SQLiteDataService;
import file.FileManager;
import menus.PlayerMenu;
import menus.StoryMenu;

/**
 * Dragonfly Reader main loop.
 *
 * The Dragonfly Reader allows for stories to be loaded from the library
 * and played through the terminal.
 */
public class Main {

	private static final TerminalRenderer TR = new TerminalRenderer();
	private static SQLiteDataService dataService;
	private static PlayerMenu playerMenu;
	private static StoryMenu storyMenu;

	private static void init() {
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
		String story = storyMenu.select();
		dataService = new SQLiteDataService(story);

		//Start Game
		play();
	}

	private static String[] select(final String folder) {
		try {
	
			FileManager fileManager = new FileManager(folder);
			
			TR.render("Select: ");

			List<String> files = fileManager.getFiles();
			for (int i = 0; i < files.size(); i++) {
				TR.render("\n\t" + (i+1) + ". " + files.get(i));
			}

			String fileId = TR.prompt();
			String fileTitle = files.get(Integer.valueOf(fileId)-1);
			String filePath = fileManager.getFilePath(fileTitle);
			
			String[] selection = new String[2];
			selection[0] = fileTitle;
			selection[1] = filePath;

			return selection;

		} catch (Exception exception) {
			TR.invalidSelection();
			return select(folder);
		}
	}

	/**
	 * This is the main loop for the game.
	 * 
	 * @param pageIndex The Integer index value specifying which page to load
	 */
	private static void play() {
	}
}
