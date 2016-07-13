package menus;

import java.util.List;
import menus.AbstractMenu;
import file.FileManager;
import static rendering.TerminalRenderer.*;

public class StoryMenu extends AbstractMenu {

	private static final String FOLDER = "library";
	
	@Override
	protected String getFolder() {
		return FOLDER;
	}

	@Override
	protected void renderHeader() {
		render("Select Story: ");
	}

	@Override
	protected String getFileInfo(final Integer selectedFile, final List<String> files, final FileManager fileManager) {
		String fileTitle = files.get(Integer.valueOf(selectedFile)-1);
		String filePath = fileManager.getFilePath(fileTitle);
		return filePath;
	}	
}
