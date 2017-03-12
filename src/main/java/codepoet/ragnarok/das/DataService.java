package codepoet.ragnarok.das;

import codepoet.ragnarok.das.model.AbstractDO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataService<D extends AbstractDO> {

	private static final Logger LOGGER = Logger.getLogger(DataService.class.getName());

	private final Connection connection;
	private final D dataObjectClass;

	public DataService(final D dataObject, final Connection connection) {
		this.dataObjectClass = dataObject;
		this.connection = connection;
	}

	private Boolean executeUpdate(final String query) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			return true;
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Failed to execute query: {0}", query);
			LOGGER.log(Level.SEVERE, exception.getMessage());
			return false;
		}
	}

	private List<D> executeQuery(final String query) {
		List<D> dataObjects = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				@SuppressWarnings("unchecked")
				D dataObject = (D) dataObjectClass.instance(results);
				dataObjects.add(dataObject);
			}
			results.close();
			statement.close();
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Failed to execute query: {0}", query);
			LOGGER.log(Level.SEVERE, exception.getMessage());
		}
		return dataObjects;
	}

	public List<D> read(Map<String, String> search) {
		if (search == null) {
			return null;
		}

		String query = new QueryBuilder.SelectQuery(dataObjectClass.getTable())
				.whereEquals(search)
				.build();

		return executeQuery(query);
	}

	public AbstractDO read(final Integer rowId) {
		if (rowId == null) {
			return null;
		}

		String idString = rowId.toString();
		String query = new QueryBuilder.SelectQuery(dataObjectClass.getTable())
				.whereEquals("id", idString)
				.build();

		List<D> results = executeQuery(query);
		return results.isEmpty() ? null : results.get(0);
	}

	public Boolean create(AbstractDO dataObject) {
		if (dataObject == null) {
			return null;
		}

		String query = new QueryBuilder.InsertQuery(dataObjectClass.getTable())
				.value(dataObject.toMap())
				.build();

		return executeUpdate(query);
	}

	public Boolean update(final Integer rowId, final AbstractDO dataObject) {
		if (rowId == null || dataObject == null) {
			return null;
		}

		String idString = rowId.toString();
		String query = new QueryBuilder.UpdateQuery(dataObjectClass.getTable())
				.set(dataObject.toMap())
				.whereEquals("id", idString)
				.build();

		return executeUpdate(query);
	}

	public Boolean delete(final Integer rowId) {
		if (rowId == null) {
			return null;
		}

		String idString = rowId.toString();
		String query = new QueryBuilder.DeleteQuery(dataObjectClass.getTable())
				.whereEquals("id", idString)
				.build();

		return executeUpdate(query);
	}
}
