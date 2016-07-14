package menus;

import java.util.List;
import file.FileManager;
import java.util.regex.Pattern;
import static rendering.TerminalRenderer.*;

public abstract class AbstractMenu {

	private static final Pattern NUMERIC = Pattern.compile("\\d+");

	protected abstract String getFolder();

	protected abstract String getFileInfo(final Integer selectedFile, final List<String> files, final FileManager fileManager);

	protected Integer getPageSize() {
		return 5;
	}

	protected void renderHeader() {
		render("Select: ");
	}

	protected String additionalSelections(final String selection, final Integer page) {
		invalidSelection();
		return select(page);
	}

	public String select() {
		return select(0);
	}

	public String select(final int page) {

		try {
			final String folder = getFolder();
			final Integer pageSize = getPageSize();

			FileManager fileManager = new FileManager(folder);
			renderHeader();

			List<String> files = fileManager.getFiles();
			for (int index = (page*pageSize); index < (page*pageSize) + pageSize; index++) {
				if(index < files.size()) {
					render("\n\t" + (index+1) + ". " + files.get(index));
				}	
			}

			render("\n\n\t<< P | N >>");
			render("\n\tPage " + (page+1) + " of " + ((files.size() / pageSize)+1));
			String selection = prompt();
			clear();

			if (NUMERIC.matcher(selection).matches()) {
				Integer selectedFile = Integer.valueOf(selection);
				return getFileInfo(selectedFile, files, fileManager);
			} else {
				switch (selection) {
					case "N":
					case "n": 
						return (page+1 >= ((files.size() / pageSize)+1)) ? select(page) : select(page+1);
					case "P":
					case "p":
						return (page-1 < 0) ? select(page) : select(page-1);
					default: 	
						return additionalSelections(selection, page);
				}					
			}

		} catch (Exception exception) {
			invalidSelection();
			return select(page);
		}
	}
}
