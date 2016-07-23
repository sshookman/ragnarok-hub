package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(AbstractDataService.class.getName());

	public static Connection getConnection(final String dbFile) throws Exception {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		} catch (Exception exception) {
			LOGGER.severe("Failed to Connect to Database: " + exception.getMessage());
			throw exception;
		}
	}
}
