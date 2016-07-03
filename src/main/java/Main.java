import java.util.List;
import java.util.ArrayList;
import model.Page;
import model.Player;
import das.PageDataService;
import rendering.TerminalRenderer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import das.SQLiteDataService;

public class Main {

	private static final String PROMPT = "> ";
	private static final String SPACER = "\n\n";
	private static final TerminalRenderer TR = new TerminalRenderer();
	private static final PageDataService PAGE_DAS = new PageDataService();

	private static Player player;

	private static SQLiteDataService dataService;

	/**
	 * This is the main entrypoint for the application.
	 *
	 * This method is where all of the game data is loaded. Here the user
	 * selects the game they wish to play and the account they will be
	 * using.
	 */
	public static void main(String[] args) throws IOException {

		List<String> stories = new ArrayList<String>();
		Files.walk(Paths.get("library")).forEach(filePath -> {
    		if (Files.isRegularFile(filePath)) {
        		stories.add(filePath.toString());
    		}
		});

		TR.write("Select a Story: ");
		for (int i = 0; i < stories.size(); i++) {
			TR.write("\n\t" + (i+1) + ". " + stories.get(i));
		}
		TR.write(SPACER + PROMPT);
		String storyId = TR.read();

		dataService = new SQLiteDataService(stories.get(Integer.valueOf(storyId) - 1));

		TR.write("Enter your name hero..." + SPACER + PROMPT);
		player = new Player(TR.read());

		gameLoop(-1);
	}

	/**
	 * This is the main loop for the game.
	 * 
	 * This loop loads a page, displays the message and prompts for user input
	 * if it is needed. Based on the user input, a new page index may be found
	 * and the loop starts again with the new page.
	 *
	 * @param pageIndex The Integer index value specifying which page to load
	 */
	private static void gameLoop(Integer pageIndex) {
		Page page = PAGE_DAS.getPage(pageIndex);
		TR.write(page.getMessage());

		if (page.isPrompt()) {
			TR.write(SPACER + player.getName() + PROMPT);
			String input = TR.read();
			
			//TODO: Should not be exiting like this
			if (input.equalsIgnoreCase("exit")) {
				return;
			}

			pageIndex = (page.getPageBinding(input) != null) ? page.getPageBinding(input) : pageIndex;
		}

		gameLoop(pageIndex);
	}
}
