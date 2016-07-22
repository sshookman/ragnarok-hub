package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import golem.mud.hub.model.AbstractDataObject;

public abstract class AbstractDataService<D extends AbstractDataObject> {
	private final Logger logger = Logger.getLogger(AbstractDataService.class.getName());

	private D dataObjectClass;
	private Connection story;

	public AbstractDataService(final D dataObject, final Connection story) {
		this.dataObjectClass = dataObject;
		this.story = story;
	}	

	private List<D> execute(final String query) {
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
		} catch (SQLException exception) {
			logger.severe("Failed to execute query: " + query);
		}
		return dataObjects;
	}
	
	public List<D> read(Map<String, String> search) {
		String query = new QueryBuilder
			.SelectQuery(dataObjectClass.getTable())
			.whereEquals(search)
			.build();
		return execute(query);
	}
	
	public AbstractDataObject read(final Integer rowId) {
		String idString = (rowId == null) ? "NULL" : rowId.toString();
		String query = new QueryBuilder
			.SelectQuery(dataObjectClass.getTable())
			.whereEquals("id", idString)
			.build();

		List<D> results = execute(query);
		return results == null || results.isEmpty() ? null : results.get(0);
	}

	public Integer create(AbstractDataObject dataObject) {
		String query = new QueryBuilder
			.InsertQuery(dataObjectClass.getTable())
			.value(dataObject.toMap())
			.build();

		List<D> results = execute(query);
		return results == null || results.isEmpty() ? null : results.get(0).getRowId();
	}

	public Integer update(final Integer rowId, final AbstractDataObject dataObject) {
		String idString = (rowId == null) ? "NULL" : rowId.toString();
		String query = new QueryBuilder
			.UpdateQuery(dataObjectClass.getTable())
			.set(dataObject.toMap())
			.whereEquals("id", idString)
			.build();

		List<D> results = execute(query);
		return results == null || results.isEmpty() ? null : results.get(0).getRowId();
	}

	public Boolean delete(final Integer rowId) {
		return false;
	}
}
