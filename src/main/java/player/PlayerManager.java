package player;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class PlayerManager {

	private static final String FOLDER = "players";
	private Map<String, String> players;

	public PlayerManager() {

		players = new HashMap<String, String>();
		try {
		Files.walk(Paths.get(FOLDER)).forEach(filePath -> {
    		if (Files.isRegularFile(filePath)) {
        		players.put(getPlayerName(filePath.toString()), filePath.toString());
    		}
		});
		} catch (IOException exception) {
			System.out.println("Failed to load players: " + exception.getMessage());
		}

	}

	public List<String> getPlayers() {
		List<String> playerNames = new ArrayList<String>();
		playerNames.addAll(players.keySet());
		return playerNames;
	}

	public String getPlayerPath(String key) {
		return players.get(key);
	}

	private String getPlayerName(final String playerPath) {
		String playerName = playerPath;
		playerName = playerName.replace(".sqlite", "");
		playerName = playerName.replace(FOLDER + "/", "");
		playerName = playerName.replaceAll("_", " ");

		return playerName;
	}
}
