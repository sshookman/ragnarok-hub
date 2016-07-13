package menus;

import java.util.List;
import file.FileManager;
import java.util.regex.Pattern;
import static rendering.TerminalRenderer.*;

public class PlayerMenu {

	private static final String FOLDER = "players";
	private static final Pattern NUMERIC = Pattern.compile("\\d+");

	public String select() {
		return select(0);
	}

	public String select(int page) {

		try {

			FileManager fileManager = new FileManager(FOLDER);
			
			render("Select Player: ");
			render("\n\tC: Create New Player");

			List<String> files = fileManager.getFiles();
			for (int index = (page*5); index < (page*5) + 5; index++) {
				if(index < files.size()) {
					render("\n\t" + (index+1) + ". " + files.get(index));
				}	
			}

			render("\n\n\t<< P | N >>");
			render("\n\tPage " + (page+1) + " of " + ((files.size() / 5)+1));
			String selection = prompt();
			clear();

			if (NUMERIC.matcher(selection).matches()) {
				Integer selectedFile = Integer.valueOf(selection);
				String fileTitle = files.get(Integer.valueOf(selectedFile)-1);
				return fileTitle;
			} else {
				switch (selection) {
					case "C": 
					case "c": 	return create();

					case "N":
					case "n": 	return (page+1 >= ((files.size() / 5)+1)) ? select(page) : select(page+1);

					case "P":
					case "p": 	return (page-1 < 0) ? select(page) : select(page-1);

					default: 	invalidSelection();
								return select(page);
				}					
			}

		} catch (Exception exception) {
			invalidSelection();
			return select(page);
		}
	}

	public static String create() {

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
