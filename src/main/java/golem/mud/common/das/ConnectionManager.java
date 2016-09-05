package golem.mud.common.das;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

import golem.mud.common.util.ResourceLoader;

public class ConnectionManager {
	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
	private static final ResourceLoader RESOURCE_LOADER = new ResourceLoader();

	private static Connection init(final Connection connection, final String fileName) throws FileNotFoundException, SQLException {
		String query = getFile(fileName);
		Statement statement = connection.createStatement();
		statement.executeUpdate(query);
		statement.close();
		return connection;
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

	public static Connection initGolemMudHub(final Connection connection) throws FileNotFoundException, SQLException {
		return init(connection, "GOLEM-MUD-HUB.sql");
	}

	public static Connection initGolem(final Connection connection) throws FileNotFoundException, SQLException {
		return init(connection, "GOLEM.sql");
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
