package menus;

import java.util.List;
import rendering.TerminalRenderer;
import file.FileManager;
import java.util.regex.Pattern;

public class PlayerMenu {
//TODO: SEAN - Create Abstract and/or Interface for menus

	private static final String FOLDER = "players";
	private static final Pattern NUMERIC = Pattern.compile("\\d+");

	private TerminalRenderer tr;

	public PlayerMenu(TerminalRenderer tr) {
		this.tr = tr;
	}

	public String select() {
		return select(0);
	}

	public String select(int page) {

		try {

			FileManager fileManager = new FileManager(FOLDER);
			
			tr.render("Select Player: ");
			tr.render("\n\tC: Create New Player");

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
				return fileTitle;
			} else {
				switch (selection) {
					case "C": 
					case "c": 	return create(tr);

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

	public static String create(TerminalRenderer tr) {

		try {

			tr.render("Create New Player: ");

			String player = tr.prompt();
			return player;

		} catch (Exception exception) {
			tr.invalidSelection();
			return create(tr);
		}
	}
}
