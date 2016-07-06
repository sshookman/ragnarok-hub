package menus;

import java.util.List;
import rendering.TerminalRenderer;
import file.FileManager;

public class PlayerMenu {

	private static final String FOLDER = "players";

	public static String select(TerminalRenderer tr) {

		try {
	
			FileManager fileManager = new FileManager(FOLDER);
			
			tr.render("Select Player: ");
			tr.render("\n\tC: Create New Player");

			List<String> files = fileManager.getFiles();
			for (int i = 0; i < files.size(); i++) {
				tr.render("\n\t" + (i+1) + ". " + files.get(i));
			}

			String selection = tr.prompt();
			return selection;

		} catch (Exception exception) {
			tr.invalidSelection();
			return select(tr);
		}
	}
}
