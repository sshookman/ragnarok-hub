package golem.mud.hub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConnectionUtil {
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class.getName());
	private static final ResourceLoader RESOURCE_LOADER = new ResourceLoader();

	private static void init(final Connection connection, final String fileName) {
		try {
			String query = getFile(fileName);
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
			statement.close();
		} catch (FileNotFoundException exception) {
			LOGGER.severe("Failed to load: " + fileName);
		} catch (SQLException exception) {
			LOGGER.severe("Failed to init: " + fileName);
		}
	}

	public static Connection establishConnection(final String dbFile) throws Exception {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		} catch (Exception exception) {
			LOGGER.severe("Failed to Connect to Database: " + exception.getMessage());
			throw exception;
		}
	}

	public static Connection initGolemMudHub(final Connection connection) {
		init(connection, "GOLEM-MUD-HUB.sql");
		return connection;
	}

	public static Connection initGolem(final Connection connection) {
		init(connection, "GOLEM.sql");
		return connection;
	}

	private static String getFile(String fileName) throws FileNotFoundException {
		StringBuilder result = new StringBuilder("");
		File file = RESOURCE_LOADER.load(fileName);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			result.append(scanner.nextLine()).append("\n");
		}

		return result.toString();
	}
}
