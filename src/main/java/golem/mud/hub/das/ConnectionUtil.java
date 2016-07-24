package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionUtil {
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class.getName());

	private static void init(final Connection connection, final String query) {
		try {
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
			statement.close();
		} catch (SQLException exception) {
			LOGGER.severe("Failed to init: " + query);
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

		init(connection, "");
		return connection;
	}

	public static Connection initGolem(final Connection connection) {

		init(connection, "");
		return connection;
	}
}
