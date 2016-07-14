package menus;

import java.util.List;
import menus.AbstractMenu;
import file.FileManager;
import static rendering.TerminalRenderer.*;

public class PlayerMenu extends AbstractMenu {

	private static final String FOLDER = "players";

	@Override
	protected String getFolder() {
		return FOLDER;
	}

	@Override
	protected void renderHeader() {
		render("Select Player: ");
		render("\n\tC. Create New Player\n");
	}

	@Override
	protected String getFileInfo(final Integer selectedFile, final List<String> files, final FileManager fileManager) {
		String fileTitle = files.get(Integer.valueOf(selectedFile)-1);
		return fileTitle;
	}

	@Override
	protected String additionalSelections(final String selection, final Integer page) {
		switch (selection) {
			case "C":
			case "c":
				return create();
			default:
				invalidSelection();
				return select(page);
		}
	}

	private static String create() {

		try {

			render("Create New Player: ");
			String player = prompt();
			clear();
			return player;
		} catch (Exception exception) {

			invalidSelection();
			return create();
		}
	}
}
