package golem.mud.hub.das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import golem.mud.hub.model.DataObject;

public abstract class AbstractDataService<D extends DataObject> {
	private final Logger logger = Logger.getLogger(AbstractDataService.class.getName());

	private D dataObjectClass;
	private Connection story;

	public AbstractDataService(final D dataObject, final Connection story) {
		this.dataObjectClass = dataObject;
		this.story = story;
	}	
	
	public List<D> read(Map<String, Object> search) {

		List<D> dataObjects = new ArrayList<D>();
		try {
			Statement statement = story.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + dataObjectClass.getTable());
			while (results.next()) {
				@SuppressWarnings("unchecked")
				D dataObject = (D) dataObjectClass.instance(results);
				dataObjects.add(dataObject);
			}
			results.close();
			statement.close();
		} catch (SQLException exception) {
			logger.warning("Failed to read from table " + dataObjectClass.getTable());
		}
		return dataObjects;
	}
	
	public DataObject read(final Integer id) {
		return null;
	}

	public Integer create(DataObject dataObject) {
		return 0;
	}

	public Integer update(final Integer id, final DataObject dataObject) {
		return 0;
	}

	public Boolean delete(final Integer id) {
		return false;
	}
}
