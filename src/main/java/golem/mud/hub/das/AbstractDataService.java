package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import golem.mud.hub.model.AbstractDO;

public abstract class AbstractDataService<D extends AbstractDO> {
	private final Logger LOGGER = Logger.getLogger(AbstractDataService.class.getName());

	private D dataObjectClass;
	private Connection story;

	public AbstractDataService(final D dataObject, final Connection story) {
		this.dataObjectClass = dataObject;
		this.story = story;
	}	

	private boolean executeUpdate(final String query) {
		try {
			Statement statement = story.createStatement();
			statement.executeUpdate(query);
			statement.close();
			return true;
		} catch (Exception exception) {
			LOGGER.severe("Failed to execute query: " + query);
			LOGGER.severe(exception.getMessage());
			return false;
		}
	}
	

	private List<D> executeQuery(final String query) {
		List<D> dataObjects = new ArrayList<D>();
		try {
			Statement statement = story.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				@SuppressWarnings("unchecked")
				D dataObject = (D) dataObjectClass.instance(results);
				dataObjects.add(dataObject);
			}
			results.close();
			statement.close();
		} catch (Exception exception) {
			LOGGER.severe("Failed to execute query: " + query);
			LOGGER.severe(exception.getMessage());
		}
		return dataObjects;
	}
	
	public List<D> read(Map<String, String> search) {
		String query = new QueryBuilder
			.SelectQuery(dataObjectClass.getTable())
			.whereEquals(search)
			.build();
		return executeQuery(query);
	}
	
	public AbstractDO read(final Integer rowId) {
        if (rowId == null) {
            return null;
        }
		String idString = rowId.toString();
		String query = new QueryBuilder
			.SelectQuery(dataObjectClass.getTable())
			.whereEquals("id", idString)
			.build();

		List<D> results = executeQuery(query);
		return results.isEmpty() ? null : results.get(0);
	}

	public boolean create(AbstractDO dataObject) {
		if (dataObject == null) {
			return false;
		}

		String query = new QueryBuilder
			.InsertQuery(dataObjectClass.getTable())
			.value(dataObject.toMap())
			.build();

		return executeUpdate(query);
	}

	public boolean update(final Integer rowId, final AbstractDO dataObject) {
		if (rowId == null || dataObject == null) {
			return false;
		}

		String idString = rowId.toString();
		String query = new QueryBuilder
			.UpdateQuery(dataObjectClass.getTable())
			.set(dataObject.toMap())
			.whereEquals("id", idString)
			.build();

		return executeUpdate(query);
	}

	public boolean delete(final Integer rowId) {
        if (rowId == null) {
            return false;
        }
		String idString = rowId.toString();
		String query = new QueryBuilder
			.DeleteQuery(dataObjectClass.getTable())
			.whereEquals("id", idString)
			.build();

		return executeUpdate(query);
	}
}
