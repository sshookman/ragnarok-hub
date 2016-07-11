package menus;

import java.util.List;
import rendering.TerminalRenderer;
import file.FileManager;
import java.util.regex.Pattern;

public class StoryMenu {

	private static final String FOLDER = "library";
	private static final Pattern NUMERIC = Pattern.compile("\\d+");

	private TerminalRenderer tr;

	public StoryMenu(TerminalRenderer tr) {
		this.tr = tr;
	}

	public String select() {
		return select(0);
	}

	public String select(int page) {

		try {

			FileManager fileManager = new FileManager(FOLDER);
			
			tr.render("Select Story: ");

			List<String> files = fileManager.getFiles();
			for (int index = (page*5); index < (page*5) + 5; index++) {
				if(index < files.size()) {
					tr.render("\n\t" + (index+1) + ". " + files.get(index));
				}	
			}

			tr.render("\n\n\t<< P | N >>");
			tr.render("\n\tPage " + (page+1) + " of " + ((files.size() / 5)+1));
			String selection = tr.prompt();
			tr.clear();

			if (NUMERIC.matcher(selection).matches()) {
				Integer selectedFile = Integer.valueOf(selection);
				String fileTitle = files.get(Integer.valueOf(selectedFile)-1);
				String filePath = fileManager.getFilePath(fileTitle);
				return filePath;
			} else {
				switch (selection) {
					case "N":
					case "n": 	return (page+1 >= ((files.size() / 5)+1)) ? select(page) : select(page+1);

					case "P":
					case "p": 	return (page-1 < 0) ? select(page) : select(page-1);

					default: 	tr.invalidSelection();
								return select(page);
				}					
			}

		} catch (Exception exception) {
			tr.invalidSelection();
			return select(page);
		}
	}
}
